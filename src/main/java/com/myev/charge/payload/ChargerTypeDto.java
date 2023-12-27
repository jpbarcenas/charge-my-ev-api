package com.myev.charge.payload;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ChargerTypeDto {
    private String type;
    private int speed;
    private double price;
}