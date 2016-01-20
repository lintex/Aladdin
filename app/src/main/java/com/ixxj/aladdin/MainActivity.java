package com.ixxj.aladdin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private List<Fragment> mFragments;

    private LinearLayout mTab1;
    private LinearLayout mTab2;
    private LinearLayout mTab3;
    private LinearLayout mTab4;

    private ImageButton mTab1_img;
    private ImageButton mTab2_img;
    private ImageButton mTab3_img;
    private ImageButton mTab4_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initEvents();
        setSelect(0);
    }

    private void initEvents() {
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
        mTab4.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTab1 = (LinearLayout) findViewById(R.id.tab1);
        mTab2 = (LinearLayout) findViewById(R.id.tab2);
        mTab3 = (LinearLayout) findViewById(R.id.tab3);
        mTab4 = (LinearLayout) findViewById(R.id.tab4);

        mTab1_img = (ImageButton) findViewById(R.id.tab1_img);
        mTab2_img = (ImageButton) findViewById(R.id.tab2_img);
        mTab3_img = (ImageButton) findViewById(R.id.tab3_img);
        mTab4_img = (ImageButton) findViewById(R.id.tab4_img);

        mFragments = new ArrayList<>();
        Fragment mTab01 = new Fragment1();
        Fragment mTab02 = new Fragment2();
        Fragment mTab03 = new Fragment3();
        Fragment mTab04 = new Fragment4();
        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);
        mFragments.add(mTab04);


        FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {

                         /*   ViewGroup parent = (ViewGroup) container.getParent();
                            if (parent != null) {
                                parent.removeAllViews();
                            }
                            container.addView(container);
                            */
                            return super.instantiateItem(container, position);

                        }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }
    private void setTab(int i)
    {
        resetImgs();
        switch (i)
        {
            case 0:
                mTab1_img.setImageResource(R.mipmap.img2);
                break;
            case 1:
                mTab2_img.setImageResource(R.mipmap.img4);
                break;
            case 2:
                mTab3_img.setImageResource(R.mipmap.img6);
                break;
            case 3:
                mTab4_img.setImageResource(R.mipmap.img8);
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tab1:
                setSelect(0);
                break;
            case R.id.tab2:
                setSelect(1);
                break;
            case R.id.tab3:
                setSelect(2);
                break;
            case R.id.tab4:
                setSelect(3);
                break;
            default:
                break;
        }
    }

    private void resetImgs() {
        //图片切换到默认颜色
        mTab1_img.setImageResource(R.mipmap.img1);
        mTab2_img.setImageResource(R.mipmap.img3);
        mTab3_img.setImageResource(R.mipmap.img5);
        mTab4_img.setImageResource(R.mipmap.img7);
    }

}
