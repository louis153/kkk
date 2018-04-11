package com.longti.upjc.entity.sporttery;
import java.io.Serializable;
import java.util.Date;


/**
 * 话题信息entity
 */
public class T_LOTO_E implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private String issue; //比赛编号
    private String[] issues;
    
	private String electronic_code;//币种

    private String play_method; //玩法信息
    private String starttime; //比赛开始时间
    private String sale_time; //开售时间
    private String endtime; //截止投注时间 开始时间的前15分钟
    private String home_team_name; //主队名称
    private String guest_team_name; //客队名称
    private String options_one; //选项1 内容
    private String options_two; //选项2
    private String options_three; //选项3
    private String cg; //比赛结果, 1选项一正确  2选项2正确, 3选项3正确
    private String odds_one; //选线1 赔率
    private String odds_two; //选项2赔率
    private String odds_three; //选项3 赔率
    private Integer status; //比赛状态,1进行中2已派奖3已开奖99取消
    private String leaguename; //比赛名称
    private Integer available; //是否可用0否,1是
    private Integer mnl_bet; //可销售0否,1是
    private Date create_time; //创建时间
    private Date start_create_time; //开始创建时间
    private Date end_create_time; //结束创建时间
    private Date update_time; //修改时间
    private Date start_update_time; //开始修改时间
    private Date end_update_time; //结束修改时间
    private String compensate_min; //赔付额上限 最小值  100W倍
    private String compensate_max; //赔付额上限 最大值   100W倍
    private String single_lottery_max; //用户单次投注的上限  100W倍
    private String single_match_max; //用户单玩法投注上限 100W倍
    private Integer sort; //排序 
    private Integer lottery_type; //投注类型, 0普通, 1不中返还
    private String return_min; //不中返还额度区间的最小值  100W倍
    private String return_max; //不中返还额度区间的最大值   100W倍
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    public String getPlay_method() {
        return play_method;
	}
	public void setPlay_method(String play_method) {
		this.play_method = play_method;
	}
    public String getStarttime() {
        return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
    public String getSale_time() {
        return sale_time;
	}
	public void setSale_time(String sale_time) {
		this.sale_time = sale_time;
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
    public String getCg() {
        return cg;
	}
	public void setCg(String cg) {
		this.cg = cg;
	}
    public String getOdds_one() {
        return odds_one;
	}
	public void setOdds_one(String odds_one) {
		this.odds_one = odds_one;
	}
    public String getOdds_two() {
        return odds_two;
	}
	public void setOdds_two(String odds_two) {
		this.odds_two = odds_two;
	}
    public String getOdds_three() {
        return odds_three;
	}
	public void setOdds_three(String odds_three) {
		this.odds_three = odds_three;
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
    public Integer getMnl_bet() {
        return mnl_bet;
	}
	public void setMnl_bet(Integer mnl_bet) {
		this.mnl_bet = mnl_bet;
	}
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
    public Date getUpdate_time() {
        return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
    public Date getStart_update_time() {
        return start_update_time;
	}
	public void setStart_update_time(Date start_update_time) {
		this.start_update_time = start_update_time;
	}
    public Date getEnd_update_time() {
        return end_update_time;
	}
	public void setEnd_update_time(Date end_update_time) {
		this.end_update_time = end_update_time;
	}
    public String getCompensate_min() {
        return compensate_min;
	}
	public void setCompensate_min(String compensate_min) {
		this.compensate_min = compensate_min;
	}
    public String getCompensate_max() {
        return compensate_max;
	}
	public void setCompensate_max(String compensate_max) {
		this.compensate_max = compensate_max;
	}
    public String getSingle_lottery_max() {
        return single_lottery_max;
	}
	public void setSingle_lottery_max(String single_lottery_max) {
		this.single_lottery_max = single_lottery_max;
	}
    public String getSingle_match_max() {
        return single_match_max;
	}
	public void setSingle_match_max(String single_match_max) {
		this.single_match_max = single_match_max;
	}
    public Integer getSort() {
        return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
    public Integer getLottery_type() {
        return lottery_type;
	}
	public void setLottery_type(Integer lottery_type) {
		this.lottery_type = lottery_type;
	}
    public String getReturn_min() {
        return return_min;
	}
	public void setReturn_min(String return_min) {
		this.return_min = return_min;
	}
    public String getReturn_max() {
        return return_max;
	}
	public void setReturn_max(String return_max) {
		this.return_max = return_max;
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
	public String[] getIssues() {
		return issues;
	}
	public void setIssues(String[] issues) {
		this.issues = issues;
	}

	
}