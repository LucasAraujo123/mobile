package com.example.prof803.mapaapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalDBHelper extends SQLiteOpenHelper {

    private final static String CREATE_LOCAL =
            "CREATE TABLE local(id TEXT PRIMARY KEY," +
                    "endereco TEXT, latitude REAL, longitude REAL)";
    private final static String DROP_LOCAL =
            "DROP TABLE IF EXISTS local";

    private final static String DATABASE_NAME = "pessoa.db";
    private final static int DATABASE_VERSION = 5;

    public LocalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_LOCAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_LOCAL);
        sqLiteDatabase.execSQL(CREATE_LOCAL);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}