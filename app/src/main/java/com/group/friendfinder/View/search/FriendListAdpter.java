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
import android.widget.Toast;

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
            if(mode == 1) {
                mobj = mobj.getJSONObject("ffriendid");
            }
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
                    if(mode == 0) {
                        String startDate = "2019-05-05T00:00:00+08:00";
                        String endDate = "";
                        new postFriendshipAsync().execute(sid, stuid + "", startDate, endDate);
                        Toast.makeText(mContext, "you add a new friend successfully", Toast.LENGTH_LONG);
                    }else {
                        String friendshipid = spStudentid + "_" + sid;
                        new deleteFriendAsyncTask().execute(friendshipid);
                        Toast.makeText(mContext, "you delete a new friend successfully", Toast.LENGTH_LONG);
                    }
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
            Profile profile1 = RestClient.createProfile(params[0]);
            Profile profile2 = RestClient.createProfile(params[1]);

            Integer sid = Integer.parseInt(params[0]), fid = Integer.parseInt(params[1]);
            String friendshipid = sid < fid ? sid + "_" + fid : fid + "_" + sid;
            Friendship friendship = new Friendship(friendshipid, params[2], params[3], profile1, profile2);

            RestClient.postFriendship(friendship);
            return "Friendship was added";
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(String response) {
            System.out.println(response);
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
