package com.ahhhhnoooo.tictactoe.records;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GameRecordsSaveTask implements Runnable {

    private GameRecord _record;
    private Context _context;

    public GameRecordsSaveTask(Context context, GameRecord record) {
        _context = context;
        _record = record;
    }

    @Override
    public void run() {
        //Save game reset in database
        GameRecordsDBHelper dbHelper = new GameRecordsDBHelper(_context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues(3);
        values.put(GameRecordsTable.COL_NAME_TIMESTAMP, _record.timestamp);
        values.put(GameRecordsTable.COL_NAME_WINNER, _record.winner);
        values.put(GameRecordsTable.COL_NAME_DURATION, _record.duration);

        db.insert(GameRecordsTable.TABLE_NAME, null, values);
    }
}