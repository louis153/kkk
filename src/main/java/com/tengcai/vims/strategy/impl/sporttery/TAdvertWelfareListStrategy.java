/**
 * @author 杨阳
 * 2017-08-24 14:49
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.T_ADVERT_WELFARE;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.service.sporttery.T_ADVERT_WELFAREService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 *查看轮播图
 * @return 
 */
@Component("t_advert_welfareList")
public class TAdvertWelfareListStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(TAdvertWelfareListStrategy.class);
	public static class WelfareData{
		private String adurl=""; //福利社链接地址
		private String adimgname=""; //福利社名称
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
	@Autowired
	private T_ADVERT_WELFAREService t_advert_welfareService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_advert_welfareList开始调用查看福利社接口doJsonMethod------>");
		ReturnValue<WelfareData> rv = new ReturnValue<WelfareData>();
		rv.setData(new WelfareData());
		
		T_ADVERT_WELFARE t_advert_welfare = new T_ADVERT_WELFARE();
		t_advert_welfare.setAdtype(1);
		t_advert_welfare.setAdstatus(1);
		String order_source="";
		if(!jsonRequest.containsKey("order_source")){
			order_source="1";
		}
		else{
			order_source=jsonRequest.getString("order_source").trim().isEmpty()?"1":jsonRequest.getString("order_source");
		}
		if(StringUtil.isInteger(order_source)==false){
			order_source="1";
		}
		T_MARKET_CHANNEL t_market_channel=new T_MARKET_CHANNEL();	
		t_market_channel.setChannelstate("1");
		t_market_channel.setChannelnum(Integer.valueOf(order_source));
	    if(t_MARKET_CHANNELService.selectT_MARKET_CHANNELCount(t_market_channel)==0){
	    	order_source="1";
	    };
	    t_advert_welfare.setOrder_source(order_source);
        List<T_ADVERT_WELFARE> lst=null;
        
		try {
			lst = this.t_advert_welfareService.selectT_ADVERT_WELFAREList(t_advert_welfare);
			if(lst.isEmpty()){
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
			}else{
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
				for(T_ADVERT_WELFARE o:lst){
					rv.getData().setAdimgname(o.getAdimgname());
					rv.getData().setAdurl(o.getAdurl());
				}
			}
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询调用查看福利社接口数据失败----->"+e.getMessage());
		}

        
        logger.info("调用查看福利社数据成功----->");
		return JSONObject.toJSONString(rv);
	}
	
}