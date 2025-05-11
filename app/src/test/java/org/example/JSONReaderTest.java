package org.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

public class JSONReaderTest {

    private JSONReader jr = new JSONReader(); // json reader
    HashMap<String,Entry> tm = new HashMap<String,Entry>(); // test map

    @Test
    public void testReadJSON() {
        tm = jr.readJSON(0); // read testData.json
        // check if the map is not null
        assertNotNull(tm);
    }    

    @Test 
    public void testIfEmpty() {
        // check if the map is empty
        tm = jr.readJSON(0); // read testData.json
        assertFalse(tm.isEmpty());;
    }
}