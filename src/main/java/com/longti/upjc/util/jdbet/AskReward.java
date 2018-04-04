package com.longti.upjc.util.jdbet;

public class AskReward {
	public String ownerIdentityData;         //用户钱包标识
	public long ownerUpUid;                  //uplive账户uid [可选] (根据业务)
	public String amount;                    //gto数量，必须为正数
	public String supporterIdentityData;     //参与交易支持者，只做记录不做运算，例如：足彩 "uplive", 表示由uplive奖励
	public long supporterUpUid;              //支持者uplive账户uid [可选] (根据业务)
	public String transactionId;             //订单交易号，业务_唯一码
	public String businessType;              //平台对接业务类型，每个appId下子业务

}
