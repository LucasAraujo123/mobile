package com.example.prof803.bdapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PessoaDao {

    PessoaDBHelper pessoaDB;
    SQLiteDatabase db;

    public PessoaDao(Context context) {
        pessoaDB = new PessoaDBHelper(context);
        db = pessoaDB.getWritableDatabase();
    }

    public void inserir(Pessoa pessoa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", pessoa.getNome());
        cv.put("telefone", pessoa.getTelefone());
        cv.put("idade", pessoa.getIdade());
        db.insert("pessoa", null, cv);
    }

    public void excluir(Pessoa pessoa) {
        db.delete(
                "pessoa",
                "nome = ?",
                new String[] { pessoa.getNome() }
        );
    }


    public List<Pessoa> listar() {

        Cursor c = db.query(
                "pessoa",
                new String[] { "nome", "telefone", "idade"},
                null,
                null,
                null,
                null,
                "nome DESC"
        );

        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        while (c.moveToNext()) {
            Pessoa p = new Pessoa(
                    c.getString(0), c.getString(1),c.getInt(2));
            pessoas.add(p);
        }
        c.close();
        return pessoas;
    }

}
