package com.example.prof803.mapaapplication;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends Activity {

    private EditText campoEndereco;
    private TextView coordenadas;
    private Button botaoCadastrar;
    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        campoEndereco = findViewById(R.id.campoEndereco);
        coordenadas = findViewById(R.id.coordenadas);
        botaoCadastrar = findViewById(R.id.botaoCadastrar);
        botaoVoltar = findViewById(R.id.botaoVoltar);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String endereco = campoEndereco.getText().toString();
                Local local = getCoordenadas(endereco);
                if (local != null) {
                    coordenadas.setText(
                            local.getLatitude() + "\n" +local.getLongitude());

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("locais");

                    String chave = reference.push().getKey();
                    local.setId(chave);

                    reference.child(chave).setValue(local);

                    LocalDao localDao = new LocalDao(getApplicationContext());
                    localDao.inserir(local);
                } else {
                    coordenadas.setText("Nenhum local encontrado!");
                }

            }
        });

    }

    public Local getCoordenadas(String endereco) {
        try {
            List<Address> lista = new ArrayList<Address>();
            Geocoder geocoder = new Geocoder(getApplicationContext());

            lista = geocoder.getFromLocationName(endereco, 5);
            if (lista.size() > 0) {
                Address address = lista.get(0);
                Local local = new Local(endereco,
                        endereco, address.getLatitude(), address.getLongitude());
                return local;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
