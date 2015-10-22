package com.ahhhhnoooo.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtonRecords(View view)
    {
        Intent intent = new Intent(this,RecordsActivity.class);
        startActivity(intent);
    }

    public void onClickButtonGame(View view)
    {
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }
}
