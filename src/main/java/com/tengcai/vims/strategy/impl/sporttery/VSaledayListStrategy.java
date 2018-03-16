package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.entity.sporttery.V_SALEDAY;
import com.tengcai.vims.service.sporttery.LOTO_BNService;
import com.tengcai.vims.service.sporttery.LOTO_FNService;
import com.tengcai.vims.service.sporttery.T_LOTO_ENService;
import com.tengcai.vims.service.sporttery.V_SALEDAYService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;

/**
 *查询首页日期栏列表
 * @return 
 * @throws Exception 
 */
@Component("v_saleday_list")
public class VSaledayListStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(VSaledayListStrategy.class);
	public static class SALEDAY_Data{
		public List<SALEDAY_DETAIL> lst=new ArrayList<SALEDAY_DETAIL>();
	}
	public static class SALEDAY_DETAIL{
		public String saleday; //
		public Integer position;
		public String endtime;//结束日期
		public String week_name;
		public Integer match_count;//比赛数目
		public Integer rem_count;//推荐的比赛数目
	}
	@Autowired
	private V_SALEDAYService vSaledayService;
	@Autowired
	private LOTO_FNService loto_fnService;
	@Autowired
	private LOTO_BNService loto_bnService;
	@Autowired
	private T_LOTO_ENService loto_enService;
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("v_saleday_list查询首页日期栏列表doJsonMethod------>");
		ReturnValue<SALEDAY_Data> rv = new ReturnValue<SALEDAY_Data>();
		rv.setData(new SALEDAY_Data());Integer.parseInt(jsonRequest.get("position").toString());
		V_SALEDAY vSaleday=new V_SALEDAY();
		vSaleday.setSport_type(Integer.parseInt(jsonRequest.get("position").toString()));
		List<V_SALEDAY> lst=null;
		try {			
			if(jsonRequest.get("position").toString().equals("4")){
				vSaleday.setEndtime(DateUtils.getDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
			}else{
				vSaleday.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
					
			}
			vSaleday.setRem_issue("");
			if(jsonRequest.get("position").toString().equals("2")){
				LOTO_F loto_fn=loto_fnService.selectRemFN();
				if(loto_fn!=null)
					vSaleday.setRem_issue(loto_fn.getIssue());
			}
			else if(jsonRequest.get("position").toString().equals("3")){
				LOTO_B loto_bn=loto_bnService.selectRemBN();
				if(loto_bn!=null)
					vSaleday.setRem_issue(loto_bn.getIssue());
			} 
			else if(jsonRequest.get("position").toString().equals("4")){
				T_LOTO_E loto_en=loto_enService.selectRemEN();
				if(loto_en!=null)
					vSaleday.setRem_issue(loto_en.getIssue());
			} 
			lst = vSaledayService.selectV_SALEDAYList(vSaleday);
			for(V_SALEDAY s:lst){
				
				Date saledate=DateUtils.getStrToDate(s.getSaleday(),"yyyyMMdd");
				s.setSaleday(DateUtils.getDateToStr(saledate,"yyyy-MM-dd"));
			    SALEDAY_DETAIL saleday_detail=new SALEDAY_DETAIL();
			    rv.getData().lst.add(saleday_detail);
			    saleday_detail.saleday=s.getSaleday();
			    saleday_detail.endtime=s.getEndtime();
			    saleday_detail.match_count=s.getMatch_count();
			    saleday_detail.rem_count=s.getRem_count();
			    saleday_detail.position=Integer.parseInt(jsonRequest.get("position").toString());
			    
				switch (DateUtils.getWeekDay(saledate)) {
				case 1:
					saleday_detail.week_name=("星期一");
					break;
				case 2:
					saleday_detail.week_name=("星期二");
					break;
				case 3:
					saleday_detail.week_name=("星期三");
					break;
				case 4:
					saleday_detail.week_name=("星期四");
					break;
				case 5:
					saleday_detail.week_name=("星期五");
					break;
				case 6:
					saleday_detail.week_name=("星期六");
					break;
				case 7:
					saleday_detail.week_name=("星期日");
					break;
				default:
					break;
				}
				saledate=null;
			}
		} catch (Exception e) {
			logger.error("查询首页日期栏列表失败----->");
			rv.setStatus(ErrorMessage.FAIL.getCode());
	        rv.setMessage(ErrorMessage.FAIL.getMessage());
			return JSONObject.toJSONString(rv);
		}
		
        
        rv.setStatus(ErrorMessage.SUCCESS.getCode());
        rv.setMessage(ErrorMessage.SUCCESS.getMessage());
        
        logger.info("查询首页日期栏列成功----->");
		return JSONObject.toJSONString(rv);
	}
	
}
