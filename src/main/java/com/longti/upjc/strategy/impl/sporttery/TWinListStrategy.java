/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.longti.upjc.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ibm.icu.math.BigDecimal;
import com.longti.upjc.entity.sporttery.LOTO_ORDER;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.service.sporttery.LOTO_ORDERService;
import com.longti.upjc.strategy.sporttery.IMethodStrategy;
import com.longti.upjc.util.ErrorMessage;
import com.longti.upjc.util.NumberUtils;
import com.longti.upjc.util.ReturnValue;
import com.longti.upjc.util.StringUtil;
import com.longti.upjc.util.jdbet.BetUtils;

/**
 *查看轮播图
 * @return 
 */
@Component("t_win_list")
public class TWinListStrategy implements IMethodStrategy{
	protected final transient static Logger logger = LoggerFactory.getLogger(TWinListStrategy.class);
	public static class WinListDetail{
		
	    private String win_fee; //奖金,0表示未中奖
	    
	    private String username; //备注
	    
	    private String electronic_code;
	   
		public String getElectronic_code() {
			return electronic_code;
		}
		public void setElectronic_code(String electronic_code) {
			this.electronic_code = electronic_code;
		}
		public String getWin_fee() {
			return win_fee;
		}
		public void setWin_fee(String win_fee) {
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
	public String doJsonMethod(Request_LtGameLogic request_LtGameLogic, JSONObject jsonRequest) throws Exception {
		logger.info("t_win_list开始调用查看轮播图接口doJsonMethod------>");
		ReturnValue<WinListData> rv = new ReturnValue<WinListData>();
		rv.setData(new WinListData());
		LOTO_ORDER lotoOrder=new LOTO_ORDER();
		lotoOrder.setPage_size(50);
		lotoOrder.setRow_start(0);
		lotoOrder.setBet_status(2);
		List<LOTO_ORDER> lst=null;
		List<WinListDetail> lstData=new ArrayList<WinListDetail>();
		rv.getData().setLst(lstData);
		try {
			int icount=0;
			LinkedList<String> iLinkedList=new LinkedList<String>();
			lst = this.loto_ORDERService.selectLOTO_ORDERList(lotoOrder);
			if(lst.isEmpty()){
				rv.setMess(ErrorMessage.NO_REC);				
			}else{
				for(LOTO_ORDER loto_ORDER:lst){
					if(iLinkedList.indexOf(loto_ORDER.getUser_pin())<0){
						iLinkedList.add(loto_ORDER.getUser_pin());						
						WinListDetail winListDetail=new WinListDetail();					
						winListDetail.setWin_fee(StringUtil.removeEndZero(new BigDecimal(NumberUtils.longDiv(loto_ORDER.getWin_fee(),BetUtils.preMul)).toString()));
						winListDetail.setElectronic_code(loto_ORDER.getElectronic_code());
						winListDetail.setUsername(loto_ORDER.getMemo());
						lstData.add(winListDetail);
						icount++;
						if(icount>=10){
							break;
						}
					}
				}
				rv.setMess(ErrorMessage.SUCCESS);
			}
		} catch (Exception e) {
			rv.setMess(ErrorMessage.FAIL);
			logger.error("查询获胜轮播列表失败----->"+e.getMessage());
		}

        rv.getData().setLst(lstData);
        
          
        logger.info("查询获胜轮播列表成功----->");
		return JSONObject.toJSONString(rv);
	}
	
}