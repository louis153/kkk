/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.entity.sporttery.LOTO_ORDER;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;
import com.tengcai.vims.entity.sporttery.V_ORDER;
import com.tengcai.vims.service.sporttery.LOTO_BNService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_BService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.service.sporttery.T_QUOTATION_CONTROLService;
import com.tengcai.vims.service.sporttery.V_ORDERService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.StringUtil;

/**
 * 蓝球支付
 * 
 * @return
 */
@Component("payB")
public class PayBStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(PayBStrategy.class);
	@Autowired
	private LOTO_BNService lotoBNService;
	@Autowired
	private T_LOTO_SIS_BService t_LOTO_SIS_BService;
	@Autowired
	private T_QUOTATION_CONTROLService t_QUOTATION_CONTROLService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;
	@Autowired
	private V_ORDERService v_orderService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("payB开始调用蓝球支付接口doJsonMethod------>" + JSONObject.toJSONString(jsonRequest));
		LOTO_B qryB = new LOTO_B();

		JSONObject jsonMatchs = ((JSONObject) jsonRequest.get("sel_matchs"));
		String[] sbIssues = new String[jsonMatchs.size()];
		String channel = "1";
		if (StringUtil.isEmpty(jsonRequest.get("channel")) == false) {
			channel = jsonRequest.get("channel").toString();// 获取渠道标记
		}
		T_MARKET_CHANNEL t_market_channel = new T_MARKET_CHANNEL();
		t_market_channel.setChannelstate("1");
		t_market_channel.setChannelnum(Integer.valueOf(channel));
		if (t_MARKET_CHANNELService.selectT_MARKET_CHANNELCount(t_market_channel) == 0) {
			channel = "1";
		}
		;
		Iterator<String> issues = jsonMatchs.keySet().iterator();
		List<String> endMatchs = new ArrayList<String>();
		while (issues.hasNext()) {
			endMatchs.add(issues.next());
		}
		int iIssue = 0;
		for (String issu : endMatchs) {
			sbIssues[iIssue++] = issu;
		}

		@SuppressWarnings("unused")
		int len = sbIssues.length;

		HashMap<String, Object> rv = new HashMap<String, Object>();
		qryB.setIssues(sbIssues);
		qryB.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		List<LOTO_B> loto_Bs = lotoBNService.selectLOTO_BNList(qryB);

		for (LOTO_B b : loto_Bs) {
			if (b.getStatus() == 99) {
				rv.put("state", "000001");
				endMatchs.clear();
				endMatchs.add(b.getIssue());
				rv.put("endMatch", endMatchs);
				rv.put("msg", "比赛已经取消");
				return JSONObject.toJSONString(rv);
			}
			if (StringUtil.isDecimal(b.getHilo_presetscore())
					&& StringUtil.isDecimal(jsonMatchs.getJSONObject(b.getIssue()).get("l").toString())) {
				if (String.format("%.2f", Double.parseDouble(b.getHilo_presetscore())).equals(String.format("%.2f",
						Double.parseDouble(jsonMatchs.getJSONObject(b.getIssue()).get("l").toString()))) == false) {
					rv.put("state", "000002");
					endMatchs.clear();
					rv.put("endMatch", endMatchs);
					rv.put("msg", "大小分标准发生变化");
					rv.put("p", b.getHilo_presetscore());
					rv.put("issue", b.getIssue());
					return JSONObject.toJSONString(rv);
				}
			}

			if (endMatchs.indexOf(b.getIssue()) >= 0) {
				endMatchs.remove(b.getIssue());
			}

		}

		if (endMatchs.isEmpty() == false) {
			rv.put("state", "000001");
			rv.put("endMatch", endMatchs);
			rv.put("msg", "已经有比赛截止投注了，<br/>请重新确认您的选择");
			return JSONObject.toJSONString(rv);
		}

		// 判断玩法是否已经到达赔率下线----->开始
		for (LOTO_B b : loto_Bs) {
			String issue = b.getIssue();
			JSONObject m = jsonMatchs.getJSONObject(issue);
			if (m.containsKey("hh") || m.containsKey("hl")) {
				if (b.getHilo_h().equals("1.01") || b.getHilo_l().equals("1.01")) {
					rv.put("state", "000001");
					endMatchs.clear();
					endMatchs.add(b.getIssue());
					rv.put("endMatch", endMatchs);
					rv.put("msg", "比赛已停止销售，请重新选择");
					return JSONObject.toJSONString(rv);
				}
			}
			if (m.containsKey("ma") || m.containsKey("mh")) {
				if (b.getMnl_h().equals("1.01") || b.getMnl_a().equals("1.01")) {
					rv.put("state", "000001");
					endMatchs.clear();
					endMatchs.add(b.getIssue());
					rv.put("endMatch", endMatchs);
					rv.put("msg", "比赛已停止销售，请重新选择");
					return JSONObject.toJSONString(rv);
				}
			}
		}
		// 判断玩法是否已经到达赔率下线----->结束

		// 判断玩法是否已经到达限额----->开始
		
        T_LOTO_SIS_B qry_sis_b=new T_LOTO_SIS_B();
        qry_sis_b.setIssues(sbIssues);
        List<T_LOTO_SIS_B> lsT_LOTO_SIS_B= t_LOTO_SIS_BService.selectT_LOTO_SIS_BList(qry_sis_b);
		for (T_LOTO_SIS_B sis_b : lsT_LOTO_SIS_B) {
			T_QUOTATION_CONTROL quotation_control = new T_QUOTATION_CONTROL();
			quotation_control.setId(3);
			quotation_control = t_QUOTATION_CONTROLService.selectT_QUOTATION_CONTROLList(quotation_control).get(0);

			String issue = sis_b.getIssue();
			JSONObject m = jsonMatchs.getJSONObject(issue);
			
			
			if (m.containsKey("hh") || m.containsKey("hl")) {
				if (sis_b.getHilo_h_d() + sis_b.getHilo_l_d()+ getNum(m, "hh")+getNum(m, "hl")> quotation_control.getDcxssx_e()) {
					rv.put("state", "000001");
					endMatchs.clear();
					endMatchs.add(sis_b.getIssue());
					rv.put("endMatch", endMatchs);
					rv.put("msg", "投注超出上限，请重新选择");
					return JSONObject.toJSONString(rv);
				}
			}
			if (m.containsKey("ma") || m.containsKey("mh")) {
				if (sis_b.getMnl_a_d() + sis_b.getMnl_h_d() + getNum(m, "ma")+getNum(m, "mh")> quotation_control.getDcxssx_e()) {
					rv.put("state", "000001");
					endMatchs.clear();
					endMatchs.add(sis_b.getIssue());
					rv.put("endMatch", endMatchs);
					rv.put("msg", "投注超出上限，请重新选择");
					return JSONObject.toJSONString(rv);
				}
			}
		}
		// 判断玩法是否已经到达限额----->结束

		StringBuffer sbFalse = new StringBuffer();
		boolean hasSuccess = false;
		for (LOTO_B b : loto_Bs) {
			String order_id = UUID.randomUUID().toString().replace("-", "");
			String issue = b.getIssue();
			JSONObject m = jsonMatchs.getJSONObject(issue);

			Iterator<String> ls = m.keySet().iterator();
			Date nowDate = new Date();
			V_ORDER vOrder = new V_ORDER();
			vOrder.setBet_fee(0);
			String bet_type = "";
			double odd = 0;
			List<LOTO_ORDER> lstTemp = new ArrayList<LOTO_ORDER>();
			while (ls.hasNext()) {
				String bet_info = "";
				String key = ls.next();

				if (key.equals("l")) {
					continue;
				}
				if (key.equals("hh")) {
					bet_type = "309";
					bet_info = "hilo_h|" + m.get("l").toString();
					if (StringUtil.isEmpty(b.getHilo_h())) {// 如果赔率为空，设置投注赔率为1
						odd = 1;
					} else {
						odd = Double.parseDouble(b.getHilo_h());
					}
				} else if (key.equals("hl")) {
					bet_type = "309";
					bet_info = "hilo_l|" + m.get("l").toString();
					if (StringUtil.isEmpty(b.getHilo_l())) {// 如果赔率为空，设置投注赔率为1
						odd = 1;
					} else {
						odd = Double.parseDouble(b.getHilo_l());
					}
				} else if (key.equals("ma")) {
					bet_type = "307";
					bet_info = "mnl_a|" + b.getMnl_a();
					if (StringUtil.isEmpty(b.getMnl_a())) {// 如果赔率为空，设置投注赔率为1
						odd = 1;
					} else {
						odd = Double.parseDouble(b.getMnl_a());
					}
				} else if (key.equals("mh")) {
					bet_type = "307";
					bet_info = "mnl_h|" + b.getMnl_h();
					if (StringUtil.isEmpty(b.getMnl_h())) {// 如果赔率为空，设置投注赔率为1
						odd = 1;
					} else {
						odd = Double.parseDouble(b.getMnl_h());
					}
				}

				LOTO_ORDER loto_ORDER = new LOTO_ORDER();
				loto_ORDER.setIssue(issue);
				loto_ORDER.setOrder_id(order_id);
				loto_ORDER.setBet_fee(Integer.valueOf(((JSONObject) m.get(key)).get("num").toString()));
				vOrder.setBet_fee(vOrder.getBet_fee() + loto_ORDER.getBet_fee());
				loto_ORDER.setBet_info(bet_info);
				loto_ORDER.setBet_type(Integer.valueOf(bet_type));
				loto_ORDER.setBet_status(1);
				loto_ORDER.setCreate_time(nowDate);
				loto_ORDER.setPrize_status(1);
				loto_ORDER.setPrize_type(1);

				loto_ORDER.setUser_pin(userPin);
				loto_ORDER.setMemo(jsonRequest.get("user_pin").toString());
				loto_ORDER.setVsteam(b.getHome_team_name() + "vs" + b.getGuest_team_name());
				loto_ORDER.setWin_fee(Integer.parseInt(String.valueOf(Math.round(loto_ORDER.getBet_fee() * odd))));
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
				v_orderService.insertV_ORDER(vOrder, lstTemp,null,null,null);
				hasSuccess = true;
			} catch (Exception e) {// 事物中的处理错误必须已异常的方式返回
				sbFalse.append(vOrder.getVsteam() + "<br/>");
			}

			lstTemp = null;

		}
		if (sbFalse.length() > 0) {
			rv.put("state", "000001");
			rv.put("endMatch", endMatchs);
			if (hasSuccess) {
				rv.put("msg", "以下比赛没有支付成功:<br/>" + sbFalse.toString());
			} else {
				rv.put("msg", "支付失败");
			}
			logger.info("调用篮球支付接口失败----->");
			return JSONObject.toJSONString(rv);
		} else {
			rv.put("state", "000000");
			logger.info("调用篮球支付接口成功----->");
			return JSONObject.toJSONString(rv);
		}
	}

	private int getNum(JSONObject m,String key){
		if(m.containsKey(key)){
			JSONObject numObj=(JSONObject)m.get(key);
			if(numObj.containsKey("num")){
				return Integer.parseInt(numObj.get("num").toString());
			}else{
				return 0;
			}
		}else{
			return 0;
		}		
	}
}