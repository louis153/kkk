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
import com.tengcai.vims.entity.sporttery.T_ADVERT_REDPACK;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.service.sporttery.T_ADVERT_REDPACKService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 *查看轮播图
 * @return 
 */
@Component("t_advert_redpackList")
public class TAdvertRedpackListStrategy implements IMethodStrategy{
	public  static class Redpack_Data{
		private List<T_ADVERT_REDPACK> lst;

		public List<T_ADVERT_REDPACK> getLst() {
			return lst;
		}

		public void setLst(List<T_ADVERT_REDPACK> lst) {
			this.lst = lst;
		}
		
	}
	public static class Redpack_Ask{
		private String order_source;

		public String getOrder_source() {
			return order_source;
		}

		public void setOrder_source(String order_source) {
			this.order_source = order_source;
		}
	}
	protected final transient static Logger logger = LoggerFactory.getLogger(TAdvertRedpackListStrategy.class);
	
	@Autowired
	private T_ADVERT_REDPACKService t_advert_redpackService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_advert_redpackList开始调用查看红包雨接口doJsonMethod------>");
		Redpack_Ask redpack_Ask=new Redpack_Ask();
		Redpack_Data redpack_Data=new Redpack_Data();
		ReturnValue<Redpack_Data> rv = new ReturnValue<Redpack_Data>();
		T_ADVERT_REDPACK t_advert_redpack = new T_ADVERT_REDPACK();
		t_advert_redpack.setAdtype(1);
		t_advert_redpack.setAdstatus(1);
		
		if(!jsonRequest.containsKey("order_source")){
			redpack_Ask.setOrder_source("1");
		}
		else{
			redpack_Ask.setOrder_source(jsonRequest.getString("order_source").trim().isEmpty()?"1":jsonRequest.getString("order_source"));
		}
		if(StringUtil.isInteger(redpack_Ask.getOrder_source())==false){
			redpack_Ask.setOrder_source("1");
		}
		T_MARKET_CHANNEL t_market_channel=new T_MARKET_CHANNEL();	
		t_market_channel.setChannelstate("1");
		t_market_channel.setChannelnum(Integer.valueOf(redpack_Ask.getOrder_source()));
	    if(t_MARKET_CHANNELService.selectT_MARKET_CHANNELCount(t_market_channel)==0){
	    	redpack_Ask.setOrder_source("1");
	    };
	    t_advert_redpack.setOrder_source(redpack_Ask.getOrder_source());
        List<T_ADVERT_REDPACK> lst=null;
		try {
			lst = this.t_advert_redpackService.selectT_ADVERT_REDPACKList(t_advert_redpack);
			redpack_Data.setLst(lst);
			rv.setData(redpack_Data);
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
		} catch (Exception e) {			
			redpack_Data.setLst(new ArrayList<T_ADVERT_REDPACK>());
			rv.setData(redpack_Data);			
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询调用查看红包雨接口数据失败----->"+e.getMessage());
		}
        
        logger.info("调用查看红包雨数据成功----->");
		return JSONObject.toJSONString(rv);
	}
	
}