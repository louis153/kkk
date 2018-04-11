package com.longti.upjc.util.jdbet;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.longti.upjc.formdata.IHead;
import com.longti.upjc.formdata.Msg;
import com.longti.upjc.formdata.RequestProperty;
import com.longti.upjc.formdata.sporttery.ASK_Token;
import com.longti.upjc.formdata.sporttery.RV_Token;
import com.longti.upjc.util.IOUtils;
import com.longti.upjc.util.PostUtils;
import com.longti.upjc.util.StringUtil;

public class TokenUtils {
	protected final transient static Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	public static final Object TOKEN_VALID = "true";
	public static String 	up_checkToken=IOUtils.getConfigParam("up.checkToken", "up.properties");
	public static String 	up_apiUser=IOUtils.getConfigParam("up.apiUser", "up.properties");
	public static String 	up_apiKey=IOUtils.getConfigParam("up.apiKey", "up.properties");
	
	public static class Head implements IHead{
		public String apiUser=up_apiUser;
		public String apiKey=up_apiKey;
		@Override
		public List<RequestProperty> getProperty() {
			List<RequestProperty> lstProperty=new ArrayList<RequestProperty>();
			RequestProperty userProperty=new RequestProperty();
			userProperty.key="apiUser";
			userProperty.value=apiUser;
			RequestProperty keyProperty=new RequestProperty();
			keyProperty.key="apiKey";
			keyProperty.value=apiKey;
			
			lstProperty.add(userProperty);
			lstProperty.add(keyProperty);
			return lstProperty;
		}
	}
	/**
	 * 生成报文头
	 * @param uuid
	 * @return
	 */
	private static Head Create_Head(){
		Head head=new Head();
		return head;
	}
	public static RV_Token CheckToken(String userToken) throws Exception{
		Msg<RV_Token> rv=new Msg<RV_Token>();
		Msg<ASK_Token> ask=new Msg<ASK_Token>();
		ask.setBody(new ASK_Token());
		ask.getBody().userToken=userToken;
		
		
		ask.setHead(Create_Head());
		try {
			StringBuffer sBuffer=new StringBuffer();
			JSONObject jsonObject=(JSONObject) JSONObject.toJSON(ask.getBody());
			for(String key:jsonObject.keySet()){
				sBuffer.append(key+"="+jsonObject.get(key).toString()+"&");
			}
			if(sBuffer.length()>0){
				sBuffer.deleteCharAt(sBuffer.length()-1);
			}
			String rvStr=PostUtils.doGet(up_checkToken,ask.getHead() ,sBuffer.toString());
			JSONObject obj=JSONObject.parseObject(rvStr);
			JSONObject userInfoObj=obj.getJSONObject("userDetailInfo");
			
			Head head=new Head();
			rv.setHead(head);
			RV_Token body=new RV_Token();
			if(userInfoObj!=null){
				body.userDetailInfo.uid=StringUtil.ifnull(userInfoObj.get("uid"), "").toString();
			    body.userDetailInfo.username=StringUtil.ifnull(userInfoObj.get("username"), "").toString();
			    body.userDetailInfo.upliveCode=StringUtil.ifnull(userInfoObj.get("upliveCode"), "").toString();
			    body.userDetailInfo.password=StringUtil.ifnull(userInfoObj.get("password"), "").toString();
			    body.userDetailInfo.mobilePhone=StringUtil.ifnull(userInfoObj.get("mobilePhone"), "").toString();
			    body.userDetailInfo.birthday=StringUtil.ifnull(userInfoObj.get("birthday"), "").toString();
			    body.userDetailInfo.userType=StringUtil.ifnull(userInfoObj.get("userType"), "").toString();
			    body.userDetailInfo.avatar=StringUtil.ifnull(userInfoObj.get("avatar"), "").toString();
				body.userDetailInfo.isThumbnailUploaded=StringUtil.ifnull(userInfoObj.get("isThumbnailUploaded"), "").toString();
			    body.userDetailInfo.signature=StringUtil.ifnull(userInfoObj.get("signature"), "").toString();
			    body.userDetailInfo.gender=StringUtil.ifnull(userInfoObj.get("gender"), "").toString();
			    body.userDetailInfo.deviceType=StringUtil.ifnull(userInfoObj.get("deviceType"), "").toString();
			    body.userDetailInfo.deviceId=StringUtil.ifnull(userInfoObj.get("deviceId"), "").toString();
			    body.userDetailInfo.source=StringUtil.ifnull(userInfoObj.get("source"), "").toString();
			    body.userDetailInfo.openId=StringUtil.ifnull(userInfoObj.get("openId"), "").toString();
			    body.userDetailInfo.token=StringUtil.ifnull(userInfoObj.get("token"), "").toString();
			    body.userDetailInfo.channel=StringUtil.ifnull(userInfoObj.get("channel"), "").toString();
			    body.userDetailInfo.userRealNameInfo=StringUtil.ifnull(userInfoObj.get("userRealNameInfo"), "").toString();
			    body.userDetailInfo.acceptAgreement=StringUtil.ifnull(userInfoObj.get("acceptAgreement"), "").toString();
				body.userDetailInfo.upliveCodeStatus=StringUtil.ifnull(userInfoObj.get("upliveCodeStatus"), "").toString();
			    body.userDetailInfo.pushType=StringUtil.ifnull(userInfoObj.get("pushType"), "").toString();
			    body.userDetailInfo.pushToken=StringUtil.ifnull(userInfoObj.get("pushToken"), "").toString();
			    body.userDetailInfo.status=StringUtil.ifnull(userInfoObj.get("status"), "").toString();
			    body.userDetailInfo.grade=StringUtil.ifnull(userInfoObj.get("grade"), "").toString();
			    body.userDetailInfo.oldGrade=StringUtil.ifnull(userInfoObj.get("oldGrade"), "").toString();
			    body.userDetailInfo.exp=StringUtil.ifnull(userInfoObj.get("exp"), "").toString();
			    body.userDetailInfo.isLogined=StringUtil.ifnull(userInfoObj.get("isLogined"), "").toString();
			    body.userDetailInfo.firstLoginTime=StringUtil.ifnull(userInfoObj.get("firstLoginTime"), "").toString();
			    body.userDetailInfo.lastTime=StringUtil.ifnull(userInfoObj.get("lastTime"), "").toString();
			    body.userDetailInfo.lastLoginIp=StringUtil.ifnull(userInfoObj.get("lastLoginIp"), "").toString();
			    body.userDetailInfo.updateTime=StringUtil.ifnull(userInfoObj.get("updateTime"), "").toString();
			    body.userDetailInfo.createTime=StringUtil.ifnull(userInfoObj.get("createTime"), "").toString();
			    body.userDetailInfo.setting=StringUtil.ifnull(userInfoObj.get("setting"), "").toString();
			    body.userDetailInfo.language=StringUtil.ifnull(userInfoObj.get("language"), "").toString();
			    body.userDetailInfo.osLanguage=StringUtil.ifnull(userInfoObj.get("osLanguage"), "").toString();
			    body.userDetailInfo.location=StringUtil.ifnull(userInfoObj.get("location"), "").toString();
				body.userDetailInfo.isLogout=StringUtil.ifnull(userInfoObj.get("isLogout"), "").toString();
			    body.userDetailInfo.isOnline=StringUtil.ifnull(userInfoObj.get("isOnline"), "").toString();
			    body.userDetailInfo.lastLiveTime=StringUtil.ifnull(userInfoObj.get("lastLiveTime"), "").toString();
			    body.userDetailInfo.userToken=StringUtil.ifnull(userInfoObj.get("userToken"), "").toString();
			    body.userDetailInfo.vipId=StringUtil.ifnull(userInfoObj.get("vipId"), "").toString();
			    body.userDetailInfo.vipName=StringUtil.ifnull(userInfoObj.get("vipName"), "").toString();
			    body.userDetailInfo.vipIcon=StringUtil.ifnull(userInfoObj.get("vipIcon"), "").toString();
			    body.userDetailInfo.vipLevel=StringUtil.ifnull(userInfoObj.get("vipLevel"), "").toString();
			    body.userDetailInfo.vipExpireTime=StringUtil.ifnull(userInfoObj.get("vipExpireTime"), "").toString();
			    body.userDetailInfo.diamond=StringUtil.ifnull(userInfoObj.get("diamond"), "").toString();
			    body.userDetailInfo.vipInfo=StringUtil.ifnull(userInfoObj.get("vipInfo"), "").toString();
			    body.userDetailInfo.officialAuth=StringUtil.ifnull(userInfoObj.get("officialAuth"), "").toString();
			    body.userDetailInfo.officialAuthContent=StringUtil.ifnull(userInfoObj.get("officialAuthContent"), "").toString();
			    body.userDetailInfo.officialAuthIcon=StringUtil.ifnull(userInfoObj.get("officialAuthIcon"), "").toString();
			    body.userDetailInfo.qualityAuth=StringUtil.ifnull(userInfoObj.get("qualityAuth"), "").toString();
			    body.userDetailInfo.countryCode=StringUtil.ifnull(userInfoObj.get("countryCode"), "").toString();
			    body.userDetailInfo.feature=StringUtil.ifnull(userInfoObj.get("feature"), "").toString();
			    body.userDetailInfo.registerIp=StringUtil.ifnull(userInfoObj.get("registerIp"), "").toString();
			    body.userDetailInfo.registerCountry=StringUtil.ifnull(userInfoObj.get("registerCountry"), "").toString();
			    body.userDetailInfo.phoneOs=StringUtil.ifnull(userInfoObj.get("phoneOs"), "").toString();
			    body.userDetailInfo.phoneModel=StringUtil.ifnull(userInfoObj.get("phoneModel"), "").toString();
			    body.userDetailInfo.version=StringUtil.ifnull(userInfoObj.get("version"), "").toString();
			    body.userDetailInfo.iosIdfv=StringUtil.ifnull(userInfoObj.get("iosIdfv"), "").toString();
			    body.userDetailInfo.build=StringUtil.ifnull(userInfoObj.get("build"), "").toString();
			    body.userDetailInfo.smServerId=StringUtil.ifnull(userInfoObj.get("smServerId"), "").toString();
			    body.userDetailInfo.newUserDueTime=StringUtil.ifnull(userInfoObj.get("newUserDueTime"), "").toString();
			    body.userDetailInfo.lastBroadcastTime=StringUtil.ifnull(userInfoObj.get("lastBroadcastTime"), "").toString();
			    body.userDetailInfo.userRealNameAuth=StringUtil.ifnull(userInfoObj.get("userRealNameAuth"), "").toString();
			}
			
			body.result=((JSONObject)obj).get("result").toString();
			rv.setBody(body);//将建json对象转换为RV_Login对象
			
		} catch (Exception e) {
			logger.error("访问亚创验证用户TOKEN接口抛出服务异常 错误信息："+(e.getMessage()==null?"":e.getMessage()));
			throw new Exception(e.getMessage());
		}
		
		return rv.getBody();
	}
}
