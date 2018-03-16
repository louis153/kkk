/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.T_ADVERT_TURN;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.service.sporttery.T_ADVERT_TURNService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 * 查看频道列表
 * 
 * @return
 */
@Component("t_advert_turnList")
public class TAdvertTurnListStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(TAdvertTurnListStrategy.class);
	public static class T_ADVERT_TURN_DATA{
		private String adtitle;
		private String adimgname;
		private String adurl;
		public String getAdtitle() {
			return adtitle;
		}
		public void setAdtitle(String adtitle) {
			this.adtitle = adtitle;
		}
		public String getAdimgname() {
			return adimgname;
		}
		public void setAdimgname(String adimgname) {
			this.adimgname = adimgname;
		}
		public String getAdurl() {
			return adurl;
		}
		public void setAdurl(String adurl) {
			this.adurl = adurl;
		}

	}
	public static class AdvertChannelData {
		
		private List<T_ADVERT_TURN_DATA> lst;

		public List<T_ADVERT_TURN_DATA> getLst() {
			return lst;
		}

		public void setLst(List<T_ADVERT_TURN_DATA> lst) {
			this.lst = lst;
		}
	}

	@Autowired
	private T_ADVERT_TURNService t_advert_turnService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_advert_turnList开始调用轮播列表接口doJsonMethod------>");
		ReturnValue<AdvertChannelData> rv = new ReturnValue<AdvertChannelData>();
		rv.setData(new AdvertChannelData());
		T_ADVERT_TURN t_advert_turn = new T_ADVERT_TURN();
		t_advert_turn.setAdtype(1);
		t_advert_turn.setAdstatus(1);
		String order_source = "";
		if (!jsonRequest.containsKey("order_source")) {
			order_source = "1";
		} else {
			order_source = jsonRequest.getString("order_source").trim().isEmpty() ? "1"
					: jsonRequest.getString("order_source");
		}
		if (jsonRequest.containsKey("position")){
			t_advert_turn.setPosition(jsonRequest.get("position").toString());
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
		t_advert_turn.setOrder_source(order_source);
		List<T_ADVERT_TURN> lst = null;
		List<T_ADVERT_TURN_DATA> lstData=new ArrayList<T_ADVERT_TURN_DATA>();
		try {
			lst = this.t_advert_turnService.selectT_ADVERT_TURNList(t_advert_turn);
			
			if (lst.isEmpty()) {
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
				rv.getData().setLst(lstData);
			} else {
				for(T_ADVERT_TURN o:lst){
					T_ADVERT_TURN_DATA advert_LIST_DATA=new T_ADVERT_TURN_DATA();
					advert_LIST_DATA.setAdimgname(o.getAdimgname());
					advert_LIST_DATA.setAdtitle(o.getAdtitle());
					advert_LIST_DATA.setAdurl(o.getAdurl());
					lstData.add(advert_LIST_DATA);
				}
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			}
			rv.getData().setLst(lstData);
		} catch (Exception e) {
			lst = new ArrayList<T_ADVERT_TURN>();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			rv.getData().setLst(lstData);
			logger.error("查询轮播列表数据失败----->" + e.getMessage());
		}

		logger.info("查询轮播列表成功----->");
		return JSONObject.toJSONString(rv);
	}

}