package com.epam.brest.cources.calc.impl;

import com.epam.brest.cources.calc.Calculator;
import com.epam.brest.cources.reader.InputData;
import com.epam.brest.cources.reader.PriceProperty;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {

    private final PriceProperty property;

    public CalculatorImpl(final PriceProperty property) {
        this.property = property;
    }

    @Override
    public BigDecimal calculatePrice(final InputData inputData) {
        final BigDecimal distance = inputData.getDistance();
        final BigDecimal weight = inputData.getWeight();

        final BigDecimal totalPrice = property.getPrice().multiply(distance.multiply(weight));

        return totalPrice.compareTo(property.getMinPrice()) >= 0 ? totalPrice : property.getMinPrice();
    }

}
