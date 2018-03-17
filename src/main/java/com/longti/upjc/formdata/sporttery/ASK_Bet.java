package com.longti.upjc.formdata.sporttery;

public class ASK_Bet {
	private Integer feeType;			//账户类型: 1：京豆，4 神豆。
	private Long betFee;				//投注金额
	private Integer gameSource;		//投注来源: 1.pc 2. 移动端APP 3.移动端M版 4.手Q 端 5.微信端
	private String gameAreaBet="";	//格式
	private String userPin;
	public Integer getFeeType() {
		return feeType;
	}
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	public Long getBetFee() {
		return betFee;
	}
	public void setBetFee(Long betFee) {
		this.betFee = betFee;
	}
	public Integer getGameSource() {
		return gameSource;
	}
	public void setGameSource(Integer gameSource) {
		this.gameSource = gameSource;
	}
	/*
	public GameAreaBet getGameAreaBet() {
		return gameAreaBet;
	}
	public void setGameAreaBet(GameAreaBet gameAreaBet) {
		this.gameAreaBet = gameAreaBet;
	}*/
	public String getUserPin() {
		return userPin;
	}
	public void setUserPin(String userPin) {
		this.userPin = userPin;
	}
	public String getGameAreaBet() {
		return gameAreaBet;
	}
	public void setGameAreaBet(String gameAreaBet) {
		this.gameAreaBet = gameAreaBet;
	}
}