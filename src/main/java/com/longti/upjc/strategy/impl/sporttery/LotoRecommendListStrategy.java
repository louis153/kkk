package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.LOTO_F;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_F;
import com.longti.upjc.service.sporttery.LOTO_FNService;
import com.longti.upjc.service.sporttery.T_LOTO_SIS_FService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.Odds;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;

/**
 *查询足球推荐信息
 * @return 
 * @throws Exception 
 */
@Component("loto_recommend_list")
public class LotoRecommendListStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(LotoRecommendListStrategy.class);
	

	public static class RemFootData {
		public String issue=""; // 比赛编号
		public String home_team_name=""; // 主队名称
		public String guest_team_name=""; // 客队名称
		public String home_team_id=""; // 主队编号
		public String guest_team_id=""; // 客队编号
		public String letcount=""; // 让球
		
		public String hh="";
		public String hd="";
		public String ha="";
		public String rh="";
		public String rd="";
		public String ra="";
		public String hh_p="";
		public String hd_p="";
		public String ha_p="";
		public String rh_p="";
		public String rd_p="";
		public String ra_p="";

		public String leaguename=""; // 比赛名称
		public String endtime="";
		public int h_bet=1;
		public int r_bet=1;
		
	}
	@Autowired
	private LOTO_FNService lotoFNService;
	@Autowired
	private T_LOTO_SIS_FService t_LOTO_SIS_FService;
	@Override	
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("进入loto_recommend_list查询足球推荐的方法：doJsonMethod");
		ReturnValue<RemFootData> rv = new ReturnValue<RemFootData>();
		rv.setData(new RemFootData());
		LOTO_F  lotoF=new LOTO_F();
		lotoF.setIs_hot(1);
		lotoF.setHad_bet(1);
		lotoF.setHhad_bet(1);
		List<LOTO_F> lst=null;
		T_LOTO_SIS_F t_LOTO_SIS_F=new T_LOTO_SIS_F();
		
		List<T_LOTO_SIS_F> lstPrecent=null;
		List<HashMap<String, String>> lstRv=new ArrayList<HashMap<String,String>>();
		HashMap<String, String> hashRv=new HashMap<String,String>();
		lstRv.add(hashRv);
		
		try {
			lotoF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
			lst = lotoFNService.selectLOTO_FNList(lotoF);
			if(lst.isEmpty()){
				lotoF.setIs_hot(null);
				lotoF.setIs_recommend(1);
				lst=lotoFNService.selectLOTO_FNList(lotoF);
			}
			if(lst.isEmpty()==false){
				t_LOTO_SIS_F.setIssue(lst.get(0).getIssue());
				lstPrecent=t_LOTO_SIS_FService.selectT_LOTO_SIS_FList(t_LOTO_SIS_F);
				rv.getData().guest_team_id=(lst.get(0).getGuest_team_id());
				rv.getData().guest_team_name=(lst.get(0).getGuest_team_name());
				rv.getData().letcount=(lst.get(0).getLetcount().toString());
				rv.getData().home_team_id=(lst.get(0).getHome_team_id());
				rv.getData().home_team_name=(lst.get(0).getHome_team_name());
				rv.getData().leaguename=(lst.get(0).getLeaguename());
				rv.getData().issue=(lst.get(0).getIssue());
				rv.getData().endtime=lst.get(0).getEndtime();
				double total1=0,total2=0;
				if(lstPrecent.isEmpty()||lstPrecent.get(0)==null){
					lstPrecent.add(new T_LOTO_SIS_F());
				}
				if(lstPrecent.get(0).getHad_h()==null){
					lstPrecent.get(0).setHad_h(0);
					lstPrecent.get(0).setHad_d(0);
					lstPrecent.get(0).setHad_a(0);
				}else{
					total1=lstPrecent.get(0).getHad_h()+lstPrecent.get(0).getHad_d()+lstPrecent.get(0).getHad_a();					
				}
				if(lstPrecent.get(0).getHhad_h()==null){
					lstPrecent.get(0).setHhad_h(0);
					lstPrecent.get(0).setHhad_d(0);
					lstPrecent.get(0).setHhad_a(0);
				}else{
					total2=lstPrecent.get(0).getHhad_h()+lstPrecent.get(0).getHhad_d()+lstPrecent.get(0).getHhad_a();					
				}
				if(lst.get(0).getHad_bet()==0){
					lst.get(0).setHad_h("");
					lst.get(0).setHad_d("");
					lst.get(0).setHad_a("");
				}
				if(lst.get(0).getHhad_bet()==0){
					lst.get(0).setHhad_h("");
					lst.get(0).setHhad_d("");
					lst.get(0).setHhad_a("");
				}
				if(StringUtil.isEmpty(lst.get(0).getHad_h())==false){
					
					rv.getData().hh=lst.get(0).getHad_h();
					rv.getData().hd=lst.get(0).getHad_d();
					rv.getData().ha=lst.get(0).getHad_a();
					rv.getData().hh_p=String.format("%.0f%%", total1==0?0.00:lstPrecent.get(0).getHad_h()/total1*100);
					rv.getData().hd_p=String.format("%.0f%%", total1==0?0.00:lstPrecent.get(0).getHad_d()/total1*100);
					rv.getData().ha_p=String.format("%.0f%%", total1==0?0.00:lstPrecent.get(0).getHad_a()/total1*100);
					rv.getData().h_bet=1;
				}else{
					rv.getData().h_bet=0;
				}
					
				if(StringUtil.isEmpty(lst.get(0).getHhad_h())==false){
					rv.getData().rh=lst.get(0).getHhad_h();
					rv.getData().rd=lst.get(0).getHhad_d();
					rv.getData().ra=lst.get(0).getHhad_a();
					rv.getData().rh_p=String.format("%.0f%%", total2==0?0.00:lstPrecent.get(0).getHhad_h()/total2*100);
					rv.getData().rd_p=String.format("%.0f%%", total2==0?0.00:lstPrecent.get(0).getHhad_d()/total2*100);
					rv.getData().ra_p=String.format("%.0f%%", total2==0?0.00:lstPrecent.get(0).getHhad_a()/total2*100);
					rv.getData().r_bet=1;
;
				}else{
					rv.getData().r_bet=0;
				}
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
				logger.info("查询足球推荐列表成功----->");
			}else{
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
				logger.info("查询足球推荐列表为空----->");
			}
			
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询足球推荐列表失败----->");
		}
		
        
        
        
		return JSONObject.toJSONString(rv);		
	}
	
}
