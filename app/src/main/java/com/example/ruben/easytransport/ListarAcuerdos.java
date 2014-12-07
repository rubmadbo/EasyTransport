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

    public static ListarAcuerdos newInstance(Bundle arguments){
        ListarAcuerdos f = new ListarAcuerdos();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.fragment_listar_acuerdos, container, false);
        ListView li = (ListView) rootView.findViewById(R.id.listViewAcuerdos);

        int idRuta=0;
        JavaPHPMySQL db = new JavaPHPMySQL();
        ArrayList<Acuerdo> listaAcuerdos;
        int UserId = 2; // habría que cargarlo de la session de userlogeado en caso que se quieran ver los acuerdos por usuario

        //jdcc, cuando viene de historico de rutas, lista los acuerdos de esa ruta
        Bundle args = getArguments();
        if (args  != null && args.containsKey("idRuta"))
            idRuta =Integer.parseInt(args.getString("idRuta"));

       /* if (getArguments() != null) {
            Bundle b = getActivity().getIntent().getExtras();
            idRuta =Integer.parseInt(b.getString("idRuta"));
        }*/

        if(idRuta!=0) {
            listaAcuerdos = db.getAcuerdosByRutaId(idRuta);

        }else {
            listaAcuerdos = db.getAcuerdosByUserId(UserId);

        }
            ArrayAdapter<Acuerdo> adap = new ArrayAdapter<Acuerdo>(ListarAcuerdos.this.getActivity(), android.R.layout.simple_list_item_1, listaAcuerdos);
            adap.notifyDataSetChanged();
            li.setAdapter(adap);

        return rootView;
    }

}

