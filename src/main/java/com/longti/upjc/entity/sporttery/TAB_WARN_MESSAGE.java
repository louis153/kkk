package com.longti.upjc.entity.sporttery;
import java.io.Serializable;
import java.util.Date;


/**
 * 告警消息列表entity
 */
public class TAB_WARN_MESSAGE implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer id; //
    private Integer type; //告警类型, 0竞猜话题
    private String issue; //比赛对阵编号 用于操作这场比赛使用
    private String currency; //币种
    private String match_name; //赛事名称 e.g. 英超....
    private String home_team_name; //主队名称
    private String guest_team_name; //客队名称
    private String play_method; //玩法 e.g. 梅西上半场是否能进2个球
    private String event_desc; //事件说明 例如: 选项能达到限陪额85%
    private Date event_time; //事件发生时间
    private Date start_event_time; //开始事件发生时间
    private Date end_event_time; //结束事件发生时间
    private Date stop_time; //话题停售时间
    private Date start_stop_time; //开始话题停售时间
    private Date end_stop_time; //结束话题停售时间
    private Integer processed; //是否处理过的 0 否1是
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public Integer getType() {
        return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
    public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    public String getCurrency() {
        return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
    public String getMatch_name() {
        return match_name;
	}
	public void setMatch_name(String match_name) {
		this.match_name = match_name;
	}
    public String getHome_team_name() {
        return home_team_name;
	}
	public void setHome_team_name(String home_team_name) {
		this.home_team_name = home_team_name;
	}
    public String getGuest_team_name() {
        return guest_team_name;
	}
	public void setGuest_team_name(String guest_team_name) {
		this.guest_team_name = guest_team_name;
	}
    public String getPlay_method() {
        return play_method;
	}
	public void setPlay_method(String play_method) {
		this.play_method = play_method;
	}
    public String getEvent_desc() {
        return event_desc;
	}
	public void setEvent_desc(String event_desc) {
		this.event_desc = event_desc;
	}
    public Date getEvent_time() {
        return event_time;
	}
	public void setEvent_time(Date event_time) {
		this.event_time = event_time;
	}
    public Date getStart_event_time() {
        return start_event_time;
	}
	public void setStart_event_time(Date start_event_time) {
		this.start_event_time = start_event_time;
	}
    public Date getEnd_event_time() {
        return end_event_time;
	}
	public void setEnd_event_time(Date end_event_time) {
		this.end_event_time = end_event_time;
	}
    public Date getStop_time() {
        return stop_time;
	}
	public void setStop_time(Date stop_time) {
		this.stop_time = stop_time;
	}
    public Date getStart_stop_time() {
        return start_stop_time;
	}
	public void setStart_stop_time(Date start_stop_time) {
		this.start_stop_time = start_stop_time;
	}
    public Date getEnd_stop_time() {
        return end_stop_time;
	}
	public void setEnd_stop_time(Date end_stop_time) {
		this.end_stop_time = end_stop_time;
	}
    public Integer getProcessed() {
        return processed;
	}
	public void setProcessed(Integer processed) {
		this.processed = processed;
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