package com.longti.upjc.entity.sporttery;
import java.io.Serializable;


/**
 * 告警消息接受人entity
 */
public class TAB_WARN_RECEIVE implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer id; //
    private String phone; //告警手机号码
    private Integer available; //是否可用0 否1是
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public String getPhone() {
        return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    public Integer getAvailable() {
        return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
    public Integer getRow_start(){
        return row_start;
    }

    public void setRow_start(Integer row_start){
        this.row_start=row_start;
    }

    public Integer getPage_size(){
        return page_size;
    }

    public void setPage_size(Integer page_size){
        this.page_size=page_size;
    }

	
}