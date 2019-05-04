package com.group.friendfinder.View.home.func;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.group.friendfinder.R;
import com.group.friendfinder.View.RestClient;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;


public class LocationBarChart extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textLocation;
    private BarChart barChart;
    private ArrayList<String> locations = new ArrayList<>();
    private ArrayList<Float> nums = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_bar_chart);

        // get the Intent from main
        Intent intent = getIntent();
        String start = intent.getStringExtra("start");
        String end = intent.getStringExtra("end");
        String studentId = "30074000";

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textLocation = findViewById(R.id.textLocation);
        textLocation.setText("The number of visits that the student " + studentId + " has made to all the places" +
                "(frequency) during " + start + " to " + end + ".");

        new locationAsync().execute(studentId, start, end);
    }

    // custom labels
    class CustomX implements IAxisValueFormatter {
        private ArrayList<String> list;
        public CustomX(ArrayList<String> list ){
            this.list = list;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            int v = (int) value;
            if (v < list.size()){
                return list.get(v);
            }else{
                return "";
            }
        }
    }

    private class locationAsync extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            return RestClient.getVistedTimes(Integer.parseInt(params[0]), params[1], params[2]);
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String ret) {
            System.out.println(ret);

            /*
             * bar chart
             */
            // data
            String pattern1 = "\"frequency\":(.*?),\"";
            String pattern2 = "\"item_name\":\"(.*?)\"";

            // Create a Pattern object
            Pattern r1 = Pattern.compile(pattern1);
            Pattern r2 = Pattern.compile(pattern2);

            // Now create matcher object.
            Matcher m1 = r1.matcher(ret);
            Matcher m2 = r2.matcher(ret);
            while(m1.find() && m2.find()){
                nums.add(Float.parseFloat(m1.group(1)));
                locations.add(m2.group(1));
            }

            // init
            barChart = findViewById(R.id.barChart);
            barChart.getDescription().setEnabled(false);
            barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    Toast.makeText(LocationBarChart.this,
                            "Visiting " + locations.get(Math.round(e.getX())) + " " + Math.round(e.getY()) + " times during this time.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected() {

                }
            });
            // X
            XAxis xAxis = barChart.getXAxis();
            xAxis.setDrawGridLines(false);
            xAxis.setGranularity(1f);
            xAxis.setCenterAxisLabels(false);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            CustomX customX = new CustomX(locations);
            xAxis.setValueFormatter(customX);
            // Y
            YAxis leftY = barChart.getAxisLeft();
            YAxis rightY = barChart.getAxisRight();
            leftY.setAxisMinimum(0f);
            rightY.setAxisMinimum(0f);
            rightY.setEnabled(false);  //disable right y axis


            // customize legends
            Legend l = barChart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setTextSize(13);

            // add data
            ArrayList<BarEntry> barEntries = new ArrayList<>();
            for(int i = 0; i < nums.size(); i++){
                barEntries.add(new BarEntry(i, nums.get(i)));
            }
            BarDataSet barDataSet = new BarDataSet(barEntries, "Visit Times");
            barDataSet.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    int v = (int)value;
                    return v + "";
                }
            });
            barDataSet.setColor(Color.parseColor("Purple"));
            barDataSet.setDrawValues(true);
            BarData barData = new BarData(barDataSet);
            barData.setValueTextSize(13);
            barChart.setData(barData);
            barChart.invalidate();
            barChart.animateY(2000);
        }
    }
}
