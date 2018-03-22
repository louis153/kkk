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
import com.longti.upjc.entity.sporttery.T_LOTO_INVCODE;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_LOTO_INVCODEService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 获取邀请码
 * 
 * @return
 */
@Component("get_invcode")
public class Invcode_GetStrategy implements IMethodStrategy {
	
	public static class LotoInvcodeDetail {
		public String invitation_code; // 邀请码
		public String gto; // GTO收益
		public String eth; // ETH收益
		public String uz; // U钻收益
	}
	
	protected final transient static Logger logger = LoggerFactory.getLogger(Invcode_GetStrategy.class);
	@Autowired
	private T_LOTO_INVCODEService lotoINVCODEService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<LotoInvcodeDetail> rv = new ReturnValue<LotoInvcodeDetail>();
		logger.info("get_invcode开始调用获取邀请码接口doJsonMethod------>");
		String user_pin = request_LtGameLogic.getUserPin();
		T_LOTO_INVCODE t_loto_invcode = new T_LOTO_INVCODE();
		t_loto_invcode.setUser_pin(user_pin);
		List<T_LOTO_INVCODE> invList = null;
		try {	
			invList = lotoINVCODEService.selectT_LOTO_INVCODEList(t_loto_invcode);
			if(invList.size()>0){
				t_loto_invcode = invList.get(0);      	
            	LotoInvcodeDetail lotoInvcodeDetail = new LotoInvcodeDetail();
            	lotoInvcodeDetail.invitation_code = t_loto_invcode.getInvitation_code();
            	lotoInvcodeDetail.gto = "";
            	lotoInvcodeDetail.eth = "";
            	lotoInvcodeDetail.uz = "";
            	rv.setData(lotoInvcodeDetail);
                rv.setStatus(ErrorMessage.SUCCESS.getCode());
    			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
    			logger.info("获取邀请码成功----->");
			}else{
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("获取邀请码失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
}