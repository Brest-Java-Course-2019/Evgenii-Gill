package com.epam.brest.cources.reader;

import java.math.BigDecimal;

public class PriceProperty {

    private BigDecimal price;
    private BigDecimal minPrice;

    public PriceProperty(final BigDecimal price, final BigDecimal minPrice) {
        this.price = price;
        this.minPrice = minPrice;
    }

    private PriceProperty() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    @Override
    public String toString() {
        return "PriceProperty{" +
                "price=" + price +
                ", minPrice=" + minPrice +
                '}';
    }

    public static PricePropertyBuilder builder() {
        return new PriceProperty().new PricePropertyBuilder();
    }

    public class PricePropertyBuilder {

        private PricePropertyBuilder() {
        }

        public PricePropertyBuilder price(final BigDecimal price) {
            PriceProperty.this.price = price;
            return this;
        }

        public PricePropertyBuilder minPrice(final BigDecimal minPrice) {
            PriceProperty.this.minPrice = minPrice;
            return this;
        }

        public PriceProperty build() {
            return PriceProperty.this;
        }
    }

}
