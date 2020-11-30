package com.jnu.youownme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditSuiLiActivity extends AppCompatActivity {
    private String suilinameplus;
    private String suiliaboutplus;
    private String suilimoneyplus;
    private String suilidateplus;
    private int position;
    private EditText EditSuiliName;
    private EditText EditSuiliAbout;
    private EditText EditSuiliMoney;
    private EditText EditSuiliDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sui_li);

        position = getIntent().getIntExtra("position", 0);
        suilinameplus = getIntent().getStringExtra("suiliname");
        suiliaboutplus = getIntent().getStringExtra("suiliabout");
        suilimoneyplus = getIntent().getStringExtra("suilimoney");
        suilidateplus = getIntent().getStringExtra("suilidate");
        Log.i("result in Edit", suilinameplus + ' ' + suiliaboutplus + ' ' + suilimoneyplus + ' '
                + suilidateplus);

        EditSuiliName = (EditText) findViewById(R.id.edit_text_suili_to);
        EditSuiliAbout = (EditText) findViewById(R.id.edit_text_suili_about);
        EditSuiliMoney = (EditText) findViewById(R.id.edit_text_suili_money);
        EditSuiliDate = (EditText) findViewById(R.id.edit_text_suili_date);
        if (null != suilinameplus) {
            EditSuiliName.setText(suilinameplus);
            EditSuiliAbout.setText(suiliaboutplus);
            EditSuiliMoney.setText(suilimoneyplus);
            EditSuiliDate.setText(suilidateplus);
        }
        Log.i("result in Edit", "in EditSuiLiActivity");

        Button buttonOk = (Button) findViewById(R.id.button_ok);
        Button buttonNO = (Button) findViewById(R.id.button_no);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditSuiLiActivity.this, MainActivity.class);   //intent的传输方向
                Log.i("result in Edit", EditSuiliName.getText().toString());
                String newSuiLiName = EditSuiliName.getText().toString();
                Log.i("result in Edit", newSuiLiName);
                intent.putExtra("newsuiliname", newSuiLiName);
                String newSuiLiAbout = EditSuiliAbout.getText().toString();
                Log.i("result in Edit", newSuiLiAbout);
                intent.putExtra("newsuiliabout", newSuiLiAbout);
                String newSuiLiMoney = EditSuiliMoney.getText().toString();
                Log.i("result in Edit", newSuiLiMoney);
                intent.putExtra("newsuilimoney", newSuiLiMoney);
                String newSuiLiDate = EditSuiliDate.getText().toString();
                Log.i("result in Edit", newSuiLiDate);
                intent.putExtra("newsuilidate", newSuiLiDate);
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