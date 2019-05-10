package com.group.friendfinder.View.search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.group.friendfinder.Friendship;
import com.group.friendfinder.Profile;
import com.group.friendfinder.R;
import com.group.friendfinder.View.RestClient;
import com.group.friendfinder.View.home.func.GetMovieInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewFriend extends AppCompatActivity {

    private TextView new_friend_show_first_name,new_friend_show_surname,
            new_friend_show_birthday,new_friend_show_gender,new_friend_show_course,new_friend_show_study_mode,new_friend_show_address,
            new_friend_show_suburb,new_friend_show_nationality,new_friend_show_native_language,new_friend_show_favourite_sport,
            new_friend_show_favourite_movie,new_friend_show_favourite_unit,new_friend_show_current_job;
    private Button add_new_friend_button;
    private int stuid,mode;
    private String newfriend;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        final Intent intent = getIntent();
        new_friend_show_first_name = findViewById(R.id.new_friend_show_first_name);
        new_friend_show_surname = findViewById(R.id.new_friend_show_surname);
        new_friend_show_birthday = findViewById(R.id.new_friend_show_birthday);
        new_friend_show_gender = findViewById(R.id.new_friend_show_gender);
        new_friend_show_course = findViewById(R.id.new_friend_show_course);
        new_friend_show_study_mode = findViewById(R.id.new_friend_show_study_mode);
        new_friend_show_address = findViewById(R.id.new_friend_show_address);
        new_friend_show_suburb = findViewById(R.id.new_friend_show_suburb);
        new_friend_show_nationality = findViewById(R.id.new_friend_show_nationality);
        new_friend_show_native_language = findViewById(R.id.new_friend_show_native_language);
        new_friend_show_favourite_sport = findViewById(R.id.new_friend_show_favourite_sport);
        new_friend_show_favourite_movie = findViewById(R.id.new_friend_show_favourite_movie);
        new_friend_show_favourite_unit = findViewById(R.id.new_friend_show_favourite_unit);
        new_friend_show_current_job = findViewById(R.id.new_friend_show_current_job);
        add_new_friend_button = findViewById(R.id.apply_button);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mode = intent.getIntExtra("mode",0);
        if(mode == 1) {
            toolbar.setBackgroundColor(getColor(R.color.my_bar));
            add_new_friend_button.setText("DELETE FRIEND");
            add_new_friend_button.setTextColor(Color.RED);
        }
        int test = intent.getIntExtra("stuid",0);
        System.out.println(test);
        new SearchNewFriendsAsync(intent.getIntExtra("stuid",0)).execute();

        add_new_friend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences spStudentid = getSharedPreferences("spStudentid",
                        Context.MODE_PRIVATE);
                String sid = spStudentid.getString("Studentid", "");
                Integer stuid = intent.getIntExtra("stuid",0);
                String startDate = "2019-05-05T00:00:00+08:00";
                String endDate = "";
                new postFriendshipAsync().execute(sid, stuid+"", startDate, endDate);

//                SharedPreferences spHttpCode = getSharedPreferences("spHttpCode", Context.MODE_PRIVATE);
//                String httpcode = spHttpCode.getString("httpcode", "");
//                System.out.println("----------" + httpcode + "-------");
//                if(httpcode == "204" || httpcode == "200"){
//                    System.out.println("you add a new friend successfully");
//                    final Toast toast = Toast.makeText(NewFriend.this,"you add a new friend successfully",Toast.LENGTH_LONG);
//                    toast.show();
//                }else{
//                    System.out.println("failed to add, please try again!");
//                    final Toast toast  = Toast.makeText(NewFriend.this,"failed to add, please try again!",Toast.LENGTH_LONG);
//                    toast.show();
//                }
            }
        });
        new_friend_show_favourite_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewFriend.this, GetMovieInfo.class);
                intent.putExtra("movie",new_friend_show_favourite_movie.getText());
                startActivity(intent);
            }
        });
    }

    private class SearchNewFriendsAsync extends AsyncTask<Integer, Void, String> {
        private int stuid;
        private SearchNewFriendsAsync(int stuid) {
            this.stuid = stuid;
        }
        protected String doInBackground(Integer... params) {
            return RestClient.getStudent(stuid);
        }

        protected void onPostExecute(String ret) {
            newfriend = ret;
            try{
                JSONArray marray = new JSONArray(newfriend);
                JSONObject mobj = marray.getJSONObject(0);
                new_friend_show_first_name.setText(mobj.getString("firstname"));
                new_friend_show_surname.setText(mobj.getString("surname"));
                new_friend_show_birthday.setText(mobj.getString("dateOfBirth"));
                new_friend_show_gender.setText(mobj.getInt("gender")== 0?"male":"female");
                new_friend_show_course.setText(mobj.getString("course"));
                new_friend_show_study_mode.setText(mobj.getInt("studyMode")== 0?"full time":"part time");
                new_friend_show_address.setText(mobj.getString("address"));
                new_friend_show_suburb.setText(mobj.getString("suburb"));
                new_friend_show_nationality.setText(mobj.getString("nationality"));
                new_friend_show_native_language.setText(mobj.getString("nativeLanguage"));
                new_friend_show_favourite_sport.setText(mobj.getString("favorSport"));
                new_friend_show_favourite_movie.setText(mobj.getString("favorMovie"));
                new_friend_show_favourite_unit.setText(mobj.getString("favorUnit"));
                new_friend_show_current_job.setText(mobj.getString("job"));
            }catch(JSONException e){

            }
        }
    }

    private class postFriendshipAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Friendship friendship;
            Profile profile1 = RestClient.createProfile(params[0]);
            Profile profile2 = RestClient.createProfile(params[1]);

            Integer sid = Integer.parseInt(params[0]), fid = Integer.parseInt(params[1]);
            String friendshipid = sid < fid ? sid + "_" + fid : fid + "_" + sid;
            if(sid > fid){
                friendship = new Friendship(friendshipid, params[2], params[3], profile2, profile1);
            }else{
                friendship = new Friendship(friendshipid, params[2], params[3], profile1, profile2);
            }
            String code = RestClient.postFriendship(friendship);

            SharedPreferences spHttpCode = getSharedPreferences("spHttpCode",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor eHttpCode = spHttpCode.edit();
            eHttpCode.putString("httpcode", code);
            eHttpCode.commit();

            return "Friendship was added";
        }

        protected void onPostExecute(String response) {
            System.out.println(response);
        }
    }
}
