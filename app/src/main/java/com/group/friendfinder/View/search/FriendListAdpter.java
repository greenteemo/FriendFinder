package com.group.friendfinder.View.search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.group.friendfinder.Friendship;
import com.group.friendfinder.Profile;
import com.group.friendfinder.R;
import com.group.friendfinder.View.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FriendListAdpter extends BaseAdapter {
    private JSONArray mArray;
    private Context mContext;
    private int mode = 0;
    private LayoutInflater mlayoutinflater;
    private String getProfileStr = "";
    private Profile profile1;
    private Profile profile2;

    public FriendListAdpter(JSONArray jsonArray, Context context, int mode){
        this.mArray = jsonArray;
        this.mContext = context;
        this.mode = mode;
        mlayoutinflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mArray.length();
    }


    static class ViewHolder {
        public Button mTextApply, mTextMore;
        public TextView mTextName,mTextGender,mTextNationality;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mholder = null;
        if (convertView == null){
            convertView = mlayoutinflater.inflate(R.layout.newfriendlist, null);
            mholder = new ViewHolder();
            mholder.mTextApply = convertView.findViewById(R.id.new_friend_apply_text);
            mholder.mTextName = convertView.findViewById(R.id.new_friend_name_text);
            mholder.mTextGender = convertView.findViewById(R.id.new_friend_gender_text);
            mholder.mTextNationality = convertView.findViewById(R.id.new_friend_nationality_text);
            mholder.mTextMore = convertView.findViewById(R.id.new_friend_more_detail_text);
            convertView.setTag(mholder);
        }else {
            mholder = (ViewHolder) convertView.getTag();
        }
        mholder.mTextApply.setText(mode == 0?"ADD":"DELETE");
        if(mode == 1) mholder.mTextApply.setTextColor(Color.RED);
        mholder.mTextMore.setText("DETAILS");
        try {
            JSONObject mobj = mArray.getJSONObject(position);
            final int stuid = mobj.getInt("studentid");
            mholder.mTextName.setText(mobj.getString("firstname") + " " + mobj.getString("surname"));
            mholder.mTextGender.setText(mobj.getInt("gender") == 1? "female":"male");
            mholder.mTextNationality.setText(mobj.getString("nationality"));
            mholder.mTextMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, NewFriend.class);
                    intent.putExtra("stuid", stuid);
                    mContext.startActivity(intent);
                }
            });
            mholder.mTextApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences spStudentid = mContext.getSharedPreferences("spStudentid",
                            Context.MODE_PRIVATE);
                    String sid = spStudentid.getString("Studentid", "");
                    String startDate = "2019-05-05T00:00:00+08:00";
                    String endDate = "";
                    new postFriendshipAsync().execute(sid, stuid+"", startDate, endDate);
                }
            });
        }catch (JSONException e){
        }
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
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
}
