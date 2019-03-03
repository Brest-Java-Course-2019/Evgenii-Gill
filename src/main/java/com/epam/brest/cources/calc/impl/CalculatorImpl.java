package com.epam.brest.cources.calc.impl;

import com.epam.brest.cources.calc.Calculator;
import com.epam.brest.cources.reader.InputData;
import com.epam.brest.cources.reader.PriceProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Configuration
public class CalculatorImpl implements Calculator {

    @Qualifier("getPropertiesPrice")
    @Autowired
    private PriceProperty property;


    public PriceProperty getProperty() {
        return property;
    }

    public CalculatorImpl() {

    }

    public CalculatorImpl(final PriceProperty property) {
        this.property = property;
    }

    @Override
    @Bean
    public BigDecimal calculatePrice(final InputData inputData) {
        final BigDecimal distance = inputData.getDistance();
        final BigDecimal weight = inputData.getWeight();

        final BigDecimal totalPrice = property.getPrice().multiply(distance.multiply(weight));

        return totalPrice.compareTo(property.getMinPrice()) >= 0 ? totalPrice : property.getMinPrice();
    }

}
