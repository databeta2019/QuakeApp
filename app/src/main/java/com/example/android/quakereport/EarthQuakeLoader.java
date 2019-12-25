package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthQuakeLoader extends AsyncTaskLoader<List<EarthQuakeDetails>> {
    public EarthQuakeLoader(Context context) {
        super(context);
    }
    private String mUrl;
    public EarthQuakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<EarthQuakeDetails> loadInBackground() {
        if(mUrl == null)
            return null;
        List<EarthQuakeDetails> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }

}
