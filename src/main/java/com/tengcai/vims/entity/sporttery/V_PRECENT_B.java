package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;


/**
 * VIEWentity
 */
public class V_PRECENT_B implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 6686086937382168780L;
	private String hilo_h; //
    private String hilo_l; //
    private String issue; //期号
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    
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
	public String getHilo_h() {
		return hilo_h;
	}
	public void setHilo_h(String hilo_h) {
		this.hilo_h = hilo_h;
	}
	public String getHilo_l() {
		return hilo_l;
	}
	public void setHilo_l(String hilo_l) {
		this.hilo_l = hilo_l;
	}
	
	
}