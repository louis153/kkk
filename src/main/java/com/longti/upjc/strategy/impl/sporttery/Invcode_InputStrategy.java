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
import com.longti.upjc.entity.sporttery.T_LOTO_INVCODE;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_LOTO_INVCODEService;
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
	private T_LOTO_INVCODEService lotoINVCODEService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<String> rv = new ReturnValue<String>();
		logger.info("input_invcode开始调用绑定邀请码接口doJsonMethod------>");
		String user_pin = request_LtGameLogic.getUserPin();
		String invitation_code = jsonRequest.get("invitation_code").toString().trim();
		
		T_LOTO_INVCODE t_loto_invcode = new T_LOTO_INVCODE();
		t_loto_invcode.setUser_pin(user_pin);
		List<T_LOTO_INVCODE> invList = null;
		try {	
			invList = lotoINVCODEService.selectT_LOTO_INVCODEList(t_loto_invcode);
			if(invList.size()>0){				
				t_loto_invcode = invList.get(0);
                if(0==t_loto_invcode.getIs_bind()&&"".equals(t_loto_invcode.getBind_invitation_code())){ 
                	T_LOTO_INVCODE t_loto_bindinvcode = new T_LOTO_INVCODE();
                	t_loto_bindinvcode.setInvitation_code(invitation_code);
                	List<T_LOTO_INVCODE> bindinvList = lotoINVCODEService.selectT_LOTO_INVCODEList(t_loto_bindinvcode);
                	if(bindinvList.size()>0){               		
                		t_loto_bindinvcode = bindinvList.get(0);
                		if(1==t_loto_bindinvcode.getIs_bind()){
                			if(t_loto_invcode.getInvitation_code().equals(t_loto_bindinvcode.getBind_invitation_code())){
                				rv.setStatus(ErrorMessage.MUTUALBIND_INVCODE.getCode());
                    			rv.setMessage(ErrorMessage.MUTUALBIND_INVCODE.getMessage());  
                			}else{
                				t_loto_invcode.setIs_bind(1);
                				t_loto_invcode.setBind_user_pin(t_loto_bindinvcode.getUser_pin());
                				t_loto_invcode.setBind_invitation_code(invitation_code);
                				t_loto_invcode.setBind_time(new Date());
                				lotoINVCODEService.updateT_LOTO_INVCODE(t_loto_invcode);
                				rv.setStatus(ErrorMessage.SUCCESS.getCode());
                    			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
                    			logger.info("绑定邀请码成功----->");
                			}
                		}else{
                			t_loto_invcode.setIs_bind(1);
            				t_loto_invcode.setBind_user_pin(t_loto_bindinvcode.getUser_pin());
            				t_loto_invcode.setBind_invitation_code(invitation_code);
            				t_loto_invcode.setBind_time(new Date());
            				lotoINVCODEService.updateT_LOTO_INVCODE(t_loto_invcode);
                			rv.setStatus(ErrorMessage.SUCCESS.getCode());
                			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
                			logger.info("绑定邀请码成功----->");
                		}
                	}else{
        				rv.setStatus(ErrorMessage.NO_INVCODE.getCode());
            			rv.setMessage(ErrorMessage.NO_INVCODE.getMessage());       
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
}