package com.epam.brest.cources.reader.impl;

import com.epam.brest.cources.reader.PricePropertyReader;
import com.epam.brest.cources.reader.exeption.PropertyReaderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

class PricePropertyReaderImplTest {

    private final static String PATH = "price.properties";
    private final static String WRONG_PATH= "resources/price11111.properties";
    private final static String PATH_WITHOUT_KEY = "price-test-without-key.properties";
    private final static String PATH_WITH_WRONG_VALUE = "price-test-with-wrong-value.properties";

    private PricePropertyReader reader = new PricePropertyReaderImpl(PATH);


    @Test
    public void setInValidFilePath() {
        final PricePropertyReader propertyReader = new PricePropertyReaderImpl(WRONG_PATH);
        Assertions.assertThrows(PropertyReaderException.class,()-> {
            propertyReader.getPropertiesPrice();
        });
    }

    @Test
    public void setInValidFilePathWithoutKey() {
        final PricePropertyReader propertyReader = new PricePropertyReaderImpl(PATH_WITHOUT_KEY);
        Assertions.assertThrows(PropertyReaderException.class,()-> {
            propertyReader.getPropertiesPrice();
        });
    }
}