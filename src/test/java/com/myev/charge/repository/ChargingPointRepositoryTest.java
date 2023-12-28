package com.myev.charge.repository;

import com.myev.charge.domain.ChargerType;
import com.myev.charge.domain.ChargingPoint;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ChargingPointRepositoryTest {

    @Autowired
    ChargingPointRepository _pointRepository;

    @Autowired
    ChargingStationRepository _stationRepository;

    private ChargingPoint _point;
    private ChargerType _chargerType;
    private Location _location;
    private ChargingStation _station;
    private static List<ChargingStation> _stationList;
    private static List<ChargingPoint> _pointList;

    @BeforeAll
    public static void init() {
        int stationCount = 5;
        int pointCount = 2;
        _stationList = new ArrayList<>();

        for (int i = 1; i <= stationCount; i++) {
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

            _pointList = new ArrayList<>();

            for (int j = 1; j <= pointCount; j++) {

                ChargingPoint point = new ChargingPoint();
                ChargerType chargerType = new ChargerType();

                if (j % 2 == 0) {
                    chargerType.setType("DC");
                    chargerType.setSpeed(10);
                    chargerType.setPrice(15);
                    point.setPowerLevel(75);
                }
                else
                {
                    chargerType.setType("AC");
                    chargerType.setSpeed(5);
                    chargerType.setPrice(8);
                    point.setPowerLevel(100);
                }

                point.setId((long) j);
                point.setChargingStation(expectedStation);
                point.setChargerType(chargerType);

                _pointList.add(point);
            }
        }
    }

    @BeforeEach
    void setUp() {
        // setting up the test location
        _location = new Location();
        _location.setAddress("124 Main St");
        _location.setLatitude(30.0);
        _location.setLongitude(-465.0);

        // setting up the test station
        _station = new ChargingStation();
        _station.setId(124L);
        _station.setLocation(_location);
        _station.setNumberOfChargingPoints(1);
        _station.setStatus(StationStatus.IN_USE);

        _stationList.add(_station);

        // setting up the test charger type
        _chargerType = new ChargerType();
        _chargerType.setType("DC");
        _chargerType.setSpeed(10);
        _chargerType.setPrice(15);

        // setting up the test charging point
        _point = new ChargingPoint();
        _point.setPowerLevel(75);
        _point.setId(115L);
        _point.setChargingStation(_station);
        _point.setChargerType(_chargerType);

        _pointList.add(_point);
    }

    @AfterEach
    void tearDown() {
        _pointList.remove(_point);
        _stationList.remove(_station);
        _stationRepository.deleteAll();
        _pointRepository.deleteAll();
    }

    @Test
    void testSave() {
        // given
        ChargingPoint expectedPoint = _point;

        _stationRepository.saveAll(_stationList);

        // when
        ChargingPoint actualPoint = _pointRepository.save(expectedPoint);

        // then
        // assert that the actualPoint is not null
        assertNotNull(actualPoint);

        // assert that the actualPoint has an id
        assertNotNull(actualPoint.getId());

        // assert that the actualPoint has the same values as the expectedPoint
        assertEquals(expectedPoint.getId(), actualPoint.getId());
        assertEquals(expectedPoint.getChargerType().getType(), actualPoint.getChargerType().getType());
        assertEquals(expectedPoint.getChargerType().getSpeed(), actualPoint.getChargerType().getSpeed());
        assertEquals(expectedPoint.getChargerType().getPrice(), actualPoint.getChargerType().getPrice());
        assertEquals(expectedPoint.getPowerLevel(), actualPoint.getPowerLevel());

        // assert that the actualPoint has the same Station as the expectedPoint
        assertEquals(expectedPoint.getChargingStation().getId(), actualPoint.getChargingStation().getId());
        assertEquals(expectedPoint.getChargingStation().getLocation().getAddress(), actualPoint.getChargingStation().getLocation().getAddress());
        assertEquals(expectedPoint.getChargingStation().getLocation().getLatitude(), actualPoint.getChargingStation().getLocation().getLatitude());
        assertEquals(expectedPoint.getChargingStation().getLocation().getLongitude(), actualPoint.getChargingStation().getLocation().getLongitude());
        assertEquals(expectedPoint.getChargingStation().getNumberOfChargingPoints(), actualPoint.getChargingStation().getNumberOfChargingPoints());
        assertEquals(expectedPoint.getChargingStation().getStatus(), actualPoint.getChargingStation().getStatus());

    }

}
