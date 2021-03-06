package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.V_SALEDAY;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.V_SALEDAYService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

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
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		logger.info("v_saleday_list查询首页日期栏列表doJsonMethod------>");
		ReturnValue<SALEDAY_Data> rv = new ReturnValue<SALEDAY_Data>();
		rv.setData(new SALEDAY_Data());
		V_SALEDAY vSaleday=new V_SALEDAY();
		
		vSaleday.setSport_type(2);
		vSaleday.setElectronic_code(request_LtGameLogic.getFeeType());
		List<V_SALEDAY> lst=null;
		try {			
			vSaleday.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
			vSaleday.setRem_issue("");
			
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
			    saleday_detail.position=2;
			    
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
			rv.setMess(ErrorMessage.FAIL);
			return JSONObject.toJSONString(rv);
		}
		
        
        rv.setMess(ErrorMessage.SUCCESS);
        
        logger.info("查询首页日期栏列成功----->");
		return JSONObject.toJSONString(rv);
	}
	
}
