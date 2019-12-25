package com.example.android.quakereport;

import java.util.Date;

public class EarthQuakeDetails {
    private double mMagnitude;
    private String mLocation;
    private Date mDate;

    public EarthQuakeDetails(double mMagnitude, String mLocation, Date mDate) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mDate = mDate;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Date getmDate() {
        return mDate;
    }

}
