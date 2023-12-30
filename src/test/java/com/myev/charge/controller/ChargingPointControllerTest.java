package com.myev.charge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myev.charge.controller.ChargingPointController;
import com.myev.charge.domain.enums.StationStatus;
import com.myev.charge.payload.*;
import com.myev.charge.service.IChargingPointService;
import com.myev.charge.service.IChargingStationService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChargingPointController.class)
class ChargingPointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IChargingPointService _chargingPointService;

    @MockBean
    private IChargingStationService _chargingStationService;

    @Autowired
    private ObjectMapper _mapper;

    private LocationDto _locationDto;

    private ChargingStationDto _stationDto;

    private ChargerTypeDto _chargerTypeDto;

    private ChargingPointDto _pointDto;

    @BeforeEach
    void setUp() {
        // create a charging Station
        _locationDto = new LocationDto();
        _locationDto.setAddress("123 Main St");
        _locationDto.setLatitude(85.0);
        _locationDto.setLongitude(-38.0);

        _stationDto = new ChargingStationDto();
        _stationDto.setId(998L);
        _stationDto.setNumberOfChargingPoints(2);
        _stationDto.setStatus(StationStatus.AVAILABLE);
        _stationDto.setLocation(_locationDto);

        // create a charging point
        _chargerTypeDto = new ChargerTypeDto();
        _chargerTypeDto.setType("AC");
        _chargerTypeDto.setSpeed(20);
        _chargerTypeDto.setPrice(30);

        _pointDto = new ChargingPointDto();
        _pointDto.setId(99L);
        _pointDto.setChargerType(_chargerTypeDto);
        _pointDto.setPowerLevel(100);
        _pointDto.setChargingStation(_stationDto);

    }

    @AfterEach
    void tearDown() {

    }

    @DisplayName("Test Create a charging point")
    @Test
    void createPoint() throws Exception {

    }

    @Test
    void getAllByStationId() {
    }

    @Test
    void getPointByIdAndStationId() {
    }

    @Test
    void updatePoint() {
    }

    @Test
    void deletePoint() {
    }
}