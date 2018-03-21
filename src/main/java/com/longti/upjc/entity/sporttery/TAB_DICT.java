package com.longti.upjc.entity.sporttery;

import java.io.Serializable;
import java.util.Date;

/**
 * 奖励机制entity
 */
public class TAB_DICT  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4486236233364110947L;
	
	private int id;//主键
	private int type;//字典类型 1.绑定邀请码获得GTO数, 2邀请用户中奖比例(GTO), 3 (ETH), 4(U钻)
	private String value;//字典值
	private Date create_time;//创建时间
	private int available;//是否可用 ,0 否1是
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	
	
	
	
}
