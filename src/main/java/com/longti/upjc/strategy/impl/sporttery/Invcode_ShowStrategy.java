/**
 * @author 张世才
 * 2018-03-20 10:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.T_USER_INVCODE;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_USER_INVCODEService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 判断是否显示绑定邀请码
 * 
 * @return
 */
@Component("show_invcode")
public class Invcode_ShowStrategy implements IMethodStrategy {
	
	protected final transient static Logger logger = LoggerFactory.getLogger(Invcode_ShowStrategy.class);
	@Autowired
	private T_USER_INVCODEService userINVCODEService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<String> rv = new ReturnValue<String>();
		logger.info("show_invcode开始调用判断是否显示绑定邀请码接口doJsonMethod------>");
		String user_pin = request_LtGameLogic.getUserPin();
		T_USER_INVCODE t_user_invcode = new T_USER_INVCODE();
		t_user_invcode.setUser_pin(user_pin);
		List<T_USER_INVCODE> invList = null;
		try {	
			invList = userINVCODEService.selectT_USER_INVCODEList(t_user_invcode);
			if(invList.size()>0){
				t_user_invcode = invList.get(0);    
				if(0==t_user_invcode.getIs_bind()){
					Date now = new Date();
	            	Date after = DateUtils.getHourAfterDate(t_user_invcode.getFirstlogin_time(),24);
	            	if(now.before(after)){
	            		rv.setStatus(ErrorMessage.SUCCESS.getCode());
	        			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
	        			logger.info("判断是否显示绑定邀请码成功----->");
	            	}else{
	            		rv.setStatus(ErrorMessage.OVER_INVCODE.getCode());
		    			rv.setMessage(ErrorMessage.OVER_INVCODE.getMessage());
	            	}
				}else{
					rv.setStatus(ErrorMessage.BINDED_INVCODE.getCode());
	    			rv.setMessage(ErrorMessage.BINDED_INVCODE.getMessage());
				}
			}else{
				rv.setStatus(ErrorMessage.NO_REC.getCode());
    			rv.setMessage(ErrorMessage.NO_REC.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("判断是否显示绑定邀请码失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
}