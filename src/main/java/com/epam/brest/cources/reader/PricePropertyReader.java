package com.epam.brest.cources.reader;

import com.epam.brest.cources.reader.exeption.PropertyReaderException;

import java.io.FileNotFoundException;

public interface PricePropertyReader {

    PriceProperty getPropertiesPrice() throws FileNotFoundException, PropertyReaderException;
}
