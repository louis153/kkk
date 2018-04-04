package com.longti.upjc.util.jdbet;



public class RvQuery {

	public static class Data {
		public String appId = "";// "giftomall10000",
		public String changeAmount = "";
		public String changeUcoins = "0";
		public int createTime = 0;
		public String currencyCode = "GTO";
		public String currencyPriceUsd = "0";
		public String currentOffchain = "8358222.0289";
		public String currentOnchain = "0.0";
		public String flowName = "?????????";
		public Integer flowType = 8;
		public Integer id = 0;
		public String ownerIdentityData = "";
		public String ownerWalletAddress = "0xd7a591edc67aba530e069e073cb2b37793d0fd0b";
		public String resultCode = "N000000";
		public String resultMsg = "??";
		public Integer status = 2; // 0 受理 1 处理中 2 成功 3 失败 4 停止
		public String supporterIdentityData = "";
		public String supporterWalletAddress = "";
		public String transactionId = "";
		public String transferFee = "0.25";
		public String transferType = "offchain";
		public String txhash = "";
		public String ucoinsPriceUsd = "0";
		public Integer uid = 0;
		public int updateTime = 0;
	}

	public String code = "N000000";
	public Data data = new Data();
	public String message = "";

}
