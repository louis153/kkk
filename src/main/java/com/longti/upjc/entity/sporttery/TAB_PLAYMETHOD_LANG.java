package com.longti.upjc.entity.sporttery;

import java.io.Serializable;

/**
 * 话题竞猜玩法信息语言字典entity
 */
public class TAB_PLAYMETHOD_LANG  implements Serializable {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 315808144853520830L;
	private int id;
	private String lang;
	private String key;
	private String value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
	
	
	
	
	
}
