/**
 * @author 杨阳
 * 2017-08-03
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.LOTO_F;
import com.longti.upjc.entity.sporttery.LOTO_ORDER;
import com.longti.upjc.entity.sporttery.T_LOTO_E;
import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.LOTO_FService;
import com.longti.upjc.service.sporttery.LOTO_ORDERService;
import com.longti.upjc.service.sporttery.T_LOTO_EService;
import com.longti.upjc.service.sporttery.T_USERService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 查看竞猜详情
 * 
 * @return
 */
@Component("getResult")
public class ResultStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(ResultStrategy.class);

	public static class ResultData {
		public Integer id=0; // 编号
		public String order_id=""; // 投注编号
		public String user_pin=""; // 用户pin
		public String issue=""; // 期号
		public Integer bet_fee=0; // 投注金额
		public Integer win_fee=0; // 奖金,0表示未中奖
		public String create_time=""; // 创建时间
		public Integer type=0; // 投注玩法,301胜平负305让球胜平负307胜负309大小分
		public String odd_name=""; // 投注方案
		public String odd_value="";
		public Integer bet_status=0; // 兑奖状态,0支付中,1已支付(待兑奖)，2已中奖，3未中奖，4已取消
		public Integer prize_status=0; // 派奖状态,1未派奖2已派奖
		public Integer prize_type; // 派奖类型,1自动2手动
		public String prize_cancel_time=""; // 派奖(取消)时间
		public String user_name=""; // 用户账号
		public String endtime=""; // 截止投注时间
		public String home_team_name=""; // 主队名称
		public String guest_team_name=""; // 客队名称
		public String home_full_result=""; // 主队全场比分
		public String guest_full_result=""; // 客队全场比分
		public String home_half_result=""; // 主队全场比分
		public String guest_half_result=""; // 客队全场比分
		public String cg=""; // 赛果
		public String l = "";
		public String match_mode="";
		public String game_name="";
		public String image_name="";

	}
	
	@Autowired
	private LOTO_ORDERService loto_ORDERService;
	
	@Autowired
	private LOTO_FService lotoFService;
	@Autowired
	private T_LOTO_EService lotoEService;

	@Autowired
	private T_USERService t_userService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		logger.info("getResult开始调用我的详情接口doJsonMethod------>" + JSONObject.toJSONString(jsonRequest));
		ReturnValue<ResultData> rv = new ReturnValue<ResultData>();
		rv.setData(new ResultData());
		// String user_pin=jsonRequest.get("user_pin").toString();
		String order_id = jsonRequest.get("id").toString();
		String position = jsonRequest.get("position").toString();
		LOTO_ORDER lotoOrder = new LOTO_ORDER();
		// lotoOrder.setId(Integer.parseInt(DesUtil.decrypt(order_id,desKey,"")));
		lotoOrder.setId(Integer.parseInt(order_id));
		T_USER t_USER=new T_USER();
		t_USER.setUser_pin(request_LtGameLogic.getUserPin());
		List<T_USER> lsT_USERs=t_userService.selectT_USERList(t_USER);
		if(lsT_USERs.isEmpty()==false){
			t_USER=lsT_USERs.get(0);
		}
		lotoOrder.setUser_pin(t_USER.getUser_pin());
		try {
			List<LOTO_ORDER> lstLotoOrder = null;
			if (position.equals("4")) {
				lstLotoOrder = loto_ORDERService.selectLOTO_ORDERList(lotoOrder);
			} else {
				lstLotoOrder = loto_ORDERService.selectLOTO_ORDERList(lotoOrder);
			}
			if (lstLotoOrder.isEmpty() == false) {
				lotoOrder = lstLotoOrder.get(0);
				rv.setMess(ErrorMessage.SUCCESS);
				rv.getData().bet_fee = lotoOrder.getBet_fee();
				rv.getData().bet_status = lotoOrder.getBet_status();
				rv.getData().create_time = (lotoOrder.getCreate_time() == null ? ""
						: DateUtils.getDateToStr(lotoOrder.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
				rv.getData().id = lotoOrder.getId();
				rv.getData().issue = lotoOrder.getIssue();
				rv.getData().order_id = lotoOrder.getOrder_id();
				rv.getData().type = lotoOrder.getBet_type();
				rv.getData().odd_value = String.format("%.2f", ((double)lotoOrder.getWin_fee() / lotoOrder.getBet_fee()));
				rv.getData().prize_cancel_time = (lotoOrder.getPrize_cancel_time() == null ? ""
						: DateUtils.getDateToStr(lotoOrder.getPrize_cancel_time(), "yyyy-MM-dd HH:mm:ss"));
				rv.getData().prize_status = lotoOrder.getPrize_status();
				rv.getData().prize_type = lotoOrder.getPrize_type();
				rv.getData().user_name = lotoOrder.getMemo();
				rv.getData().user_pin=t_USER.getNick_name();
				if (lotoOrder.getBet_status() == 2) {
					rv.getData().win_fee = lotoOrder.getWin_fee();
				} else if (lotoOrder.getBet_status() == 4) {
					rv.getData().win_fee = lotoOrder.getBet_fee();
				} else {
					rv.getData().win_fee = 0;
				}
				if (lotoOrder.getBet_type() == 301 ) {
					LOTO_F f = new LOTO_F();
					f.setIssue(lotoOrder.getIssue());
					List<LOTO_F> lst_f = lotoFService.selectLOTO_FList(f);
					if (lst_f.isEmpty() == false) {
						f = lst_f.get(0);
						rv.getData().game_name=f.getLeaguename();
						rv.getData().endtime = f.getStarttime().substring(0, 4) + "-" + f.getStarttime().substring(4, 6) + "-"
								+ f.getStarttime().substring(6, 8) + " " + f.getStarttime().substring(8, 10) + ":"
								+ f.getStarttime().substring(10, 12) + ":" + f.getStarttime().substring(12, 14);
						
					}

					
					if (lotoOrder.getBet_type() == 301) {
						rv.getData().cg = f.getCg_had();
						if (lotoOrder.getBet_info().startsWith("had_h")) {
							rv.getData().odd_name = "hh";
						} else if (lotoOrder.getBet_info().startsWith("had_d")) {
							rv.getData().odd_name = "hd";
						} else if (lotoOrder.getBet_info().startsWith("had_a")) {
							rv.getData().odd_name = "ha";
						}
						
					} 
					rv.getData().home_full_result = f.getHome_full_result();
					rv.getData().home_half_result = f.getHome_half_result();
					rv.getData().home_team_name = f.getHome_team_name();
					rv.getData().guest_full_result = f.getGuest_full_result();
					rv.getData().guest_half_result = f.getGuest_half_result();
					rv.getData().guest_team_name = f.getGuest_team_name();
				} else {
					T_LOTO_E e = new T_LOTO_E();
					e.setIssue(lotoOrder.getIssue());
					List<T_LOTO_E> lst_e = lotoEService.selectT_LOTO_EList(e);
					if (lst_e.isEmpty() == false) {
						e = lst_e.get(0);
						rv.getData().endtime = e.getStarttime();
					}
					

					if (lotoOrder.getBet_type() == 501) {
						rv.getData().cg = e.getCg();
						if (lotoOrder.getBet_info().startsWith("mnl_h")) {
							rv.getData().odd_name = "mh";
						} else if (lotoOrder.getBet_info().startsWith("mnl_a")) {
							rv.getData().odd_name = "ma";
						}
					}
					rv.getData().home_half_result = "";
					rv.getData().home_team_name = e.getHome_team_name();
					rv.getData().guest_half_result = "";
					rv.getData().guest_team_name = e.getGuest_team_name();
					rv.getData().game_name=e.getLeaguename();
					
				}

				rv.setMess(ErrorMessage.SUCCESS);

			} else {
				rv.setMess(ErrorMessage.FAIL);
			}

		} catch (Exception e) {
			logger.error("调用投注详情接口失败----->" + e.getMessage());
		}
		logger.info("调用投注详情接口成功----->");
		return JSONObject.toJSONString(rv);
	}

}