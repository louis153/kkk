package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;

import com.tengcai.vims.util.StringUtil;

/**
 * 篮球彩票信息entity
 */
public class LOTO_B implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4384384644295906754L;
	private String issue; //比赛编号
    private String[] issues;
    private String StartIssue;//查询开始销售编号
    private String EndIssue;//查询结束销售编号
    private String starttime; //比赛开始时间
    private String endtime; //截止投注时间
    private String home_team_name; //主队名称
    private String guest_team_name; //客队名称
    private String home_team_id; //主队编号
    private String guest_team_id; //客队编号
    private String home_full_result; //主队全场比分
    private String guest_full_result; //客队全场比分
    private String cg_hilo; //大小分赛果
    private String cg_mnl; //胜负赛果
    private String mnl; //胜负赛果赔率
    private String mnl_h; //胜
    private String mnl_a; //负
    private String hilo; //大小分赛果赔率
    private String hilo_h; //大分
    private String hilo_l; //小分
    private String hilo_presetscore; //大小分标准
    private String mnl_h_0; //胜初始值
    
	private String mnl_a_0; //负初始值
    private String hilo_h_0; //大分初始值
    private String hilo_l_0; //小分初始值
    private Integer is_recommend; //推荐 1推荐 0 不推荐
    private Integer status; //比赛状态,1进行中2已派奖3已开奖99取消
    private String leaguename; //比赛名称
    private Integer row_start;//开始行
    private Integer page_size;//每页行数
    private Integer is_hot;
    private Integer hilo_bet;
    private Integer mnl_bet;
    private int hilobet=1;
    private int mnlbet=1;

    
    public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    public String getStarttime() {
        return (starttime);
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
    public String getEndtime() {
        return (endtime);
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
    public String getHome_team_name() {
        return (home_team_name);
	}
	public void setHome_team_name(String home_team_name) {
		this.home_team_name = home_team_name;
	}
    public String getGuest_team_name() {
        return (guest_team_name);
	}
	public void setGuest_team_name(String guest_team_name) {
		this.guest_team_name = guest_team_name;
	}
    public String getHome_team_id() {
        return (home_team_id);
	}
	public void setHome_team_id(String home_team_id) {
		this.home_team_id = home_team_id;
	}
    public String getGuest_team_id() {
        return (guest_team_id);
	}
	public void setGuest_team_id(String guest_team_id) {
		this.guest_team_id = guest_team_id;
	}
    public String getHome_full_result() {
        return (home_full_result);
	}
	public void setHome_full_result(String home_full_result) {
		this.home_full_result = home_full_result;
	}
    public String getGuest_full_result() {
        return (guest_full_result);
	}
	public void setGuest_full_result(String guest_full_result) {
		this.guest_full_result = guest_full_result;
	}
    public String getCg_hilo() {
        return (cg_hilo);
	}
	public void setCg_hilo(String cg_hilo) {
		this.cg_hilo = cg_hilo;
	}
    public String getCg_mnl() {
        return (cg_mnl);
	}
	public void setCg_mnl(String cg_mnl) {
		this.cg_mnl = cg_mnl;
	}
    public String getMnl() {
        return (mnl);
	}
	public void setMnl(String mnl) {
		this.mnl = mnl;
	}
    public String getMnl_h() {
        return (mnl_h);
	}
	public void setMnl_h(String mnl_h) {
		this.mnl_h = mnl_h;
	}
    public String getMnl_a() {
        return (mnl_a);
	}
	public void setMnl_a(String mnl_a) {
		this.mnl_a = mnl_a;
	}
    public String getHilo() {
        return (hilo);
	}
	public void setHilo(String hilo) {
		this.hilo = hilo;
	}
    public String getHilo_h() {
        return (hilo_h);
	}
	public void setHilo_h(String hilo_h) {
		this.hilo_h = hilo_h;
	}
    public String getHilo_l() {
        return (hilo_l);
	}
	public void setHilo_l(String hilo_l) {
		this.hilo_l = hilo_l;
	}
    public String getHilo_presetscore() {
        return (hilo_presetscore);
	}
	public void setHilo_presetscore(String hilo_presetscore) {
		this.hilo_presetscore = hilo_presetscore;
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
        return StringUtil.ifnull(leaguename);
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
	public String[] getIssues() {
		return (issues);
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

	public String getMnl_h_0() {
		return mnl_h_0;
	}
	public void setMnl_h_0(String mnl_h_0) {
		this.mnl_h_0 = mnl_h_0;
	}
	public String getMnl_a_0() {
		return mnl_a_0;
	}
	public void setMnl_a_0(String mnl_a_0) {
		this.mnl_a_0 = mnl_a_0;
	}
	public String getHilo_h_0() {
		return hilo_h_0;
	}
	public void setHilo_h_0(String hilo_h_0) {
		this.hilo_h_0 = hilo_h_0;
	}
	public String getHilo_l_0() {
		return hilo_l_0;
	}
	public void setHilo_l_0(String hilo_l_0) {
		this.hilo_l_0 = hilo_l_0;
	}
	public int getHilobet() {
		return hilobet;
	}
	public void setHilobet(int hilobet) {
		this.hilobet = hilobet;
	}
	public int getMnlbet() {
		return mnlbet;
	}
	public void setMnlbet(int mnlbet) {
		this.mnlbet = mnlbet;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public Integer getHilo_bet() {
		return hilo_bet;
	}
	public void setHilo_bet(Integer hilo_bet) {
		this.hilo_bet = hilo_bet;
	}
	public Integer getMnl_bet() {
		return mnl_bet;
	}
	public void setMnl_bet(Integer mnl_bet) {
		this.mnl_bet = mnl_bet;
	}
}