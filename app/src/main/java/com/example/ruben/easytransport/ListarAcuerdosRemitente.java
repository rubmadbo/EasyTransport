package com.example.ruben.easytransport;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.util.ArrayList;

import Objetos.Acuerdo;
import Objetos.Usuario;


public class ListarAcuerdosRemitente extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listar_acuerdos, container, false);
        ListView li = (ListView) rootView.findViewById(R.id.listViewAcuerdos);

        JavaPHPMySQL db = new JavaPHPMySQL();
        ArrayList<Acuerdo> listaAcuerdos;
        Usuario UsuarioLogeado = Sessions.getUsuarioLogeado(); // habría que cargarlo de la session de userlogeado en caso que se quieran ver los acuerdos por usuario

        listaAcuerdos = db.getAcuerdosByUserId(UsuarioLogeado.getIdUsuario());

        ArrayAdapter<Acuerdo> adap = new ArrayAdapter<Acuerdo>(ListarAcuerdosRemitente.this.getActivity(), android.R.layout.simple_list_item_1, listaAcuerdos);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);

        return rootView;
    }



}
