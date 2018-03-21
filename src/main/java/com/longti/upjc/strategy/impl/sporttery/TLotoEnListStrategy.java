/**
 * @author 张世才
 * 2018-03-20 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.T_LOTO_EN_ETH;
import com.longti.upjc.entity.sporttery.T_LOTO_EN_GTO;
import com.longti.upjc.entity.sporttery.T_LOTO_EN_UZ;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_LOTO_EN_ETHService;
import com.longti.upjc.service.sporttery.T_LOTO_EN_GTOService;
import com.longti.upjc.service.sporttery.T_LOTO_EN_UZService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 查看话题竞猜列表
 * 
 * @return
 */
@Component("loto_en_list")
public class TLotoEnListStrategy implements IMethodStrategy {

	public static class LotoEnDetail {
		public String issue; // 比赛编号
		public String endtime; // 截止投注时间
		public String home_team_name; // 主队名称
		public String guest_team_name; // 客队名称
		public String leaguename; // 比赛名称
		public String options_one;	//选项1内容
		public String options_two;	//选项2内容
		public String options_three;	//选项3内容
		public String odds_one;	//选项1赔率
		public String odds_two;	//选项2赔率
		public String odds_three;	//选项3赔率
	}

	public static class LotoEnData {
		public List<LotoEnDetail> lst = new ArrayList<LotoEnDetail>();
	}

	protected final transient static Logger logger = LoggerFactory.getLogger(TLotoEnListStrategy.class);
	@Autowired
	private T_LOTO_EN_ETHService lotoENETHService;
	@Autowired
	private T_LOTO_EN_GTOService lotoENGTOService; 
	@Autowired
	private T_LOTO_EN_UZService lotoENUZService;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<LotoEnData> rv = new ReturnValue<LotoEnData>();
		rv.setData(new LotoEnData());
		logger.info("loto_en_list开始调用查看话题竞猜列表接口doJsonMethod------>");
		String electronic_code = jsonRequest.get("electronic_code").toString().trim();
		String lang = jsonRequest.get("lang").toString().trim();
		List<T_LOTO_EN_ETH> ethlst = null;
		List<T_LOTO_EN_GTO> gtolst = null;
		List<T_LOTO_EN_UZ> uzlst = null;
		
		try {	
			if("ETH".equals(electronic_code)){
				T_LOTO_EN_ETH t_loto_en_eth = new T_LOTO_EN_ETH();
				t_loto_en_eth.setStatus(1);
				t_loto_en_eth.setMnl_bet(1);		
				ethlst = lotoENETHService.selectT_LOTO_EN_ETHList(t_loto_en_eth);
				for (T_LOTO_EN_ETH e : ethlst) {
					LotoEnDetail lotoEnDetail = new LotoEnDetail();
					lotoEnDetail.endtime = e.getEndtime().replace("-", "").replace(" ", "").replace(":", "");
					lotoEnDetail.home_team_name = e.getHome_team_name();
					lotoEnDetail.guest_team_name = e.getGuest_team_name();
					lotoEnDetail.issue = e.getIssue();
					lotoEnDetail.leaguename = e.getLeaguename();
					lotoEnDetail.options_one=e.getOptions_one();
					lotoEnDetail.options_two=e.getOptions_two();
					lotoEnDetail.options_three=e.getOptions_three();
					lotoEnDetail.odds_one=e.getOdds_one();
					lotoEnDetail.odds_two=e.getOdds_two();
					lotoEnDetail.odds_three=e.getOdds_three();
					rv.getData().lst.add(lotoEnDetail);
				}
			}else if("GTO".equals(electronic_code)){
				T_LOTO_EN_GTO t_loto_en_gto = new T_LOTO_EN_GTO();
				t_loto_en_gto.setStatus(1);
				t_loto_en_gto.setMnl_bet(1);		
				gtolst = lotoENGTOService.selectT_LOTO_EN_GTOList(t_loto_en_gto);
				for (T_LOTO_EN_GTO e : gtolst) {
					LotoEnDetail lotoEnDetail = new LotoEnDetail();
					lotoEnDetail.endtime = e.getEndtime().replace("-", "").replace(" ", "").replace(":", "");
					lotoEnDetail.home_team_name = e.getHome_team_name();
					lotoEnDetail.guest_team_name = e.getGuest_team_name();
					lotoEnDetail.issue = e.getIssue();
					lotoEnDetail.leaguename = e.getLeaguename();
					lotoEnDetail.options_one=e.getOptions_one();
					lotoEnDetail.options_two=e.getOptions_two();
					lotoEnDetail.options_three=e.getOptions_three();
					lotoEnDetail.odds_one=e.getOdds_one();
					lotoEnDetail.odds_two=e.getOdds_two();
					lotoEnDetail.odds_three=e.getOdds_three();
					rv.getData().lst.add(lotoEnDetail);
				}
			}else if("UZ".equals(electronic_code)){
				T_LOTO_EN_UZ t_loto_en_uz = new T_LOTO_EN_UZ();
				t_loto_en_uz.setStatus(1);
				t_loto_en_uz.setMnl_bet(1);		
				uzlst = lotoENUZService.selectT_LOTO_EN_UZList(t_loto_en_uz);
				for (T_LOTO_EN_UZ e : uzlst) {
					LotoEnDetail lotoEnDetail = new LotoEnDetail();
					lotoEnDetail.endtime = e.getEndtime().replace("-", "").replace(" ", "").replace(":", "");
					lotoEnDetail.home_team_name = e.getHome_team_name();
					lotoEnDetail.guest_team_name = e.getGuest_team_name();
					lotoEnDetail.issue = e.getIssue();
					lotoEnDetail.leaguename = e.getLeaguename();
					lotoEnDetail.options_one=e.getOptions_one();
					lotoEnDetail.options_two=e.getOptions_two();
					lotoEnDetail.options_three=e.getOptions_three();
					lotoEnDetail.odds_one=e.getOdds_one();
					lotoEnDetail.odds_two=e.getOdds_two();
					lotoEnDetail.odds_three=e.getOdds_three();
					rv.getData().lst.add(lotoEnDetail);
				}
			}
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			logger.info("查看话题竞猜列表成功----->");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("查看话题竞猜列表失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
}