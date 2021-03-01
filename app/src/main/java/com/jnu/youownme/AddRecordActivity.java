package com.jnu.youownme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jnu.youownme.R;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private AddRecordActivity that =this;
    String datestr = "";
    public static final int REQUEST_CODE_ADD_NEW = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button addShouli = (Button) findViewById(R.id.button_add_shouli);
        Button addSuili = (Button)findViewById(R.id.button_add_suili);
        TextView timeText = (TextView) findViewById(R.id.textView_time);

        datestr = getIntent().getStringExtra("date");
        timeText.setText("今天是"+datestr);

        addShouli.setOnClickListener(this);
        addSuili.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intentAdd;
        switch(v.getId()){
            case R.id.button_add_shouli:
                intentAdd = new Intent(that,EditShouLiActivity.class);
                intentAdd.putExtra("position", 0);//获取当前的索引值
                intentAdd.putExtra("shouliname","姓名");
                intentAdd.putExtra("shouliabout","关于");
                intentAdd.putExtra("shoulimoney","金额");
                intentAdd.putExtra("shoulidate",datestr);
                startActivityForResult(intentAdd, REQUEST_CODE_ADD_NEW);
                Toast.makeText(this.getApplicationContext(),"点击了添加收礼",Toast.LENGTH_LONG).show();
                break;
            case R.id.button_add_suili:
                intentAdd = new Intent(that,EditSuiLiActivity.class);
                intentAdd.putExtra("position", 0);//获取当前的索引值
                intentAdd.putExtra("suiliname","姓名");
                intentAdd.putExtra("suiliabout","关于");
                intentAdd.putExtra("suilimoney","金额");
                intentAdd.putExtra("suilidate",datestr);
                startActivityForResult(intentAdd, REQUEST_CODE_ADD_NEW);

                Toast.makeText(this.getApplicationContext(),"点击了添加随礼",Toast.LENGTH_LONG).show();
                break;
        }

    }
}