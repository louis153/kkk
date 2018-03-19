/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;

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
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		logger.info("loto_fn_list开始调用查看足球赛事场次列表接口doJsonMethod------>");
		ReturnValue<LotoFnData> rv=new ReturnValue<LotoFnData>();
		rv.setData(new LotoFnData());
		LOTO_F  lotoF=new LOTO_F();
		List<LOTO_F> lst=null;
		lotoF.setStatus(1);
		
		lotoF.setStartIssue(jsonRequest.get("saleday").toString().replace("-", ""));
		lotoF.setEndIssue(jsonRequest.get("saleday").toString().replace("-", "")+"9999");		
		lotoF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		
		lotoF.setHad_bet(1);
		try {
			lst = lotoFNService.selectLOTO_FNList(lotoF);	
			
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
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			//checkCanBet(lst);
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询足球赛事列表失败----->");
			throw e;
		}

      
        logger.info("查询足球赛事列表成功----->");
        return JSONObject.toJSONString(rv);		
	}
	
	}