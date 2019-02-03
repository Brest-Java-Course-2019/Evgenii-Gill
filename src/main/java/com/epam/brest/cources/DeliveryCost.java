
package com.epam.brest.cources;

import com.epam.brest.cources.calc.Calculator;
import com.epam.brest.cources.calc.impl.CalculatorImpl;
import com.epam.brest.cources.reader.InputData;
import com.epam.brest.cources.reader.InputDataReader;
import com.epam.brest.cources.reader.PriceProperty;
import com.epam.brest.cources.reader.PricePropertyReader;
import com.epam.brest.cources.reader.exeption.InputDataReaderException;
import com.epam.brest.cources.reader.exeption.PropertyReaderException;
import com.epam.brest.cources.reader.impl.InputDataReaderImpl;
import com.epam.brest.cources.reader.impl.PricePropertyReaderImpl;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class DeliveryCost {
    private static final Logger LOGGER = Logger.getLogger("InfoLogging");

    public static void main(final String[] args) throws PropertyReaderException, InputDataReaderException {
        LOGGER.info("Logging an INFO-level message");

        final PricePropertyReader propertyReader = new PricePropertyReaderImpl("path");
        final PriceProperty priceProperty = propertyReader.getPropertiesPrice();

        final InputDataReader inputDataReader = new InputDataReaderImpl();
        final InputData inputData = inputDataReader.getInputData();

        final Calculator calculator = new CalculatorImpl(priceProperty);
        final BigDecimal totalPrice = calculator.calculatePrice(inputData);

        System.out.println("Total Price of delivery: " + totalPrice + "$.");
    }

}


