package com.epam.brest.cources.reader;

public interface DataWriter {
    byte[] marhal(Object... objects) throws Exception;
   Object [] unmarshal(byte[] bytes) throws Exception;
}
