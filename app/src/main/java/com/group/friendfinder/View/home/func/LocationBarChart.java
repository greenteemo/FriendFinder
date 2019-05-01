package com.group.friendfinder.View.home.func;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.group.friendfinder.R;

import java.util.ArrayList;

import static java.lang.Float.NaN;

public class LocationBarChart extends AppCompatActivity {

    private Toolbar toolbar;
    private BarChart barChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_bar_chart);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // init
        barChart = findViewById(R.id.barChart);
        barChart.getDescription().setEnabled(false);

        // customize legends
        Legend l = barChart.getLegend();
        l.setTextSize(13);

        // add data
        // entities
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(2, 10));
        barEntries.add(new BarEntry(4, 20));
        barEntries.add(new BarEntry(6, 25));
        barEntries.add(new BarEntry(8, 15));
        // labels
        String[] labels = new String[]{"a", "b", "c", "d"};

        BarDataSet barDataSet = new BarDataSet(barEntries, "Locations");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setDrawValues(true);
        barDataSet.setStackLabels(labels);


        BarData barData = new BarData(barDataSet);
        barData.setValueTextSize(13);
        barChart.setData(barData);
        barChart.invalidate();
        barChart.animateY(2000);
    }
}
