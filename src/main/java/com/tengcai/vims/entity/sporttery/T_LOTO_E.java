package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;


/**
 * 电子竞猜比赛信息entity
 */
public class T_LOTO_E implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private String issue; //比赛编号
    private String match_mode; //比赛模式
    private String game_name; //游戏名称, 创建比赛时存入
    private String starttime; //比赛开始时间
    private String endtime; //截止投注时间 开始时间的前15分钟
    private String home_team_name; //主队名称
    private String guest_team_name; //客队名称
    private String home_team_id; //主队编号
    private String guest_team_id; //客队编号
    private String home_full_result; //主队全场比分
    private String guest_full_result; //客队全场比分
    private String cg_mnl; //胜负赛果 e.g. 胜 or 负
    private String init_h; //初始胜赔率
    private String init_a; //初始负赔率
    private String mnl; //e.g. Payout or Selling .......
    private String mnl_h; //胜
    private String mnl_a; //胜
    private Integer status; //比赛状态,1进行中2已派奖3已开奖99取消
    private String leaguename; //比赛名称
    private Integer available; //是否可用0否,1是
    private Integer is_recommend; //是否推荐 0 no,1 yes
    private Integer is_hot; //热点0否,1是
    private Integer row_start;//开始行
    private Integer page_size;//每页行数
    private Integer mnl_bet;//
    private String[] issues;
    private String StartIssue;//查询开始销售编号
    private String EndIssue;//查询结束销售编号
    private String img_name;
    public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    public String getMatch_mode() {
        return match_mode;
	}
	public void setMatch_mode(String match_mode) {
		this.match_mode = match_mode;
	}
    public String getGame_name() {
        return game_name;
	}
	public void setGame_name(String game_name) {
		this.game_name = game_name;
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
    public String getCg_mnl() {
        return cg_mnl;
	}
	public void setCg_mnl(String cg_mnl) {
		this.cg_mnl = cg_mnl;
	}
    public String getInit_h() {
        return init_h;
	}
	public void setInit_h(String init_h) {
		this.init_h = init_h;
	}
    public String getInit_a() {
        return init_a;
	}
	public void setInit_a(String init_a) {
		this.init_a = init_a;
	}
    public String getMnl() {
        return mnl;
	}
	public void setMnl(String mnl) {
		this.mnl = mnl;
	}
    public String getMnl_h() {
        return mnl_h;
	}
	public void setMnl_h(String mnl_h) {
		this.mnl_h = mnl_h;
	}
    public String getMnl_a() {
        return mnl_a;
	}
	public void setMnl_a(String mnl_a) {
		this.mnl_a = mnl_a;
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
    public Integer getAvailable() {
        return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
    public Integer getIs_recommend() {
        return is_recommend;
	}
	public void setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
	}
    public Integer getIs_hot() {
        return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
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
	
	public Integer getMnl_bet() {
		return mnl_bet;
	}
	public void setMnl_bet(Integer mnl_bet) {
		this.mnl_bet = mnl_bet;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	
}