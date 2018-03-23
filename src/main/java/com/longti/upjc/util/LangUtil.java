package com.longti.upjc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.strategy.impl.sporttery.LangListStrategy;

public class LangUtil {
	
	private LangListStrategy langListStrategy;
	public static class LangObj{
		public String issue;
		public String leaguename;
		public String home_team_name;
		public String guest_team_name;
		public String play_method;
		public String options_one;
		public String options_two;
		public String options_three;
	}
	public LangUtil(LangListStrategy langListStrategy){
		this.langListStrategy=langListStrategy;
	}
	public Map<String,LangObj> getLangMap(String feeType,String lang,String userPin,List<LangObj> lstLang) throws Exception{
		
		Request_LtGameLogic request_LtGameLogic=new Request_LtGameLogic();
		request_LtGameLogic.setExt("");
		request_LtGameLogic.setFeeType(feeType);
		request_LtGameLogic.setGameId(500L);
		request_LtGameLogic.setGameSource(1);
		request_LtGameLogic.setTranType(0);
		request_LtGameLogic.setLang(lang);		
		request_LtGameLogic.setUserPin(userPin);
		request_LtGameLogic.setUserToken("");
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("lst", lstLang);
		request_LtGameLogic.setGameRequest(jsonObject.toJSONString());
		String rvStr=langListStrategy.doJsonMethod(request_LtGameLogic, jsonObject);
		JSONObject 	rv=(JSONObject)JSONObject.parse(rvStr);
		Map<String,LangObj> mapRv=new HashMap<String,LangObj>();
		if(rv.get("status").equals("000000")){
			JSONArray lst= (JSONArray)rv.get("lst");
			
			
			for(Object o:lst){
				LangObj langObj=new LangObj();
				langObj.issue=((JSONObject)o).get("issue").toString();
				langObj.guest_team_name=((JSONObject)o).get("guest_team_name").toString();
				langObj.home_team_name=((JSONObject)o).get("home_team_name").toString();
				langObj.leaguename=((JSONObject)o).get("leaguename").toString();
				langObj.options_one=((JSONObject)o).get("options_one").toString();
				langObj.options_three=((JSONObject)o).get("options_three").toString();
				langObj.options_two=((JSONObject)o).get("options_two").toString();
				langObj.play_method=((JSONObject)o).get("play_method").toString();
				mapRv.put(langObj.issue,langObj);
				
			}
			return mapRv;
 
		}else{
			for(LangObj o:lstLang){
				mapRv.put(o.issue, o);
			}
			return mapRv;
		}
		
	}
}
