package com.longti.upjc.entity.sporttery;
import java.io.Serializable;


/**
 * VIEWentity
 */
public class V_LEAGUE implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9058820038302900712L;
	private  Integer leaguecount;//比赛场数
	private String electronic_code;//币种
    private String leaguename; //比赛名称
    private String saleday;//销售日
    private Integer row_start;//开始行
    private Integer page_size;//每页行数
  

    public String getLeaguename() {
        return leaguename;
	}
	public void setLeaguename(String leaguename) {
		this.leaguename = leaguename;
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
	public Integer getLeaguecount() {
		return leaguecount;
	}
	public void setLeaguecount(Integer leaguecount) {
		this.leaguecount = leaguecount;
	}
	public String getSaleday() {
		return saleday;
	}
	public void setSaleday(String saleday) {
		this.saleday = saleday;
	}
	public String getElectronic_code() {
		return electronic_code;
	}
	public void setElectronic_code(String electronic_code) {
		this.electronic_code = electronic_code;
	}
	

	
}