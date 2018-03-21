package com.longti.upjc.entity.sporttery;
import java.io.Serializable;


/**
 * 订单主表entity
 */
public class V_ORDER implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6336455048318621347L;
	private String order_id; //订单编号
	private String electronic_code;//币种
    private String user_pin; //用户PIN
    private Integer bet_type; //玩法
    private String create_time; //创建日期
    private String vsteam; //对阵
    private Integer bet_status; //中奖状态
    private Integer prize_status; //派奖状态
    private Long bet_fee; //消费
    private Integer win_fee; //奖金
    private String issume; //期号
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public String getOrder_id() {
        return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
    public String getUser_pin() {
        return user_pin;
	}
	public void setUser_pin(String user_pin) {
		this.user_pin = user_pin;
	}
    public Integer getBet_type() {
        return bet_type;
	}
	public void setBet_type(Integer bet_type) {
		this.bet_type = bet_type;
	}
    public String getCreate_time() {
        return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
    public String getVsteam() {
        return vsteam;
	}
	public void setVsteam(String vsteam) {
		this.vsteam = vsteam;
	}
    public Integer getBet_status() {
        return bet_status;
	}
	public void setBet_status(Integer bet_status) {
		this.bet_status = bet_status;
	}
    public Integer getPrize_status() {
        return prize_status;
	}
	public void setPrize_status(Integer prize_status) {
		this.prize_status = prize_status;
	}
    public Long getBet_fee() {
        return bet_fee;
	}
	public void setBet_fee(Long bet_fee) {
		this.bet_fee = bet_fee;
	}
    public Integer getWin_fee() {
        return win_fee;
	}
	public void setWin_fee(Integer win_fee) {
		this.win_fee = win_fee;
	}
    public String getIssume() {
        return issume;
	}
	public void setIssume(String issume) {
		this.issume = issume;
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
	public String getElectronic_code() {
		return electronic_code;
	}
	public void setElectronic_code(String electronic_code) {
		this.electronic_code = electronic_code;
	}

	
}