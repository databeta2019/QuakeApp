package com.example.android.quakereport;

import android.content.Context;
import android.media.audiofx.Equalizer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EarthQuakeAdaptor extends ArrayAdapter<EarthQuakeDetails> {

    public EarthQuakeAdaptor(Context context, ArrayList<EarthQuakeDetails> quakes)
    {
        super(context,0,quakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext())
                    .inflate(R.layout.quake_item,parent,false);
        }
        EarthQuakeDetails currentQuake = getItem(position);


        // Magnitude Text
        TextView magnitudeText = (TextView) listViewItem.findViewById(R.id.mangitude_view);
        // Set Magnitude
        magnitudeText.setText(String.valueOf(currentQuake.getmMagnitude()));


        // Magnitude Text
        TextView locationText = (TextView) listViewItem.findViewById(R.id.location_view);
        // Set Magnitude
        locationText.setText(currentQuake.getmLocation());


        // Magnitude Text
        TextView dateText = (TextView) listViewItem.findViewById(R.id.date_view);
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        // Set Magnitude
        dateText.setText(formatter.format(currentQuake.getmDate()));

        return listViewItem;
    }
}
