package com.longti.upjc.entity.sporttery;
import java.io.Serializable;


/**
 * 足球统计entity
 */
public class T_LOTO_SIS_F implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private String issue; //期号
    private String electronic_code; //电子货币简称
    private String[] issues;//查询的期号列表
    private Integer had_h; //投胜数量
    private Integer had_d; //投平数量
    private Integer had_a; //投负数量
    
    private long had_h_d;//胜平负胜投注额 
    private long had_d_d; //胜平负平投注额
    private long had_a_d; //胜平负负投注额
    
    private Integer row_start;//开始行
    private Integer page_size;//每页行数
    
    public long getHad_h_d() {
		return had_h_d;
	}
	public void setHad_h_d(long had_h_d) {
		this.had_h_d = had_h_d;
	}
	public long getHad_d_d() {
		return had_d_d;
	}
	public void setHad_d_d(long had_d_d) {
		this.had_d_d = had_d_d;
	}
	public long getHad_a_d() {
		return had_a_d;
	}
	public void setHad_a_d(long had_a_d) {
		this.had_a_d = had_a_d;
	}
	public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    public Integer getHad_h() {
        return had_h;
	}
	public void setHad_h(Integer had_h) {
		this.had_h = had_h;
	}
    public Integer getHad_d() {
        return had_d;
	}
	public void setHad_d(Integer had_d) {
		this.had_d = had_d;
	}
    public Integer getHad_a() {
        return had_a;
	}
	public void setHad_a(Integer had_a) {
		this.had_a = had_a;
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
	public String getElectronic_code() {
		return electronic_code;
	}
	public void setElectronic_code(String electronic_code) {
		this.electronic_code = electronic_code;
	}

	
}