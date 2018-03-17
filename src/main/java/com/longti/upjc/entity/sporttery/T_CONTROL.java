package com.longti.upjc.entity.sporttery;
import java.io.Serializable;


/**
 * 阀值管理entity
 */
public class T_CONTROL implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer id; //主键id
    private Integer maxval; //最大阀值
    private String sporttype; //游戏类型
    private Integer setstatus; //状态,0停用1启用
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public Integer getMaxval() {
        return maxval;
	}
	public void setMaxval(Integer maxval) {
		this.maxval = maxval;
	}
    public String getSporttype() {
        return sporttype;
	}
	public void setSporttype(String sporttype) {
		this.sporttype = sporttype;
	}
    public Integer getSetstatus() {
        return setstatus;
	}
	public void setSetstatus(Integer setstatus) {
		this.setstatus = setstatus;
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