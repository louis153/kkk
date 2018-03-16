package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;


/**
 * 蓝球统计entity
 */
public class T_LOTO_SIS_B implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private String issue; //期号
    private String[] issues;//查询的期号列表
    private Integer hilo_h; //投大分数量
    private Integer hilo_l; //投小分数量
    private Integer mnl_h; //投胜数量
    private Integer mnl_a; //投负数量
    private double hilo_h_d; //投大分投注额    
	private double hilo_l_d; //投小分投注额
    private double mnl_h_d; //投胜投注额
    private double mnl_a_d; //投负投注额
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    public Integer getHilo_h() {
        return hilo_h;
	}
	public void setHilo_h(Integer hilo_h) {
		this.hilo_h = hilo_h;
	}
    public Integer getHilo_l() {
        return hilo_l;
	}
	public void setHilo_l(Integer hilo_l) {
		this.hilo_l = hilo_l;
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
	public Integer getMnl_h() {
		return mnl_h;
	}
	public void setMnl_h(Integer mnl_h) {
		this.mnl_h = mnl_h;
	}
	public Integer getMnl_a() {
		return mnl_a;
	}
	public void setMnl_a(Integer mnl_a) {
		this.mnl_a = mnl_a;
	}
	public double getHilo_h_d() {
		return hilo_h_d;
	}
	public void setHilo_h_d(double hilo_h_d) {
		this.hilo_h_d = hilo_h_d;
	}
	public double getHilo_l_d() {
		return hilo_l_d;
	}
	public void setHilo_l_d(double hilo_l_d) {
		this.hilo_l_d = hilo_l_d;
	}
	public double getMnl_h_d() {
		return mnl_h_d;
	}
	public void setMnl_h_d(double mnl_h_d) {
		this.mnl_h_d = mnl_h_d;
	}
	public double getMnl_a_d() {
		return mnl_a_d;
	}
	public void setMnl_a_d(double mnl_a_d) {
		this.mnl_a_d = mnl_a_d;
	}
	public String[] getIssues() {
		return issues;
	}
	public void setIssues(String[] issues) {
		this.issues = issues;
	}
	
}