package com.ahhhhnoooo.tictactoe.records;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ahhhhnoooo.tictactoe.R;

/**
 * Created by ahhhh on 10/28/2015.
 */
public class GameRecordsLoadTask extends AsyncTask<Void, Void, Cursor> {

    ListView _listView;
    Context _context;
    String[] from = {GameRecordsTable.COL_NAME_TIMESTAMP, GameRecordsTable.COL_NAME_WINNER, GameRecordsTable.COL_NAME_DURATION};
    int[] to = {R.id.records_label_timestamp, R.id.records_label_winner, R.id.records_label_duration};

    public GameRecordsLoadTask(Context context, ListView listView) {
        _listView = listView;
        _context = context;
    }

    @Override
    protected Cursor doInBackground(Void... params) {
        GameRecordsDBHelper dbHelper = new GameRecordsDBHelper(_context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + GameRecordsTable.TABLE_NAME, null);
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        _listView.setAdapter(new SimpleCursorAdapter(
                _context,
                R.layout.game_record_layout,
                cursor,
                from,
                to,
                0));
    }
}
