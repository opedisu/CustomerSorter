package com.demo.ope;

import com.google.gson.JsonSyntaxException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomerSorterTest {

    private static CustomerSorter customerSorter;

    @BeforeClass
    public static void init() {
        customerSorter = new CustomerSorter(53.339428, -6.257664);
    }

    @Test
    public void setDataResourceSimpleURLTest() {
        assertTrue(customerSorter.setDataResource("https://www.google.com"));
    }

    @Test
    public void setDataResourceTest_nullPointerHandled() {
        assertFalse(customerSorter.setDataResource(null));
    }

    @Test
    public void setDataResourceTest_emptyStringHandled() {
        assertFalse(customerSorter.setDataResource(""));
    }

    @Test
    public void setDataResourceMalformedURLReturnsFalseTest() {
        assertFalse(customerSorter.setDataResource("www . not a url . test"));
    }

    @Test(expected = JsonSyntaxException.class)
    public void getCustomerRecordsWithoutSettingJSONDataResourceTest() {
        customerSorter.setDataResource("https://www.google.com");
        customerSorter.filterCustomersWithin100KmAndSortById(customerSorter.getCustomerRecordsAsList());
    }

    @Test
    public void calculateDistanceUsingLatitudeLongitudeTest() {
        assertTrue(41.76872550088835 == customerSorter.calculateDistanceUsingLatitudeLongitude(52.986375, -6.043701));
    }
}