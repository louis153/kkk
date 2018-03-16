package com.tengcai.vims.util;

public class Odds {
	private String odd_name;
	private String odd_value;
	private String odd_per;
	public Odds(String name,String value,String per){
		odd_name=name;
		odd_value=value;
		setOdd_per(per);
	}
	public String getOdd_name() {
		return odd_name;
	}
	public void setOdd_name(String odd_name) {
		this.odd_name = odd_name;
	}
	public String getOdd_value() {
		return odd_value;
	}
	public void setOdd_value(String odd_value) {
		this.odd_value = odd_value;
	}
	public String getOdd_per() {
		return odd_per;
	}
	public void setOdd_per(String odd_per) {
		this.odd_per = odd_per;
	}
	
}
