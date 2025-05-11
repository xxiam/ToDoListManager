package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.After;


public class EntryTest {
    private Entry entry = new Entry("Test Title", "Test Description", 1, "2003-10-01", "2005-12-29");

    @After
    public void after() {
        entry = new Entry("Test Title", "Test Description", 1, "2003-10-01", "2005-12-29");
    }

    @Test
    public void testCreateEntry() {
        assertNotNull(entry);
        assertEquals("Test Title", entry.title);
        assertEquals("Test Description", entry.description);
        assertEquals(1, entry.priority);
        assertEquals("2003-10-01", entry.dateCreated);
        assertEquals("2005-12-29", entry.dateDue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEmptyEntry() {
        entry = new Entry("", "Test Description", 1, "2003-10-01", "2005-12-29");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEntryWithNegativePriority() {
        entry = new Entry("Test Title", "Test Description", -1, "2003-10-01", "2005-12-29");
    }

    @Test
    public void testSetTitle() {
        
        entry.setTitle("New Title");
        assertEquals("New Title", entry.title);
    }

    @Test
    public void testSetDescription() {
        entry.setDescription("New Description");
        assertEquals("New Description", entry.description);
    }

    @Test
    public void testSetPriority() {
        entry.setPriority(2);
        assertEquals(2, entry.priority);
    }

    @Test
    public void testSetCompleted() {
        entry.setCompleted(true);
        assertTrue(entry.completed);
    }

    @Test
    public void testSetDateCreated() {
        entry.setDateCreated("2023-10-02");
        assertEquals("2023-10-02", entry.dateCreated);
    }

    @Test
    public void testSetDateDue() {
        entry.setDateDue("2023-11-01");
        assertEquals("2023-11-01", entry.dateDue);
    }

    @Test
    public void testSetDateCompleted() {
        entry.setDateCompleted("2023-10-15");
        assertEquals("2023-10-15", entry.dateCompleted);
    }

    @Test
    public void testGetAsArray() {
        String[] entryArray = entry.getAsArray();
        assertNotNull(entryArray);
        assertEquals(7, entryArray.length);
        assertEquals("Test Title", entryArray[0]);
        assertEquals("Test Description", entryArray[1]);
        assertEquals("1", entryArray[2]);
        assertEquals("false", entryArray[3]);
        assertEquals("2003-10-01", entryArray[4]);
        assertEquals("2005-12-29", entryArray[5]);
        assertEquals(null, entryArray[6]);
    }
}