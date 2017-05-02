package com.example.android.gamescorer.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * GameScorer Created by Muir on 22/02/2017.
 */

public class GameDbHelper extends SQLiteOpenHelper {

    // Logcat tag
    public static final String LOG_TAG = GameDbHelper.class.getSimpleName();
    /**
     * Name of the database file
     **/
    private static final String DATABASE_NAME = "games.db";
    /**
     * Database version. If you change the database schema, you must increment the database version
     */
    private static final int DATABASE_VERSION = 1;
    private String SQL_CREATE_ASCENSION_TABLE = "CREATE TABLE " + GamesContract.PlayerEntry.TABLE_ASCENSION
            + " ("
            + GamesContract.PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GamesContract.PlayerEntry.COLUMN_NAME + " TEXT NOT NULL, "
            + GamesContract.PlayerEntry.COLUMN_WHITE + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_RED + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_CARD + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_TOTAL + " INTEGER NOT NULL DEFAULT 0);";
    //Create a string that contains the SQL statement to create the caverna table
    private String SQL_CREATE_CAVERNA_TABLE = "CREATE TABLE " + GamesContract.PlayerEntry.TABLE_CAVERNA
            + " ("
            + GamesContract.PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GamesContract.PlayerEntry.COLUMN_NAME + " TEXT NOT NULL, "
            + GamesContract.PlayerEntry.COLUMN_PER_FARM_ANIMAL_DOG + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_PER_MISSING_ANIMAL_TYPE + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_PER_GRAIN + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_PER_VEG + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_PER_RUBY + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_PER_DWARF + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_PER_UNUSED_SPACE + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_FURNISHINGS_PASTURES_MINES + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_PARLORS_STORAGES_CHAMBERS + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_GOLD_COINS_BEGGING_MARKERS + " REAL NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_TOTAL + " REAL NOT NULL DEFAULT 0);";

    //Create a string that contains the SQL statement to create the ascension table
    //Create a string that contains the SQL statement to create the zombie dice table
    private String SQL_CREATE_ZOMBIEDICE_TABLE = "CREATE TABLE " + GamesContract.PlayerEntry.TABLE_ZOMBIEDICE
            + " ("
            + GamesContract.PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GamesContract.PlayerEntry.COLUMN_NAME + " TEXT NOT NULL, "
            + GamesContract.PlayerEntry.COLUMN_BRAINS + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_TOTAL + " INTEGER NOT NULL DEFAULT 0);";
    //Create a string that contains the SQL statement to create the 7 Wonders table
    private String SQL_CREATE_SEVENWONDERS_TABLE = "CREATE TABLE " + GamesContract.PlayerEntry.TABLE_SEVENWONDERS
            + " ("
            + GamesContract.PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + GamesContract.PlayerEntry.COLUMN_NAME + " TEXT NOT NULL, "
            + GamesContract.PlayerEntry.COLUMN_MILITARY_CONFLICTS + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_TREASURY + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_WONDER + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_CIVILIAN_STRUCTURES + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_IDENTICAL_SCIENTIFIC_STRUCTURES + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_DIFFERENT_SCIENTIFIC_STRUCTURES + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_COMMERCIAL_STRUCTURES + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_GUILDS + " INTEGER NOT NULL DEFAULT 0, "
            + GamesContract.PlayerEntry.COLUMN_TOTAL + " INTEGER NOT NULL DEFAULT 0);";

    /**
     * Constructs a new instance of {@link GameDbHelper}.
     *
     * @param context of the app
     */
    public GameDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void CreateTable(GameType gameType, SQLiteDatabase db) {
        switch (gameType) {
            case Ascension:
                db.execSQL(SQL_CREATE_ASCENSION_TABLE);
                break;
            case Caverna:
                db.execSQL(SQL_CREATE_CAVERNA_TABLE);
                break;
            case ZombieDice:
                db.execSQL(SQL_CREATE_ZOMBIEDICE_TABLE);
                break;
            case SevenWonders:
                db.execSQL(SQL_CREATE_SEVENWONDERS_TABLE);
                break;

            default:
                throw new IllegalArgumentException(" Create table not implemented for this game ");


        }
    }

    //called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Execute the SQL statements
        db.execSQL(SQL_CREATE_ASCENSION_TABLE);
        db.execSQL(SQL_CREATE_CAVERNA_TABLE);
        db.execSQL(SQL_CREATE_ZOMBIEDICE_TABLE);
        db.execSQL(SQL_CREATE_SEVENWONDERS_TABLE);

    }

    //called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + GamesContract.PlayerEntry.TABLE_ASCENSION);
        db.execSQL("DROP TABLE IF EXISTS " + GamesContract.PlayerEntry.TABLE_CAVERNA);
        db.execSQL("DROP TABLE IF EXISTS " + GamesContract.PlayerEntry.TABLE_ZOMBIEDICE);
        db.execSQL("DROP TABLE IF EXISTS " + GamesContract.PlayerEntry.TABLE_SEVENWONDERS);

        // create new tables
        onCreate(db);

    }

    public enum GameType {
        Ascension,
        ZombieDice,
        Caverna,
        SevenWonders
    }
}
