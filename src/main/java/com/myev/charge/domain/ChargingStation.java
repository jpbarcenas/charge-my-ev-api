package com.myev.charge.domain;

import com.myev.charge.domain.enums.StationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "charging_stations",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"address"}, name = "UK_charging_stations_location_address")
        }
)
public class ChargingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Location location;

    @Column(name = "number_charging_points")
    private int numberOfChargingPoints;

    @OneToMany(mappedBy = "chargingStation", cascade = CascadeType.ALL)
    private Set<ChargingPoint> chargingPoints;

    @Enumerated(EnumType.STRING)
    private StationStatus status;
}