package com.ixxj.aladdin;

import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lintex on 2016/1/13.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragList;
    private List<String>titleList;

    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment>fragList) {
        super(fm);
        this.fragList=fragList;
    }



    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public int getCount() {
        return fragList.size();
    }
}
