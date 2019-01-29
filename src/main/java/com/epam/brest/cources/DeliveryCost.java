
package com.epam.brest.cources;

import com.epam.brest.cources.calc.Calculator;
import com.epam.brest.cources.calc.impl.CalculatorImpl;
import com.epam.brest.cources.reader.exeption.PropertyReaderException;
import com.epam.brest.cources.reader.impl.PricePropertyReaderImpl;
import com.epam.brest.cources.reader.PricePropertyReader;
import com.epam.brest.cources.reader.PriceProperty;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

public class DeliveryCost {
    private static final Logger LOGGER = Logger.getLogger("InfoLogging");

    public static void main(final String[] args) throws PropertyReaderException {
        LOGGER.info("Logging an INFO-level message");
       try {
           
           //to do
           
           final BigDecimal weight = BigDecimal.ONE;
           final BigDecimal distance = BigDecimal.ONE;
           
           final PricePropertyReader propertyReader = new PricePropertyReaderImpl();
           final PriceProperty priceProperty = propertyReader.getPropertiesPrice();

           
           final Calculator calculator = new CalculatorImpl(priceProperty);
           
           final BigDecimal totalPrice = calculator.calculatePrice(weight, distance);
           
       }catch (final IOException ex){
           ex.getMessage();
       }
    }
}


