/**
 * @author 张世才
 * 2018-03-21 10:49
 */
package com.longti.upjc.strategy.impl.sporttery;

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
 * 生成邀请码
 * 
 * @return
 */
@Component("create_invcode")
public class Invcode_CreateStrategy implements IMethodStrategy {
	
	protected final transient static Logger logger = LoggerFactory.getLogger(Invcode_CreateStrategy.class);
	@Autowired
	private T_USER_INVCODEService userINVCODEService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<String> rv = new ReturnValue<String>();
		logger.info("create_invcode开始调用生成邀请码接口doJsonMethod------>");
		String user_pin = request_LtGameLogic.getUserPin();
		String first_time = jsonRequest.get("first_time").toString().trim();
		String invitation_code = getInvcode();//随机生成六位邀请码
		T_USER_INVCODE t_user_invcode = new T_USER_INVCODE();
		t_user_invcode.setUser_pin(user_pin);
		t_user_invcode.setInvitation_code(invitation_code);
		t_user_invcode.setFirstlogin_time(DateUtils.getStrToDate(first_time,"yyyy-MM-dd HH:mm:ss"));
		t_user_invcode.setIs_bind(0);
		t_user_invcode.setBind_user_pin("");
		t_user_invcode.setBind_invitation_code("");
		t_user_invcode.setBind_time(null);
		try {	
			userINVCODEService.insertT_USER_INVCODE(t_user_invcode);
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			logger.info("生成邀请码成功----->");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("生成邀请码失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
	
	//获取六位邀请码
    public static String getInvcode(){
    	 String[] arr = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
         String invcode = "";
         for(int i=0;i<6;i++){
       	  int sz = (int)(Math.random()*62);
       	  invcode += ""+arr[sz];
         }
         return invcode;
    }
}