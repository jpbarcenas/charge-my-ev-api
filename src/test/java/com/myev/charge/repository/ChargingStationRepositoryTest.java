package com.myev.charge.repository;

import com.myev.charge.domain.ChargingStation;
import com.myev.charge.domain.Location;
import com.myev.charge.domain.enums.StationStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ChargingStationRepositoryTest {

    @Autowired
    ChargingStationRepository _stationRepository;

    private Location _location;
    private ChargingStation _station;
    private static List<ChargingStation> _stationList;

    @BeforeAll
    public static void init() {
        int recordCount = 5;
        _stationList = new ArrayList<>();

        for (int i = 1; i <= recordCount; i++) {
            Location location = new Location();
            location.setAddress(i + " Main St");
            location.setLatitude(122.0 + i);
            location.setLongitude(-78.0 + i);

            ChargingStation expectedStation = new ChargingStation();
            expectedStation.setId((long) i);
            expectedStation.setLocation(location);
            expectedStation.setNumberOfChargingPoints(2);

            if (i % 2 == 0) {
                expectedStation.setStatus(StationStatus.IN_USE);
            }
            else
            {
                expectedStation.setStatus(StationStatus.AVAILABLE);
            }

            _stationList.add(expectedStation);
        }
    }

    @BeforeEach
    void setUp() {
        _location = new Location();
        _location.setAddress("124 Main St");
        _location.setLatitude(30.0);
        _location.setLongitude(-465.0);

        _station = new ChargingStation();
        _station.setId(124L);
        _station.setLocation(_location);
        _station.setNumberOfChargingPoints(1);
        _station.setStatus(StationStatus.IN_USE);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSave() {
        // given
        ChargingStation expectedStation = _station;

        // when
        ChargingStation actualStation = _stationRepository.save(expectedStation);

        // then
        // assert that the actualStation is not null
        assertNotNull(actualStation);

        // assert that the actualStation has an id
        assertEquals(expectedStation.getId(), actualStation.getId());

        // assert that the actualStation has the same values as the expectedStation
        assertEquals(expectedStation.getLocation().getAddress(), actualStation.getLocation().getAddress());
        assertEquals(expectedStation.getLocation().getLatitude(), actualStation.getLocation().getLatitude());
        assertEquals(expectedStation.getLocation().getLongitude(), actualStation.getLocation().getLongitude());
        assertEquals(expectedStation.getNumberOfChargingPoints(), actualStation.getNumberOfChargingPoints());
        assertEquals(expectedStation.getStatus(), actualStation.getStatus());
    }
}