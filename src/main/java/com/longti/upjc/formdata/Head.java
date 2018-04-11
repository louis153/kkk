package com.longti.upjc.formdata;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息响应头
 * @author 杨阳
 * 201706291523
 */
public class Head implements IHead{
	
	
	private String appkey;//需要放到请求头中，用于钱包接口查询对应的appSecret来解密数据。

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	@Override
	public List<RequestProperty> getProperty() {
		List<RequestProperty> lstProperty=new ArrayList<RequestProperty>();
		RequestProperty appkeyProperty=new RequestProperty();
		appkeyProperty.key="appkey";
		appkeyProperty.value=appkey;		
		lstProperty.add(appkeyProperty);
		return lstProperty;
	}	
	
}
