package com.longti.upjc.entity.sporttery;
import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.longti.upjc.util.StringUtil;


/**
 * entity
 */
public class LOTO_ORDER implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5007265541484529987L;
	private Integer id; //编号
    private String order_id; //投注编号
    private String electronic_code;
    private String user_pin; //用户pin
    private String issue; //期号
    private String vsteam; //对阵信息
    private String vsresult; //对阵结果
    private Integer bet_fee; //投注金额
    private Integer win_fee; //奖金,0表示未中奖
    private Date create_time; //创建时间
    private Date start_create_time; //开始创建时间
    private Date end_create_time; //结束创建时间
    private Integer bet_type; //投注玩法,301胜平负305让球胜平负307胜负309大小分
    private String bet_info; //投注方案
    private Integer bet_status; //兑奖状态,0支付中,1已支付(待兑奖)，2已中奖，3未中奖，4已取消
    private Integer prize_status; //派奖状态,1未派奖2已派奖
    private Integer prize_type; //派奖类型,1自动2手动
    private Date prize_cancel_time; //派奖(取消)时间
    private Date start_prize_cancel_time; //开始派奖(取消)时间
    private Date end_prize_cancel_time; //结束派奖(取消)时间
    private String order_source; //来源
    private String position;//频道
    private String memo; //备注
    private String options_one;//选项1
    private String options_two;//选项2
    private String options_three;//选项3
    private String play_method;//话题标题
    private String leaguename;//比赛名称
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
    public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    public String getVsteam() {
        return vsteam;
	}
	public void setVsteam(String vsteam) {
		this.vsteam = vsteam;
	}
    public String getVsresult() {
        return vsresult;
	}
	public void setVsresult(String vsresult) {
		this.vsresult = vsresult;
	}
    public Integer getBet_fee() {
        return bet_fee;
	}
	public void setBet_fee(Integer bet_fee) {
		this.bet_fee = bet_fee;
	}
    public Integer getWin_fee() {
        return win_fee;
	}
	public void setWin_fee(Integer win_fee) {
		this.win_fee = win_fee;
	}
	@JSONField (format="yyyy-MM-dd HH:mm:ss") 
    public Date getCreate_time() {
        return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
    public Date getStart_create_time() {
        return start_create_time;
	}
	public void setStart_create_time(Date start_create_time) {
		this.start_create_time = start_create_time;
	}
    public Date getEnd_create_time() {
        return end_create_time;
	}
	public void setEnd_create_time(Date end_create_time) {
		this.end_create_time = end_create_time;
	}
    public Integer getBet_type() {
        return bet_type;
	}
	public void setBet_type(Integer bet_type) {
		this.bet_type = bet_type;
	}
    public String getBet_info() {
        return StringUtil.ifnull(bet_info);
	}
	public void setBet_info(String bet_info) {
		this.bet_info = bet_info;
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
    public Integer getPrize_type() {
        return prize_type;
	}
	public void setPrize_type(Integer prize_type) {
		this.prize_type = prize_type;
	}
    public Date getPrize_cancel_time() {
        return prize_cancel_time;
	}
	public void setPrize_cancel_time(Date prize_cancel_time) {
		this.prize_cancel_time = prize_cancel_time;
	}
    public Date getStart_prize_cancel_time() {
        return start_prize_cancel_time;
	}
	public void setStart_prize_cancel_time(Date start_prize_cancel_time) {
		this.start_prize_cancel_time = start_prize_cancel_time;
	}
    public Date getEnd_prize_cancel_time() {
        return end_prize_cancel_time;
	}
	public void setEnd_prize_cancel_time(Date end_prize_cancel_time) {
		this.end_prize_cancel_time = end_prize_cancel_time;
	}
    public String getOrder_source() {
        return StringUtil.ifnull(order_source);
	}
	public void setOrder_source(String order_source) {
		this.order_source = order_source;
	}
    public String getMemo() {
        return StringUtil.ifnull(memo);
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getPlay_method() {
		return play_method;
	}
	public void setPlay_method(String play_method) {
		this.play_method = play_method;
	}
	public String getLeaguename() {
		return leaguename;
	}
	public void setLeaguename(String leaguename) {
		this.leaguename = leaguename;
	}
	public String getElectronic_code() {
		return electronic_code;
	}
	public void setElectronic_code(String electronic_code) {
		this.electronic_code = electronic_code;
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

	
}