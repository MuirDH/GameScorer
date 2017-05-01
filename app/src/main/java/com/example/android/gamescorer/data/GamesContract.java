package com.example.android.gamescorer.data;

import android.provider.BaseColumns;

/**
 * GameScorer Created by Muir on 22/02/2017.
 */

public final class GamesContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private GamesContract() {
    }

    /**
     * Inner class that defines constant values for the games database table.
     * Each entry in the table represents a single player.
     */
    public static final class PlayerEntry implements BaseColumns {

        // names of database tables
        public final static String TABLE_ASCENSION = "ascension";
        public final static String TABLE_CAVERNA = "caverna";
        public final static String TABLE_ZOMBIEDICE = "zombie_dice";
        public final static String TABLE_SEVENWONDERS = "sevenwonders";

        // Common Column Names
        public final static String COLUMN_NAME = "name";
        public final static String COLUMN_TOTAL = "total";
        public final static String _ID = BaseColumns._ID;

        // Ascension Table - Column Names
        public final static String COLUMN_WHITE = "white";
        public final static String COLUMN_RED = "red";
        public final static String COLUMN_CARD = "card";

        // Caverna Table - Column Names
        public final static String COLUMN_PER_FARM_ANIMAL_DOG = "per_farm_animal_and_dog";
        public final static String COLUMN_PER_MISSING_ANIMAL_TYPE = "per_missing_animal_type";
        public final static String COLUMN_PER_GRAIN = "per_grain";
        public final static String COLUMN_PER_VEG = "per_vegetable";
        public final static String COLUMN_PER_RUBY = "per_ruby";
        public final static String COLUMN_PER_DWARF = "per_dwarf";
        public final static String COLUMN_PER_UNUSED_SPACE = "per_unused_space";
        public final static String COLUMN_FURNISHINGS_PASTURES_MINES = "furnishing_tiles_pastures_and_mines";
        public final static String COLUMN_PARLORS_STORAGES_CHAMBERS = "parlors_storages_and_chambers";
        public final static String COLUMN_GOLD_COINS_BEGGING_MARKERS = "gold_coins_and_begging_markers";

        // Zombie Dice Table - Column Names

        public final static String COLUMN_BRAINS = "brains";

        // 7 Wonders Table - Column Names
        public final static String COLUMN_MILITARY_CONFLICTS = "military_conflicts";
        public final static String COLUMN_TREASURY = "treasury";
        public final static String COLUMN_WONDER = "wonder";
        public final static String COLUMN_CIVILIAN_STRUCTURES = "civilian_structures";
        public final static String COLUMN_IDENTICAL_SCIENTIFIC_STRUCTURES = "identical_scientific_structures";
        public final static String COLUMN_DIFFERENT_SCIENTIFIC_STRUCTURES = "different_scientific_structures";
        public final static String COLUMN_COMMERCIAL_STRUCTURES = "commercial_structures";
        public final static String COLUMN_GUILDS = "guilds";


    }
}
