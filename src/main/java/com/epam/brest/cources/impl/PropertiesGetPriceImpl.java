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
    private static final String PRICE = "price";
    private static final String DISTANCE = "distance";
    private static final String MINPRICE = "minprice";
    private static final String MIDPRICE = "midprice";
    private static final String MAXPRICE = "maxprice";


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
            final BigDecimal minprice = getValueByKey(properties, MINPRICE);
            final BigDecimal midprice = getValueByKey(properties, MIDPRICE);
            final BigDecimal maxprice = getValueByKey(properties, MAXPRICE);

            if(weight.doubleValue()<=0) {
                System.out.println(" Larger then 0");
            }else if(weight.doubleValue()<5){
                final double mincost = weight.doubleValue()* distance.doubleValue()* minprice.doubleValue();
                System.out.println("MinimalCost = " + mincost);
            }else if (weight.doubleValue()>=5 && weight.doubleValue()<10){
                final double midcost = weight.doubleValue()* distance.doubleValue()*midprice.doubleValue();
                System.out.println("MidiumCost " + midcost);
            } else if (weight.doubleValue()>=10){
                final double maxcost = weight.doubleValue()* distance.doubleValue()*maxprice.doubleValue();
                System.out.println("MaxCost " + maxcost);
            }


            return new PriceProperty(weight, distance,price, minprice,midprice,maxprice);
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


