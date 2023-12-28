package com.myev.charge.controller;

import com.myev.charge.payload.ChargingPointDto;
import com.myev.charge.payload.ChargingPointResponse;
import com.myev.charge.service.IChargingPointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/charging/stations/{stationId}")
public class ChargingPointController {

    private final IChargingPointService _pointService;

    public ChargingPointController(IChargingPointService pointService) {
        this._pointService = pointService;
    }

    // create charging point
    @PostMapping("/points")
    public ResponseEntity<ChargingPointDto> createPoint(
            @PathVariable("stationId") long stationsId,
            @RequestBody ChargingPointDto pointDto) {

        var newPoint = _pointService.doCreate(stationsId, pointDto);

        return new ResponseEntity<>(newPoint, HttpStatus.CREATED);
    }


    // get all charging points with pagination and sorting
    @GetMapping("/points")
    public ResponseEntity<ChargingPointResponse> getAllByStationId(
            @PathVariable("stationId") long stationsId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return new ResponseEntity<>(_pointService.doGetAllByStationId(stationsId, pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }
}
