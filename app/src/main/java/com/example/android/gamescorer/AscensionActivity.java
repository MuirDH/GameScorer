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


public class AscensionActivity extends AppCompatActivity {

    // player 1

    private static final String STATE_P1NAME = "p1NameString";
    private static final String STATE_P2NAME = "p2NameString";
    private static final String STATE_P1WHITE = "player1WhiteHonour";
    private static final String STATE_P2WHITE = "player2WhiteHonour";
    private static final String STATE_P1RED = "player1RedHonour";
    private static final String STATE_P2RED = "player2RedHonour";
    private static final String STATE_P1CARD = "player1CardHonour";
    private static final String STATE_P2CARD = "player2CardHonour";
    private static final String STATE_P1SCORE = "player1Score";
    private static final String STATE_P2SCORE = "player2Score";
    String p1NameString;
    int player1WhiteHonour;
    int player1RedHonour;
    int player1CardHonour;
    int player1Score;
    // player 2
    String p2NameString;
    int player2WhiteHonour;
    int player2RedHonour;
    int player2CardHonour;
    int player2Score;
    /**
     * Database helper that will provide us access to the database
     * Compiler warns that mDbHelper and TextViews for Honour and TotalScore aren't accessed, but
     * removing them breaks the code, so they have been kept
     */
    private GameDbHelper mDbHelper;

    private EditText mP1NameEditText;
    private EditText mP2NameEditText;

    private TextView mP1WhiteHonour;
    private TextView mP2WhiteHonour;

    private TextView mP1RedHonour;
    private TextView mP2RedHonour;

    private TextView mP1CardHonour;
    private TextView mP2CardHonour;

    private TextView mP1TotalScore;
    private TextView mP2TotalScore;


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString(STATE_P1NAME, p1NameString);
        savedInstanceState.putString(STATE_P2NAME, p2NameString);

        savedInstanceState.putInt(STATE_P1WHITE, player1WhiteHonour);
        savedInstanceState.putInt(STATE_P2WHITE, player2WhiteHonour);

        savedInstanceState.putInt(STATE_P1RED, player1RedHonour);
        savedInstanceState.putInt(STATE_P2RED, player2RedHonour);

        savedInstanceState.putInt(STATE_P1CARD, player1CardHonour);
        savedInstanceState.putInt(STATE_P2CARD, player2CardHonour);

        savedInstanceState.putInt(STATE_P1SCORE, player1Score);
        savedInstanceState.putInt(STATE_P2SCORE, player2Score);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        displayForPlayer1(player1WhiteHonour, player1RedHonour, player1CardHonour,
                player1Score);
        displayForPlayer2(player2WhiteHonour, player2RedHonour, player2CardHonour,
                player2Score);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascension);
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
        mP1WhiteHonour = (TextView) findViewById(R.id.p1_r1);
        mP2WhiteHonour = (TextView) findViewById(R.id.p2_r1);
        /*
      red honour points fields
     */
        mP1RedHonour = (TextView) findViewById(R.id.p1_r2);
        mP2RedHonour = (TextView) findViewById(R.id.p2_r2);
        /*
      card honour points fields
     */
        mP1CardHonour = (TextView) findViewById(R.id.p1_r3);
        mP2CardHonour = (TextView) findViewById(R.id.p2_r3);
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

            player1WhiteHonour = savedInstanceState.getInt(STATE_P1WHITE);
            player2WhiteHonour = savedInstanceState.getInt(STATE_P2WHITE);

            player1RedHonour = savedInstanceState.getInt(STATE_P1RED);
            player2RedHonour = savedInstanceState.getInt(STATE_P2RED);

            player1CardHonour = savedInstanceState.getInt(STATE_P1CARD);
            player2CardHonour = savedInstanceState.getInt(STATE_P2CARD);

            player1Score = savedInstanceState.getInt(STATE_P1SCORE);
            player2Score = savedInstanceState.getInt(STATE_P2SCORE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //Get player data and save into database
    private void insertAscensionPlayerData() {

        // Create database helper
        GameDbHelper mDbHelper = new GameDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Remove the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + GamesContract.PlayerEntry.TABLE_ASCENSION);
        mDbHelper.CreateTable(GameDbHelper.GameType.Ascension, db);

        // Create a ContentValues object where column names are the keys,
        // and player attributes from the editor are the values.
        ContentValues valuesA = new ContentValues();

        p1NameString = mP1NameEditText.getText().toString();

        //player 1
        valuesA.put(GamesContract.PlayerEntry.COLUMN_NAME, p1NameString);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_WHITE, player1WhiteHonour);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_RED, player1RedHonour);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_CARD, player1CardHonour);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_TOTAL, player1Score);

        ContentValues valuesB = new ContentValues();

        p2NameString = mP2NameEditText.getText().toString();

        //player 2
        valuesB.put(GamesContract.PlayerEntry.COLUMN_NAME, p2NameString);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_WHITE, player2WhiteHonour);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_RED, player2RedHonour);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_CARD, player2CardHonour);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_TOTAL, player2Score);

        addRowToAscensionTable(valuesA, db);
        addRowToAscensionTable(valuesB, db);

    }

    // Show a toast message depending on whether or not the insertion was successful
    private void addRowToAscensionTable(ContentValues values, SQLiteDatabase db) {
        long newRowID = db.insert(GamesContract.PlayerEntry.TABLE_ASCENSION, null, values);

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

    //for  P1 white honour
    public void give1HonPointP1(View view) {
        player1WhiteHonour = player1WhiteHonour + 1;

        displayForPlayer1(player1WhiteHonour, player1RedHonour, player1CardHonour, player1Score);
    }

    public void take1HonPointP1(View view) {
        player1WhiteHonour = player1WhiteHonour - 1;

        if (player1WhiteHonour <= 0)
            player1WhiteHonour = 0;

        displayForPlayer1(player1WhiteHonour, player1RedHonour, player1CardHonour, player1Score);
    }

    //for P2 white honour
    public void give1HonPointP2(View view) {
        player2WhiteHonour = player2WhiteHonour + 1;
        displayForPlayer2(player2WhiteHonour, player2RedHonour, player2CardHonour, player2Score);
    }

    public void take1HonPointP2(View view) {
        player2WhiteHonour = player2WhiteHonour - 1;

        if (player2WhiteHonour <= 0)
            player2WhiteHonour = 0;

        displayForPlayer2(player2WhiteHonour, player2RedHonour, player2CardHonour, player2Score);
    }

    //for P1 red honour
    public void give5HonPointP1(View view) {
        player1RedHonour = player1RedHonour + 5;
        displayForPlayer1(player1WhiteHonour, player1RedHonour, player1CardHonour, player1Score);
    }

    public void take5HonPointP1(View view) {
        player1RedHonour = player1RedHonour - 5;

        if (player1RedHonour <= 0)
            player1RedHonour = 0;

        displayForPlayer1(player1WhiteHonour, player1RedHonour, player1CardHonour, player1Score);
    }

    //for P2 red honour
    public void give5HonPointP2(View view) {
        player2RedHonour = player2RedHonour + 5;
        displayForPlayer2(player2WhiteHonour, player2RedHonour, player2CardHonour, player2Score);
    }

    public void take5HonPointP2(View view) {
        player2RedHonour = player2RedHonour - 5;

        if (player2RedHonour <= 0)
            player2RedHonour = 0;

        displayForPlayer2(player2WhiteHonour, player2RedHonour, player2CardHonour, player2Score);
    }

    //for P1 card honour
    public void give1CardPointP1(View view) {
        player1CardHonour = player1CardHonour + 1;
        displayForPlayer1(player1WhiteHonour, player1RedHonour, player1CardHonour, player1Score);
    }

    public void take1CardPointP1(View view) {
        player1CardHonour = player1CardHonour - 1;

        if (player1CardHonour <= 0)
            player1CardHonour = 0;

        displayForPlayer1(player1WhiteHonour, player1RedHonour, player1CardHonour, player1Score);
    }

    //for P2 card honour
    public void give1CardPointP2(View view) {
        player2CardHonour = player2CardHonour + 1;
        displayForPlayer2(player2WhiteHonour, player2RedHonour, player2CardHonour, player2Score);
    }

    public void take1CardPointP2(View view) {
        player2CardHonour = player2CardHonour - 1;

        if (player2CardHonour <= 0)
            player2CardHonour = 0;

        displayForPlayer2(player2WhiteHonour, player2RedHonour, player2CardHonour, player2Score);
    }

    //to reset scores
    public void resetPlayerScores(View view) {
        player1WhiteHonour = 0;
        player1RedHonour = 0;
        player1CardHonour = 0;
        player1Score = 0;
        displayForPlayer1(player1WhiteHonour, player1RedHonour, player1CardHonour, player1Score);

        player2WhiteHonour = 0;
        player2RedHonour = 0;
        player2CardHonour = 0;
        player2Score = 0;
        displayForPlayer2(player2WhiteHonour, player2RedHonour, player2CardHonour, player2Score);
    }

    //to save user data
    public void savePlayerData(View view) {
        insertAscensionPlayerData();
    }

    public void loadPlayerData() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(GamesContract.PlayerEntry.TABLE_ASCENSION);

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
        player1WhiteHonour = cursor.getInt(2);
        player1RedHonour = cursor.getInt(3);
        player1CardHonour = cursor.getInt(4);
        player1Score = cursor.getInt(5);
        displayForPlayer1(p1NameString, player1WhiteHonour, player1RedHonour, player1CardHonour, player1Score);
        cursor.moveToNext();

        p2NameString = cursor.getString(1);
        player2WhiteHonour = cursor.getInt(2);
        player2RedHonour = cursor.getInt(3);
        player2CardHonour = cursor.getInt(4);
        player2Score = cursor.getInt(5);
        displayForPlayer2(p2NameString, player2WhiteHonour, player2RedHonour, player2CardHonour, player2Score);

        cursor.close();
    }

    //method which displays the given scores for player 1
    public void displayForPlayer1(String p1Name, int p1WhtHon, int p1RedHon, int p1CdHon, int p1Score) {

        EditText p1NameView = (EditText) findViewById(R.id.player1);
        p1NameView.setText(p1Name);

        displayForPlayer1(p1WhtHon, p1RedHon, p1CdHon, p1Score);
    }

    //method which displays the given scores for player 2
    public void displayForPlayer2(String p2Name, int p2WhtHon, int p2RedHon, int p2CdHon, int p2Score) {

        EditText p2NameView = (EditText) findViewById(R.id.player2);
        p2NameView.setText(p2Name);

        displayForPlayer2(p2WhtHon, p2RedHon, p2CdHon, p2Score);
    }

    public void displayForPlayer1(int p1WhtHon, int p1RedHon, int p1CdHon, int p1Score) {
        p1Score = p1Score + p1WhtHon + p1RedHon + p1CdHon;

        if (p1Score <= 0)
            p1Score = 0;

        TextView p1WhtHonView = (TextView) findViewById(R.id.p1_r1);
        p1WhtHonView.setText(String.valueOf(p1WhtHon));

        TextView p1RedHonView = (TextView) findViewById(R.id.p1_r2);
        p1RedHonView.setText(String.valueOf(p1RedHon));

        TextView p1CdHonView = (TextView) findViewById(R.id.p1_r3);
        p1CdHonView.setText(String.valueOf(p1CdHon));

        TextView p1ScoreView = (TextView) findViewById(R.id.player_1_score);
        p1ScoreView.setText(String.valueOf(p1Score));
    }

    //method which displays the given scores for player 2
    public void displayForPlayer2(int p2WhtHon, int p2RedHon, int p2CdHon, int p2Score) {
        p2Score = p2Score + p2WhtHon + p2RedHon + p2CdHon;

        if (p2Score <= 0)
            p2Score = 0;

        TextView p2WhtHonView = (TextView) findViewById(R.id.p2_r1);
        p2WhtHonView.setText(String.valueOf(p2WhtHon));

        TextView p2RedHonView = (TextView) findViewById(R.id.p2_r2);
        p2RedHonView.setText(String.valueOf(p2RedHon));

        TextView p2CdHonView = (TextView) findViewById(R.id.p2_r3);
        p2CdHonView.setText(String.valueOf(p2CdHon));

        TextView p2ScoreView = (TextView) findViewById(R.id.player_2_score);
        p2ScoreView.setText(String.valueOf(p2Score));
    }

}
