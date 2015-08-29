package com.skocken.efficientadapter.example.models;

public class Plane implements Item {

    private String mManufacturer;

    private String mModel;

    public Plane(String manufacturer, String model) {
        mManufacturer = manufacturer;
        mModel = model;
    }

    public String getManufacturer() {
        return mManufacturer;
    }

    public String getModel() {
        return mModel;
    }

    @Override
    public String toString() {
        return mManufacturer + ": " + mModel;
    }
}
