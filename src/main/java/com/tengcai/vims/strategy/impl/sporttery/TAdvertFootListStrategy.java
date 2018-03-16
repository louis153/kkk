/**
 * @author 杨阳
 * 2017-08-03
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.T_ADVERT_FOOT;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.service.sporttery.T_ADVERT_FOOTService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 *查看首页页尾双图
 * @return 
 */
@Component("t_advert_footList")
public class TAdvertFootListStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(TAdvertFootListStrategy.class);
	public static class TAdvertFootDetail{
		public String adtitle; //广告名称
		public Integer adtype; //1-M端
		public Integer adseq; //排序
		public String adimgname; //广告图片名称
		public String adurl; //广告链接地址
	}
	public static class TAdvertFootData{
		public List<TAdvertFootDetail> lst=new ArrayList<TAdvertFootDetail>();
	}
	@Autowired
	private T_ADVERT_FOOTService t_advert_footService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_advert_footList开始调用首页页尾双图接口doJsonMethod------>");
		ReturnValue<TAdvertFootData> rv = new ReturnValue<TAdvertFootData>();
		rv.setData(new TAdvertFootData());
		T_ADVERT_FOOT t_advert_foot = new T_ADVERT_FOOT();
		t_advert_foot.setAdtype(1);
		t_advert_foot.setAdstatus(1);
		
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
	    t_advert_foot.setOrder_source(order_source);
		List<T_ADVERT_FOOT> lst=null;
		try {
			t_advert_foot.setPosition(jsonRequest.get("position").toString());
			lst = this.t_advert_footService.selectT_ADVERT_FOOTList(t_advert_foot);
			if(lst.isEmpty()){				
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
			}else{
				for(T_ADVERT_FOOT o:lst){
					TAdvertFootDetail advertFootDetail=new TAdvertFootDetail();
					advertFootDetail.adimgname=o.getAdimgname();
					advertFootDetail.adseq=o.getAdseq();
					advertFootDetail.adtitle=o.getAdtitle();
					advertFootDetail.adurl=o.getAdurl();
					advertFootDetail.adtype=o.getAdtype();
					rv.getData().lst.add(advertFootDetail);
				}
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			}
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询首页页脚双图数据失败----->"+e.getMessage());
		}

       
       
        logger.info("查询首页页脚双图数据成功----->");
		return JSONObject.toJSONString(rv);
	}
	
}