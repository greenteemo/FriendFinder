package com.group.friendfinder.View.search;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.group.friendfinder.View.RestClient;


import com.group.friendfinder.R;
import com.group.friendfinder.View.home.func.FavorUnitsPie;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResult extends AppCompatActivity {

    private Toolbar toolbar;
    private String NewFriends;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        new SearchNewFriendsAsync(intent.getIntArrayExtra("attributes")).execute();
    }

    private class SearchNewFriendsAsync extends AsyncTask<Integer, Void, String> {
        private int[] attributes;
        private SearchNewFriendsAsync(int[] attributes) {
            this.attributes = attributes;
        }
        protected String doInBackground(Integer... params) {
            return RestClient.getStudentsByKeys(300014,0,0,
                    attributes[0],attributes[1],attributes[2],attributes[3],attributes[4],
                    attributes[5],attributes[6],attributes[7],attributes[8],attributes[9],
                    attributes[10],attributes[11],attributes[12],attributes[13],attributes[0],
                    attributes[0]);
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String ret) {
            NewFriends = ret;
            Toast.makeText(SearchResult.this, NewFriends,Toast.LENGTH_SHORT).show();

            String pattern1 = "\"frequency\":(.*?),\"";
            String pattern2 = "\"item_name\":\"(.*?)\"";
        }
    }
}
