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
import com.tengcai.vims.entity.sporttery.T_ADVERT_CHANNEL;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.service.sporttery.T_ADVERT_CHANNELService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 * 查看轮播图
 * 
 * @return
 */
@Component("t_advert_channelList")
public class TAdvertChannelListStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(TAdvertChannelListStrategy.class);
	public static class T_ADVERT_CHANNEL_DATA{
		private String adtitle;
		private String adimgname;
		private String adurl;
		private String position;
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
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}

	}
	public static class AdvertTurnData {
		
		private List<T_ADVERT_CHANNEL_DATA> lst;

		public List<T_ADVERT_CHANNEL_DATA> getLst() {
			return lst;
		}

		public void setLst(List<T_ADVERT_CHANNEL_DATA> lst) {
			this.lst = lst;
		}
	}

	@Autowired
	private T_ADVERT_CHANNELService t_advert_turnService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_advert_channelList开始调用查看频道链接接口doJsonMethod------>");
		ReturnValue<AdvertTurnData> rv = new ReturnValue<AdvertTurnData>();
		rv.setData(new AdvertTurnData());
		T_ADVERT_CHANNEL t_advert_turn = new T_ADVERT_CHANNEL();
		t_advert_turn.setAdtype(1);
		t_advert_turn.setAdstatus(1);
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
		t_advert_turn.setOrder_source(order_source);
		List<T_ADVERT_CHANNEL> lst = null;
		List<T_ADVERT_CHANNEL_DATA> lstData=new ArrayList<T_ADVERT_CHANNEL_DATA>();
		try {
			lst = this.t_advert_turnService.selectT_ADVERT_CHANNELList(t_advert_turn);
			
			if (lst.isEmpty()) {
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
				rv.getData().setLst(lstData);
			} else {
				for(T_ADVERT_CHANNEL o:lst){
					T_ADVERT_CHANNEL_DATA advert_LIST_DATA=new T_ADVERT_CHANNEL_DATA();
					advert_LIST_DATA.setAdimgname(o.getAdimgname());
					advert_LIST_DATA.setAdtitle(o.getAdtitle());
					advert_LIST_DATA.setAdurl(o.getAdurl());
					advert_LIST_DATA.setPosition(o.getPosition());
					lstData.add(advert_LIST_DATA);
				}
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			}
			rv.getData().setLst(lstData);
		} catch (Exception e) {
			lst = new ArrayList<T_ADVERT_CHANNEL>();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			rv.getData().setLst(lstData);
			logger.error("查询频道链接失败----->" + e.getMessage());
		}

		logger.info("查询频道链接成功----->");
		return JSONObject.toJSONString(rv);
	}

}