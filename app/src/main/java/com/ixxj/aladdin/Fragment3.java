package com.ixxj.aladdin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ixxj.aladdin.activity.GuaguakaActivity;
import com.ixxj.aladdin.activity.LocationActivity;
import com.ixxj.aladdin.activity.LocationActivity2;
import com.ixxj.aladdin.activity.LoginActivity;
import com.ixxj.aladdin.activity.WebViewActivity;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by lintex on 2016/1/13.
 */
public class Fragment3 extends Fragment {
    private View view=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view3, container, false);
        SMSSDK.initSDK(getActivity(), "f3824f847d66", "1d05e3fa2b5a021b66350644965e462c");

        Button btn = (Button) view.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                registerPage.show(getActivity());
            }
        });
        Button btn_WebView = (Button) view.findViewById(R.id.btn_WebView);
        btn_WebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url","http://baidu.com");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });


        //启动登录界面
        Button btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        //启动GPS界面
        Button btn_GPS = (Button) view.findViewById(R.id.id_btn_GPS);
        btn_GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });


        //启动GPS界面2
        Button btn_GPS2 = (Button) view.findViewById(R.id.id_btn_GPS2);
        btn_GPS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationActivity2.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });


        //启动刮刮卡界面
        Button btn_guaguaka = (Button) view.findViewById(R.id.id_btn_guaguaka);
        btn_guaguaka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GuaguakaActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });

        return view;
    }


}
