package com.kapsch.weather.backend.service;

import java.io.IOException;

import com.kapsch.weather.backend.dto.MasterDetailDto;
import com.kapsch.weather.backend.entity.Master;

import jakarta.servlet.http.HttpServletResponse;

public interface IMasterDetailsService {
	
	public Master saveMasterDetail(MasterDetailDto masterDetailDto);
	public void exportDetailsToCsv(HttpServletResponse response) throws IOException;

}
