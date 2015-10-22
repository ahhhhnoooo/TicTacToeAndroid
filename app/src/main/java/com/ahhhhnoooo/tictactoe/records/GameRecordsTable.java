package com.ahhhhnoooo.tictactoe.records;

/**
 * Created by user on 10/21/15.
 */
public class GameRecordsTable {
    public static final String TABLE_NAME = "game_records";
    public static final String COL_NAME_TIMESTAMP = "timestamp";
    public static final String COL_TYPE_TIMESTAMP = "INTEGER PRIMARY KEY";
    public static final String COL_NAME_WINNER = "winner";
    public static final String COL_TYPE_WINNER = "TEXT";
    public static final String COL_NAME_DURATION = "duration";
    public static final String COL_TYPE_DURATION = "INTEGER";
}
