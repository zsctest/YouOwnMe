package com.jnu.youownme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditShouLiActivity extends AppCompatActivity {
    private String shoulinameplus;
    private String shouliaboutplus;
    private String shoulimoneyplus;
    private String shoulidateplus;
    private int position;
    private EditText EditShouliName;
    private EditText EditShouliAbout;
    private EditText EditShouliMoney;
    private EditText EditShouliDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shou_li);

        position = getIntent().getIntExtra("position", 0);
        shoulinameplus = getIntent().getStringExtra("shouliname");
        shouliaboutplus = getIntent().getStringExtra("shouliabout");
        shoulimoneyplus = getIntent().getStringExtra("shoulimoney");
        shoulidateplus = getIntent().getStringExtra("shoulidate");
        Log.i("result in Edit", shoulinameplus + ' ' + shouliaboutplus + ' ' + shoulimoneyplus + ' '
                + shoulidateplus);

        EditShouliName = (EditText) findViewById(R.id.edit_text_shouli_from);
        EditShouliAbout = (EditText) findViewById(R.id.edit_text_shouli_about);
        EditShouliMoney = (EditText) findViewById(R.id.edit_text_shouli_money);
        EditShouliDate = (EditText) findViewById(R.id.edit_text_shouli_date);
        if (null != shoulinameplus) {
            EditShouliName.setText(shoulinameplus);
            EditShouliAbout.setText(shouliaboutplus);
            EditShouliMoney.setText(shoulimoneyplus);
            EditShouliDate.setText(shoulidateplus);
        }
        Log.i("result in Edit", "in EditShouLiActivity");

        Button buttonOk = (Button) findViewById(R.id.button_ok);
        Button buttonNO = (Button) findViewById(R.id.button_no);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditShouLiActivity.this, MainActivity.class);   //intent的传输方向
                Log.i("result in Edit", EditShouliName.getText().toString());
                String newShouLiName = EditShouliName.getText().toString();
                Log.i("result in Edit", newShouLiName);
                intent.putExtra("newshouliname", newShouLiName);
                String newShouLiAbout = EditShouliAbout.getText().toString();
                Log.i("result in Edit", newShouLiAbout);
                intent.putExtra("newshouliabout", newShouLiAbout);
                String newShouLiMoney = EditShouliMoney.getText().toString();
                Log.i("result in Edit", newShouLiMoney);
                intent.putExtra("newshoulimoney", newShouLiMoney);
                String newShouLiDate = EditShouliDate.getText().toString();
                Log.i("result in Edit", newShouLiDate);
                intent.putExtra("newshoulidate", newShouLiDate);
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