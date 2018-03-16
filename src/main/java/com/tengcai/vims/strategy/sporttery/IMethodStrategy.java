package com.tengcai.vims.strategy.sporttery;

import com.alibaba.fastjson.JSONObject;

public interface IMethodStrategy {
	String doJsonMethod(String userPin ,JSONObject gameRequest) throws Exception;
}
