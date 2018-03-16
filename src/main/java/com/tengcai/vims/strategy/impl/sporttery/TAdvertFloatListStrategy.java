/**
 * @author 杨阳
 * 2017-08-24 14:49
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.T_ADVERT_FLOAT;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.service.sporttery.T_ADVERT_FLOATService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 *查看轮播图
 * @return 
 */
@Component("t_advert_floatList")
public class TAdvertFloatListStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(TAdvertFloatListStrategy.class);
	public static class TAdvertFloatDetail{
		public String adtitle; //广告名称
		public Integer adtype; //1-M端
		public Integer adseq; //排序
		public String adimgname; //广告图片名称
		public String adurl; //广告链接地址
	}
	public static class TAdvertFloatData{
		public List<TAdvertFloatDetail> lst=new ArrayList<TAdvertFloatDetail>();
	}
	@Autowired
	private T_ADVERT_FLOATService t_advert_floatService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;
	
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_advert_floatList开始调用查看浮动广告接口doJsonMethod------>");
		ReturnValue<TAdvertFloatData> rv = new ReturnValue<TAdvertFloatData>();
		rv.setData(new TAdvertFloatData());
		T_ADVERT_FLOAT t_advert_float = new T_ADVERT_FLOAT();
		t_advert_float.setAdtype(1);
		t_advert_float.setPosition(jsonRequest.get("position").toString());
		t_advert_float.setAdstatus(1);
		
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
		t_advert_float.setOrder_source(order_source);
        List<T_ADVERT_FLOAT> lst=null;
		try {
			lst = this.t_advert_floatService.selectT_ADVERT_FLOATList(t_advert_float);
			for(T_ADVERT_FLOAT advert_FLOAT:lst){
				TAdvertFloatDetail advertFloatDetail=new TAdvertFloatDetail();
				advertFloatDetail.adimgname=advert_FLOAT.getAdimgname();
				advertFloatDetail.adseq=advert_FLOAT.getAdseq();
				advertFloatDetail.adtitle=advert_FLOAT.getAdtitle();
				advertFloatDetail.adtype=advert_FLOAT.getAdtype();
				advertFloatDetail.adurl=advert_FLOAT.getAdurl();
				rv.getData().lst.add(advertFloatDetail);
			}
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询调用查看浮动广告接口数据失败----->"+e.getMessage());
		}

        
        logger.info("调用查看浮动广告接口数据成功----->");
		return JSONObject.toJSONString(rv);
	}
	
}