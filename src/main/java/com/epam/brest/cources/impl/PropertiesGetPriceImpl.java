package com.epam.brest.cources.impl;

import com.epam.brest.cources.exeption.PropertyExeption;
import com.epam.brest.cources.intf.PropertiesGetPrice;
import com.epam.brest.cources.reader.PriceProperty;


import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class PropertiesGetPriceImpl implements PropertiesGetPrice
{

    private static final String PATH = "/home/evgen/EPAM_PROJECT/src/main/resources/price.properties";
    private static final String WEIGHT = "weight";
    private static final String DISTANCE = "distance";
    private static final String PRICE = "price";


    @Override
    public PriceProperty getPropertiesPrice() throws PropertyExeption
    {
        try {
            Properties properties = new Properties();
            final FileReader fileReader = new FileReader(PATH);
            properties.load(fileReader);

            final BigDecimal weight = getValueByKey(properties, WEIGHT);
            final BigDecimal distance = getValueByKey(properties, DISTANCE);
            final BigDecimal price = getValueByKey(properties, PRICE);

            return new PriceProperty(weight, distance, price);

        } catch (final IOException e) {
            final String errorMessage = "Failed to read file from path [" + PATH + "]";
            throw new PropertyExeption(errorMessage);
        }
    }

    private static BigDecimal getValueByKey(final Properties properties, final String key) throws PropertyExeption {
        final String value = properties.getProperty(key);

       try{
           return new BigDecimal(value);
       }
           catch (NumberFormatException ex){
           final String erorMessage = "Failed to parse value [" + value + "] by key [" + key + "].";
           throw new PropertyExeption(erorMessage);
       }
    }
}


