package com.epam.brest.cources.calc.impl;

import com.epam.brest.cources.calc.Calculator;
import com.epam.brest.cources.reader.InputData;
import com.epam.brest.cources.reader.PriceProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CalculatorImplTest {

    private static final BigDecimal PROPERTY_PRICE = new BigDecimal("10");
    private static final BigDecimal PROPERTY_MIN_PRICE = new BigDecimal("5");

    private static final PriceProperty PRICE_PROPERTY = PriceProperty.builder()
            .price(PROPERTY_PRICE)
            .minPrice(PROPERTY_MIN_PRICE)
            .build();

    private static final Calculator CALCULATOR = new CalculatorImpl(PRICE_PROPERTY);

    @Test
    void calculatePrice_totalPriceGatherThanMinPrice() {
        final InputData inputData = InputData.builder()
                .distance(new BigDecimal("4"))
                .weight(new BigDecimal("2"))
                .build();

        final BigDecimal actualResult = CALCULATOR.calculatePrice(inputData);
        final BigDecimal expectedResult = new BigDecimal("80");

        verifyResult(expectedResult, actualResult);
    }

    @Test
    void calculatePrice_totalPriceLessThanMinPrice() {
        final InputData inputData = InputData.builder()
                .distance(new BigDecimal("0.1"))
                .weight(new BigDecimal("1"))
                .build();

        final BigDecimal actualResult = CALCULATOR.calculatePrice(inputData);
        final BigDecimal expectedResult = new BigDecimal("5");

        verifyResult(expectedResult, actualResult);
    }

    @Test
    void calculatePrice_totalPriceEqualsMinPrice() {
        final InputData inputData = InputData.builder()
                .distance(new BigDecimal("0.5"))
                .weight(new BigDecimal("1"))
                .build();

        final BigDecimal actualResult = CALCULATOR.calculatePrice(inputData);
        final BigDecimal expectedResult = new BigDecimal("5.0");

        verifyResult(expectedResult, actualResult);
    }


    private static void verifyResult(final BigDecimal expectedResult, final BigDecimal actualResult) {
        Assertions.assertEquals(expectedResult, actualResult, "Error - Unexpected result");
    }

}