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
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.TAB_INVITATION_BINDService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 绑定邀请码获得GTO数量
 * 
 * @return
 */
@Component("bind_gto_amount")
public class GtoBindStrategy implements IMethodStrategy {
	
	public static class Tab_Invitation_BindDetail {
		public String gto; // GTO数
	}
	
	protected final transient static Logger logger = LoggerFactory.getLogger(GtoBindStrategy.class);
	@Autowired
	private TAB_INVITATION_BINDService tab_invitation_bindService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<Tab_Invitation_BindDetail> rv = new ReturnValue<Tab_Invitation_BindDetail>();
		logger.info("bind_gto_amount开始调用绑定邀请码获得GTO数量接口doJsonMethod------>");
		TAB_INVITATION_BIND tab_invitation_bind = new TAB_INVITATION_BIND();
		tab_invitation_bind.setBind_available(1);
		List<TAB_INVITATION_BIND> tab_invitation_bindList = null;
		try {	
			tab_invitation_bindList = tab_invitation_bindService.selectTAB_INVITATION_BINDList(tab_invitation_bind);
			if(tab_invitation_bindList.size()>0){
				tab_invitation_bind = tab_invitation_bindList.get(0);
				Tab_Invitation_BindDetail tab_Invitation_BindDetail = new Tab_Invitation_BindDetail();
				tab_Invitation_BindDetail.gto=tab_invitation_bind.getBind_fee().toString();
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