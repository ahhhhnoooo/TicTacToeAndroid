package com.ahhhhnoooo.tictactoe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ahhhhnoooo.tictactoe.records.GameRecordsDBHelper;
import com.ahhhhnoooo.tictactoe.records.GameRecordsTable;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    private void recordGameResult(int timestamp, String winner, int duration) {
        GameRecordsDBHelper dbHelper = new GameRecordsDBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues(3);
        values.put(GameRecordsTable.COL_NAME_TIMESTAMP, timestamp);
        values.put(GameRecordsTable.COL_NAME_WINNER, winner);
        values.put(GameRecordsTable.COL_NAME_DURATION, duration);

        db.insert(GameRecordsTable.TABLE_NAME, null, values);
    }
}
