package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;


/**
 * VIEWentity
 */
public class V_PRECENT_F implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5773169716699347643L;
	private String had_h; //
    private String had_d; //
    private String had_a; //
    private String issue; //期号
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


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
    public String getIssue() {
        return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
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