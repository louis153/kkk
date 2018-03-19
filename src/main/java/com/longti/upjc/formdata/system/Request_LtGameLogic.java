package com.longti.upjc.formdata.system;

public class Request_LtGameLogic {
	private Long gameId=500L;//  Y 500
	private String feeType;//  Y 代币类型，GTO、ETH、UZ
	private Integer tranType;//  Y 交易类型，0 ：非下注接口 1： 下注接口
	private Integer gameSource;//  Y 游戏来源: 1.pc 2. 移动端APP 3.移动端M版 4.手Q 端 5.微信端
	private String userPin;//  Y 用户标示
	private String gameRequest ;//Y 游戏请求信息（游戏系统补充内容）
	private String userToken; //用户token
	private String lang;//语言
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	private String ext;// N 游戏扩展字段
	
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public Integer getTranType() {
		return tranType;
	}
	public void setTranType(Integer tranType) {
		this.tranType = tranType;
	}
	public Integer getGameSource() {
		return gameSource;
	}
	public void setGameSource(Integer gameSource) {
		this.gameSource = gameSource;
	}
	public String getUserPin() {
		return userPin;
	}
	public void setUserPin(String userPin) {
		this.userPin = userPin;
	}
	public String getGameRequest() {
		return gameRequest;
	}
	public void setGameRequest(String gameRequest) {
		this.gameRequest = gameRequest;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
}
