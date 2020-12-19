package com.jnu.youownme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        try {
//            Thread.sleep(100); //线程休眠1s，使出现白屏时的效果更加明显
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        //展示2s后前往主页
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 200);
    }
}