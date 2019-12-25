package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.media.audiofx.Equalizer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthQuakeAdaptor extends ArrayAdapter<EarthQuakeDetails> {

    public EarthQuakeAdaptor(Context context, ArrayList<EarthQuakeDetails> quakes) {
        super(context, 0, quakes);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext())
                    .inflate(R.layout.quake_item, parent, false);
        }
        EarthQuakeDetails currentQuake = getItem(position);


        // Magnitude Text
        TextView magnitudeText = (TextView) listViewItem.findViewById(R.id.mangitude_view);
        // Set Magnitude
        magnitudeText.setText(formatMagnitude(currentQuake.getmMagnitude()));


        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeText.getBackground();
        magnitudeCircle.setColor(getMagnitudeColor(currentQuake.getmMagnitude()));

        String locationString = currentQuake.getmLocation();
        // Location Text
        TextView proximityText = (TextView) listViewItem.findViewById(R.id.proximity_view);
        // Set Location
        proximityText.setText(formatProximity(locationString));

        // Location Text
        TextView locationText = (TextView) listViewItem.findViewById(R.id.location_view);
        // Set Location
        locationText.setText(formatLocation(locationString));

        Date date = new Date(currentQuake.getmTimeInMilliseconds());
        // Date Text
        TextView dateText = (TextView) listViewItem.findViewById(R.id.date_view);
        // Set Date
        dateText.setText(formateDate(date));


        // Time Text
        TextView timeText = (TextView) listViewItem.findViewById(R.id.time_view);
        // Set Date
        timeText.setText(formatTime(date));

        return listViewItem;
    }

    private int getMagnitudeColor(double magnitudeValue) {
        int magnitudeColorResourceId;
        int magnitude = (int) Math.floor(magnitudeValue);
        switch (magnitude) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


    private String formateDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(date);
    }

    private String formatTime(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(date);
    }

    private String formatProximity(String location) {
        int index = location.indexOf("of");
        if (index == -1)
            return "Near the";
        else
            return location.substring(0, index + 2);
    }


    private String formatLocation(String location) {
        int index = location.indexOf("of");
        if (index == -1)
            return location;
        else
            return location.substring(index + 2);
    }


    private String formatMagnitude(double magnitude) {
        DecimalFormat magFormat = new DecimalFormat("0.0");
        return magFormat.format(magnitude);
    }
}
