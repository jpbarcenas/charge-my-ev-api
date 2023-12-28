package com.myev.charge.controller;

import com.myev.charge.service.IChargingPointService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/charging/stations/{stationId}")
public class ChargingPointController {

    private final IChargingPointService _pointService;

    public ChargingPointController(IChargingPointService pointService) {
        this._pointService = pointService;
    }

}
