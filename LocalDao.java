package com.example.prof803.mapaapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LocalDao {

    LocalDBHelper localDB;
    SQLiteDatabase db;

    public LocalDao(Context context) {
        localDB = new LocalDBHelper(context);
        db = localDB.getWritableDatabase();
    }

    public void inserir(Local local) {
        ContentValues cv = new ContentValues();
        cv.put("id", local.getId());
        cv.put("endereco", local.getEndereco());
        cv.put("latitude", local.getLatitude());
        cv.put("longitude", local.getLongitude());
        db.insert("local", null, cv);
    }

    public void excluir(Local local) {
        db.delete(
                "local",
                "id = ?",
                new String[] { local.getId() }
        );
    }

    public void atualizar(Local local) {
        ContentValues cv = new ContentValues();
        cv.put("endereco", local.getEndereco());
        cv.put("latitude", local.getLatitude());
        cv.put("longitude", local.getLongitude());
        db.update(
                "local",
                cv,
                "id = ?",
                new String[] { local.getId() }
        );
    }

    public List<Local> listar(String id) {

        String selection = null;
        String [] selectionArgs = null;
        if (id != null) {
            selection = "id = ?";
            selectionArgs = new String[] { id };
        }

        Cursor c = db.query(
                "local",
                new String[] { "id", "endereco", "latitude", "longitude"},
                selection,
                selectionArgs,
                null,
                null,
                "id DESC"
        );

        ArrayList<Local> locais = new ArrayList<Local>();
        while (c.moveToNext()) {
            Local p = new Local(
                    c.getString(0),  // id
                    c.getString(1),  // nome
                    c.getDouble(2), // telefone
                    c.getDouble(3) //idade
            );
            locais.add(p);
        }
        c.close();
        return locais;
    }

}
