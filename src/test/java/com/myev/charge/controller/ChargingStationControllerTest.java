package com.myev.charge.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myev.charge.domain.enums.StationStatus;
import com.myev.charge.payload.*;
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

    @DisplayName("Test Create Charging Station")
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

        given(_stationService.doCreate(any(ChargingStationDto.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when
        ResultActions response = mockMvc.perform(post("/api/charging/stations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(stationDto)));

        // then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(998)))
                .andExpect(jsonPath("$.numberOfChargingPoints", CoreMatchers.is(2)))
                .andExpect(jsonPath("$.status", CoreMatchers.is("AVAILABLE")))
                .andExpect(jsonPath("$.location.address", CoreMatchers.is("123 Main St")))
                .andExpect(jsonPath("$.location.latitude", CoreMatchers.is(85.0)))
                .andExpect(jsonPath("$.location.longitude", CoreMatchers.is(-38.0)));

    }

    @DisplayName("Test Get All Charging Stations with Pagination and Sorting")
    @Test
    void getAllStations() throws Exception {
        // given
        int recordCount = 15;
        List<ChargingStationVO> stationVOList = new ArrayList<>();

        for (int i = 1; i <= recordCount; i++){
            LocationVO locationVO = new LocationVO();
            locationVO.setAddress(i + " Main St");
            locationVO.setLatitude(45.0 + i);
            locationVO.setLongitude(-38.0 - i);

            ChargingStationVO stationDto = new ChargingStationVO();
            stationDto.setStationId((long) i);
            stationDto.setNumberOfChargingPoints(2);
            stationDto.setLocation(locationVO);

            if (i % 2 == 0)
            {
                stationDto.setStatus(StationStatus.IN_USE);
            }
            else
            {
                stationDto.setStatus(StationStatus.AVAILABLE);
            }

            stationVOList.add(stationDto);
        }

        ChargingStationResponse stationResponse = new ChargingStationResponse();
        stationResponse.setContent(stationVOList);
        stationResponse.setTotalElements(stationVOList.size());
        stationResponse.setPageNo(0);
        stationResponse.setPageSize(10);
        stationResponse.setTotalPages(3);
        stationResponse.setLast(false);


        given(_stationService.doGetAll(0, 10, "id", "asc"))
                .willReturn(stationResponse);

        // when
        ResultActions response = mockMvc.perform(get("/api/charging/stations")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content", Matchers.hasSize(15)));

        // verify that each element of the response content is the same as the corresponding element of the stationVOList
        for (int i = 0; i < recordCount; i++) {
            response.andExpect(jsonPath("$.content[" + i + "].stationId", CoreMatchers.is(i + 1)))
                    .andExpect(jsonPath("$.content[" + i + "].numberOfChargingPoints", CoreMatchers.is(2)))
                    .andExpect(jsonPath("$.content[" + i + "].location.address", CoreMatchers.is((i + 1) + " Main St")))
                    .andExpect(jsonPath("$.content[" + i + "].location.latitude", CoreMatchers.is(45.0 + i + 1)))
                    .andExpect(jsonPath("$.content[" + i + "].location.longitude", CoreMatchers.is(-38.0 - i - 1)));
        }
    }

    @DisplayName("Test Get Charging Station By Id with VALID Id")
    @Test
    void getStationByIdValid() throws Exception {
        // given
        int recordCount = 15;
        List<ChargingStationVO> stationVOList = new ArrayList<>();

        for (int i = 1; i <= recordCount; i++){
            LocationVO locationVO = new LocationVO();
            locationVO.setAddress(i + " Main St");
            locationVO.setLatitude(45.0 + i);
            locationVO.setLongitude(-38.0 - i);

            ChargingStationVO stationDto = new ChargingStationVO();
            stationDto.setStationId((long) i);
            stationDto.setNumberOfChargingPoints(2);
            stationDto.setLocation(locationVO);

            if (i % 2 == 0)
            {
                stationDto.setStatus(StationStatus.IN_USE);
            }
            else
            {
                stationDto.setStatus(StationStatus.AVAILABLE);
            }

            stationVOList.add(stationDto);
        }

        ChargingStationVO stationVO = stationVOList.get(4);

        LocationDto locationDto = new LocationDto();
        locationDto.setAddress(stationVO.getLocation().getAddress());
        locationDto.setLatitude(stationVO.getLocation().getLatitude());
        locationDto.setLongitude(stationVO.getLocation().getLongitude());

        ChargingStationDto stationDto = new ChargingStationDto();
        stationDto.setId(stationVO.getStationId());
        stationDto.setNumberOfChargingPoints(stationVO.getNumberOfChargingPoints());
        stationDto.setStatus(stationVO.getStatus());
        stationDto.setLocation(locationDto);

        given(_stationService.doGetById(stationDto.getId()))
                .willReturn(stationDto);

        // when
        ResultActions response = mockMvc.perform(get("/api/charging/stations/" + stationDto.getId())
                .contentType(MediaType.APPLICATION_JSON));

        // then
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(stationDto.getId().intValue())))
                .andExpect(jsonPath("$.numberOfChargingPoints", CoreMatchers.is(stationDto.getNumberOfChargingPoints())))
                .andExpect(jsonPath("$.status", CoreMatchers.is(stationDto.getStatus().toString())))
                .andExpect(jsonPath("$.location.address", CoreMatchers.is(stationDto.getLocation().getAddress())))
                .andExpect(jsonPath("$.location.latitude", CoreMatchers.is(stationDto.getLocation().getLatitude())))
                .andExpect(jsonPath("$.location.longitude", CoreMatchers.is(stationDto.getLocation().getLongitude())));
    }

    @DisplayName("Test Get Charging Station By Id with INVALID Id")
    @Test
    void getStationByIdInvalid() throws Exception {
        // given
        long stationId = 300L;

        LocationDto locationDto = new LocationDto();
        locationDto.setAddress("123 Main St");
        locationDto.setLatitude(85.0);
        locationDto.setLongitude(-38.0);

        ChargingStationDto stationDto = new ChargingStationDto();
        stationDto.setId(998L);
        stationDto.setNumberOfChargingPoints(2);
        stationDto.setStatus(StationStatus.AVAILABLE);
        stationDto.setLocation(locationDto);

        given(_stationService.doGetById(stationId)).willReturn(null);

        // when
        ResultActions response = mockMvc.perform(get("/api/charging/stations/" + stationId));

        // then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateStation() throws Exception {
        // given
        long stationId = 998L;

        LocationDto locationDto = new LocationDto();
        locationDto.setAddress("123 Main St");
        locationDto.setLatitude(85.0);
        locationDto.setLongitude(-38.0);

        ChargingStationDto stationDto = new ChargingStationDto();
        stationDto.setId(998L);
        stationDto.setNumberOfChargingPoints(2);
        stationDto.setStatus(StationStatus.AVAILABLE);
        stationDto.setLocation(locationDto);

        // new stationDto
        LocationDto updatedLocationDto = new LocationDto();
        updatedLocationDto.setAddress("130 Main St");
        updatedLocationDto.setLatitude(86.0);
        updatedLocationDto.setLongitude(-48.0);

        ChargingStationDto updatedStationDto = new ChargingStationDto();
        updatedStationDto.setId(998L);
        updatedStationDto.setNumberOfChargingPoints(3);
        updatedStationDto.setStatus(StationStatus.IN_USE);
        updatedStationDto.setLocation(updatedLocationDto);

        given(_stationService.doGetById(stationId)).willReturn(stationDto);

        given(_stationService.doUpdate(any(stationDto.getClass()), any(Long.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when
        ResultActions response = mockMvc.perform(put("/api/charging/stations/" + stationId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedStationDto)));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", CoreMatchers.is(updatedStationDto.getId().intValue())))
                .andExpect(jsonPath("$.numberOfChargingPoints", CoreMatchers.is(updatedStationDto.getNumberOfChargingPoints())))
                .andExpect(jsonPath("$.status", CoreMatchers.is(updatedStationDto.getStatus().toString())))
                .andExpect(jsonPath("$.location.address", CoreMatchers.is(updatedStationDto.getLocation().getAddress())))
                .andExpect(jsonPath("$.location.latitude", CoreMatchers.is(updatedStationDto.getLocation().getLatitude())))
                .andExpect(jsonPath("$.location.longitude", CoreMatchers.is(updatedStationDto.getLocation().getLongitude())));
    }

    @Test
    void updateStationInvalidId() throws Exception {
        // given
        long stationId = 998L;

        LocationDto locationDto = new LocationDto();
        locationDto.setAddress("123 Main St");
        locationDto.setLatitude(85.0);
        locationDto.setLongitude(-38.0);

        ChargingStationDto stationDto = new ChargingStationDto();
        stationDto.setId(998L);
        stationDto.setNumberOfChargingPoints(2);
        stationDto.setStatus(StationStatus.AVAILABLE);
        stationDto.setLocation(locationDto);

        // new stationDto
        LocationDto updatedLocationDto = new LocationDto();
        updatedLocationDto.setAddress("130 Main St");
        updatedLocationDto.setLatitude(86.0);
        updatedLocationDto.setLongitude(-48.0);

        ChargingStationDto updatedStationDto = new ChargingStationDto();
        updatedStationDto.setId(998L);
        updatedStationDto.setNumberOfChargingPoints(3);
        updatedStationDto.setStatus(StationStatus.IN_USE);
        updatedStationDto.setLocation(updatedLocationDto);

        given(_stationService.doGetById(stationId)).willReturn(null);

        given(_stationService.doUpdate(any(stationDto.getClass()), any(Long.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        // when
        ResultActions response = mockMvc.perform(put("/api/charging/stations/" + stationId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedStationDto)));

        // then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void deleteStation() {
    }
}