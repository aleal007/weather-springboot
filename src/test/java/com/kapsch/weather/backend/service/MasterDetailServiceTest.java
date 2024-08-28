package com.kapsch.weather.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kapsch.weather.backend.dto.MasterDetailDto;
import com.kapsch.weather.backend.entity.Detail;
import com.kapsch.weather.backend.entity.Master;
import com.kapsch.weather.backend.repository.DetailRepository;
import com.kapsch.weather.backend.repository.MasterRepository;

import jakarta.servlet.http.HttpServletResponse;

class MasterDetailServiceTest {

    @InjectMocks
    private MasterDetailService masterDetailService;

    @Mock
    private MasterRepository masterRepository;

    @Mock
    private DetailRepository detailRepository;

    @Mock
    private HttpServletResponse httpResponse;

    @Mock
    private PrintWriter writer;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        when(httpResponse.getWriter()).thenReturn(writer);
    }

    @Test
    void testSaveMasterDetailSuccess() {
        MasterDetailDto masterDetailDto = new MasterDetailDto();
        Master masterEntity = new Master();
        masterEntity.setId(1L);
        masterEntity.setLatitude(52.52);
        masterEntity.setLongitude(13.41);
        masterDetailDto.setMaster(masterEntity);

        when(masterRepository.save(any(Master.class))).thenReturn(masterEntity);

        Master savedMaster = masterDetailService.saveMasterDetail(masterDetailDto);

        assertNotNull(savedMaster);
        assertEquals(masterEntity.getId(), savedMaster.getId());
        verify(masterRepository, times(1)).save(any(Master.class));
    }

    @Test
    void testSaveMasterDetailThrowsExceptionWhenDtoIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            masterDetailService.saveMasterDetail(null);
        });
    }

    @Test
    void testExportDetailsToCsv() throws IOException {
        Detail detail = new Detail();
        detail.setId(1L);
        detail.setTemperature(22.5);
        detail.setWindspeed(5.2);
        detail.setWinddirection(180);
        detail.setTimeUTC("2024-08-27T12:00:00Z");
        detail.setTimeLocalTime("2024-08-27T14:00:00+02:00");

        List<Detail> details = Collections.singletonList(detail);
        when(detailRepository.findAll()).thenReturn(details);

        masterDetailService.exportDetailsToCsv(httpResponse);

        verify(detailRepository, times(1)).findAll();
        verify(writer, times(1)).println("id,temperature,windspeed,winddirection,timeUTC,timeLocalTime");
        verify(writer, times(1)).flush();
    }

    @Test
    void testExportDetailsToCsvWithEmptyList() throws IOException {
        when(detailRepository.findAll()).thenReturn(Collections.emptyList());

        masterDetailService.exportDetailsToCsv(httpResponse);

        verify(detailRepository, times(1)).findAll();
        verify(writer, times(1)).println("id,temperature,windspeed,winddirection,timeUTC,timeLocalTime");
        verify(writer, times(1)).flush();
    }
}
