package com.myev.charge.service.impl;

import com.myev.charge.payload.*;
import com.myev.charge.repository.ChargingStationRepository;
import com.myev.charge.service.IChargingStationService;
import org.springframework.stereotype.Service;


@Service
public class ChargingStationServiceImpl implements IChargingStationService {

    private final ChargingStationRepository _stationRepository;

    public ChargingStationServiceImpl(ChargingStationRepository stationRepository) {
        this._stationRepository = stationRepository;
    }

    @Override
    public ChargingStationDto doCreate(ChargingStationDto stationDto) {
        return null;
    }

    @Override
    public ChargingStationResponse doGetAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public ChargingStationDto doGetAllById(long id) {
        return null;
    }

    @Override
    public ChargingStationDto doUpdate(ChargingStationDto stationDto, long id) {
        return null;
    }

    @Override
    public void doDelete(long id) {

    }
}
