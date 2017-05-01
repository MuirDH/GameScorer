package com.example.android.gamescorer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.gamescorer.data.GameDbHelper;
import com.example.android.gamescorer.data.GamesContract;


public class ZombieDiceActivity extends AppCompatActivity {

    private static final String STATE_P1NAME = "p1NameString";
    private static final String STATE_P2NAME = "p2NameString";
    private static final String STATE_P1BRAINS = "player1Brains";
    private static final String STATE_P2BRAINS = "player2Brains";
    private static final String STATE_P1SCORE = "player1Score";
    private static final String STATE_P2SCORE = "player2Score";

    // player 1
    String p1NameString;
    int player1Brains;
    int player1Score;

    // player 2
    String p2NameString;
    int player2Brains;
    int player2Score;

    /**
     * Database helper that will provide us access to the database
     */
    private GameDbHelper mDbHelper;

    private EditText mP1NameEditText;
    private EditText mP2NameEditText;

    private TextView mP1Brains;
    private TextView mP2Brains;

    private TextView mP1TotalScore;
    private TextView mP2TotalScore;


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString(STATE_P1NAME, p1NameString);
        savedInstanceState.putString(STATE_P2NAME, p2NameString);

        savedInstanceState.putInt(STATE_P1BRAINS, player1Brains);
        savedInstanceState.putInt(STATE_P2BRAINS, player2Brains);

        savedInstanceState.putInt(STATE_P1SCORE, player1Score);
        savedInstanceState.putInt(STATE_P2SCORE, player2Score);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        displayForPlayer1(player1Brains, player1Score);
        displayForPlayer2(player2Brains, player2Score);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zombie_dice);
        mDbHelper = new GameDbHelper(this);

        // Find all relevant views that we will need to read user input from
        /*
      EditText fields to enter the player's names
     */
        mP1NameEditText = (EditText) findViewById(R.id.player1);
        mP2NameEditText = (EditText) findViewById(R.id.player2);
        /*
      white honour points fields
     */
        mP1Brains = (TextView) findViewById(R.id.p1_r1);
        mP2Brains = (TextView) findViewById(R.id.p2_r1);
        /*
      total score fields
     */
        mP1TotalScore = (TextView) findViewById(R.id.player_1_score);
        mP2TotalScore = (TextView) findViewById(R.id.player_2_score);

        if (savedInstanceState == null) {
            // Read from input fields for players 1, 2
            // Use trim to eliminate leading or trailing white space
            p1NameString = mP1NameEditText.getText().toString().trim();
            p2NameString = mP2NameEditText.getText().toString().trim();

            loadPlayerData();
        } else {
            p1NameString = savedInstanceState.getString(STATE_P1NAME);

            p2NameString = savedInstanceState.getString(STATE_P2NAME);

            player1Brains = savedInstanceState.getInt(STATE_P1BRAINS);
            player2Brains = savedInstanceState.getInt(STATE_P2BRAINS);

            player1Score = savedInstanceState.getInt(STATE_P1SCORE);
            player2Score = savedInstanceState.getInt(STATE_P2SCORE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //Get player data and save into database
    private void insertZombieDicePlayerData() {

        // Create database helper
        GameDbHelper mDbHelper = new GameDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Remove the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + GamesContract.PlayerEntry.TABLE_ZOMBIEDICE);
        mDbHelper.CreateTable(GameDbHelper.GameType.ZombieDice, db);

        // Create a ContentValues object where column names are the keys,
        // and player attributes from the editor are the values.
        ContentValues valuesA = new ContentValues();

        p1NameString = mP1NameEditText.getText().toString();

        //player 1
        valuesA.put(GamesContract.PlayerEntry.COLUMN_NAME, p1NameString);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_BRAINS, player1Brains);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_TOTAL, player1Score);

        ContentValues valuesB = new ContentValues();

        p2NameString = mP2NameEditText.getText().toString();

        //player 2
        valuesB.put(GamesContract.PlayerEntry.COLUMN_NAME, p2NameString);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_BRAINS, player2Brains);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_TOTAL, player2Score);

        addRowToZombieDiceTable(valuesA, db);
        addRowToZombieDiceTable(valuesB, db);

    }

    // Show a toast message depending on whether or not the insertion was successful
    private void addRowToZombieDiceTable(ContentValues values, SQLiteDatabase db) {
        long newRowID = db.insert(GamesContract.PlayerEntry.TABLE_ZOMBIEDICE, null, values);

        if (newRowID == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving player", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Player saved with row id: " + newRowID, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //these methods are called when the applicable buttons are clicked

    //for  P1 Brains
    public void give1BrainP1(View view) {
        player1Brains = player1Brains + 1;

        displayForPlayer1(player1Brains, player1Score);
    }

    public void take1BrainP1(View view) {
        player1Brains = player1Brains - 1;

        if (player1Brains <= 0)
            player1Brains = 0;

        displayForPlayer1(player1Brains, player1Score);
    }

    //for P2 brains
    public void give1BrainP2(View view) {
        player2Brains = player2Brains + 1;
        displayForPlayer2(player2Brains, player2Score);
    }

    public void take1BrainP2(View view) {
        player2Brains = player2Brains - 1;

        if (player2Brains <= 0)
            player2Brains = 0;

        displayForPlayer2(player2Brains, player2Score);
    }

    //to reset scores
    public void resetPlayerScores(View view) {
        player1Brains = 0;
        player1Score = 0;
        displayForPlayer1(player1Brains, player1Score);

        player2Brains = 0;
        player2Score = 0;
        displayForPlayer2(player2Brains, player2Score);
    }

    //to save user data
    public void savePlayerData(View view) {
        insertZombieDicePlayerData();
    }

    public void loadPlayerData() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(GamesContract.PlayerEntry.TABLE_ZOMBIEDICE);

        // Create database helper
        GameDbHelper mDbHelper = new GameDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(stringBuilder.toString(), null);
        if (cursor.getCount() == 0) {
            resetPlayerScores(null);
            return;
        }

        cursor.moveToFirst();

        p1NameString = cursor.getString(1);
        player1Brains = cursor.getInt(2);
        player1Score = cursor.getInt(3);
        displayForPlayer1(p1NameString, player1Brains, player1Score);
        cursor.moveToNext();

        p2NameString = cursor.getString(1);
        player2Brains = cursor.getInt(2);
        player2Score = cursor.getInt(3);
        displayForPlayer2(p2NameString, player2Brains, player2Score);

        cursor.close();
    }

    //method which displays the given scores for player 1
    public void displayForPlayer1(String p1Name, int p1Brains, int p1Score) {

        EditText p1NameView = (EditText) findViewById(R.id.player1);
        p1NameView.setText(p1Name);

        displayForPlayer1(p1Brains, p1Score);
    }

    //method which displays the given scores for player 2
    public void displayForPlayer2(String p2Name, int p2Brains, int p2Score) {

        EditText p2NameView = (EditText) findViewById(R.id.player2);
        p2NameView.setText(p2Name);

        displayForPlayer2(p2Brains, p2Score);
    }

    public void displayForPlayer1(int p1Brains, int p1Score) {
        p1Score = p1Score + p1Brains;

        if (p1Score <= 0)
            p1Score = 0;

        TextView p1WhtHonView = (TextView) findViewById(R.id.p1_r1);
        p1WhtHonView.setText(String.valueOf(p1Brains));

        TextView p1ScoreView = (TextView) findViewById(R.id.player_1_score);
        p1ScoreView.setText(String.valueOf(p1Score));
    }

    //method which displays the given scores for player 2
    public void displayForPlayer2(int p2Brains, int p2Score) {
        p2Score = p2Score + p2Brains;

        if (p2Score <= 0)
            p2Score = 0;

        TextView p2WhtHonView = (TextView) findViewById(R.id.p2_r1);
        p2WhtHonView.setText(String.valueOf(p2Brains));

        TextView p2ScoreView = (TextView) findViewById(R.id.player_2_score);
        p2ScoreView.setText(String.valueOf(p2Score));
    }


}