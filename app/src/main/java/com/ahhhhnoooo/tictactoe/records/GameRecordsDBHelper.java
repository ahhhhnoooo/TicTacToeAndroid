package com.ahhhhnoooo.tictactoe.records;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 10/21/15.
 */
public class GameRecordsDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GameRecords.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE =
            "CREATE TABLE " + GameRecordsTable.TABLE_NAME + " (" +
                    GameRecordsTable._ID + " " + GameRecordsTable.COL_TYPE_ID + " , " +
                    GameRecordsTable.COL_NAME_TIMESTAMP + " " + GameRecordsTable.COL_TYPE_TIMESTAMP + " , " +
                    GameRecordsTable.COL_NAME_WINNER + " " + GameRecordsTable.COL_TYPE_WINNER + " , " +
                    GameRecordsTable.COL_NAME_DURATION + " " + GameRecordsTable.COL_TYPE_DURATION + ")";

    private static final String SQL_DELETE =
            "DROP TABLE IF EXISTS " + GameRecordsTable.TABLE_NAME;

    public GameRecordsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO next version
    }

    public void resetDatabase(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE);
        db.execSQL(SQL_CREATE);
    }
}