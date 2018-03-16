package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;
import java.util.Date;


/**
 * entity
 */
public class USER_DAYWIN implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3805726201809506554L;
	private Integer id; //主键
    private String user_pin; //用户编号
    private Integer win_fee; //奖金
    private Date prize_cancel_time; //
    private Integer prize_status; //
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public String getUser_pin() {
        return user_pin;
	}
	public void setUser_pin(String user_pin) {
		this.user_pin = user_pin;
	}
    public Integer getWin_fee() {
        return win_fee;
	}
	public void setWin_fee(Integer win_fee) {
		this.win_fee = win_fee;
	}
    public Date getPrize_cancel_time() {
        return prize_cancel_time;
	}
	public void setPrize_cancel_time(Date prize_cancel_time) {
		this.prize_cancel_time = prize_cancel_time;
	}
    public Integer getPrize_status() {
        return prize_status;
	}
	public void setPrize_status(Integer prize_status) {
		this.prize_status = prize_status;
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