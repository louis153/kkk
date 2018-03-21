package com.longti.upjc.entity.sporttery;

import java.io.Serializable;

/**
 * 投注底注entity
 */
public class TAB_CHIP  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8011867313659202656L;
	private int id;//主键
	private String currency_type;//币种 GTO ETH UZ(U钻)
	private String options_one;//底注1的值
	private String options_two;//底注2的值
	private String options_three;//底注3的值
	private String options_four;//底注4的值
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCurrency_type() {
		return currency_type;
	}
	public void setCurrency_type(String currency_type) {
		this.currency_type = currency_type;
	}
	public String getOptions_one() {
		return options_one;
	}
	public void setOptions_one(String options_one) {
		this.options_one = options_one;
	}
	public String getOptions_two() {
		return options_two;
	}
	public void setOptions_two(String options_two) {
		this.options_two = options_two;
	}
	public String getOptions_three() {
		return options_three;
	}
	public void setOptions_three(String options_three) {
		this.options_three = options_three;
	}
	public String getOptions_four() {
		return options_four;
	}
	public void setOptions_four(String options_four) {
		this.options_four = options_four;
	}
	
	
	
}
