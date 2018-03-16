/**
 * 
 */
/**
 * @author Dell
 *
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.T_ADVERT_POP;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.service.sporttery.T_ADVERT_POPService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 * 查看弹出广告
 * 
 * @return
 */
@Component("t_advert_popList")
public class TAdvertPopListStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(TAdvertPopListStrategy.class);

	public static class AdvertPopData {
		private List<T_ADVERT_POP> lst;

		public List<T_ADVERT_POP> getLst() {
			return lst;
		}

		public void setLst(List<T_ADVERT_POP> lst) {
			this.lst = lst;
		}
	}

	@Autowired
	private T_ADVERT_POPService t_advert_popService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_advert_popList开始调用首页弹出广告接口doJsonMethod------>");
		ReturnValue<AdvertPopData> rv = new ReturnValue<AdvertPopData>();
		AdvertPopData advertPopData = new AdvertPopData();
		rv.setData(advertPopData);

		T_ADVERT_POP t_advert_pop = new T_ADVERT_POP();
		t_advert_pop.setAdtype(1);
		t_advert_pop.setAdstatus(1);
		String order_source = "";
		if (!jsonRequest.containsKey("order_source")) {
			order_source = "1";
		} else {
			order_source = jsonRequest.getString("order_source").trim().isEmpty() ? "1"
					: jsonRequest.getString("order_source");
		}
		if (StringUtil.isInteger(order_source) == false) {
			order_source = "1";
		}
		T_MARKET_CHANNEL t_market_channel = new T_MARKET_CHANNEL();
		t_market_channel.setChannelstate("1");
		t_market_channel.setChannelnum(Integer.valueOf(order_source));
		if (t_MARKET_CHANNELService.selectT_MARKET_CHANNELCount(t_market_channel) == 0) {
			order_source = "1";
		}
		;
		t_advert_pop.setOrder_source(order_source);
		List<T_ADVERT_POP> lst = null;
		try {
			t_advert_pop.setPosition(jsonRequest.get("position").toString());
			lst = this.t_advert_popService.selectT_ADVERT_POPList(t_advert_pop);
			if (lst.isEmpty()) {
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
			} else {
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			}
			rv.getData().setLst(lst);
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			rv.getData().setLst(lst);
			logger.error("查询首页弹出广告数据失败----->" + e.getMessage());
		}

		logger.info("查询首页弹出广告数据成功----->");
		return JSONObject.toJSONString(rv);
	}

}