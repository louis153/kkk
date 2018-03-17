package com.longti.upjc.formdata.sporttery;

public class ASK_Award {
	private String awardUsers;		//Uuid&&pin1&&fee1,uuid&&pin2&&fee2；uuid
									//标识为投注时的uuid，pin 标示用户，fee 标
									//示中奖金额（可为0，表示未中奖），若为多
									//个用户用逗号分隔
	
	private String gameResult;		//游戏当期开奖结果
	public String getAwardUsers() {
		return awardUsers;
	}
	public void setAwardUsers(String awardUsers) {
		this.awardUsers = awardUsers;
	}
	public String getGameResult() {
		return gameResult;
	}
	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}
}

