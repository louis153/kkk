/**
 * @author 张世才
 * 2018-03-20 10:49
 */
package com.longti.upjc.strategy.impl.sporttery;

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
 * 获取绑定邀请码
 * 
 * @return
 */
@Component("get_bindinvcode")
public class BindInvcode_GetStrategy implements IMethodStrategy {
	
	public static class LotoBindInvcodeDetail {
		public String bindinvitation_code; // 已绑定邀请码
		public String gto; // GTO贡献
		public String eth; // ETH贡献
		public String uz; // U钻贡献
	}
	
	protected final transient static Logger logger = LoggerFactory.getLogger(BindInvcode_GetStrategy.class);
	@Autowired
	private T_USER_INVCODEService userINVCODEService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<LotoBindInvcodeDetail> rv = new ReturnValue<LotoBindInvcodeDetail>();
		logger.info("get_bindinvcode开始调用获取绑定邀请码接口doJsonMethod------>");
		String user_pin = request_LtGameLogic.getUserPin();
		T_USER_INVCODE t_user_invcode = new T_USER_INVCODE();
		t_user_invcode.setUser_pin(user_pin);
		List<T_USER_INVCODE> invList = null;
		try {	
			invList = userINVCODEService.selectT_USER_INVCODEList(t_user_invcode);
			if(invList.size()>0){
				t_user_invcode = invList.get(0);     	
            	LotoBindInvcodeDetail lotoBindInvcodeDetail = new LotoBindInvcodeDetail();
            	lotoBindInvcodeDetail.bindinvitation_code = t_user_invcode.getBind_invitation_code();
            	lotoBindInvcodeDetail.gto = "";
            	lotoBindInvcodeDetail.eth = "";
            	lotoBindInvcodeDetail.uz = "";
            	rv.setData(lotoBindInvcodeDetail);
            	rv.setStatus(ErrorMessage.SUCCESS.getCode());
    			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
    			logger.info("获取绑定邀请码成功----->");
			}else{
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("获取绑定邀请码失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
}