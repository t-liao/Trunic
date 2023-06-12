package com.example.trunic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PHONEMES_COL + " TEXT,"
                + SYMBOL_COL + " TEXT,"
                + EXAMPLE_COL + " TEXT,"
                + EDGE_COL + " TEXT,"
                + IS_VOWEL_COL + " BOOLEAN)";

        db.execSQL(query);

        insert(db,"A",     "æ",    "back, sad",    "edgeABC", true);
        insert(db,"AR",    "ɑ:",   "arm, large",   "edgeABDE", true);
        insert(db,"AH",    "ɒ",    "swan, box",    "edgeBC", true);
        insert(db,"AY",    "eɪ",   "bay, game",    "edgeB", true);
        insert(db,"E",     "ɛ",    "end, pet",     "edgeCDE", true);
        insert(db,"EE",    "i:",   "bee, team",    "edgeBCDE", true);
        insert(db,"EER",   "ɪəʳ",  "near, here",   "edgeBCE", true);
        insert(db,"EH",    "ə",    "the, about",   "edgeAB", true);
        insert(db,"ERE",   "eəʳ",  "air, vary",    "edgeCE", true);
        insert(db,"I",     "ɪ",    "bit, rich",    "edgeDE", true);
        insert(db,"IE",    "aɪ",   "guy, life",    "edgeA", true);
        insert(db,"IR",    "ɜ:ʳ",  "bird, work",   "edgeACDE", true);
        insert(db,"OH",    "oʊ",   "toe, over",    "edgeABCDE", true);
        insert(db,"OI",    "ɔɪ",   "toy, avoid",   "edgeD", true);
        insert(db,"OO",    "u:",   "too, june",    "edgeABCD", true);
        insert(db,"OU",    "ʊ",    "wolf, good",   "edgeCD", true);
        insert(db,"OW",    "aʊ",   "how, hour",    "edgeE", true);
        insert(db,"ORE",   "ʊəʳ",  "your, cure",   "edgeABCE", true);
        insert(db, " ", " "," ","edge",true);

        insert(db,"B",   "b",  "bug, boss",   "edgeGK", false);
        insert(db,"CH",   "tʃ",  "chat, catch",   "edgeHJ", false);
        insert(db,"D",   "d",  "dog, add",   "edgeGIK", false);
        insert(db,"F",   "f",  "fox, fail",   "edgeFIJ", false);
        insert(db,"G",   "g",  "gun, bag",   "edgeFJK", false);
        insert(db,"H",   "h",  "hop, house",   "edgeGJK", false);
        insert(db,"J",   "dʒ",  "jam, judge",   "edgeGI", false);
        insert(db,"K",   "k",  "cat, skip",   "edgeFGK", false);
        insert(db,"L",   "l",  "live, leaf",   "edgeGJ", false);
        insert(db,"M",   "m",  "man, mime",   "edgeIK", false);
        insert(db,"N",   "n",  "net, nun",   "edgeHIK", false);
        insert(db,"NG",   "ŋ",  "rink, sing",   "edgeFGHIJK", false);
        insert(db,"P",   "p",  "poppy, pip",   "edgeFJ", false);
        insert(db,"R",   "r",  "run, borrow",   "edgeFGJ", false);
        insert(db,"S",   "s",  "sit, sass",   "edgeFGIJ", false);
        insert(db,"SH",   "ʃ",  "shut, shoe",   "edgeFHIJK", false);
        insert(db,"T",   "t",  "tunic, stop",   "edgeFHJ", false);
        insert(db,"TH",   "θ",  "think, bath",   "edgeFGHJ", false);
        insert(db,"TH",   "ð",  "this, bathe",   "edgeGIJK", false);
        insert(db,"V",   "v",  "vine, five",   "edgeGHK", false);
        insert(db,"W",   "w",  "wit, wow",   "edgeFH", false);
        insert(db,"Y",   "j",  "you, yes",   "edgeGHJ", false);
        insert(db,"Z",   "z",  "zit, zoo",   "edgeGHJK", false);
        insert(db,"ZH",   "ʒ",  "vision, treasure",   "edgeFGHIK", false);
        insert(db, " ", " "," ","edge",false);
    }

    public void insert(SQLiteDatabase db, String phonemes, String symbol, String example, String edge, Boolean isVowel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PHONEMES_COL, phonemes);
        contentValues.put(SYMBOL_COL, symbol);
        contentValues.put(EXAMPLE_COL, example);
        contentValues.put(EDGE_COL, edge);
        contentValues.put(IS_VOWEL_COL, isVowel);

        db.insert(TABLE_NAME, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public String[] runeInfo(String edges, int isVowel){
        String[] info = new String[3];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + PHONEMES_COL + "," + SYMBOL_COL + "," + EXAMPLE_COL +
                " FROM " + TABLE_NAME + " WHERE " + EDGE_COL + " = '" + edges + "' AND " +
                IS_VOWEL_COL + " = " + isVowel, null);


        if (c.moveToFirst()){
            info[0] = c.getString(0);
            info[1] = c.getString(1);
            info[2] = c.getString(2);
        } else {
            return null;
        }
        c.close();
        db.close();

        return info;
    }

    public String[] findRandRune() {
        String[] info = new String[3];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + PHONEMES_COL + "," + SYMBOL_COL + "," + EXAMPLE_COL +
                " FROM " + TABLE_NAME + " WHERE " + PHONEMES_COL + " != ' ' ORDER BY RANDOM() LIMIT 1", null);

        if (c.moveToFirst()){
            info[0] = c.getString(0);
            info[1] = c.getString(1);
            info[2] = c.getString(2);
        } else {
            return null;
        }
        c.close();
        db.close();

        return info;
    }
}
