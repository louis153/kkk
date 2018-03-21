package com.longti.upjc.entity.sporttery;
import java.io.Serializable;

/**
 * GTO竞猜话题统计entity
 */
public class T_LOTO_SIS_E_GTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4102861938142494194L;
	private String issue;//期号
	private String electronic_code;//电子货币简称
	private int one;//投选项1数量
	private int two;//投选项2数量
	private int three;//投选项3数量
	private int one_d;//投选项1投注额
	private int two_d;//投选项2投注额
	private int three_d;//投选项3投注额
	
	private Integer row_start;//开始行
	private Integer page_size;//每页行数
	private String[] issues;//期号数组
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
	public int getOne() {
		return one;
	}
	public void setOne(int one) {
		this.one = one;
	}
	public int getTwo() {
		return two;
	}
	public void setTwo(int two) {
		this.two = two;
	}
	public int getThree() {
		return three;
	}
	public void setThree(int three) {
		this.three = three;
	}
	public int getOne_d() {
		return one_d;
	}
	public void setOne_d(int one_d) {
		this.one_d = one_d;
	}
	public int getTwo_d() {
		return two_d;
	}
	public void setTwo_d(int two_d) {
		this.two_d = two_d;
	}
	public int getThree_d() {
		return three_d;
	}
	public void setThree_d(int three_d) {
		this.three_d = three_d;
	}
	public Integer getRow_start() {
		return row_start;
	}
	public void setRow_start(Integer row_start) {
		this.row_start = row_start;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}
	public String[] getIssues() {
		return issues;
	}
	public void setIssues(String[] issues) {
		this.issues = issues;
	}
	

    

	
}