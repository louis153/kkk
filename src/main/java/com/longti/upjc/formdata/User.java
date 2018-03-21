package com.longti.upjc.formdata;

public class User {
	private String userPin;//用户ID
	private String userToken;//用户token
	private String nick_name;//用户昵称
	public User(String userPin,String userToken){
		
	}
	public String getUserPin() {
		return userPin;
	}
	public void setUserPin(String userPin) {
		this.userPin = userPin;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
}
