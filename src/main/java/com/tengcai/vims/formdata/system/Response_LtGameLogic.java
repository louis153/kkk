package com.tengcai.vims.formdata.system;

public class Response_LtGameLogic {
	private Integer returnCode;//返回值
	private String returnMsg;//返回消息
	private String gameResponse;//游戏系统定义
	public Integer getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(Integer returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getGameResponse() {
		return gameResponse;
	}
	public void setGameResponse(String gameResponse) {
		this.gameResponse = gameResponse;
	}
}
