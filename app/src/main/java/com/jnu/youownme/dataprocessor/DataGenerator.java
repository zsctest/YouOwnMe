package com.jnu.youownme.dataprocessor;

import com.jnu.youownme.R;

public class DataGenerator {
    public static final int []mTabRes = new int[]{R.drawable.money_out,R.drawable.home_page,R.drawable.money_in};
    public static final int []mTabResPressed = new int[]{R.drawable.money_out_on,R.drawable.home_page_on,R.drawable.money_in_on};
    public static final String []mTabTitle = new String[]{"随礼","首页","收礼"};


    public static int[] getmTabRes() {
        return mTabRes;
    }

    public static int[] getmTabResPressed() {
        return mTabResPressed;
    }

    public static String[] getmTabTitle() {
        return mTabTitle;
    }
}
