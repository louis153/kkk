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

import com.alibaba.fastjson.JSONArray;
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
		public String play_method; //玩法信息
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
	@Autowired
	private LangListStrategy langListStrategy;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<LotoEnData> rv = new ReturnValue<LotoEnData>();
		rv.setData(new LotoEnData());
		logger.info("loto_en_list开始调用查看话题竞猜列表接口doJsonMethod------>");
		String electronic_code = request_LtGameLogic.getFeeType();
		List<T_LOTO_EN_ETH> ethlst = null;
		List<T_LOTO_EN_GTO> gtolst = null;
		List<T_LOTO_EN_UZ> uzlst = null;
		try {	
			if("ETH".equals(electronic_code)){
				T_LOTO_EN_ETH t_loto_en_eth = new T_LOTO_EN_ETH();
				t_loto_en_eth.setStatus(1);
				t_loto_en_eth.setMnl_bet(1);		
				ethlst = lotoENETHService.selectT_LOTO_EN_ETHList(t_loto_en_eth);
				logger.info("多语言转换开始----->");
				JSONObject jsonRV=transfer_lang(request_LtGameLogic,ethlst,null,null);
				if(jsonRV.getString("status").equals(ErrorMessage.SUCCESS.getCode())==false){
					rv.setStatus(jsonRV.getString("status"));
					rv.setMessage(jsonRV.getString("message"));
					logger.info("多语言转换失败----->"+jsonRV.getString("message"));
					return JSONObject.toJSONString(rv);
				}else{
					JSONObject data = jsonRV.getJSONObject("data");
					JSONArray lst = data.getJSONArray("lst");
					for (Object jOdd : lst) {
						String issue = ((JSONObject) jOdd).get("issue").toString();
						for (T_LOTO_EN_ETH e : ethlst) {
							if(issue.equals(e.getIssue())){
								LotoEnDetail lotoEnDetail = new LotoEnDetail();
								lotoEnDetail.endtime = e.getEndtime().replace("-", "").replace(" ", "").replace(":", "");
								lotoEnDetail.home_team_name = ((JSONObject) jOdd).get("home_team_name").toString();//e.getHome_team_name();
								lotoEnDetail.guest_team_name = ((JSONObject) jOdd).get("guest_team_name").toString();//e.getGuest_team_name();
								lotoEnDetail.issue = e.getIssue();
								lotoEnDetail.leaguename = ((JSONObject) jOdd).get("leaguename").toString();//e.getLeaguename();
								lotoEnDetail.play_method = ((JSONObject) jOdd).get("play_method").toString();//e.getPlay_method();
								lotoEnDetail.options_one= ((JSONObject) jOdd).get("options_one").toString();//e.getOptions_one();
								lotoEnDetail.options_two= ((JSONObject) jOdd).get("options_two").toString();//e.getOptions_two();
								lotoEnDetail.options_three= ((JSONObject) jOdd).get("options_three").toString();//e.getOptions_three();
								lotoEnDetail.odds_one=e.getOdds_one();
								lotoEnDetail.odds_two=e.getOdds_two();
								lotoEnDetail.odds_three=e.getOdds_three();
								rv.getData().lst.add(lotoEnDetail);
							}
						}
					}
				}			
				logger.info("多语言转换成功----->");
			}else if("GTO".equals(electronic_code)){
				T_LOTO_EN_GTO t_loto_en_gto = new T_LOTO_EN_GTO();
				t_loto_en_gto.setStatus(1);
				t_loto_en_gto.setMnl_bet(1);		
				gtolst = lotoENGTOService.selectT_LOTO_EN_GTOList(t_loto_en_gto);
				logger.info("多语言转换开始----->");
				JSONObject jsonRV=transfer_lang(request_LtGameLogic,null,gtolst,null);
				if(jsonRV.getString("status").equals(ErrorMessage.SUCCESS.getCode())==false){
					rv.setStatus(jsonRV.getString("status"));
					rv.setMessage(jsonRV.getString("message"));
					logger.info("多语言转换失败----->"+jsonRV.getString("message"));
					return JSONObject.toJSONString(rv);
				}else{
					JSONObject data = jsonRV.getJSONObject("data");
					JSONArray lst = data.getJSONArray("lst");
					for (Object jOdd : lst) {
						String issue = ((JSONObject) jOdd).get("issue").toString();
						for (T_LOTO_EN_GTO e : gtolst) {
							if(issue.equals(e.getIssue())){
								LotoEnDetail lotoEnDetail = new LotoEnDetail();
								lotoEnDetail.endtime = e.getEndtime().replace("-", "").replace(" ", "").replace(":", "");
								lotoEnDetail.home_team_name = ((JSONObject) jOdd).get("home_team_name").toString();//e.getHome_team_name();
								lotoEnDetail.guest_team_name = ((JSONObject) jOdd).get("guest_team_name").toString();//e.getGuest_team_name();
								lotoEnDetail.issue = e.getIssue();
								lotoEnDetail.leaguename = ((JSONObject) jOdd).get("leaguename").toString();//e.getLeaguename();
								lotoEnDetail.play_method = ((JSONObject) jOdd).get("play_method").toString();//e.getPlay_method();
								lotoEnDetail.options_one= ((JSONObject) jOdd).get("options_one").toString();//e.getOptions_one();
								lotoEnDetail.options_two= ((JSONObject) jOdd).get("options_two").toString();//e.getOptions_two();
								lotoEnDetail.options_three= ((JSONObject) jOdd).get("options_three").toString();//e.getOptions_three();
								lotoEnDetail.odds_one=e.getOdds_one();
								lotoEnDetail.odds_two=e.getOdds_two();
								lotoEnDetail.odds_three=e.getOdds_three();
								rv.getData().lst.add(lotoEnDetail);
							}
						}
					}
				}			
				logger.info("多语言转换成功----->");				
			}else if("UZ".equals(electronic_code)){
				T_LOTO_EN_UZ t_loto_en_uz = new T_LOTO_EN_UZ();
				t_loto_en_uz.setStatus(1);
				t_loto_en_uz.setMnl_bet(1);		
				uzlst = lotoENUZService.selectT_LOTO_EN_UZList(t_loto_en_uz);
				logger.info("多语言转换开始----->");
				JSONObject jsonRV=transfer_lang(request_LtGameLogic,null,null,uzlst);
				if(jsonRV.getString("status").equals(ErrorMessage.SUCCESS.getCode())==false){
					rv.setStatus(jsonRV.getString("status"));
					rv.setMessage(jsonRV.getString("message"));
					logger.info("多语言转换失败----->"+jsonRV.getString("message"));
					return JSONObject.toJSONString(rv);
				}else{
					JSONObject data = jsonRV.getJSONObject("data");
					JSONArray lst = data.getJSONArray("lst");
					for (Object jOdd : lst) {
						String issue = ((JSONObject) jOdd).get("issue").toString();
						for (T_LOTO_EN_UZ e : uzlst) {
							if(issue.equals(e.getIssue())){
								LotoEnDetail lotoEnDetail = new LotoEnDetail();
								lotoEnDetail.endtime = e.getEndtime().replace("-", "").replace(" ", "").replace(":", "");
								lotoEnDetail.home_team_name = ((JSONObject) jOdd).get("home_team_name").toString();//e.getHome_team_name();
								lotoEnDetail.guest_team_name = ((JSONObject) jOdd).get("guest_team_name").toString();//e.getGuest_team_name();
								lotoEnDetail.issue = e.getIssue();
								lotoEnDetail.leaguename = ((JSONObject) jOdd).get("leaguename").toString();//e.getLeaguename();
								lotoEnDetail.play_method = ((JSONObject) jOdd).get("play_method").toString();//e.getPlay_method();
								lotoEnDetail.options_one= ((JSONObject) jOdd).get("options_one").toString();//e.getOptions_one();
								lotoEnDetail.options_two= ((JSONObject) jOdd).get("options_two").toString();//e.getOptions_two();
								lotoEnDetail.options_three= ((JSONObject) jOdd).get("options_three").toString();//e.getOptions_three();
								lotoEnDetail.odds_one=e.getOdds_one();
								lotoEnDetail.odds_two=e.getOdds_two();
								lotoEnDetail.odds_three=e.getOdds_three();
								rv.getData().lst.add(lotoEnDetail);
							}
						}
					}
				}			
				logger.info("多语言转换成功----->");
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
	
	private JSONObject transfer_lang(Request_LtGameLogic request_LtGameLogic,List<T_LOTO_EN_ETH> ethlst,List<T_LOTO_EN_GTO> gtolst,List<T_LOTO_EN_UZ> uzlst) throws Exception{
		Request_LtGameLogic invcode_Request=new Request_LtGameLogic(request_LtGameLogic);
		JSONObject invcodeJSON=new JSONObject();
		invcodeJSON.put("method","lang_list");
		if(ethlst != null){
			invcodeJSON.put("lst", ethlst);
		}else if(gtolst != null){
			invcodeJSON.put("lst", gtolst);
		}else if(uzlst != null){
			invcodeJSON.put("lst", uzlst);
		}
		invcode_Request.setGameRequest(invcodeJSON.toJSONString());
		JSONObject jsonRV=JSONObject.parseObject(langListStrategy.doJsonMethod(invcode_Request, invcodeJSON));
		return jsonRV;
	}
	
	
}