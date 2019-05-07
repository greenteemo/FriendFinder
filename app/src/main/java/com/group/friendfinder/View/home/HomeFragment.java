package com.group.friendfinder.View.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.group.friendfinder.Base.BaseLazyLoadFragment;
import com.group.friendfinder.R;
import com.group.friendfinder.View.home.func.ChooseDate;
import com.group.friendfinder.View.home.func.FavorUnitsPie;
import com.group.friendfinder.View.home.func.GetMovieInfo;
import com.group.friendfinder.View.home.func.LocationBarChart;
import com.group.friendfinder.View.home.func.exampleActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends BaseLazyLoadFragment{
    private GridView gridView;

    private TextView txtTime;
    private final Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //Weather weather = (Weather) msg.obj;
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
    }

    @Override
    protected void initView(View mContentView) {

        txtTime = mContentView.findViewById(R.id.txtTime);

        gridView = mContentView.findViewById(R.id.home_gridview);
        gridView.setAdapter(new HomeGridviewAdapter(getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        Intent intent1 = new Intent(getActivity(), exampleActivity.class);
                        startActivity(intent1);
                        //Toast.makeText(getContext(), "function1_Activity",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
//                        Intent intent = new Intent(getActivity(), function1_Activity().class);
//                        startActivity(intent);
                        Toast.makeText(getContext(), "function1_Activity",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                        Intent intent = new Intent(getActivity(), function2_Activity().class);
//                        startActivity(intent);
                        Toast.makeText(getContext(), "to function2_Activity",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Intent intent = new Intent(getActivity(), GetMovieInfo.class);
                        startActivity(intent);
//                        Toast.makeText(getContext(), "to function3_Activity",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Intent intent4 = new Intent(getActivity(), FavorUnitsPie.class);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5 = new Intent(getActivity(), ChooseDate.class);
                        startActivity(intent5);
                        break;
                }
            }
        });
    }
}
