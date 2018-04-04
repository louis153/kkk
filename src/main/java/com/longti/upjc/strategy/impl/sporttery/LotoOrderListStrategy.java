/**
 * @author 杨阳
 * 2017-08-03
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.LOTO_ORDER;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.LOTO_ORDERService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.NumberUtils;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;
import com.longti.upjc.util.jdbet.BetUtils;

/**
 *我的投注
 * @return 
 * @throws Exception 
 */
@Component(value="loto_orderList")
public class LotoOrderListStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(LotoOrderListStrategy.class);
	public static class LOTO_ORDERDetail{
	    public String bet_fee;//投注金额
		public String bet_status;//兑奖状态,0支付中,1已支付(待兑奖)，2已中奖，3未中奖，4已取消;
		public String bet_type; //投注玩法,301胜平负305让球胜平负307胜负309大小分
		public String create_time; //创建时间//(DateUtils.getDateToStr(loto_ORDER.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
		public String issue; //期号
	
		//v_ORDER.setOrder_id(DesUtil.encrypt(loto_ORDER.getId().toString(),desKey,""));
		public String id; //订单明细编号
		public String prize_status; //派奖状态,1未派奖2已派奖
		public String user_pin; //用户pin
		public String home_team_name; //主队
		public String guest_team_name;//客队
		public String vsteam;//对阵
		public String win_fee; //奖金,0表示未中奖
	}
	public static class LOTO_ORDERData{
		public int page_index;
		public int page_size;
		public int page_count;
		public int record_count;
		public List<LOTO_ORDERDetail> lst=new ArrayList<LOTO_ORDERDetail>();
	}
	
	
	@Autowired
	private LOTO_ORDERService loto_ORDERService;
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic , JSONObject jsonRequest) throws Exception {
		logger.info("进入loto_orderList查询我的投注信息的方法：doJsonMethod");
		ReturnValue<LOTO_ORDERData> rv = new ReturnValue<LOTO_ORDERData>();
		rv.setData(new LOTO_ORDERData());
		LOTO_ORDER lotoOrder = new LOTO_ORDER();
		
		int page_index = 0;
		
		if (jsonRequest.containsKey("page_index")) {
			page_index = Integer.parseInt(jsonRequest.get("page_index").toString());
		}
		if (jsonRequest.containsKey("position")) {
			lotoOrder.setPosition(jsonRequest.get("position").toString());
		}
		if (StringUtil.isEmpty(request_LtGameLogic.getFeeType()) == false) {
			lotoOrder.setElectronic_code(request_LtGameLogic.getFeeType());
		}
		if (StringUtil.isEmpty(request_LtGameLogic.getUserPin()) == false) {
			lotoOrder.setUser_pin(request_LtGameLogic.getUserPin());
		}
		if(StringUtil.isEmpty(jsonRequest.get("bet_status"))==false){
			lotoOrder.setBet_status(Integer.parseInt(jsonRequest.get("bet_status").toString()));
		}
		int record_count=0;
		try {
			record_count = this.loto_ORDERService.selectLOTO_ORDERCount(lotoOrder);
		} catch (Exception e) {
			logger.error("查询我的投注数据总数userPin："+request_LtGameLogic.getUserPin()+"失败----->");
			rv.setMess(ErrorMessage.FAIL);
			return JSONObject.toJSONString(rv);
		}
		lotoOrder.setPage_size(20);
		int page_count = (record_count % lotoOrder.getPage_size() == 0 ? record_count / lotoOrder.getPage_size() : record_count / lotoOrder.getPage_size() + 1);
        if (page_index >= page_count){
            page_index = page_count - 1;
            rv.getData().page_index=(page_index);
            rv.getData().page_size= lotoOrder.getPage_size();
            rv.getData().page_count=page_count;
            rv.getData().record_count= record_count;
            rv.setMess(ErrorMessage.NO_REC);
            return JSONObject.toJSONString(rv);
        }
        if (page_index < 0)
            page_index = 0;
        lotoOrder.setRow_start(page_index * lotoOrder.getPage_size());
        
        List<LOTO_ORDER> lst=null;
        
		try {
			lst = this.loto_ORDERService.selectLOTO_ORDERList(lotoOrder);
			for(LOTO_ORDER loto_ORDER:lst){
				LOTO_ORDERDetail loto_ORDERDetail=new LOTO_ORDERDetail();
				loto_ORDERDetail.bet_fee=(NumberUtils.longDiv(loto_ORDER.getBet_fee(),BetUtils.preMul).toString());
				loto_ORDERDetail.bet_status=(loto_ORDER.getBet_status().toString());
				loto_ORDERDetail.bet_type=(loto_ORDER.getBet_type().toString());
				loto_ORDERDetail.create_time=(DateUtils.getDateToStr(loto_ORDER.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
				loto_ORDERDetail.issue=(loto_ORDER.getIssue());
			
				//v_ORDER.setOrder_id(DesUtil.encrypt(loto_ORDER.getId().toString(),desKey,""));
				loto_ORDERDetail.id=(loto_ORDER.getId().toString());
				loto_ORDERDetail.prize_status=(loto_ORDER.getPrize_status().toString());
				loto_ORDERDetail.user_pin=(loto_ORDER.getUser_pin());
//				String[] team_names=loto_ORDER.getVsteam().split("vs");
//				loto_ORDERDetail.home_team_name=(team_names[0]);
//				loto_ORDERDetail.guest_team_name=(team_names[1]);
				loto_ORDERDetail.vsteam = loto_ORDER.getVsteam();
				if(loto_ORDER.getBet_status()==2){
					loto_ORDERDetail.win_fee=(NumberUtils.longDiv(loto_ORDER.getWin_fee(),BetUtils.preMul).toString());
				}else if(loto_ORDER.getBet_status()==4){
					loto_ORDERDetail.win_fee=(NumberUtils.longDiv(loto_ORDER.getBet_fee(),BetUtils.preMul).toString());
				}else{
					loto_ORDERDetail.win_fee="0";
				}				
				rv.getData().lst.add(loto_ORDERDetail);
			}
			
		} catch (Exception e) {			
			logger.error("查询我的投注数据userPin："+request_LtGameLogic.getUserPin()+"失败----->");
			rv.setMess(ErrorMessage.FAIL);
	        return JSONObject.toJSONString(rv);
		}

        
        rv.getData().page_index= page_index;
        rv.getData().page_size= lotoOrder.getPage_size();
        rv.getData().page_count= page_count;
        rv.getData().record_count= record_count;
        rv.setMess(ErrorMessage.SUCCESS);
        String outStr=JSONObject.toJSONString(rv);
        logger.info("查询我的投注数据userPin："+request_LtGameLogic.getUserPin()+"成功----->");
		return outStr;
	}
	
}