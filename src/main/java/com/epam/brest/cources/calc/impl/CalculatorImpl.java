package com.epam.brest.cources.calc.impl;

import com.epam.brest.cources.calc.Calculator;
import com.epam.brest.cources.reader.PriceProperty;

import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {

    private final PriceProperty property;

    public CalculatorImpl(final PriceProperty property) {
        this.property = property;
    }

    @Override
    public BigDecimal calculatePrice(final BigDecimal distance, final BigDecimal weight) {
        final BigDecimal totalPrice = property.getPrice().multiply(distance.multiply(weight));
        System.out.println("totalPrice = " + totalPrice);

        if (totalPrice.compareTo(property.getMinprice()) >= 0){
            return totalPrice;
        }
        return property.getMinprice();
    }
}
