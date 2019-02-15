package com.epam.brest.cources.reader;

import com.epam.brest.cources.reader.exeption.PropertyReaderException;
import com.epam.brest.cources.reader.impl.PricePropertyReaderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PricePropertyTest {
    private final static String PATH = "price.properties";
    private final static BigDecimal PRICE = new BigDecimal(2);
    private final static BigDecimal MIN_PRICE = new BigDecimal(5);

    private final static PriceProperty result = PriceProperty.builder()
            .price(PRICE)
            .minPrice(MIN_PRICE)
            .build();

    @Test
    void getPrice() throws PropertyReaderException {

        final PricePropertyReaderImpl reader = new PricePropertyReaderImpl(PATH);
        final PriceProperty property = reader.getPropertiesPrice();

        final String actualResultString = property.toString();
        final String expectedResultString = result.toString();

        final BigDecimal actualResultPrice = property.getPrice();
        final BigDecimal actualResultMinPrice = property.getMinPrice();


        final BigDecimal expectedResultPrice = PRICE;
        final BigDecimal expectedResultMinPrice = MIN_PRICE;


        Assertions.assertEquals(actualResultPrice, expectedResultPrice, "Price - Unexpected result");
        Assertions.assertEquals(actualResultMinPrice, expectedResultMinPrice, "Min - Unexpected result");
        Assertions.assertEquals(actualResultString, expectedResultString);
    }
}