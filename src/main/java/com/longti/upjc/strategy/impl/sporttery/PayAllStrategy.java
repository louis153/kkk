/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.icu.math.BigDecimal;
import com.longti.upjc.entity.sporttery.LOTO_F;
import com.longti.upjc.entity.sporttery.LOTO_ORDER;
import com.longti.upjc.entity.sporttery.TAB_SALES_THRESHOLD;
import com.longti.upjc.entity.sporttery.TAB_WARN_MESSAGE;
import com.longti.upjc.entity.sporttery.TAB_WARN_SETTING;
import com.longti.upjc.entity.sporttery.TAB_WARN_RECEIVE;
import com.longti.upjc.entity.sporttery.T_LOTO_E;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_E;
import com.longti.upjc.entity.sporttery.T_LOTO_SIS_F;
import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.entity.sporttery.V_ORDER;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.LOTO_FNService;
import com.longti.upjc.service.sporttery.TAB_SALES_THRESHOLDService;
import com.longti.upjc.service.sporttery.TAB_WARN_MESSAGEService;
import com.longti.upjc.service.sporttery.T_LOTO_ENService;
import com.longti.upjc.service.sporttery.T_LOTO_SIS_EService;
import com.longti.upjc.service.sporttery.T_LOTO_SIS_FService;
import com.longti.upjc.service.sporttery.T_USERService;
import com.longti.upjc.service.sporttery.V_ORDERService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.LangUtil;
import com.longti.upjc.util.LangUtil.LangObj;
import com.longti.upjc.util.jdbet.BetUtils;
import com.mysql.jdbc.log.Log;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.SmsUtils;
import com.longti.upjc.util.StringUtil;

/**
 * 蓝球支付
 * 
 * @return
 */
@Component("pay")
public class PayAllStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(PayAllStrategy.class);
	
	@Autowired
	private LOTO_FNService lotoFNService;
	@Autowired
	private T_LOTO_ENService lotoENService;
	@Autowired
	private T_LOTO_SIS_FService loto_SIS_FService;
	@Autowired
	private T_LOTO_SIS_EService loto_SIS_EService;
	@Autowired
	private TAB_SALES_THRESHOLDService tab_SALES_THRESHOLDService;
	@Autowired
	private V_ORDERService v_orderService;
	@Autowired
	private T_USERService t_USERService;
	@Autowired
	private LangListStrategy langListStrategy;
	@Autowired
	private TAB_WARN_MESSAGEService tab_WARN_MESSAGEService;
	
	
	public static class Odd {
		private String odd_name;
		private String odd_value;
		private String odd_cost;

		public Odd(String name, String value, String cost) {
			odd_name = name;
			odd_value = value;
			odd_cost = cost;
		}

		public String getOdd_name() {
			return odd_name;
		}

		public void setOdd_name(String odd_name) {
			this.odd_name = odd_name;
		}

		public String getOdd_value() {
			return odd_value;
		}

		public void setOdd_value(String odd_value) {
			this.odd_value = odd_value;
		}

		public String getOdd_cost() {
			return odd_cost;
		}

		public void setOdd_cost(String odd_cost) {
			this.odd_cost = odd_cost;
		}
	}

	public static class RequestData {
		private String issue;
		private List<Odd> odds;
		public String getIssue() {
			return issue;
		}

		public void setIssue(String issue) {
			this.issue = issue;
		}

		

		public List<Odd> getOdds() {
			return odds;
		}

		public void setOdds(List<Odd> odds) {
			this.odds = odds;
		}

	}

	public static class Presetscore {
		private String issue;
		private String p;

		public String getIssue() {
			return issue;
		}

		public String getP() {
			return p;
		}

		public Presetscore(String issue, String p) {
			this.issue = issue;
			this.p = p;
		}
	}

	public static class PayAll_Data {
		private List<String> endmatchs = new ArrayList<String>();// 截止投注的比赛
		private List<Presetscore> change_presetscore = new ArrayList<Presetscore>();// 大小分标准发生变化

		public List<String> getEndmatchs() {
			return endmatchs;
		}

		public void setEndmatchs(List<String> endmatchs) {
			this.endmatchs = endmatchs;
		}

		public List<Presetscore> getChange_presetscore() {
			return change_presetscore;
		}

		public void setChange_presetscore(List<Presetscore> change_presetscore) {
			this.change_presetscore = change_presetscore;
		}

	}

	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {		
		logger.info("pay开始调用支付接口doJsonMethod------>" + JSONObject.toJSONString(jsonRequest));
		ReturnValue<PayAll_Data> rv = new ReturnValue<>();
		rv.setData(new PayAll_Data());

		
		
		JSONArray lst_rem = ((JSONArray) jsonRequest.get("lst_rem"));
		if (lst_rem==null||lst_rem.isEmpty()) {
			rv.setMess(ErrorMessage.NO_REC);
			logger.info("调用篮球支付接口失败----->");
			return JSONObject.toJSONString(rv);
		}
		String channel = "1";
		if (StringUtil.isEmpty(jsonRequest.get("channel")) == false) {
			channel = jsonRequest.get("channel").toString();// 获取渠道标记
		}
		if (StringUtil.isEmpty(jsonRequest.get("order_source")) == false) {
			channel = jsonRequest.get("order_source").toString();// 获取渠道标记
		}
		List<String> sbFalse = new ArrayList<String>();
		T_USER t_USER=new T_USER();
		t_USER.setUser_pin(request_LtGameLogic.getUserPin());
		List<T_USER> lsUsers=t_USERService.selectT_USERList(t_USER);
		if(lsUsers.isEmpty()==false){
			t_USER=lsUsers.get(0);
		}
		
		for (Object positionObj : lst_rem) {
			String position = ((JSONObject) positionObj).get("position").toString();
			switch (position) {
			case "2":				
				payFoot(rv, sbFalse, channel, ((JSONObject) positionObj).getJSONArray("lst"), request_LtGameLogic.getUserPin(),
						t_USER.getNick_name(),request_LtGameLogic.getFeeType(),request_LtGameLogic.getLang());
				if (rv.getStatus() != null && rv.getStatus().equals(ErrorMessage.SUCCESS.getCode()) == false) {
					return JSONObject.toJSONString(rv);
				}
				break;			
			case "4":
				payDj(rv, sbFalse, channel, ((JSONObject) positionObj).getJSONArray("lst"), request_LtGameLogic.getUserPin(),
						t_USER.getNick_name(),request_LtGameLogic.getFeeType(),request_LtGameLogic.getLang());
				if (rv.getStatus() != null && rv.getStatus().equals(ErrorMessage.SUCCESS.getCode()) == false) {
					return JSONObject.toJSONString(rv);
				}
				break;
			}

		}

		if (sbFalse.size() > 0) {
			rv.setMess(ErrorMessage.FAIL);

			logger.info("调用支付接口失败----->");
			return JSONObject.toJSONString(rv);
		} else {
			rv.setMess(ErrorMessage.SUCCESS);
			logger.info("调用支付接口成功----->");
			return JSONObject.toJSONString(rv);
		}
	}

	// 判断是否可以计算新赔率
	private void checkCanChangeOdd(HashMap<String, Boolean> canChangeOdd, String issue, Integer type, double new_sum,
			double oldSum, long dcxssx_s) {
		double per10 = dcxssx_s / 10;
		canChangeOdd.put(issue + "|" + type, Math.floor(oldSum / per10) != Math.floor(new_sum / per10));
	}

	// 判断是否达到销售限额
	private void checkCanBet(List<String> canBet, String issue, Integer type, long new_sum, long dcxssx_s) {
		if (new_sum >= dcxssx_s) {
			canBet.add(issue + "|" + type);
		}
	}
	
	// 判断是否报警
	private TAB_WARN_SETTING tab_warn_setting=null;
	private List<TAB_WARN_RECEIVE> lTab_WARN_RECEIVEs;
	private void checkCanWarn(Long new_sum,Long dcxssx_s,TAB_WARN_MESSAGE tab_warn_message) throws Exception{
		if (tab_warn_setting==null){
			tab_warn_setting=tab_WARN_MESSAGEService.selectTAB_WARN_SETTING();
		}
		if(lTab_WARN_RECEIVEs==null){
			lTab_WARN_RECEIVEs=tab_WARN_MESSAGEService.selectTAB_WARN_RECEIVEList();
		}
		if(tab_warn_setting!=null)
		{
			if(new_sum*100/dcxssx_s>tab_warn_setting.getRatio()){
				if(tab_WARN_MESSAGEService.selectTAB_WARN_MESSAGEList(tab_warn_message).isEmpty()){
					tab_warn_message.setEvent_desc("达到限陪额"+tab_warn_setting.getRatio()+"%");
					tab_WARN_MESSAGEService.insertTAB_WARN_MESSAGE(tab_warn_message);
					for(TAB_WARN_RECEIVE tab_WARN_RECEIVE: lTab_WARN_RECEIVEs){
						SmsUtils.SendSms(tab_WARN_RECEIVE.getPhone(), "240505", new String[]{""});
					}					
				}
			}
		}
	}
	
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
	private void changeDjLang(List<T_LOTO_E> loto_Es,String feeType,String lang,String userPin) throws Exception{
		
		List<LangObj> lstLang=new ArrayList<LangObj>();
		for(T_LOTO_E e:loto_Es){
			LangObj langObj=new LangObj();
			langObj.guest_team_name=e.getGuest_team_name();
			langObj.home_team_name=e.getHome_team_name();
			langObj.issue=e.getIssue();
			langObj.leaguename=e.getLeaguename();
			langObj.options_one=e.getOptions_one();
			langObj.options_three=e.getOptions_three();
			langObj.options_two=e.getOptions_two();
			langObj.play_method=e.getPlay_method();
			lstLang.add(langObj);
		}
		Map<String,LangObj> mapLang= (new LangUtil(langListStrategy)).getLangMap(feeType,lang,userPin,lstLang);
		for(T_LOTO_E e:loto_Es){
			if(mapLang.containsKey(e.getIssue())){
				LangObj langObj=mapLang.get(e.getIssue());
				if(langObj.guest_team_name.isEmpty()==false)
				e.setGuest_team_name(langObj.guest_team_name);
				if(langObj.home_team_name.isEmpty()==false)
				e.setHome_team_name(langObj.home_team_name);
				if(langObj.leaguename.isEmpty()==false)
				e.setLeaguename(langObj.leaguename);
				if(langObj.options_one.isEmpty()==false)
				e.setOptions_one(langObj.options_one);
				if(langObj.options_three.isEmpty()==false)
				e.setOptions_three(langObj.options_three);
				if(langObj.options_two.isEmpty()==false)
				e.setOptions_two(langObj.options_two);
				if(langObj.play_method.isEmpty()==false)
				e.setPlay_method(langObj.play_method);				
			}
		}
	}
	private void payFoot(ReturnValue<PayAll_Data> rv, List<String> sbFalse, String channel, JSONArray lst_rem,
			String userPin, String nickName,String electronic_code,String lang) throws Exception {

		if(lst_rem.size()==0){
			rv.setMess(ErrorMessage.FAIL);
			logger.info("调用足球支付接口失败----->投注信息不能为[]");
			return;
		}
		String[] sbIssues = new String[lst_rem.size()];

		HashMap<String, RequestData> jsonMatchs = new HashMap<String, RequestData>();
		List<String> endMatchs = new ArrayList<String>();
		for (Object rem : lst_rem) {
			RequestData requestData = new RequestData();
			if(rem==null){
				rv.setMess(ErrorMessage.FAIL);
				logger.info("调用足球支付接口失败----->投注信息不能为[]");
				return;
			}
			if(((JSONObject) rem).containsKey("issue")==false){
				rv.setMess(ErrorMessage.FAIL);
				logger.info("调用足球支付接口失败----->投注信息不能为[]");
				return;
			}
			requestData.setIssue(((JSONObject) rem).get("issue").toString());
			List<Odd> odds = new ArrayList<Odd>();
			JSONArray jsonOdds = ((JSONObject) rem).getJSONArray("odds");
			for (Object jOdd : jsonOdds) {	
				if(((JSONObject) jOdd).get("odd_name").equals("hh")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("hd")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("ha")==false){
					rv.setMess(ErrorMessage.FAIL);
					logger.info("调用足球支付接口失败----->投注信息不正确"+((JSONObject) jOdd).get("odd_name").toString());
					return;
				}
				odds.add(new Odd(
						((JSONObject) jOdd).get("odd_name").toString(), (((JSONObject) jOdd).containsKey("odd_value")
								? ((JSONObject) jOdd).get("odd_value").toString() : ""),
						((JSONObject) jOdd).get("odd_cost").toString()));
				
			}
			
			requestData.setOdds(odds);
			jsonMatchs.put(requestData.getIssue(), requestData);
			endMatchs.add(requestData.getIssue());

		}
		int iIssue = 0;

		for (String issu : endMatchs) {

			sbIssues[iIssue++] = issu;
		}

		LOTO_F qryF = new LOTO_F();
		qryF.setElectronic_code(electronic_code);
		if(sbIssues.length>0){
			qryF.setIssues(sbIssues);
			
		}
		qryF.setHad_bet(1);
		qryF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		List<LOTO_F> loto_Fs = lotoFNService.selectLOTO_FNList(qryF);

		changeFsLang(loto_Fs,electronic_code,lang, userPin);
		for (LOTO_F b : loto_Fs) {
			if (b.getStatus() == 99) {
				rv.setMess(ErrorMessage.CANCEL);
				endMatchs.clear();
				endMatchs.add(b.getIssue());
				rv.getData().setEndmatchs(endMatchs);
				return;
			}

			if (endMatchs.indexOf(b.getIssue()) >= 0) {
				endMatchs.remove(b.getIssue());
			}

		}

		if (endMatchs.isEmpty() == false) {
			rv.setMess(ErrorMessage.END_MATCH);
			rv.getData().setEndmatchs(endMatchs);
			return;
		}
		
		TAB_SALES_THRESHOLD tab_SALES_THRESHOLD = new TAB_SALES_THRESHOLD();
		tab_SALES_THRESHOLD.setCurrency(electronic_code);
		tab_SALES_THRESHOLD = tab_SALES_THRESHOLDService.selectTAB_SALES_THRESHOLDList(tab_SALES_THRESHOLD).get(0);
		
		// 判断玩法是否已经到达赔率下线----->开始
		Long sum_cost = 0L;//本次总投注额
		for (LOTO_F f : loto_Fs) {
			String issue = f.getIssue();
			RequestData m = jsonMatchs.get(issue);
			
			for (Odd odd : m.odds) {
				if (odd.getOdd_name().equals("hh") || odd.getOdd_name().equals("ha")
						|| odd.getOdd_name().equals("hd")) {
					sum_cost+=(long)Double.parseDouble(odd.getOdd_cost());
					if (StringUtil.isEmpty(f.getHad_h())
					  ||new BigDecimal(f.getHad_h()).compareTo(new BigDecimal("1.01"))<0 
					  ||new BigDecimal(f.getHad_d()).compareTo(new BigDecimal("1.01"))<0
					  ||new BigDecimal(f.getHad_a()).compareTo(new BigDecimal("1.01"))<0) {
						rv.setMess(ErrorMessage.END_SELL);
						endMatchs.clear();
						endMatchs.add(f.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
					if (f.getHad_bet()==0) {
						rv.setMess(ErrorMessage.ERR_OVERFLOW);
						endMatchs.clear();
						endMatchs.add(f.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
					
					if(Long.parseLong(odd.getOdd_cost())>tab_SALES_THRESHOLD.getSingle_match_max()){
						rv.setMess(ErrorMessage.ERR_OVERMATCH);
						endMatchs.clear();				
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
				}
				
			}
			
		}
		if(sum_cost>tab_SALES_THRESHOLD.getSingle_lottery_max()){
			rv.setMess(ErrorMessage.ERR_OVERFLOW);
			endMatchs.clear();				
			rv.getData().setEndmatchs(endMatchs);
			return;
		}
		// 判断玩法是否已经到达赔率下线----->结束

		// 判断玩法是否已经到达限额----->开始

		T_LOTO_SIS_F qry_sis_f = new T_LOTO_SIS_F();
		qry_sis_f.setElectronic_code(electronic_code);
		qry_sis_f.setIssues(sbIssues);
		List<T_LOTO_SIS_F> lsT_LOTO_SIS_F = loto_SIS_FService.selectT_LOTO_SIS_FList(qry_sis_f);

		List<String> lsIssues=new ArrayList<String>();
		lsIssues.addAll(Arrays.asList(sbIssues));
		for(T_LOTO_SIS_F f:lsT_LOTO_SIS_F){			
			int inx=lsIssues.indexOf(f.getIssue());
			if(inx>=0){
				lsIssues.remove(inx);
			}
		}
		for(String iss:lsIssues){
			T_LOTO_SIS_F sis_F=new T_LOTO_SIS_F();
			sis_F.setIssue(iss);
			sis_F.setHad_a(0);
			sis_F.setHad_a_d(0);
			sis_F.setHad_d(0);
			sis_F.setHad_d_d(0);
			sis_F.setHad_h(0);
			sis_F.setHad_h_d(0);
			lsT_LOTO_SIS_F.add(sis_F);
		}
		HashMap<String, Boolean> canChangeOdd = new HashMap<String, Boolean>();
		List<String> canBet = new ArrayList<String>();

		
		for (T_LOTO_SIS_F sis_f : lsT_LOTO_SIS_F) {
			
			

			String issue = sis_f.getIssue();
			RequestData m = jsonMatchs.get(issue);
			long h_cost = 0;
			for (Odd odd : m.getOdds()) {
				if (odd.getOdd_name().equals("hh") || odd.getOdd_name().equals("hd")
						|| odd.getOdd_name().equals("ha")) {
					h_cost += (int)Double.parseDouble(odd.getOdd_cost());
				}
			}
			if (h_cost != 0) {
				checkCanChangeOdd(canChangeOdd, issue, 301,
						sis_f.getHad_h_d() + sis_f.getHad_d_d() + sis_f.getHad_a_d() + h_cost,
						sis_f.getHad_h_d() + sis_f.getHad_d_d() + sis_f.getHad_a_d(), tab_SALES_THRESHOLD.getXssx_min());
				checkCanBet(canBet, issue, 301, sis_f.getHad_h_d() + sis_f.getHad_d_d() + sis_f.getHad_a_d() + h_cost,
						tab_SALES_THRESHOLD.getXssx_min());

				if (sis_f.getHad_h_d() + sis_f.getHad_d_d() + sis_f.getHad_a_d() + h_cost > tab_SALES_THRESHOLD.getXssx_max()) {
					rv.setMess(ErrorMessage.ERR_OVERFLOW);
					endMatchs.clear();
					endMatchs.add(sis_f.getIssue());
					rv.getData().setEndmatchs(endMatchs);
					return;
				}

			}
			

		}
		// 判断玩法是否已经到达限额----->结束

		for (LOTO_F f : loto_Fs) {
			String order_id = UUID.randomUUID().toString().replace("-", "");
			String issue = f.getIssue();
			RequestData m = jsonMatchs.get(issue);

			Date nowDate = new Date();
			V_ORDER vOrder = new V_ORDER();
			vOrder.setBet_fee(0L);
			String bet_type = "";
			double oddv = 0;
			List<LOTO_ORDER> lstTemp = new ArrayList<LOTO_ORDER>();
			for (Odd odd : m.getOdds()) {
				String bet_info = "";
				String key = odd.getOdd_name();

				if (key.equals("hh")) {
					bet_type = "301";
					bet_info = "had_h|" + f.getHad_h();
					if (StringUtil.isEmpty(f.getHad_h())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(f.getHad_h());
					}
				} else if (key.equals("hd")) {
					bet_type = "301";
					bet_info = "had_d|" + f.getHad_d();
					if (StringUtil.isEmpty(f.getHad_d())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(f.getHad_d());
					}
				} else if (key.equals("ha")) {
					bet_type = "301";
					bet_info = "had_a|" + f.getHad_a();
					if (StringUtil.isEmpty(f.getHad_a())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(f.getHad_a());
					}
				} 

				LOTO_ORDER loto_ORDER = new LOTO_ORDER();
				loto_ORDER.setIssue(issue);
				loto_ORDER.setOrder_id(order_id);
				loto_ORDER.setBet_fee(Math.round(Double.parseDouble((odd.getOdd_cost()))));
				vOrder.setBet_fee(vOrder.getBet_fee() + loto_ORDER.getBet_fee());
				loto_ORDER.setBet_info(bet_info);
				loto_ORDER.setBet_type(Integer.valueOf(bet_type));
				loto_ORDER.setBet_status(1);
				loto_ORDER.setCreate_time(nowDate);
				loto_ORDER.setPrize_status(1);
				loto_ORDER.setPrize_type(1);
				loto_ORDER.setElectronic_code(electronic_code);
				loto_ORDER.setUser_pin(userPin);
				loto_ORDER.setMemo(nickName);
				loto_ORDER.setMatch_result("");
				loto_ORDER.setOptions_one("");
				loto_ORDER.setOptions_three("");
				loto_ORDER.setOptions_two("");
				loto_ORDER.setPlay_method("");
				loto_ORDER.setVsteam(f.getHome_team_name() + "vs" + f.getGuest_team_name());
				loto_ORDER.setWin_fee((int)Math.round(loto_ORDER.getBet_fee() * oddv));
				loto_ORDER.setPrize_cancel_time(DateUtils.getDateToStr("1900-1-1"));
				loto_ORDER.setOptions_one("");
				loto_ORDER.setLeaguename(f.getLeaguename());
				loto_ORDER.setOrder_source(channel);
				loto_ORDER.setVsresult("");
				loto_ORDER.setReward_bet_fee("");
				loto_ORDER.setReward_fee("");
				loto_ORDER.setReward_user_pin("");
				lstTemp.add(loto_ORDER);
				logger.info(String.format("开始调用Bet接口订单信息：order_id:%s,userPin:%s,bet_info:%s,bet_fee:%s",
						loto_ORDER.getOrder_id(), loto_ORDER.getUser_pin(), bet_info, loto_ORDER.getBet_fee()));
			}
			vOrder.setElectronic_code(electronic_code);
			vOrder.setBet_status(1);
			vOrder.setBet_type(Integer.parseInt(bet_type));
			vOrder.setCreate_time(DateUtils.getDateToStr(nowDate, "yyyy-MM-dd HH:mm:ss"));
			vOrder.setOrder_id(order_id);
			vOrder.setUser_pin(userPin);
			vOrder.setPrize_status(1);
			vOrder.setWin_fee(0);
			vOrder.setVsteam(f.getHome_team_name() + "vs" + f.getGuest_team_name());
			vOrder.setIssume(issue);
			try {
				v_orderService.insertV_ORDER(vOrder, lstTemp, canChangeOdd, canBet,tab_SALES_THRESHOLD);
			} catch (Exception e) {// 事物中的处理错误必须已异常的方式返回
				sbFalse.add(vOrder.getVsteam());
			}

			lstTemp = null;

		}
	}
	
	private void payDj(ReturnValue<PayAll_Data> rv, List<String> sbFalse, String channel, JSONArray lst_rem,
			String userPin, String nickName,String electronic_code,String lang) throws Exception {

		String[] sbIssues = new String[lst_rem.size()];

		HashMap<String, RequestData> jsonMatchs = new HashMap<String, RequestData>();
		List<String> endMatchs = new ArrayList<String>();
		for (Object rem : lst_rem) {
			RequestData requestData = new RequestData();
			if(rem==null){
				rv.setMess(ErrorMessage.FAIL);
				logger.info("调用话题支付接口失败----->投注信息不能为[]");
				return;
			}
			if(((JSONObject) rem).containsKey("issue")==false){
				rv.setMess(ErrorMessage.FAIL);
				logger.info("调用话题支付接口失败----->投注信息不能为[]");
				return;
			}
			requestData.setIssue(((JSONObject) rem).get("issue").toString());
			List<Odd> odds = new ArrayList<Odd>();
			JSONArray jsonOdds = ((JSONObject) rem).getJSONArray("odds");
			for (Object jOdd : jsonOdds) {
				if(((JSONObject) jOdd).get("odd_name").equals("odds_one")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("odds_two")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("odds_three")==false){
							rv.setMess(ErrorMessage.FAIL);
							logger.info("调用话题支付接口失败----->投注信息不正确"+((JSONObject) jOdd).get("odd_name").toString());
							return;
						}
				odds.add(new Odd(
						((JSONObject) jOdd).get("odd_name").toString(), (((JSONObject) jOdd).containsKey("odd_value")
								? ((JSONObject) jOdd).get("odd_value").toString() : ""),
						((JSONObject) jOdd).get("odd_cost").toString()));
			}

			requestData.setOdds(odds);
			jsonMatchs.put(requestData.getIssue(), requestData);
			endMatchs.add(requestData.getIssue());

		}
		int iIssue = 0;

		for (String issu : endMatchs) {

			sbIssues[iIssue++] = issu;
		}

		T_LOTO_E qryE = new T_LOTO_E();
		qryE.setElectronic_code(electronic_code);
		qryE.setIssues(sbIssues);
		qryE.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		List<T_LOTO_E> loto_Es = lotoENService.selectT_LOTO_ENList(qryE);
		changeDjLang(loto_Es,electronic_code, lang, userPin);
		Map<String, T_LOTO_E> mapEs=new HashMap<String,T_LOTO_E>();
		for (T_LOTO_E e : loto_Es) {
			mapEs.put(e.getIssue(), e);
			if (e.getStatus() == 99) {
				rv.setMess(ErrorMessage.CANCEL);
				endMatchs.clear();
				endMatchs.add(e.getIssue());
				rv.getData().setEndmatchs(endMatchs);
				return;
			}

			if (endMatchs.indexOf(e.getIssue()) >= 0) {
				endMatchs.remove(e.getIssue());
			}

		}

		if (endMatchs.isEmpty() == false) {
			rv.setMess(ErrorMessage.END_MATCH);
			rv.getData().setEndmatchs(endMatchs);
			return;
		}

		// 判断玩法是否已经到达赔率下线----->开始
		for (T_LOTO_E e : loto_Es) {
			String issue = e.getIssue();
			RequestData m = jsonMatchs.get(issue);
			Long sumCost=0L;
			if (m != null) {
				BigDecimal minOdd=new BigDecimal("1.01");
				for (Odd odd : m.odds) {
					if (odd.getOdd_name().equals("odds_one") || odd.getOdd_name().equals("odds_two")|| odd.getOdd_name().equals("odds_three")) {
						sumCost+=Long.parseLong(odd.getOdd_cost());
						BigDecimal odds_one=new BigDecimal("0");
						BigDecimal odds_two=new BigDecimal("0");;
						BigDecimal odds_three=new BigDecimal("0");
						if(StringUtil.isEmpty(e.getOdds_one())==false){
							odds_one=new BigDecimal(e.getOdds_one());
							odds_two=new BigDecimal(e.getOdds_two());
							odds_three=new BigDecimal(e.getOdds_three());
						}
						
						if (StringUtil.isEmpty(e.getOdds_one())||odds_one.compareTo(minOdd)<0||odds_two.compareTo(minOdd)<0 || odds_three.compareTo(minOdd)<0) {
							rv.setMess(ErrorMessage.END_SELL);
							endMatchs.clear();
							endMatchs.add(e.getIssue());
							rv.getData().setEndmatchs(endMatchs);
							return;
						}
						
						if (e.getMnl_bet()==0) {
							rv.setMess(ErrorMessage.ERR_OVERFLOW);
							endMatchs.clear();
							endMatchs.add(e.getIssue());
							rv.getData().setEndmatchs(endMatchs);
							return;
						}
						
						if(Long.parseLong(odd.getOdd_cost())>(long)(Double.parseDouble(e.getSingle_match_max())*1000000)){
							rv.setMess(ErrorMessage.ERR_OVERMATCH);
							endMatchs.clear();
							endMatchs.add(e.getIssue());
							rv.getData().setEndmatchs(endMatchs);
							return;
						}
					}
				}
			}
			if(sumCost>(long)(Double.parseDouble(e.getSingle_lottery_max())*1000000)){
				rv.setMess(ErrorMessage.ERR_OVERFLOW);
				endMatchs.clear();
				endMatchs.add(e.getIssue());
				rv.getData().setEndmatchs(endMatchs);
				return;
			}
		}

		// 判断玩法是否已经到达赔率下线----->结束

		// 判断玩法是否已经到达限额----->开始
		HashMap<String, Boolean> canChangeOdd = new HashMap<String, Boolean>();
		List<String> canBet = new ArrayList<String>();

		T_LOTO_SIS_E qry_sis_e = new T_LOTO_SIS_E();
		qry_sis_e.setIssues(sbIssues);
		qry_sis_e.setElectronic_code(electronic_code);
		List<T_LOTO_SIS_E> lsT_LOTO_SIS_E = loto_SIS_EService.selectT_LOTO_SIS_EList(qry_sis_e);
		
		List<String> lsIssues=new ArrayList<String>();
		lsIssues.addAll(Arrays.asList(sbIssues));
		for(T_LOTO_SIS_E e:lsT_LOTO_SIS_E){
			int inx=lsIssues.indexOf(e.getIssue());
			if(inx>=0){
				lsIssues.remove(inx);
			}
		}
		for(String iss:lsIssues){
			T_LOTO_SIS_E sis_E=new T_LOTO_SIS_E();
			sis_E.setIssue(iss);			
			sis_E.setOne(0L);
			sis_E.setOne_d(0L);
			sis_E.setTwo(0L);
			sis_E.setTwo_d(0L);
			sis_E.setThree(0L);
			sis_E.setThree_d(0L);
			sis_E.setElectronic_code(electronic_code);
			lsT_LOTO_SIS_E.add(sis_E);
		}
		
		for (T_LOTO_SIS_E sis_e : lsT_LOTO_SIS_E) {			

			String issue = sis_e.getIssue();
			RequestData m = jsonMatchs.get(issue);
			
			long m_cost=0;
			for (Odd odd : m.getOdds()) {				
				if (odd.getOdd_name().equals("odds_one") ){
					m_cost=(long)(Double.parseDouble(mapEs.get(sis_e.getIssue()).getOdds_one())* (long)Double.parseDouble(odd.getOdd_cost())-(long)Double.parseDouble(odd.getOdd_cost()));
				}
				if (odd.getOdd_name().equals("odds_two")){
					m_cost=(long)(Double.parseDouble(mapEs.get(sis_e.getIssue()).getOdds_one())* (long)Double.parseDouble(odd.getOdd_cost())-(long)Double.parseDouble(odd.getOdd_cost()));
				}
				if (odd.getOdd_name().equals("odds_three")){
					m_cost=(long)(Double.parseDouble(mapEs.get(sis_e.getIssue()).getOdds_one())* (long)Double.parseDouble(odd.getOdd_cost())-(long)Double.parseDouble(odd.getOdd_cost()));
				}
			}
			if (m_cost != 0) {
				long new_sum=StringUtil.ifnull(sis_e.getOne_p(),0L)+StringUtil.ifnull(sis_e.getTwo_p(),0L)+StringUtil.ifnull(sis_e.getThree_p(),0L)+ m_cost;
				long dcxssx_s=(long)(Double.parseDouble(mapEs.get(issue).getCompensate_max())*BetUtils.preMul);
				checkCanBet(canBet, issue, 501,new_sum ,dcxssx_s);

				if (new_sum > dcxssx_s) {
					rv.setMess(ErrorMessage.ERR_OVERFLOW);

					endMatchs.clear();
					endMatchs.add(sis_e.getIssue());
					rv.getData().setEndmatchs(endMatchs);
					return;
				}
				TAB_WARN_MESSAGE tab_WARN_MESSAGE=new TAB_WARN_MESSAGE();
				tab_WARN_MESSAGE.setCurrency(electronic_code);//'币种'
				tab_WARN_MESSAGE.setEvent_desc("");//事件说明 例如: 选项能达到限陪额85%//插入时动态确认
				tab_WARN_MESSAGE.setEvent_time(new Date());//事件发生时间
				tab_WARN_MESSAGE.setGuest_team_name(mapEs.get(sis_e.getIssue()).getGuest_team_name());//客队名称
				tab_WARN_MESSAGE.setHome_team_name(mapEs.get(sis_e.getIssue()).getHome_team_name());//主队名称
				tab_WARN_MESSAGE.setIssue(issue);//比赛对阵编号 用于操作这场比赛使用
				tab_WARN_MESSAGE.setMatch_name(mapEs.get(sis_e.getIssue()).getLeaguename());//赛事名称 e.g. 英超....
				tab_WARN_MESSAGE.setPlay_method(mapEs.get(sis_e.getIssue()).getPlay_method());//玩法 e.g. 梅西上半场是否能进2个球
				tab_WARN_MESSAGE.setProcessed(0);//是否处理过的 0 否1是
				tab_WARN_MESSAGE.setStop_time(DateUtils.getStrToDate(mapEs.get(sis_e.getIssue()).getEndtime(),"yyyyMMddHHmmss"));//话题停售时间
				tab_WARN_MESSAGE.setType(0);	//告警类型, 0竞猜话题		
				
				checkCanWarn(new_sum,dcxssx_s,tab_WARN_MESSAGE);
			}

		}
		// 判断玩法是否已经到达限额----->结束

		for (T_LOTO_E e : loto_Es) {
			String order_id = UUID.randomUUID().toString().replace("-", "");
			String issue = e.getIssue();
			RequestData m = jsonMatchs.get(issue);

			Date nowDate = new Date();
			V_ORDER vOrder = new V_ORDER();
			vOrder.setElectronic_code(electronic_code);
			
			vOrder.setBet_fee(0L);
			String bet_type = "";
			double oddv = 0;
			List<LOTO_ORDER> lstTemp = new ArrayList<LOTO_ORDER>();
			if(m==null)
				continue;
			for (Odd odd : m.getOdds()) {
				String bet_info = "";
				String key = odd.getOdd_name();

				if (key.equals("odds_one")) {
					bet_type = "501";
					bet_info = "options_one|" + e.getOdds_one();
					if (StringUtil.isEmpty(e.getOdds_one())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(e.getOdds_one());
					}
				} else if (key.equals("odds_two")) {
					bet_type = "501";
					bet_info = "options_two|" + e.getOdds_two();
					if (StringUtil.isEmpty(e.getOdds_two())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(e.getOdds_two());
					}
				} else if (key.equals("odds_three")) {
					bet_type = "501";
					bet_info = "options_three|" + e.getOdds_three();
					if (StringUtil.isEmpty(e.getOdds_three())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(e.getOdds_three());
					}
				}

				LOTO_ORDER loto_ORDER = new LOTO_ORDER();
				loto_ORDER.setIssue(issue);
				loto_ORDER.setOrder_id(order_id);
				loto_ORDER.setBet_fee(Math.round(Double.parseDouble(odd.getOdd_cost())));
				vOrder.setBet_fee(vOrder.getBet_fee() + loto_ORDER.getBet_fee());
				loto_ORDER.setBet_info(bet_info);
				loto_ORDER.setBet_type(Integer.valueOf(bet_type));
				loto_ORDER.setBet_status(1);
				loto_ORDER.setCreate_time(nowDate);
				loto_ORDER.setPrize_status(1);
				loto_ORDER.setPrize_type(1);
				loto_ORDER.setElectronic_code(electronic_code);
				loto_ORDER.setUser_pin(userPin);
				loto_ORDER.setMemo(nickName);
				loto_ORDER.setVsteam(e.getHome_team_name() + "vs" + e.getGuest_team_name());
				loto_ORDER.setWin_fee(Integer.parseInt(String.valueOf(Math.round(loto_ORDER.getBet_fee() * oddv))));
				loto_ORDER.setPrize_cancel_time(DateUtils.getDateToStr("1900-1-1"));
				loto_ORDER.setOrder_source(channel);
				loto_ORDER.setOptions_one(e.getOptions_one());
				loto_ORDER.setOptions_three(e.getOptions_three());
				loto_ORDER.setOptions_two(e.getOptions_two());
				loto_ORDER.setPlay_method(e.getPlay_method());
				loto_ORDER.setLeaguename(e.getLeaguename());
				loto_ORDER.setReward_bet_fee("");
				loto_ORDER.setReward_fee("");
				loto_ORDER.setReward_user_pin("");
				loto_ORDER.setPlay_method(e.getPlay_method());
				loto_ORDER.setOptions_one(e.getOptions_one());
				loto_ORDER.setOptions_three(e.getOptions_three());
				loto_ORDER.setOptions_two(e.getOptions_two());
				loto_ORDER.setMatch_result("");
				loto_ORDER.setVsresult("");
				lstTemp.add(loto_ORDER);
				logger.info(String.format("开始调用Bet接口订单信息：order_id:%s,userPin:%s,bet_info:%s,bet_fee:%s",
						loto_ORDER.getOrder_id(), loto_ORDER.getUser_pin(), bet_info, loto_ORDER.getBet_fee()));
			}

			vOrder.setBet_status(1);
			vOrder.setElectronic_code(electronic_code);
			vOrder.setBet_type(Integer.parseInt(bet_type));
			vOrder.setCreate_time(DateUtils.getDateToStr(nowDate, "yyyy-MM-dd HH:mm:ss"));
			vOrder.setOrder_id(order_id);
			vOrder.setUser_pin(userPin);
			vOrder.setPrize_status(1);
			vOrder.setWin_fee(0);
			vOrder.setVsteam(e.getHome_team_name() + "vs" + e.getGuest_team_name());
			vOrder.setIssume(issue);
			try {
				v_orderService.insertV_ORDER(vOrder, lstTemp, canChangeOdd, canBet,null);
				
			} catch (Exception e1) {// 事物中的处理错误必须已异常的方式返回
				sbFalse.add(vOrder.getVsteam());
			}

			lstTemp = null;

		}
	}
}