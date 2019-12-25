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
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

//        // Create a fake list of earthquake locations.
//        ArrayList<EarthQuakeDetails> earthquakes = new ArrayList<>();
//        Calendar c =Calendar.getInstance();
//        c.set(1992,4,5);
//        earthquakes.add(new EarthQuakeDetails(5.0,"San Francisco",c.getTime()));
//        c.set(2000,4,5);
//        earthquakes.add(new EarthQuakeDetails(5.0,"London", c.getTime()));
//        c.set(2006,3,22);
//        earthquakes.add(new EarthQuakeDetails(5.0,"Tokyo", c.getTime()));
//        c.set(2010,6,25);
//        earthquakes.add(new EarthQuakeDetails(5.0,"Rio de Janerio", c.getTime()));
//        c.set(2016,8,17);
//        earthquakes.add(new EarthQuakeDetails(5.0,"Moscow", c.getTime()));
//        c.set(2012,9,9);
//        earthquakes.add(new EarthQuakeDetails(5.0,"Mexico City", c.getTime()));
        ArrayList<EarthQuakeDetails> earthquakes = QueryUtils.extractEarthquakes();
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthQuakeAdaptor earthQuakeAdaptor = new EarthQuakeAdaptor(this,earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthQuakeAdaptor);
    }
}
