/**
 * 
 */
/**
 * @author Dell
 *
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.T_ADVERT_RECORD;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.service.sporttery.T_ADVERT_RECORDService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;
import com.tengcai.vims.util.StringUtil;

/**
 * 我的竞猜页页尾广告
 * 
 * @return
 */
@Component("t_advert_recordList")
public class TAdvertRecordListStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(TAdvertRecordListStrategy.class);

	public static class TAdvertRecordDetail{
		public String adtitle; //广告名称
		public Integer adtype; //1-M端
		public Integer adseq; //排序
		public String adimgname; //广告图片名称
		public String adurl; //广告链接地址
	}
	public static class TAdvertRecordData{
		public List<TAdvertRecordDetail> lst=new ArrayList<TAdvertRecordDetail>();
	}

	@Autowired
	private T_ADVERT_RECORDService t_advert_turnService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_advert_recordList开始调用我的竞猜页广告接口doJsonMethod------>");

		ReturnValue<TAdvertRecordData> rv = new ReturnValue<TAdvertRecordData>();
		rv.setData(new TAdvertRecordData());
		T_ADVERT_RECORD t_advert_record = new T_ADVERT_RECORD();
		t_advert_record.setAdtype(1);
		t_advert_record.setAdstatus(1);
		t_advert_record.setPosition(jsonRequest.get("position").toString());
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
		t_advert_record.setOrder_source(order_source);
		List<T_ADVERT_RECORD> lst = null;
		try {
			lst = this.t_advert_turnService.selectT_ADVERT_RECORDList(t_advert_record);
			if(lst.isEmpty()){
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
			}else{
				for(T_ADVERT_RECORD o:lst){
					TAdvertRecordDetail advertRecordDetail=new TAdvertRecordDetail();
					advertRecordDetail.adimgname=o.getAdimgname();
					advertRecordDetail.adseq=o.getAdseq();
					advertRecordDetail.adtitle=o.getAdtitle();
					advertRecordDetail.adurl=o.getAdurl();
					advertRecordDetail.adtype=o.getAdtype();
					rv.getData().lst.add(advertRecordDetail);
				}
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			}
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询首页调用我的竞猜页广告接口失败----->" + e.getMessage());
		}

		logger.info("查询首页调用我的竞猜页广告接口成功----->");
		return JSONObject.toJSONString(rv);
	}

}