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
import com.longti.upjc.entity.sporttery.T_INVITATION_AWARD;
import com.longti.upjc.entity.sporttery.T_USER_INVCODE;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_INVITATION_AWARDService;
import com.longti.upjc.service.sporttery.T_USER_INVCODEService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.NumberUtils;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.jdbet.BetUtils;

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
	private T_USER_INVCODEService userINVCODEService;
	@Autowired
	private T_INVITATION_AWARDService t_invitation_awardService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<LotoInvcodeDetail> rv = new ReturnValue<LotoInvcodeDetail>();
		logger.info("get_invcode开始调用获取邀请码接口doJsonMethod------>");
		String user_pin = request_LtGameLogic.getUserPin();
		T_USER_INVCODE t_user_invcode = new T_USER_INVCODE();
		t_user_invcode.setUser_pin(user_pin);
		List<T_USER_INVCODE> invList = null;
		try {	
			invList = userINVCODEService.selectT_USER_INVCODEList(t_user_invcode);
			T_INVITATION_AWARD t_invitation_award = new T_INVITATION_AWARD();
			t_invitation_award.setUser_pin(user_pin);
			List<T_INVITATION_AWARD> awlst = t_invitation_awardService.sumT_INVITATION_AWARD(t_invitation_award);
			if(invList.size()>0){
				t_user_invcode = invList.get(0);      	
            	LotoInvcodeDetail lotoInvcodeDetail = new LotoInvcodeDetail();
            	lotoInvcodeDetail.invitation_code = t_user_invcode.getInvitation_code();
            	String gto = "0";
            	String eth = "0";
            	String uz = "0";
            	for(T_INVITATION_AWARD aw : awlst){
            		if("GTO".equals(aw.getElectronic_code())){
            			gto = String.valueOf(aw.getTotal());
            		}else if("ETH".equals(aw.getElectronic_code())){
            			eth = String.valueOf(aw.getTotal());
            		}else if("UZ".equals(aw.getElectronic_code())){
            			uz= String.valueOf(aw.getTotal());
            		}
            	}
            	lotoInvcodeDetail.gto = gto;
            	lotoInvcodeDetail.eth = eth;
            	lotoInvcodeDetail.uz = uz;
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