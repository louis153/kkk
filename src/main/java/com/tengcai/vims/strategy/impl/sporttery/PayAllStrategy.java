/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.entity.sporttery.LOTO_ORDER;
import com.tengcai.vims.entity.sporttery.T_LOTO_E;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_E;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F;
import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;
import com.tengcai.vims.entity.sporttery.V_ORDER;
import com.tengcai.vims.service.sporttery.LOTO_BNService;
import com.tengcai.vims.service.sporttery.LOTO_FNService;
import com.tengcai.vims.service.sporttery.T_LOTO_ENService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_BService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_EService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_FService;
import com.tengcai.vims.service.sporttery.T_QUOTATION_CONTROLService;
import com.tengcai.vims.service.sporttery.V_ORDERService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 * 蓝球支付
 * 
 * @return
 */
@Component("pay")
public class PayAllStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(PayAllStrategy.class);
	@Autowired
	private LOTO_BNService lotoBNService;
	@Autowired
	private LOTO_FNService lotoFNService;
	@Autowired
	private T_LOTO_ENService lotoENService;
	@Autowired
	private T_LOTO_SIS_BService loto_SIS_BService;
	@Autowired
	private T_LOTO_SIS_FService loto_SIS_FService;
	@Autowired
	private T_LOTO_SIS_EService loto_SIS_EService;
	@Autowired
	private T_QUOTATION_CONTROLService t_QUOTATION_CONTROLService;
	@Autowired
	private V_ORDERService v_orderService;

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
		private String l;
		private List<Odd> odds;

		public String getIssue() {
			return issue;
		}

		public void setIssue(String issue) {
			this.issue = issue;
		}

		public String getL() {
			return l;
		}

		public void setL(String l) {
			this.l = l;
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
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("pay开始调用支付接口doJsonMethod------>" + JSONObject.toJSONString(jsonRequest));
		ReturnValue<PayAll_Data> rv = new ReturnValue<>();
		rv.setData(new PayAll_Data());

		JSONArray lst = ((JSONArray) jsonRequest.get("lst"));
		if (lst.isEmpty()) {
			rv.setStatus(ErrorMessage.NO_REC.getCode());
			rv.setMessage(ErrorMessage.NO_REC.getMessage());

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
		for (Object positionObj : lst) {
			String position = ((JSONObject) positionObj).get("position").toString();
			switch (position) {
			case "2":
				payFoot(rv, sbFalse, channel, ((JSONObject) positionObj).getJSONArray("lst_rem"), userPin,
						jsonRequest.get("user_pin").toString());
				if (rv.getStatus() != null && rv.getStatus().equals(ErrorMessage.SUCCESS.getCode()) == false) {
					return JSONObject.toJSONString(rv);
				}
				break;
			case "3":
				payBasket(rv, sbFalse, channel, ((JSONObject) positionObj).getJSONArray("lst_rem"), userPin,
						jsonRequest.get("user_pin").toString());
				if (rv.getStatus() != null && rv.getStatus().equals(ErrorMessage.SUCCESS.getCode()) == false) {
					return JSONObject.toJSONString(rv);
				}
				break;
			case "4":
				payDj(rv, sbFalse, channel, ((JSONObject) positionObj).getJSONArray("lst_rem"), userPin,
						jsonRequest.get("user_pin").toString());
				if (rv.getStatus() != null && rv.getStatus().equals(ErrorMessage.SUCCESS.getCode()) == false) {
					return JSONObject.toJSONString(rv);
				}
				break;
			}

		}

		if (sbFalse.size() > 0) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setStatus(ErrorMessage.FAIL.getMessage());

			logger.info("调用支付接口失败----->");
			return JSONObject.toJSONString(rv);
		} else {
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			logger.info("调用支付接口成功----->");
			return JSONObject.toJSONString(rv);
		}
	}

	// 判断是否可以计算新赔率
	private void checkCanChangeOdd(HashMap<String, Boolean> canChangeOdd, String issue, Integer type, double new_sum,
			double oldSum, int dcxssx_s) {
		double per10 = dcxssx_s / 10;
		canChangeOdd.put(issue + "|" + type, Math.floor(oldSum / per10) != Math.floor(new_sum / per10));
	}

	// 判断是否达到销售限额
	private void checkCanBet(List<String> canBet, String issue, Integer type, double new_sum, int dcxssx_s) {
		if (new_sum >= dcxssx_s) {
			canBet.add(issue + "|" + type);
		}
	}

	private void payBasket(ReturnValue<PayAll_Data> rv, List<String> sbFalse, String channel, JSONArray lst_rem,
			String userPin, String user_pin) throws Exception {

		String[] sbIssues = new String[lst_rem.size()];

		HashMap<String, RequestData> jsonMatchs = new HashMap<String, RequestData>();
		List<String> endMatchs = new ArrayList<String>();
		for (Object rem : lst_rem) {
			RequestData requestData = new RequestData();
			if(rem==null){
				rv.setStatus(ErrorMessage.FAIL.getCode());
				rv.setMessage(ErrorMessage.FAIL.getMessage());
				logger.info("调用篮球支付接口失败----->投注信息不能为[]");
				return;
			}
			if(((JSONObject) rem).containsKey("issue")==false){
				rv.setStatus(ErrorMessage.FAIL.getCode());
				rv.setMessage(ErrorMessage.FAIL.getMessage());
				logger.info("调用篮球支付接口失败----->投注信息不能为[]");
				return;
			}
			requestData.setIssue(((JSONObject) rem).get("issue").toString());
			requestData.setL(((JSONObject) rem).get("l").toString());
			List<Odd> odds = new ArrayList<Odd>();
			JSONArray jsonOdds = ((JSONObject) rem).getJSONArray("odds");
			for (Object jOdd : jsonOdds) {
				if(((JSONObject) jOdd).get("odd_name").equals("hh")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("hl")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("mh")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("ma")==false){
					rv.setStatus(ErrorMessage.FAIL.getCode());
					rv.setMessage(ErrorMessage.FAIL.getMessage());
					logger.info("调用蓝球支付接口失败----->投注信息不正确"+((JSONObject) jOdd).get("odd_name").toString());
					return;
				}
				Odd odd = new Odd(
						((JSONObject) jOdd).get("odd_name").toString(), (((JSONObject) jOdd).containsKey("odd_value")
								? ((JSONObject) jOdd).get("odd_value").toString() : ""),
						((JSONObject) jOdd).get("odd_cost").toString());
				if (odd.getOdd_cost().equals("0")) {
					rv.setStatus(ErrorMessage.FAIL.getCode());
					rv.setMessage(ErrorMessage.FAIL.getMessage());
					logger.info("调用篮球支付接口失败----->投注金额不能为0");
					return;
				}
				odds.add(odd);
			}

			requestData.setOdds(odds);
			jsonMatchs.put(requestData.getIssue(), requestData);
			endMatchs.add(requestData.getIssue());

		}
		int iIssue = 0;

		for (String issu : endMatchs) {

			sbIssues[iIssue++] = issu;
		}

		LOTO_B qryB = new LOTO_B();

		qryB.setIssues(sbIssues);
		qryB.setHilo_bet(1);
		qryB.setMnl_bet(1);
		qryB.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		List<LOTO_B> loto_Bs = lotoBNService.selectLOTO_BNList(qryB);

		for (LOTO_B b : loto_Bs) {
			if (b.getStatus() == 99) {
				rv.setStatus(ErrorMessage.CANCEL.getCode());
				rv.setMessage(ErrorMessage.CANCEL.getMessage());
				endMatchs.clear();
				endMatchs.add(b.getIssue());
				rv.getData().setEndmatchs(endMatchs);
				return;
			}
			if (StringUtil.isDecimal(b.getHilo_presetscore())
					&& StringUtil.isDecimal(jsonMatchs.get(b.getIssue()).getL())) {
				if (String.format("%.2f", Double.parseDouble(b.getHilo_presetscore())).equals(
						String.format("%.2f", Double.parseDouble(jsonMatchs.get(b.getIssue()).getL()))) == false) {
					rv.setStatus(ErrorMessage.SDANDARD_ERR.getCode());
					rv.setMessage(ErrorMessage.SDANDARD_ERR.getMessage());
					endMatchs.clear();
					rv.getData().setEndmatchs(endMatchs);
					rv.getData().getChange_presetscore().add(new Presetscore(b.getIssue(), b.getHilo_presetscore()));
					return;
				}
			}

			if (endMatchs.indexOf(b.getIssue()) >= 0) {
				endMatchs.remove(b.getIssue());
			}

		}

		if (endMatchs.isEmpty() == false) {

			rv.setStatus(ErrorMessage.END_MATCH.getCode());
			rv.setMessage(ErrorMessage.END_MATCH.getMessage());
			rv.getData().setEndmatchs(endMatchs);
			return;
		}

		// 判断玩法是否已经到达赔率下线----->开始
		for (LOTO_B b : loto_Bs) {
			String issue = b.getIssue();
			RequestData m = jsonMatchs.get(issue);
			for (Odd odd : m.odds) {
				if (odd.getOdd_name().equals("hh") || odd.getOdd_name().equals("hl")) {
					if (StringUtil.isEmpty(b.getHilo_h())||b.getHilo_h().equals("1.01") || b.getHilo_l().equals("1.01")) {
						rv.setStatus(ErrorMessage.END_SELL.getCode());
						rv.setMessage(ErrorMessage.END_SELL.getMessage());
						endMatchs.clear();
						endMatchs.add(b.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
					if (b.getHilo_bet()==0) {
						rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
						rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());
						endMatchs.clear();
						endMatchs.add(b.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
				}
				if (odd.getOdd_name().equals("ma") || odd.getOdd_name().equals("mh")) {
					if (StringUtil.isEmpty(b.getMnl_h())||b.getMnl_h().equals("1.01") || b.getMnl_a().equals("1.01")) {
						rv.setStatus(ErrorMessage.END_SELL.getCode());
						rv.setMessage(ErrorMessage.END_SELL.getMessage());
						endMatchs.clear();
						endMatchs.add(b.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
					if (b.getMnl_bet()==0) {
						rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
						rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());
						endMatchs.clear();
						endMatchs.add(b.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
				}
			}
		}
		// 判断玩法是否已经到达赔率下线----->结束

		// 判断玩法是否已经到达限额----->开始
		HashMap<String, Boolean> canChangeOdd = new HashMap<String, Boolean>();
		List<String> canBet = new ArrayList<String>();

		T_LOTO_SIS_B qry_sis_b = new T_LOTO_SIS_B();
		qry_sis_b.setIssues(sbIssues);
		List<T_LOTO_SIS_B> lsT_LOTO_SIS_B = loto_SIS_BService.selectT_LOTO_SIS_BList(qry_sis_b);
		
		List<String> lsIssues=new ArrayList<String>();
		lsIssues.addAll(Arrays.asList(sbIssues));
		for(T_LOTO_SIS_B b:lsT_LOTO_SIS_B){
			int inx=lsIssues.indexOf(b.getIssue());
			if(inx>=0){
				lsIssues.remove(inx);
			}
		}
		for(String iss:lsIssues){
			T_LOTO_SIS_B sis_B=new T_LOTO_SIS_B();
			sis_B.setIssue(iss);
			sis_B.setHilo_h(0);
			sis_B.setHilo_h_d(0);
			sis_B.setHilo_l(0);
			sis_B.setHilo_l_d(0);
			sis_B.setMnl_h(0);
			sis_B.setMnl_h_d(0);
			sis_B.setMnl_a(0);
			sis_B.setMnl_a_d(0);
			lsT_LOTO_SIS_B.add(sis_B);
		}
		T_QUOTATION_CONTROL quotation_control = new T_QUOTATION_CONTROL();
		quotation_control.setId(3);
		quotation_control = t_QUOTATION_CONTROLService.selectT_QUOTATION_CONTROLList(quotation_control).get(0);
		for (T_LOTO_SIS_B sis_b : lsT_LOTO_SIS_B) {
			

			String issue = sis_b.getIssue();
			RequestData m = jsonMatchs.get(issue);
			int h_cost = 0, m_cost = 0;
			for (Odd odd : m.getOdds()) {
				if (odd.getOdd_name().equals("hh") || odd.getOdd_name().equals("hl")) {
					h_cost += (int)Double.parseDouble(odd.getOdd_cost());
				}
				if (odd.getOdd_name().equals("ma") || odd.getOdd_name().equals("mh")) {
					m_cost += (int)Double.parseDouble(odd.getOdd_cost());
				}
			}
			if (h_cost != 0) {
				checkCanChangeOdd(canChangeOdd, issue, 309, sis_b.getHilo_h_d() + sis_b.getHilo_l_d() + h_cost,
						sis_b.getHilo_h_d() + sis_b.getHilo_l_d(), quotation_control.getDcxssx_s());
				checkCanBet(canBet, issue, 309, sis_b.getHilo_h_d() + sis_b.getHilo_l_d() + h_cost,
						quotation_control.getDcxssx_s());
				if (sis_b.getHilo_h_d() + sis_b.getHilo_l_d() + h_cost > quotation_control.getDcxssx_e()) {
					rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
					rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());

					endMatchs.clear();
					endMatchs.add(sis_b.getIssue());
					rv.getData().setEndmatchs(endMatchs);
					return;
				}

			}
			if (m_cost != 0) {
				checkCanChangeOdd(canChangeOdd, issue, 307, sis_b.getMnl_a_d() + sis_b.getMnl_h_d() + m_cost,
						sis_b.getMnl_h_d() + sis_b.getMnl_a_d(), quotation_control.getDcxssx_s());
				checkCanBet(canBet, issue, 307, sis_b.getMnl_a_d() + sis_b.getMnl_h_d() + m_cost,
						quotation_control.getDcxssx_s());

				if (sis_b.getMnl_a_d() + sis_b.getMnl_h_d() + m_cost > quotation_control.getDcxssx_e()) {
					rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
					rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());

					endMatchs.clear();
					endMatchs.add(sis_b.getIssue());
					rv.getData().setEndmatchs(endMatchs);
					return;
				}
			}

		}
		// 判断玩法是否已经到达限额----->结束

		for (LOTO_B b : loto_Bs) {
			String order_id = UUID.randomUUID().toString().replace("-", "");
			String issue = b.getIssue();
			RequestData m = jsonMatchs.get(issue);

			Date nowDate = new Date();
			V_ORDER vOrder = new V_ORDER();
			vOrder.setBet_fee(0);
			String bet_type = "";
			double oddv = 0;
			List<LOTO_ORDER> lstTemp = new ArrayList<LOTO_ORDER>();
			for (Odd odd : m.getOdds()) {
				String bet_info = "";
				String key = odd.getOdd_name();

				if (key.equals("hh")) {
					bet_type = "309";
					bet_info = "hilo_h|" + b.getHilo_presetscore();
					if (StringUtil.isEmpty(b.getHilo_h())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(b.getHilo_h());
					}
				} else if (key.equals("hl")) {
					bet_type = "309";
					bet_info = "hilo_l|" + b.getHilo_presetscore();
					if (StringUtil.isEmpty(b.getHilo_l())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(b.getHilo_l());
					}
				} else if (key.equals("ma")) {
					bet_type = "307";
					bet_info = "mnl_a|" + b.getMnl_a();
					if (StringUtil.isEmpty(b.getMnl_a())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(b.getMnl_a());
					}
				} else if (key.equals("mh")) {
					bet_type = "307";
					bet_info = "mnl_h|" + b.getMnl_h();
					if (StringUtil.isEmpty(b.getMnl_h())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(b.getMnl_h());
					}
				}

				LOTO_ORDER loto_ORDER = new LOTO_ORDER();
				loto_ORDER.setIssue(issue);
				loto_ORDER.setOrder_id(order_id);
				loto_ORDER.setBet_fee((int)Math.round(Double.parseDouble(odd.getOdd_cost())));
				vOrder.setBet_fee(vOrder.getBet_fee() + loto_ORDER.getBet_fee());
				loto_ORDER.setBet_info(bet_info);
				loto_ORDER.setBet_type(Integer.valueOf(bet_type));
				loto_ORDER.setBet_status(1);
				loto_ORDER.setCreate_time(nowDate);
				loto_ORDER.setPrize_status(1);
				loto_ORDER.setPrize_type(1);

				loto_ORDER.setUser_pin(userPin);
				loto_ORDER.setMemo(user_pin);
				loto_ORDER.setVsteam(b.getHome_team_name() + "vs" + b.getGuest_team_name());
				loto_ORDER.setWin_fee(Integer.parseInt(String.valueOf(Math.round(loto_ORDER.getBet_fee() * oddv))));
				loto_ORDER.setPrize_cancel_time(DateUtils.getDateToStr("1900-1-1"));
				loto_ORDER.setOrder_source(channel);
				loto_ORDER.setVsresult("");
				lstTemp.add(loto_ORDER);
				logger.info(String.format("开始调用Bet接口订单信息：order_id:%s,userPin:%s,bet_info:%s,bet_fee:%s",
						loto_ORDER.getOrder_id(), loto_ORDER.getUser_pin(), bet_info, loto_ORDER.getBet_fee()));
			}

			vOrder.setBet_status(1);
			vOrder.setBet_type(Integer.parseInt(bet_type));
			vOrder.setCreate_time(DateUtils.getDateToStr(nowDate, "yyyy-MM-dd HH:mm:ss"));
			vOrder.setOrder_id(order_id);
			vOrder.setUser_pin(userPin);
			vOrder.setPrize_status(1);
			vOrder.setWin_fee(0);
			vOrder.setVsteam(b.getGuest_team_name() + "vs" + b.getHome_team_name());
			vOrder.setIssume(issue);
			try {
				v_orderService.insertV_ORDER(vOrder, lstTemp, canChangeOdd, canBet,quotation_control);
			} catch (Exception e) {// 事物中的处理错误必须已异常的方式返回
				sbFalse.add(vOrder.getVsteam());
			}

			lstTemp = null;

		}
	}

	private void payFoot(ReturnValue<PayAll_Data> rv, List<String> sbFalse, String channel, JSONArray lst_rem,
			String userPin, String user_pin) throws Exception {

		String[] sbIssues = new String[lst_rem.size()];

		HashMap<String, RequestData> jsonMatchs = new HashMap<String, RequestData>();
		List<String> endMatchs = new ArrayList<String>();
		for (Object rem : lst_rem) {
			RequestData requestData = new RequestData();
			if(rem==null){
				rv.setStatus(ErrorMessage.FAIL.getCode());
				rv.setMessage(ErrorMessage.FAIL.getMessage());
				logger.info("调用足球支付接口失败----->投注信息不能为[]");
				return;
			}
			if(((JSONObject) rem).containsKey("issue")==false){
				rv.setStatus(ErrorMessage.FAIL.getCode());
				rv.setMessage(ErrorMessage.FAIL.getMessage());
				logger.info("调用足球支付接口失败----->投注信息不能为[]");
				return;
			}
			requestData.setIssue(((JSONObject) rem).get("issue").toString());
			requestData.setL(((JSONObject) rem).get("l").toString());
			List<Odd> odds = new ArrayList<Odd>();
			JSONArray jsonOdds = ((JSONObject) rem).getJSONArray("odds");
			for (Object jOdd : jsonOdds) {	
				if(((JSONObject) jOdd).get("odd_name").equals("hh")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("hd")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("ha")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("rh")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("rd")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("ra")==false){
					rv.setStatus(ErrorMessage.FAIL.getCode());
					rv.setMessage(ErrorMessage.FAIL.getMessage());
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

		qryF.setIssues(sbIssues);
		qryF.setHad_bet(1);
		qryF.setHhad_bet(1);
		qryF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		List<LOTO_F> loto_Fs = lotoFNService.selectLOTO_FNList(qryF);

		for (LOTO_F b : loto_Fs) {
			if (b.getStatus() == 99) {
				rv.setStatus(ErrorMessage.CANCEL.getCode());
				rv.setMessage(ErrorMessage.CANCEL.getMessage());
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

			rv.setStatus(ErrorMessage.END_MATCH.getCode());
			rv.setMessage(ErrorMessage.END_MATCH.getMessage());
			rv.getData().setEndmatchs(endMatchs);
			return;
		}

		// 判断玩法是否已经到达赔率下线----->开始
		for (LOTO_F f : loto_Fs) {
			String issue = f.getIssue();
			RequestData m = jsonMatchs.get(issue);
			for (Odd odd : m.odds) {
				if (odd.getOdd_name().equals("hh") || odd.getOdd_name().equals("ha")
						|| odd.getOdd_name().equals("hd")) {
					if (StringUtil.isEmpty(f.getHad_h())||f.getHad_h().equals("1.01") || f.getHad_d().equals("1.01") || f.getHad_a().equals("1.01")) {
						rv.setStatus(ErrorMessage.END_SELL.getCode());
						rv.setMessage(ErrorMessage.END_SELL.getMessage());
						endMatchs.clear();
						endMatchs.add(f.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
					if (f.getHad_bet()==0) {
						rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
						rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());
						endMatchs.clear();
						endMatchs.add(f.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
				}
				if (odd.getOdd_name().equals("rh") || odd.getOdd_name().equals("rd")
						|| odd.getOdd_name().equals("ra")) {
					if (StringUtil.isEmpty(f.getHhad_h())||f.getHhad_h().equals("1.01") || f.getHhad_d().equals("1.01") || f.getHhad_a().equals("1.01")) {
						rv.setStatus(ErrorMessage.END_SELL.getCode());
						rv.setMessage(ErrorMessage.END_SELL.getMessage());
						endMatchs.clear();
						endMatchs.add(f.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
					if (f.getHhad_bet()==0) {
						rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
						rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());
						endMatchs.clear();
						endMatchs.add(f.getIssue());
						rv.getData().setEndmatchs(endMatchs);
						return;
					}
				}
			}
		}
		// 判断玩法是否已经到达赔率下线----->结束

		// 判断玩法是否已经到达限额----->开始

		T_LOTO_SIS_F qry_sis_f = new T_LOTO_SIS_F();
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
			sis_F.setHhad_a(0);
			sis_F.setHhad_a_d(0);
			sis_F.setHhad_d(0);
			sis_F.setHhad_d_d(0);
			sis_F.setHhad_h(0);
			sis_F.setHhad_h_d(0);
			lsT_LOTO_SIS_F.add(sis_F);
		}
		HashMap<String, Boolean> canChangeOdd = new HashMap<String, Boolean>();
		List<String> canBet = new ArrayList<String>();

		T_QUOTATION_CONTROL quotation_control = new T_QUOTATION_CONTROL();
		quotation_control.setId(2);
		quotation_control = t_QUOTATION_CONTROLService.selectT_QUOTATION_CONTROLList(quotation_control).get(0);
		for (T_LOTO_SIS_F sis_f : lsT_LOTO_SIS_F) {
			
			

			String issue = sis_f.getIssue();
			RequestData m = jsonMatchs.get(issue);
			int h_cost = 0, r_cost = 0;
			for (Odd odd : m.getOdds()) {
				if (odd.getOdd_name().equals("hh") || odd.getOdd_name().equals("hd")
						|| odd.getOdd_name().equals("ha")) {
					h_cost += (int)Double.parseDouble(odd.getOdd_cost());
				}
				if (odd.getOdd_name().equals("rh") || odd.getOdd_name().equals("rd")
						|| odd.getOdd_name().equals("ra")) {
					r_cost += (int)Double.parseDouble(odd.getOdd_cost());
				}
			}
			if (h_cost != 0) {
				checkCanChangeOdd(canChangeOdd, issue, 301,
						sis_f.getHad_h_d() + sis_f.getHad_d_d() + sis_f.getHad_a_d() + h_cost,
						sis_f.getHad_h_d() + sis_f.getHad_d_d() + sis_f.getHad_a_d(), quotation_control.getDcxssx_s());
				checkCanBet(canBet, issue, 301, sis_f.getHad_h_d() + sis_f.getHad_d_d() + sis_f.getHad_a_d() + h_cost,
						quotation_control.getDcxssx_s());

				if (sis_f.getHad_h_d() + sis_f.getHad_d_d() + sis_f.getHad_a_d() + h_cost > quotation_control
						.getDcxssx_e()) {
					rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
					rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());

					endMatchs.clear();
					endMatchs.add(sis_f.getIssue());
					rv.getData().setEndmatchs(endMatchs);
					return;
				}

			}
			if (r_cost != 0) {
				checkCanChangeOdd(canChangeOdd, issue, 305,
						sis_f.getHhad_h_d() + sis_f.getHhad_d_d() + sis_f.getHhad_a_d() + r_cost,
						sis_f.getHhad_h_d() + sis_f.getHhad_d_d() + sis_f.getHhad_a_d(),
						quotation_control.getDcxssx_s());
				checkCanBet(canBet, issue, 305,
						sis_f.getHhad_h_d() + sis_f.getHhad_d_d() + sis_f.getHhad_a_d() + r_cost,
						quotation_control.getDcxssx_s());

				if (sis_f.getHhad_h_d() + sis_f.getHhad_d_d() + sis_f.getHhad_a_d() + r_cost > quotation_control
						.getDcxssx_e()) {
					rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
					rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());

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
			vOrder.setBet_fee(0);
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
				} else if (key.equals("ra")) {
					bet_type = "305";
					bet_info = "hhad_a|" + f.getHhad_a();
					if (StringUtil.isEmpty(f.getHhad_a())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(f.getHhad_a());
					}
				} else if (key.equals("rd")) {
					bet_type = "305";
					bet_info = "hhad_d|" + f.getHhad_d();
					if (StringUtil.isEmpty(f.getHhad_d())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(f.getHhad_d());
					}
				} else if (key.equals("rh")) {
					bet_type = "305";
					bet_info = "hhad_h|" + f.getHhad_h();
					if (StringUtil.isEmpty(f.getHhad_h())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(f.getHhad_h());
					}
				}

				LOTO_ORDER loto_ORDER = new LOTO_ORDER();
				loto_ORDER.setIssue(issue);
				loto_ORDER.setOrder_id(order_id);
				loto_ORDER.setBet_fee((int)Math.round(Double.parseDouble((odd.getOdd_cost()))));
				vOrder.setBet_fee(vOrder.getBet_fee() + loto_ORDER.getBet_fee());
				loto_ORDER.setBet_info(bet_info);
				loto_ORDER.setBet_type(Integer.valueOf(bet_type));
				loto_ORDER.setBet_status(1);
				loto_ORDER.setCreate_time(nowDate);
				loto_ORDER.setPrize_status(1);
				loto_ORDER.setPrize_type(1);

				loto_ORDER.setUser_pin(userPin);
				loto_ORDER.setMemo(user_pin);
				loto_ORDER.setVsteam(f.getHome_team_name() + "vs" + f.getGuest_team_name());
				loto_ORDER.setWin_fee((int)Math.round(loto_ORDER.getBet_fee() * oddv));
				loto_ORDER.setPrize_cancel_time(DateUtils.getDateToStr("1900-1-1"));
				loto_ORDER.setOrder_source(channel);
				loto_ORDER.setVsresult("");
				lstTemp.add(loto_ORDER);
				logger.info(String.format("开始调用Bet接口订单信息：order_id:%s,userPin:%s,bet_info:%s,bet_fee:%s",
						loto_ORDER.getOrder_id(), loto_ORDER.getUser_pin(), bet_info, loto_ORDER.getBet_fee()));
			}

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
				v_orderService.insertV_ORDER(vOrder, lstTemp, canChangeOdd, canBet,quotation_control);
			} catch (Exception e) {// 事物中的处理错误必须已异常的方式返回
				sbFalse.add(vOrder.getVsteam());
			}

			lstTemp = null;

		}
	}

	private void payDj(ReturnValue<PayAll_Data> rv, List<String> sbFalse, String channel, JSONArray lst_rem,
			String userPin, String user_pin) throws Exception {

		String[] sbIssues = new String[lst_rem.size()];

		HashMap<String, RequestData> jsonMatchs = new HashMap<String, RequestData>();
		List<String> endMatchs = new ArrayList<String>();
		for (Object rem : lst_rem) {
			RequestData requestData = new RequestData();
			if(rem==null){
				rv.setStatus(ErrorMessage.FAIL.getCode());
				rv.setMessage(ErrorMessage.FAIL.getMessage());
				logger.info("调用电竞支付接口失败----->投注信息不能为[]");
				return;
			}
			if(((JSONObject) rem).containsKey("issue")==false){
				rv.setStatus(ErrorMessage.FAIL.getCode());
				rv.setMessage(ErrorMessage.FAIL.getMessage());
				logger.info("调用电竞支付接口失败----->投注信息不能为[]");
				return;
			}
			requestData.setIssue(((JSONObject) rem).get("issue").toString());
			requestData.setL("0");
			List<Odd> odds = new ArrayList<Odd>();
			JSONArray jsonOdds = ((JSONObject) rem).getJSONArray("odds");
			for (Object jOdd : jsonOdds) {
				if(((JSONObject) jOdd).get("odd_name").equals("mh")==false
				&& ((JSONObject) jOdd).get("odd_name").equals("ma")==false){
							rv.setStatus(ErrorMessage.FAIL.getCode());
							rv.setMessage(ErrorMessage.FAIL.getMessage());
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

		T_LOTO_E qryE = new T_LOTO_E();

		qryE.setIssues(sbIssues);
		qryE.setEndtime(DateUtils.getDateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		List<T_LOTO_E> loto_Es = lotoENService.selectT_LOTO_ENList(qryE);

		for (T_LOTO_E e : loto_Es) {
			if (e.getStatus() == 99) {
				rv.setStatus(ErrorMessage.CANCEL.getCode());
				rv.setMessage(ErrorMessage.CANCEL.getMessage());
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

			rv.setStatus(ErrorMessage.END_MATCH.getCode());
			rv.setMessage(ErrorMessage.END_MATCH.getMessage());
			rv.getData().setEndmatchs(endMatchs);
			return;
		}

		// 判断玩法是否已经到达赔率下线----->开始
		for (T_LOTO_E e : loto_Es) {
			String issue = e.getIssue();
			RequestData m = jsonMatchs.get(issue);
			if (m != null) {
				for (Odd odd : m.odds) {
					if (odd.getOdd_name().equals("mh") || odd.getOdd_name().equals("ma")) {
						if (StringUtil.isEmpty(e.getMnl_h())||e.getMnl_h().equals("1.01") || e.getMnl_a().equals("1.01")) {
							rv.setStatus(ErrorMessage.END_SELL.getCode());
							rv.setMessage(ErrorMessage.END_SELL.getMessage());
							endMatchs.clear();
							endMatchs.add(e.getIssue());
							rv.getData().setEndmatchs(endMatchs);
							return;
						}
						if (e.getMnl_bet()==0) {
							rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
							rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());
							endMatchs.clear();
							endMatchs.add(e.getIssue());
							rv.getData().setEndmatchs(endMatchs);
							return;
						}
					}
				}
			}
		}

		// 判断玩法是否已经到达赔率下线----->结束

		// 判断玩法是否已经到达限额----->开始
		HashMap<String, Boolean> canChangeOdd = new HashMap<String, Boolean>();
		List<String> canBet = new ArrayList<String>();

		T_LOTO_SIS_E qry_sis_e = new T_LOTO_SIS_E();
		qry_sis_e.setIssues(sbIssues);
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
			sis_E.setMnl_h(0);
			sis_E.setMnl_h_d(0);
			sis_E.setMnl_a(0);
			sis_E.setMnl_a_d(0);
			lsT_LOTO_SIS_E.add(sis_E);
		}
		T_QUOTATION_CONTROL quotation_control = new T_QUOTATION_CONTROL();
		quotation_control.setId(4);
		quotation_control = t_QUOTATION_CONTROLService.selectT_QUOTATION_CONTROLList(quotation_control).get(0);
		for (T_LOTO_SIS_E sis_e : lsT_LOTO_SIS_E) {			

			String issue = sis_e.getIssue();
			RequestData m = jsonMatchs.get(issue);
			int m_cost = 0;
			for (Odd odd : m.getOdds()) {
				if (odd.getOdd_name().equals("mh") || odd.getOdd_name().equals("ma")) {
					m_cost += (int)Double.parseDouble(odd.getOdd_cost());
				}

			}
			if (m_cost != 0) {
				checkCanChangeOdd(canChangeOdd, issue, 407, sis_e.getMnl_h_d() + sis_e.getMnl_a_d() + m_cost,
						sis_e.getMnl_h_d() + sis_e.getMnl_a_d(), quotation_control.getDcxssx_s());
				checkCanBet(canBet, issue, 407, sis_e.getMnl_h_d() + sis_e.getMnl_a_d() + m_cost,
						quotation_control.getDcxssx_s());

				if (sis_e.getMnl_h_d() + sis_e.getMnl_a_d() + m_cost > quotation_control.getDcxssx_e()) {
					rv.setStatus(ErrorMessage.ERR_OVERFLOW.getCode());
					rv.setMessage(ErrorMessage.ERR_OVERFLOW.getMessage());

					endMatchs.clear();
					endMatchs.add(sis_e.getIssue());
					rv.getData().setEndmatchs(endMatchs);
					return;
				}

			}

		}
		// 判断玩法是否已经到达限额----->结束

		for (T_LOTO_E e : loto_Es) {
			String order_id = UUID.randomUUID().toString().replace("-", "");
			String issue = e.getIssue();
			RequestData m = jsonMatchs.get(issue);

			Date nowDate = new Date();
			V_ORDER vOrder = new V_ORDER();
			vOrder.setBet_fee(0);
			String bet_type = "";
			double oddv = 0;
			List<LOTO_ORDER> lstTemp = new ArrayList<LOTO_ORDER>();
			if(m==null)
				continue;
			for (Odd odd : m.getOdds()) {
				String bet_info = "";
				String key = odd.getOdd_name();

				if (key.equals("mh")) {
					bet_type = "407";
					bet_info = "mnl_h|" + e.getMnl_h();
					if (StringUtil.isEmpty(e.getMnl_h())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(e.getMnl_h());
					}
				} else if (key.equals("ma")) {
					bet_type = "407";
					bet_info = "mnl_a|" + e.getMnl_a();
					if (StringUtil.isEmpty(e.getMnl_a())) {// 如果赔率为空，设置投注赔率为1
						oddv = 1;
					} else {
						oddv = Double.parseDouble(e.getMnl_a());
					}
				}

				LOTO_ORDER loto_ORDER = new LOTO_ORDER();
				loto_ORDER.setIssue(issue);
				loto_ORDER.setOrder_id(order_id);
				loto_ORDER.setBet_fee((int)Math.round(Double.parseDouble(odd.getOdd_cost())));
				vOrder.setBet_fee(vOrder.getBet_fee() + loto_ORDER.getBet_fee());
				loto_ORDER.setBet_info(bet_info);
				loto_ORDER.setBet_type(Integer.valueOf(bet_type));
				loto_ORDER.setBet_status(1);
				loto_ORDER.setCreate_time(nowDate);
				loto_ORDER.setPrize_status(1);
				loto_ORDER.setPrize_type(1);

				loto_ORDER.setUser_pin(userPin);
				loto_ORDER.setMemo(user_pin);
				loto_ORDER.setVsteam(e.getHome_team_name() + "vs" + e.getGuest_team_name());
				loto_ORDER.setWin_fee(Integer.parseInt(String.valueOf(Math.round(loto_ORDER.getBet_fee() * oddv))));
				loto_ORDER.setPrize_cancel_time(DateUtils.getDateToStr("1900-1-1"));
				loto_ORDER.setOrder_source(channel);
				loto_ORDER.setVsresult("");
				lstTemp.add(loto_ORDER);
				logger.info(String.format("开始调用Bet接口订单信息：order_id:%s,userPin:%s,bet_info:%s,bet_fee:%s",
						loto_ORDER.getOrder_id(), loto_ORDER.getUser_pin(), bet_info, loto_ORDER.getBet_fee()));
			}

			vOrder.setBet_status(1);
			vOrder.setBet_type(Integer.parseInt(bet_type));
			vOrder.setCreate_time(DateUtils.getDateToStr(nowDate, "yyyy-MM-dd HH:mm:ss"));
			vOrder.setOrder_id(order_id);
			vOrder.setUser_pin(userPin);
			vOrder.setPrize_status(1);
			vOrder.setWin_fee(0);
			vOrder.setVsteam(e.getHome_team_name() + "vs" + e.getGuest_team_name());
			vOrder.setIssume(issue);
			try {
				v_orderService.insertV_ORDER(vOrder, lstTemp, canChangeOdd, canBet,quotation_control);
				v_orderService.updateDPs(vOrder.getIssume(), canChangeOdd, canBet);
			} catch (Exception e1) {// 事物中的处理错误必须已异常的方式返回
				sbFalse.add(vOrder.getVsteam());
			}

			lstTemp = null;

		}
	}
}