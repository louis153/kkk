package com.longti.upjc.entity.sporttery;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 绑定邀请码设置entity
 */
public class TAB_INVITATION_BIND  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6649173540386984258L;
	private int id;//主键
	private Integer bind_available;//是否可用0否1是
	private BigDecimal bind_fee;//绑定邀请码成功用户获得的GTO数量
	private Integer be_bind_available;//邀请码被绑定奖励是否可用0否1是
	private BigDecimal be_bind_fee_total;//邀请码被绑定的奖励GTO总数
	private BigDecimal be_bind_fee_one;//第一次奖励数量
	private BigDecimal be_bind_fee_two;//第二次奖励数量
	private BigDecimal be_bind_fee_three;//第三次奖励数量
	private BigDecimal be_bind_fee_four;//第四次奖励数量
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getBind_available() {
		return bind_available;
	}
	public void setBind_available(Integer bind_available) {
		this.bind_available = bind_available;
	}
	public BigDecimal getBind_fee() {
		return bind_fee;
	}
	public void setBind_fee(BigDecimal bind_fee) {
		this.bind_fee = bind_fee;
	}
	public Integer getBe_bind_available() {
		return be_bind_available;
	}
	public void setBe_bind_available(Integer be_bind_available) {
		this.be_bind_available = be_bind_available;
	}
	public BigDecimal getBe_bind_fee_total() {
		return be_bind_fee_total;
	}
	public void setBe_bind_fee_total(BigDecimal be_bind_fee_total) {
		this.be_bind_fee_total = be_bind_fee_total;
	}
	public BigDecimal getBe_bind_fee_one() {
		return be_bind_fee_one;
	}
	public void setBe_bind_fee_one(BigDecimal be_bind_fee_one) {
		this.be_bind_fee_one = be_bind_fee_one;
	}
	public BigDecimal getBe_bind_fee_two() {
		return be_bind_fee_two;
	}
	public void setBe_bind_fee_two(BigDecimal be_bind_fee_two) {
		this.be_bind_fee_two = be_bind_fee_two;
	}
	public BigDecimal getBe_bind_fee_three() {
		return be_bind_fee_three;
	}
	public void setBe_bind_fee_three(BigDecimal be_bind_fee_three) {
		this.be_bind_fee_three = be_bind_fee_three;
	}
	public BigDecimal getBe_bind_fee_four() {
		return be_bind_fee_four;
	}
	public void setBe_bind_fee_four(BigDecimal be_bind_fee_four) {
		this.be_bind_fee_four = be_bind_fee_four;
	}
	
	
	
	
}
