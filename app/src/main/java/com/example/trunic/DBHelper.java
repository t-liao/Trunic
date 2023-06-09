package com.example.trunic;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "RuneDB";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Runes";
    public static final String ID_COL = "ID";
    public static final String PHONEMES_COL = "Phoneme";
    public static final String SYMBOL_COL = "Symbol";
    public static final String EXAMPLE_COL = "Examples";
    public static final String EDGE_COL = "Edges";
    public static final String IS_VOWEL_COL = "IsVowel";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PHONEMES_COL + " TEXT,"
                + SYMBOL_COL + " TEXT,"
                + EXAMPLE_COL + " TEXT,"
                + EDGE_COL + " TEXT,"
                + IS_VOWEL_COL + " BOOLEAN)";

        db.execSQL(query);

        insert("A",     "æ",    "back, sad",    "ABC", true);
        insert("AR",    "ɑ:",   "arm, large",   "ABDE", true);
        insert("AH",    "ɒ",    "swan, box",    "BC", true);
        insert("AY",    "eɪ",   "bay, game",    "B", true);
        insert("E",     "ɛ",    "end, pet",     "CDE", true);
        insert("EE",    "i:",   "bee, team",    "BCDE", true);
        insert("EER",   "ɪəʳ",  "near, here",   "BCE", true);
        insert("EH",    "ə",    "the, about",   "AB", true);
        insert("ERE",   "eəʳ",  "air, vary",    "CE", true);
        insert("I",     "ɪ",    "bit, rich",    "DE", true);
        insert("IE",    "aɪ",   "guy, life",    "A", true);
        insert("IR",    "ɜ:ʳ",  "bird, work",   "ACDE", true);
        insert("OH",    "oʊ",   "toe, over",    "ABCDE", true);
        insert("OI",    "ɔɪ",   "toy, avoid",   "D", true);
        insert("OO",    "u:",   "too, june",    "ABCD", true);
        insert("OU",    "ʊ",    "wolf, good",   "CD", true);
        insert("OW",    "aʊ",   "how, hour",    "E", true);
        insert("ORE",   "ʊəʳ",  "your, cure",   "ABCE", true);

        insert("B",   "b",  "bug, boss",   "GK", false);
        insert("CH",   "tʃ",  "chat, catch",   "HJ", false);
        insert("D",   "d",  "dog, add",   "GIK", false);
        insert("F",   "f",  "fox, fail",   "FIJ", false);
        insert("G",   "g",  "gun, bag",   "FJK", false);
        insert("H",   "h",  "hop, house",   "GJK", false);
        insert("J",   "dʒ",  "jam, judge",   "GI", false);
        insert("K",   "k",  "cat, skip",   "FGK", false);
        insert("L",   "l",  "live, leaf",   "GJ", false);
        insert("M",   "m",  "man, mime",   "IK", false);
        insert("N",   "n",  "net, nun",   "HIK", false);
        insert("NG",   "ŋ",  "rink, sing",   "FGHIJK", false);
        insert("P",   "p",  "poppy, pip",   "FJ", false);
        insert("R",   "r",  "run, borrow",   "FGJ", false);
        insert("S",   "s",  "sit, sass",   "FGIJ", false);
        insert("SH",   "ʃ",  "shut, shoe",   "FHIJK", false);
        insert("T",   "t",  "tunic, stop",   "FHJ", false);
        insert("TH",   "θ",  "think, bath",   "FGHJ", false);
        insert("TH",   "ð",  "this, bathe",   "GIJK", false);
        insert("V",   "v",  "vine, five",   "GHK", false);
        insert("W",   "w",  "wit, wow",   "FH", false);
        insert("Y",   "j",  "you, yes",   "GHJ", false);
        insert("Z",   "z",  "zit, zoo",   "GHJK", false);
        insert("ZH",   "ʒ",  "vision, treasure",   "FGHIK", false);

    }

    public void insert(String phonemes, String symbol, String example, String edge, Boolean isVowel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PHONEMES_COL, phonemes);
        contentValues.put(SYMBOL_COL, symbol);
        contentValues.put(EXAMPLE_COL, example);
        contentValues.put(EDGE_COL, edge);
        contentValues.put(IS_VOWEL_COL, isVowel);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
