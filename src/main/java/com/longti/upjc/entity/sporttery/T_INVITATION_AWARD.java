package com.longti.upjc.entity.sporttery;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀请码奖励entity
 */
public class T_INVITATION_AWARD  implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4440409485189166282L;
	private int id;
	private String user_pin;//用户pin
	private String contributor;//贡献者
	private String electronic_code;//币种
	private int win_fee;//获得奖金
	private Date create_time;//创建时间
	
	private int total;//合计金额	
    private Integer row_start;//开始行
	private Integer page_size;//每页行数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_pin() {
		return user_pin;
	}
	public void setUser_pin(String user_pin) {
		this.user_pin = user_pin;
	}
	public String getContributor() {
		return contributor;
	}
	public void setContributor(String contributor) {
		this.contributor = contributor;
	}
	public String getElectronic_code() {
		return electronic_code;
	}
	public void setElectronic_code(String electronic_code) {
		this.electronic_code = electronic_code;
	}
	public int getWin_fee() {
		return win_fee;
	}
	public void setWin_fee(int win_fee) {
		this.win_fee = win_fee;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Integer getRow_start() {
		return row_start;
	}
	public void setRow_start(Integer row_start) {
		this.row_start = row_start;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}


	
	
}
