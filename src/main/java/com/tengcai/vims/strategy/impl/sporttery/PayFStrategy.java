/**
 * @author 杨阳
 * 2017-08-03 14:49
 */
package com.tengcai.vims.strategy.impl.sporttery;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.tengcai.vims.entity.sporttery.LOTO_F;
import com.tengcai.vims.entity.sporttery.LOTO_ORDER;
import com.tengcai.vims.entity.sporttery.T_LOTO_SIS_F;
import com.tengcai.vims.entity.sporttery.T_MARKET_CHANNEL;
import com.tengcai.vims.entity.sporttery.T_QUOTATION_CONTROL;
import com.tengcai.vims.entity.sporttery.V_ORDER;
import com.tengcai.vims.service.sporttery.LOTO_FNService;
import com.tengcai.vims.service.sporttery.T_LOTO_SIS_FService;
import com.tengcai.vims.service.sporttery.T_MARKET_CHANNELService;
import com.tengcai.vims.service.sporttery.T_QUOTATION_CONTROLService;
import com.tengcai.vims.service.sporttery.V_ORDERService;
import com.tengcai.vims.strategy.sporttery.IMethodStrategy;
import com.tengcai.vims.util.DateUtils;
import com.tengcai.vims.util.StringUtil;

/**
 * 足球支付
 * 
 * @return
 */
@Component("payF")
public class PayFStrategy implements IMethodStrategy {
	protected final transient static Logger logger = LoggerFactory.getLogger(PayFStrategy.class);
	@Autowired
	private LOTO_FNService lotoFNService;
	@Autowired
	private T_LOTO_SIS_FService t_LOTO_SIS_FService;
	@Autowired
	private T_QUOTATION_CONTROLService t_QUOTATION_CONTROLService;
	@Autowired
	private T_MARKET_CHANNELService t_MARKET_CHANNELService;
	@Autowired
	private V_ORDERService v_orderService;

	@Override
	public String doJsonMethod(String userPin, JSONObject jsonRequest) throws Exception {
		logger.info("payB开始调用足球支付接口doJsonMethod------>"+JSONObject.toJSONString(jsonRequest));
		LOTO_F qryF=new LOTO_F();
				
		
		JSONObject jsonMatchs=((JSONObject)jsonRequest.get("sel_matchs"));
		String[] sbIssues=new String[jsonMatchs.size()];
		String channel="1";
		if(StringUtil.isEmpty(jsonRequest.get("channel"))==false){
			channel=jsonRequest.get("channel").toString();//获取渠道标记
		}		
		T_MARKET_CHANNEL t_market_channel=new T_MARKET_CHANNEL();	
		t_market_channel.setChannelstate("1");
		t_market_channel.setChannelnum(Integer.valueOf(channel));
	    if(t_MARKET_CHANNELService.selectT_MARKET_CHANNELCount(t_market_channel)==0){
	    	channel="1";
	    };
		Iterator<String> issues=jsonMatchs.keySet().iterator();
		ArrayList<String> endMatchs=new ArrayList<String>();
		while (issues.hasNext()) { 
			endMatchs.add(issues.next());
        }
		int iIssue=0;
		for (String issu :endMatchs) { 
        	sbIssues[iIssue++]=issu;        	
        }
        
		
		qryF.setIssues(sbIssues);
        qryF.setEndtime(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
       
        List<LOTO_F> loto_Fs=lotoFNService.selectLOTO_FNList(qryF);
        T_LOTO_SIS_F qry_sis_f=new T_LOTO_SIS_F();
        qry_sis_f.setIssues(sbIssues);
        List<T_LOTO_SIS_F> lsT_LOTO_SIS_F= t_LOTO_SIS_FService.selectT_LOTO_SIS_FList(qry_sis_f);
        for(LOTO_F f:loto_Fs){
        	if(endMatchs.indexOf(f.getIssue())>=0){
        		endMatchs.remove(f.getIssue());
        	}
        	
        }
        HashMap<String, Object> rv=new HashMap<String,Object>();
        if(endMatchs.isEmpty()==false){
        	rv.put("state", "000001");
            rv.put("endMatch", endMatchs);
            rv.put("msg", "已经有比赛截止投注了，<br/>请重新确认您的选择");
    		return JSONObject.toJSONString(rv);
        }
        new ArrayList<LOTO_ORDER>();
        new ArrayList<V_ORDER>();
        for(LOTO_F f:loto_Fs){
        	if(f.getStatus()==99){
        		rv.put("state", "000001");
    			endMatchs.clear();
    			endMatchs.add(f.getIssue());
                rv.put("endMatch", endMatchs);
                rv.put("msg", "比赛已经取消");
        		return JSONObject.toJSONString(rv);	
        	}
        }
        
        
        
        
        //判断玩法是否已经到达限制销售赔率----->开始
        for(LOTO_F f:loto_Fs){
        	
	        String issue = f.getIssue();
	        JSONObject m=jsonMatchs.getJSONObject(issue);
	        
	        if(m.containsKey("hh")||m.containsKey("hd")||m.containsKey("ha")){
	        	if(f.getHad_h().equals("1.01")||f.getHad_d().equals("1.01")||f.getHad_a().equals("1.01")){
	        		rv.put("state", "000001");
	    			endMatchs.clear();
	    			endMatchs.add(f.getIssue());
	                rv.put("endMatch", endMatchs);
	                rv.put("msg", "比赛已停止销售，请重新选择");
	        		return JSONObject.toJSONString(rv);	
	        	}
	        }
			if(m.containsKey("rh")||m.containsKey("rd")||m.containsKey("ra")){
				if(f.getHhad_h().equals("1.01")||f.getHhad_d().equals("1.01")||f.getHhad_a().equals("1.01")){
	        		rv.put("state", "000001");
	    			endMatchs.clear();
	    			endMatchs.add(f.getIssue());
	                rv.put("endMatch", endMatchs);
	                rv.put("msg", "比赛已停止销售，请重新选择");
	        		return JSONObject.toJSONString(rv);	
	        	}
			}
        }        
        
        T_QUOTATION_CONTROL quotation_control=new T_QUOTATION_CONTROL();
        quotation_control.setId(2);
    	quotation_control=  	t_QUOTATION_CONTROLService.selectT_QUOTATION_CONTROLList(quotation_control).get(0);
    	
        //判断玩法是否已经到达限制销售赔率----->结束
        
        
        //判断玩法是否已经到达限额----->开始
        
        for(T_LOTO_SIS_F sis_f:lsT_LOTO_SIS_F){
        	
        	
	        String issue = sis_f.getIssue();
	        JSONObject m=jsonMatchs.getJSONObject(issue);
	        
	        if(m.containsKey("hh")||m.containsKey("hd")||m.containsKey("ha")){
	        	if(sis_f.getHad_a_d()+sis_f.getHad_d_d()+sis_f.getHad_h_d()+getNum(m, "hh")+getNum(m, "hd")+getNum(m, "ha")>quotation_control.getDcxssx_e()){
	        		rv.put("state", "000001");
	    			endMatchs.clear();
	    			endMatchs.add(sis_f.getIssue());
	                rv.put("endMatch", endMatchs);
	                rv.put("msg", "投注超出上限，请重新选择");
	        		return JSONObject.toJSONString(rv);	
	        	}
	        }
			if(m.containsKey("rh")||m.containsKey("rd")||m.containsKey("ra")){
				if(sis_f.getHhad_a_d()+sis_f.getHhad_d_d()+sis_f.getHhad_h_d()+getNum(m, "rh")+getNum(m, "rd")+getNum(m, "ra")>quotation_control.getDcxssx_e()){
	        		rv.put("state", "000001");
	    			endMatchs.clear();
	    			endMatchs.add(sis_f.getIssue());
	                rv.put("endMatch", endMatchs);
	                rv.put("msg", "投注超出上限，请重新选择");
	        		return JSONObject.toJSONString(rv);	
	        	}
			}
        }
        //判断玩法是否已经到达限额----->结束
       
        
        
        StringBuffer sbFalse=new StringBuffer();
        boolean hasSuccess=false;
        for(LOTO_F f:loto_Fs){
	        String order_id=UUID.randomUUID().toString().replace("-", "");
	        String issue = f.getIssue();
	        JSONObject m=jsonMatchs.getJSONObject(issue);
	        Iterator<String> ls= m.keySet().iterator();
	        Date nowDate=new Date();
	        V_ORDER vOrder=new V_ORDER();
	        vOrder.setBet_fee(0);
	        String bet_type="";
	        List<LOTO_ORDER> lstTemp=new ArrayList<LOTO_ORDER>();
	        while(ls.hasNext()){	        	
	        	String bet_info="";
	        	String key=ls.next();
	        	double odd=0;
	        	if(key.equals("l"))
	        		continue;
	        	if(key.equals("hh")){
	        		bet_type="301";
	        		bet_info="had_h|"+f.getHad_h();
	        		if(StringUtil.isEmpty(f.getHad_h())){//如果赔率为空，设置投注赔率为1
	        			odd=1;
	        		}else{
	        			odd=Double.parseDouble(f.getHad_h());
	        		}
	        	}
	        	else if(key.equals("hd")){
	        		bet_type="301";
	        		bet_info="had_d|"+f.getHad_d();
	        		if(StringUtil.isEmpty(f.getHad_d())){//如果赔率为空，设置投注赔率为1
	        			odd=1;
	        		}else{
	        			odd=Double.parseDouble(f.getHad_d());
	        		}
	        	}
	        	else if(key.equals("ha")){
	        		bet_type="301";
	        		bet_info="had_a|"+f.getHad_a();
	        		if(StringUtil.isEmpty(f.getHad_a())){//如果赔率为空，设置投注赔率为1
	        			odd=1;
	        		}else{
	        			odd=Double.parseDouble(f.getHad_a());
	        		}
	        	}
	        	else if(key.equals("rh")){
	        		bet_type="305";
	        		bet_info="hhad_h|"+f.getHhad_h();
	        		if(StringUtil.isEmpty(f.getHhad_h())){//如果赔率为空，设置投注赔率为1
	        			odd=1;
	        		}else{
	        			odd=Double.parseDouble(f.getHhad_h());
	        		}
	        	}
	        	else if(key.equals("rd")){
	        		bet_type="305";
	        		bet_info="hhad_d|"+f.getHhad_d();
	        		if(StringUtil.isEmpty(f.getHhad_d())){//如果赔率为空，设置投注赔率为1
	        			odd=1;
	        		}else{
	        			odd=Double.parseDouble(f.getHhad_d());
	        		}
	        	}
	        	else if(key.equals("ra")){
	        		bet_type="305";
	        		bet_info="hhad_a|"+f.getHhad_a();
	        		if(StringUtil.isEmpty(f.getHhad_a())){//如果赔率为空，设置投注赔率为1
	        			odd=1;
	        		}else{
	        			odd=Double.parseDouble(f.getHhad_a());
	        		}
	        	}
	        	
		        LOTO_ORDER loto_ORDER=new LOTO_ORDER();
		        loto_ORDER.setIssue(issue);
		        loto_ORDER.setOrder_id(order_id);
		        loto_ORDER.setBet_fee(Integer.valueOf(((JSONObject)m.get(key)).get("num").toString()));
		        vOrder.setBet_fee(vOrder.getBet_fee()+loto_ORDER.getBet_fee());
		        loto_ORDER.setBet_info(bet_info);
				loto_ORDER.setBet_type(Integer.valueOf(bet_type));
				loto_ORDER.setBet_status(1);
				loto_ORDER.setCreate_time(nowDate);
				loto_ORDER.setPrize_status(1);
				loto_ORDER.setPrize_type(1);				
				loto_ORDER.setUser_pin(userPin);
				loto_ORDER.setMemo(jsonRequest.get("user_pin").toString());
				loto_ORDER.setVsteam(f.getHome_team_name()+"vs"+f.getGuest_team_name());
				loto_ORDER.setWin_fee(Integer.parseInt(String.valueOf(Math.round(loto_ORDER.getBet_fee()*odd))));
				loto_ORDER.setVsresult("");
				loto_ORDER.setOrder_source(channel);
				loto_ORDER.setPrize_cancel_time(DateUtils.getDateToStr("1900-1-1"));
				lstTemp.add(loto_ORDER);
				logger.info(String.format("开始调用Bet接口订单信息：order_id:%s,userPin:%s,bet_info:%s,bet_fee:%s",loto_ORDER.getOrder_id(),loto_ORDER.getUser_pin(),bet_info,loto_ORDER.getBet_fee()));
	        }
	        
	        vOrder.setBet_status(1);
	        vOrder.setBet_type(Integer.parseInt(bet_type));
	        vOrder.setCreate_time(DateUtils.getDateToStr(nowDate,"yyyy-MM-dd HH:mm:ss"));
	        vOrder.setOrder_id(order_id);
	        vOrder.setUser_pin(userPin);
	        vOrder.setPrize_status(1);
	        vOrder.setWin_fee(0);
	        vOrder.setIssume(issue);
	        vOrder.setVsteam(f.getHome_team_name()+"vs"+f.getGuest_team_name());
	        
	        
	        try{
	        	v_orderService.insertV_ORDER(vOrder, lstTemp,null,null,null);
	        	hasSuccess=true;
	        }catch(Exception e){//事物中的处理错误必须已异常的方式返回
	        	sbFalse.append(vOrder.getVsteam()+"错误："+e.getMessage()+"<br/>");
	        }
	        
	        lstTemp=null;
        }
        if(sbFalse.length()>0){
	        rv.put("state", "000001");
	        rv.put("endMatch", endMatchs);
	        if(hasSuccess){
	        	rv.put("msg", "以下比赛没有支付成功:<br/>"+sbFalse.toString());
	        }else{
	        	rv.put("msg", "支付失败");
	        }
        	logger.info("调用足球支付接口失败----->");
			return JSONObject.toJSONString(rv);
        }else{
        	 rv.put("state", "000000");
             rv.put("endMatch", endMatchs);
             rv.put("msg", "支付成功");
        }
        logger.info("调用足球订单支付接口成功----->");
		return JSONObject.toJSONString(rv);
	}
	private int getNum(JSONObject m,String key){
		if(m.containsKey(key)){
			JSONObject numObj=(JSONObject)m.get(key);
			if(numObj.containsKey("num")){
				return Integer.parseInt(numObj.get("num").toString());
			}else{
				return 0;
			}
		}else{
			return 0;
		}		
	}
}