package com.group.friendfinder.View.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group.friendfinder.R;

public class HomeGridviewAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    int[] icon = {R.mipmap.ic_launcher, R.mipmap.home, R.mipmap.search, R.mipmap.my, R.mipmap.pie, R.mipmap.bar};
    String[] iconName = {"exampleActivity","faction1", "faction2", "faction3", "Favorite Units", "Location BarChart"};

    public HomeGridviewAdapter(Context context)
    {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return icon.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (convertView == null)
        {
            convertView = mLayoutInflater.inflate(R.layout.home_gridview_item, null);

            holder = new ViewHolder();
            holder.Grid_imageview = convertView.findViewById(R.id.grid_item_img);
            holder.Grid_textview = convertView.findViewById(R.id.grid_item_txt);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Grid_textview.setText(iconName[position]);
        holder.Grid_imageview.setImageResource(icon[position]);

        return convertView;
    }

    static class ViewHolder
    {
        public ImageView Grid_imageview;
        public TextView Grid_textview;
    }
}
