package com.ahhhhnoooo.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ahhhhnoooo.tictactoe.records.GameRecordsLoadTask;

public class RecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        new GameRecordsLoadTask(this, (ListView) findViewById(R.id.game_records_list_view)).execute();
    }
}
