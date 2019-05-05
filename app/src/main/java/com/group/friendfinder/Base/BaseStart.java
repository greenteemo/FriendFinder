package com.group.friendfinder.Base;//启动（缓冲）界面——美观（广告位）作用

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.group.friendfinder.R;

public class BaseStart extends Activity{
    private final int SPLASH_DISPLAY_LENGHT = 2500;   //延迟2.5s后进入

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent mainIntent = new Intent(BaseStart.this,BaseLogin.class);
                BaseStart.this.startActivity(mainIntent);
                BaseStart.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}
