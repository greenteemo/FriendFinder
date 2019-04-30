/*
    Task7 Reports
    (a) Common attributes pie graph screen: this screen will show a pie chart that shows all the favourite units
     of all the subscribed students in the Profile table based on their frequency. The labels and percentages
     should be shown on the chart.
 */

package com.group.friendfinder.View;

import android.os.AsyncTask;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import com.group.friendfinder.R;

public class FavorUnitsPie extends AppCompatActivity {

    private String favorUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favor_units_pie);

        // get favor units
        new favorUnitsAsync().execute();

        // [{"frequency":6,"item_name":"1"},{"frequency":1,"item_name":"FIT5183"}]


        /*
            draw a pie
         */
        // init
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);

        // description
        Description desc = new Description();
        desc.setText("Favorite Units of Students");
        desc.setTextSize(10f);
        pieChart.setDescription(desc);

        // change format
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleRadius(25f);

        // add data
        List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(30f, "test2"));
        value.add(new PieEntry(70f, "test1"));


        PieDataSet pieDataSet = new PieDataSet(value, "Units");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.animateXY(1400,1400);     // make it awesome :)
    }

    private class favorUnitsAsync extends AsyncTask<Void, Void, String> {

        protected String doInBackground(Void... params) {
            return RestClient.getFavorUnits();
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String ret) {
            favorUnits = ret;
            System.out.println(favorUnits);
        }
    }
}
