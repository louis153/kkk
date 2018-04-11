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
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 绑定邀请码
 * 
 * @return
 */
@Component("bind_invcode")
public class Invcode_InputStrategy implements IMethodStrategy {
	
	protected final transient static Logger logger = LoggerFactory.getLogger(Invcode_InputStrategy.class);
	@Autowired
	private T_USER_INVCODEService userINVCODEService;
	@Autowired
	private GtoBindStrategy gtoBindStrategy;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<String> rv = new ReturnValue<String>();
		logger.info("input_invcode开始调用绑定邀请码接口doJsonMethod------>");
		String user_pin = request_LtGameLogic.getUserPin();
		String invitation_code = jsonRequest.get("invitation_code").toString().trim();
		
		T_USER_INVCODE t_user_invcode = new T_USER_INVCODE();
		t_user_invcode.setUser_pin(user_pin);
		List<T_USER_INVCODE> invList = null;
		try {	
			invList = userINVCODEService.selectT_USER_INVCODEList(t_user_invcode);
			if(invList.size()>0){				
				t_user_invcode = invList.get(0);
                if(0==t_user_invcode.getIs_bind()&&"".equals(t_user_invcode.getBind_invitation_code().trim())){
                	if(t_user_invcode.getInvitation_code().trim().equals(invitation_code)){
                		rv.setStatus(ErrorMessage.SELF_INVCODE.getCode());
            			rv.setMessage(ErrorMessage.SELF_INVCODE.getMessage());
                	}else{
                		T_USER_INVCODE t_user_bindinvcode = new T_USER_INVCODE();
                    	t_user_bindinvcode.setInvitation_code(invitation_code);
                    	List<T_USER_INVCODE> bindinvList = userINVCODEService.selectT_USER_INVCODEList(t_user_bindinvcode);
                    	if(bindinvList.size()>0){               		
                    		t_user_bindinvcode = bindinvList.get(0);
                    		logger.info("绑定邀请码接口调用获取GTO奖励个数接口开始----->");
                			JSONObject jsonRV=get_bindgto(request_LtGameLogic);
                			if(jsonRV.getString("status").equals(ErrorMessage.SUCCESS.getCode())==false){
                				rv.setStatus(jsonRV.getString("status"));
                				rv.setMessage(jsonRV.getString("message"));
                				logger.info("绑定邀请码接口调用获取GTO奖励个数接口失败----->"+jsonRV.getString("message"));
                				return JSONObject.toJSONString(rv);
                			}	
                			JSONObject data = jsonRV.getJSONObject("data");
        					String gto = data.getString("gto");
                			logger.info("绑定邀请码接口调用获取GTO奖励个数接口成功----->");	
                    		if(1==t_user_bindinvcode.getIs_bind()){
                    			if(t_user_invcode.getInvitation_code().equals(t_user_bindinvcode.getBind_invitation_code())){
                    				rv.setStatus(ErrorMessage.MUTUALBIND_INVCODE.getCode());
                        			rv.setMessage(ErrorMessage.MUTUALBIND_INVCODE.getMessage());  
                    			}else{
                    				t_user_invcode.setIs_bind(1);
                    				t_user_invcode.setBind_user_pin(t_user_bindinvcode.getUser_pin());
                    				t_user_invcode.setBind_invitation_code(invitation_code);
                    				t_user_invcode.setBind_time(new Date());
                    				userINVCODEService.updateT_USER_INVCODE(t_user_invcode,gto);              				
                    				rv.setStatus(ErrorMessage.SUCCESS.getCode());
                        			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
                        			logger.info("绑定邀请码成功----->");
                    			}
                    		}else{
                    			t_user_invcode.setIs_bind(1);
                    			t_user_invcode.setBind_user_pin(t_user_bindinvcode.getUser_pin());
                    			t_user_invcode.setBind_invitation_code(invitation_code);
                    			t_user_invcode.setBind_time(new Date());
                				userINVCODEService.updateT_USER_INVCODE(t_user_invcode,gto);
                    			rv.setStatus(ErrorMessage.SUCCESS.getCode());
                    			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
                    			logger.info("绑定邀请码成功----->");
                    		}
                    	}else{
            				rv.setStatus(ErrorMessage.NO_INVCODE.getCode());
                			rv.setMessage(ErrorMessage.NO_INVCODE.getMessage());       
                    	}
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
			logger.info("绑定邀请码失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
	
	
	private JSONObject get_bindgto(Request_LtGameLogic request_LtGameLogic) throws Exception{
		Request_LtGameLogic invcode_Request=new Request_LtGameLogic(request_LtGameLogic);
		JSONObject invcodeJSON=new JSONObject();
		invcodeJSON.put("method","bind_gto_amount");	
		invcode_Request.setGameRequest(invcodeJSON.toJSONString());
		JSONObject jsonRV=JSONObject.parseObject(gtoBindStrategy.doJsonMethod(invcode_Request, invcodeJSON));
		return jsonRV;
	}
}