package com.epam.brest.cources.reader;

import com.epam.brest.cources.reader.exeption.InputDataReaderException;

public interface InputDataReader {

    InputData getInputData() throws InputDataReaderException;

}
