package com.example.prof803.bdapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PessoaDBHelper extends SQLiteOpenHelper {

    private final static String CREATE_PESSOA =
            "CREATE TABLE pessoa(nome TEXT, " +
                    "telefone TEXT, idade INTEGER)";
    private final static String DROP_PESSOA =
            "DROP TABLE IF EXISTS pessoa";

    private final static String DATABASE_NAME = "pessoa.db";
    private final static int DATABASE_VERSION = 1;

    public PessoaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PESSOA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_PESSOA);
        sqLiteDatabase.execSQL(CREATE_PESSOA);
    }

}