
package com.epam.brest.cources;

import java.io.*;
import com.epam.brest.cources.impl.PropertiesGetPriceImpl;

public class DelivaryCost {
    public static void main(String[] args) throws IOException {

        try {
            final PropertiesGetPrice price = new PropertiesGetPriceImpl();
            final PriceProperty priceProperty = price.getPropertiesPrice();
            System.out.println("priceProperty = " + priceProperty);
        }catch (final IOException ex){
            ex.getMessage();
        }
        }
    }


