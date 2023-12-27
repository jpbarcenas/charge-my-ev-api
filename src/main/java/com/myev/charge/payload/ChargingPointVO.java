package com.myev.charge.payload;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChargingPointVO {
    private Long pointId;
    private Long stationId;
    @Embedded
    private ChargerTypeVO chargerType;
    private double powerLevel;
}
