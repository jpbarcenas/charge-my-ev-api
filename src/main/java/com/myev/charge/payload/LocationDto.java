package com.myev.charge.payload;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class LocationDto {
    private String address;
    private double latitude;
    private double longitude;
}