package com.myev.charge.payload;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ChargerTypeVO {
    private String type;
    private int speed;
    private double price;
}
