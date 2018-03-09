package com.demo.ope;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerRecordTest {

    private static CustomerRecord customerRecord;

    @BeforeClass
    public static void init() {
        customerRecord = new CustomerRecord(1, "John Doe", 50.1234, -10.12345);
    }

    @Test
    public void getLatitudeTest() {
        assertTrue(customerRecord.getLatitude() == 50.1234);
    }

    @Test
    public void getUserIDTest() {
        assertEquals(customerRecord.getUserID(), 1);
    }

    @Test
    public void getNameTest() {
        assertEquals(customerRecord.getName(), "John Doe");
    }

    @Test
    public void getLongitudeTest() {
        assertTrue(customerRecord.getLongitude() == -10.12345);
    }

}