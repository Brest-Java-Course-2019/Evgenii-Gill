package com.epam.brest.cources.reader.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class InputDataReaderImplTest {

        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        private final PrintStream originalOut = System.out;
        private final PrintStream originalErr = System.err;

        @Before
        public void setUpStreams() {
            System.setOut(new PrintStream(outContent));
            System.setErr(new PrintStream(errContent));
        }

        @After
        public void restoreStreams() {
            System.setOut(originalOut);
            System.setErr(originalErr);
        }

        @Test
        public void out() {
            System.out.print("");
            Assert.assertEquals("", outContent.toString());
        }
    }
