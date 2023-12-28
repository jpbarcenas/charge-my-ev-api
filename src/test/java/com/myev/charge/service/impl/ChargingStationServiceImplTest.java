package com.myev.charge.service.impl;

import com.myev.charge.domain.ChargingStation;
import com.myev.charge.domain.Location;
import com.myev.charge.domain.enums.StationStatus;
import com.myev.charge.payload.ChargingStationDto;
import com.myev.charge.payload.LocationDto;
import com.myev.charge.repository.ChargingStationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChargingStationServiceImplTest {

    @Mock
    private ChargingStationRepository _stationRepository;

    @InjectMocks
    private ChargingStationServiceImpl _stationService;

    private static ChargingStationDto _stationDto;
    private static ChargingStation _station;

    private static LocationDto _locationDto;
    private static Location _location;

    private static List<ChargingStationDto> _stationDtoList;
    private static List<ChargingStation> _stationList;

    @BeforeAll
    static void init() throws Exception {
        int recordCount = 25;
        _stationList = new ArrayList<>();
        _stationDtoList = new ArrayList<>();

        for (int i = 1; i <= recordCount; i++) {
            // Setting up the Entities
            Location location = new Location();
            location.setAddress(i + " Main St");
            location.setLatitude(15.0 + i);
            location.setLongitude(-18.0 - i);

            ChargingStation station = new ChargingStation();
            station.setId((long) i);
            station.setLocation(location);
            station.setNumberOfChargingPoints(2);
            station.setStatus(StationStatus.AVAILABLE);

            _stationList.add(station);

            // Setting up the DTOs
            LocationDto locationDto = new LocationDto();
            locationDto.setAddress(i + " Main St");
            locationDto.setLatitude(15.0 + i);
            locationDto.setLongitude(-18.0 - i);

            ChargingStationDto stationDto = new ChargingStationDto();
            stationDto.setId((long) i);
            stationDto.setLocation(locationDto);
            stationDto.setNumberOfChargingPoints(2);
            stationDto.setStatus(StationStatus.AVAILABLE);

            _stationDtoList.add(stationDto);
        }
    }

    @BeforeEach
    void setUp() {
        // Setting up the DTOs
        _locationDto = new LocationDto();
        _locationDto.setAddress("999 Main St");
        _locationDto.setLatitude(115.0);
        _locationDto.setLongitude(-465.0);

        _stationDto = new ChargingStationDto();
        _stationDto.setId(999L);
        _stationDto.setLocation(_locationDto);
        _stationDto.setNumberOfChargingPoints(2);
        _stationDto.setStatus(StationStatus.AVAILABLE);

        _stationDtoList.add(_stationDto);

        // Setting up the Entities
        _location = new Location();
        _location.setAddress("999 Main St");
        _location.setLatitude(115.0);
        _location.setLongitude(-465.0);

        _station = new ChargingStation();
        _station.setId(999L);
        _station.setLocation(_location);
        _station.setNumberOfChargingPoints(2);
        _station.setStatus(StationStatus.AVAILABLE);

        _stationList.add(_station);

    }

    @AfterEach
    void tearDown() {
        _stationList.remove(_station);
        _stationDtoList.remove(_stationDto);

    }

    @Test
    void testDoCreate() {
    }

    @Test
    void testDoGetAll() {
    }

    @Test
    void testDoGetById() {
    }

    @Test
    void testDoUpdate() {
    }

    @Test
    void testDoDelete() {
    }
}