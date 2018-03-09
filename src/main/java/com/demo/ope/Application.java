package com.demo.ope;

import java.util.List;

class Application {
    public static void main(String[] args) {

        Config config = new Config();

        try {
            String arg = args[0];

            if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("h") || args[0].equals("?")) {
                showHelp();
            } else {

                //Create obj of CustomerSorter and initialize using latitude and longitude of dublin office
                CustomerSorter customerSorterObj = new CustomerSorter(Double.valueOf(config.getProperty("latitude")), Double.valueOf(config.getProperty("longitude")));

                //Set data resource to URL for raw JSON encoded text file -- view by clicking raw in link provided via email.
                customerSorterObj.setDataResource(arg);

                //Get customer records, then run filter method which returns list of desired results (within 100km and asc of user_id)
                List<CustomerRecord> customers = customerSorterObj.filterCustomersWithin100KmAndSortById(customerSorterObj.getCustomerRecordsAsList());

                //If list was retrieved successfully simply output the pre-sorted user ids and names
                if (customers != null && !customers.isEmpty()) {
                    System.out.println("User ID \tName");
                    for (CustomerRecord customer : customers) {
                        System.out.println(customer.getUserID() + "\t\t | \t" + customer.getName());
                    }
                } else {
                    System.out.println("Unable to get list of customers");
                }
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("Input file not detected");
        } catch (NullPointerException npe) {
            System.out.println("Invalid input detected");
        }
    }


    public static void showHelp() {
        System.out.println("\n****\nUsage :" + "\n$ gradle installDist");
        System.out.println("\n$ cd build/install/IntercomCustomerSorter/bin"
                + "\n$ ./IntercomCustomerSorter pathOrUrlToEncodedJSON");

        System.out.println("\nPlease refer to readme file for more information\n****\n");
    }
}


