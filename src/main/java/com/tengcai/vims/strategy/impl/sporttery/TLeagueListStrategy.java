/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.entity.sporttery.V_LEAGUE;
import com.tengcai.vims.service.sporttery.LOTO_BNService;
import com.tengcai.vims.service.sporttery.LOTO_FNService;
import com.tengcai.vims.service.sporttery.T_LOTO_ENService;
import com.tengcai.vims.service.sporttery.V_LEAGUEService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;

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
		public String position = "";
	}

	public static class LeagueEnDetail {
		public String game_name = "";
		public List<CountList> lstDay = new ArrayList<CountList>();
		public String position = "";
	}

	public static class LeagueData<T> {
		public List<T> lst = new ArrayList<T>();
	}

	@Autowired
	private V_LEAGUEService v_LEAGUEService;
	@Autowired
	private LOTO_FNService loto_fnService;
	@Autowired
	private LOTO_BNService loto_bnService;
	@Autowired
	private T_LOTO_ENService loto_enService;
	
	
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("v_league_list开始调用查询赛事列表接口doJsonMethod------>");
		V_LEAGUE vLeague = new V_LEAGUE();
		vLeague.setPosition(jsonRequest.get("position").toString());
        if(vLeague.getPosition().equals("2")){
        	LOTO_F loto_F= loto_fnService.selectRemFN();
        	if(loto_F!=null){
        		vLeague.setRem_issue(loto_F.getIssue());
        	}else{
        		vLeague.setRem_issue("");
        	}
        }
        else if(vLeague.getPosition().equals("3")){
        	LOTO_B loto_B= loto_bnService.selectRemBN();
        	if(loto_B!=null){
        		vLeague.setRem_issue(loto_B.getIssue());
        	}else{
        		vLeague.setRem_issue("");
        	}
        }
        else if(vLeague.getPosition().equals("4")){
        	T_LOTO_E loto_E= loto_enService.selectRemEN();
        	if(loto_E!=null){
        		vLeague.setRem_issue(loto_E.getIssue());
        	}else{
        		vLeague.setRem_issue("");
        	}
        }
		List<V_LEAGUE> list = v_LEAGUEService.selectV_LEAGUE_List(vLeague);

		if (vLeague.getPosition().equals("4")) {
			return getEn(list);
		} else {
			return getFb(list);
		}

	}
	private String getEn(List<V_LEAGUE> list){
		ReturnValue<LeagueData<LeagueEnDetail>> rv = new ReturnValue<LeagueData<LeagueEnDetail>>();
		try {

			rv.setData(new LeagueData<LeagueEnDetail>());
			LeagueEnDetail olddetail = null;
			for (V_LEAGUE v : list) {
				if (olddetail != null) {
					if (olddetail.game_name.equals(v.getLeaguename())) {
						CountList countList = new CountList();
						countList.count = v.getLeaguecount();
						countList.saleday = v.getSaleday().replace("-", "");
						olddetail.lstDay.add(countList);
						continue;
					}
				}
				LeagueEnDetail detail = new LeagueEnDetail();
				detail.game_name = v.getLeaguename();
				detail.position = v.getPosition();
				CountList countList = new CountList();
				countList.saleday = v.getSaleday().replace("-", "");
				countList.count = v.getLeaguecount();
				detail.lstDay.add(countList);
				rv.getData().lst.add(detail);
				olddetail = detail;
			}
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询赛事列表失败----->" + e.getMessage());
		}
		logger.info("查询赛事列表成功----->");

		return JSONObject.toJSONString(rv);
	}
	private String getFb(List<V_LEAGUE> list){
		ReturnValue<LeagueData<LeagueFbDetail>> rv = new ReturnValue<LeagueData<LeagueFbDetail>>();
		try {

			rv.setData(new LeagueData<LeagueFbDetail>());
			LeagueFbDetail olddetail = null;
			for (V_LEAGUE v : list) {
				if (olddetail != null) {
					if (olddetail.leaguename.equals(v.getLeaguename())) {
						CountList countList = new CountList();
						countList.count = v.getLeaguecount();
						countList.saleday = v.getSaleday().replace("-", "");
						olddetail.lstDay.add(countList);
						continue;
					}
				}
				LeagueFbDetail detail = new LeagueFbDetail();
				detail.leaguename = v.getLeaguename();
				detail.position = v.getPosition();
				CountList countList = new CountList();
				countList.saleday = v.getSaleday().replace("-", "");
				countList.count = v.getLeaguecount();
				detail.lstDay.add(countList);
				rv.getData().lst.add(detail);
				olddetail = detail;
			}
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询赛事列表失败----->" + e.getMessage());
		}
		logger.info("查询赛事列表成功----->");

		return JSONObject.toJSONString(rv);
	}
}