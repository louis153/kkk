/**
 * @author 张世才
 * 2018-03-23
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
 *查看竞猜详情
 * @return 
 * @throws Exception 
 */
@Component(value="getResult")
public class Result_GetStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(Result_GetStrategy.class);
	public static class LOTO_ORDERDetail{
		public String issue; //期号
		public String id; //订单明细编号
		public String order_id;//订单编号
		public String home_team_name; //主队
		public String guest_team_name;//客队
		public String create_time; //创建时间
		public String bet_status;//兑奖状态,0支付中,1已支付(待兑奖)，2已中奖，3未中奖，4已取消;
		public String prize_status; //派奖状态,1未派奖2已派奖
		public String leaguename;//比赛名称
		public String prize_cancel_time; //派奖(取消)时间
		public String user_name; //用户账号
		public String endtime;	//截止投注时间
		public String home_full_result;	//主队全场比分
		public String guest_full_result;	//客队全场比分
		public String home_half_result;	//主队半场比分
		public String guest_half_result;	//客队半场比分
        public String type;  //玩法类型
		public String odd_name; //投注
        public String odd_value; //赔率值
        public String bet_fee; //投注金额
        public String win_fee; //奖金,0表示未中奖
        public String cg; //赛果（话题结果）
        public String huat_context; //话题内容
        public String huat_result; //	竞猜结果
        public String result_context; //	结果内容
        public String electronic_code; //币种简称

        
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
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic , JSONObject jsonRequest) throws Exception {
		logger.info("getResult开始调用我的详情接口doJsonMethod------>" + JSONObject.toJSONString(jsonRequest));
		ReturnValue<LOTO_ORDERDetail> rv = new ReturnValue<LOTO_ORDERDetail>();
		rv.setData(new LOTO_ORDERDetail());
		String electronic_code = request_LtGameLogic.getFeeType();
		String order_id = jsonRequest.get("id").toString();
		LOTO_ORDER lotoOrder = new LOTO_ORDER();
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
			lstLotoOrder = loto_ORDERService.selectLOTO_ORDERList(lotoOrder);
			if (lstLotoOrder.isEmpty() == false) {
				lotoOrder = lstLotoOrder.get(0);
				rv.setMess(ErrorMessage.SUCCESS);
				rv.getData().bet_fee = lotoOrder.getBet_fee().toString();
				rv.getData().bet_status = lotoOrder.getBet_status().toString();
				rv.getData().create_time = (lotoOrder.getCreate_time() == null ? ""
						: DateUtils.getDateToStr(lotoOrder.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
				rv.getData().id = lotoOrder.getId().toString();
				rv.getData().issue = lotoOrder.getIssue();
				rv.getData().order_id = lotoOrder.getOrder_id();
				rv.getData().type = lotoOrder.getBet_type().toString();
				rv.getData().odd_value = String.format("%.2f", ((double)lotoOrder.getWin_fee() / lotoOrder.getBet_fee()));
				rv.getData().prize_cancel_time = (lotoOrder.getPrize_cancel_time() == null ? ""
						: DateUtils.getDateToStr(lotoOrder.getPrize_cancel_time(), "yyyy-MM-dd HH:mm:ss"));
				rv.getData().prize_status = lotoOrder.getPrize_status().toString();
				rv.getData().user_name=t_USER.getNick_name();
				if (lotoOrder.getBet_status() == 2) {
					rv.getData().win_fee = lotoOrder.getWin_fee().toString();
				} else if (lotoOrder.getBet_status() == 4) {
					rv.getData().win_fee = lotoOrder.getBet_fee().toString();
				} else {
					rv.getData().win_fee = "0";
				}
				rv.getData().result_context=lotoOrder.getVsresult();
				rv.getData().electronic_code=(lotoOrder.getElectronic_code());;
				if (lotoOrder.getBet_type() == 301 ) {
					LOTO_F f = new LOTO_F();
					f.setIssue(lotoOrder.getIssue());
					f.setElectronic_code(electronic_code);
					List<LOTO_F> lst_f = lotoFService.selectLOTO_FList(f);
					if (lst_f.isEmpty() == false) {
						f = lst_f.get(0);
						rv.getData().leaguename=f.getLeaguename();
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
					e.setElectronic_code(electronic_code);
					e.setIssue(lotoOrder.getIssue());
					List<T_LOTO_E> lst_e = lotoEService.selectT_LOTO_EList(e);
					if (lst_e.isEmpty() == false) {
						e = lst_e.get(0);
						rv.getData().endtime = e.getStarttime();
					}
					

					if (lotoOrder.getBet_type() == 501) {
						rv.getData().cg = e.getCg();
						if (lotoOrder.getBet_info().startsWith("huat_a")) {
							rv.getData().odd_name = lotoOrder.getOptions_one();
						} else if (lotoOrder.getBet_info().startsWith("huat_d")) {
							rv.getData().odd_name = lotoOrder.getOptions_two();
						} else if (lotoOrder.getBet_info().startsWith("huat_h")) {
							rv.getData().odd_name = lotoOrder.getOptions_three();
						}
					}
					rv.getData().home_full_result = "";
					rv.getData().home_half_result = "";
					rv.getData().home_team_name = e.getHome_team_name();
					rv.getData().guest_full_result = "";
					rv.getData().guest_half_result = "";
					rv.getData().guest_team_name = e.getGuest_team_name();
					rv.getData().leaguename=e.getLeaguename();
					
					rv.getData().huat_context=lotoOrder.getPlay_method();
					rv.getData().huat_result="";					
					if(2==lotoOrder.getBet_status()||3==lotoOrder.getBet_status()){
						rv.getData().huat_result="1";
					}else if(5==lotoOrder.getBet_status()){//话题不中返还
						rv.getData().huat_result="0";
					}
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