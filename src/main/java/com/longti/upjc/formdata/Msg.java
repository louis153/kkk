package com.longti.upjc.formdata;

public class Msg<T> {
	private IHead head;		//消息头
	private T body;		//消息体
	public IHead getHead() {
		return head;
	}

	public void setHead(IHead head) {
		this.head = head;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
