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
import com.longti.upjc.entity.sporttery.T_USER_INVCODE;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_USER_INVCODEService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 邀请绑定成功轮播
 * 
 * @return
 */
@Component("invcode_list")
public class InvcodeListStrategy implements IMethodStrategy {

	public static class InvcodeDetail {		
		public String nick_name;//用户昵称
		public String gto;//奖励GTO
	}

	public static class InvcodeListData {
		public List<InvcodeDetail> lst = new ArrayList<InvcodeDetail>();
	}

	protected final transient static Logger logger = LoggerFactory.getLogger(InvcodeListStrategy.class);
	@Autowired
	private T_USER_INVCODEService userINVCODEService;
	@Autowired
	private GtoBindStrategy gtoBindStrategy;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<InvcodeListData> rv = new ReturnValue<InvcodeListData>();
		rv.setData(new InvcodeListData());
		logger.info("invcode_list开始调用邀请绑定成功轮播接口doJsonMethod------>");
		try{
			T_USER_INVCODE t_user_invcode = new T_USER_INVCODE();
			t_user_invcode.setIs_bind(1);
			t_user_invcode.setRow_start(0);
			t_user_invcode.setPage_size(20);
			List<T_USER_INVCODE> invuserList = userINVCODEService.selectT_USER_INVCODEList(t_user_invcode);
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
			for(T_USER_INVCODE invuser : invuserList){
				InvcodeDetail invcodeDetail = new InvcodeDetail();
				invcodeDetail.nick_name = invuser.getNick_name();
				invcodeDetail.gto = gto;
				rv.getData().lst.add(invcodeDetail);
			}
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			logger.info("邀请绑定成功轮播成功----->");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("邀请绑定成功轮播失败----->");
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