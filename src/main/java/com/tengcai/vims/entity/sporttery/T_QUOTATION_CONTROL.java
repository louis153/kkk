package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;


/**
 * 销售阀值entity
 */
public class T_QUOTATION_CONTROL implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer id; //
    private Integer xnpktze; //虚拟盘口投注额
    private Integer dcxssx_s; //单场销售上线区间 单位个
    private Integer dcxssx_e; //单场销售上线区间 单位个
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public Integer getXnpktze() {
        return xnpktze;
	}
	public void setXnpktze(Integer xnpktze) {
		this.xnpktze = xnpktze;
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
	public Integer getDcxssx_s() {
		return dcxssx_s;
	}
	public void setDcxssx_s(Integer dcxssx_s) {
		this.dcxssx_s = dcxssx_s;
	}
	public Integer getDcxssx_e() {
		return dcxssx_e;
	}
	public void setDcxssx_e(Integer dcxssx_e) {
		this.dcxssx_e = dcxssx_e;
	}

	
}