package com.example.prof803.traducao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultadoActivity extends Activity {

    private TextView textoResultado;
    private Button botaoNovoTeste;
    private TextView textoErros;
    private TextView textoAcertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        textoResultado = findViewById(R.id.textoResultado);
        botaoNovoTeste = findViewById(R.id.botaoNovoTeste);
        textoErros = findViewById(R.id.textoErros);
        textoAcertos = findViewById(R.id.textoAcertos);

        Intent intent = getIntent();
        String resultado = intent.getStringExtra("mensagem");
        int acertos = intent.getIntExtra("acertos", 0);
        int erros = intent.getIntExtra("erros", 0);

        textoResultado.setText(resultado);
        textoErros.setText("Erros: " + erros);
        textoAcertos.setText("Acertos: " + acertos);

        botaoNovoTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVolta = new Intent(
                        getApplicationContext(),
                        MainActivity.class
                );
                startActivity(intentVolta);
            }
        });


    }
}
