package com.example.edgar.amigooculto;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by estagiario35102 on 24/11/2017.
 */

public class SorteioActivity extends AppCompatActivity{

    private List<String> pessoas;

    private TextView nome;
    private TextView fim;

    private LinearLayout resultado;

    private Button sortear;
    private Button engano;
    private Button ok;

    private int index;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sorteio);

        pessoas = MainActivity.pessoas;

        nome = (TextView) findViewById(R.id.nome);
        fim = (TextView) findViewById(R.id.fim);
        resultado = (LinearLayout) findViewById(R.id.resultado);

        sortear = (Button) findViewById(R.id.sortear);
        engano = (Button) findViewById(R.id.engano);
        ok = (Button) findViewById(R.id.ok);

        sortear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engano.setVisibility(View.VISIBLE);
                if(!pessoas.isEmpty()) {
                    resultado.setVisibility(View.VISIBLE);
                    index = new Random().nextInt(pessoas.size());
                    nome.setText(pessoas.get(index));
                }
                else {
                    fim.setVisibility(View.VISIBLE);
                    resultado.setVisibility(View.GONE);
                    sortear.setVisibility(View.GONE);
                    engano.setVisibility(View.GONE);
                }
            }
        });

        engano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pessoas.isEmpty()) {
                    index = new Random().nextInt(pessoas.size());
                    nome.setText(pessoas.get(index));
                }
                else {
                    fim.setVisibility(View.VISIBLE);
                    resultado.setVisibility(View.GONE);
                    sortear.setVisibility(View.GONE);
                    engano.setVisibility(View.GONE);
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engano.setVisibility(View.GONE);
                resultado.setVisibility(View.GONE);
                if(!pessoas.isEmpty())
                    pessoas.remove(index);
                else {
                    fim.setVisibility(View.VISIBLE);
                    resultado.setVisibility(View.GONE);
                    sortear.setVisibility(View.GONE);
                    engano.setVisibility(View.GONE);
                }
            }
        });


    }
}
