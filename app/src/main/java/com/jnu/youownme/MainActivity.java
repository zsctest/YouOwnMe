package com.jnu.youownme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.jnu.youownme.dataprocessor.DataGenerator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;

    public class MyPageAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> datas;
        ArrayList<String> titles;

        public MyPageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void setDatas(ArrayList<Fragment> datas){ this.datas = datas;}

        public void setTitles(ArrayList<String> titles) { this.titles = titles; }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //若不为空，返回某一fragment
            return datas == null?null:datas.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //若不为空，返回某一title
            return titles == null ? null : titles.get(position);
        }

        @Override
        public int getCount() {
            //返回fragment数组长度，以便索引
            return datas == null?0:datas.size();
        }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity that = this;
        initView();
    }

    private void initView() {
        ArrayList<Fragment> datas = new ArrayList<Fragment>();
        datas.add(new HomeFragment());
        datas.add(new SuiLiFragment());
        datas.add(new ShouLiFragment());

        ArrayList<String> titles = new ArrayList<String>();
        titles.add("首页");
        titles.add("随礼");
        titles.add("收礼");

        MyPageAdapter myPageAdapter = new MyPageAdapter(this.getSupportFragmentManager());
        myPageAdapter.setDatas(datas);
        myPageAdapter.setTitles(titles);

        //与布局绑定，展示


        ViewPager viewPager = findViewById(R.id.view_pager_content);
        viewPager.setAdapter(myPageAdapter);
//        mTabLayout.setupWithViewPager(viewPager);

        mTabLayout = findViewById(R.id.table_layout_header);
        mTabLayout.getTabAt(0).setIcon(R.drawable.money_out);
        mTabLayout.getTabAt(1).setIcon(R.drawable.home_page_on);
        mTabLayout.getTabAt(2).setIcon(R.drawable.money_in);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                for(int i = 0;i<mTabLayout.getTabCount();i++){
                    if(i == tab.getPosition()){
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabResPressed[i]));
                    }else{
                        mTabLayout.getTabAt(i).setIcon(getResources().getDrawable(DataGenerator.mTabRes[i]));
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.setupWithViewPager(viewPager);
    }


}