/**
 * @author 张世才
 * 2018-03-26 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.T_INVITATION_AWARD;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_INVITATION_AWARDService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.NumberUtils;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;
import com.longti.upjc.util.jdbet.BetUtils;

/**
 * 查看收益明细列表
 * 
 * @return
 */
@Component("profit_details")
public class ProfileListStrategy implements IMethodStrategy {

	public static class ProfileDetail {
		public String val;//收益
		public String electronic_code;//币种简称
		public String nick_name;//用户昵称
		public String get_date;//时间
	}

	public static class ProfileListData {
		public int page_index;
		public int page_size;
		public int page_count;
		public int record_count;
		public List<ProfileDetail> lst = new ArrayList<ProfileDetail>();
	}

	protected final transient static Logger logger = LoggerFactory.getLogger(ProfileListStrategy.class);
	@Autowired
	private T_INVITATION_AWARDService t_invitation_awardService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<ProfileListData> rv = new ReturnValue<ProfileListData>();
		rv.setData(new ProfileListData());
		logger.info("profit_details开始调用查看收益明细列表接口doJsonMethod------>");
		int page_index = 0;
		T_INVITATION_AWARD t_invitation_award = new T_INVITATION_AWARD();
		if (jsonRequest.containsKey("page_index")) {
			page_index = Integer.parseInt(jsonRequest.get("page_index").toString());
		}
		if (StringUtil.isEmpty(request_LtGameLogic.getFeeType()) == false) {
			t_invitation_award.setElectronic_code(request_LtGameLogic.getFeeType());
		}
		if (StringUtil.isEmpty(request_LtGameLogic.getUserPin()) == false) {
			t_invitation_award.setUser_pin(request_LtGameLogic.getUserPin());
		}
		int record_count=0;
		try {
			record_count = this.t_invitation_awardService.selectT_INVITATION_AWARDCount(t_invitation_award);
		} catch (Exception e) {
			logger.error("查询收益列表数据总数userPin："+request_LtGameLogic.getUserPin()+"失败----->");
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			return JSONObject.toJSONString(rv);
		}
		t_invitation_award.setPage_size(20);
		int page_count = (record_count % t_invitation_award.getPage_size() == 0 ? record_count / t_invitation_award.getPage_size() : record_count / t_invitation_award.getPage_size() + 1);
        if (page_index >= page_count){
            page_index = page_count - 1;
            rv.getData().page_index=(page_index);
            rv.getData().page_size= t_invitation_award.getPage_size();
            rv.getData().page_count=page_count;
            rv.getData().record_count= record_count;
            rv.setStatus(ErrorMessage.NO_REC.getCode());
            rv.setMessage(ErrorMessage.NO_REC.getMessage());
            return JSONObject.toJSONString(rv);
        }
        if (page_index < 0)
            page_index = 0;
        t_invitation_award.setRow_start(page_index * t_invitation_award.getPage_size());
        List<T_INVITATION_AWARD> invlst = null;
		try{
			invlst = t_invitation_awardService.selectT_INVITATION_AWARDList(t_invitation_award);
			for(T_INVITATION_AWARD inv : invlst){
				ProfileDetail profileDetail = new ProfileDetail();
				profileDetail.electronic_code = inv.getElectronic_code();
				profileDetail.get_date = DateUtils.getDateToStr(inv.getCreate_time(), "yyyy-MM-dd HH:mm:ss");
				profileDetail.nick_name = inv.getUser_pin();
				profileDetail.val = NumberUtils.longDiv(inv.getWin_fee(),BetUtils.preMul).toString();
				rv.getData().lst.add(profileDetail);
			}
			rv.getData().page_index= page_index;
	        rv.getData().page_size= t_invitation_award.getPage_size();
	        rv.getData().page_count= page_count;
	        rv.getData().record_count= record_count;
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			logger.info("查看收益明细列表成功----->");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("查看收益明细列表失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
	
	
}