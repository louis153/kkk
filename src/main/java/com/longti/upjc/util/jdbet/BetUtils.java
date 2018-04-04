package com.longti.upjc.util.jdbet;


import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.formdata.Head;
import com.longti.upjc.formdata.Msg;
import com.longti.upjc.formdata.sporttery.ASK_Token;
import com.longti.upjc.formdata.sporttery.RV_Balance;
import com.longti.upjc.formdata.sporttery.RV_Change;
import com.longti.upjc.formdata.sporttery.RV_Query;
import com.longti.upjc.formdata.sporttery.RV_Token;
import com.longti.upjc.util.IOUtils;
import com.longti.upjc.util.PostUtils;
import com.longti.upjc.util.StringUtil;


public class BetUtils {
	protected final transient static Logger logger = LoggerFactory.getLogger(BetUtils.class);
	public static Long preMul=1000000L;
	
	
	public static String TOKEN_VALID="valid";
	public static String TOKEN_INVALID="invalid";
	
	public static String 	up_detail=IOUtils.getConfigParam("up.detail", "up.properties");
	public static String 	up_reward=IOUtils.getConfigParam("up.reward", "up.properties");
	public static String 	up_consume=IOUtils.getConfigParam("up.consume", "up.properties");
	public static String 	up_check=IOUtils.getConfigParam("up.check", "up.properties");
	public static String 	up_checkToken=IOUtils.getConfigParam("up.checkToken", "up.properties");
	public static String 	up_appkey=IOUtils.getConfigParam("up.appkey", "up.properties");
	public static String 	up_appSecret=IOUtils.getConfigParam("up.appSecret", "up.properties");
	
	public static class ErrInfo{
		private String code;
		private String message;
		public ErrInfo(String strCode,String strMessage){
			this.code=(strCode);
			this.message=(strMessage);
		}
		public String getMessage() {
			return message;
		}
		public String getCode() {
			return code;
		}
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
		Msg<AskConsume> ask=new Msg<AskConsume>();
		ask.setBody(new AskConsume());
		ask.getBody().amount=bet_fee;
		ask.getBody().businessType="";
		ask.getBody().ownerIdentityData="uplive"+userPin;
		ask.getBody().transactionId=orderId;
		ask.getBody().supporterIdentityData="uplive";
		ask.getBody().supporterUpUid=0;	
		
		ask.setHead(Create_Head());
		try {
			String rvStr=PostUtils.doPostGZip(up_consume+"/"+electronic_code.toLowerCase(),ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setAppkey(up_appkey);
			rv.setHead(head);
			RvConsume rvConsume=new RvConsume();
			rvConsume.code=obj.getString("code");
			JSONObject data=(JSONObject)StringUtil.ifnull(obj.get("data"),new JSONObject());			
			rvConsume.data.firstname=StringUtil.ifnull(data.get("firstname"),"").toString();
			rvConsume.data.identityData=StringUtil.ifnull(data.get("identityData"),"").toString();
			rvConsume.data.lastname=StringUtil.ifnull(data.get("lastname"),"").toString();
			JSONArray wallets=(JSONArray)StringUtil.ifnull(data.get("wallets"),new JSONArray());	
			for(Object o :wallets){
				JSONObject w=(JSONObject)o;
				Wallet wallet=new Wallet();
				wallet.address=StringUtil.ifnull(w.get("address"),"").toString();
				wallet.blocked=StringUtil.ifnull(w.get("blocked"),"").toString();
				wallet.currencyCode=StringUtil.ifnull(w.get("currencyCode"),"").toString();
				wallet.offChained=StringUtil.ifnull(w.get("offChained"),"").toString();
				wallet.onChained=StringUtil.ifnull(w.get("onChained"),"").toString();
				rvConsume.data.wallets.add(wallet);
			}
			rvConsume.message=StringUtil.ifnull(obj.get("message"),SC_UNKNOWN.message).toString();
			RV_Change body=new RV_Change();
			
			for(Wallet wallet:rvConsume.data.wallets){
				if(wallet.currencyCode.equalsIgnoreCase("gto")){
					body.balance.GTO=wallet.offChained;
				}
				else if(wallet.currencyCode.equalsIgnoreCase("eth")){
					body.balance.ETH=wallet.offChained;
				}
				else if(wallet.currencyCode.equalsIgnoreCase("uz")){
					body.balance.UZ=wallet.offChained;
				}
			}
			
			if(rvConsume.code.equals(SC_SUCCESS.code)){
				body.status="success";
				body.message=SC_SUCCESS.message;
			}
			else{
				if(body.status.equals(SC_UNKNOWN.code)){
					RV_Query rv_Query= Query(orderId, userPin);
					rv.getBody().status=rv_Query.status;
					rv.getBody().message=rv_Query.message;
				}else{
					body.status="failed";
					logger.error(rvConsume.message+"----->");
					body.message=StringUtil.ifnull(ErrHash.get(rvConsume.code),SC_UNKNOWN).message;
				}
			}
			
			
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问亚创支付抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
	public static RV_Change Award(String userPin,String electronic_code,String orderId,String fee) throws Exception{
		Msg<RV_Change> rv=new Msg<RV_Change>();
		Msg<AskReward> ask=new Msg<AskReward>();
		ask.setBody(new AskReward());
		ask.getBody().amount=fee;
		ask.getBody().businessType="";
		ask.getBody().ownerIdentityData="uplive"+userPin;
		ask.getBody().transactionId=orderId;
		ask.getBody().supporterIdentityData="uplive";
		ask.getBody().supporterUpUid=0;	
		
		
		ask.setHead(Create_Head());
		try {
			String rvStr=PostUtils.doPostGZip(up_reward+"/"+electronic_code.toLowerCase(),ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setAppkey(up_appkey);
			rv.setHead(head);
			
			RvReward rvReward=new RvReward();
			rvReward.code=StringUtil.ifnull(obj.get("code"),"").toString();
			JSONObject data=(JSONObject)StringUtil.ifnull(obj.get("data"),new JSONObject());			
			rvReward.data.firstname=StringUtil.ifnull(data.get("firstname"),"").toString();
			rvReward.data.identityData=StringUtil.ifnull(data.get("identityData"),"").toString();
			rvReward.data.lastname=StringUtil.ifnull(data.get("lastname"),"").toString();
			JSONArray wallets=(JSONArray)StringUtil.ifnull(data.get("wallets"),new JSONArray());	
			for(Object o :wallets){
				JSONObject w=(JSONObject)o;
				Wallet wallet=new Wallet();
				wallet.address=StringUtil.ifnull(w.get("address"),"").toString();
				wallet.blocked=StringUtil.ifnull(w.get("blocked"),"").toString();
				wallet.currencyCode=StringUtil.ifnull(w.get("currencyCode"),"").toString();
				wallet.offChained=StringUtil.ifnull(w.get("offChained"),"").toString();
				wallet.onChained=StringUtil.ifnull(w.get("onChained"),"").toString();
				rvReward.data.wallets.add(wallet);
			}
			rvReward.message=StringUtil.ifnull(obj.get("message"),"").toString();
			RV_Change body=new RV_Change();
			
			for(Wallet wallet:rvReward.data.wallets){
				if(wallet.currencyCode.equalsIgnoreCase("gto")){
					body.balance.GTO=wallet.offChained;
				}
				else if(wallet.currencyCode.equalsIgnoreCase("eth")){
					body.balance.ETH=wallet.offChained;
				}
				else if(wallet.currencyCode.equalsIgnoreCase("uz")){
					body.balance.UZ=wallet.offChained;
				}
			}
			
			if(rvReward.code.equals(SC_SUCCESS.code)){
				body.status="success";
				body.message=SC_SUCCESS.message;
			}
			else{
				if(body.status.equals(SC_UNKNOWN.code)){
					RV_Query rv_Query= Query(orderId, userPin);
					rv.getBody().status=rv_Query.status;
					rv.getBody().message=rv_Query.message;
				}else{
					body.status="failed";
					logger.error(rvReward.message+"----->");
					body.message=StringUtil.ifnull(ErrHash.get(rvReward.code),SC_UNKNOWN).message;
				}
			}
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问亚创派奖接口抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
	
	public static RV_Balance Balance(String userPin,String electronic_code) throws Exception{
		Msg<RV_Balance> rv=new Msg<RV_Balance>();
		Msg<AskDetail> ask=new Msg<AskDetail>();
		ask.setBody(new AskDetail());
		ask.getBody().identityData="uplive"+userPin;
		
		
		ask.setHead(Create_Head());
		try {
			String rvStr=PostUtils.doPostGZip(up_detail+"/"+electronic_code.toLowerCase(),ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setAppkey(up_appkey);
			rv.setHead(head);
			RvDetail rvDetail=new RvDetail();
			rvDetail.code=StringUtil.ifnull(obj.get("code"),"").toString();
			JSONObject data=(JSONObject)StringUtil.ifnull(obj.get("data"),new JSONObject());
			rvDetail.data.firstname=StringUtil.ifnull(obj.get("firstname"),"").toString();
			rvDetail.data.identityData=StringUtil.ifnull(obj.get("identityData"),"").toString();
			rvDetail.data.lastname=StringUtil.ifnull(obj.get("lastname"),"").toString();
			
			JSONArray wallets= (JSONArray)StringUtil.ifnull(data.get("wallets"),new JSONArray());
			for(Object o:wallets){
				JSONObject w=(JSONObject)o;
				Wallet wallet=new Wallet();
				wallet.address=StringUtil.ifnull(w.get("address"),"").toString();
				wallet.blocked=StringUtil.ifnull(w.get("blocked"),"").toString();
				wallet.currencyCode=StringUtil.ifnull(w.get("currencyCode"),"").toString();
				wallet.offChained=StringUtil.ifnull(w.get("offChained"),"").toString();
				wallet.onChained=StringUtil.ifnull(w.get("offChained"),"").toString();
				rvDetail.data.wallets.add(wallet);
			}
			rvDetail.message=StringUtil.ifnull(obj.get("message"),"").toString();
			
			RV_Balance body=new RV_Balance();
			for(Wallet wallet:rvDetail.data.wallets){
				if(electronic_code.equalsIgnoreCase("ETH")){
					body.balance=wallet.offChained;	
				}
				else if(electronic_code.equalsIgnoreCase("GTO")){
					body.balance=wallet.offChained;	
				}
				else if(electronic_code.equalsIgnoreCase("UZ")){
					body.balance=wallet.offChained;
				}
			}
			if(rvDetail.code.equals(SC_SUCCESS.code)){
				body.status="success";
				body.message=SC_SUCCESS.message;
			}
			else{
				if(rvDetail.code.equals(SC_UNKNOWN.code)){
					
				}else{
					body.status="failed";
					logger.error(rvDetail.message+"----->");
					body.message=StringUtil.ifnull(ErrHash.get(rvDetail.code),SC_UNKNOWN).message;
				}
			}
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
	public static RV_Query Query(String transactionId,String userPin) throws Exception{
		Msg<RV_Query> rv=new Msg<RV_Query>();
		Msg<AskQuery> ask=new Msg<AskQuery>();
		ask.setBody(new AskQuery());
		ask.getBody().identityData=userPin;		
		ask.getBody().transactionId=transactionId;
		ask.setHead(Create_Head());
		try {
			String rvStr=PostUtils.doPostGZip(up_detail,ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setAppkey(up_appkey);
			rv.setHead(head);
			
			RvDetail rvDetail=new RvDetail();
			rvDetail.code=StringUtil.ifnull(obj.get("code"),"").toString();
			JSONObject data=(JSONObject)StringUtil.ifnull(obj.get("data"),new JSONObject());
			rvDetail.data.firstname=StringUtil.ifnull(data.get("firstname"),"").toString();
			rvDetail.data.identityData=StringUtil.ifnull(data.get("identityData"),"").toString();
			rvDetail.data.lastname=StringUtil.ifnull(data.get("lastname"),"").toString();
			JSONArray wallets=(JSONArray)StringUtil.ifnull(data.get("wallets"),new JSONArray());
			for(Object o:wallets){
				JSONObject w=(JSONObject)o;
				Wallet wallet=new Wallet();
				wallet.address=StringUtil.ifnull(w.get("address"),"").toString();
				wallet.blocked=StringUtil.ifnull(w.get("blocked"),"").toString();
				wallet.currencyCode=StringUtil.ifnull(w.get("currencyCode"),"").toString();
				wallet.offChained=StringUtil.ifnull(w.get("offChained"),"").toString();
				wallet.onChained=StringUtil.ifnull(w.get("onChained"),"").toString();
				rvDetail.data.wallets.add(wallet);
			}
			rvDetail.message=StringUtil.ifnull(obj.get("message"),"").toString();
			RV_Query body=new RV_Query();
			for(Wallet wallet:rvDetail.data.wallets){
				if(wallet.currencyCode.equalsIgnoreCase("gto")){
					body.balance.GTO=wallet.offChained;					
				}
				else if(wallet.currencyCode.equalsIgnoreCase("eth")){
					body.balance.ETH=wallet.offChained;
				}
				else if(wallet.currencyCode.equalsIgnoreCase("uz")){
					body.balance.UZ=wallet.offChained;
				}
			}
			if(rvDetail.code.equals(SC_SUCCESS.code)){
				body.status="success";
				body.message=SC_SUCCESS.message;
			}
			else{
				if(rvDetail.code.equals(SC_UNKNOWN.code)){
					
				}else{
					body.status="failed";
					logger.error(rvDetail.message+"----->");
					body.message=StringUtil.ifnull(ErrHash.get(rvDetail.code),SC_UNKNOWN).message;
				}
			}
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问亚创查询交易接口抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
	
	
	public static ErrInfo SC_SUCCESS=new ErrInfo("N000000", "成功");

	public static ErrInfo SC_UNKNOWN=new ErrInfo("E000000", "未知");

	public static ErrInfo SC_SYSTEM_ERROR=new ErrInfo("E000001", "系统错误");

	public static ErrInfo SC_PARAMETER_INVALID=new ErrInfo("E000002", "参数错误");

	/*-------------------E001000 up_mall------------------------*/
	public static ErrInfo UP_MALL_FAIL=new ErrInfo("E001001", "up_mall系统错误/超时");

	public static ErrInfo UP_MALL_UCOINS_NOT_ENOUGH=new ErrInfo("E001002", "用户u钻不足");

	public static ErrInfo UP_MALL_TRANSACTION_REPEATED=new ErrInfo("E001003", "系统交易订单重复");

	public static ErrInfo UP_MALL_TRANSACTION_NOT_EXIST=new ErrInfo("E001004", "系统交易订单不存在");

	public static ErrInfo SC_MALL_USER_FROZEN=new ErrInfo("E001005", "用户被冻结");


	/*-------------------E1002000 wallet -----------------------*/
	public static ErrInfo WALLET_BALANCE_NOT_ENOUGH=new ErrInfo("E002001", "钱包资金不足");

	public static ErrInfo WALLET_PASSPHRASE_NOT_CORRECT=new ErrInfo("E002002", "钱包支付密码不正确");

	public static ErrInfo WALLET_SENDER_IDENTITY_NOT_FOUNT=new ErrInfo("E002003", "转账钱包不存在");

	public static ErrInfo WALLET_RECEIVER_IDENTITY_NOT_FOUNT=new ErrInfo("E002004", "收账钱包不存在");

	public static ErrInfo WALLET_TRANSACTION_NOT_ACCEPTED=new ErrInfo("E002005", "钱包未受理交易");
	
	public static HashMap<String, ErrInfo> ErrHash = new HashMap<String, ErrInfo>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put(SC_SUCCESS.code, SC_SUCCESS);
			put(SC_UNKNOWN.code,SC_UNKNOWN);
			put(SC_SYSTEM_ERROR.code,SC_SYSTEM_ERROR);
			put(SC_PARAMETER_INVALID.code,SC_PARAMETER_INVALID);
			
			put(UP_MALL_FAIL.code,UP_MALL_FAIL);
			put(UP_MALL_UCOINS_NOT_ENOUGH.code,UP_MALL_UCOINS_NOT_ENOUGH);
			put(UP_MALL_TRANSACTION_REPEATED.code,UP_MALL_TRANSACTION_REPEATED);
			put(UP_MALL_TRANSACTION_NOT_EXIST.code,UP_MALL_TRANSACTION_NOT_EXIST);
			put(SC_MALL_USER_FROZEN.code,SC_MALL_USER_FROZEN);

			put(WALLET_BALANCE_NOT_ENOUGH.code,WALLET_BALANCE_NOT_ENOUGH);
			put(WALLET_PASSPHRASE_NOT_CORRECT.code,WALLET_PASSPHRASE_NOT_CORRECT);
			put(WALLET_SENDER_IDENTITY_NOT_FOUNT.code,WALLET_SENDER_IDENTITY_NOT_FOUNT);
			put(WALLET_RECEIVER_IDENTITY_NOT_FOUNT.code,WALLET_RECEIVER_IDENTITY_NOT_FOUNT);
			put(WALLET_TRANSACTION_NOT_ACCEPTED.code,WALLET_TRANSACTION_NOT_ACCEPTED);
		};
	
	};
}
