/**
 * @author 张世才
 * 2018-03-22 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.entity.sporttery.TAB_LEAGUENAME_LANG;
import com.longti.upjc.entity.sporttery.TAB_OPTIONS_LANG;
import com.longti.upjc.entity.sporttery.TAB_PLAYMETHOD_LANG;
import com.longti.upjc.entity.sporttery.TAB_TEAMNAME_LANG;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.TAB_LEAGUENAME_LANGService;
import com.longti.upjc.service.sporttery.TAB_OPTIONS_LANGService;
import com.longti.upjc.service.sporttery.TAB_PLAYMETHOD_LANGService;
import com.longti.upjc.service.sporttery.TAB_TEAMNAME_LANGService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.ReturnValue;

/**
 * 多语言列表
 * 
 * @return
 */
@Component("lang_list")
public class LangListStrategy implements IMethodStrategy {

	public static class LotoLangDetail {
		public String issue; // 比赛编号
		public String leaguename; // 比赛名称
		public String home_team_name; // 主队名称
		public String guest_team_name; // 客队名称
		public String play_method; //玩法信息
		public String options_one;	//选项1内容
		public String options_two;	//选项2内容
		public String options_three;	//选项3内容
	}

	public static class LotoLangData {
		public List<LotoLangDetail> lst = new ArrayList<LotoLangDetail>();
	}
	
	@Autowired
	private TAB_LEAGUENAME_LANGService tab_LEAGUENAME_LANGService;
	@Autowired
	private TAB_TEAMNAME_LANGService tab_TEAMNAME_LANGService;
	@Autowired
	private TAB_PLAYMETHOD_LANGService tab_PLAYMETHOD_LANGService;
	@Autowired
	private TAB_OPTIONS_LANGService tab_OPTIONS_LANGService;
	
	protected final transient static Logger logger = LoggerFactory.getLogger(LangListStrategy.class);
	
	@Override
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		ReturnValue<LotoLangData> rv = new ReturnValue<LotoLangData>();
		rv.setData(new LotoLangData());
		logger.info("lang_list开始调用多语言接口doJsonMethod------>");
		String lang = request_LtGameLogic.getLang();
		JSONArray lst = jsonRequest.getJSONArray("lst");
		try {	
			TAB_LEAGUENAME_LANG tab_leaguename_lang = new TAB_LEAGUENAME_LANG(); 
			tab_leaguename_lang.setLang(lang);
			TAB_TEAMNAME_LANG tab_teamname_lang = new TAB_TEAMNAME_LANG(); 
			tab_teamname_lang.setLang(lang);
			TAB_PLAYMETHOD_LANG tab_playmethod_lang = new TAB_PLAYMETHOD_LANG(); 
			tab_playmethod_lang.setLang(lang);
			TAB_OPTIONS_LANG tab_options_lang = new TAB_OPTIONS_LANG(); 
			tab_options_lang.setLang(lang);
			HashMap<String,String> tab_leaguename_langs = tab_LEAGUENAME_LANGService.selectTAB_LEAGUENAME_LANGMap(tab_leaguename_lang);
			HashMap<String,String> tab_teamname_langs = tab_TEAMNAME_LANGService.selectTAB_TEAMNAME_LANGMap(tab_teamname_lang);
			HashMap<String,String> tab_playmethod_langs = tab_PLAYMETHOD_LANGService.selectTAB_PLAYMETHOD_LANGMap(tab_playmethod_lang);
			HashMap<String,String> tab_options_langs = tab_OPTIONS_LANGService.selectTAB_OPTIONS_LANGMap(tab_options_lang);
			for (Object jOdd : lst) {	
				LotoLangDetail lotoLangDetail = new LotoLangDetail();
				String issue = ((JSONObject) jOdd).get("issue").toString();
				String leaguename = ((JSONObject) jOdd).get("leaguename").toString();
				String home_team_name = ((JSONObject) jOdd).get("home_team_name").toString();
				String guest_team_name = ((JSONObject) jOdd).get("guest_team_name").toString();
				String play_method = ((JSONObject) jOdd).get("play_method").toString();
				String options_one = ((JSONObject) jOdd).get("options_one").toString();
				String options_two = ((JSONObject) jOdd).get("options_two").toString();
				String options_three = ((JSONObject) jOdd).get("options_three").toString();
				lotoLangDetail.issue = issue;
				lotoLangDetail.leaguename = tab_leaguename_langs.get(leaguename);
				lotoLangDetail.home_team_name = tab_teamname_langs.get(home_team_name);
				lotoLangDetail.guest_team_name = tab_teamname_langs.get(guest_team_name);
				lotoLangDetail.play_method = tab_playmethod_langs.get(play_method);
				lotoLangDetail.options_one = tab_options_langs.get(options_one);
				lotoLangDetail.options_two = tab_options_langs.get(options_two);
				lotoLangDetail.options_three = tab_options_langs.get(options_three);
				rv.getData().lst.add(lotoLangDetail);
			}
			rv.setStatus(ErrorMessage.SUCCESS.getCode());
			rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			logger.info("多语言转换成功----->");
		} catch (Exception e) {
			e.printStackTrace();
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.info("多语言转换失败----->");
		}
		return JSONObject.toJSONString(rv);
	}
}