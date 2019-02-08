package com.epam.brest.cources.reader.impl;

import com.epam.brest.cources.reader.exeption.PropertyReaderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PricePropertyReaderImplTest {
    private final static String PATH = "price.properties";
    private final static String WRONG_PATH = "price1.properties";
    private final static String PATH_WITHOUT_KEY = "price.test-without-key-properties";
    private final static String PATH_WITH_WRONG_VALUE = "price.test-with-wrong-value-properties";


    @Test
    public void setWrongPath() {
        final PricePropertyReaderImpl reader = new PricePropertyReaderImpl(WRONG_PATH);

        Assertions.assertThrows(PropertyReaderException.class, () -> {
            reader.getPropertiesPrice();
        });
    }

    @Test
    public void setPathWithoutKey() {
        final PricePropertyReaderImpl reader = new PricePropertyReaderImpl(PATH_WITHOUT_KEY);

        Assertions.assertThrows(PropertyReaderException.class, () -> {
            reader.getPropertiesPrice();
        });
    }

    @Test
    public void setPathWithWrongValue() {
        final PricePropertyReaderImpl reader = new PricePropertyReaderImpl(PATH_WITH_WRONG_VALUE);

        Assertions.assertThrows(PropertyReaderException.class, () -> {
            reader.getPropertiesPrice();
        });
    }
    }

