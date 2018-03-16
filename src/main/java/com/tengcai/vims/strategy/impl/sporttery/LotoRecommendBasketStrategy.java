package com.tengcai.vims.strategy.impl.sporttery;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.LOTO_B;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_B;
import com.tengcai.vims.service.sporttery.LOTO_BNService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_BService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 * 查询蓝球推荐信息
 * 
 * @return
 * @throws Exception
 */
@Component("loto_recommend_basket")
public class LotoRecommendBasketStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(LotoRecommendBasketStrategy.class);

	

	public static class RemBasketData {
		public String issue=""; // 比赛编号
		public String home_team_name=""; // 主队名称
		public String guest_team_name=""; // 客队名称
		public String home_team_id=""; // 主队编号
		public String guest_team_id=""; // 客队编号
		public String hilo_presetscore=""; // 大小分标准
		public String leaguename=""; // 比赛名称
		public String hh="";
		public String hl="";
		public String mh="";
		public String ma="";
		public int h_bet=1;
		public int m_bet=1;
		public String hh_p="";
		public String hl_p="";
		public String mh_p="";
		public String ma_p="";
		public String endtime="";
	}

	@Autowired
	private LOTO_BNService lotoBNService;
	@Autowired
	private T_LOTO_SIS_BService t_LOTO_SIS_BService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("进入loto_recommend_basket查询篮球推荐的方法：doJsonMethod");
		ReturnValue<RemBasketData> rv = new ReturnValue<RemBasketData>();
		rv.setData(new RemBasketData());
		LOTO_B lotoB = new LOTO_B();
		lotoB.setIs_hot(1);
		lotoB.setHilo_bet(1);
		lotoB.setMnl_bet(1);
		List<LOTO_B> lst = null;
		T_LOTO_SIS_B t_LOTO_SIS_B = new T_LOTO_SIS_B();

		List<T_LOTO_SIS_B> lstPrecent = null;

		try {
			lotoB.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
			lst = lotoBNService.selectLOTO_BNList(lotoB);
			if (lst.isEmpty()) {
				lotoB.setIs_hot(null);
				lotoB.setIs_recommend(1);
				lst = lotoBNService.selectLOTO_BNList(lotoB);
			}
			if (lst.isEmpty() == false) {
				t_LOTO_SIS_B.setIssue(lst.get(0).getIssue());
				lstPrecent = t_LOTO_SIS_BService.selectT_LOTO_SIS_BList(t_LOTO_SIS_B);

				rv.getData().guest_team_id=(lst.get(0).getGuest_team_id());
				rv.getData().guest_team_name=(lst.get(0).getGuest_team_name());
				rv.getData().hilo_presetscore=(lst.get(0).getHilo_presetscore());
				rv.getData().home_team_id=(lst.get(0).getHome_team_id());
				rv.getData().home_team_name=(lst.get(0).getHome_team_name());
				rv.getData().leaguename=(lst.get(0).getLeaguename());
				rv.getData().issue=(lst.get(0).getIssue());
				rv.getData().endtime=(lst.get(0).getEndtime());
				if(lst.get(0).getHilo_bet()==0){
					lst.get(0).setHilo_h("");
					lst.get(0).setHilo_l("");
				}
				if(lst.get(0).getMnl_bet()==0){
					lst.get(0).setMnl_h("");
					lst.get(0).setMnl_a("");
				}
				double total1 = 0, total2 = 0;
				if (lstPrecent.isEmpty() || lstPrecent.get(0) == null) {
					lstPrecent.add(new T_LOTO_SIS_B());
				}
				if (lstPrecent.get(0).getMnl_h() == null) {
					lstPrecent.get(0).setMnl_a(0);
					lstPrecent.get(0).setMnl_h(0);
				} else {
					total1 = lstPrecent.get(0).getMnl_h() + lstPrecent.get(0).getMnl_a();

				}
				if (lstPrecent.get(0).getHilo_h() == null) {
					lstPrecent.get(0).setHilo_h(0);
					lstPrecent.get(0).setHilo_l(0);
				} else {
					total2 = lstPrecent.get(0).getHilo_h() + lstPrecent.get(0).getHilo_l();
				}

				if (StringUtil.isEmpty(lst.get(0).getHilo_h()) == false) {
					rv.getData().hh=lst.get(0).getHilo_h().toString();
					rv.getData().hl=lst.get(0).getHilo_l().toString();
					rv.getData().hh_p=String.format("%.0f%%", total2 == 0 ? 0.00 : lstPrecent.get(0).getHilo_h() / total2 * 100);
					rv.getData().hl_p=String.format("%.0f%%", total2 == 0 ? 0.00 : lstPrecent.get(0).getHilo_l() / total2 * 100);
					rv.getData().h_bet=1;
				}else{
					rv.getData().h_bet=0;
				}
				if (StringUtil.isEmpty(lst.get(0).getMnl_h()) == false) {
					rv.getData().mh=lst.get(0).getMnl_h().toString();
					rv.getData().ma=lst.get(0).getMnl_a().toString();
					rv.getData().mh_p=String.format("%.0f%%", total1 == 0 ? 0.00 : lstPrecent.get(0).getMnl_h() / total1 * 100);
					rv.getData().ma_p=String.format("%.0f%%", total1 == 0 ? 0.00 : lstPrecent.get(0).getMnl_a() / total1 * 100);
					rv.getData().m_bet=1;
				}else{
					rv.getData().m_bet=0;
				}
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
				logger.info("查询蓝球推荐列表成功----->");
			}else{
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
				logger.info("查询蓝球推荐列表为空----->");
			}
			
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询蓝球推荐列表失败----->");
		}

		// rv.put("data", lst);
		// rv.put("precent", lstRv);
		
		return JSONObject.toJSONString(rv);
	}
	/*
	 * private void checkCanBet(List<T_LOTO_SIS_B> lstPrecent,List<LOTO_B> lst){
	 * double
	 * hilo_sum=lstPrecent.get(0).getHilo_h_d()+lstPrecent.get(0).getHilo_l_d();
	 * double
	 * mnl_sum=lstPrecent.get(0).getMnl_h_d()+lstPrecent.get(0).getHilo_l_d();
	 * if(lst.get(0).getHilo_h().equals("1.01")||lst.get(0).getHilo_l().equals(
	 * "1.01")||hilo_sum>=quotation_control.getDcxssx_s()){
	 * lst.remove(lst.get(0)); }
	 * if(lst.get(0).getMnl_a().equals("1.01")||lst.get(0).getMnl_h().equals(
	 * "1.01") ||mnl_sum>quotation_control.getDcxssx_s()){
	 * lst.remove(lst.get(0)); }
	 * 
	 * }
	 */
}
