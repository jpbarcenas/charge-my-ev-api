package com.myev.charge.payload;

import com.myev.charge.domain.enums.StationStatus;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargingStationVO {
    private Long id;
    @Embedded
    private LocationVO location;
    private int numberOfChargingPoints;
    private Set<ChargingPointVO> chargingPoints;
    private StationStatus status;
}
