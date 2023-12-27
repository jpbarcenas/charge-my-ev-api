package com.myev.charge.payload;

import com.myev.charge.domain.enums.StationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargingStationDto {
    private Long id;

    @Embedded
    private LocationDto location;

    private int numberOfChargingPoints;

    private Set<ChargingPointDto> chargingPoints;

    private StationStatus status;
}