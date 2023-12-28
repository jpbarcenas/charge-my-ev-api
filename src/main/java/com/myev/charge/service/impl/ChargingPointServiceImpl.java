package com.myev.charge.service.impl;

import com.myev.charge.domain.ChargerType;
import com.myev.charge.domain.ChargingPoint;
import com.myev.charge.payload.ChargerTypeDto;
import com.myev.charge.payload.ChargingPointDto;
import com.myev.charge.payload.ChargingPointResponse;
import com.myev.charge.payload.ChargingStationDto;
import com.myev.charge.repository.ChargingPointRepository;
import com.myev.charge.repository.ChargingStationRepository;
import com.myev.charge.service.IChargingPointService;

public class ChargingPointServiceImpl implements IChargingPointService {

    private final ChargingPointRepository _pointRepository;

    private final ChargingStationRepository _stationRepository;

    public ChargingPointServiceImpl(ChargingPointRepository pointRepository, ChargingStationRepository stationRepository) {
        this._pointRepository = pointRepository;
        this._stationRepository = stationRepository;
    }

    @Override
    public ChargingPointDto doCreate(long stationId, ChargingPointDto pointDto) {
        return null;
    }

    @Override
    public ChargingPointResponse doGetAllByStationId(long stationId, int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
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
}
