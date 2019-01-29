package com.epam.brest.cources.reader.impl;

import com.epam.brest.cources.reader.exeption.PropertyReaderException;
import com.epam.brest.cources.reader.PricePropertyReader;
import com.epam.brest.cources.reader.PriceProperty;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class PricePropertyReaderImpl implements PricePropertyReader {

    private static final String PATH = "resources/price.properties";
    private static final String WEIGHT = "weight";
   // private static final String MIN_WEIGHT = "min-weight";
    private static final String PRICE = "price";
    private static final String DISTANCE = "distance";
    private static final String MIN_PRICE = "minprice";


    @Override
    public PriceProperty getPropertiesPrice() throws PropertyReaderException {
        try {
            final Properties properties = new Properties();
            final ClassLoader reader = getClass().getClassLoader();
            File file = new File(reader.getResource(PATH).getFile());
            properties.load(file);

           // final BigDecimal minweight = getValueByKey(properties, MIN_WEIGHT);
            final BigDecimal weight = getValueByKey(properties, WEIGHT);
            final BigDecimal distance = getValueByKey(properties, DISTANCE);
            final BigDecimal price = getValueByKey(properties, PRICE);
            final BigDecimal minprice = getValueByKey(properties, MIN_PRICE);


            return PriceProperty.builder()
                    .minprice(minprice)
                    .price(price)
                    .distance(distance)
                    .weight(weight)
                    .build();

        } catch (final IOException e) {
            final String errorMessage = "Failed to read file from path [" + this.getClass().getClassLoader().getResourceAsStream(PATH) + "]";
            throw new PropertyReaderException(errorMessage);
        }
    }

    private static BigDecimal getValueByKey(final Properties properties, final String key) throws PropertyReaderException {
        final String value = properties.getProperty(key);

        try {
            return new BigDecimal(value);
        } catch (final NumberFormatException ex) {
            throw new PropertyReaderException("Failed to parse value [" + value + "] by key [" + key + "].");
        }
    }
}


