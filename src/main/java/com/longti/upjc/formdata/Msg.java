package com.longti.upjc.formdata;

public class Msg<T> {
	private Head head;		//消息头
	private T body;		//消息体
	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
