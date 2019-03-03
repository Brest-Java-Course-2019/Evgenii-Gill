package com.epam.brest.cources.reader.impl;

import com.epam.brest.cources.reader.PriceProperty;
import com.epam.brest.cources.reader.PricePropertyReader;
import com.epam.brest.cources.reader.exeption.PropertyReaderException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Properties;
@Component
public class PricePropertyReaderImpl implements PricePropertyReader {

    private static final String PATH = "price.properties";
    private static final String PRICE = "price";
    private static final String MIN_PRICE = "min-price";
    private final String path;

    public PricePropertyReaderImpl(final String path) {
        this.path = path;
    }

    public PricePropertyReaderImpl(){
        this.path = PATH;
    }

    @Override
    @Bean
    public PriceProperty getPropertiesPrice() throws PropertyReaderException{

        final Properties properties = getProperty(path);

        final BigDecimal price = getValueByKey(properties, PRICE);
        final BigDecimal minPrice = getValueByKey(properties, MIN_PRICE);

        return PriceProperty.builder()
                .minPrice(minPrice)
                .price(price)
                .build();
    }

    private static Properties getProperty(final String path) throws PropertyReaderException {
        try {
            final Properties properties = new Properties();

            final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            properties.load(classloader.getResourceAsStream(path));
            return properties;

        } catch (final Exception e) {
            final String errorMessage = "Failed to read file from path [" + PATH + "]";
            throw new PropertyReaderException(errorMessage);
        }
    }

    private static BigDecimal getValueByKey(final Properties properties, final String key) throws PropertyReaderException {
        final String value = properties.getProperty(key);

        try {
            return new BigDecimal(value);
        } catch (final InputMismatchException ex) {
            throw new PropertyReaderException("Failed to parse value [" + value + "] by key [" + key + "].");
        }
    }
}