package com.epam.brest.cources.intf;

import com.epam.brest.cources.exeption.PropertyExeption;
import com.epam.brest.cources.reader.PriceProperty;

import java.io.FileNotFoundException;

public interface PropertiesGetPrice {

    PriceProperty getPropertiesPrice() throws FileNotFoundException, PropertyExeption;
}
