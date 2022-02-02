package com.tweet.helperClass;

public class ResponseHelper {
	
	private boolean status;
	private Object data;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ResponseHelper(boolean status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseHelper [status=" + status + ", data=" + data + "]";
	}
	

}
