package com.longti.upjc.entity.sporttery;
import java.io.Serializable;


/**
 * 销售阈值entity
 */
public class TAB_SALES_THRESHOLD implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    private Integer id; //
    private String currency; //币种 GTO ETH UZ(U钻)
    private Long xnpktze; //虚拟盘口投注额 存储为实际数值的100W倍
    private Long xssx_min; //销售上限区间 最小值  存储为实际数值的100W倍
    private Long xssx_max; //销售上限区间 最大值  存储为实际数值的100W倍
    private Long single_lottery_max; //用户单次投注上限  存储为实际数值的100W倍
    private Long single_match_max; //用户单玩法投注上限  存储为实际数值的100W倍
    private Integer row_start;//开始行
    private Integer page_size;//每页行数


    public Integer getId() {
        return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    public String getCurrency() {
        return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
    public Long getXnpktze() {
        return xnpktze;
	}
	public void setXnpktze(Long xnpktze) {
		this.xnpktze = xnpktze;
	}
    public Long getXssx_min() {
        return xssx_min;
	}
	public void setXssx_min(Long xssx_min) {
		this.xssx_min = xssx_min;
	}
    public Long getXssx_max() {
        return xssx_max;
	}
	public void setXssx_max(Long xssx_max) {
		this.xssx_max = xssx_max;
	}
    public Long getSingle_lottery_max() {
        return single_lottery_max;
	}
	public void setSingle_lottery_max(Long single_lottery_max) {
		this.single_lottery_max = single_lottery_max;
	}
    public Long getSingle_match_max() {
        return single_match_max;
	}
	public void setSingle_match_max(Long single_match_max) {
		this.single_match_max = single_match_max;
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