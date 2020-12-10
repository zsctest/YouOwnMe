package com.jnu.youownme;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class HomePageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragment = new ArrayList<>();
    private final FragmentManager homePageAdapterManager;
    private boolean mUpdateFlag;
    private Fragment mCurFragment;
    private String[] mTitles;

    public HomePageAdapter(FragmentManager fm) {
        super(fm);
        this.homePageAdapterManager = fm;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
