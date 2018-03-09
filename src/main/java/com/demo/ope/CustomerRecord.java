package com.demo.ope;

public class CustomerRecord {

    private final String name;
    private final double latitude;
    private final double longitude;
    private final int user_id;

    public CustomerRecord(int userId, String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user_id = userId;
    }

    public int getUserID() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
