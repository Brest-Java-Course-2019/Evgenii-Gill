package com.epam.brest.cources.reader;

import java.math.BigDecimal;

public class PriceProperty {

    private BigDecimal weight;
    private BigDecimal distance;
    private BigDecimal price;
    private BigDecimal minprice;

    public PriceProperty(final BigDecimal weight, final BigDecimal distance, final BigDecimal price, final BigDecimal minprice) {
        this.weight = weight;
        this.distance = distance;
        this.price = price;
        this.minprice = minprice;
    }

    public PriceProperty() {

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

    @Override
    public String toString() {
        return "PriceProperty{" +
                "weight=" + weight +
                ", distance=" + distance +
                ", price=" + price +
                ", minprice=" + minprice +
                '}';
    }

    public static PricePropertyBuilder builder(){
        return new PriceProperty(). new PricePropertyBuilder();
    }

    public class PricePropertyBuilder{

        private PricePropertyBuilder(){}

        public PricePropertyBuilder price(final BigDecimal price){
            PriceProperty.this.price = price;
            return this;
        }

        public PricePropertyBuilder distance(final BigDecimal distance){
            PriceProperty.this.distance = distance;
            return this;
        }

        public PricePropertyBuilder weight(final BigDecimal weight){
            PriceProperty.this.weight = weight;
            return this;
        }

        public PricePropertyBuilder minprice(final BigDecimal minprice){
            PriceProperty.this.minprice = minprice;
            return this;
        }
        public PriceProperty build(){
            return PriceProperty.this;
        }
    }
}
