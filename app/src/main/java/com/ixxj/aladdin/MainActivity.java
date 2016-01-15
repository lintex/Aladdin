package com.ixxj.aladdin;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager pager;

    private LinearLayout mTab1;
    private LinearLayout mTab2;
    private LinearLayout mTab3;
    private LinearLayout mTab4;

    private ImageButton mTab1_img;
    private ImageButton mTab2_img;
    private ImageButton mTab3_img;
    private ImageButton mTab4_img;

    private List<Fragment> fragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initEvents();

        fragList = new ArrayList<Fragment>();
        fragList.add(new Fragment1());
        fragList.add(new Fragment2());
        fragList.add(new Fragment3());
        fragList.add(new Fragment4());

        pager = (ViewPager) findViewById(R.id.viewPager);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragList);
        pager.setAdapter(adapter);
    }

    private void initEvents() {
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);

        pager.setOnPageChangeListener();
    }

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.tab1:
                mTab1_img.setImageResource(R.mipmap.ic_launcher);
                break;
            case R.id.tab2:
                mTab2_img.setImageResource(R.mipmap.ic_launcher);
                break;
            case R.id.tab3:
                mTab3_img.setImageResource(R.mipmap.ic_launcher);
                break;
            case R.id.tab4:
                mTab4_img.setImageResource(R.mipmap.ic_launcher);
                break;
        }
    }

    private void resetImg() {
        //图片切换到默认颜色
        mTab1_img.setImageResource(R.mipmap.ic_launcher);
        mTab2_img.setImageResource(R.mipmap.ic_launcher);
        mTab3_img.setImageResource(R.mipmap.ic_launcher);
        mTab4_img.setImageResource(R.mipmap.ic_launcher);
    }

    private void initView() {
        mTab1 = (LinearLayout) findViewById(R.id.tab1);
        mTab2 = (LinearLayout) findViewById(R.id.tab2);
        mTab3 = (LinearLayout) findViewById(R.id.tab3);
        mTab4 = (LinearLayout) findViewById(R.id.tab4);

        mTab1_img = (ImageButton) findViewById(R.id.tab1_img);
        mTab2_img = (ImageButton) findViewById(R.id.tab2_img);
        mTab3_img = (ImageButton) findViewById(R.id.tab3_img);
        mTab4_img = (ImageButton) findViewById(R.id.tab4_img);
    }
}
