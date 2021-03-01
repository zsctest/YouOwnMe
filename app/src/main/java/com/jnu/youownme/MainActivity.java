package com.jnu.youownme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.jnu.youownme.dataprocessor.DataGenerator;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private MainActivity that = this;

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

        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            Intent intentMain;
            Calendar cal = Calendar.getInstance();
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                intentMain = new Intent(MainActivity.this,AddRecordActivity.class);
                String datestr = cal.get(Calendar.YEAR)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.DATE);
                intentMain.putExtra("date",datestr);
                startActivity(intentMain);
                //TODO:设置监听，询问选择添加随礼还是收礼
            }
        });
    }

    private void initView() {
        ArrayList<Fragment> datas = new ArrayList<Fragment>();
//        datas.add(new HomePageFragment());
        datas.add(new HomeFragment());
        Log.i("msp","homefragment ok");
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
            @SuppressLint("UseCompatLoadingForDrawables")
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