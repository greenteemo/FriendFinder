package com.group.friendfinder.View.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;

import com.group.friendfinder.Base.BaseLazyLoadFragment;
import com.group.friendfinder.R;
import com.group.friendfinder.View.search.SearchResult;

public class SearchFragment extends BaseLazyLoadFragment{

    private CheckBox cb[] = new CheckBox[14];
    private enum mCB{
        search_first_name,
        search_surname,
        search_birthday,
        search_gender,
        search_course,
        search_study_mode,
        search_address,
        search_suburb,
        search_nationality,
        search_native_language,
        search_favourite_sport,
        search_favourite_movie,
        search_favourite_unit,
        search_current_job,
    }
    private Button mbtn1;

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void initView(View mContentView) {
        this.cb[mCB.search_first_name.ordinal()] = mContentView.findViewById(R.id.search_first_name);
        this.cb[mCB.search_surname.ordinal()] = mContentView.findViewById(R.id.search_surname);
        this.cb[mCB.search_birthday.ordinal()] = mContentView.findViewById(R.id.search_birthday);
        this.cb[mCB.search_course.ordinal()] = mContentView.findViewById(R.id.search_course);
        this.cb[mCB.search_address.ordinal()] = mContentView.findViewById(R.id.search_address);
        this.cb[mCB.search_suburb.ordinal()] = mContentView.findViewById(R.id.search_suburb);
        this.cb[mCB.search_native_language.ordinal()] = mContentView.findViewById(R.id.search_native_language);
        this.cb[mCB.search_favourite_sport.ordinal()] = mContentView.findViewById(R.id.search_favourite_sport);
        this.cb[mCB.search_favourite_movie.ordinal()] = mContentView.findViewById(R.id.search_favourite_movie);
        this.cb[mCB.search_favourite_unit.ordinal()] = mContentView.findViewById(R.id.search_favourite_unit);
        this.cb[mCB.search_current_job.ordinal()] = mContentView.findViewById(R.id.search_current_job);
        this.cb[mCB.search_gender.ordinal()] = mContentView.findViewById(R.id.search_gender);
        this.cb[mCB.search_study_mode.ordinal()] = mContentView.findViewById(R.id.search_study_mode);
        this.cb[mCB.search_nationality.ordinal()] = mContentView.findViewById(R.id.search_nationality);
        this.mbtn1 = mContentView.findViewById(R.id.search_button);

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int attributes[] = new int[14];
                int index = 0;
                for(int i = 0;i < attributes.length; i++ ){
                    attributes[i] = 0;
                    if(cb[i].isChecked()){
                        attributes[i] = 1;
                        index++;
                    }
                }
                if(index == 0) {
                    Toast.makeText(getContext(), "you have to select one attribute at least",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), SearchResult.class);
                    startActivity(intent);
                }
            }
        });
    }

}
