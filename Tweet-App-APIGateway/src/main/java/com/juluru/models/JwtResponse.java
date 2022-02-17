package com.juluru.models;

import org.springframework.stereotype.Component;


@Component
public class JwtResponse {

    public JwtResponse(String data,boolean statu) {
		super();
		this.data = data;
		this.status=statu;
	}
    public JwtResponse() {
  		super();
  		
  	}

    private boolean status=false;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	private String data;

	public String getdata() {
		return data;
	}

	public void setdata(String data) {
		this.data = data;
	}
}
