/**
 * @author 张世才
 * 2018-03-20 17:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.TAB_INVITATION_BIND;
import com.longti.upjc.entity.sporttery.T_USER;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.TAB_INVITATION_BINDService;
import com.longti.upjc.service.sporttery.T_USERService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;

/**
 * 绑定邀请码获得GTO数量
 * 
 * @return
 */
@Component("bind_gto_amount")
public class GtoBindStrategy implements IMethodStrategy {
	
	public static class Tab_Invitation_BindDetail {
		public String gto; // GTO数
		public String use;//  是否使用
	}
	
	protected final transient static Logger logger = LoggerFactory.getLogger(GtoBindStrategy.class);
	@Autowired
	private TAB_INVITATION_BINDService tab_invitation_bindService;
	@Autowired
	private T_USERService t_userService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<Tab_Invitation_BindDetail> rv = new ReturnValue<Tab_Invitation_BindDetail>();
		logger.info("bind_gto_amount开始调用绑定邀请码获得GTO数量接口doJsonMethod------>");
		String user_pin = request_LtGameLogic.getUserPin();
		T_USER t_user = new T_USER();
		t_user.setUser_pin(user_pin);
		TAB_INVITATION_BIND tab_invitation_bind = new TAB_INVITATION_BIND();
		tab_invitation_bind.setBind_available(1);
		List<T_USER> t_useList = null; 
		List<TAB_INVITATION_BIND> tab_invitation_bindList = null;
		try {
			t_useList = t_userService.selectT_USERList(t_user);
			tab_invitation_bindList = tab_invitation_bindService.selectTAB_INVITATION_BINDList(tab_invitation_bind);
			if(tab_invitation_bindList.size()>0){
				String use = "0";
				tab_invitation_bind = tab_invitation_bindList.get(0);
				Tab_Invitation_BindDetail tab_Invitation_BindDetail = new Tab_Invitation_BindDetail();
				tab_Invitation_BindDetail.gto = StringUtil.removeEndZero(tab_invitation_bind.getBind_fee().toString());
				if(t_useList.size()>0){
					t_user = t_useList.get(0);
					if(t_user.getCost_gto().compareTo(tab_invitation_bind.getBind_fee())>=0){
						use = "1";
					}
				}
				tab_Invitation_BindDetail.use = use;
				rv.setData(tab_Invitation_BindDetail);
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
				logger.info("绑定邀请码获得GTO数量成功----->");
			}else{
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("绑定邀请码获得GTO数量失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
}