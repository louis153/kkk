/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.LOTO_ORDER;
import com.tengcai.vims.service.sporttery.LOTO_ORDERService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.ErrorMessage;
import com.tengcai.vims.util.ReturnValue;

/**
 *查看轮播图
 * @return 
 */
@Component("t_win_list")
public class TWinListStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(TWinListStrategy.class);
	public static class WinListDetail{
		
	    private Integer win_fee; //奖金,0表示未中奖
	    
	    private String username; //备注
	    
	   
		public Integer getWin_fee() {
			return win_fee;
		}
		public void setWin_fee(Integer win_fee) {
			this.win_fee = win_fee;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		
		
		
	}
	public static class WinListData{
		private List<WinListDetail> lst;

		public List<WinListDetail> getLst() {
			return lst;
		}

		public void setLst(List<WinListDetail> lst) {
			this.lst = lst;
		}
	}
	@Autowired
	private LOTO_ORDERService loto_ORDERService;
	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("t_win_list开始调用查看轮播图接口doJsonMethod------>");
		ReturnValue<WinListData> rv = new ReturnValue<WinListData>();
		rv.setData(new WinListData());
		LOTO_ORDER lotoOrder=new LOTO_ORDER();
		lotoOrder.setPage_size(5);
		lotoOrder.setRow_start(0);
		lotoOrder.setBet_status(2);
		List<LOTO_ORDER> lst=null;
		List<WinListDetail> lstData=new ArrayList<WinListDetail>();
		rv.getData().setLst(lstData);
		try {
			lst = this.loto_ORDERService.selectLOTO_ORDERList(lotoOrder);
			if(lst.isEmpty()){
				rv.setStatus(ErrorMessage.NO_REC.getCode());
				rv.setMessage(ErrorMessage.NO_REC.getMessage());				
			}else{
				for(LOTO_ORDER loto_ORDER:lst){
					WinListDetail winListDetail=new WinListDetail();					
					winListDetail.setWin_fee(loto_ORDER.getWin_fee());
					winListDetail.setUsername(loto_ORDER.getMemo());
					lstData.add(winListDetail);
				}
				rv.setStatus(ErrorMessage.SUCCESS.getCode());
				rv.setMessage(ErrorMessage.SUCCESS.getMessage());
			}
		} catch (Exception e) {
			rv.setStatus(ErrorMessage.FAIL.getCode());
			rv.setMessage(ErrorMessage.FAIL.getMessage());
			logger.error("查询获胜轮播列表失败----->"+e.getMessage());
		}

        rv.getData().setLst(lstData);
        
          
        logger.info("查询获胜轮播列表成功----->");
		return JSONObject.toJSONString(rv);
	}
	
}