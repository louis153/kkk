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
import com.longti.upjc.entity.sporttery.TAB_DICT;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.TAB_DICTService;
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
	
	public static class Tab_DictDetail {
		public String gto; // GTO数
	}
	
	protected final transient static Logger logger = LoggerFactory.getLogger(GtoBindStrategy.class);
	@Autowired
	private TAB_DICTService tab_DICTService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<Tab_DictDetail> rv = new ReturnValue<Tab_DictDetail>();
		logger.info("bind_gto_amount开始调用绑定邀请码获得GTO数量接口doJsonMethod------>");
		TAB_DICT tab_dict = new TAB_DICT();
		tab_dict.setType(1);
		tab_dict.setAvailable(1);
		List<TAB_DICT> tab_dictList = null;
		try {	
			tab_dictList = tab_DICTService.selectTAB_DICTList(tab_dict);
			if(tab_dictList.size()>0){
				tab_dict = tab_dictList.get(0);
				Tab_DictDetail tab_DictDetail = new Tab_DictDetail();
				tab_DictDetail.gto=tab_dict.getValue();
				rv.setData(tab_DictDetail);
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