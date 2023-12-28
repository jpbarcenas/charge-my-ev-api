package com.myev.charge.service.impl;

import com.myev.charge.domain.ChargerType;
import com.myev.charge.domain.ChargingPoint;
import com.myev.charge.domain.ChargingStation;
import com.myev.charge.exception.MaxChargingPointsReachedException;
import com.myev.charge.exception.ResourceNotFoundException;
import com.myev.charge.payload.*;
import com.myev.charge.repository.ChargingPointRepository;
import com.myev.charge.repository.ChargingStationRepository;
import com.myev.charge.service.IChargingPointService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargingPointServiceImpl implements IChargingPointService {

    private final ChargingPointRepository _pointRepository;

    private final ChargingStationRepository _stationRepository;

    public ChargingPointServiceImpl(ChargingPointRepository pointRepository, ChargingStationRepository stationRepository) {
        this._pointRepository = pointRepository;
        this._stationRepository = stationRepository;
    }

    @Override
    public ChargingPointDto doCreate(long stationId, ChargingPointDto pointDto) {

        // get charging station by id
        ChargingStation station = _stationRepository.findById(stationId)
                .orElseThrow(() -> new ResourceNotFoundException("Charging Station", "id", stationId));

        // Get number of charging points of the station
        int maxPoints = station.getNumberOfChargingPoints();

        // get total number of charging points of the station
        int totalPoints = _pointRepository.findAll().size();


        // Check if the station has reached the maximum number of charging points
        if (totalPoints >= maxPoints) {
            throw new MaxChargingPointsReachedException("Charging Station", stationId, maxPoints);
        }

        // convert Dto to Entity
        ChargingPoint point = mapToEntity(pointDto);

        // set charging station to charging point
        point.setChargingStation(station);

        // save Entity to database
        ChargingPoint newPoint = _pointRepository.save(point);

        return mapToDto(newPoint);

    }

    @Override
    public ChargingPointResponse doGetAllByStationId(long stationId, int pageNo, int pageSize, String sortBy, String sortDir) {
        // create Sort object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        // create Pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // get Page object where ChargingStation id is equal to stationId
        Page<ChargingPoint> pointPages = _pointRepository.findAll(pageable);

        // get content from Page object
        List<ChargingPoint> pointList = pointPages.getContent();

        // convert List of Entity to List of Dto
        List<ChargingPointVO> content = pointList.stream()
                .filter(point -> point.getChargingStation().getId().equals(stationId))
                .map(this::mapToVO).collect(Collectors.toList());

        ChargingPointResponse pointResponse = new ChargingPointResponse();
        pointResponse.setContent(content);
        pointResponse.setPageNo(pointPages.getNumber());
        pointResponse.setPageSize(pointPages.getSize());
        pointResponse.setTotalElements(pointPages.getTotalElements());
        pointResponse.setTotalPages(pointPages.getTotalPages());
        pointResponse.setLast(pointPages.isLast());

        return pointResponse;
    }

    @Override
    public ChargingPointDto getByIdAndStationId(long stationId, long pointId) {
        return null;
    }

    @Override
    public ChargingPointDto doUpdate(ChargingPointDto pointDto, long pointId) {
        return null;
    }

    @Override
    public void doDelete(long id) {

    }

    private ChargingPointDto mapToDto(ChargingPoint chargingPoint) {

        // getting the charger type from charging point
        ChargerTypeDto chargerTypeDto = new ChargerTypeDto();
        chargerTypeDto.setType(chargingPoint.getChargerType().getType());
        chargerTypeDto.setPrice(chargingPoint.getChargerType().getPrice());
        chargerTypeDto.setSpeed(chargingPoint.getChargerType().getSpeed());

        // getting the charging station from charging point
        ChargingStationDto chargingStationDto = new ChargingStationDto();
        chargingStationDto.setId(chargingPoint.getChargingStation().getId());
        chargingStationDto.setNumberOfChargingPoints(chargingPoint.getChargingStation().getNumberOfChargingPoints());
        chargingStationDto.setStatus(chargingPoint.getChargingStation().getStatus());

        // create Dto object
        ChargingPointDto chargingPointDto = new ChargingPointDto();
        chargingPointDto.setId(chargingPoint.getId());
        chargingPointDto.setPowerLevel(chargingPoint.getPowerLevel());
        chargingPointDto.setChargerType(chargerTypeDto);
        chargingPointDto.setChargingStation(chargingStationDto);

        return chargingPointDto;
    }

    private ChargingPoint mapToEntity(ChargingPointDto chargingPointDto) {

        // getting the charger type from charging point
        ChargerType chargerType = new ChargerType();
        chargerType.setType(chargingPointDto.getChargerType().getType());
        chargerType.setPrice(chargingPointDto.getChargerType().getPrice());
        chargerType.setSpeed(chargingPointDto.getChargerType().getSpeed());

        // create Dto object
        ChargingPoint chargingPoint = new ChargingPoint();
        chargingPoint.setId(chargingPointDto.getId());
        chargingPoint.setPowerLevel(chargingPointDto.getPowerLevel());
        chargingPoint.setChargerType(chargerType);

        return chargingPoint;
    }

    private ChargingPointVO mapToVO(ChargingPoint point) {

            // getting the charger type from charging point
            ChargerTypeVO chargerTypeVO = new ChargerTypeVO();
            chargerTypeVO.setType(point.getChargerType().getType());
            chargerTypeVO.setPrice(point.getChargerType().getPrice());
            chargerTypeVO.setSpeed(point.getChargerType().getSpeed());

            // create VO object
            ChargingPointVO pointVO = new ChargingPointVO();
            pointVO.setStationId(point.getChargingStation().getId());
            pointVO.setPowerLevel(point.getPowerLevel());
            pointVO.setChargerType(chargerTypeVO);

            return pointVO;
    }
}
