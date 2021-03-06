package com.ixxj.aladdin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ixxj.aladdin.activity.ContentActivity2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lintex on 2016/1/13.
 */
public class Fragment1 extends Fragment implements ListView.OnItemClickListener{

    private ListView mListView;
    //private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private static String URL = "http://ixxj.sinaapp.com/json.php";
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view1,container,false);
        mListView = (ListView) view.findViewById(R.id.listView_tab1);


        //下拉刷新ListView
        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(view.getContext(), "正在刷新", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(view.getContext(), "刷新完成", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000);
            }
        });

        new NewsAsyncTask().execute(URL);
        mListView.setOnItemClickListener(this);
        return view;
    }

    private List<NewsBean> getJsonData(String url) {
        List<NewsBean> newsBeanList = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            //Log.i("yxx",jsonString);
            JSONObject jsonObject;
            NewsBean newsBean;
            try{
                jsonObject =new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++){
                    jsonObject = jsonArray.getJSONObject(i);
                    newsBean = new NewsBean();
                    newsBean.newsId=jsonObject.getString("id");
                    newsBean.newsIconUrl=jsonObject.getString("picSmall");
                    newsBean.newsTitle=jsonObject.getString("name");
                    newsBean.newsContent=jsonObject.getString("description");
                    newsBeanList.add(newsBean);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsBeanList;
    }

    private String readStream(InputStream is) {
        InputStreamReader isr;
        String result = "";
        try {
            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent= new Intent();
        intent.setClass(getActivity(), ContentActivity2.class);


        NewsBean mItemNewsBean = (NewsBean) mListView.getItemAtPosition(position);
        Bundle mItemBundle = new Bundle();
        mItemBundle.putString("title", mItemNewsBean.newsTitle);
        mItemBundle.putString("content", mItemNewsBean.newsContent);
        mItemBundle.putString("id", mItemNewsBean.newsId);
        intent.putExtras(mItemBundle);

        //getActivity().startActivity(intent);

        startActivityForResult(intent, 0);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        //Toast.makeText(view.getContext(), "点击了"+mListView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
    }


    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {
        @Override
        protected List<NewsBean> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBeans) {
            super.onPostExecute(newsBeans);
            NewsAdapter adapter = new NewsAdapter(getActivity(),newsBeans,mListView);
            mListView.setAdapter(adapter);
        }
    }
}
