package com.group.friendfinder.View.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.group.friendfinder.Base.BaseLazyLoadFragment;
import com.group.friendfinder.Base.MyLocationListener;
import com.group.friendfinder.R;
import com.group.friendfinder.View.home.func.ChooseDate;
import com.group.friendfinder.View.home.func.FavorUnitsPie;
import com.group.friendfinder.View.home.func.GetMovieInfo;
import com.group.friendfinder.View.home.func.exampleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends BaseLazyLoadFragment{
    private GridView gridView;
    private TextView txtTime;
    private TextView tv_city;
    private TextView tv_weather;
    private TextView tv_wind;

    private double log, lat;
    private String urlStr = "https://free-api.heweather.com/s6/weather/now?lang=en&key=766daf718ff14ed7840c74b476d9623d&location=";
    //"https://free-api.heweather.com/s6/weather/now?lang=en&key=766daf718ff14ed7840c74b476d9623d&location=31.298886,120.58531600000003";

    private final Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long time = System.currentTimeMillis();
                    Date date = new Date(time);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EEEE");
                    String data = format.format(date);
                    txtTime.setText(data);
                    break;
                case 2:
                    break;
                default:
                    break;

            }
        }
    };

    class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = 1; //消息(一个整型值)
                    mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } while (true);
        }
    }

    // 获取经纬度
    private class  MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            log = bdLocation.getLatitude();

            lat = bdLocation.getLongitude();

        }
    }


    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TimeThread().start();


        new MyLocationListener();
        urlStr += log + "," + lat;
        new getWeatherAsyncTask().execute(urlStr);

        System.out.println(log);
        System.out.println(lat);
    }

    @Override
    protected void initView(View mContentView) {

        txtTime = mContentView.findViewById(R.id.txtTime);
        tv_city = mContentView.findViewById(R.id.txtWeather1);
        tv_weather = mContentView.findViewById(R.id.txtWeather2);
        tv_wind = mContentView.findViewById(R.id.txtWeather3);

        gridView = mContentView.findViewById(R.id.home_gridview);
        gridView.setAdapter(new HomeGridviewAdapter(getContext()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position)
                {
//                    case 0:
//                        Intent intent1 = new Intent(getActivity(), exampleActivity.class);
//                        startActivity(intent1);
//                        //Toast.makeText(getContext(), "function1_Activity",Toast.LENGTH_SHORT).show();
//                        break;
//                    case 1:
////                        Intent intent = new Intent(getActivity(), function1_Activity().class);
////                        startActivity(intent);
//                        Toast.makeText(getContext(), "function1_Activity",Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
////                        Intent intent = new Intent(getActivity(), function2_Activity().class);
////                        startActivity(intent);
//                        Toast.makeText(getContext(), "to function2_Activity",Toast.LENGTH_SHORT).show();
//                        break;
                    case 0:
                        Intent intent = new Intent(getActivity(), GetMovieInfo.class);
                        startActivity(intent);
//                        Toast.makeText(getContext(), "to function3_Activity",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent4 = new Intent(getActivity(), FavorUnitsPie.class);
                        startActivity(intent4);
                        break;
                    case 2:
                        Intent intent5 = new Intent(getActivity(), ChooseDate.class);
                        startActivity(intent5);
                        break;  //windows-x86_64\qemu-system-i386.exe: failed to initialize HAX: Invalid argument
                }
            }
        });
    }


    class getWeatherAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return getURLResponse(strings[0]);
        }


        @Override
        protected void onPostExecute(String ret) {
            super.onPostExecute(ret);

            try{
                JSONObject jsonObject = new JSONObject(ret);
                JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
                JSONObject allJsonObject = jsonArray.getJSONObject(0);

                String status = allJsonObject.getString("status");
                if (status.equals("ok")){
                    JSONObject basic = allJsonObject.getJSONObject("basic");
                    JSONObject update = allJsonObject.getJSONObject("update");
                    JSONObject now = allJsonObject.getJSONObject("now");

                    String city = "loc: " + basic.getString("parent_city");
                    String wea = "weather: " + now.getString("cond_txt") + " " + now.getString("tmp") + "deg";
                    String wind = "wind: " + now.getString("wind_dir") + " level " + now.getString("wind_sc");
                    tv_city.setText(city);
                    tv_weather.setText(wea);
                    tv_wind.setText(wind);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private String getURLResponse(String urlString){
        HttpURLConnection conn = null;
        InputStream in = null;
        String responseStr = "";

        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            //System.out.println(conn.getResponseCode());
            in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            //response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                //response.append(line).append("\r\n");
                responseStr += line + "\n";
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (conn != null){
                conn.disconnect();
            }
        }

        return responseStr;
    }
}
