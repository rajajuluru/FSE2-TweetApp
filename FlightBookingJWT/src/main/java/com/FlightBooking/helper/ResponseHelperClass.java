package com.FlightBooking.helper;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component

public class ResponseHelperClass implements Serializable{
	
	private boolean status;
	private Object data;
	@Override
	public String toString() {
		return "ResponseHelperClass [status=" + status + ", data=" + data + "]";
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public ResponseHelperClass(boolean status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}
	public ResponseHelperClass() {
		super();
	}

}
