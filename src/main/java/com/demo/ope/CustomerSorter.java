package com.demo.ope;

import com.google.gson.Gson;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomerSorter {

    //Latitude and Longitude fields for Dublin office address.
    private final double la1;
    private final double lo1;

    private URL rawDataURL;
    private String filePath;

    //Initialize la1, lo1 using constructor as values do not change during run-time
    public CustomerSorter(double fromLat, double fromLong) {
        this.la1 = fromLat;
        this.lo1 = fromLong;
    }

    //returns false is URL is malformed or
    public boolean setDataResource(String input) {

        if (input == null || input.isEmpty())
            return false;

        File file = new File(input);
        if (file.isFile() && file.exists() && !file.isDirectory()) {
            filePath = input;
        } else {
            try {
                rawDataURL = new URL(input);
            } catch (MalformedURLException e) {
                return false;
            }
        }
        return true;
    }

    public double calculateDistanceUsingLatitudeLongitude(double la2, double lo2) {

        double centralAngle =
                Math.acos(Math.sin(Math.toRadians(la1))
                        * Math.sin(Math.toRadians(la2))
                        + Math.cos(Math.toRadians(la1))
                        * Math.cos(Math.toRadians(la2))
                        * Math.cos(Math.toRadians(lo1 - lo2)));

        int radiusOfEarth = 6371;

        return radiusOfEarth * centralAngle;
    }

    public List<CustomerRecord> getCustomerRecordsAsList() {

        BufferedReader reader = null;
        List<CustomerRecord> customerList = null;

        customerList = new ArrayList<>();
        Gson gson = new Gson();
        String line;

        try {
            if (rawDataURL != null && filePath == null) {
                URLConnection connection = rawDataURL.openConnection();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else if (filePath != null && rawDataURL == null) {
                reader = new BufferedReader(new FileReader(filePath));
            }

            while (true) {

                line = reader.readLine();
                if (line == null || line.isEmpty())
                    break;

                CustomerRecord customerRecord = gson.fromJson(line, CustomerRecord.class);
                customerList.add(customerRecord);
            }

            reader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return customerList;
    }

    public List<CustomerRecord> filterCustomersWithin100KmAndSortById(List<CustomerRecord> allCustomers) {

        if (allCustomers != null && !allCustomers.isEmpty()) {
            List<CustomerRecord> customersWithin100Km = new ArrayList<>();
            double latitude, longitude;

            for (CustomerRecord customer : allCustomers) {

                latitude = customer.getLatitude();
                longitude = customer.getLongitude();

                if (calculateDistanceUsingLatitudeLongitude(latitude, longitude) <= 100)
                    customersWithin100Km.add(customer);
            }

            //Sort by asc order of user_id
            customersWithin100Km.sort(Comparator.comparing(CustomerRecord::getUserID));

            return customersWithin100Km;

        } else return null;
    }
}
