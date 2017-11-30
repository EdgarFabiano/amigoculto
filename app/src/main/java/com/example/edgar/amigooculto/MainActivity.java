package com.example.edgar.amigooculto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AlertDialog alertaIncluir;
    AlertDialog alertaExcluir;

    public static List<String> pessoas;

    private EditText nome;

    Context context;

    private Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        pessoas = new ArrayList<>();
        pessoas.add("Beatriz Chiarelli");
        pessoas.add("Bruno Gois");
        pessoas.add("Caio Oliveira");
        pessoas.add("Daniel Medeiros");
        pessoas.add("Danilo Santos");
        pessoas.add("Edgar Fabiano");
        pessoas.add("Gabriel Vinhaes");
        pessoas.add("Luciana Ribeiro");
        pessoas.add("Thaís Pereira");

        iniciar = (Button) findViewById(R.id.iniciar);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SorteioActivity.class);
                startActivity(intent);
            }
        });

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pessoas);

        ListView lv = (ListView) findViewById(R.id.lista);
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Adicionar participante");
                builder.setIcon(R.drawable.logo);

                LayoutInflater li = getLayoutInflater();
                View dialogLayout = li.inflate(R.layout.alerta, null);

                nome = (EditText) dialogLayout.findViewById(R.id.nome);

                dialogLayout.findViewById(R.id.cancelar).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        alertaIncluir.dismiss();
                    }
                });

                dialogLayout.findViewById(R.id.adicionar).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        if (!nome.getText().toString().isEmpty()) {
                            pessoas.add(nome.getText().toString());
                            adapter.notifyDataSetChanged();
                            alertaIncluir.dismiss();
                        } else {
                            nome.setError("Preencha o nome");
                        }
                    }
                });

                builder.setView(dialogLayout);
                alertaIncluir = builder.create();
                alertaIncluir.show();

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                //define o titulo
                builder.setTitle("Confirmar exclusão de participante");
                //define a mensagem
                builder.setMessage(pessoas.get(position));
                //define um botão como positivo
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        pessoas.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                //define um botão como negativo.
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        alertaExcluir.dismiss();
                    }
                });
                alertaExcluir = builder.create();
                alertaExcluir.show();
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
