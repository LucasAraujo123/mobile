package com.example.prof803.bdapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lista);

        PessoaDao pessoaDao =
                new PessoaDao(getApplicationContext());
        pessoaDao.inserir(
                new Pessoa("xyz", "123",30));


        ArrayAdapter<Pessoa> items =
                new ArrayAdapter<Pessoa>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        pessoaDao.listar()
                );
        lista.setAdapter(items);




    }
}
