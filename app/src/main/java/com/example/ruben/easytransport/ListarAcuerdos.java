package com.example.ruben.easytransport;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ruben.easytransport.AdminSQLiteOpenHelper;
import com.example.ruben.easytransport.GestionDeRutas;
import com.example.ruben.easytransport.R;

import java.util.ArrayList;
import java.util.List;

import Objetos.Acuerdo;
import Objetos.Ruta;


public class ListarAcuerdos extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.fragment_listar_acuerdos, container, false);
        ListView li = (ListView) rootView.findViewById(R.id.listViewAcuerdos);

        Acuerdo acuerdo;

        //Conexión a la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(ListarAcuerdos.this.getActivity(), "administracion", null, 1);
        List<Acuerdo> listaAcuerdos = admin.getAcuerdos();
        //Inserción en el ListView
        ArrayAdapter<Acuerdo> adap = new ArrayAdapter<Acuerdo>(ListarAcuerdos.this.getActivity(),android.R.layout.simple_list_item_1, listaAcuerdos);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);

        //Cerramos la conexion
        admin.close();

       /* boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(VistaRutas.this.getActivity(),GestionDeRutas.class);
                startActivity(intent);
            }
        });*/
        return rootView;
    }

}

