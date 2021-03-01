package com.jnu.youownme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private String nameplus;
    private String aboutplus;
    private String moneyplus;
    private String dateplus;
    private int position;
    private EditText EditName;
    private EditText EditAbout;
    private EditText EditMoney;
    private EditText EditDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
//TODO:AddActivity不能使新增数据插入ListView
//TODO:想法，在AddActivity设置一个参数，根据传来的参数选择支线操作

    position = getIntent().getIntExtra("position", 0);
    nameplus = getIntent().getStringExtra("addname");
    aboutplus = getIntent().getStringExtra("addabout");
    moneyplus = getIntent().getStringExtra("addmoney");
    dateplus = getIntent().getStringExtra("adddate");
        Log.i("result in Edit", nameplus + ' ' + aboutplus + ' ' + moneyplus + ' '
            + dateplus);

    EditName = (EditText) findViewById(R.id.edit_text_add_name);
    EditAbout = (EditText) findViewById(R.id.edit_text_add_about);
    EditMoney = (EditText) findViewById(R.id.edit_text_add_money);
    EditDate = (EditText) findViewById(R.id.edit_text_add_date);
        if (null != nameplus) {
        EditName.setText(nameplus);
        EditAbout.setText(aboutplus);
        EditMoney.setText(moneyplus);
        EditDate.setText(dateplus);
    }
        Log.i("result in Edit", "in EditShouLiActivity");

    Button buttonOk = (Button) findViewById(R.id.button_ok);
    Button buttonNO = (Button) findViewById(R.id.button_no);

        buttonOk.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddActivity.this, MainActivity.class);   //intent的传输方向
            Log.i("result in Edit", EditName.getText().toString());
            String newName = EditName.getText().toString();
            Log.i("result in Edit", newName);
            intent.putExtra("newname", newName);
            String newAbout = EditAbout.getText().toString();
            Log.i("result in Edit", newAbout);
            intent.putExtra("newabout", newAbout);
            String newMoney = EditMoney.getText().toString();
            Log.i("result in Edit", newMoney);
            intent.putExtra("newmoney", newMoney);
            String newDate = EditDate.getText().toString();
            Log.i("result in Edit", newDate);
            intent.putExtra("newdate", newDate);
            intent.putExtra("position", position);

            setResult(RESULT_OK, intent);

            finish();

        }
    });

        buttonNO.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            finish();
        }
    });
}
}