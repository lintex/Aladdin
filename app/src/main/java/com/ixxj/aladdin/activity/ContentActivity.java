package com.ixxj.aladdin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ixxj.aladdin.BasicHttpClient;
import com.ixxj.aladdin.NewToast;
import com.ixxj.aladdin.NewsAdapter;
import com.ixxj.aladdin.NewsBean;
import com.ixxj.aladdin.R;
import com.ixxj.aladdin.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentActivity extends Activity implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private ImageButton btn_back;
    GestureDetector mGestureDetector;
    private int verticalMinDistance = 150;
    private int minVelocity = 0;

    private String mURL = "http://ixxj.sinaapp.com/json_content.php";
    private ListView mListView;
    private String str;
    private EditText editText;
    private Button btn_send;
    private SendTask mSendTask;
    private String id;//分类id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new common().openImmerseStatasBarMode(this);
        setContentView(R.layout.activity_content);


        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);
        LinearLayout viewSnsLayout = (LinearLayout) findViewById(R.id.id_content);
        viewSnsLayout.setOnTouchListener(this);
        viewSnsLayout.setLongClickable(true);

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView tv_title = (TextView) findViewById(R.id.id_item_title);
        Bundle bundle = this.getIntent().getExtras();
        tv_title.setText(bundle.getString("title"));
        textView2.setText(bundle.getString("content"));
        id = bundle.getString("id");
        mURL = mURL + "?id=" + id;
        mListView = (ListView) findViewById(R.id.content_listView);
        new NewsAsyncTask().execute(mURL);

        btn_back = (ImageButton) findViewById(R.id.id_item_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentActivity.this.finish();
            }
        });

        //提交数据到服数据库
        editText = (EditText) findViewById(R.id.editText);
        btn_send = (Button) findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = editText.getText().toString();
                if (str == null || str.length() <= 0) {
                    Toast.makeText(ContentActivity.this, "没有输入！", Toast.LENGTH_SHORT).show();
                } else {
                    mSendTask = new SendTask();
                    mSendTask.execute("http://ixxj.sinaapp.com/android_cc.php", str, id);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //当activity退出时，将发表数据线程中止
        if (mSendTask != null && mSendTask.getStatus() == AsyncTask.Status.RUNNING) {
            //cancel方法只是将对应的aysncTask标记为cancel状态，并不是取消线程
            mSendTask.cancel(true);
        }
    }

    class SendTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btn_send.setClickable(false);
        }

        @Override
        protected String doInBackground(String... params) {
            if (isCancelled()) {
                return "{\"code\":202,\"message\":\"线程取消\",\"data\":null}";
            }
            String Url = params[0];
            String param = "content=" + params[1] + "&id=" + params[2];
            Log.i("yxx", param);
            BasicHttpClient client = new BasicHttpClient();
            String response = client.httpPost(Url, param);
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("yxx", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                Integer code = jsonObject.getInt("code");
                String message = jsonObject.getString("message");
                if (code == 200) {
                    editText.setText("");
                } else {
                    editText.setText(str);
                }
                NewToast.showMessage(ContentActivity.this, message);
                btn_send.setClickable(true);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        // scroll.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {

            // 切换Activity
            // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
            // startActivity(intent);
            //Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();
        } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            ContentActivity.this.finish();
            // 切换Activity
            // Intent intent = new Intent(ViewSnsActivity.this, UpdateStatusActivity.class);
            // startActivity(intent);
            //Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }


    private List<NewsBean> getJsonData(String url) {
        List<NewsBean> newsBeanList = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            //Log.i("yxx", jsonString);
            JSONObject jsonObject;
            NewsBean newsBean;
            try {
                jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    newsBean = new NewsBean();
                    newsBean.newsIconUrl = "http://img.mukewang.com/55249cf30001ae8a06000338-300-170.jpg";
                    newsBean.newsTitle = jsonObject.getString("author");
                    newsBean.newsContent = jsonObject.getString("content");
                    newsBeanList.add(newsBean);
                    //Log.i("yxx", jsonObject.getString("author"));
                }
            } catch (JSONException e) {
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
            NewsAdapter adapter = new NewsAdapter(ContentActivity.this, newsBeans, mListView);
            mListView.setAdapter(adapter);
        }
    }
}