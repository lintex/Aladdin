package com.ixxj.aladdin;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private List<Fragment> fragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragList = new ArrayList<Fragment>();
        fragList.add(new Fragment1());
        fragList.add(new Fragment2());
        fragList.add(new Fragment3());
        fragList.add(new Fragment4());

        pager = (ViewPager) findViewById(R.id.viewPager);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragList);
        pager.setAdapter(adapter);
    }
}
