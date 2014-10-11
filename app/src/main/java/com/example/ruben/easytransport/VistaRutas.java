package com.example.ruben.easytransport;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import Objetos.Ruta;


public class VistaRutas extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_vista_rutas, container, false);
        Button boton = (Button) rootView.findViewById(R.id.buttonAnyadir);

        //Insercion de las rutas en el listView
        ListView li = (ListView) rootView.findViewById(R.id.listViewRutas);
        ArrayList<Ruta> listaRuta = new ArrayList();
        Ruta ruta;

        //Conexión a la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(VistaRutas.this.getActivity(), "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //Consulta a la base de datos
        Cursor cur = db.rawQuery("SELECT * FROM Ruta", null);

        if(cur.moveToFirst()){
            do{
                ruta = new Ruta(cur.getInt(0),cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4),
                        cur.getString(5),cur.getString(6),cur.getInt(7));
                listaRuta.add(ruta);
            }while(cur.moveToNext());
        }

        //Inserción en el ListView
        ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(VistaRutas.this.getActivity(),android.R.layout.simple_list_item_1, listaRuta);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);

        //Cerramos la conexion
        cur.close();
        admin.close();

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(VistaRutas.this.getActivity(),GestionDeRutas.class);
                startActivity(intent);
            }
        });
    return rootView;
    }

}