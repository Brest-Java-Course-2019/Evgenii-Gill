package com.epam.brest.cources.calc;

import com.epam.brest.cources.reader.InputData;

import java.math.BigDecimal;

public interface Calculator {

    BigDecimal calculatePrice(InputData inputData);
}
