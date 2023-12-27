package com.myev.charge.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ChargerType {
    private String type;
    private int speed;
    private double price;
}
