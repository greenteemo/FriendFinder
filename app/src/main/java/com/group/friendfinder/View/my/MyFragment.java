package com.group.friendfinder.View.my;

import android.view.View;

import com.group.friendfinder.Base.BaseLazyLoadFragment;
import com.group.friendfinder.R;

public class MyFragment extends BaseLazyLoadFragment{
    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void initView(View mContentView) {

    }
}
