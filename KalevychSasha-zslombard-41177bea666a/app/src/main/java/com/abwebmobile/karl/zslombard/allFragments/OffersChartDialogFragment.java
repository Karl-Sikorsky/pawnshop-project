package com.abwebmobile.karl.zslombard.allFragments;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.Offer;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Karl on 03.03.2018.
 * Диаграмма кредитов
 */

public class OffersChartDialogFragment extends android.support.v4.app.DialogFragment {
    GraphView mGraph;
    ArrayList<Offer> mInsideData;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
        Log.d("chart", "created");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("ГРАФІК КРЕДИТІВ");
        View v = inflater.inflate(R.layout.dialog_offers_chart, container, false);
        Log.d("chart", "onCreateView");
        mGraph = (GraphView)v.findViewById(R.id.graph);
              setGraph(mInsideData);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onClick(View v) {
        if(v.getId()==R.id.buttonPay){
            dismiss();}
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }


    public void setData(ArrayList<Offer> data) {
        this.mInsideData = data;

    }

    public void setGraph(ArrayList<Offer> data) {



        final DataPoint[] dataPoints = new DataPoint[data.size()];

        for(int i=0;i<data.size();i++){
            String string = data.get(i).agreementDate;
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            try {
                Date date = format.parse(string);
                dataPoints[i] = new DataPoint(date, data.get(i).creditBalance);
            } catch (ParseException e) {
                Log.d("DATECATCHER",string);
                e.printStackTrace();
            }

        }
        for(int i=0;i<data.size();i++){
            Log.d("dataPoints", dataPoints[i].toString());
        }
        if(dataPoints.length>1){
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
            PointsGraphSeries<DataPoint> seriesPoints = new PointsGraphSeries<>(dataPoints);
            mGraph.addSeries(series);
            mGraph.addSeries(seriesPoints);
            seriesPoints.setShape(PointsGraphSeries.Shape.POINT);
            seriesPoints.setColor(Color.BLUE);
            mGraph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(mGraph.getContext()));
            mGraph.getGridLabelRenderer().setNumHorizontalLabels(2);

            // set manual x bounds to have nice steps
            mGraph.getViewport().setMinX(dataPoints[1].getX());
            mGraph.getViewport().setMaxX(dataPoints[dataPoints.length-1].getX());
            mGraph.getViewport().setXAxisBoundsManual(true);
            mGraph.getViewport().setScalable(true);

            // as we use dates as labels, the human rounding to nice readable numbers
            // is not nessecary
            mGraph.getGridLabelRenderer().setHumanRounding(false);

            seriesPoints.setOnDataPointTapListener(new OnDataPointTapListener() {
                @Override
                public void onTap(Series series, DataPointInterface dataPoint) {
                    for (int i = 0;i< dataPoints.length;i++) {
                        if ( (dataPoint.getX()==dataPoints[i].getX())&&(dataPoint.getY()==dataPoints[i].getY())) {
                            Toast.makeText(getActivity(),  mInsideData.get(i).description, Toast.LENGTH_SHORT).show();
                        }
                        }
                }
            });
        }
        else {
            Log.d("chart", "fuck");
        }
    }
}