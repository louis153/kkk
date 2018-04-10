/**
 * @author 张世才
 * 2018-03-20 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.T_LOTO_E;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.T_LOTO_ENService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.LangUtil;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.LangUtil.LangObj;

/**
 * 查看话题竞猜列表
 * 
 * @return
 */
@Component("loto_en_list")
public class TLotoEnListStrategy implements IMethodStrategy {

	public static class LotoEnDetail {
	    public String lottery_type;//投注类型
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
	private T_LOTO_ENService lotoENService;
	@Autowired
	private LangListStrategy langListStrategy;
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<LotoEnData> rv = new ReturnValue<LotoEnData>();
		rv.setData(new LotoEnData());
		logger.info("loto_en_list开始调用查看话题竞猜列表接口doJsonMethod------>");
		String electronic_code = request_LtGameLogic.getFeeType();
		String lang = request_LtGameLogic.getLang();
		String user_pin = request_LtGameLogic.getUserPin();
		List<T_LOTO_E> lst = null;
		try {	
			T_LOTO_E t_loto_e = new T_LOTO_E();
			t_loto_e.setElectronic_code(electronic_code);
			t_loto_e.setStatus(1);
			t_loto_e.setAvailable(1);
			t_loto_e.setMnl_bet(1);
			lst = lotoENService.selectT_LOTO_ENList(t_loto_e);
			logger.info("多语言转换开始----->");
			changEsLang(lst,electronic_code,lang,user_pin);
			logger.info("多语言转换成功----->");
			for (T_LOTO_E e : lst) {
				LotoEnDetail lotoEnDetail = new LotoEnDetail();
				lotoEnDetail.lottery_type = String.valueOf(e.getLottery_type());
				lotoEnDetail.endtime = e.getEndtime().replace("-", "").replace(" ", "").replace(":", "");
				lotoEnDetail.home_team_name = e.getHome_team_name();
				lotoEnDetail.guest_team_name = e.getGuest_team_name();
				lotoEnDetail.issue = e.getIssue();
				lotoEnDetail.leaguename = e.getLeaguename();
				lotoEnDetail.play_method = e.getPlay_method();
				lotoEnDetail.options_one= e.getOptions_one();
				lotoEnDetail.options_two= e.getOptions_two();
				lotoEnDetail.options_three= e.getOptions_three();
				lotoEnDetail.odds_one=e.getOdds_one();
				lotoEnDetail.odds_two=e.getOdds_two();
				lotoEnDetail.odds_three=e.getOdds_three();
				rv.getData().lst.add(lotoEnDetail);
		    }			
			rv.setMess(ErrorMessage.SUCCESS);
			logger.info("查看话题竞猜列表成功----->");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setMess(ErrorMessage.FAIL);
			logger.info("查看话题竞猜列表失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
	
	private void changEsLang(List<T_LOTO_E> lst,String feeType,String lang,String userPin) throws Exception{
		
		List<LangObj> lstLang=new ArrayList<LangObj>();
		if(lst!=null){
			for(T_LOTO_E e:lst){
				LangObj langObj=new LangObj();
				langObj.issue=e.getIssue();
				langObj.leaguename=e.getLeaguename();
				langObj.home_team_name=e.getHome_team_name();
				langObj.guest_team_name=e.getGuest_team_name();
				langObj.play_method=e.getPlay_method();
				langObj.options_one=e.getOptions_one();
				langObj.options_two=e.getOptions_two();
				langObj.options_three=e.getOptions_three();
				lstLang.add(langObj);
			}

			Map<String,LangObj> mapLang= (new LangUtil(langListStrategy)).getLangMap(feeType,lang,userPin,lstLang);
			for(T_LOTO_E e:lst){
				if(mapLang.containsKey(e.getIssue())){
						LangObj langObj=mapLang.get(e.getIssue());
				    if(langObj.leaguename.isEmpty()==false)
				    	e.setLeaguename(langObj.leaguename);
					if(langObj.home_team_name.isEmpty()==false)
						e.setHome_team_name(langObj.home_team_name);
					if(langObj.guest_team_name.isEmpty()==false)
						e.setGuest_team_name(langObj.guest_team_name);
					if(langObj.play_method.isEmpty()==false)
						e.setPlay_method(langObj.play_method);
					if(langObj.guest_team_name.isEmpty()==false)
						e.setOptions_one(langObj.options_one);
					if(langObj.options_one.isEmpty()==false)
						e.setOptions_two(langObj.options_two);
					if(langObj.options_three.isEmpty()==false)
						e.setOptions_three(langObj.options_three);	
				}
			}
		}
	}
	
	
}