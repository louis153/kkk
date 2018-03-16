/**
 * 
 */
/**
 * @author Dell
 *
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;


@Service
public class GameLogicMethodFactory {
    @Autowired
    private Map<String,IMethodStrategy> methodContextStrategyMap;
    
    
    public String doAction(String strType, String userPin ,JSONObject gameRequest) throws Exception{
         return this.methodContextStrategyMap.get(strType).doJsonMethod(userPin, gameRequest);
    }



	public Map<String,IMethodStrategy> getMethodContextStrategyMap() {
		return methodContextStrategyMap;
	}



	public void setMethodContextStrategyMap(Map<String,IMethodStrategy> methodContextStrategyMap) {
		this.methodContextStrategyMap = methodContextStrategyMap;
	}
}