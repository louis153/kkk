package com.longti.upjc.util.jdbet;

import java.util.ArrayList;
import java.util.List;

public class RvReward {
	
	
	public static class Data
	{
		
		public String identityData; 
	    public String firstname; 
	    public String lastname; 
	    public List<Wallet> wallets=new ArrayList<Wallet>();

	}
	public String code="";
	public Data data=new Data();
	public String message="";
	
}
