/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_USERService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 登录
 * 
 * @return
 */
@Component("login")
public class LoginStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(LoginStrategy.class);
	
	@Autowired
	private T_USERService t_USERService;
	@Autowired
	private Invcode_CreateStrategy invcode_CreateStrategy;
	
	public static class Login_Request{
		public String nick_name="";
		public Login_Request(JSONObject jsonRequest){
			nick_name=jsonRequest.getString("nick_name");
		}
	}
	public static class Login_Data{
		
	}
	private JSONObject create_invcode(Request_LtGameLogic request_LtGameLogic,String first_time) throws Exception{
		Request_LtGameLogic invcode_Request=new Request_LtGameLogic(request_LtGameLogic);
		JSONObject invcodeJSON=new JSONObject();
		invcodeJSON.put("method","create_invcode");
		invcodeJSON.put("first_time", first_time);		
		invcode_Request.setGameRequest(invcodeJSON.toJSONString());
		JSONObject jsonRV=JSONObject.parseObject(invcode_CreateStrategy.doJsonMethod(invcode_Request, invcodeJSON));
		return jsonRV;
	}
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		logger.info("login开始调用登录接口doJsonMethod------>" + JSONObject.toJSONString(jsonRequest));
		
		ReturnValue<Login_Data> rv = new ReturnValue<>();
		rv.setData(new Login_Data());
		Login_Request login_Request=new Login_Request(jsonRequest);
		T_USER t_user=new T_USER();
		t_user.setUser_pin(request_LtGameLogic.getUserPin());
		List<T_USER> lsT_USERs=t_USERService.selectT_USERList(t_user);
		if(lsT_USERs.isEmpty()){
			t_user.setFirst_time(new Date());
			t_user.setLand_time(new Date());
			t_user.setUser_pin(request_LtGameLogic.getUserPin());
			t_user.setUser_token(request_LtGameLogic.getUserToken());
			t_user.setNick_name(login_Request.nick_name);
			
			logger.info("登录接口调用生成邀请码接口开始----->");
			JSONObject jsonRV=create_invcode(request_LtGameLogic,DateUtils.getDateToStr(t_user.getFirst_time(), "yyyy-MM-dd HH:mm:ss"));
			if(jsonRV.getString("status").equals(ErrorMessage.SUCCESS.getCode())==false){
				rv.setStatus(jsonRV.getString("status"));
				rv.setMessage(jsonRV.getString("message"));
				logger.info("登录接口调用生成邀请码接口失败----->"+jsonRV.getString("message"));
				return JSONObject.toJSONString(rv);
			}			
			logger.info("登录接口调用生成邀请码接口成功----->");			
			
			t_USERService.insertT_USER(t_user);
		}else{
			t_user=lsT_USERs.get(0);
			t_user.setLand_time(new Date());
			t_user.setUser_pin(request_LtGameLogic.getUserPin());
			t_user.setUser_token(request_LtGameLogic.getUserToken());
			t_user.setNick_name(login_Request.nick_name);			
			t_USERService.updateT_USER(t_user);
		}		
		
		rv.setStatus(ErrorMessage.SUCCESS.getCode());
		rv.setMessage(ErrorMessage.SUCCESS.getMessage());
		logger.info("调用登录接口成功----->");
		return JSONObject.toJSONString(rv);
	}

	

}