package com.example.ruben.easytransport.util;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ruben.easytransport.JavaPHPMySQL;
import com.example.ruben.easytransport.R;

import java.util.ArrayList;

import Objetos.Acuerdo;

import static java.lang.Integer.parseInt;

public class ListarAcuerdosRuta extends ActionBarActivity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_acuerdos_ruta);

        ArrayList<Acuerdo> listaAcuerdos = new ArrayList<Acuerdo>();

        lista = (ListView) findViewById(R.id.listAcuerdosRuta);
        JavaPHPMySQL db = new JavaPHPMySQL();
        Intent intent = getIntent();
        final int rutaId= intent.getIntExtra("IdRuta",0);
        System.out.println("El id de ruta es -------------------> " +rutaId);
        listaAcuerdos = db.getAcuerdosByRutaId(rutaId);


        ArrayAdapter<Acuerdo> adap = new ArrayAdapter<Acuerdo>(ListarAcuerdosRuta.this.getApplicationContext(), android.R.layout.simple_list_item_1, listaAcuerdos);
        adap.notifyDataSetChanged();
        lista.setAdapter(adap);
    }




}
