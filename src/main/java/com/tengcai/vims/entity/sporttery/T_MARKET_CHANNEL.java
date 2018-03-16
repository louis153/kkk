package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;
import java.util.Date;


/**
 * 渠道管理entity
 */
public class T_MARKET_CHANNEL implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer id; //主键
    private Integer channelnum; //渠道编号
    private String channelname; //渠道名称
    private String channelstate; //1可用，其他标记已删除
    private Date createtime; //创建时间
    private Date start_createtime; //开始创建时间
    private Date end_createtime; //结束创建时间
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public Integer getChannelnum() {
        return channelnum;
	}
	public void setChannelnum(Integer channelnum) {
		this.channelnum = channelnum;
	}
    public String getChannelname() {
        return channelname;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
    public String getChannelstate() {
        return channelstate;
	}
	public void setChannelstate(String channelstate) {
		this.channelstate = channelstate;
	}
    public Date getCreatetime() {
        return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
    public Date getStart_createtime() {
        return start_createtime;
	}
	public void setStart_createtime(Date start_createtime) {
		this.start_createtime = start_createtime;
	}
    public Date getEnd_createtime() {
        return end_createtime;
	}
	public void setEnd_createtime(Date end_createtime) {
		this.end_createtime = end_createtime;
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