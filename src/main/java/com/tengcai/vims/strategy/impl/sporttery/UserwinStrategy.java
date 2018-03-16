/**
 * @author 杨阳
 * 2017-08-03
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.USER_DAYWIN;
import com.tengcai.vims.service.sporttery.USER_DAYWINService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;

/**
 *查询用户昨天中奖情况
 * @return 
 */
@Component("get_userwin")
public class UserwinStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(UserwinStrategy.class);
	public static class UserWinData{
		
	}
	@Autowired
	private USER_DAYWINService userDayWinService;
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("get_userwin查询用户昨天中奖情况doJsonMethod------>");
		
		ReturnValue<USER_DAYWIN> rv = new ReturnValue<>();
		USER_DAYWIN user_DAYWIN=new USER_DAYWIN();
		user_DAYWIN.setUser_pin(userPin);
		
        List<USER_DAYWIN> lst=null;
		try {
			lst = this.userDayWinService.selectUSER_DAYWINList(user_DAYWIN);
			if(lst.isEmpty()==false){				
				this.userDayWinService.deleteUSER_DAYWIN(lst.get(0));
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
				rv.setData(lst.get(0));
			}else{
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());
				rv.setData(user_DAYWIN);
			}
			
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			rv.setData(user_DAYWIN);
			logger.error("查询用户昨天中奖情况失败----->"+e.getMessage());
		}

        
        logger.info("查询用户昨天中奖情况成功----->");
		return JSONObject.toJSONString(rv);
		
	}
	
}