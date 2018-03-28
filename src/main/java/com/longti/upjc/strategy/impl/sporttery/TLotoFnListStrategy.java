/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.LOTO_F;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.LOTO_FNService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.LangUtil;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;
import com.longti.upjc.util.LangUtil.LangObj;

/**
 *查看足球赛事场次列表
 * @return 
 */
@Component("loto_fn_list")
public class TLotoFnListStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(TLotoFnListStrategy.class);
	
	public static class LotoFnDetail{
		public String issue; //比赛编号	    
		public String endtime; //截止投注时间
		public String home_team_name; //主队名称
		public String guest_team_name; //客队名称  
		public String leaguename; //比赛名称
		
		public String hh="";
		public String hd="";
		public String ha="";
		public int h_bet=1;
	   
	}
	public static class LotoFnData{
		public List<LotoFnDetail> lst=new ArrayList<LotoFnDetail>();
	}
	@Autowired
	private LOTO_FNService lotoFNService;
	@Autowired
	private LangListStrategy langListStrategy;
	
	private void changeFsLang(List<LOTO_F> loto_Fs,String feeType,String lang,String userPin) throws Exception{
		
		List<LangObj> lstLang=new ArrayList<LangObj>();
		for(LOTO_F f:loto_Fs){
			LangObj langObj=new LangObj();
			langObj.guest_team_name=f.getGuest_team_name();
			langObj.home_team_name=f.getHome_team_name();
			langObj.issue=f.getIssue();
			langObj.leaguename=f.getLeaguename();
			langObj.options_one="";
			langObj.options_three="";
			langObj.options_two="";
			langObj.play_method="";
			lstLang.add(langObj);
		}
		Map<String,LangObj> mapLang= (new LangUtil(langListStrategy)).getLangMap(feeType,lang,userPin,lstLang);
		for(LOTO_F f:loto_Fs){
			if(mapLang.containsKey(f.getIssue())){
					LangObj langObj=mapLang.get(f.getIssue());
				if(langObj.guest_team_name.isEmpty()==false)
					f.setGuest_team_name(langObj.guest_team_name);
				if(langObj.home_team_name.isEmpty()==false)
					f.setHome_team_name(langObj.home_team_name);
				if(langObj.leaguename.isEmpty()==false)
					f.setLeaguename(langObj.leaguename);				
			}
		}
	}
	
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		logger.info("loto_fn_list开始调用查看足球赛事场次列表接口doJsonMethod------>");
		ReturnValue<LotoFnData> rv=new ReturnValue<LotoFnData>();
		rv.setData(new LotoFnData());
		LOTO_F  lotoF=new LOTO_F();
		List<LOTO_F> lst=null;
		lotoF.setStatus(1);
		lotoF.setElectronic_code(request_LtGameLogic.getFeeType());
		lotoF.setStartIssue(jsonRequest.get("saleday").toString().replace("-", ""));
		lotoF.setEndIssue(jsonRequest.get("saleday").toString().replace("-", "")+"9999");		
		lotoF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		
		lotoF.setHad_bet(1);
		try {
			lst = lotoFNService.selectLOTO_FNList(lotoF);	
			changeFsLang(lst,request_LtGameLogic.getFeeType(),request_LtGameLogic.getLang(),request_LtGameLogic.getUserPin());
			for(LOTO_F f:lst){
				LotoFnDetail lotoFnDetail=new LotoFnDetail();
				lotoFnDetail.endtime=f.getEndtime();
				lotoFnDetail.guest_team_name=f.getGuest_team_name();
				lotoFnDetail.home_team_name=f.getHome_team_name();
				lotoFnDetail.issue=f.getIssue();
				lotoFnDetail.leaguename=f.getLeaguename();
				
				if(f.getHad_bet()==0){
					f.setHad_h("");
					f.setHad_d("");
					f.setHad_a("");
				}
				
				if(StringUtil.isEmpty(f.getHad_h())==false){
					lotoFnDetail.hh=f.getHad_h();
					lotoFnDetail.hd=f.getHad_d();
					lotoFnDetail.ha=f.getHad_a();
					lotoFnDetail.h_bet=1;
				}else{
					lotoFnDetail.h_bet=0;
				}
				
				rv.getData().lst.add(lotoFnDetail);
			}
			rv.setMess(ErrorMessage.SUCCESS);
			//checkCanBet(lst);
		} catch (Exception e) {
			rv.setMess(ErrorMessage.FAIL);
			logger.error("查询足球赛事列表失败----->");
			throw e;
		}

      
        logger.info("查询足球赛事列表成功----->");
        return JSONObject.toJSONString(rv);		
	}
	
	}