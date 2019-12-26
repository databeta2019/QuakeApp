/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthQuakeDetails>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_QUERY_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private static final int LOADER_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID,null, this);
//        EarthQuakeAsyncTask task = new EarthQuakeAsyncTask();
//        task.execute(USGS_QUERY_URL);
    }

    @Override
    public Loader<List<EarthQuakeDetails>> onCreateLoader(int i, Bundle bundle) {
        return new EarthQuakeLoader(this, USGS_QUERY_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthQuakeDetails>> loader, List<EarthQuakeDetails> earthQuakeDetails) {
        updateUi(earthQuakeDetails);
    }

    @Override
    public void onLoaderReset(Loader<List<EarthQuakeDetails>> loader) {
        updateUi(new ArrayList<EarthQuakeDetails>());
    }


//    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, List<EarthQuakeDetails>> {
//
//        @Override
//        protected List<EarthQuakeDetails> doInBackground(String... urls) {
//            if (urls.length < 1)
//                return null;
//            if (urls[0] == null)
//                return null;
//            List<EarthQuakeDetails> earthquakes = QueryUtils.fetchEarthquakeData(urls[0]);
//            return earthquakes;
//        }
//
//        @Override
//        protected void onPostExecute(List<EarthQuakeDetails> event) {
//            if (event == null)
//                return;
//            updateUi(event);
//        }
//    }

    private void updateUi(List<EarthQuakeDetails> event) {
        final ArrayList<EarthQuakeDetails> earthquakes = new ArrayList<EarthQuakeDetails>(event);
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthQuakeAdaptor earthQuakeAdaptor = new EarthQuakeAdaptor(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthQuakeAdaptor);


        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EarthQuakeDetails currentQuake = earthquakes.get(i);
                String url = currentQuake.getmUrl();

                // Visit Website
                Uri uri = Uri.parse(url);
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(websiteIntent);
            }
        });

        // Empty View Stuf
        TextView emptyView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(emptyView);

    }
}
