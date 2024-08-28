package com.kapsch.weather.backend.dto;

import com.kapsch.weather.backend.entity.Detail;
import com.kapsch.weather.backend.entity.Master;

public class MasterDetailDto {
	
	private Master master;
	private Detail detail;
	
	public Master getMaster() {
		return master;
	}
	public void setMaster(Master master) {
		this.master = master;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}

}
