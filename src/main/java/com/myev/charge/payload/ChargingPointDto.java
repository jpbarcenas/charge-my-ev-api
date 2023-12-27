package com.myev.charge.payload;

import jakarta.persistence.Embedded;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargingPointDto {
    private Long id;

    private ChargingStationDto chargingStation;

    @Embedded
    private ChargerTypeDto chargerType;

    private double powerLevel;
}
