package com.longti.upjc.entity.sporttery;
import java.io.Serializable;
import java.util.Date;


/**
 * 告警比例设置entity
 */
public class TAB_WARN_SETTING implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer id; //
    private String type; //告警类型 0 竞猜话题先赔额告警比例
    private Integer ratio; //比例 0 到100 之间 由程序控制, 默认不告警
    private Date create_time; //创建时间
    private Date start_create_time; //开始创建时间
    private Date end_create_time; //结束创建时间
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public String getType() {
        return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    public Integer getRatio() {
        return ratio;
	}
	public void setRatio(Integer ratio) {
		this.ratio = ratio;
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