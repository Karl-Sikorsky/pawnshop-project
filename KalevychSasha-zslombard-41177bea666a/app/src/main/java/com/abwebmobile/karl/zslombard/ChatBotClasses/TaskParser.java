package com.abwebmobile.karl.zslombard.ChatBotClasses;


import android.os.AsyncTask;
import android.util.Log;


import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>> > {
    TaskRequestCallback taskRequestCallback;
    public TaskParser(TaskRequestCallback taskRequestCallback) {
        this.taskRequestCallback = taskRequestCallback;
    }

    @Override
    protected List<List<HashMap<String, String>>> doInBackground(String... strings) {
        JSONObject jsonObject = null;
        List<List<HashMap<String, String>>> routes = null;
        try {
            jsonObject = new JSONObject(strings[0]);
            DirectionsParser directionsParser = new DirectionsParser();
            routes = directionsParser.parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return routes;
    }

    @Override
    protected void onPostExecute(List<List<HashMap<String, String>>> lists) {
        //Get list route and display it into the map

        ArrayList points = null;

        PolylineOptions polylineOptions = null;

        for (List<HashMap<String, String>> path : lists) {
            points = new ArrayList();
            polylineOptions = new PolylineOptions();

            for (HashMap<String, String> point : path) {
                double lat = Double.parseDouble(point.get("lat"));
                double lon = Double.parseDouble(point.get("lon"));

                points.add(new LatLng(lat,lon));
            }
            polylineOptions.addAll(points);

        }

        if (polylineOptions!=null) {
            taskRequestCallback.onCallback(polylineOptions);
            Log.d("FUUUCK", "all ok");
        } else {
            Log.d("FUUUCK", "something wrong");
        }

    }
}
