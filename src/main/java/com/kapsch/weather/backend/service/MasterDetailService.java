package com.kapsch.weather.backend.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kapsch.weather.backend.dto.MasterDetailDto;
import com.kapsch.weather.backend.entity.Detail;
import com.kapsch.weather.backend.entity.Master;
import com.kapsch.weather.backend.repository.DetailRepository;
import com.kapsch.weather.backend.repository.MasterRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class MasterDetailService implements IMasterDetailsService{
	
	@Autowired
    private MasterRepository masterRepository;
	
	@Autowired
    private DetailRepository detailRepository;

	@Transactional
	public Master saveMasterDetail(MasterDetailDto masterDetailDto) {
	    return Optional.ofNullable(masterDetailDto)
	            .map(dto -> {
	                Master master = new Master();
	                master.setId(dto.getMaster().getId());
	                master.setLatitude(dto.getMaster().getLatitude());
	                master.setLongitude(dto.getMaster().getLongitude());
	                master.setDetail(dto.getDetail());
	                return master;
	            })
	            .map(masterRepository::save)
	            .orElseThrow(() -> new IllegalArgumentException("MasterDetailDto cannot be null"));
	}


	@Override
	public void exportDetailsToCsv(HttpServletResponse response) throws IOException {
	    List<Detail> details = detailRepository.findAll();

	    // configure the respose to download the file
	    response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; filename=details.csv");

	    try (PrintWriter writer = response.getWriter()) {
	        writer.println("id,temperature,windspeed,winddirection,timeUTC,timeLocalTime");

	        details.stream()
	            .map(detail -> String.join(",",
	                    String.valueOf(detail.getId()),
	                    String.valueOf(detail.getTemperature()),
	                    String.valueOf(detail.getWindspeed()),
	                    String.valueOf(detail.getWinddirection()),
	                    String.valueOf(detail.getTimeUTC()),
	                    String.valueOf(detail.getTimeLocalTime())))
	            .forEach(writer::println);

	        writer.flush();
	    }
	}


}
