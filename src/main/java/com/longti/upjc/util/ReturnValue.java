package com.longti.upjc.util;

import com.longti.upjc.util.ErrorMessage.ErrInfo;

public class ReturnValue<T> {
	private String status;
	private String message;
	private T data;
	
	public String getStatus() {
		return status;
	}
	@Deprecated
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	@Deprecated
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public void setMess(ErrInfo errInfo){
		this.status=errInfo.getCode();
		this.message=errInfo.getMessage();
	}
}
