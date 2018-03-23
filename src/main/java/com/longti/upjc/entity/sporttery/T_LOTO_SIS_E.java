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
    private String[] issues;//查询的期号列表
    private String electronic_code; //电子货币简称
    private Long one; //投选项1数量
    private Long two; //投选项2数量
    private Long three; //投选项3数量
    private Long one_d; //投选项1投注额
    private Long two_d; //投选项2投注额
    private Long three_d; //投选项3投注额
    private Long one_p; //投选项1赔付额
    private Long two_p; //投选项2赔付额
    private Long three_p; //投选项3赔付额
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    public String getElectronic_code() {
        return electronic_code;
	}
	public void setElectronic_code(String electronic_code) {
		this.electronic_code = electronic_code;
	}
    public Long getOne() {
        return one;
	}
	public void setOne(Long one) {
		this.one = one;
	}
    public Long getTwo() {
        return two;
	}
	public void setTwo(Long two) {
		this.two = two;
	}
    public Long getThree() {
        return three;
	}
	public void setThree(Long three) {
		this.three = three;
	}
    public Long getOne_d() {
        return one_d;
	}
	public void setOne_d(Long one_d) {
		this.one_d = one_d;
	}
    public Long getTwo_d() {
        return two_d;
	}
	public void setTwo_d(Long two_d) {
		this.two_d = two_d;
	}
    public Long getThree_d() {
        return three_d;
	}
	public void setThree_d(Long three_d) {
		this.three_d = three_d;
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
	public Long getOne_p() {
		return one_p;
	}
	public void setOne_p(Long one_p) {
		this.one_p = one_p;
	}
	public Long getTwo_p() {
		return two_p;
	}
	public void setTwo_p(Long two_p) {
		this.two_p = two_p;
	}
	public Long getThree_p() {
		return three_p;
	}
	public void setThree_p(Long three_p) {
		this.three_p = three_p;
	}

	
}