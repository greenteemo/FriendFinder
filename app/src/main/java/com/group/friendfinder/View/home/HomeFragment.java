package com.group.friendfinder.View.home;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.group.friendfinder.Base.BaseLazyLoadFragment;
import com.group.friendfinder.R;
import com.group.friendfinder.View.home.func.FavorUnitsPie;
import com.group.friendfinder.View.home.func.exampleActivity;

public class HomeFragment extends BaseLazyLoadFragment{
    private GridView gridView;
    //private List<Map<String, Object>> data_list;
    //private HomeGridviewAdapter gridAdapter;

    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    @Override
    protected void initView(View mContentView) {
        gridView = mContentView.findViewById(R.id.home_gridview);
        gridView.setAdapter(new HomeGridviewAdapter(getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        Intent intent1 = new Intent(getActivity(), exampleActivity.class);
                        startActivity(intent1);
                        //Toast.makeText(getContext(), "function1_Activity",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
//                        Intent intent = new Intent(getActivity(), function1_Activity().class);
//                        startActivity(intent);
                        Toast.makeText(getContext(), "function1_Activity",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
//                        Intent intent = new Intent(getActivity(), function2_Activity().class);
//                        startActivity(intent);
                        Toast.makeText(getContext(), "to function2_Activity",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
//                        Intent intent = new Intent(getActivity(), function3_Activity().class);
//                        startActivity(intent);
                        Toast.makeText(getContext(), "to function3_Activity",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Intent intent4 = new Intent(getActivity(), FavorUnitsPie.class);
                        startActivity(intent4);
                        break;
                }
            }
        });
    }
}
