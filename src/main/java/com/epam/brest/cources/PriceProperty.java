package com.epam.brest.cources;

import java.math.BigDecimal;

public class PriceProperty {
    final private BigDecimal weight;
    final private BigDecimal distance;
    final private BigDecimal price;


    public PriceProperty(final BigDecimal weight, final BigDecimal distance, final BigDecimal price) {
        this.weight = weight;
        this.distance = distance;
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PriceProperty{" +
                "weight=" + weight +
                ", distance=" + distance +
                ", price=" + price +
                '}';
    }
}
