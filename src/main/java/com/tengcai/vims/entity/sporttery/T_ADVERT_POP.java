package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;
import java.util.Date;


/**
 * 首页弹层广告entity
 */
public class T_ADVERT_POP implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer id; //ID
    private String adtitle; //广告名称
    private Date adtimebegin; //上线日期
    private Date start_adtimebegin; //开始上线日期
    private Date end_adtimebegin; //结束上线日期
    private Date adtimeend; //下线日期
    private Date start_adtimeend; //开始下线日期
    private Date end_adtimeend; //结束下线日期
    private Integer adtype; //1-M端
    private Integer adseq; //排序
    private String adimgname; //广告图片名称
    private String adurl; //广告链接地址
    private Integer adstatus; //状态1-已发布，2-未发布
    private Date createtime; //创建日期
    private Date start_createtime; //开始创建日期
    private Date end_createtime; //结束创建日期
    private Date updatetime; //修改时间
    private Date start_updatetime; //开始修改时间
    private Date end_updatetime; //结束修改时间
    private String order_source; //来源
    private Integer row_start;//开始行
    private Integer page_size;//每页行数
    private String position;

    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public String getAdtitle() {
        return adtitle;
	}
	public void setAdtitle(String adtitle) {
		this.adtitle = adtitle;
	}
    public Date getAdtimebegin() {
        return adtimebegin;
	}
	public void setAdtimebegin(Date adtimebegin) {
		this.adtimebegin = adtimebegin;
	}
    public Date getStart_adtimebegin() {
        return start_adtimebegin;
	}
	public void setStart_adtimebegin(Date start_adtimebegin) {
		this.start_adtimebegin = start_adtimebegin;
	}
    public Date getEnd_adtimebegin() {
        return end_adtimebegin;
	}
	public void setEnd_adtimebegin(Date end_adtimebegin) {
		this.end_adtimebegin = end_adtimebegin;
	}
    public Date getAdtimeend() {
        return adtimeend;
	}
	public void setAdtimeend(Date adtimeend) {
		this.adtimeend = adtimeend;
	}
    public Date getStart_adtimeend() {
        return start_adtimeend;
	}
	public void setStart_adtimeend(Date start_adtimeend) {
		this.start_adtimeend = start_adtimeend;
	}
    public Date getEnd_adtimeend() {
        return end_adtimeend;
	}
	public void setEnd_adtimeend(Date end_adtimeend) {
		this.end_adtimeend = end_adtimeend;
	}
    public Integer getAdtype() {
        return adtype;
	}
	public void setAdtype(Integer adtype) {
		this.adtype = adtype;
	}
    public Integer getAdseq() {
        return adseq;
	}
	public void setAdseq(Integer adseq) {
		this.adseq = adseq;
	}
    public String getAdimgname() {
        return adimgname;
	}
	public void setAdimgname(String adimgname) {
		this.adimgname = adimgname;
	}
    public String getAdurl() {
        return adurl;
	}
	public void setAdurl(String adurl) {
		this.adurl = adurl;
	}
    public Integer getAdstatus() {
        return adstatus;
	}
	public void setAdstatus(Integer adstatus) {
		this.adstatus = adstatus;
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
    public Date getUpdatetime() {
        return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
    public Date getStart_updatetime() {
        return start_updatetime;
	}
	public void setStart_updatetime(Date start_updatetime) {
		this.start_updatetime = start_updatetime;
	}
    public Date getEnd_updatetime() {
        return end_updatetime;
	}
	public void setEnd_updatetime(Date end_updatetime) {
		this.end_updatetime = end_updatetime;
	}
    public String getOrder_source() {
        return order_source;
	}
	public void setOrder_source(String order_source) {
		this.order_source = order_source;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}

	
}