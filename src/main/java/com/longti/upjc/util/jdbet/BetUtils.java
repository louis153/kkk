package com.longti.upjc.util.jdbet;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.controller.system.Game_Controller;
import com.longti.upjc.entity.sporttery.V_ORDER;
import com.longti.upjc.formdata.Head;
import com.longti.upjc.formdata.Msg;
import com.longti.upjc.formdata.sporttery.ASK_Bet;
import com.longti.upjc.formdata.sporttery.GameAreaBet;
import com.longti.upjc.formdata.sporttery.RV;
import com.longti.upjc.formdata.sporttery.RV_Login;
import com.longti.upjc.util.DateUtils;
import com.longti.upjc.util.IOUtils;
import com.longti.upjc.util.Md5;
import com.longti.upjc.util.PostUtils;


public class BetUtils {
	protected final transient static Logger logger = LoggerFactory.getLogger(Game_Controller.class);
	public static String venderId;
	public static String gameId;
	public static String url;
	public static String key;
	public static String version;
	static{
		venderId=IOUtils.getConfigParam("jd.venderId", "send.properties");
		gameId=IOUtils.getConfigParam("jd.gameId", "send.properties");
		url=IOUtils.getConfigParam("jd.url", "send.properties");
		key=IOUtils.getConfigParam("jd.key", "send.properties");
		version="1.2";
	}
	
	/**
	 * 验证签名
	 * @param str_xml
	 * @param md5Sign
	 * @return
	 */
	@SuppressWarnings("unused")
	private static boolean check_sign(Head head){
		String checkStr= Md5.getDigest(venderId+gameId+head.getUuId().toLowerCase()+head.getTimestamp()+key);		
		return head.getMd().toLowerCase().equals(checkStr.toLowerCase());
	}
	/**
	 * 创建签名 
	 * @param str_xml
	 * @return
	 */
	private static void Set_sign(Head head){		
		String md= Md5.getDigest(venderId+gameId+head.getUuId()+head.getTimestamp()+key).toLowerCase();	
		head.setMd(md);
	}
	/**
	 * 生成报文头
	 * @param uuid
	 * @return
	 */
	private static Head Create_Head(String uuid){
		Head head=new Head();
		head.setGameId(gameId);
		head.setTimestamp(DateUtils.getDateToStr(new Date(), "yyyyMMddHHmmss"));
		head.setUuId(uuid);
		head.setVenderId(venderId);
		head.setVersion(version);
		Set_sign(head);
		return head;
	}
	public static RV_Login login(){
		RV_Login rv=null;
		try {
			String jsonStr=PostUtils.doPost(url+"/login", "{}");
			JSONObject obj = JSONObject.parseObject(jsonStr);
			//将json对象转换为java对象
			rv = (RV_Login)JSONObject.toJavaObject(obj,RV_Login.class);//将建json对象转换为RV_Login对象
		} catch (Exception e) {
			logger.error("测试login错误");
		}
		return rv;
	}
	
	public static RV Bet(V_ORDER order) throws Exception{
		Msg<RV> rv=new Msg<RV>();
		Msg<ASK_Bet> ask=new Msg<ASK_Bet>();
		ask.setBody(new ASK_Bet());
		ask.getBody().setBetFee(Long.valueOf(order.getBet_fee()));
		ask.getBody().setFeeType(4);
		GameAreaBet gameAreaBet=new GameAreaBet();
		gameAreaBet.setBetBatchNo(order.getIssume());
		ask.getBody().setGameAreaBet(JSONObject.toJSONString(gameAreaBet));
		ask.getBody().setGameSource(3);
		ask.getBody().setUserPin(order.getUser_pin());
		ask.setHead(Create_Head(order.getOrder_id()));
		try {
			String rvStr=PostUtils.doPostGZip(url+"game/bet/"+order.getUser_pin(),ask.getHead() ,JSONObject.toJSONString(ask.getBody()).toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			Head head=new Head();
			head.setGameId("");
			head.setMd("");
			head.setTimestamp("");
			head.setUuId("");
			head.setVenderId("");
			head.setVersion("1.2");
			rv.setHead(head);
			RV body=new RV();
			body.setCode(Integer.valueOf(((JSONObject)obj).getString("code")));
			body.setMsg(((JSONObject)obj).getString("msg"));
			body.setSuccess(Boolean.valueOf(((JSONObject)obj).getString("success")));
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问京东神豆接口抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
	
}
