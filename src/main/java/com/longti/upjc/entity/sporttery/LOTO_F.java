package com.longti.upjc.entity.sporttery;
import java.io.Serializable;

import com.longti.upjc.util.StringUtil;


/**
 * 足球彩票信息entity
 */
public class LOTO_F implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 8549423862211941476L;
	private String issue; //比赛编号
	private String[] issues;
    private String StartIssue;//查询开始销售编号
    private String EndIssue;//查询结束销售编号
    private String starttime; //比赛开始时间
    private String endtime; //比赛结束时间
    private String home_team_name; //主队名称
    private String guest_team_name; //客队名称
    private String home_team_id; //主队编号
    private String guest_team_id; //客队编号
    private String home_half_result; //主队半场进球
    private String guest_half_result; //客队半场进球
    private String home_full_result; //主队全场进球
    private String guest_full_result; //客队全场进球
    private String had; //胜平负赛果赔率
    private String cg_had; //胜平负赛果
    private String had_h; //胜
    private String had_d; //平
    private String had_a; //负
    
    
    private String had_h_0; //胜初始值    
	private String had_d_0; //平初始值
    private String had_a_0; //负初始值
    
    private Integer is_recommend; //推荐 1推荐 0 不推荐
    private Integer status; //比赛状态,1进行中2已派奖3已开奖99取消
    private String leaguename; //比赛名称
    private Integer row_start;//开始行
    private Integer page_size;//每页行数
    private Integer letcount;//让球

    private Integer is_hot;//热点
    private Integer had_bet;
    public String getIssue() {
        return StringUtil.ifnull(issue);
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    public String getStarttime() {
        return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
    public String getEndtime() {
        return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
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
    public String getHome_team_id() {
        return home_team_id;
	}
	public void setHome_team_id(String home_team_id) {
		this.home_team_id = home_team_id;
	}
    public String getGuest_team_id() {
        return guest_team_id;
	}
	public void setGuest_team_id(String guest_team_id) {
		this.guest_team_id = guest_team_id;
	}
    public String getHome_half_result() {
        return home_half_result;
	}
	public void setHome_half_result(String home_half_result) {
		this.home_half_result = home_half_result;
	}
    public String getGuest_half_result() {
        return guest_half_result;
	}
	public void setGuest_half_result(String guest_half_result) {
		this.guest_half_result = guest_half_result;
	}
    public String getHome_full_result() {
        return home_full_result;
	}
	public void setHome_full_result(String home_full_result) {
		this.home_full_result = home_full_result;
	}
    public String getGuest_full_result() {
        return guest_full_result;
	}
	public void setGuest_full_result(String guest_full_result) {
		this.guest_full_result = guest_full_result;
	}
    public String getHad() {
        return had;
	}
	public void setHad(String had) {
		this.had = had;
	}
    public String getCg_had() {
        return cg_had;
	}
	public void setCg_had(String cg_had) {
		this.cg_had = cg_had;
	}
    
	
    public String getHad_h() {
        return had_h;
	}
	public void setHad_h(String had_h) {
		this.had_h = had_h;
	}
    public String getHad_d() {
        return had_d;
	}
	public void setHad_d(String had_d) {
		this.had_d = had_d;
	}
    public String getHad_a() {
        return had_a;
	}
	public void setHad_a(String had_a) {
		this.had_a = had_a;
	}
    
    public Integer getIs_recommend() {
        return is_recommend;
	}
	public void setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
	}
    public Integer getStatus() {
        return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    public String getLeaguename() {
        return leaguename;
	}
	public void setLeaguename(String leaguename) {
		this.leaguename = leaguename;
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
	public Integer getLetcount() {
		return letcount;
	}
	public void setLetcount(Integer letcount) {
		this.letcount = letcount;
	}
	public String[] getIssues() {
		return issues;
	}
	public void setIssues(String[] issues) {
		this.issues = issues;
	}
	public String getStartIssue() {
		return StartIssue;
	}
	public void setStartIssue(String startIssue) {
		StartIssue = startIssue;
	}
	public String getEndIssue() {
		return EndIssue;
	}
	public void setEndIssue(String endIssue) {
		EndIssue = endIssue;
	}
	public String getHad_h_0() {
		return had_h_0;
	}
	public void setHad_h_0(String had_h_0) {
		this.had_h_0 = had_h_0;
	}
	public String getHad_d_0() {
		return had_d_0;
	}
	public void setHad_d_0(String had_d_0) {
		this.had_d_0 = had_d_0;
	}
	public String getHad_a_0() {
		return had_a_0;
	}
	public void setHad_a_0(String had_a_0) {
		this.had_a_0 = had_a_0;
	}
	
	
	
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public Integer getHad_bet() {
		return had_bet;
	}
	public void setHad_bet(Integer had_bet) {
		this.had_bet = had_bet;
	}
	
	
}