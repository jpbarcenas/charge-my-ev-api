package com.myev.charge.service.impl;

import com.myev.charge.domain.ChargerType;
import com.myev.charge.domain.ChargingPoint;
import com.myev.charge.domain.ChargingStation;
import com.myev.charge.domain.Location;
import com.myev.charge.exception.DuplicateLocationException;
import com.myev.charge.exception.ResourceNotFoundException;
import com.myev.charge.payload.*;
import com.myev.charge.repository.ChargingStationRepository;
import com.myev.charge.service.IChargingStationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ChargingStationServiceImpl implements IChargingStationService {

    private final ChargingStationRepository _stationRepository;

    public ChargingStationServiceImpl(ChargingStationRepository stationRepository) {
        this._stationRepository = stationRepository;
    }

    @Override
    public ChargingStationDto doCreate(ChargingStationDto stationDto) {
        // convert Dto to Entity
        ChargingStation station = mapToEntity(stationDto);

        // check if the address already exists
        if (existsByLocationAddress(station.getLocation().getAddress())) {
            throw new DuplicateLocationException("Charging Station", "address", station.getLocation().getAddress());
        }

        // save Entity to database
        ChargingStation newStation = _stationRepository.save(station);

        return mapToDto(newStation);
    }

    @Override
    public ChargingStationResponse doGetAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        // create Sort object
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        // create Pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // get Page object
        Page<ChargingStation> stationPages = _stationRepository.findAll(pageable);

        // get content from Page object
        List<ChargingStation> stationList = stationPages.getContent();

        // convert List of Entity to List of Dto
        List<ChargingStationVO> content = stationList.stream().map(this::mapToVO).collect(Collectors.toList());

        ChargingStationResponse stationResponse = new ChargingStationResponse();
        stationResponse.setContent(content);
        stationResponse.setPageNo(stationPages.getNumber());
        stationResponse.setPageSize(stationPages.getSize());
        stationResponse.setTotalElements(stationPages.getTotalElements());
        stationResponse.setTotalPages(stationPages.getTotalPages());
        stationResponse.setLast(stationPages.isLast());

        return stationResponse;
    }

    @Override
    public ChargingStationDto doGetById(long id) {
        ChargingStation station = _stationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Charging Station", "id", id));

        return mapToDto(station);
    }

    @Override
    public ChargingStationDto doUpdate(ChargingStationDto stationDto, long id) {
        return null;
    }

    @Override
    public void doDelete(long id) {

    }

    private boolean existsByLocationAddress(String address) {
        ChargingStation station = _stationRepository.findAll().stream()
                .filter(s -> s.getLocation().getAddress().equals(address))
                .findFirst()
                .orElse(null);

        return station != null;
    }

    private ChargingStationDto mapToDto(ChargingStation station) {
        LocationDto locationDto = new LocationDto();
        locationDto.setAddress(station.getLocation().getAddress());
        locationDto.setLatitude(station.getLocation().getLatitude());
        locationDto.setLongitude(station.getLocation().getLongitude());

        ChargingStationDto stationDto = new ChargingStationDto();
        stationDto.setId(station.getId());
        stationDto.setLocation(locationDto);
        stationDto.setNumberOfChargingPoints(station.getNumberOfChargingPoints());
        stationDto.setStatus(station.getStatus());

        if (station.getChargingPoints() == null) {
            stationDto.setChargingPoints(null);
        }
        else {
            Set<ChargingPointDto> chargingPointDtoSet = getChargingPointDto(station);

            stationDto.setChargingPoints(chargingPointDtoSet);
        }

        return stationDto;
    }

    private Set<ChargingPointDto> getChargingPointDto(ChargingStation station) {
        Set<ChargingPointDto> chargingPointDtoSet = new HashSet<>();
        for (ChargingPoint chargingPoint : station.getChargingPoints()) {
            ChargingPointDto chargingPointDto = new ChargingPointDto();
            chargingPointDto.setId(chargingPoint.getId());
            chargingPointDto.setPowerLevel(chargingPoint.getPowerLevel());

            ChargerTypeDto chargerTypeDto = new ChargerTypeDto();
            chargerTypeDto.setType(chargingPoint.getChargerType().getType());
            chargerTypeDto.setSpeed(chargingPoint.getChargerType().getSpeed());
            chargerTypeDto.setPrice(chargingPoint.getChargerType().getPrice());

            chargingPointDto.setChargerType(chargerTypeDto);

            chargingPointDtoSet.add(chargingPointDto);
        }
        return chargingPointDtoSet;
    }

    private ChargingStation mapToEntity(ChargingStationDto stationDto) {
        Location location = new Location();
        location.setAddress(stationDto.getLocation().getAddress());
        location.setLatitude(stationDto.getLocation().getLatitude());
        location.setLongitude(stationDto.getLocation().getLongitude());

        ChargingStation station = new ChargingStation();
        station.setId(stationDto.getId());
        station.setLocation(location);
        station.setNumberOfChargingPoints(stationDto.getNumberOfChargingPoints());
        station.setStatus(stationDto.getStatus());

        if (stationDto.getChargingPoints() == null) {
            station.setChargingPoints(null);
        }
        else {
            Set<ChargingPoint> chargingPointSet = getChargingPoints(stationDto);

            station.setChargingPoints(chargingPointSet);
        }

        return station;
    }

    private Set<ChargingPoint> getChargingPoints(ChargingStationDto stationDto) {
        Set<ChargingPoint> chargingPointSet = new HashSet<>();
        for (ChargingPointDto chargingPointDto : stationDto.getChargingPoints()) {
            ChargingPoint chargingPoint = new ChargingPoint();
            chargingPoint.setId(chargingPointDto.getId());
            chargingPoint.setPowerLevel(chargingPointDto.getPowerLevel());

            ChargerType chargerType = new ChargerType();
            chargerType.setType(chargingPointDto.getChargerType().getType());
            chargerType.setSpeed(chargingPointDto.getChargerType().getSpeed());
            chargerType.setPrice(chargingPointDto.getChargerType().getPrice());

            chargingPoint.setChargerType(chargerType);

            chargingPointSet.add(chargingPoint);
        }
        return chargingPointSet;
    }

    private ChargingStationVO mapToVO(ChargingStation station) {

        LocationVO locationVO = new LocationVO();
        locationVO.setAddress(station.getLocation().getAddress());
        locationVO.setLatitude(station.getLocation().getLatitude());
        locationVO.setLongitude(station.getLocation().getLongitude());

        ChargingStationVO stationVO = new ChargingStationVO();
        stationVO.setStationId(station.getId());
        stationVO.setLocation(locationVO);
        stationVO.setNumberOfChargingPoints(station.getChargingPoints().size());
        stationVO.setStatus(station.getStatus());

        if (station.getChargingPoints() == null) {
            stationVO.setChargingPoints(null);
        }
        else {
            Set<ChargingPointVO> chargingPointVOSet = getChargingPointVO(station);

            stationVO.setChargingPoints(chargingPointVOSet);
        }

        return stationVO;
    }

    private static Set<ChargingPointVO> getChargingPointVO(ChargingStation station) {
        Set<ChargingPointVO> chargingPointVOSet = new HashSet<>();
        for (ChargingPoint chargingPoint : station.getChargingPoints()) {

            ChargerTypeVO chargerTypeVO = new ChargerTypeVO();
            chargerTypeVO.setType(chargingPoint.getChargerType().getType());
            chargerTypeVO.setSpeed(chargingPoint.getChargerType().getSpeed());
            chargerTypeVO.setPrice(chargingPoint.getChargerType().getPrice());

            ChargingPointVO pointVO = new ChargingPointVO();
            pointVO.setPointId(chargingPoint.getId());
            pointVO.setStationId(station.getId());
            pointVO.setChargerType(chargerTypeVO);
            pointVO.setPowerLevel(chargingPoint.getPowerLevel());

            chargingPointVOSet.add(pointVO);
        }
        return chargingPointVOSet;
    }
}
