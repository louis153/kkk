/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.V_LEAGUE;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.V_LEAGUEService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.LangUtil;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.LangUtil.LangObj;

/**
 * 查询蓝球赛事列表
 * 
 * @param userPin
 * @param jsonStr
 * @return
 */
@Component("v_league_list")
public class TLeagueListStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(TLeagueListStrategy.class);

	public static class CountList {
		public String count = "";
		public String saleday = "";
	}

	public static class LeagueFbDetail {
		public String leaguename = "";
		public List<CountList> lstDay = new ArrayList<CountList>();
	}

	

	public static class LeagueData<T> {
		public List<T> lst = new ArrayList<T>();
	}

	@Autowired
	private V_LEAGUEService v_LEAGUEService;
	@Autowired
	private LangListStrategy langListStrategy;
	
	private void changeLeaguesLang(List<V_LEAGUE> lsV_LEAGUEs,String feeType,String lang,String userPin) throws Exception{
		
		List<LangObj> lstLang=new ArrayList<LangObj>();
		for(V_LEAGUE l:lsV_LEAGUEs){
			LangObj langObj=new LangObj();
			langObj.guest_team_name="";
			langObj.home_team_name="";
			langObj.issue=l.getLeaguename();
			langObj.leaguename=l.getLeaguename();
			langObj.options_one="";
			langObj.options_three="";
			langObj.options_two="";
			langObj.play_method="";
			lstLang.add(langObj);
		}
		Map<String,LangObj> mapLang= (new LangUtil(langListStrategy)).getLangMap(feeType,lang,userPin,lstLang);
		for(V_LEAGUE l:lsV_LEAGUEs){
			if(mapLang.containsKey(l.getLeaguename())){
					LangObj langObj=mapLang.get(l.getLeaguename());
				if(langObj.leaguename.isEmpty()==false)
					l.setLeaguename(langObj.leaguename);				
			}
		}
	}
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic , JSONObject jsonRequest) throws Exception {
		logger.info("v_league_list开始调用查询赛事列表接口doJsonMethod------>");
		ReturnValue<LeagueData<LeagueFbDetail>> rv = new ReturnValue<LeagueData<LeagueFbDetail>>();
		try {

			rv.setData(new LeagueData<LeagueFbDetail>());
			LeagueFbDetail olddetail = null;
			V_LEAGUE v_league=new V_LEAGUE();
			v_league.setElectronic_code(request_LtGameLogic.getFeeType());
			List<V_LEAGUE> lsLeagues=v_LEAGUEService.selectV_LEAGUE_List(v_league);
			changeLeaguesLang(lsLeagues,request_LtGameLogic.getFeeType(),request_LtGameLogic.getLang(),request_LtGameLogic.getUserPin());
			for (V_LEAGUE v : lsLeagues) {
				if (olddetail != null) {
					if (olddetail.leaguename.equals(v.getLeaguename())) {
						CountList countList = new CountList();
						countList.count = v.getLeaguecount().toString();
						countList.saleday = v.getSaleday().replace("-", "");
						olddetail.lstDay.add(countList);
						continue;
					}
				}
				LeagueFbDetail detail = new LeagueFbDetail();
				detail.leaguename = v.getLeaguename();				
				CountList countList = new CountList();
				countList.saleday = v.getSaleday().replace("-", "");
				countList.count = v.getLeaguecount().toString();
				detail.lstDay.add(countList);
				rv.getData().lst.add(detail);
				olddetail = detail;
			}
			rv.setMess(ErrorMessage.SUCCESS);
		} catch (Exception e) {
			rv.setMess(ErrorMessage.FAIL);
			logger.error("查询赛事列表失败----->" + e.getMessage());
		}
		logger.info("查询赛事列表成功----->");

		return JSONObject.toJSONString(rv);

	}



	public V_LEAGUEService getV_LEAGUEService() {
		return v_LEAGUEService;
	}



	public void setV_LEAGUEService(V_LEAGUEService v_LEAGUEService) {
		this.v_LEAGUEService = v_LEAGUEService;
	}
	
	
}