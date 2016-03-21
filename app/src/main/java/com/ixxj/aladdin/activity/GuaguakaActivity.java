package com.ixxj.aladdin.activity;

import android.app.Activity;
import android.os.Bundle;

import com.ixxj.aladdin.R;
import com.ixxj.aladdin.utils.GuaGuaKa;

public class GuaguakaActivity extends Activity {
    GuaGuaKa mGuaGuaKa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guaguaka);

        mGuaGuaKa = (GuaGuaKa) findViewById(R.id.id_guaguaka);
        mGuaGuaKa.setOnGuaGuaKaCompleteListener(new GuaGuaKa.OnGuaGuaKaCompleteListener() {
                    @Override
                    public void complete() {
//						Toast.makeText(getApplicationContext(), "用户已经刮得差不多了",
//								Toast.LENGTH_SHORT).show();
                        //do something
                    }
                });

        mGuaGuaKa.setText("Android新技能Get");

    }
}
