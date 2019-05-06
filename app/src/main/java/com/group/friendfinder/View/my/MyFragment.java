package com.group.friendfinder.View.my;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.group.friendfinder.Base.BaseLazyLoadFragment;
import com.group.friendfinder.R;
import com.group.friendfinder.View.search.SearchResult;

public class MyFragment extends BaseLazyLoadFragment{
    private TextView my_show_studentid,my_show_email,my_show_first_name,my_show_surname,
            my_show_birthday,my_show_gender,my_show_course,my_show_study_mode,my_show_address,
            my_show_suburb,my_show_nationality,my_show_native_language,my_show_favourite_sport,
            my_show_favourite_movie,my_show_favourite_unit,my_show_current_job;
    private Button my_button;
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
        my_show_studentid = mContentView.findViewById(R.id.my_show_studentid);
        my_show_email = mContentView.findViewById(R.id.my_show_email);
        my_show_first_name = mContentView.findViewById(R.id.my_show_first_name);
        my_show_surname = mContentView.findViewById(R.id.my_show_surname);
        my_show_birthday = mContentView.findViewById(R.id.my_show_birthday);
        my_show_gender = mContentView.findViewById(R.id.my_show_gender);
        my_show_course = mContentView.findViewById(R.id.my_show_course);
        my_show_study_mode = mContentView.findViewById(R.id.my_show_study_mode);
        my_show_address = mContentView.findViewById(R.id.my_show_address);
        my_show_suburb = mContentView.findViewById(R.id.my_show_suburb);
        my_show_nationality = mContentView.findViewById(R.id.my_show_nationality);
        my_show_native_language = mContentView.findViewById(R.id.my_show_native_language);
        my_show_favourite_sport = mContentView.findViewById(R.id.my_show_favourite_sport);
        my_show_favourite_movie = mContentView.findViewById(R.id.my_show_favourite_movie);
        my_show_favourite_unit = mContentView.findViewById(R.id.my_show_favourite_unit);
        my_show_current_job = mContentView.findViewById(R.id.my_show_current_job);
        my_button = mContentView.findViewById(R.id.my_button);

        my_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
            }
        });
    }
}
