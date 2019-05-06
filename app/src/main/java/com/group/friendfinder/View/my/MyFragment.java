package com.group.friendfinder.View.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.group.friendfinder.Base.BaseLazyLoadFragment;
import com.group.friendfinder.R;
import com.group.friendfinder.View.search.SearchResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        SharedPreferences spUserInfo = getActivity().getSharedPreferences("spUserInfo", Context.MODE_PRIVATE);
        String UserInfo = spUserInfo.getString("UserInfo", "");
        try{
            JSONArray marray = new JSONArray(UserInfo);
            JSONObject mobj = marray.getJSONObject(0);
            my_show_studentid.setText(mobj.getString("studentid"));
            my_show_email.setText(mobj.getString("smonashEmail"));
            my_show_first_name.setText(mobj.getString("firstname"));
            my_show_surname.setText(mobj.getString("surname"));
            my_show_birthday.setText(mobj.getString("dateOfBirth"));
            my_show_gender.setText(mobj.getInt("gender")== 0?"male":"female");
            my_show_course.setText(mobj.getString("course"));
            my_show_study_mode.setText(mobj.getInt("studyMode")== 0?"full time":"part time");
            my_show_address.setText(mobj.getString("address"));
            my_show_suburb.setText(mobj.getString("suburb"));
            my_show_nationality.setText(mobj.getString("nationality"));
            my_show_native_language.setText(mobj.getString("nativeLanguage"));
            my_show_favourite_sport.setText(mobj.getString("favorSport"));
            my_show_favourite_movie.setText(mobj.getString("favorMovie"));
            my_show_favourite_unit.setText(mobj.getString("favorUnit"));
            my_show_current_job.setText(mobj.getString("job"));
            my_button = mContentView.findViewById(R.id.my_button);
        }catch(JSONException e){

        }
        System.out.println(UserInfo);

        my_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);

            }
        });
    }
}
