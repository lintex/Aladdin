package com.ixxj.aladdin;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends Activity {
    private EditText et_phone, et_password;
    private Button btn_login, btn_forgotPassword, btn_register;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private String phone;
    private String password;
    private LoginTask mLoginTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        editor = pref.edit();
        phone = pref.getString("phone", "");
        if (phone != null) {
            et_phone.setText(phone);
        }
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.id_et_phone);
        et_password = (EditText) findViewById(R.id.id_et_password);
        btn_login = (Button) findViewById(R.id.id_btn_login);
        btn_register = (Button) findViewById(R.id.id_btn_register);
        btn_forgotPassword = (Button) findViewById(R.id.id_btn_forgotPassword);
        //初始化短信验证界面
        SMSSDK.initSDK(this, "f3824f847d66", "1d05e3fa2b5a021b66350644965e462c");
    }

    public void login(View v) {
        phone = et_phone.getText().toString().trim();
        password = et_password.getText().toString().trim();
        //异步验证用户名密码
        mLoginTask = new LoginTask();
        mLoginTask.execute("http://1.ixxj.applinzi.com/android_login.php", phone, password);
    }

    public void register(View v) {
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");
                    // 提交用户信息
                    //registerUser(country, phone);
                }
            }
        });
        registerPage.show(this);
    }

    public void forgotPassword(View v) {


    }


    class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String Url = params[0];
            String param = "username=" + params[1] + "&password=" +params[2];
            Log.i("yxx",param);
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
                if(code==200){
                    jsonObject = jsonObject.getJSONObject("data");
                    editor.putString("username", jsonObject.getString("username"));
                    editor.putString("phone", jsonObject.getString("phone"));
                    editor.putString("nickname", jsonObject.getString("nickname"));
                    editor.commit();
                    Log.i("yxx", jsonObject.getString("nickname"));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    LoginActivity.this.finish();
                }
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}
