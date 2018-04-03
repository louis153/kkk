package com.longti.upjc.entity.sporttery;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * entity
 */
public class T_USER implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private String user_pin; //用户唯一标识
    private String user_token; //用户token
    private String nick_name; //用户昵称
    private Date first_time; //第一次登录时间
    private Date start_first_time; //开始第一次登录时间
    private Date end_first_time; //结束第一次登录时间
    private Date land_time; //本次登录时间
    private BigDecimal award_gto; //奖励的gto账户金额
    private Date start_land_time; //开始本次登录时间
    private Date end_land_time; //结束本次登录时间
    private BigDecimal award_eth;//奖励的eth账户金额
    private BigDecimal award_uz;//奖励的uz账户金额
    private Integer bet_times;//投注次数
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public String getUser_pin() {
        return user_pin;
	}
	public void setUser_pin(String user_pin) {
		this.user_pin = user_pin;
	}
    public String getUser_token() {
        return user_token;
	}
	public void setUser_token(String user_token) {
		this.user_token = user_token;
	}
    public String getNick_name() {
        return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
    public Date getFirst_time() {
        return first_time;
	}
	public void setFirst_time(Date first_time) {
		this.first_time = first_time;
	}
    public Date getStart_first_time() {
        return start_first_time;
	}
	public void setStart_first_time(Date start_first_time) {
		this.start_first_time = start_first_time;
	}
    public Date getEnd_first_time() {
        return end_first_time;
	}
	public void setEnd_first_time(Date end_first_time) {
		this.end_first_time = end_first_time;
	}
    public Date getLand_time() {
        return land_time;
	}
	public void setLand_time(Date land_time) {
		this.land_time = land_time;
	}
    public BigDecimal getAward_gto() {
		return award_gto;
	}
	public void setAward_gto(BigDecimal award_gto) {
		this.award_gto = award_gto;
	}
	public Date getStart_land_time() {
        return start_land_time;
	}
	public void setStart_land_time(Date start_land_time) {
		this.start_land_time = start_land_time;
	}
    public Date getEnd_land_time() {
        return end_land_time;
	}
	public void setEnd_land_time(Date end_land_time) {
		this.end_land_time = end_land_time;
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
	public BigDecimal getAward_eth() {
		return award_eth;
	}
	public void setAward_eth(BigDecimal award_eth) {
		this.award_eth = award_eth;
	}
	public BigDecimal getAward_uz() {
		return award_uz;
	}
	public void setAward_uz(BigDecimal award_uz) {
		this.award_uz = award_uz;
	}
	public Integer getBet_times() {
		return bet_times;
	}
	public void setBet_times(Integer bet_times) {
		this.bet_times = bet_times;
	}

	
}