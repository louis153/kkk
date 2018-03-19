package com.longti.upjc.strategy.sporttery;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.formdata.system.Request_LtGameLogic;

public interface IMethodStrategy {
	String doJsonMethod(Request_LtGameLogic request_LtGameLogic ,JSONObject gameRequest) throws Exception;
}
