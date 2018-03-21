package com.longti.upjc.controller.system;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.ibm.icu.math.BigDecimal;



/**
 * 杨阳 2017-06-14 13:12
 */
@Controller
@RequestMapping("")
public class Test_Controller {
	protected final transient static Logger logger = LoggerFactory.getLogger(Test_Controller.class);
	
	public static Map<String,Map<String,BigDecimal>> walletMap=new HashMap<String,Map<String,BigDecimal>>();
	public static Map<String,String> orderMap=new HashMap<String,String>(); 
	public static Map<String, BigDecimal> getWalletMap(String walletId){
		if(walletMap.containsKey(walletId)){
			return walletMap.get(walletId);
		}else{
			HashMap<String,BigDecimal> map=new HashMap<String,BigDecimal>();
			map.put("GTO", new BigDecimal("2000000"));
			map.put("ETH", new BigDecimal("20000"));
			map.put("UZ", new BigDecimal("2000000"));
			walletMap.put(walletId, map);
			return map;
		}
	}
	/**
	 * 测试求余额方法
	 * @throws Exception 
	 */
	@RequestMapping(value = "balance", method = RequestMethod.POST)	
	public void balance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("访问gamelogic发生编码错误");
		}
		response.setCharacterEncoding("UTF-8");
		//获取post参数 
		InputStream is=null;
		try {
			is = request.getInputStream();
		} catch (IOException e1) {
			logger.error("访问gamelogic发生IO错误："+e1.getMessage());
		} 
		
		String strJson = ""; 
		JSONObject jsonObject=null;
		try {
		   try {
			   
			   strJson=(org.apache.commons.io.IOUtils.toString(is));
			   
		   } catch (IOException e) {
			   logger.info("调用gameLogic接口失败----->");
			   
		   }
		   jsonObject=JSONObject.parseObject(strJson);
		  
		} finally {
			 org.apache.commons.io.IOUtils.closeQuietly(is);
		}	
		JSONObject rv=new JSONObject();
		rv.put("status", "success");
		
		rv.put("balance", getWalletMap(jsonObject.get("walletId").toString()));
		
		String outStr=JSONObject.toJSONString(rv);
		OutPrintStr(outStr,response);
	}
	private void changeGto(String currencyCode,String type,String change,String walletId){
		BigDecimal v=new BigDecimal(change);
		Map<String, BigDecimal> wMap=getWalletMap(walletId);
		if(type.equals("add")){
		  	wMap.get(currencyCode).add(v);
		}
		else if(type.equals("sub")){
		   	wMap.get(currencyCode).subtract(v);
		}		
	}
	/**
	 * 测试求交易方法
	 * @throws Exception 
	 */
	@RequestMapping(value = "change", method = RequestMethod.POST)	
	public void gameLogic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("访问gamelogic发生编码错误");
		}
		response.setCharacterEncoding("UTF-8");
		//获取post参数 
		InputStream is=null;
		try {
			is = request.getInputStream();
		} catch (IOException e1) {
			logger.error("访问gamelogic发生IO错误："+e1.getMessage());
		} 
		
		String strJson = ""; 
		JSONObject jsonObject=null;
		try {
		   try {
			   
			   strJson=(org.apache.commons.io.IOUtils.toString(is));
			   
		   } catch (IOException e) {
			   logger.info("调用gameLogic接口失败----->");
			   
		   }
		   jsonObject=JSONObject.parseObject(strJson);
		  
		} finally {
			 org.apache.commons.io.IOUtils.closeQuietly(is);
		}	
		String currencyCode=jsonObject.getString("currencyCode");
		String type=jsonObject.getString("type");
		String change=jsonObject.getString("amount");
		String walletId=jsonObject.getString("walletId");
		String transactionId=jsonObject.getString("transactionId");
		orderMap.put(transactionId, walletId);
		changeGto(currencyCode, type, change, walletId);
		JSONObject rv=new JSONObject();
		rv.put("status", "success");
		
		rv.put("balance", getWalletMap(jsonObject.get("walletId").toString()));
		
		String outStr=JSONObject.toJSONString(rv);
		OutPrintStr(outStr,response);
	}
	/**
	 * 测试求余额方法
	 * @throws Exception 
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)	
	public void query(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			logger.error("访问gamelogic发生编码错误");
		}
		response.setCharacterEncoding("UTF-8");
		//获取post参数 
		InputStream is=null;
		try {
			is = request.getInputStream();
		} catch (IOException e1) {
			logger.error("访问gamelogic发生IO错误："+e1.getMessage());
		} 
		
		String strJson = ""; 
		JSONObject jsonObject=null;
		try {
		   try {
			   
			   strJson=(org.apache.commons.io.IOUtils.toString(is));
			   
		   } catch (IOException e) {
			   logger.info("调用gameLogic接口失败----->");
			   
		   }
		   jsonObject=JSONObject.parseObject(strJson);
		  
		} finally {
			 org.apache.commons.io.IOUtils.closeQuietly(is);
		}	
		JSONObject rv=new JSONObject();
		rv.put("status", "success");
		rv.put("transactionStatus", "success");
		String transactionId=jsonObject.get("transactionId").toString();
		String walletId=orderMap.get(transactionId);
		rv.put("balance", getWalletMap(walletId));
		
		String outStr=JSONObject.toJSONString(rv);
		OutPrintStr(outStr,response);
	}
	/**
	 * 给前台返回信息
	 * @param str
	 * @return
	 * 
	 */
	private void OutPrintStr(String str,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = null;
		
		try {
			out = new PrintWriter(response.getOutputStream());// 发送请求参数
	        out.print(str);//使用url编码
	        // flush输出流的缓冲	        
	        out.flush();
		} catch (IOException e) {
			logger.error("调用统一输出方法失败----->");			
		} finally{
			if(out!=null)
				out.close();
		}
        
        
	}
		
	
	
	
	
	
	
}
