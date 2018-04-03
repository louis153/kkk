/**
 * @author 张世才
 * 2018-03-26 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
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
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;

/**
 * 查看邀请好友列表
 * 
 * @return
 */
@Component("invcode_details")
public class InvcodeDetailsStrategy implements IMethodStrategy {

	public static class InvcodeDetail {		
		public String nick_name;//用户昵称
		public String gto;//贡献GTO
		public String eth;//贡献ETH
		public String uz;//贡献U钻
	}

	public static class InvcodeListData {
		public String invcode_count;
		public List<InvcodeDetail> lst = new ArrayList<InvcodeDetail>();
	}

	protected final transient static Logger logger = LoggerFactory.getLogger(InvcodeDetailsStrategy.class);
	@Autowired
	private T_INVITATION_AWARDService t_invitation_awardService;
	@Autowired
	private T_USER_INVCODEService userINVCODEService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<InvcodeListData> rv = new ReturnValue<InvcodeListData>();
		rv.setData(new InvcodeListData());
		logger.info("invcode_details开始调用查看邀请好友列表接口doJsonMethod------>");
		String userPin = "";
		if (StringUtil.isEmpty(request_LtGameLogic.getUserPin()) == false) {
			userPin = request_LtGameLogic.getUserPin();
		}
		T_INVITATION_AWARD t_invitation_award = new T_INVITATION_AWARD();
		t_invitation_award.setUser_pin(userPin);
		t_invitation_award.setType("1");//奖励
        List<T_INVITATION_AWARD> invlst = null;
		try{
			T_USER_INVCODE t_user_invcode = new T_USER_INVCODE();
			t_user_invcode.setBind_user_pin(userPin);
			List<T_USER_INVCODE> invuserList = userINVCODEService.selectT_USER_INVCODEList(t_user_invcode);
			invlst = t_invitation_awardService.selectT_INVITATION_AWARD(t_invitation_award);
			for(T_USER_INVCODE invuser : invuserList){
				InvcodeDetail invcodeDetail = new InvcodeDetail();
				invcodeDetail.nick_name = invuser.getNick_name();
				String gto = "";
				String eth = "";
				String uz = "";
				for(T_INVITATION_AWARD inv : invlst){
					if(invuser.getUser_pin().equals(inv.getContributor())){
						if("GTO".equals(inv.getElectronic_code())){
							gto = String.valueOf(inv.getTotal());
						}else if("ETH".equals(inv.getElectronic_code())){
							eth = String.valueOf(inv.getTotal());
						}else if("UZ".equals(inv.getElectronic_code())){
							uz = String.valueOf(inv.getTotal());
						}
					}
				}
				invcodeDetail.gto = gto;
				invcodeDetail.eth = eth;
				invcodeDetail.uz = uz;
				rv.getData().lst.add(invcodeDetail);
			}
	        rv.getData().invcode_count= String.valueOf(invuserList.size());
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			logger.info("查看邀请好友列表成功----->");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("查看邀请好友列表失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
	
	
}