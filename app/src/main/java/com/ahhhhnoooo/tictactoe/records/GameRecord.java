package com.ahhhhnoooo.tictactoe.records;

public class GameRecord {
    //Time game was completed
    long timestamp;
    //Winner of game, x, o, or tie
    String winner;
    //Duration of game, in milliseconds
    long duration;

    public GameRecord(long timestamp, String winner, long duration)
    {
        this.timestamp = timestamp;
        this.winner = winner;
        this.duration = duration;
    }
}
