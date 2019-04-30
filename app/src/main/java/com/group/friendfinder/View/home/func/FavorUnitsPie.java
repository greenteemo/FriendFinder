/*
    Task7 Reports
    (a) Common attributes pie graph screen: this screen will show a pie chart that shows all the favourite units
     of all the subscribed students in the Profile table based on their frequency. The labels and percentages
     should be shown on the chart.
 */

package com.group.friendfinder.View.home.func;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.group.friendfinder.R;
import com.group.friendfinder.View.RestClient;

public class FavorUnitsPie extends AppCompatActivity {

    private Toolbar toolbar;
    private String favorUnits;
    private Float totalNum = 0f;
    private ArrayList<Float> nums = new ArrayList<>();
    private ArrayList<String> units = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favor_units_pie);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new favorUnitsAsync().execute();
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

            String pattern1 = "\"frequency\":(.*?),\"";
            String pattern2 = "\"item_name\":\"(.*?)\"";

            // Create a Pattern object
            Pattern r1 = Pattern.compile(pattern1);
            Pattern r2 = Pattern.compile(pattern2);

            // Now create matcher object.
            Matcher m1 = r1.matcher(favorUnits);
            Matcher m2 = r2.matcher(favorUnits);
            while(m1.find() && m2.find()){
                nums.add(Float.parseFloat(m1.group(1)));
                totalNum += Float.parseFloat(m1.group(1));
                units.add(m2.group(1));
            }

             /*
                draw a pie
             */
            // init
            PieChart pieChart = findViewById(R.id.pieChart);
            pieChart.setUsePercentValues(true);
            pieChart.setEntryLabelTextSize(13);

            // description
            Description desc = new Description();
            desc.setText("Favorite Units of Students");
            desc.setTextSize(14f);
            pieChart.setDescription(desc);

            // change format
            pieChart.setHoleRadius(25f);
            pieChart.setTransparentCircleRadius(25f);

            // set a chart value selected listener
            pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    // display msg when value selected
                    if (e == null)
                        return;

                    Toast.makeText(FavorUnitsPie.this,
                             "The percent of " + units.get(Math.round(h.getX()))+ " is " + (float)(Math.round(h.getY() * 1000)) / 10 + "%", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected() {

                }
            });

            // customize legends
            Legend l = pieChart.getLegend();
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setYEntrySpace(1);
            l.setTextSize(13);

            // add data
            List<PieEntry> value = new ArrayList<>();
            for(int i = 0; i < units.size(); i++){
                value.add(new PieEntry((nums.get(i)/totalNum), units.get(i)));
            }

            PieDataSet pieDataSet = new PieDataSet(value, "Units");
            pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            PieData pieData = new PieData(pieDataSet);
            pieData.setValueFormatter(new PercentFormatter());
            pieData.setValueTextSize(13f);
            pieData.setValueTextColor(Color.WHITE);

            pieChart.setData(pieData);
            pieChart.animateXY(1400,1400);     // make it awesome :)
        }
    }
}