package com.group.friendfinder.View.home.func;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.group.friendfinder.R;
import com.group.friendfinder.View.RestClient;

public class ChooseDate extends AppCompatActivity{

    private Toolbar toolbar;
    private DatePicker datePickerStart;
    private DatePicker datePickerEnd;
    private int dayStart, monthStart, yearStart;
    private int dayEnd, monthEnd, yearEnd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_date);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // date picker
        datePickerStart = findViewById(R.id.datePickerStart);
        datePickerEnd = findViewById(R.id.datePickerEnd);
    }

    public void search(View v){
        dayStart = datePickerStart.getDayOfMonth();
        monthStart = datePickerStart.getMonth()+1;
        yearStart = datePickerStart.getYear();

        dayEnd = datePickerEnd.getDayOfMonth();
        monthEnd = datePickerEnd.getMonth()+1;
        yearEnd = datePickerEnd.getYear();

//        new movieAsync().execute("Avenger");

        if(yearStart * 10000 + monthStart * 100 + dayStart > yearEnd * 10000 + monthEnd * 100 + dayEnd){
            Toast.makeText(ChooseDate.this,
                    "Start time is more than End time, please pick correct time.", Toast.LENGTH_SHORT).show();
        }else{
            String start = yearStart + (monthStart < 10 ? "-0" : "-") + monthStart + (dayStart < 10 ? "-0" : "-") + dayStart;
            String end = yearEnd + (monthEnd < 10 ? "-0" : "-") + monthEnd + (dayEnd < 10 ? "-0" : "-") + dayEnd;

            Intent intent = new Intent(this, LocationBarChart.class);
            intent.putExtra("start", start);
            intent.putExtra("end", end);
            startActivity(intent);
        }
    }

//    private class movieAsync extends AsyncTask<String, Void, String> {
//
//        protected String doInBackground(String... params) {
//            return RestClient.getMovie(params[0]);
//        }
//
//        /** The system calls this to perform work in the UI thread and delivers
//         * the result from doInBackground() */
//        protected void onPostExecute(String ret) {
//            System.out.println(ret);
//        }
//    }
}
