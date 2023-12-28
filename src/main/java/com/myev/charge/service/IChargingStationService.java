package com.myev.charge.service;

import com.myev.charge.payload.ChargingStationDto;
import com.myev.charge.payload.ChargingStationResponse;

public interface IChargingStationService {

    ChargingStationDto doCreate(ChargingStationDto stationDto);

    ChargingStationResponse doGetAll(int pageNo, int pageSize, String sortBy, String sortDir);

    ChargingStationDto doGetById(long id);

    ChargingStationDto doUpdate(ChargingStationDto stationDto, long id);

    void doDelete(long id);
}
