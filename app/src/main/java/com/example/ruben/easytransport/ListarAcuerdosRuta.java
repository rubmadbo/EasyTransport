package com.example.ruben.easytransport;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Objetos.Acuerdo;

import static java.lang.Integer.parseInt;

public class ListarAcuerdosRuta extends ActionBarActivity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_acuerdos_ruta);

        ArrayList<Acuerdo> listaAcuerdos;

        lista = (ListView) findViewById(R.id.listAcuerdosRuta);
        JavaPHPMySQL db = new JavaPHPMySQL();

        Intent intent = getIntent();
        final int rutaId = intent.getIntExtra("IdRuta",0);
        listaAcuerdos = db.getAcuerdosByRutaId(rutaId);


        ArrayAdapter<Acuerdo> adap = new ArrayAdapter<Acuerdo>(ListarAcuerdosRuta.this, android.R.layout.simple_list_item_1, listaAcuerdos);
        adap.notifyDataSetChanged();
        lista.setAdapter(adap);
    }




}
