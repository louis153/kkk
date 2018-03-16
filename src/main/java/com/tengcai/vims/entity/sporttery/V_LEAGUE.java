package com.tengcai.vims.entity.sporttery;
import java.io.Serializable;
/**
 * VIEWentity
 */
public class V_LEAGUE implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4466437930315751026L;
	


    private String leaguename; //比赛名称
    private String leaguecount;//比赛场次
    private String saleday;//销售日
    private String position;//频道
    private String rem_issue;
    public String getLeaguename() {
        return leaguename;
	}
	public void setLeaguename(String leaguename) {
		this.leaguename = leaguename;
	}
   
	public String getLeaguecount() {
		return leaguecount;
	}
	public void setLeaguecount(String leaguecount) {
		this.leaguecount = leaguecount;
	}
	public String getSaleday() {
		return saleday;
	}
	public void setSaleday(String saleday) {
		this.saleday = saleday;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRem_issue() {
		return rem_issue;
	}
	public void setRem_issue(String rem_issue) {
		this.rem_issue = rem_issue;
	}
	

	
}