/**
 * 
 */
/**
 * @author Dell
 *
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;


@Service
public class GameLogicMethodFactory {
    @Autowired
    private Map<String,IMethodStrategy> methodContextStrategyMap;
    
    
    public String doAction(String strType,Request_LtGameLogic request_LtGameLogic ,JSONObject gameRequest) throws Exception{
         return this.methodContextStrategyMap.get(strType).doJsonMethod(request_LtGameLogic, gameRequest);
    }



	public Map<String,IMethodStrategy> getMethodContextStrategyMap() {
		return methodContextStrategyMap;
	}



	public void setMethodContextStrategyMap(Map<String,IMethodStrategy> methodContextStrategyMap) {
		this.methodContextStrategyMap = methodContextStrategyMap;
	}
}