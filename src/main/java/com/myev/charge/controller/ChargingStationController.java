package com.myev.charge.controller;

import com.myev.charge.payload.ChargingStationResponse;
import com.myev.charge.service.IChargingStationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/charging/stations")
public class ChargingStationController {

    private final IChargingStationService _stationService;

    public ChargingStationController(IChargingStationService stationService) {

        this._stationService = stationService;
    }

    // get all charging stations with pagination and sorting
    @GetMapping
    public ResponseEntity<ChargingStationResponse> getAllStations(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return new ResponseEntity<>(_stationService.doGetAll(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }
}
