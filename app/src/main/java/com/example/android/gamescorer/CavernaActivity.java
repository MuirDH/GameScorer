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


public class CavernaActivity extends AppCompatActivity {

    private static final String STATE_P1NAME = "p1NameString";
    private static final String STATE_P2NAME = "p2NameString";
    private static final String STATE_P1FARM = "player1FarmAnimalPoints";
    private static final String STATE_P2FARM = "player2FarmAnimalPoints";
    private static final String STATE_P1GRAIN = "player1GrainPoints";
    private static final String STATE_P2GRAIN = "player2GrainPoints";
    private static final String STATE_P1VEGGIE = "player1VeggiePoints";
    private static final String STATE_P2VEGGIE = "player2VeggiePoints";
    private static final String STATE_P1RUBY = "player1RubyPoints";
    private static final String STATE_P2RUBY = "player2RubyPoints";
    private static final String STATE_P1DWARF = "player1DwarfPoints";
    private static final String STATE_P2DWARF = "player2DwarfPoints";
    private static final String STATE_P1UNUSEDSPACE = "player1UnusedSpacePoints";
    private static final String STATE_P2UNUSEDSPACE = "player2UnusedSpacePoints";
    private static final String STATE_P1FURNISHING = "player1FurnishingPoints";
    private static final String STATE_P2FURNISHING = "player2FurnishingPoints";
    private static final String STATE_P1BONUS = "player1BonusPoints";
    private static final String STATE_P2BONUS = "player2BonusPoints";
    private static final String STATE_P1GOLD = "player1GoldPoints";
    private static final String STATE_P2GOLD = "player2GoldPoints";
    private static final String STATE_P1SCORE = "player1Score";
    private static final String STATE_P2SCORE = "player2Score";
    // player 1
    String p1NameString;
    double player1FarmAnimalPoints;
    double player1GrainPoints;
    double player1VeggiePoints;
    double player1RubyPoints;
    double player1DwarfPoints;
    double player1UnusedSpacePoints;
    double player1FurnishingPoints;
    double player1BonusPoints;
    double player1GoldPoints;
    double player1Score;
    // player 2
    String p2NameString;
    double player2FarmAnimalPoints;
    double player2GrainPoints;
    double player2VeggiePoints;
    double player2RubyPoints;
    double player2DwarfPoints;
    double player2UnusedSpacePoints;
    double player2FurnishingPoints;
    double player2BonusPoints;
    double player2GoldPoints;
    double player2Score;
    /**
     * Database helper that will provide us access to the database
     */
    private GameDbHelper mDbHelper;

    private EditText mP1NameEditText;
    private EditText mP2NameEditText;

    private TextView mP1Farm;
    private TextView mP2Farm;

    private TextView mP1Grain;
    private TextView mP2Grain;

    private TextView mP1Veggie;
    private TextView mP2Veggie;

    private TextView mP1Ruby;
    private TextView mP2Ruby;

    private TextView mP1Dwarf;
    private TextView mP2Dwarf;

    private TextView mP1UnusedSpace;
    private TextView mP2UnusedSpace;

    private TextView mP1Furnishing;
    private TextView mP2Furnishing;

    private TextView mP1Bonus;
    private TextView mP2Bonus;

    private TextView mP1Gold;
    private TextView mP2Gold;

    private TextView mP1TotalScore;
    private TextView mP2TotalScore;


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putString(STATE_P1NAME, p1NameString);
        savedInstanceState.putString(STATE_P2NAME, p2NameString);

        savedInstanceState.putDouble(STATE_P1FARM, player1FarmAnimalPoints);
        savedInstanceState.putDouble(STATE_P2FARM, player2FarmAnimalPoints);

        savedInstanceState.putDouble(STATE_P1GRAIN, player1GrainPoints);
        savedInstanceState.putDouble(STATE_P2GRAIN, player2GrainPoints);

        savedInstanceState.putDouble(STATE_P1VEGGIE, player1VeggiePoints);
        savedInstanceState.putDouble(STATE_P2VEGGIE, player2VeggiePoints);

        savedInstanceState.putDouble(STATE_P1RUBY, player1RubyPoints);
        savedInstanceState.putDouble(STATE_P2RUBY, player2RubyPoints);

        savedInstanceState.putDouble(STATE_P1DWARF, player1DwarfPoints);
        savedInstanceState.putDouble(STATE_P2DWARF, player2DwarfPoints);

        savedInstanceState.putDouble(STATE_P1UNUSEDSPACE, player1UnusedSpacePoints);
        savedInstanceState.putDouble(STATE_P2UNUSEDSPACE, player2UnusedSpacePoints);

        savedInstanceState.putDouble(STATE_P1FURNISHING, player1FurnishingPoints);
        savedInstanceState.putDouble(STATE_P2FURNISHING, player2FurnishingPoints);

        savedInstanceState.putDouble(STATE_P1BONUS, player1BonusPoints);
        savedInstanceState.putDouble(STATE_P2BONUS, player2BonusPoints);

        savedInstanceState.putDouble(STATE_P1GOLD, player1GoldPoints);
        savedInstanceState.putDouble(STATE_P2GOLD, player2GoldPoints);

        savedInstanceState.putDouble(STATE_P1SCORE, player1Score);
        savedInstanceState.putDouble(STATE_P2SCORE, player2Score);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caverna);
        mDbHelper = new GameDbHelper(this);

        // Find all relevant views that we will need to read user input from
        /*
      EditText fields to enter the player's names
     */
        mP1NameEditText = (EditText) findViewById(player1);
        mP2NameEditText = (EditText) findViewById(R.id.player2);
        /*
      farm points fields
     */
        mP1Farm = (TextView) findViewById(R.id.p1_r1);
        mP2Farm = (TextView) findViewById(R.id.p2_r1);
        /*
      grain points fields
     */
        mP1Grain = (TextView) findViewById(R.id.p1_r2);
        mP2Grain = (TextView) findViewById(R.id.p2_r2);
        /*
      veggie points fields
     */
        mP1Veggie = (TextView) findViewById(R.id.p1_r3);
        mP2Veggie = (TextView) findViewById(R.id.p2_r3);
        /*
        ruby points fields
         */
        mP1Ruby = (TextView) findViewById(R.id.p1_r4);
        mP2Ruby = (TextView) findViewById(R.id.p2_r4);
        /*
        dwarf points fields
         */
        mP1Dwarf = (TextView) findViewById(R.id.p1_r5);
        mP2Dwarf = (TextView) findViewById(R.id.p2_r5);
        /*
        unused space points fields
         */
        mP1UnusedSpace = (TextView) findViewById(R.id.p1_r6);
        mP2UnusedSpace = (TextView) findViewById(R.id.p2_r6);
        /*
        furnishing points fields
         */
        mP1Furnishing = (TextView) findViewById(R.id.p1_r7);
        mP2Furnishing = (TextView) findViewById(R.id.p2_r7);
        /*
        bonus points fields
         */
        mP1Bonus = (TextView) findViewById(R.id.p1_r8);
        mP2Bonus = (TextView) findViewById(R.id.p2_r8);
        /*
        gold points fields
        */
        mP1Gold = (TextView) findViewById(R.id.p1_r9);
        mP2Gold = (TextView) findViewById(R.id.p2_r9);
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

            player1FarmAnimalPoints = savedInstanceState.getDouble(STATE_P1FARM);
            player2FarmAnimalPoints = savedInstanceState.getDouble(STATE_P2FARM);

            player1GrainPoints = savedInstanceState.getDouble(STATE_P1GRAIN);
            player2GrainPoints = savedInstanceState.getDouble(STATE_P2GRAIN);

            player1VeggiePoints = savedInstanceState.getDouble(STATE_P1VEGGIE);
            player2VeggiePoints = savedInstanceState.getDouble(STATE_P2VEGGIE);

            player1RubyPoints = savedInstanceState.getDouble(STATE_P1RUBY);
            player2RubyPoints = savedInstanceState.getDouble(STATE_P2RUBY);

            player1DwarfPoints = savedInstanceState.getDouble(STATE_P1DWARF);
            player2DwarfPoints = savedInstanceState.getDouble(STATE_P2DWARF);

            player1UnusedSpacePoints = savedInstanceState.getDouble(STATE_P1UNUSEDSPACE);
            player2UnusedSpacePoints = savedInstanceState.getDouble(STATE_P2UNUSEDSPACE);

            player1FurnishingPoints = savedInstanceState.getDouble(STATE_P1FURNISHING);
            player2FurnishingPoints = savedInstanceState.getDouble(STATE_P2FURNISHING);

            player1BonusPoints = savedInstanceState.getDouble(STATE_P1BONUS);
            player2BonusPoints = savedInstanceState.getDouble(STATE_P2BONUS);

            player1GoldPoints = savedInstanceState.getDouble(STATE_P1GOLD);
            player2GoldPoints = savedInstanceState.getDouble(STATE_P2GOLD);

            player1Score = savedInstanceState.getDouble(STATE_P1SCORE);
            player2Score = savedInstanceState.getDouble(STATE_P2SCORE);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        //displayDatabaseInfo();
    }

    //Get player data and save into database
    private void insertCavernaPlayerData() {

        // Create database helper
        GameDbHelper mDbHelper = new GameDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Remove the table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + GamesContract.PlayerEntry.TABLE_CAVERNA);
        mDbHelper.CreateTable(GameDbHelper.GameType.Caverna, db);

        // Create a ContentValues object where column names are the keys,
        // and player attributes from the editor are the values.
        ContentValues valuesA = new ContentValues();

        p1NameString = mP1NameEditText.getText().toString();

        //player 1
        valuesA.put(GamesContract.PlayerEntry.COLUMN_NAME, p1NameString);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_PER_FARM_ANIMAL_DOG, player1FarmAnimalPoints);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_PER_GRAIN, player1GrainPoints);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_PER_VEG, player1VeggiePoints);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_PER_RUBY, player1RubyPoints);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_PER_DWARF, player1DwarfPoints);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_PER_UNUSED_SPACE, player1UnusedSpacePoints);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_FURNISHINGS_PASTURES_MINES, player1FurnishingPoints);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_PARLORS_STORAGES_CHAMBERS, player1BonusPoints);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_GOLD_COINS_BEGGING_MARKERS, player1GoldPoints);
        valuesA.put(GamesContract.PlayerEntry.COLUMN_TOTAL, player1Score);

        ContentValues valuesB = new ContentValues();

        p2NameString = mP2NameEditText.getText().toString();

        //player 2
        valuesB.put(GamesContract.PlayerEntry.COLUMN_NAME, p2NameString);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_PER_FARM_ANIMAL_DOG, player2FarmAnimalPoints);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_PER_GRAIN, player2GrainPoints);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_PER_VEG, player2VeggiePoints);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_PER_RUBY, player2RubyPoints);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_PER_DWARF, player2DwarfPoints);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_PER_UNUSED_SPACE, player2UnusedSpacePoints);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_FURNISHINGS_PASTURES_MINES, player2FurnishingPoints);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_PARLORS_STORAGES_CHAMBERS, player2BonusPoints);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_GOLD_COINS_BEGGING_MARKERS, player2GoldPoints);
        valuesB.put(GamesContract.PlayerEntry.COLUMN_TOTAL, player2Score);

        addRowToCavernaTable(valuesA, db);
        addRowToCavernaTable(valuesB, db);

        //displayDatabaseInfo();
    }

    // Show a toast message depending on whether or not the insertion was successful
    private void addRowToCavernaTable(ContentValues values, SQLiteDatabase db) {
        long newRowID = db.insert(GamesContract.PlayerEntry.TABLE_CAVERNA, null, values);

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

    //for  P1 farm animals
    public void give1FarmAnimalPointP1(View view) {
        player1FarmAnimalPoints = player1FarmAnimalPoints + 1;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    public void take2FarmAnimalPointsP1(View view) {
        player1FarmAnimalPoints = player1FarmAnimalPoints - 2;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    //for  P2 farm animals
    public void give1FarmAnimalPointP2(View view) {
        player2FarmAnimalPoints = player2FarmAnimalPoints + 1;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    public void take2FarmAnimalPointsP2(View view) {
        player2FarmAnimalPoints = player2FarmAnimalPoints - 2;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    //for P1 grain
    public void giveHalfPointP1(View view) {
        player1GrainPoints = player1GrainPoints + 0.5;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    public void takeHalfPointP1(View view) {
        player1GrainPoints = player1GrainPoints - 0.5;

        if (player1GrainPoints <= 0)
            player1GrainPoints = 0;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    //for P2 grain
    public void giveHalfPointP2(View view) {
        player2GrainPoints = player2GrainPoints + 0.5;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    public void takeHalfPointP2(View view) {
        player2GrainPoints = player2GrainPoints - 0.5;

        if (player2GrainPoints <= 0)
            player2GrainPoints = 0;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    // for P1 veggies
    public void give1VeggiePointP1(View view) {
        player1VeggiePoints = player1VeggiePoints + 1;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    public void take1VeggiePointP1(View view) {
        player1VeggiePoints = player1VeggiePoints - 1;

        if (player1VeggiePoints <= 0)
            player1VeggiePoints = 0;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);

    }

    // for P2 veggies
    public void give1VeggiePointP2(View view) {
        player2VeggiePoints = player2VeggiePoints + 1;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    public void take1VeggiePointP2(View view) {
        player2VeggiePoints = player2VeggiePoints - 1;

        if (player2VeggiePoints <= 0)
            player2VeggiePoints = 0;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);

    }

    // for P1 Rubies
    public void give1RubyPointP1(View view) {
        player1RubyPoints = player1RubyPoints + 1;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    public void take1RubyPointP1(View view) {
        player1RubyPoints = player1RubyPoints - 1;

        if (player1RubyPoints <= 0)
            player1RubyPoints = 0;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    // for P2 Rubies
    public void give1RubyPointP2(View view) {
        player2RubyPoints = player2RubyPoints + 1;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    public void take1RubyPointP2(View view) {
        player2RubyPoints = player2RubyPoints - 1;

        if (player2RubyPoints <= 0)
            player2RubyPoints = 0;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    // for P1 Dwarves
    public void give1DwarfPointP1(View view) {
        player1DwarfPoints = player1DwarfPoints + 1;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    public void take1DwarfPointP1(View view) {
        player1DwarfPoints = player1DwarfPoints - 1;

        if (player1DwarfPoints <= 0)
            player1DwarfPoints = 0;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    // for P2 Dwarves
    public void give1DwarfPointP2(View view) {
        player2DwarfPoints = player2DwarfPoints + 1;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    public void take1DwarfPointP2(View view) {
        player2DwarfPoints = player2DwarfPoints - 1;

        if (player2DwarfPoints <= 0)
            player2DwarfPoints = 0;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    // for P1 Unused Space
    public void give1SpacePointP1(View view) {
        player1UnusedSpacePoints = player1UnusedSpacePoints + 1;

        if (player1UnusedSpacePoints >= 0)
            player1UnusedSpacePoints = 0;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    public void take1SpacePointP1(View view) {
        player1UnusedSpacePoints = player1UnusedSpacePoints - 1;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    // for P2 Unused Space
    public void give1SpacePointP2(View view) {
        player2UnusedSpacePoints = player2UnusedSpacePoints + 1;

        if (player2UnusedSpacePoints >= 0)
            player2UnusedSpacePoints = 0;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    public void take1SpacePointP2(View view) {
        player2UnusedSpacePoints = player2UnusedSpacePoints - 1;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    // for P1 Furnishings
    public void give1FurnishingPointP1(View view) {
        player1FurnishingPoints = player1FurnishingPoints + 1;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    public void take1FurnishingPointP1(View view) {
        player1FurnishingPoints = player1FurnishingPoints - 1;

        if (player1FurnishingPoints <= 0)
            player1FurnishingPoints = 0;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    // for P2 Furnishings
    public void give1FurnishingPointP2(View view) {
        player1FurnishingPoints = player1FurnishingPoints + 1;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    public void take1FurnishingPointP2(View view) {
        player2FurnishingPoints = player2FurnishingPoints - 1;

        if (player2FurnishingPoints <= 0)
            player2FurnishingPoints = 0;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    // for P1 Bonus
    public void give1BonusPointP1(View view) {
        player1BonusPoints = player1BonusPoints + 1;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    public void take1BonusPointP1(View view) {
        player1BonusPoints = player1BonusPoints - 1;

        if (player1BonusPoints <= 0)
            player1BonusPoints = 0;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    // for P2 Bonus
    public void give1BonusPointP2(View view) {
        player2BonusPoints = player2BonusPoints + 1;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    public void take1BonusPointP2(View view) {
        player2BonusPoints = player2BonusPoints - 1;

        if (player2BonusPoints <= 0)
            player2BonusPoints = 0;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    // for P1 Gold and Begging markers
    public void give1GoldPointP1(View view) {
        player1GoldPoints = player1GoldPoints + 1;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    public void take1GoldPointP1(View view) {
        player1GoldPoints = player1GoldPoints - 1;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
    }

    // for P2 Gold and Begging markers
    public void give1GoldPointP2(View view) {
        player2GoldPoints = player2GoldPoints + 1;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    public void take1GoldPointP2(View view) {
        player2GoldPoints = player2GoldPoints - 1;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    //to reset scores
    public void resetPlayerScores(View view) {
        player1FarmAnimalPoints = 0;
        player1GrainPoints = 0;
        player1VeggiePoints = 0;
        player1RubyPoints = 0;
        player1DwarfPoints = 0;
        player1UnusedSpacePoints = 0;
        player1FurnishingPoints = 0;
        player1BonusPoints = 0;
        player1GoldPoints = 0;
        player1Score = 0;

        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);

        player2FarmAnimalPoints = 0;
        player2GrainPoints = 0;
        player2VeggiePoints = 0;
        player2RubyPoints = 0;
        player2DwarfPoints = 0;
        player2UnusedSpacePoints = 0;
        player2FurnishingPoints = 0;
        player2BonusPoints = 0;
        player2GoldPoints = 0;
        player2Score = 0;

        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);
    }

    //to save user data
    public void savePlayerData(View view) {
        insertCavernaPlayerData();
    }

    public void loadPlayerData() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ");
        stringBuilder.append(GamesContract.PlayerEntry.TABLE_CAVERNA);

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
        player1FarmAnimalPoints = cursor.getDouble(2);
        player1GrainPoints = cursor.getDouble(3);
        player1VeggiePoints = cursor.getDouble(4);
        player1RubyPoints = cursor.getDouble(5);
        player1DwarfPoints = cursor.getDouble(6);
        player1UnusedSpacePoints = cursor.getDouble(7);
        player1FurnishingPoints = cursor.getDouble(8);
        player1BonusPoints = cursor.getDouble(9);
        player1GoldPoints = cursor.getDouble(10);
        player1Score = cursor.getDouble(11);
        displayForPlayer1(player1FarmAnimalPoints, player1GrainPoints, player1VeggiePoints,
                player1RubyPoints, player1DwarfPoints, player1UnusedSpacePoints, player1FurnishingPoints,
                player1BonusPoints, player1GoldPoints, player1Score);
        cursor.moveToNext();

        p2NameString = cursor.getString(1);
        player2FarmAnimalPoints = cursor.getDouble(2);
        player2GrainPoints = cursor.getDouble(3);
        player2VeggiePoints = cursor.getDouble(4);
        player2RubyPoints = cursor.getDouble(5);
        player2DwarfPoints = cursor.getDouble(6);
        player2UnusedSpacePoints = cursor.getDouble(7);
        player2FurnishingPoints = cursor.getDouble(8);
        player2BonusPoints = cursor.getDouble(9);
        player2GoldPoints = cursor.getDouble(10);
        player2Score = cursor.getDouble(11);
        displayForPlayer2(player2FarmAnimalPoints, player2GrainPoints, player2VeggiePoints,
                player2RubyPoints, player2DwarfPoints, player2UnusedSpacePoints, player2FurnishingPoints,
                player2BonusPoints, player2GoldPoints, player2Score);

        cursor.close();
    }

    //method which displays the given scores for player 1
    public void displayForPlayer1(String p1Name, double p1Farm, double p1Grain, double p1Veggie,
                                  double p1Ruby, double p1Dwarf, double p1Unused, double p1Furnishing,
                                  double p1Bonus, double p1Gold, double p1Score) {

        EditText p1NameView = (EditText) findViewById(R.id.player1);
        p1NameView.setText(p1Name);

        displayForPlayer1(p1Farm, p1Grain, p1Veggie, p1Ruby, p1Dwarf, p1Unused, p1Furnishing,
                p1Bonus, p1Gold, p1Score);
    }

    //method which displays the given scores for player 2
    public void displayForPlayer2(String p2Name, double p2Farm, double p2Grain, double p2Veggie,
                                  double p2Ruby, double p2Dwarf, double p2Unused, double p2Furnishing,
                                  double p2Bonus, double p2Gold, double p2Score) {

        EditText p2NameView = (EditText) findViewById(R.id.player2);
        p2NameView.setText(p2Name);

        displayForPlayer2(p2Farm, p2Grain, p2Veggie, p2Ruby, p2Dwarf, p2Unused, p2Furnishing,
                p2Bonus, p2Gold, p2Score);
    }

    public void displayForPlayer1(double p1Farm, double p1Grain, double p1Veggie, double p1Ruby,
                                  double p1Dwarf, double p1Unused, double p1Furnishing, double p1Bonus,
                                  double p1Gold, double p1Score) {

        p1Score = Math.ceil(p1Score + p1Farm + p1Grain + p1Veggie + p1Ruby + p1Dwarf + p1Unused
                + p1Furnishing + p1Bonus + p1Gold);

        TextView p1FarmView = (TextView) findViewById(R.id.p1_r1);
        p1FarmView.setText(String.valueOf(p1Farm));

        TextView p1GrainView = (TextView) findViewById(R.id.p1_r2);
        p1GrainView.setText(String.valueOf(p1Grain));

        TextView p1VeggieView = (TextView) findViewById(R.id.p1_r3);
        p1VeggieView.setText(String.valueOf(p1Veggie));

        TextView p1RubyView = (TextView) findViewById(R.id.p1_r4);
        p1RubyView.setText(String.valueOf(p1Ruby));

        TextView p1DwarfView = (TextView) findViewById(R.id.p1_r5);
        p1DwarfView.setText(String.valueOf(p1Dwarf));

        TextView p1UnusedView = (TextView) findViewById(R.id.p1_r6);
        p1UnusedView.setText(String.valueOf(p1Unused));

        TextView p1FurnishingView = (TextView) findViewById(R.id.p1_r7);
        p1FurnishingView.setText(String.valueOf(p1Furnishing));

        TextView p1BonusView = (TextView) findViewById(R.id.p1_r8);
        p1BonusView.setText(String.valueOf(p1Bonus));

        TextView p1GoldView = (TextView) findViewById(R.id.p1_r9);
        p1GoldView.setText(String.valueOf(p1Gold));

        TextView p1ScoreView = (TextView) findViewById(R.id.player_1_score);
        p1ScoreView.setText(String.valueOf(p1Score));
    }

    public void displayForPlayer2(double p2Farm, double p2Grain, double p2Veggie, double p2Ruby,
                                  double p2Dwarf, double p2Unused, double p2Furnishing, double p2Bonus,
                                  double p2Gold, double p2Score) {

        p2Score = Math.ceil(p2Score + p2Farm + p2Grain + p2Veggie + p2Ruby + p2Dwarf + p2Unused
                + p2Furnishing + p2Bonus + p2Gold);

        TextView p2FarmView = (TextView) findViewById(R.id.p2_r1);
        p2FarmView.setText(String.valueOf(p2Farm));

        TextView p2GrainView = (TextView) findViewById(R.id.p2_r2);
        p2GrainView.setText(String.valueOf(p2Grain));

        TextView p2VeggieView = (TextView) findViewById(R.id.p2_r3);
        p2VeggieView.setText(String.valueOf(p2Veggie));

        TextView p2RubyView = (TextView) findViewById(R.id.p2_r4);
        p2RubyView.setText(String.valueOf(p2Ruby));

        TextView p2DwarfView = (TextView) findViewById(R.id.p2_r5);
        p2DwarfView.setText(String.valueOf(p2Dwarf));

        TextView p2UnusedView = (TextView) findViewById(R.id.p2_r6);
        p2UnusedView.setText(String.valueOf(p2Unused));

        TextView p2FurnishingView = (TextView) findViewById(R.id.p2_r7);
        p2FurnishingView.setText(String.valueOf(p2Furnishing));

        TextView p2BonusView = (TextView) findViewById(R.id.p2_r8);
        p2BonusView.setText(String.valueOf(p2Bonus));

        TextView p2GoldView = (TextView) findViewById(R.id.p2_r9);
        p2GoldView.setText(String.valueOf(p2Gold));

        TextView p2ScoreView = (TextView) findViewById(R.id.player_2_score);
        p2ScoreView.setText(String.valueOf(p2Score));
    }


}
