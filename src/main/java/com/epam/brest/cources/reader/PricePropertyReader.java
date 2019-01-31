package com.epam.brest.cources.reader;

import com.epam.brest.cources.reader.exeption.PropertyReaderException;

public interface PricePropertyReader {

    PriceProperty getPropertiesPrice() throws NumberFormatException;
}
