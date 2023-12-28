package com.myev.charge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myev.charge.domain.enums.StationStatus;
import com.myev.charge.payload.ChargingStationDto;
import com.myev.charge.payload.LocationDto;
import com.myev.charge.service.IChargingStationService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ChargingStationController.class)
class ChargingStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IChargingStationService _stationService;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createStation() throws Exception {
        // given
        LocationDto locationDto = new LocationDto();
        locationDto.setAddress("123 Main St");
        locationDto.setLatitude(85.0);
        locationDto.setLongitude(-38.0);

        ChargingStationDto stationDto = new ChargingStationDto();
        stationDto.setId(998L);
        stationDto.setNumberOfChargingPoints(2);
        stationDto.setStatus(StationStatus.AVAILABLE);
        stationDto.setLocation(locationDto);

        BDDMockito.given(_stationService.doCreate(ArgumentMatchers.any(ChargingStationDto.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when
        ResultActions response = mockMvc.perform(post("/api/charging/stations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stationDto)));

        // then
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(998)))
                .andExpect(jsonPath("$.numberOfChargingPoints", CoreMatchers.is(2)))
                .andExpect(jsonPath("$.status", CoreMatchers.is("AVAILABLE")))
                .andExpect(jsonPath("$.location.address", CoreMatchers.is("123 Main St")))
                .andExpect(jsonPath("$.location.latitude", CoreMatchers.is(85.0)))
                .andExpect(jsonPath("$.location.longitude", CoreMatchers.is(-38.0)));

    }

    @Test
    void getAllStations() {
    }

    @Test
    void getStationById() {
    }

    @Test
    void updateStation() {
    }

    @Test
    void deleteStation() {
    }
}