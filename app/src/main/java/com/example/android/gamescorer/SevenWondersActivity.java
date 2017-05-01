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

import static com.example.android.gamescorer.R.id.player1;
import static com.example.android.gamescorer.R.id.player2;

public class SevenWondersActivity extends AppCompatActivity {

    private static final String STATE_P1NAME = "p1NameString";
    private static final String STATE_P2NAME = "p2NameString";
    private static final String STATE_P1MILITARY = "player1MilitaryConflicts";
    private static final String STATE_P2MILITARY = "player2MilitaryConflicts";
    private static final String STATE_P1TREASURY = "player1TreasuryContents";
    private static final String STATE_P2TREASURY = "player2TreasuryContents";
    private static final String STATE_P1WONDER = "player1Wonder";
    private static final String STATE_P2WONDER = "player2Wonder";
    private static final String STATE_P1CIVILIAN = "player1CivilianStructures";
    private static final String STATE_P2CIVILIAN = "player2CivilianStructures";
    private static final String STATE_P1IDENTICAL = "player1IdenticalScience";
    private static final String STATE_P2IDENTICAL = "player2IdenticalScience";
    private static final String STATE_P1DIFFERENT = "player1DifferentScience";
    private static final String STATE_P2DIFFERENT = "player2DifferentScience";
    private static final String STATE_P1COMMERCIAL = "player1CommercialStructures";
    private static final String STATE_P2COMMERCIAL = "player2CommercialStructures";
    private static final String STATE_P1GUILDS = "player1Guilds";
    private static final String STATE_P2GUILDS = "player2Guilds";
    private static final String STATE_P1SCORE = "player1Score";
    private static final String STATE_P2SCORE = "player2Score";

    // Player 1
    String p1NameString;
    int player1MilitaryConflicts;
    int player1TreasuryContents;
    int player1Wonder;
    int player1CivilianStructures;
    int player1IdenticalScience;
    int player1DifferentScience;
    int player1CommercialStructures;
    int player1Guilds;
    int player1Score;

    // Player 2
    String p2NameString;
    int player2MilitaryConflicts;
    int player2TreasuryContents;
    int player2Wonder;
    int player2CivilianStructures;
    int player2IdenticalScience;
    int player2DifferentScience;
    int player2CommercialStructures;
    int player2Guilds;
    int player2Score;

    /**
     * Database helper that will provide us access to the database
     */
    private GameDbHelper mDbHelper;

    private EditText mP1NameEditText;
    private EditText mP2NameEditText;

    private TextView mP1MilitaryConflicts;
    private TextView mP2MilitaryConflicts;

    private TextView mP1TreasuryContents;
    private TextView mP2TreasuryContents;

    private TextView mP1Wonder;
    private TextView mP2Wonder;

    private TextView mP1CivilianStructures;
    private TextView mP2CivilianStructures;

    private TextView mP1IdenticalScience;
    private TextView mP2IdenticalScience;

    private TextView mP1DifferentScience;
    private TextView mP2DifferentScience;

    private TextView mP1CommercialStructures;
    private TextView mP2CommercialStructures;

    private TextView mP1Guilds;
    private TextView mP2Guilds;

    private TextView mP1TotalScore;
    private TextView mP2TotalScore;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString(STATE_P1NAME, p1NameString);
        savedInstanceState.putString(STATE_P2NAME, p2NameString);

        savedInstanceState.putInt(STATE_P1MILITARY, player1MilitaryConflicts);
        savedInstanceState.putInt(STATE_P2MILITARY, player2MilitaryConflicts);

        savedInstanceState.putInt(STATE_P1TREASURY, player1TreasuryContents);
        savedInstanceState.putInt(STATE_P2TREASURY, player2TreasuryContents);

        savedInstanceState.putInt(STATE_P1WONDER, player1Wonder);
        savedInstanceState.putInt(STATE_P2WONDER, player2Wonder);

        savedInstanceState.putInt(STATE_P1CIVILIAN, player1CivilianStructures);
        savedInstanceState.putInt(STATE_P2CIVILIAN, player2CivilianStructures);

        savedInstanceState.putInt(STATE_P1IDENTICAL, player1IdenticalScience);
        savedInstanceState.putInt(STATE_P2IDENTICAL, player2IdenticalScience);

        savedInstanceState.putInt(STATE_P1DIFFERENT, player1DifferentScience);
        savedInstanceState.putInt(STATE_P2DIFFERENT, player2DifferentScience);

        savedInstanceState.putInt(STATE_P1COMMERCIAL, player1CommercialStructures);
        savedInstanceState.putInt(STATE_P2COMMERCIAL, player2CommercialStructures);

        savedInstanceState.putInt(STATE_P1GUILDS, player1Guilds);
        savedInstanceState.putInt(STATE_P2GUILDS, player2Guilds);

        savedInstanceState.putInt(STATE_P1SCORE, player1Score);
        savedInstanceState.putInt(STATE_P2SCORE, player2Score);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_wonders);
        mDbHelper = new GameDbHelper(this);

        // Find all relevant views that we will need to read user input from
        /*
      EditText fields to enter the player's names
     */
        mP1NameEditText = (EditText) findViewById(player1);
        mP2NameEditText = (EditText) findViewById(player2);
        /*
      Military Conflicts points fields
     */
        mP1MilitaryConflicts = (TextView) findViewById(R.id.p1_r1);
        mP2MilitaryConflicts = (TextView) findViewById(R.id.p2_r1);
        /*
      Treasury points fields
     */
        mP1TreasuryContents = (TextView) findViewById(R.id.p1_r2);
        mP2TreasuryContents = (TextView) findViewById(R.id.p2_r2);
        /*
      Wonder points fields
     */
        mP1Wonder = (TextView) findViewById(R.id.p1_r3);
        mP2Wonder = (TextView) findViewById(R.id.p2_r3);
          /*
      Civilian Structures points fields
     */
        mP1CivilianStructures = (TextView) findViewById(R.id.p1_r4);
        mP2CivilianStructures = (TextView) findViewById(R.id.p2_r4);
          /*
      Identical Science points fields
     */
        mP1IdenticalScience = (TextView) findViewById(R.id.p1_r5);
        mP2IdenticalScience = (TextView) findViewById(R.id.p2_r5);
          /*
      Different Science points fields
     */
        mP1DifferentScience = (TextView) findViewById(R.id.p1_r6);
        mP2DifferentScience = (TextView) findViewById(R.id.p2_r6);
          /*
      Commercial points fields
     */
        mP1CommercialStructures = (TextView) findViewById(R.id.p1_r7);
        mP2CommercialStructures = (TextView) findViewById(R.id.p2_r7);
          /*
      Guilds points fields
     */
        mP1Guilds = (TextView) findViewById(R.id.p1_r8);
        mP2Guilds = (TextView) findViewById(R.id.p2_r8);
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

            player1MilitaryConflicts = savedInstanceState.getInt(STATE_P1MILITARY);
            player2MilitaryConflicts = savedInstanceState.getInt(STATE_P2MILITARY);

            player1TreasuryContents = savedInstanceState.getInt(STATE_P1TREASURY);
            player2TreasuryContents = savedInstanceState.getInt(STATE_P2TREASURY);

            player1Wonder = savedInstanceState.getInt(STATE_P1WONDER);
            player2Wonder = savedInstanceState.getInt(STATE_P2WONDER);

            player1CivilianStructures = savedInstanceState.getInt(STATE_P1CIVILIAN);
            player2CivilianStructures = savedInstanceState.getInt(STATE_P2CIVILIAN);

            player1IdenticalScience = savedInstanceState.getInt(STATE_P1IDENTICAL);
            player2IdenticalScience = savedInstanceState.getInt(STATE_P2IDENTICAL);

            player1DifferentScience = savedInstanceState.getInt(STATE_P1DIFFERENT);
            player2DifferentScience = savedInstanceState.getInt(STATE_P2DIFFERENT);

            player1CommercialStructures = savedInstanceState.getInt(STATE_P1COMMERCIAL);
            player2CommercialStructures = savedInstanceState.getInt(STATE_P2COMMERCIAL);

            player1Guilds = savedInstanceState.getInt(STATE_P1GUILDS);
            player2Guilds = savedInstanceState.getInt(STATE_P2GUILDS);

            player1Score = savedInstanceState.getInt(STATE_P1SCORE);
            player2Score = savedInstanceState.getInt(STATE_P2SCORE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //Get player data and save into database
    private void insertSevenWondersPlayerData() {

        // Create database helper
        GameDbHelper mDbHelper = new GameDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Remove the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + GamesContract.PlayerEntry.TABLE_SEVENWONDERS);
        mDbHelper.CreateTable(GameDbHelper.GameType.SevenWonders, db);

        // Create a ContentValues object where column names are the keys,
        // and player attributes from the editor are the values.
        ContentValues valuesA = new ContentValues();
        ContentValues valuesB = new ContentValues();

        // player 1

        p1NameString = mP1NameEditText.getText().toString();
        valuesA.put(GamesContract.PlayerEntry.COLUMN_NAME, p1NameString);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_MILITARY_CONFLICTS, player1MilitaryConflicts);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_TREASURY, player1TreasuryContents);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_WONDER, player1Wonder);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_CIVILIAN_STRUCTURES, player1CivilianStructures);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_IDENTICAL_SCIENTIFIC_STRUCTURES, player1IdenticalScience);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_DIFFERENT_SCIENTIFIC_STRUCTURES, player1DifferentScience);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_COMMERCIAL_STRUCTURES, player1CommercialStructures);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_GUILDS, player1Guilds);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_TOTAL, player1Score);

        // player 2

        p2NameString = mP2NameEditText.getText().toString();
        valuesB.put(GamesContract.PlayerEntry.COLUMN_NAME, p2NameString);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_MILITARY_CONFLICTS, player2MilitaryConflicts);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_TREASURY, player2TreasuryContents);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_WONDER, player2Wonder);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_CIVILIAN_STRUCTURES, player2CivilianStructures);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_IDENTICAL_SCIENTIFIC_STRUCTURES, player2IdenticalScience);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_DIFFERENT_SCIENTIFIC_STRUCTURES, player2DifferentScience);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_COMMERCIAL_STRUCTURES, player2CommercialStructures);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_GUILDS, player2Guilds);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_TOTAL, player2Score);

        addRowToSevenWondersTable(valuesA, db);
        addRowToSevenWondersTable(valuesB, db);

    }

    // Show a toast message depending on whether or not the insertion was successful
    private void addRowToSevenWondersTable(ContentValues values, SQLiteDatabase db) {
        long newRowID = db.insert(GamesContract.PlayerEntry.TABLE_SEVENWONDERS, null, values);

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

    //for  P1 Military Conflicts
    public void give1MilitaryPointP1(View view) {
        player1MilitaryConflicts = player1MilitaryConflicts + 1;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    public void take1MilitaryPointP1(View view) {
        // can be negative
        player1MilitaryConflicts = player1MilitaryConflicts - 1;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    //for  P2 Military Conflicts
    public void give1MilitaryPointP2(View view) {
        player2MilitaryConflicts = player2MilitaryConflicts + 1;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    public void take1MilitaryPointP2(View view) {
        // can be negative
        player2MilitaryConflicts = player2MilitaryConflicts - 1;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    // for P1 Treasury Contents
    public void give1TreasuryPointP1(View view) {
        player1TreasuryContents = player1TreasuryContents + 1;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    public void take1TreasuryPointP1(View view) {
        player1TreasuryContents = player1TreasuryContents - 1;

        if (player1TreasuryContents <= 0)
            player1TreasuryContents = 0;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    // for P2 Treasury Contents
    public void give1TreasuryPointP2(View view) {
        player2TreasuryContents = player2TreasuryContents + 1;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    public void take1TreasuryPointP2(View view) {
        player2TreasuryContents = player2TreasuryContents - 1;

        if (player2TreasuryContents <= 0)
            player2TreasuryContents = 0;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    // for P1 Wonder
    public void give1WonderPointP1(View view) {
        player1Wonder = player1Wonder + 1;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    public void take1WonderPointP1(View view) {
        player1Wonder = player1Wonder - 1;

        if (player1Wonder <= 0)
            player1Wonder = 0;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    // for P2 Wonder
    public void give1WonderPointP2(View view) {
        player2Wonder = player2Wonder + 1;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    public void take1WonderPointP2(View view) {
        player2Wonder = player2Wonder - 1;

        if (player2Wonder <= 0)
            player2Wonder = 0;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }


    // for P1 Civilian Structures
    public void give1CivilianPointP1(View view) {
        player1CivilianStructures = player1CivilianStructures + 1;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    public void take1CivilianPointP1(View view) {
        player1CivilianStructures = player1CivilianStructures - 1;

        if (player1CivilianStructures <= 0)
            player1CivilianStructures = 0;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    // for P2 Civilian Structures
    public void give1CivilianPointP2(View view) {
        player2CivilianStructures = player2CivilianStructures + 1;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    public void take1CivilianPointP2(View view) {
        player2CivilianStructures = player2CivilianStructures - 1;

        if (player2CivilianStructures <= 0)
            player2CivilianStructures = 0;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    // for P1 Identical Science Symbols
    public void giveSquaredIdenticalPointsP1(View view) {

        player1IdenticalScience = player1IdenticalScience + 1;

        //when score is totalled, this number will be squared
        //(int) Math.pow(player1IdenticalScience, 2);

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    public void takeSquaredIdenticalPointsP1(View view) {

        player1IdenticalScience = player1IdenticalScience - 1;

        // when score is totalled, this number will be negatively squared
        //(int) Math.pow(player1IdenticalScience, -2);

        if (player1IdenticalScience <= 0)
            player1IdenticalScience = 0;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    // for P2 Identical Science Symbols

    public void giveSquaredIdenticalPointsP2(View view) {

        player2IdenticalScience = player2IdenticalScience + 1;

        //when score is totalled, this number will be squared
        //(int) Math.pow(player2IdenticalScience, 2);

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    public void takeSquaredIdenticalPointsP2(View view) {

        player2IdenticalScience = player2IdenticalScience - 1;

        // when score is totalled, this number will be negatively squared
        //(int) Math.pow(player2IdenticalScience, -2);

        if (player2IdenticalScience <= 0)
            player2IdenticalScience = 0;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    // for P1 Different Science Symbols
    public void give7DifferentPointsP1(View view) {
        player1DifferentScience = player1DifferentScience + 1;

        // When score is totalled, this number will be multiplied by 7

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    public void take7DifferentPointsP1(View view) {
        player1DifferentScience = player1DifferentScience - 1;

        // When score is totalled, 7 will be subtracted

        if (player1DifferentScience <= 0)
            player1DifferentScience = 0;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    // for P2 Different Science Symbols

    public void give7DifferentPointsP2(View view) {
        player2DifferentScience = player2DifferentScience + 1;

        // When score is totalled, this number will be multiplied by 7

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    public void take7DifferentPointsP2(View view) {
        player2DifferentScience = player2DifferentScience - 1;

        // When score is totalled, 7 will be subtracted

        if (player2DifferentScience <= 0)
            player2DifferentScience = 0;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    // for P1 Commercial Structures
    public void give1CommercialPointP1(View view) {
        player1CommercialStructures = player1CommercialStructures + 1;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    public void take1CommercialPointP1(View view) {
        player1CommercialStructures = player1CommercialStructures - 1;

        if (player1CommercialStructures <= 0)
            player1CommercialStructures = 0;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    // for P2 Commercial Structures
    public void give1CommercialPointP2(View view) {
        player2CommercialStructures = player2CommercialStructures + 1;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    public void take1CommercialPointP2(View view) {
        player2CommercialStructures = player2CommercialStructures - 1;

        if (player2CommercialStructures <= 0)
            player2CommercialStructures = 0;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    // for P1 Guilds
    public void give1GuildPointP1(View view) {
        player1Guilds = player1Guilds + 1;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    public void take1GuildPointP1(View view) {
        player1Guilds = player1Guilds - 1;

        if (player1Guilds <= 0)
            player1Guilds = 0;

        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
    }

    // for P2 Guilds
    public void give1GuildPointP2(View view) {
        player2Guilds = player2Guilds + 1;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    public void take1GuildPointP2(View view) {
        player2Guilds = player2Guilds - 1;

        if (player2Guilds <= 0)
            player2Guilds = 0;

        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    //to reset scores
    public void resetPlayerScores(View view) {
        player1MilitaryConflicts = 0;
        player1TreasuryContents = 0;
        player1Wonder = 0;
        player1CivilianStructures = 0;
        player1IdenticalScience = 0;
        player1DifferentScience = 0;
        player1CommercialStructures = 0;
        player1Guilds = 0;
        player1Score = 0;
        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);

        player2MilitaryConflicts = 0;
        player2TreasuryContents = 0;
        player2Wonder = 0;
        player2CivilianStructures = 0;
        player2IdenticalScience = 0;
        player2DifferentScience = 0;
        player2CommercialStructures = 0;
        player2Guilds = 0;
        player2Score = 0;
        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);
    }

    //to save user data
    public void savePlayerData(View view) {
        insertSevenWondersPlayerData();
    }

    public void loadPlayerData() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(GamesContract.PlayerEntry.TABLE_SEVENWONDERS);

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
        player1MilitaryConflicts = cursor.getInt(2);
        player1TreasuryContents = cursor.getInt(3);
        player1Wonder = cursor.getInt(4);
        player1CivilianStructures = cursor.getInt(5);
        player1IdenticalScience = cursor.getInt(6);
        player1DifferentScience = cursor.getInt(7);
        player1CommercialStructures = cursor.getInt(8);
        player1Score = cursor.getInt(9);
        displayForPlayer1(player1MilitaryConflicts, player1TreasuryContents, player1Wonder,
                player1CivilianStructures, player1IdenticalScience, player1DifferentScience,
                player1CommercialStructures, player1Guilds, player1Score);
        cursor.moveToNext();

        p2NameString = cursor.getString(1);
        player2MilitaryConflicts = cursor.getInt(2);
        player2TreasuryContents = cursor.getInt(3);
        player2Wonder = cursor.getInt(4);
        player2CivilianStructures = cursor.getInt(5);
        player2IdenticalScience = cursor.getInt(6);
        player2DifferentScience = cursor.getInt(7);
        player2CommercialStructures = cursor.getInt(8);
        player2Score = cursor.getInt(9);
        displayForPlayer2(player2MilitaryConflicts, player2TreasuryContents, player2Wonder,
                player2CivilianStructures, player2IdenticalScience, player2DifferentScience,
                player2CommercialStructures, player2Guilds, player2Score);

        cursor.close();
    }

    //method which displays the given scores for player 1
    public void displayForPlayer1(String p1Name, int p1MilCon, int p1Treasure, int p1Won, int p1Civ,
                                  int p1IdentSci, int p1DiffSci, int p1Commercial, int p1Guild,
                                  int p1Score) {

        EditText p1NameView = (EditText) findViewById(R.id.player1);
        p1NameView.setText(p1Name);

        displayForPlayer1(p1MilCon, p1Treasure, p1Won, p1Civ, p1IdentSci, p1DiffSci, p1Commercial,
                p1Guild, p1Score);
    }

    //method which displays the given scores for player 2
    public void displayForPlayer2(String p2Name, int p2MilCon, int p2Treasure, int p2Won, int p2Civ,
                                  int p2IdentSci, int p2DiffSci, int p2Commercial, int p2Guild, int p2Score) {

        EditText p2NameView = (EditText) findViewById(R.id.player2);
        p2NameView.setText(p2Name);

        displayForPlayer2(p2MilCon, p2Treasure, p2Won, p2Civ, p2IdentSci, p2DiffSci, p2Commercial,
                p2Guild, p2Score);
    }

    public void displayForPlayer1(int p1MilCon, int p1Treasure, int p1Won, int p1Civ,
                                  int p1IdentSci, int p1DiffSci, int p1Commercial, int p1Guild,
                                  int p1Score) {

        p1IdentSci = (int) Math.pow(p1IdentSci, 2);
        p1DiffSci = p1DiffSci * 7;

        p1Score = p1Score + p1MilCon + p1Treasure + p1Won + p1Civ + p1IdentSci + p1DiffSci + p1Commercial + p1Guild;

        TextView p1MilConView = (TextView) findViewById(R.id.p1_r1);
        p1MilConView.setText(String.valueOf(p1MilCon));

        TextView p1TreasureView = (TextView) findViewById(R.id.p1_r2);
        p1TreasureView.setText(String.valueOf(p1Treasure));

        TextView p1WonView = (TextView) findViewById(R.id.p1_r3);
        p1WonView.setText(String.valueOf(p1Won));

        TextView p1CivView = (TextView) findViewById(R.id.p1_r4);
        p1CivView.setText(String.valueOf(p1Civ));

        TextView p1IdentSciView = (TextView) findViewById(R.id.p1_r5);
        p1IdentSciView.setText(String.valueOf(p1IdentSci));

        TextView p1DiffSciView = (TextView) findViewById(R.id.p1_r6);
        p1DiffSciView.setText(String.valueOf(p1DiffSci));

        TextView p1CommercialView = (TextView) findViewById(R.id.p1_r7);
        p1CommercialView.setText(String.valueOf(p1Commercial));

        TextView p1GuildView = (TextView) findViewById(R.id.p1_r8);
        p1GuildView.setText(String.valueOf(p1Guild));

        TextView p1ScoreView = (TextView) findViewById(R.id.player_1_score);
        p1ScoreView.setText(String.valueOf(p1Score));

    }

    public void displayForPlayer2(int p2MilCon, int p2Treasure, int p2Won, int p2Civ,
                                  int p2IdentSci, int p2DiffSci, int p2Commercial, int p2Guild,
                                  int p2Score) {

        p2IdentSci = (int) Math.pow(p2IdentSci, 2);
        p2DiffSci = p2DiffSci * 7;

        p2Score = p2Score + p2MilCon + p2Treasure + p2Won + p2Civ + p2IdentSci + p2DiffSci + p2Commercial + p2Guild;

        TextView p2MilConView = (TextView) findViewById(R.id.p2_r1);
        p2MilConView.setText(String.valueOf(p2MilCon));

        TextView p2TreasureView = (TextView) findViewById(R.id.p2_r2);
        p2TreasureView.setText(String.valueOf(p2Treasure));

        TextView p2WonView = (TextView) findViewById(R.id.p2_r3);
        p2WonView.setText(String.valueOf(p2Won));

        TextView p2CivView = (TextView) findViewById(R.id.p2_r4);
        p2CivView.setText(String.valueOf(p2Civ));

        TextView p2IdentSciView = (TextView) findViewById(R.id.p2_r5);
        p2IdentSciView.setText(String.valueOf(p2IdentSci));

        TextView p2DiffSciView = (TextView) findViewById(R.id.p2_r6);
        p2DiffSciView.setText(String.valueOf(p2DiffSci));

        TextView p2CommercialView = (TextView) findViewById(R.id.p2_r7);
        p2CommercialView.setText(String.valueOf(p2Commercial));

        TextView p2GuildView = (TextView) findViewById(R.id.p2_r8);
        p2GuildView.setText(String.valueOf(p2Guild));

        TextView p2ScoreView = (TextView) findViewById(R.id.player_2_score);
        p2ScoreView.setText(String.valueOf(p2Score));

    }


}
