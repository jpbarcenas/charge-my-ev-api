package com.myev.charge.service.impl;

import com.myev.charge.payload.ChargingPointDto;
import com.myev.charge.payload.ChargingPointResponse;
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
}
