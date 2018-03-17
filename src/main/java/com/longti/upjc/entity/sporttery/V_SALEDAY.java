package com.longti.upjc.entity.sporttery;
import java.io.Serializable;


/**
 * VIEWentity
 */
public class V_SALEDAY implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4379894055517632363L;
	private String saleday; //
    private Integer sport_type;
    private Integer row_start;//开始行
    private String endtime;//结束日期
    private String week_name;
    private Integer page_size;//每页行数
    private Integer match_count;//比赛数目
    private Integer rem_count;//推荐的比赛数目
    private String rem_issue;
    public String getSaleday() {
        return saleday;
	}
	public void setSaleday(String saleday) {
		this.saleday = saleday;
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
	public Integer getSport_type() {
		return sport_type;
	}
	public void setSport_type(Integer sport_type) {
		this.sport_type = sport_type;
	}
	public String getWeek_name() {
		return week_name;
	}
	public void setWeek_name(String week_name) {
		this.week_name = week_name;
	}
	public Integer getMatch_count() {
		return match_count;
	}
	public void setMatch_count(Integer match_count) {
		this.match_count = match_count;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Integer getRem_count() {
		return rem_count;
	}
	public void setRem_count(Integer rem_count) {
		this.rem_count = rem_count;
	}
	public String getRem_issue() {
		return rem_issue;
	}
	public void setRem_issue(String rem_issue) {
		this.rem_issue = rem_issue;
	}

	
}