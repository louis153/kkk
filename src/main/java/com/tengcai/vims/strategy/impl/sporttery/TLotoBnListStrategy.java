/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
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
import com.tengcai.vims.service.sporttery.LOTO_BNService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 *查看蓝球赛事场次列表
 * @return 
 */
@Component("loto_bn_list")
public class TLotoBnListStrategy implements IMethodStrategy{
	
	public static class LotoBnDetail{
		public String issue=""; //比赛编号	    
		public String endtime=""; //截止投注时间
		public String home_team_name=""; //主队名称
		public String guest_team_name=""; //客队名称  
	    public String l=""; //大小分标准/让球数
		public String leaguename=""; //比赛名称
		public String hh="";
		public String hl="";
		public String mh="";
		public String ma="";
		public int h_bet=1;
		public int m_bet=1;
	   
	}
	public static class LotoBnData{
		public List<LotoBnDetail> lst=new ArrayList<LotoBnDetail>();
	}
	protected final transient static Logger logger = LoggerFactory.getLogger(TLotoBnListStrategy.class);
	@Autowired
	private LOTO_BNService lotoBNService;
	
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		
		ReturnValue<LotoBnData> rv=new ReturnValue<LotoBnData>();
		rv.setData(new LotoBnData());
		logger.info("loto_bn_list开始调用查看蓝球赛事场次列表接口doJsonMethod------>");
		String rem_issue=getBasketRem_issue(jsonRequest);
		LOTO_B  lotoB=new LOTO_B();
		lotoB.setStatus(1);
		lotoB.setStartIssue(jsonRequest.get("saleday").toString().replace("-", ""));
		lotoB.setEndIssue(jsonRequest.get("saleday").toString().replace("-", "")+"9999");
		lotoB.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		lotoB.setHilo_bet(1);
		lotoB.setMnl_bet(1);
		
		
		List<LOTO_B> lst=null;
		try {
			
			lst = lotoBNService.selectLOTO_BNList(lotoB);
			
			if(StringUtil.isEmpty(rem_issue)==false){
				int irecommend=-1;
				for(int i=0;i<lst.size();i++){
					LOTO_B f=lst.get(i);
					if(f.getIssue().equals(rem_issue)){
						irecommend=i;
						break;
					}
				}
				if(irecommend!=-1){
					lst.remove(irecommend);
				}
				
			}
			for(LOTO_B b:lst){
				LotoBnDetail lotoBnDetail=new LotoBnDetail();
				lotoBnDetail.endtime=b.getEndtime();
				lotoBnDetail.guest_team_name=b.getHome_team_name();
				lotoBnDetail.home_team_name=b.getGuest_team_name();
				lotoBnDetail.issue=b.getIssue();
				lotoBnDetail.l=b.getHilo_presetscore();
				lotoBnDetail.leaguename=b.getLeaguename();
				
				if(b.getHilo_bet()==0){
					b.setHilo_h("");
					b.setHilo_l("");
				}
				if(b.getMnl_bet()==0){
					b.setMnl_h("");
					b.setMnl_a("");
				}
				if(StringUtil.isEmpty(b.getHilo_h())==false){
					
					lotoBnDetail.hh=b.getHilo_h();
					lotoBnDetail.hl=b.getHilo_l();
					lotoBnDetail.h_bet=1;
				}else{
					lotoBnDetail.h_bet=0;
				}
				if(StringUtil.isEmpty(b.getMnl_h())==false){
					lotoBnDetail.mh=b.getMnl_h();
					lotoBnDetail.ma=b.getMnl_a();
					lotoBnDetail.m_bet=1;
				}else{
					lotoBnDetail.m_bet=0;
				}
				rv.getData().lst.add(lotoBnDetail);
			}
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("查询蓝球赛事列表失败----->");
			throw e;
		}

        
       
        logger.info("查询蓝球赛事列表成功----->");
		return JSONObject.toJSONString(rv);		
	}
	
	private String getBasketRem_issue(JSONObject jsonRequest) throws Exception{
		LOTO_B lotoB = new LOTO_B();
		lotoB.setIs_hot(1);
		lotoB.setHilo_bet(1);
		lotoB.setMnl_bet(1);
		List<LOTO_B> lst = null;
		lotoB.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		lotoB.setStartIssue(jsonRequest.get("saleday").toString().replace("-", ""));
		lotoB.setEndIssue(jsonRequest.get("saleday").toString().replace("-", "")+"9999");
			lst = lotoBNService.selectLOTO_BNList(lotoB);
			if (lst.isEmpty()) {
				lotoB.setIs_hot(null);
				lotoB.setIs_recommend(1);
				lst = lotoBNService.selectLOTO_BNList(lotoB);
			}
		if(lst.size()>0)
		{
			return lst.get(0).getIssue();
		}else{
			return "";
		}
	}
}