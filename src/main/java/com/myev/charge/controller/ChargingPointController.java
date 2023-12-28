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

    // get charging point by id
    @GetMapping("/points/{pointId}")
    public ResponseEntity<ChargingPointDto> getPointByIdAndStationId(
            @PathVariable("stationId") long stationId,
            @PathVariable("pointId") long pointId) {

        var point = _pointService.getByIdAndStationId(stationId, pointId);

        return new ResponseEntity<>(point, HttpStatus.OK);
    }

    // update charging point
    @PutMapping("/points/{pointId}")
    public ResponseEntity<ChargingPointDto> updatePoint(@RequestBody ChargingPointDto pointDto, @PathVariable("pointId") long pointId) {

        var updatedPoint = _pointService.doUpdate(pointDto, pointId);

        return new ResponseEntity<>(updatedPoint, HttpStatus.OK);
    }



    // delete charging point
    @DeleteMapping("/points/{pointId}")
    public ResponseEntity<String> deletePoint(
            @PathVariable("stationId") long stationId,
            @PathVariable("pointId") long pointId) {
        _pointService.doDelete(stationId, pointId);
        return new ResponseEntity<>("Charging point deleted successfully!", HttpStatus.OK);
    }

}
