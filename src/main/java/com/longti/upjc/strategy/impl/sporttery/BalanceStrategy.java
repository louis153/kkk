/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.formdata.sporttery.RV_Balance;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_USERService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;
import com.longti.upjc.util.jdbet.BetUtils;

/**
 * 获取余额
 * 
 * @return
 */
@Component("get_balance")
public class BalanceStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(BalanceStrategy.class);
	@Autowired
	private T_USERService tuserService;
	public static class Balance_Request{
		public String user_pin="";
		public String electronic_code="";
		public Balance_Request(JSONObject jsonRequest){
			user_pin=jsonRequest.getString("user_pin");
			electronic_code=jsonRequest.getString("electronic_code");
		}
	}
	public static class Balance_Data{
		public String balance="";
	}
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		logger.info("pay开始调用查看余额doJsonMethod------>" + JSONObject.toJSONString(jsonRequest));
		
		ReturnValue<Balance_Data> rv=new ReturnValue<Balance_Data>();
		Balance_Request balance_Request=new Balance_Request(jsonRequest);
		balance_Request.electronic_code=request_LtGameLogic.getFeeType();
		balance_Request.user_pin=request_LtGameLogic.getUserPin();
		RV_Balance rv_Balance= BetUtils.Balance(balance_Request.user_pin, balance_Request.electronic_code);
		
		BigDecimal balance=null;
		T_USER t_user=new T_USER();
		t_user.setUser_pin(balance_Request.user_pin);		
		List<T_USER> lT_USERs= tuserService.selectT_USERList(t_user);
		if(lT_USERs.size()>0){
			t_user=lT_USERs.get(0);
		}
		if(request_LtGameLogic.getFeeType().equalsIgnoreCase("GTO")){			
			if(StringUtil.ifnull(t_user.getAward_gto(),0).equals(0)){
				t_user.setAward_gto(new BigDecimal(0L));
			}			
			balance=new BigDecimal(rv_Balance.balance).add(t_user.getAward_gto());
		}
		else if(request_LtGameLogic.getFeeType().equalsIgnoreCase("ETH")){			
			if(StringUtil.ifnull(t_user.getAward_eth(),0).equals(0)){
				t_user.setAward_gto(new BigDecimal(0L));
			}			
			balance=new BigDecimal(rv_Balance.balance).add(t_user.getAward_eth());
		}
		else if(request_LtGameLogic.getFeeType().equalsIgnoreCase("UZ")){			
			if(StringUtil.ifnull(t_user.getAward_eth(),0).equals(0)){
				t_user.setAward_gto(new BigDecimal(0L));
			}			
			balance=new BigDecimal(rv_Balance.balance).add(t_user.getAward_uz());
		}
		else{
			balance=new BigDecimal(rv_Balance.balance);
		}
		rv.setData(new Balance_Data());
		rv.getData().balance=balance.toString();
	
		rv.setMess(ErrorMessage.SUCCESS);
		logger.info("调用查看余额接口成功----->");
		return JSONObject.toJSONString(rv);
	}

	

}