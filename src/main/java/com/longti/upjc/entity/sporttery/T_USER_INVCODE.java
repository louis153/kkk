package com.longti.upjc.entity.sporttery;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户绑定邀请码entity
 */
public class T_USER_INVCODE  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8314376785250012229L;
	
	
	private String user_pin;//用户pin
	private String invitation_code;//邀请码
	private Date firstlogin_time;//首次登录时间
	private int is_bind;//是否绑定
	private String bind_user_pin;//关联用户PIN
	private String bind_invitation_code;//已绑定邀请码
	private Date bind_time;//绑定时间
	public String getUser_pin() {
		return user_pin;
	}
	public void setUser_pin(String user_pin) {
		this.user_pin = user_pin;
	}
	public String getInvitation_code() {
		return invitation_code;
	}
	public void setInvitation_code(String invitation_code) {
		this.invitation_code = invitation_code;
	}
	public Date getFirstlogin_time() {
		return firstlogin_time;
	}
	public void setFirstlogin_time(Date firstlogin_time) {
		this.firstlogin_time = firstlogin_time;
	}
	public int getIs_bind() {
		return is_bind;
	}
	public void setIs_bind(int is_bind) {
		this.is_bind = is_bind;
	}
	public String getBind_user_pin() {
		return bind_user_pin;
	}
	public void setBind_user_pin(String bind_user_pin) {
		this.bind_user_pin = bind_user_pin;
	}
	public String getBind_invitation_code() {
		return bind_invitation_code;
	}
	public void setBind_invitation_code(String bind_invitation_code) {
		this.bind_invitation_code = bind_invitation_code;
	}
	public Date getBind_time() {
		return bind_time;
	}
	public void setBind_time(Date bind_time) {
		this.bind_time = bind_time;
	}
	
	
}
