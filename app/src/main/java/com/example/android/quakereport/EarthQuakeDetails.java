package com.example.android.quakereport;

import java.util.Date;

public class EarthQuakeDetails {
    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;

    public EarthQuakeDetails(double mMagnitude, String mLocation, long mTimeInMilliseconds) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
