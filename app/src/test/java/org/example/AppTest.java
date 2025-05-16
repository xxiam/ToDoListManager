package org.example;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import org.junit.After;

public class AppTest {

    @Test
    public void isValidDateTest() {
        assertTrue(App.isValidDate("2003-10-01")); // valid date
        assertTrue(App.isValidDate("2005-12-29"));
        assertTrue(App.isValidDate("2003-10-10"));

        // random ints
        assertFalse(App.isValidDate("111111"));

        // invalid month
        assertFalse(App.isValidDate("2003-13-01"));

        // invalid day
        assertFalse(App.isValidDate("2003-10-32"));

        // invalid date format
        assertFalse(App.isValidDate("2003-10-01T00:00:00"));
        assertFalse(App.isValidDate("10-08-2003"));
        assertFalse(App.isValidDate("2003/10/01"));
        assertFalse(App.isValidDate("2003.10.01"));
    }

    @Test
    public void addNewEntryTest() {
        Handler handler = new Handler(1);
        InputStream in = System.in;
        // system.in 
        String input = "Title1Test\n" +
                       "Description1Test\n" +
                       "3\n" +
                       "2100-10-01\n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        App.addNewEntry(handler);
        // check if entry is added
        assertTrue(handler.entryMap.containsKey("Title1Test"));

        // then delete the entry to clean up
        handler.entryMap.remove("Title1Test");
        // check if entry is deleted
        assertFalse(handler.entryMap.containsKey("Title1Test"));
    }
}