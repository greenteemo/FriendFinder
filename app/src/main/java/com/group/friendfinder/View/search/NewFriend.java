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
    private String getProfileStr = "";
    private Profile profile1;
    private Profile profile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        Intent intent = getIntent();
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
                if(mode == 0){
                    String startDate = "2019-05-05T00:00:00+08:00";
                    String endDate = "";
                    new postFriendshipAsync().execute(sid, stuid+"", startDate, endDate);
                    Toast.makeText(NewFriend.this, "you add a new friend successfully", Toast.LENGTH_LONG);
                }else{
                    String friendshipid = spStudentid + "_" + sid;
                    new deleteFriendAsyncTask().execute(friendshipid);
                    Toast.makeText(NewFriend.this, "you delete a new friend successfully", Toast.LENGTH_LONG);
                }
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
            return RestClient.getStudents(stuid);
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
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
            // construct Profile by studentid
//            public String getProfileStr = "";  记得在开头声明


            String patternsmonashEmail = "\"smonashEmail\":\"(.*?)\"";
            String patternspassword = "\"spassword\":\"(.*?)\"";
            String patternfirstname = "\"firstname\":\"(.*?)\"";
            String patternsurname = "\"surname\":\"(.*?)\"";
            String patterndateOfBirth = "\"dateOfBirth\":\"(.*?)\"";
            String patterngender = "\"gender\":\"(.*?)\"";
            String patternnationality = "\"nationality\":\"(.*?)\"";
            String patternnativeLanguage = "\"nativeLanguage\":\"(.*?)\"";
            String patternaddress = "\"address\":\"(.*?)\"";
            String patternsuburb = "\"suburb\":\"(.*?)\"";
            String patterncourse = "\"course\":\"(.*?)\"";
            String patternstudyMode = "\"studyMode\":\"(.*?)\"";
            String patternjob = "\"job\":\"(.*?)\"";
            String patternfavorSport = "\"favorSport\":\"(.*?)\"";
            String patternfavorMovie = "\"favorMovie\":\"(.*?)\"";
            String patternfavorUnit = "\"favorUnit\":\"(.*?)\"";
            String patternsubscribeData = "\"subscribeData\":\"(.*?)\"";
            String patternsubscribeTime = "\"subscribeTime\":\"(.*?)\"";

            // Create a Pattern object
            Pattern rsmonashEmail = Pattern.compile(patternsmonashEmail);
            Pattern rspassword = Pattern.compile(patternspassword);
            Pattern rfirstname = Pattern.compile(patternfirstname);
            Pattern rsurname = Pattern.compile(patternsurname);
            Pattern rdateOfBirth = Pattern.compile(patterndateOfBirth);
            Pattern rgender = Pattern.compile(patterngender);
            Pattern rnationality = Pattern.compile(patternnationality);
            Pattern rnativeLanguage = Pattern.compile(patternnativeLanguage);
            Pattern raddress = Pattern.compile(patternaddress);
            Pattern rsuburb = Pattern.compile(patternsuburb);
            Pattern rcourse = Pattern.compile(patterncourse);
            Pattern rstudyMode = Pattern.compile(patternstudyMode);
            Pattern rjob = Pattern.compile(patternjob);
            Pattern rfavorSport = Pattern.compile(patternfavorSport);
            Pattern rfavorMovie = Pattern.compile(patternfavorMovie);
            Pattern rfavorUnit = Pattern.compile(patternfavorUnit);
            Pattern rsubscribeData = Pattern.compile(patternsubscribeData);
            Pattern rsubscribeTime = Pattern.compile(patternsubscribeTime);

            new getStudentAsync().execute(Integer.parseInt(params[0]));
            // Now create matcher object.
            Matcher msmonashEmail = rsmonashEmail.matcher(getProfileStr);
            Matcher mspassword = rspassword.matcher(getProfileStr);
            Matcher mfirstname = rfirstname.matcher(getProfileStr);
            Matcher msurname = rsurname.matcher(getProfileStr);
            Matcher mdateOfBirth = rdateOfBirth.matcher(getProfileStr);
            Matcher mgender = rgender.matcher(getProfileStr);
            Matcher mnationality = rnationality.matcher(getProfileStr);
            Matcher mnativeLanguage = rnativeLanguage.matcher(getProfileStr);
            Matcher maddress = raddress.matcher(getProfileStr);
            Matcher msuburb = rsuburb.matcher(getProfileStr);
            Matcher mcourse = rcourse.matcher(getProfileStr);
            Matcher mstudyMode = rstudyMode.matcher(getProfileStr);
            Matcher mjob = rjob.matcher(getProfileStr);
            Matcher mfavorSport = rfavorSport.matcher(getProfileStr);
            Matcher mfavorMovie = rfavorMovie.matcher(getProfileStr);
            Matcher mfavorUnit = rfavorUnit.matcher(getProfileStr);
            Matcher msubscribeData = rsubscribeData.matcher(getProfileStr);
            Matcher msubscribeTime = rsubscribeTime.matcher(getProfileStr);
            if(msmonashEmail.find() && mspassword.find() && mfirstname.find() && msurname.find() && mdateOfBirth.find() && mgender.find() && mnationality.find() && mnativeLanguage.find() && maddress.find() && msuburb.find() && mcourse.find() && mstudyMode.find() && mjob.find() && mfavorSport.find() && mfavorMovie.find() && mfavorUnit.find() && msubscribeData.find() && msubscribeTime.find()){
                System.out.println(getProfileStr);
                profile1 = new Profile(Integer.parseInt(params[0]), msmonashEmail.group(1), mspassword.group(1), mfirstname.group(1), msurname.group(1), mdateOfBirth.group(1), mgender.group(1).charAt(0), mnationality.group(1), mnativeLanguage.group(1), maddress.group(1), msuburb.group(1), mcourse.group(1), mstudyMode.group(1).charAt(0), mjob.group(1), mfavorSport.group(1), mfavorMovie.group(1), mfavorUnit.group(1), msubscribeData.group(1), msubscribeTime.group(1));
            }

            new getStudentAsync().execute(Integer.parseInt(params[1]));
            msmonashEmail = rsmonashEmail.matcher(getProfileStr);
            mspassword = rspassword.matcher(getProfileStr);
            mfirstname = rfirstname.matcher(getProfileStr);
            msurname = rsurname.matcher(getProfileStr);
            mdateOfBirth = rdateOfBirth.matcher(getProfileStr);
            mgender = rgender.matcher(getProfileStr);
            mnationality = rnationality.matcher(getProfileStr);
            mnativeLanguage = rnativeLanguage.matcher(getProfileStr);
            maddress = raddress.matcher(getProfileStr);
            msuburb = rsuburb.matcher(getProfileStr);
            mcourse = rcourse.matcher(getProfileStr);
            mstudyMode = rstudyMode.matcher(getProfileStr);
            mjob = rjob.matcher(getProfileStr);
            mfavorSport = rfavorSport.matcher(getProfileStr);
            mfavorMovie = rfavorMovie.matcher(getProfileStr);
            mfavorUnit = rfavorUnit.matcher(getProfileStr);
            msubscribeData = rsubscribeData.matcher(getProfileStr);
            msubscribeTime = rsubscribeTime.matcher(getProfileStr);

            if(msmonashEmail.find() && mspassword.find() && mfirstname.find() && msurname.find() && mdateOfBirth.find() && mgender.find() && mnationality.find() && mnativeLanguage.find() && maddress.find() && msuburb.find() && mcourse.find() && mstudyMode.find() && mjob.find() && mfavorSport.find() && mfavorMovie.find() && mfavorUnit.find() && msubscribeData.find() && msubscribeTime.find()){
                System.out.println(getProfileStr);
                profile2 = new Profile(Integer.parseInt(params[1]), msmonashEmail.group(1), mspassword.group(1), mfirstname.group(1), msurname.group(1), mdateOfBirth.group(1), mgender.group(1).charAt(0), mnationality.group(1), mnativeLanguage.group(1), maddress.group(1), msuburb.group(1), mcourse.group(1), mstudyMode.group(1).charAt(0), mjob.group(1), mfavorSport.group(1), mfavorMovie.group(1), mfavorUnit.group(1), msubscribeData.group(1), msubscribeTime.group(1));
                System.out.println(profile2.getFirstname());
            }

            Friendship friendship = new Friendship(Integer.parseInt(params[0]) + "_" + Integer.parseInt(params[1]), params[2], params[3], profile1, profile2);
            RestClient.postFriendship(friendship);
            return "Friendship was added";
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String response) {
            System.out.println(response);
        }
    }
    private class getStudentAsync extends AsyncTask<Integer, Void, String> {

        protected String doInBackground(Integer... params) {
            return RestClient.getStudents(params[0]);
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String students) {
            System.out.println(students);
            getProfileStr = students;
        }
    }
    private class deleteFriendAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            RestClient.deleteFriend(params[0]);
            return "Friendship was deleted";
        }

        @Override
        protected void onPostExecute(String ret) {
            System.out.println(ret);
        }
    }
}
