package com.group.friendfinder.View.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.group.friendfinder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FriendListAdpter extends BaseAdapter {
    private JSONArray mArray;
    private Context mContext;
    private LayoutInflater mlayoutinflater;

    public FriendListAdpter(JSONArray jsonArray, Context context){
        this.mArray = jsonArray;
        this.mContext = context;
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
        mholder.mTextApply.setText("ADD");
        mholder.mTextMore.setText("DETAILS");
        try {
            JSONObject mobj = mArray.getJSONObject(position);
            mholder.mTextName.setText(mobj.getString("firstname") + " " + mobj.getString("surname"));
            mholder.mTextGender.setText(mobj.getInt("gender") == 1? "female":"male");
            mholder.mTextNationality.setText(mobj.getString("nationality"));
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
}
