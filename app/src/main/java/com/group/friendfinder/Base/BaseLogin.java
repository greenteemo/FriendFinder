package com.group.friendfinder.Base;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.group.friendfinder.R;
import com.group.friendfinder.View.MainActivity;
import com.group.friendfinder.View.RestClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.Calendar;



public class BaseLogin extends Activity {
    Button bnlogin, bnsub;  //
    EditText etaccount, etpwd;
    TextView etsound_help, etsound_facebook;

    Calendar calendar = Calendar.getInstance();
    //获取系统的日期
//年
    int year = calendar.get(Calendar.YEAR);
    //月
    int month = calendar.get(Calendar.MONTH)+1;
    //日
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    //获取系统时间
//小时
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    //分钟
    int minute = calendar.get(Calendar.MINUTE);
    //秒
    int second = calendar.get(Calendar.SECOND);




    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to quit the application?")
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })
                    .setPositiveButton("Sure",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    finish();
                                }
                            }).show();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etsound_help = (TextView) findViewById(R.id.sound_help);
//获取编辑框
        etaccount = (EditText) findViewById(R.id.accountEdittext);
        etpwd = (EditText) findViewById(R.id.pwdEdittext);
//获取按钮
        bnlogin = (Button) findViewById(R.id.resetpwd_btn_sure);
        bnsub = (Button) findViewById(R.id.sub);
        bnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etaccount.getText().toString();
                String pwd = etpwd.getText().toString();

                new LoginAsyncTask().execute(username, pwd);
            }
        });
    }

    class LoginAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return RestClient.login(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String ret) {
            super.onPostExecute(ret);
            if(ret != null){
                SharedPreferences spUserInfo = getSharedPreferences("spUserInfo",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor eUserInfo = spUserInfo.edit();
                eUserInfo.putString("UserInfo", ret);
                eUserInfo.commit();

                String pattern1 = "\"studentid\":(.*?),\"";
                // Create a Pattern object
                Pattern r1 = Pattern.compile(pattern1);
                // Now create matcher object.
                Matcher m1 = r1.matcher(ret);
                if(m1.find()){
                    SharedPreferences spStudentid = getSharedPreferences("spStudentid",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor eStudentid = spStudentid.edit();
                    eStudentid.putString("Studentid", m1.group(1));
                    eStudentid.commit();

                    Intent intent = new Intent(BaseLogin.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(BaseLogin.this,
                            "The user not exist!", Toast.LENGTH_SHORT).show();
                }
                System.out.println(ret);
            }else{

            }
        }

    }
}