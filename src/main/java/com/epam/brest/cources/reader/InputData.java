package com.epam.brest.cources.reader;

import java.math.BigDecimal;

public class InputData {

    private BigDecimal distance;
    private BigDecimal weight;

    public BigDecimal getDistance() {
        return distance;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "distance=" + distance +
                ", weight=" + weight +
                '}';
    }

    public InputData(final BigDecimal distance, final BigDecimal weight) {
        this.distance = distance;
        this.weight = weight;
    }

    private InputData() {
    }

    public static InputDataBuilder builder() {
        return new InputData().new InputDataBuilder();
    }

    public class InputDataBuilder {

        private InputDataBuilder() {
        }

        public InputDataBuilder distance(final BigDecimal distance) {
            InputData.this.distance = distance;
            return this;
        }

        public InputDataBuilder weight(final BigDecimal weight) {
            InputData.this.weight = weight;
            return this;
        }

        public InputData build() {
            return InputData.this;
        }
    }

}
