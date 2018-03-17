package com.longti.upjc.entity.sporttery;
import java.io.Serializable;


/**
 * 电竞统计entity
 */
public class T_LOTO_SIS_E implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private String issue; //期号
    private String[] issues;
    private Integer mnl_h; //投胜数量
    private Integer mnl_a; //投负数量
    private Integer mnl_h_d; //投注胜负胜数量
    private Integer mnl_a_d; //投注胜负负数量
    
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
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
    public Integer getMnl_h_d() {
        return mnl_h_d;
	}
	public void setMnl_h_d(Integer mnl_h_d) {
		this.mnl_h_d = mnl_h_d;
	}
    public Integer getMnl_a_d() {
        return mnl_a_d;
	}
	public void setMnl_a_d(Integer mnl_a_d) {
		this.mnl_a_d = mnl_a_d;
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
	

	
}