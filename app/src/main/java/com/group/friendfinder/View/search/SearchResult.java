package com.group.friendfinder.View.search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResult extends AppCompatActivity {

    public static final String ARRAYS_COUNT = "com.yourname.ARRAYS_COUNT";

    public static final String ARRAY_INDEX = "com.yourname.ARRAY_INDEX";

    private Toolbar toolbar;
    private TextView mTextView;
    private ListView mlistView;
    private Button mbtn1;
    private String NewFriends;
    private int count = 0, mode;
    private int stuid[];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        toolbar = findViewById(R.id.toolbar);
        mTextView = findViewById(R.id.new_friend_count);
        mlistView = findViewById(R.id.listview);
        mbtn1 = findViewById(R.id.search_result_botton);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        mode = intent.getIntExtra("mode", 0);
        if (mode == 1) {
            toolbar.setBackgroundColor(getColor(R.color.my_bar));
            SharedPreferences spStudentid = getSharedPreferences("spStudentid", Context.MODE_PRIVATE);
            String sid = spStudentid.getString("Studentid", "");
            new getFriendAsyncTask().execute(Integer.parseInt(sid));
        } else {
            new SearchNewFriendsAsync(intent.getIntArrayExtra("attributes")).execute();
        }

    }

    private class SearchNewFriendsAsync extends AsyncTask<Integer, Void, String> {
        private int[] attributes;

        private SearchNewFriendsAsync(int[] attributes) {
            this.attributes = attributes;
        }

        protected String doInBackground(Integer... params) {
            SharedPreferences spStudentid = getSharedPreferences("spStudentid",
                    Context.MODE_PRIVATE);
            String sid = spStudentid.getString("Studentid", "");
            System.out.println(sid);
            return RestClient.getStudentsByKeys(Integer.parseInt(sid), 0, 0,
                    attributes[0], attributes[1], attributes[2], attributes[3], attributes[4],
                    attributes[5], attributes[6], attributes[7], attributes[8], attributes[9],
                    attributes[10], attributes[11], attributes[12], attributes[13], attributes[0],
                    attributes[0]);
        }

        /**
         * The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground()
         */
        protected void onPostExecute(String ret) {
            NewFriends = ret;
            System.out.println(ret);
            try {
                JSONArray myArray = new JSONArray(NewFriends);
                count = myArray.length();
                if (count == 0) {
                    if (mode == 1) {
                        mTextView.setText("try to add a new friend");
                        mTextView.setTextColor(getColor(R.color.blue));
                    }
                    Toast.makeText(SearchResult.this, "no result", Toast.LENGTH_SHORT).show();
                } else {
                    mTextView.setText("Found " + count + " new friends who has those same attributes with you");
                    mTextView.setTextColor(getColor(R.color.blue));
                    mlistView.setAdapter(new FriendListAdpter(myArray, SearchResult.this, mode));
                    stuid = new int[count];
                    final ArrayList<Integer> studentids = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        stuid[i] = myArray.getJSONObject(i).getInt("studentid");
                        studentids.add(stuid[i]);
                    }
                    mbtn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new recentLocgAsyncTask().execute(studentids);
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class getFriendAsyncTask extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {
            return RestClient.getFriend(params[0]);
        }

        @Override
        protected void onPostExecute(String ret) {
            super.onPostExecute(ret);
            System.out.println(ret);
            NewFriends = ret;
            try {
                JSONArray myArray = new JSONArray(NewFriends);
                count = myArray.length();
                if (count == 0) {
                    mTextView.setText("why not try to add a new friend");
                    mTextView.setTextColor(getColor(R.color.blue));
                    Toast.makeText(SearchResult.this, "no result", Toast.LENGTH_SHORT).show();
                } else {
                    mTextView.setText("You have " + count + " friends");
                    mTextView.setTextColor(getColor(R.color.blue));
                    mlistView.setAdapter(new FriendListAdpter(myArray, SearchResult.this, mode));
                    stuid = new int[count];
                    final ArrayList<Integer> studentids = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        JSONObject mobj = myArray.getJSONObject(i);
                        SharedPreferences spStudentid = getSharedPreferences("spStudentid",
                                Context.MODE_PRIVATE);
                        final Integer sid = Integer.parseInt(spStudentid.getString("Studentid", ""));
                        if (mode == 1) {
                            JSONObject mobj_temp = mobj.getJSONObject("ffriendid");
                            Integer stuid = mobj_temp.getInt("studentid");
                            if (stuid - sid == 0) {
                                System.out.println("----------" + stuid + "----------" + sid);
                                mobj = mobj.getJSONObject("fstudentid");
                            } else {
                                mobj = mobj_temp;
                            }
                        }
                        stuid[i] = mobj.getInt("studentid");
                        studentids.add(stuid[i]);
                    }
                    mbtn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new recentLocgAsyncTask().execute(studentids);
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class recentLocgAsyncTask extends AsyncTask<ArrayList<Integer>, Void, ArrayList<ArrayList<Double>>> {
        @Override
        protected ArrayList<ArrayList<Double>> doInBackground(ArrayList<Integer>... params) {
            System.out.println(params.length);
            return RestClient.getMostRecentLocs(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<Double>> result) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i).get(0) + "," + result.get(i).get(1));
            }
            Intent intent = new Intent(SearchResult.this, ShowMap.class);
            intent.putExtra("mode", mode);
            intent.putExtra("count", count);
            intent.putExtra("stuid", stuid);
            double results[][] = new double[count][2];
            for(int i = 0;i<count;i++) {
                for (int j = 0; j < 2; j++) {
                    results[i][j] = result.get(i).get(j);
                }
                intent.putExtra("ARRAY_INDEX" + i, results[i]);
            }
            startActivity(intent);
        }
    }
}
