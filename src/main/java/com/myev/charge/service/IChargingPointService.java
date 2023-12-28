package com.myev.charge.service;

import com.myev.charge.payload.ChargingPointDto;
import com.myev.charge.payload.ChargingPointResponse;

public interface IChargingPointService {

    ChargingPointDto doCreate(long stationId, ChargingPointDto pointDto);

    ChargingPointResponse doGetAllByStationId(long stationId, int pageNo, int pageSize, String sortBy, String sortDir);

    ChargingPointDto getByIdAndStationId(long stationId, long pointId);

    ChargingPointDto doUpdate(ChargingPointDto pointDto, long pointId);

    void doDelete(long id);
}
