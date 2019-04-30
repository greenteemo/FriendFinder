package com.group.friendfinder.Base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseLazyLoadFragment extends BaseFragment{
    protected boolean isVisible = false;       //whether visible
    private boolean isPrepared = false;        //whether connected with View
    private boolean isFirst = true;            //whether first loaded

    private View mContentView;

    protected void lazyLoadData()
    {
        if (!isPrepared || !isVisible || !isFirst)
            return;
        getData();
        isFirst = false;
    }


    //set the state of whether it can be visible
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        if (isVisibleToUser)
        {
            isVisible = true;
            lazyLoadData();
        }
        else
        {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        if (mContentView == null)
        {
            mContentView = inflater.inflate(getLayoutId(), container, false);
        }
        initView(mContentView);
        isPrepared = true;
        lazyLoadData();
        return mContentView;
    }

    // load data to show
    protected abstract void getData();
    //connect view in Layout and the variable of the fragment
    protected abstract @LayoutRes int getLayoutId();
    //load the file of Layout
    protected abstract void initView(View mContentView);

}
