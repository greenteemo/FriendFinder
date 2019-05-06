package com.group.friendfinder.View.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.group.friendfinder.R;

public class NewFriend extends AppCompatActivity {

    private int stuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        Intent intent = getIntent();
        intent.getIntExtra("stuid", stuid);
    }
}
