package com.longti.upjc.util.jdbet;

import java.util.ArrayList;
import java.util.List;

public class RvDetail {
		
	public static class Data
	{
		
		public String identityData="";//用户钱包标识
		public String	firstname="";//创建钱包用户名
		public String lastname="";//创建钱包用户名
		public 	List<Wallet> wallets=new ArrayList<Wallet>();//钱包资产数组，支持多种货币
	}
	public String code="";
	public Data data=new Data();
	public String message="";
	
}
