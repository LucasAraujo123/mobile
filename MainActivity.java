package com.example.prof803.traducao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView textoFrase;
    private EditText campoTraducao;
    private Button botaoEnviar;
    private TextView numTentativas;

    private TextoTraducao frases [] = {
            new TextoTraducao("House", "Casa"),
            new TextoTraducao("Dog", "Cachorro"),
            new TextoTraducao("Cat", "Gato"),
            new TextoTraducao("Car", "Carro")
    };

    private int posicao = 0;

    private int acertos = 0;
    private int erros = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoFrase = findViewById(R.id.textFrase);
        campoTraducao = findViewById(R.id.campoTraducao);
        botaoEnviar = findViewById(R.id.botaoEnviar);
        numTentativas = findViewById(R.id.numTentativas);

        textoFrase.setText(frases[posicao].getTexto());

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicao > frases.length - 1) {
                    Intent intent = new Intent(
                            getApplicationContext(),
                            ResultadoActivity.class
                    );
                    intent.putExtra(
                            "mensagem", "Parabéns! Você ganhou!");
                    intent.putExtra("acertos", acertos);
                    intent.putExtra("erros", erros);
                    startActivity(intent);
                } else {

                    int num = Integer.parseInt(
                            numTentativas.getText().toString());
                    if (num == 0) {
                        Intent intent = new Intent(
                                getApplicationContext(),
                                ResultadoActivity.class
                        );
                        intent.putExtra(
                                "mensagem", "Tente novamente!");
                        intent.putExtra("acertos", acertos);
                        intent.putExtra("erros", erros);
                        startActivity(intent);
                    } else {
                        String tentativa = campoTraducao.getText().toString();
                        String traducao = frases[posicao].getTraducao();
                        if (tentativa.equalsIgnoreCase(traducao)) {
                            acertos++;
                            campoTraducao.setText("");
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Parabéns, você acertou!",
                                    Toast.LENGTH_LONG
                            ).show();

                            posicao++;

                            if (!(posicao > frases.length - 1)) {
                                textoFrase.setText(frases[posicao].getTexto());
                            }

                        } else {
                            erros++;
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Errou!",
                                    Toast.LENGTH_LONG
                            ).show();

                            num--;
                            numTentativas.setText(String.valueOf(num));

                        }
                    }
                }

            }
        });

    }
}
