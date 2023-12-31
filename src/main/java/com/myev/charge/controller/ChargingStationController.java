package com.myev.charge.controller;

import com.myev.charge.payload.ChargingStationDto;
import com.myev.charge.payload.ChargingStationResponse;
import com.myev.charge.service.IChargingStationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/charging/stations")
public class ChargingStationController {

    private final IChargingStationService _stationService;

    public ChargingStationController(IChargingStationService stationService) {

        this._stationService = stationService;
    }

    // create charging station
    @PostMapping
    public ResponseEntity<ChargingStationDto> createStation(@RequestBody ChargingStationDto stationDto) {

        var newStation = _stationService.doCreate(stationDto);

        return new ResponseEntity<>(newStation, HttpStatus.CREATED);
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

    // get charging station by id
    @GetMapping("/{id}")
    public ResponseEntity<ChargingStationDto> getStationById(@PathVariable("id") Long id) {

        var station = _stationService.doGetById(id);

        return new ResponseEntity<>(station, HttpStatus.OK);
    }

    // update charging station
    @PutMapping("/{id}")
    public ResponseEntity<ChargingStationDto> updateStation(@RequestBody ChargingStationDto stationDto, @PathVariable("id") Long id) {

        var updatedStation = _stationService.doUpdate(stationDto, id);

        return new ResponseEntity<>(updatedStation, HttpStatus.OK);
    }

    // delete charging station
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStation(@PathVariable("id") Long id) {

        _stationService.doDelete(id);

        return new ResponseEntity<>("Charging Station has been deleted!", HttpStatus.OK);
    }

}
