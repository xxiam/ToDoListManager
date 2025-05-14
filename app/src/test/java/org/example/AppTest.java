package org.example;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.After;

public class AppTest {
 
    @Test
    public void isValidDate() {
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
}