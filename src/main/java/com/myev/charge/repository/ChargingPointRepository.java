package com.myev.charge.repository;

import com.myev.charge.domain.ChargingPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingPointRepository extends JpaRepository<ChargingPoint, Long> {
}
