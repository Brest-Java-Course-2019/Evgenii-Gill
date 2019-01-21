
package com.epam.brest.cources;

import com.epam.brest.cources.Impl.PropertiesGetPriceImpl;
import com.epam.brest.cources.exeption.PropertyExeption;
import com.epam.brest.cources.intf.PropertiesGetPrice;
import com.epam.brest.cources.reader.PriceProperty;

import java.io.*;

public class DelivaryCost {
    public static void main(String[] args) throws PropertyExeption {

       try {
           final PropertiesGetPrice price = new PropertiesGetPriceImpl();
           final PriceProperty priceProperty = price.getPropertiesPrice();
           System.out.println("priceProperty = " + priceProperty);
       }catch (final IOException ex){
           ex.getMessage();
       }
    }
}


