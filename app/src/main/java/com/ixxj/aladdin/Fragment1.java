package com.ixxj.aladdin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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
public class Fragment1 extends Fragment {

    private ListView mListView;
    private static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view1,container,false);
        mListView = (ListView) view.findViewById(R.id.listView_tab1);
        new NewsAsyncTask().execute(URL);
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

    class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {
        @Override
        protected List<NewsBean> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<NewsBean> newsBeans) {
            super.onPostExecute(newsBeans);
            NewsAdapter adapter = new NewsAdapter(getActivity(),newsBeans);
            mListView.setAdapter(adapter);
        }
    }
}
