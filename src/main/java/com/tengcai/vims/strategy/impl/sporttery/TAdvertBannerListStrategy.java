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
import com.tengcai.vims.entity.sporttery.T_ADVERT_BANNER;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.service.sporttery.T_ADVERT_BANNERService;
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
@Component("t_advert_bannerList")
public class TAdvertBannerListStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(TAdvertBannerListStrategy.class);
	public static class T_ADVERT_BANNER_DATA{
		private String adtitle;
		private String adimgname;
		private String adurl;
		private String adseq;
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
		public String getAdseq() {
			return adseq;
		}
		public void setAdseq(String adseq) {
			this.adseq = adseq;
		}

	}
	public static class AdvertChannelData {
		
		private List<T_ADVERT_BANNER_DATA> lst;

		public List<T_ADVERT_BANNER_DATA> getLst() {
			return lst;
		}

		public void setLst(List<T_ADVERT_BANNER_DATA> lst) {
			this.lst = lst;
		}
	}

	@Autowired
	private T_ADVERT_BANNERService t_advert_bannerService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_advert_turnList开始调用通栏列表接口doJsonMethod------>");
		ReturnValue<AdvertChannelData> rv = new ReturnValue<AdvertChannelData>();
		rv.setData(new AdvertChannelData());
		T_ADVERT_BANNER t_advert_banner = new T_ADVERT_BANNER();
		t_advert_banner.setAdtype(1);
		t_advert_banner.setAdstatus(1);
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
		t_advert_banner.setOrder_source(order_source);
		List<T_ADVERT_BANNER> lst = null;
		List<T_ADVERT_BANNER_DATA> lstData=new ArrayList<T_ADVERT_BANNER_DATA>();
		try {
			lst = this.t_advert_bannerService.selectT_ADVERT_BANNERList(t_advert_banner);
			
			if (lst.isEmpty()) {
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
				rv.getData().setLst(lstData);
			} else {
				for(T_ADVERT_BANNER o:lst){
					T_ADVERT_BANNER_DATA advert_LIST_DATA=new T_ADVERT_BANNER_DATA();
					advert_LIST_DATA.setAdimgname(o.getAdimgname());
					advert_LIST_DATA.setAdtitle(o.getAdtitle());
					advert_LIST_DATA.setAdurl(o.getAdurl());
					advert_LIST_DATA.setAdseq(o.getAdseq().toString());
					lstData.add(advert_LIST_DATA);
				}
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			}
			rv.getData().setLst(lstData);
		} catch (Exception e) {
			lst = new ArrayList<T_ADVERT_BANNER>();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			rv.getData().setLst(lstData);
			logger.error("查询通栏列表数据失败----->" + e.getMessage());
		}

		logger.info("查询通栏列表成功----->");
		return JSONObject.toJSONString(rv);
	}

}