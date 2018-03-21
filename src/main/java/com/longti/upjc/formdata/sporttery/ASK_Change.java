package com.longti.upjc.formdata.sporttery;

public class ASK_Change {
	public String type="";// '交易类型',  可选值: add 加币 sub 减币
	public String walletId="";// 例如：'uplive2222',规则同上
	public String transactionId="";// 钱包交易订单号, 有固定规则生成
	public String passphrase="";// 用户钱包支付密码
	public String currencyCode="";// 'GTO' 货币代码，支持GTO/ETH
	public String amount="";// 交易数量
}

