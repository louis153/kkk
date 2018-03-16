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
import org.springframework.beans.factory.parsing.FailFastProblemReporter;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.service.sporttery.LOTO_FNService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

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
	    public String l; //大小分标准/让球数
		public String leaguename; //比赛名称
		
		public String hh="";
		public String hd="";
		public String ha="";
		public String rh="";
		public String rd="";
		public String ra="";
		public int h_bet=1;
		public int r_bet=1;
	   
	}
	public static class LotoFnData{
		public List<LotoFnDetail> lst=new ArrayList<LotoFnDetail>();
	}
	@Autowired
	private LOTO_FNService lotoFNService;
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("loto_fn_list开始调用查看足球赛事场次列表接口doJsonMethod------>");
		ReturnValue<LotoFnData> rv=new ReturnValue<LotoFnData>();
		rv.setData(new LotoFnData());
		LOTO_F  lotoF=new LOTO_F();
		List<LOTO_F> lst=null;
		lotoF.setStatus(1);
		String rem_issue=getFootRem_issue(jsonRequest);
		lotoF.setStartIssue(jsonRequest.get("saleday").toString().replace("-", ""));
		lotoF.setEndIssue(jsonRequest.get("saleday").toString().replace("-", "")+"9999");		
		lotoF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		
		lotoF.setHad_bet(1);
		lotoF.setHhad_bet(1);
		try {
			lst = lotoFNService.selectLOTO_FNList(lotoF);	
			if(StringUtil.isEmpty(rem_issue)==false){
				int irecommend=-1;
				for(int i=0;i<lst.size();i++){
					LOTO_F f=lst.get(i);
					if(f.getIssue().equals(rem_issue)){
						irecommend=i;
						break;
					}
				}
				if(irecommend!=-1)
					lst.remove(irecommend);
			}
			for(LOTO_F f:lst){
				LotoFnDetail lotoFnDetail=new LotoFnDetail();
				lotoFnDetail.endtime=f.getEndtime();
				lotoFnDetail.guest_team_name=f.getGuest_team_name();
				lotoFnDetail.home_team_name=f.getHome_team_name();
				lotoFnDetail.issue=f.getIssue();
				lotoFnDetail.l=f.getLetcount().toString();
				lotoFnDetail.leaguename=f.getLeaguename();
				
				if(f.getHad_bet()==0){
					f.setHad_h("");
					f.setHad_d("");
					f.setHad_a("");
				}
				if(f.getHhad_bet()==0){
					f.setHhad_h("");
					f.setHhad_d("");
					f.setHhad_a("");
				}
				if(StringUtil.isEmpty(f.getHad_h())==false){
					lotoFnDetail.hh=f.getHad_h();
					lotoFnDetail.hd=f.getHad_d();
					lotoFnDetail.ha=f.getHad_a();
					lotoFnDetail.h_bet=1;
				}else{
					lotoFnDetail.h_bet=0;
				}
				if(StringUtil.isEmpty(f.getHhad_h())==false){
					lotoFnDetail.rh=f.getHhad_h();
					lotoFnDetail.rd=f.getHhad_d();
					lotoFnDetail.ra=f.getHhad_a();
					lotoFnDetail.r_bet=1;
				}else{
					lotoFnDetail.r_bet=0;
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
	private String getFootRem_issue(JSONObject jsonRequest) throws Exception{
		LOTO_F lotoF = new LOTO_F();
		lotoF.setIs_hot(1);
		lotoF.setHad_bet(1);
		lotoF.setHhad_bet(1);
		List<LOTO_F> lst = null;
		lotoF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		lotoF.setStartIssue(jsonRequest.get("saleday").toString().replace("-", ""));
		lotoF.setEndIssue(jsonRequest.get("saleday").toString().replace("-", "")+"9999");
			lst = lotoFNService.selectLOTO_FNList(lotoF);
			if (lst.isEmpty()) {
				lotoF.setIs_hot(null);
				lotoF.setIs_recommend(1);
				lst = lotoFNService.selectLOTO_FNList(lotoF);
			}
		if(lst.size()>0)
		{
			return lst.get(0).getIssue();
		}else{
			return "";
		}
	}
	}