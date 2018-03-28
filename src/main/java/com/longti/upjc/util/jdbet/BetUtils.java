package com.longti.upjc.util.jdbet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.formdata.Head;
import com.longti.upjc.formdata.Msg;
import com.longti.upjc.formdata.sporttery.ASK_Balance;
import com.longti.upjc.formdata.sporttery.ASK_Change;
import com.longti.upjc.formdata.sporttery.ASK_Query;
import com.longti.upjc.formdata.sporttery.ASK_Token;
import com.longti.upjc.formdata.sporttery.RV_Balance;
import com.longti.upjc.formdata.sporttery.RV_Change;
import com.longti.upjc.formdata.sporttery.RV_Query;
import com.longti.upjc.formdata.sporttery.RV_Token;
import com.longti.upjc.util.IOUtils;
import com.longti.upjc.util.PostUtils;


public class BetUtils {
	protected final transient static Logger logger = LoggerFactory.getLogger(BetUtils.class);
	public static Long preMul=1000000L;
	public static String up_balance;
	public static String up_change;
	public static String up_query;
	public static String up_appkey;
	public static String up_appSecret;
	public static String up_checkToken;
	
	public static String TOKEN_VALID="valid";
	public static String TOKEN_INVALID="invalid";
	
	static{
		up_balance=IOUtils.getConfigParam("up.balance", "up.properties");
		up_change=IOUtils.getConfigParam("up.change", "up.properties");
		up_query=IOUtils.getConfigParam("up.query", "up.properties");
		up_appkey=IOUtils.getConfigParam("up.appkey", "up.properties");
		up_appSecret=IOUtils.getConfigParam("up.appSecret", "up.properties");
		up_checkToken=IOUtils.getConfigParam("up.checkToken", "up.properties");
	}
	
	/**
	 * 验证签名
	 * @param str_xml
	 * @param md5Sign
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean check_sign(Head head){
		return true;
	}
	/**
	 * 创建签名 
	 * @param str_xml
	 * @return
	 */
	private static void Set_sign(Head head){		
		
	}
	/**
	 * 生成报文头
	 * @param uuid
	 * @return
	 */
	private static Head Create_Head(){
		Head head=new Head();
		head.setAppkey(up_appkey);
		Set_sign(head);
		return head;
	}
	
	
	public static RV_Change Bet(String userPin,String electronic_code,String orderId,String bet_fee,String password) throws Exception{
		Msg<RV_Change> rv=new Msg<RV_Change>();
		Msg<ASK_Change> ask=new Msg<ASK_Change>();
		ask.setBody(new ASK_Change());
		ask.getBody().amount=bet_fee;
		ask.getBody().currencyCode=electronic_code.toUpperCase();
		ask.getBody().passphrase=password;
		ask.getBody().transactionId=orderId;
		ask.getBody().type="sub";
		ask.getBody().walletId="uplive"+userPin;
		
		
		ask.setHead(Create_Head());
		try {
			String rvStr=PostUtils.doPostGZip(up_change,ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setAppkey(up_appkey);
			rv.setHead(head);
			RV_Change body=new RV_Change();
			JSONObject jsonBalance=(JSONObject)((JSONObject)obj).get("balance");
			body.balance.ETH=jsonBalance.getString("ETH");
			body.balance.GTO=jsonBalance.getString("GTO");
			body.status="success";
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问亚创支付抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
	public static RV_Change Award(String userPin,String electronic_code,String orderId,String bet_fee) throws Exception{
		Msg<RV_Change> rv=new Msg<RV_Change>();
		Msg<ASK_Change> ask=new Msg<ASK_Change>();
		ask.setBody(new ASK_Change());
		ask.getBody().amount=bet_fee;
		ask.getBody().currencyCode=electronic_code.toUpperCase();
		ask.getBody().transactionId=orderId;
		ask.getBody().type="add";
		ask.getBody().walletId="uplive"+userPin;
		
		
		ask.setHead(Create_Head());
		try {
			String rvStr=PostUtils.doPostGZip(up_change,ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setAppkey(up_appkey);
			rv.setHead(head);
			RV_Change body=new RV_Change();
			JSONObject jsonBalance=(JSONObject)((JSONObject)obj).get("balance");
			body.balance.ETH=jsonBalance.getString("ETH");
			body.balance.GTO=jsonBalance.getString("GTO");
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问亚创派奖接口抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
	
	public static RV_Balance Balance(String userPin,String electronic_code) throws Exception{
		Msg<RV_Balance> rv=new Msg<RV_Balance>();
		Msg<ASK_Balance> ask=new Msg<ASK_Balance>();
		ask.setBody(new ASK_Balance());
		ask.getBody().walletId="uplive"+userPin;
		ask.getBody().currencyCode=electronic_code.toUpperCase();
		
		
		ask.setHead(Create_Head());
		try {
			String rvStr=PostUtils.doPostGZip(up_balance,ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setAppkey(up_appkey);
			rv.setHead(head);
			RV_Balance body=new RV_Balance();
			JSONObject jsonBalance=(JSONObject)((JSONObject)obj).get("balance");
			body.balance=jsonBalance.getString("ETH");
			body.balance=jsonBalance.getString("GTO");
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问亚创余额接口抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
	public static RV_Token CheckToken(String userToken) throws Exception{
		Msg<RV_Token> rv=new Msg<RV_Token>();
		Msg<ASK_Token> ask=new Msg<ASK_Token>();
		ask.setBody(new ASK_Token());
		ask.getBody().userToken=userToken;
		
		
		ask.setHead(Create_Head());
		try {
			String rvStr=PostUtils.doPostGZip(up_checkToken,ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setAppkey(up_appkey);
			rv.setHead(head);
			RV_Token body=new RV_Token();
			body.status=((JSONObject)obj).get("status").toString();
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问亚创验证用户TOKEN接口抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
	public static RV_Query Query(String transactionId) throws Exception{
		Msg<RV_Query> rv=new Msg<RV_Query>();
		Msg<ASK_Query> ask=new Msg<ASK_Query>();
		ask.setBody(new ASK_Query());
		ask.getBody().transactionId=transactionId;		
		
		ask.setHead(Create_Head());
		try {
			String rvStr=PostUtils.doPostGZip(up_query,ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setAppkey(up_appkey);
			rv.setHead(head);
			RV_Query body=new RV_Query();
			JSONObject jsonBalance=(JSONObject)((JSONObject)obj).get("balance");
			body.balance.ETH=jsonBalance.getString("ETH");
			body.balance.GTO=jsonBalance.getString("GTO");
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问亚创查询交易接口抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
}
