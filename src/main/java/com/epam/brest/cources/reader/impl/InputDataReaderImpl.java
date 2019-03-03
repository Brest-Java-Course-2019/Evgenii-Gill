package com.epam.brest.cources.reader.impl;

import com.epam.brest.cources.reader.InputData;
import com.epam.brest.cources.reader.InputDataReader;
import com.epam.brest.cources.reader.exeption.InputDataReaderException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;
@Component
public class InputDataReaderImpl implements InputDataReader {

    private static final String DISTANCE = "distance (km)";
    private static final String WEIGHT = "weight (kg)";
    private static final Scanner SCANNER = new Scanner(System.in, "UTF-8");

    @Override
    @Bean
    public InputData getInputData() throws InputDataReaderException {
        final BigDecimal distance = getValueWithName(DISTANCE);
        final BigDecimal weight = getValueWithName(WEIGHT);

        return InputData.builder()
                .distance(distance)
                .weight(weight)
                .build();
    }

    private static BigDecimal getValueWithName(final String name) throws InputDataReaderException {
        try {
            System.out.println("Please enter " + name + ":");
            return SCANNER.nextBigDecimal();
        } catch (final InputMismatchException ex) {
            throw new InputDataReaderException("Failed to get entered value with name [" + name + "].");
        }
    }
}
