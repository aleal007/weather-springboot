package com.kapsch.weather.backend.response;

import java.io.Serializable;

public class ResponseWeather implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2565545069996799573L;

	public ResponseWeather(String status, String code) {
		this.status = status;
		this.code = code;
	}
	
	private String status;
	private String code;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
