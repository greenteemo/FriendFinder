package com.group.friendfinder.Base;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.group.friendfinder.LocationClass;
import com.group.friendfinder.Profile;
import com.group.friendfinder.R;
import com.group.friendfinder.View.MainActivity;
import com.group.friendfinder.View.RestClient;
import com.group.friendfinder.View.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;
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

    public double Log,Lat;

    private LocationClient mLocationclient;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etsound_help = findViewById(R.id.sound_help);
//获取编辑框
        etaccount = findViewById(R.id.accountEdittext);
        etpwd = findViewById(R.id.pwdEdittext);
//获取按钮
        bnlogin = findViewById(R.id.resetpwd_btn_sure);
        bnsub = findViewById(R.id.sub);
        bnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etaccount.getText().toString();
                String pwd = etpwd.getText().toString();

                new LoginAsyncTask().execute(username, pwd);
            }
        });
        bnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent( BaseLogin.this,BaseSub.class);
                BaseLogin.this.startActivity(mainIntent);
                BaseLogin.this.finish();
            }
        });
        super.onCreate(savedInstanceState);

        mLocationclient = new LocationClient(getApplicationContext());
//        监听位置的变化
        mLocationclient.registerLocationListener(new MyLocationListener());
        List<String> permissionList = new ArrayList<>();
//        开启权限
        if (ContextCompat.checkSelfPermission(BaseLogin.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(BaseLogin.this,Manifest.permission.READ_PHONE_STATE)
                !=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(BaseLogin.this,permissions,1);
        }else{
            requestLocation();
        }
    }

    //    设置更新时间间隔
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setScanSpan(2000);
        option.setIsNeedAddress(true);
        mLocationclient.setLocOption(option);
    }
    //    开启定位并且初始化
    private void requestLocation(){
        initLocation();
        mLocationclient.start();
    }

    //    请求权限之后返回数值
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    //    监听器响应值
    private class  MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            double log = bdLocation.getLatitude();

            double lat = bdLocation.getLongitude();

            Log = bdLocation.getLongitude();
            Lat = bdLocation.getLatitude();
            System.out.println("登陆页面经纬度：");
            System.out.println(String.format("%.6f", log));
            System.out.println(String.format("%.6f", lat));
        }
    }

    @Override
    protected void onDestroy() {
        mLocationclient.stop();
        super.onDestroy();
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return RestClient.login(params[0], params[1]);
        }

        @Override
        protected void onPostExecute(String ret) {
            System.out.println(ret);
            super.onPostExecute(ret);
            if(ret != null){
                SharedPreferences spUserInfo = getSharedPreferences("spUserInfo",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor eUserInfo = spUserInfo.edit();
                eUserInfo.putString("UserInfo", ret);
                eUserInfo.commit();

                String pattern1 = "\"studentid\":(.*?),\"";
                String pattern2 = "\"firstname\":\"(.*?)\"";
                // Create a Pattern object
                Pattern r1 = Pattern.compile(pattern1);
                Pattern r2 = Pattern.compile(pattern2);
                // Now create matcher object.
                Matcher m1 = r1.matcher(ret);
                Matcher m2 = r2.matcher(ret);
                if(m1.find() && m2.find()){
                    SharedPreferences spStudentid = getSharedPreferences("spStudentid",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor eStudentid = spStudentid.edit();
                    eStudentid.putString("Studentid", m1.group(1));
                    eStudentid.putString("firstname", m2.group(1));
                    eStudentid.commit();

//                    new postLocAsyncTask().execute();

                    Intent intent = new Intent(BaseLogin.this, MainActivity.class);

                    intent.putExtra("Log", Log);
                    intent.putExtra("Lat", Lat);
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

    class postLocAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            // get sid
            SharedPreferences spStudentid = getSharedPreferences("spStudentid",
                    Context.MODE_PRIVATE);
            String sid = spStudentid.getString("Studentid", "");
            // create profile
            Profile profile = RestClient.createProfile(sid);
            // get date & time
            String mm = month >= 10 ? month+"" : "0"+month;
            String dd = day >= 10 ? day+"" : "0"+day;
            String curDate = year+"-"+mm+"-"+dd+"T00:00:00+08:00";
            String hh = hour >= 10 ? hour+"" : "0"+hour;
            String MM = minute >= 10 ? minute+"" : "0"+minute;
            String ss = second >= 10 ? second+"" : "0"+second;
            String curTime = "1970-01-01T" + hh + ":" + MM + ":" + ss + "+08:00";

            LocationClass loc = new LocationClass(second+minute*100+hour*10000+day*1000000+month*100000000, profile, "Suzhou", 25.0, 52.0, curDate, curTime);
            RestClient.postLocation(loc);
            return "Location is added";
        }

        @Override
        protected void onPostExecute(String ret) {
            System.out.println(ret);
        }
    }
}