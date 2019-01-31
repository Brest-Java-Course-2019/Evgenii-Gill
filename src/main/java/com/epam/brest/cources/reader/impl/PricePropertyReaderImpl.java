package com.epam.brest.cources.reader.impl;

import com.epam.brest.cources.reader.PriceProperty;
import com.epam.brest.cources.reader.PricePropertyReader;
import com.epam.brest.cources.reader.exeption.PropertyReaderException;

import java.math.BigDecimal;
import java.util.Properties;

public class PricePropertyReaderImpl implements PricePropertyReader {

    private static final String PATH = "price.properties";
    private static final String PRICE = "price";
    private static final String MIN_PRICE = "min-price";


    @Override
    public PriceProperty getPropertiesPrice() throws PropertyReaderException {
        try {
            final Properties properties = new Properties();
            final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            properties.load(classloader.getResourceAsStream("price.properties"));

            final BigDecimal price = getValueByKey(properties, PRICE);
            final BigDecimal minPrice = getValueByKey(properties, MIN_PRICE);

            return PriceProperty.builder()
                    .minPrice(minPrice)
                    .price(price)
                    .build();

        } catch (final Exception e) {
            final String errorMessage = "Failed to read file from path [" + PATH + "]";
            throw new PropertyReaderException(errorMessage);
        }
    }

    private static BigDecimal getValueByKey(final Properties properties, final String key) throws NumberFormatException {
        final String value = properties.getProperty(key);

        try {
            return new BigDecimal(value);
        } catch (final NumberFormatException ex) {
            throw new PropertyReaderException("Failed to parse value [" + value + "] by key [" + key + "].");
        }
    }

}


