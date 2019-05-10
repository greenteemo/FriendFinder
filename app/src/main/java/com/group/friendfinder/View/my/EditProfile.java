package com.group.friendfinder.View.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.group.friendfinder.Profile;
import com.group.friendfinder.R;
import com.group.friendfinder.View.MainActivity;
import com.group.friendfinder.View.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditProfile extends AppCompatActivity {

    private EditText et[] = new EditText[12];

    private enum mET {
        profile_edit_first_name,
        profile_edit_surname,
        profile_edit_birthday,
        profile_edit_course,
        profile_edit_address,
        profile_edit_suburb,
        profile_edit_nationality,
        profile_edit_native_language,
        profile_edit_favourite_sport,
        profile_edit_favourite_movie,
        profile_edit_favourite_unit,
        profile_edit_current_job
    }

    private Spinner sp[] = new Spinner[3];

    private enum mSP {
        profile_edit_gender,
        profile_edit_study_mode,
    }

    private Button mbtn1;
    private String studentid, smonashEmail, spassword, firstname, surname, dateOfBirth, gender, nationality, nativeLanguage, address, suburb, course, studyMode, job, favorSport, favorMovie, favorUnit, subscribeData, subscribeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        this.et[mET.profile_edit_first_name.ordinal()] = findViewById(R.id.profile_edit_first_name);
        this.et[mET.profile_edit_surname.ordinal()] = findViewById(R.id.profile_edit_surname);
        this.et[mET.profile_edit_birthday.ordinal()] = findViewById(R.id.profile_edit_birthday);
        this.et[mET.profile_edit_course.ordinal()] = findViewById(R.id.profile_edit_course);
        this.et[mET.profile_edit_address.ordinal()] = findViewById(R.id.profile_edit_address);
        this.et[mET.profile_edit_suburb.ordinal()] = findViewById(R.id.profile_edit_suburb);
        this.et[mET.profile_edit_native_language.ordinal()] = findViewById(R.id.profile_edit_native_language);
        this.et[mET.profile_edit_favourite_sport.ordinal()] = findViewById(R.id.profile_edit_favourite_sport);
        this.et[mET.profile_edit_favourite_movie.ordinal()] = findViewById(R.id.profile_edit_favourite_movie);
        this.et[mET.profile_edit_favourite_unit.ordinal()] = findViewById(R.id.profile_edit_favourite_unit);
        this.et[mET.profile_edit_current_job.ordinal()] = findViewById(R.id.profile_edit_current_job);
        this.sp[mSP.profile_edit_gender.ordinal()] = findViewById(R.id.profile_edit_gender);
        this.sp[mSP.profile_edit_study_mode.ordinal()] = findViewById(R.id.profile_edit_study_mode);
        this.et[mET.profile_edit_nationality.ordinal()] = findViewById(R.id.profile_edit_nationality);
        this.mbtn1 = findViewById(R.id.profile_button);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences spUserInfo = getSharedPreferences("spUserInfo", Context.MODE_PRIVATE);
        String UserInfo = spUserInfo.getString("UserInfo", "");
        try {
            JSONArray marray = new JSONArray(UserInfo);
            JSONObject mobj = marray.getJSONObject(0);

            studentid = mobj.getString("studentid");
            smonashEmail = mobj.getString("smonashEmail");
            spassword = mobj.getString("spassword");
            subscribeData = mobj.getString("subscribeData");
            subscribeTime = mobj.getString("subscribeTime");

            et[mET.profile_edit_first_name.ordinal()].setText(mobj.getString("firstname"));
            et[mET.profile_edit_surname.ordinal()].setText(mobj.getString("surname"));
            et[mET.profile_edit_birthday.ordinal()].setText(mobj.getString("dateOfBirth"));
            sp[mSP.profile_edit_gender.ordinal()].setSelection(mobj.getInt("gender"));
            et[mET.profile_edit_course.ordinal()].setText(mobj.getString("course"));
            sp[mSP.profile_edit_study_mode.ordinal()].setSelection(mobj.getInt("studyMode"));
            et[mET.profile_edit_address.ordinal()].setText(mobj.getString("address"));
            et[mET.profile_edit_suburb.ordinal()].setText(mobj.getString("suburb"));
            et[mET.profile_edit_nationality.ordinal()].setText(mobj.getString("nationality"));
            et[mET.profile_edit_native_language.ordinal()].setText(mobj.getString("nativeLanguage"));
            et[mET.profile_edit_favourite_sport.ordinal()].setText(mobj.getString("favorSport"));
            et[mET.profile_edit_favourite_movie.ordinal()].setText(mobj.getString("favorMovie"));
            et[mET.profile_edit_favourite_unit.ordinal()].setText(mobj.getString("favorUnit"));
            et[mET.profile_edit_current_job.ordinal()].setText(mobj.getString("job"));
        } catch (JSONException e) {

        }

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = et[mET.profile_edit_first_name.ordinal()].getText().toString();
                surname = et[mET.profile_edit_surname.ordinal()].getText().toString();
                dateOfBirth = et[mET.profile_edit_birthday.ordinal()].getText().toString();
                gender = String.valueOf(sp[mSP.profile_edit_gender.ordinal()].getSelectedItemPosition());
                nationality = et[mET.profile_edit_nationality.ordinal()].getText().toString();
                nativeLanguage = et[mET.profile_edit_native_language.ordinal()].getText().toString();
                address = et[mET.profile_edit_address.ordinal()].getText().toString();
                suburb = et[mET.profile_edit_suburb.ordinal()].getText().toString();
                course = et[mET.profile_edit_course.ordinal()].getText().toString();
                studyMode = String.valueOf(sp[mSP.profile_edit_study_mode.ordinal()].getSelectedItemPosition());
                job = et[mET.profile_edit_current_job.ordinal()].getText().toString();
                favorSport = et[mET.profile_edit_favourite_sport.ordinal()].getText().toString();
                favorMovie = et[mET.profile_edit_favourite_movie.ordinal()].getText().toString();
                favorUnit = et[mET.profile_edit_favourite_unit.ordinal()].getText().toString();
                System.out.println(studentid + "" +
                        smonashEmail + "" +
                        spassword + "" +
                        et[mET.profile_edit_first_name.ordinal()].getText().toString() + "" +
                        et[mET.profile_edit_surname.ordinal()].getText().toString() + "" +
                        et[mET.profile_edit_birthday.ordinal()].getText().toString() + "" +
                        String.valueOf(sp[mSP.profile_edit_gender.ordinal()].getSelectedItemPosition()) + "" +
                        et[mET.profile_edit_nationality.ordinal()].getText().toString() + "" +
                        et[mET.profile_edit_native_language.ordinal()].getText().toString() + "" +
                        et[mET.profile_edit_address.ordinal()].getText().toString() + "" +
                        et[mET.profile_edit_suburb.ordinal()].getText().toString() + "" +
                        et[mET.profile_edit_course.ordinal()].getText().toString() + "" +
                        String.valueOf(sp[mSP.profile_edit_study_mode.ordinal()].getSelectedItemPosition()) + "" +
                        et[mET.profile_edit_current_job.ordinal()].getText().toString() + "" +
                        et[mET.profile_edit_favourite_sport.ordinal()].getText().toString() + "" +
                        et[mET.profile_edit_favourite_movie.ordinal()].getText().toString() + "" +
                        et[mET.profile_edit_favourite_unit.ordinal()].getText().toString() + "" +
                        subscribeData + "" +
                        subscribeTime);
                new updateProfileAsync().execute(studentid, smonashEmail, spassword, firstname, surname, dateOfBirth, gender, nationality, nativeLanguage, address, suburb, course, studyMode, job, favorSport, favorMovie, favorUnit, subscribeData, subscribeTime);
                if (true) //todo
                    new EditAsyncTask().execute(Integer.valueOf(studentid));
            }
        });
    }

    private class updateProfileAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Profile profile = new Profile(Integer.parseInt(params[0]), params[1], params[2], params[3], params[4], params[5], params[6].charAt(0), params[7], params[8], params[9], params[10], params[11], params[12].charAt(0), params[13], params[14], params[15], params[16], params[17], params[18]);
            RestClient.updateProfile(Integer.parseInt(params[0]), profile);
            return "Profile was updated";
        }

        /**
         * The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground()
         */
        protected void onPostExecute(String response) {
            System.out.println(response);
        }
    }

    private class EditAsyncTask extends AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {
            return RestClient.getStudent(params[0]);
        }

        @Override
        protected void onPostExecute(String ret) {
            super.onPostExecute(ret);
            System.out.println("thin is get" + ret);
            if(ret != null){
                SharedPreferences spUserInfo = getSharedPreferences("spUserInfo",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor eUserInfo = spUserInfo.edit();
                eUserInfo.putString("UserInfo", ret);
                eUserInfo.commit();

                Toast.makeText(EditProfile.this,"Profile was updated successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditProfile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }
}
