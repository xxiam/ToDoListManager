package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;

public class HandlerTest {
    private Handler hd = new Handler(0);

    @Test
    public void testHandlerInitialization() {
        assertNotNull(hd);
    }

    @Test
    public void createDateTest() {
        String date = Handler.createDate();
        assertNotNull(date);
        assertTrue(date.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    public void addEntryTest() {
        hd.addEntry("Test Title", "Test Description", 1, "2030-10-01");
        Entry entry = hd.getEntry("Test Title");
        assertNotNull(entry);
        assertEquals("Test Title", entry.title);
        assertEquals("Test Description", entry.description);
        assertEquals(1, entry.priority);
        assertEquals("2030-10-01", entry.dateDue);
        // date created will always be different
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDuplicateEntryTest() {
        hd.addEntry("Test Title", "Test Description", 1, "2030-10-01");
        hd.addEntry("Test Title", "Another Description", 2, "2031-10-01");
    }

    @Test
    public void removeEntryTest() {
        hd.addEntry("Test Title", "Test Description", 1, "2030-10-01");
        Entry removedEntry = hd.removeEntry("Test Title");
        assertNotNull(removedEntry);
        assertEquals("Test Title", removedEntry.title);
        assertEquals("Test Description", removedEntry.description);
        assertEquals(1, removedEntry.priority);
        assertEquals("2030-10-01", removedEntry.dateDue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistentEntryTest() {
        hd.removeEntry("Non Existent Title");
    }

}