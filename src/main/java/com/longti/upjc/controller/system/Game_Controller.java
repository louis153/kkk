package com.longti.upjc.controller.system;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.formdata.system.Request_LtGameLogic;
import com.longti.upjc.formdata.system.Response_LtGameLogic;
import com.longti.upjc.strategy.impl.sporttery.GameLogicMethodFactory;



/**
 * 杨阳 2017-06-14 13:12
 */
@Controller
@RequestMapping("")
public class Game_Controller {
	protected final transient static Logger logger = LoggerFactory.getLogger(Game_Controller.class);
	@Autowired
	private GameLogicMethodFactory ctxMethod;
	/**
	 * 提交方法
	 *  100请求ctrl格式错误！
	 * @throws Exception 
	 */
	@RequestMapping(value = "gameLogic", method = RequestMethod.POST)	
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
		Request_LtGameLogic request_LtGameLogic=new Request_LtGameLogic();
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
		
		request_LtGameLogic.setExt(jsonObject.getString("ext"));
		request_LtGameLogic.setFeeType((jsonObject.get("feeType").toString()));
		request_LtGameLogic.setGameId(Long.parseLong(jsonObject.get("gameID").toString()));
		request_LtGameLogic.setGameRequest(jsonObject.get("gameRequest").toString());
		request_LtGameLogic.setTranType(Integer.valueOf(jsonObject.get("tranType").toString()));
		request_LtGameLogic.setGameSource(Integer.valueOf(jsonObject.get("gameSource").toString()));
		request_LtGameLogic.setUserPin(jsonObject.getString("userPin"));
		request_LtGameLogic.setUserToken(jsonObject.getString("userToken"));
		Response_LtGameLogic response_LtGameLogic=new Response_LtGameLogic();
		
		try {
			String gameRequest=java.net.URLDecoder.decode(request_LtGameLogic.getGameRequest(), "utf-8");
			logger.info("游戏逻辑gameLogic接收到的请求信息----->："+gameRequest,gameRequest);
			
			//meth = this.getClass().getDeclaredMethod(JSONObject.parseObject(gameRequest).get("method").toString(),String.class,request_LtGameLogic.getGameRequest().getClass());
			JSONObject jsonRequest=JSONObject.parseObject(gameRequest);
			logger.info("请求的方法："+jsonRequest.get("method").toString(),jsonRequest.get("method").toString());
			logger.info("访问的userPin:"+request_LtGameLogic.getUserPin(),request_LtGameLogic.getUserPin());
			
			String gameResponse=ctxMethod.doAction(jsonRequest.get("method").toString(),request_LtGameLogic ,jsonRequest);
			response_LtGameLogic.setGameResponse(gameResponse);
			response_LtGameLogic.setReturnCode(0);
			response_LtGameLogic.setReturnMsg("访问成功");
			logger.info("游戏逻辑gameLogic：访问成功----->");
		} catch (NoSuchMethodException e) {			
			logger.error("游戏逻辑gameLogic：访问的游戏逻辑接口不存在----->");
			throw new Exception("访问的游戏逻辑接口不存在");			
		} catch (SecurityException e) {
			logger.error("游戏逻辑gameLogic：没有访问的游戏逻辑的安全权限----->");
			throw new Exception("访问的游戏逻辑接口不存在");
		}
		
		String outStr=JSONObject.toJSONString(response_LtGameLogic);
		logger.info("游戏逻辑gameLogic调用成功,返回信息----->："+outStr);
		OutPrintStr(outStr,response);
	}
	
	/**
	 * 给京东返回信息
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
