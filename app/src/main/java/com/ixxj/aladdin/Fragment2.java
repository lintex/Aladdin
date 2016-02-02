package com.ixxj.aladdin;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lintex on 2016/1/13.
 */
public class Fragment2 extends Fragment implements ListView.OnItemClickListener, ListView.OnScrollListener {
    private ListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> dataList;
    private View view=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.view2, container, false);
        listView = (ListView) view.findViewById(R.id.listView1);
        dataList = new ArrayList<Map<String, Object>>();
        simp_adapter = new SimpleAdapter(getActivity(), getData(), R.layout.item, new String[]{"pic", "text"}, new int[]{R.id.item_pic, R.id.item_text});
        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
        return view;
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("text", "阿拉丁" + i);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = listView.getItemAtPosition(position) + "";
        Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
/*        switch (scrollState){
            case SCROLL_STATE_TOUCH_SCROLL:
                break;
            case SCROLL_STATE_FLING:
                break;
            case SCROLL_STATE_IDLE:
                break;
        }
 */
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING)
        {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("pic",R.mipmap.ic_launcher);
            map.put("text","阿拉丁丁");
            dataList.add(map);
            simp_adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
