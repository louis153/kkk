package com.longti.upjc.util;

import java.util.HashMap;

import com.cloopen.rest.sdk.CCPRestSDK;


public class SmsUtils {
	private static String up_fore = "8aaf0708624670f201626010e0c707c3";// 亚创猜球
	@SuppressWarnings("serial")
	public static HashMap<String, String> SmsHash = new HashMap<String, String>() {
		{
			put("240505", up_fore);// 修改手机号验证提示
			
		}
	};

	/**
	 * 发送短信 
	 * @param phone_code  电话号码
	 * @param model_id 模板编号
	 * @param strParam 参数数组
	 * @return 返回布尔值
	 */
	public static HashMap<String, Object> SendSms(String phone_code, String model_id, String[] strParam) {
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("aaf98f894ecd7d6a014ed77e13f70f2f", "059dddb94cbc4511936ced73398fa44d");// 初始化主帐号名称和主帐号令牌
		restAPI.setAppId(SmsUtils.SmsHash.get(model_id));// 初始化应用ID
		HashMap<String, Object> result = null;

		result = restAPI.sendTemplateSMS(phone_code, model_id, strParam);

		return result;
	}
	public static void main(String[] args) {
		SendSms("18088707289", "240505", new String[]{""});
	}
}
