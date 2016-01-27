package com.ixxj.aladdin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class LoadingActivity extends Activity {

    /*    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_loading);

            ImageView loadingImagView = (ImageView) findViewById(R.id.loadingImageView);
            loadingImagView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(LoadingActivity.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                    LoadingActivity.this.finish();
                }
            });
        }
        */
    //页面任务：
    // 1、在线更新显示图片
    // 2、根据版本号，提示软件更新

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFormat(PixelFormat.RGBA_8888);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);
        new common().openImmerseStatasBarMode(this);

        setContentView(R.layout.activity_loading);

        //Display the current version number
        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo("com.ixxj.aladdin", 0);
            TextView versionNumber = (TextView) findViewById(R.id.versionNumber);
            versionNumber.setText("Version " + pi.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {
                /* Create an Intent that will start the Main WordPress Activity. */
                Intent mainIntent = new Intent(LoadingActivity.this, MainActivity.class);
                LoadingActivity.this.startActivity(mainIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                LoadingActivity.this.finish();
            }
        }, 2900); //2900 for release


    }
}
