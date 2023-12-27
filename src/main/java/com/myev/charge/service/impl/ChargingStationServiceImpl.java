package com.myev.charge.service.impl;

import com.myev.charge.domain.ChargingPoint;
import com.myev.charge.domain.ChargingStation;
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
        return null;
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

    private ChargingStationVO mapToVO(ChargingStation station) {

        LocationVO locationVO = new LocationVO();
        locationVO.setAddress(station.getLocation().getAddress());
        locationVO.setLatitude(station.getLocation().getLatitude());
        locationVO.setLongitude(station.getLocation().getLongitude());

        ChargingStationVO stationVO = new ChargingStationVO();
        stationVO.setId(station.getId());
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
