package com.group.friendfinder.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.group.friendfinder.Base.BaseFragment;

import com.group.friendfinder.R;
import com.group.friendfinder.View.search.SearchFragment;
import com.group.friendfinder.View.home.HomeFragment;
import com.group.friendfinder.View.my.MyFragment;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


public class MainActivity extends AppCompatActivity{

    private BaseFragment mSelectedFragment;
    private BaseFragment[] mFragments = new BaseFragment[3];
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences spStudentid = getSharedPreferences("spStudentid",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor eStudentid = spStudentid.edit();
        eStudentid.putString("Studentid", "30074000");
        eStudentid.commit();

        String sid = spStudentid.getString("Studentid", "");
        System.out.println(sid);

        mToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initContentFragment();
        initBottomBar();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_setting:
                Toast.makeText(MainActivity.this, "Individual center", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initBottomBar()
    {
        BottomBar bottomBar = findViewById(R.id.bottom_bar_view);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                int position = 0;
                switch (tabId)
                {
                    case R.id.menu_home:   position = 0; break;
                    case R.id.menu_search: position = 1; break;
                    case R.id.menu_my:     position = 2; break;
                }

                FragmentTransaction fragmentTransaction =
                        getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(mSelectedFragment);
                fragmentTransaction.show(mFragments[position]);
                fragmentTransaction.commit();

                mSelectedFragment.setUserVisibleHint(false);
                mFragments[position].setUserVisibleHint(true);
                mSelectedFragment = mFragments[position];
                setToolbarColor(position);
            }
        });
    }



    private void initContentFragment()
    {
        mFragments[0] = new HomeFragment();
        mFragments[1] = new SearchFragment();
        mFragments[2] = new MyFragment();

        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_content_view, mFragments[0]);
        fragmentTransaction.add(R.id.frame_content_view, mFragments[1]);
        fragmentTransaction.add(R.id.frame_content_view, mFragments[2]);
        fragmentTransaction.show(mFragments[0]);
        fragmentTransaction.hide(mFragments[1]);
        fragmentTransaction.hide(mFragments[2]);
        fragmentTransaction.commit();
        mSelectedFragment = mFragments[0];

        setToolbarColor(0);
    }

    private void setToolbarColor(int p)
    {
        switch (p)
        {
            //mToolbar.setBackgroundColor();
            //"#"+Integer.toHexString(getResources().getColor(R.color.home_bar))
            case 0:   mToolbar.setBackgroundColor(getResources().getColor(R.color.home_bar)); break;
            case 1:   mToolbar.setBackgroundColor(getResources().getColor(R.color.search_bar)); break;
            case 2:   mToolbar.setBackgroundColor(getResources().getColor(R.color.my_bar)); break;
        }

    }
}
