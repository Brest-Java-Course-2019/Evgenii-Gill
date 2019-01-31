package com.epam.brest.cources.reader.impl;

import com.epam.brest.cources.reader.InputData;
import com.epam.brest.cources.reader.InputDataReader;
import com.epam.brest.cources.reader.exeption.InputDataReaderException;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputDataReaderImpl implements InputDataReader {

    private static final String DISTANCE = "distance (km)";
    private static final String WEIGHT = "weight (kg)";
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public InputData getInputData() throws InputDataReaderException {
        final BigDecimal distance = getValueWithName(DISTANCE);
        final BigDecimal weight = getValueWithName(WEIGHT);

        return InputData.builder()
                .distance(distance)
                .weight(weight)
                .build();
    }

    private static BigDecimal getValueWithName(final String name) throws NumberFormatException {
        try {
            System.out.println("Please enter " + name + ":");
            return SCANNER.nextBigDecimal();
        } catch (final Exception ex) {
            throw new InputDataReaderException("Failed to get entered value with name [" + name + "].");
        }
    }

}
