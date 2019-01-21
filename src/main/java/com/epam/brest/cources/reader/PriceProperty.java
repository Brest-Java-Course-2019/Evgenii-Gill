package com.epam.brest.cources.reader;

import java.math.BigDecimal;

public class PriceProperty {

    final private BigDecimal weight;
    final private BigDecimal distance;
    final private BigDecimal price;
    final private BigDecimal minprice;
    final private BigDecimal midprice;
    final private BigDecimal maxprice;

    public PriceProperty(BigDecimal weight, BigDecimal distance, BigDecimal price, BigDecimal minprice, BigDecimal midprice, BigDecimal maxprice) {
        this.weight = weight;
        this.distance = distance;
        this.price = price;
        this.minprice = minprice;
        this.midprice = midprice;
        this.maxprice = maxprice;
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

    public BigDecimal getMinprice() {
        return minprice;
    }

    public BigDecimal getMidprice() {
        return midprice;
    }

    public BigDecimal getMaxprice() {
        return maxprice;
    }

    @Override
    public String toString() {
        return "PriceProperty{" +
                "weight=" + weight +
                ", distance=" + distance +
                ", price=" + price +
                ", minprice=" + minprice +
                ", midprice=" + midprice +
                ", maxprice=" + maxprice +
                '}';
    }
}
