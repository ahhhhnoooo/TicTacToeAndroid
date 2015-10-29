package com.ahhhhnoooo.tictactoe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahhhhnoooo.tictactoe.records.GameRecord;
import com.ahhhhnoooo.tictactoe.records.GameRecordsDBHelper;
import com.ahhhhnoooo.tictactoe.records.GameRecordsSaveTask;
import com.ahhhhnoooo.tictactoe.records.GameRecordsTable;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class GameActivity extends AppCompatActivity {
    private static final int TAG_BLANK = 0;
    private static final int TAG_X = 1;
    private static final int TAG_O = -1;
    private ImageButton[] board;
    private ImageView turnImage;
    private TextView turnLabel;
    private Chronometer gameTimer;
    private boolean gameStarted;
    private boolean oTurn = false; //X starts

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        turnImage = (ImageView) findViewById(R.id.image_turn);
        turnLabel = (TextView) findViewById(R.id.label_turn);
        gameTimer = (Chronometer) findViewById(R.id.chronometer);

        board = new ImageButton[9];
        board[0] = (ImageButton) findViewById(R.id.imageButton);
        board[1] = (ImageButton) findViewById(R.id.imageButton2);
        board[2] = (ImageButton) findViewById(R.id.imageButton3);
        board[3] = (ImageButton) findViewById(R.id.imageButton4);
        board[4] = (ImageButton) findViewById(R.id.imageButton5);
        board[5] = (ImageButton) findViewById(R.id.imageButton6);
        board[6] = (ImageButton) findViewById(R.id.imageButton7);
        board[7] = (ImageButton) findViewById(R.id.imageButton8);
        board[8] = (ImageButton) findViewById(R.id.imageButton9);

        reset();
    }

    //Handle Reset button
    public void onClickGameReset(View view) {
        //Save results
        if (gameStarted) {
            //TODO save results on reset if game is not ended
        }
        reset();
    }

    //Handle box on board click
    public void onClickGameBoard(View view) {
        //Ignore if already taken
        if (0 != (int) view.getTag()) return;
        //Start timer if necessary
        if (!gameStarted) {
            gameTimer.setBase(SystemClock.elapsedRealtime());
            gameTimer.start();
            gameStarted = true;
        }

        int id = R.drawable.x;
        int next = R.drawable.o;
        int tag = TAG_X;
        if (oTurn) {
            id = R.drawable.o;
            next = R.drawable.x;
            tag = TAG_O;
        }

        //Update clicked box
        ImageButton button = (ImageButton) view;
        button.setImageResource(id);
        button.setTag(tag);

        if (checkWinnerBoard()) return;

        //Change turns
        oTurn = !oTurn;
        turnImage.setImageResource(next);
    }

    //Check board if there is a winner
    //@return true if winner found
    private boolean checkWinnerBoard() {
        int tag = TAG_X;
        if (oTurn) tag = TAG_O;
        //Check 3 rows, 3 cols, 2 diag
        return (
                checkWinnerRow(tag, board[0], board[1], board[2]) ||
                        checkWinnerRow(tag, board[3], board[4], board[5]) ||
                        checkWinnerRow(tag, board[6], board[7], board[8]) ||
                        checkWinnerRow(tag, board[0], board[3], board[6]) ||
                        checkWinnerRow(tag, board[1], board[4], board[7]) ||
                        checkWinnerRow(tag, board[2], board[5], board[8]) ||
                        checkWinnerRow(tag, board[0], board[4], board[8]) ||
                        checkWinnerRow(tag, board[2], board[4], board[6])
        );
        //TODO If board is full with no winner
    }

    //Check row if this is a winning row
    //@return true if winner found
    private boolean checkWinnerRow(int tag, ImageView a, ImageView b, ImageView c) {
        if (tag == (int) a.getTag() && tag == (int) b.getTag() && tag == (int) c.getTag()) {
            //Stop timer
            gameStarted = false;
            gameTimer.stop();

            //Disable board
            for (ImageButton imageButton : board) {
                imageButton.setEnabled(false);
            }

            //declare winner
            turnLabel.setText(R.string.game_label_winner);

            //highlight winning boxes
            a.setBackgroundColor(Color.YELLOW);
            b.setBackgroundColor(Color.YELLOW);
            c.setBackgroundColor(Color.YELLOW);

            //Save result
            long now = new Date().getTime();
            String winnerName = "x";
            if (oTurn) winnerName = "o";
            long duration = SystemClock.elapsedRealtime() - gameTimer.getBase();
            GameRecordsSaveTask saveTask = new GameRecordsSaveTask(this, new GameRecord(now, winnerName, duration));
            new Thread(saveTask).start();

            return true;
        }
        return false;
    }

    //Reset entire board
    private void reset() {
        //Reset variables
        oTurn = false;
        gameStarted = false;
        gameTimer.stop();
        //TODO clear game timer display
        turnLabel.setText(R.string.game_label_turn);

        //Reset the board
        for (ImageButton imageButton : board) {
            imageButton.setImageResource(R.drawable.blank);
            imageButton.setTag(TAG_BLANK);
            imageButton.setEnabled(true);
            imageButton.setBackgroundColor(0); //Clear background color
        }
    }
}
