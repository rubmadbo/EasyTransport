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
import java.util.HashMap;

import Helpers.LoginSesion;
import Objetos.Acuerdo;
import Objetos.Usuario;


public class ListarAcuerdosRemitente extends Fragment {

    public Usuario getUsuarioLog(){
        LoginSesion session;

        Usuario user = new Usuario(0,"","","","tuviejaa",null,null,null,"");
        String email;

        session = new LoginSesion(getActivity().getApplicationContext());

        HashMap<String, String> usuario = session.getUserDetails();

        // email
        email = usuario.get(LoginSesion.KEY_EMAIL);
        // CON ESTO OBTINES EL EMAIL
        try{user=JavaPHPMySQL.getUsuarioByEmail(email);}
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_listar_acuerdos, container, false);
        ListView li = (ListView) rootView.findViewById(R.id.listViewAcuerdos);

        JavaPHPMySQL db = new JavaPHPMySQL();
        ArrayList<Acuerdo> listaAcuerdos;


        listaAcuerdos = db.getAcuerdosByUserId(getUsuarioLog().getIdUsuario());
        if (listaAcuerdos.isEmpty())
            Toast.makeText(getActivity(), "No has enviado ningún acuerdo", Toast.LENGTH_LONG).show();

        ArrayAdapter<Acuerdo> adap = new ArrayAdapter<Acuerdo>(ListarAcuerdosRemitente.this.getActivity(), android.R.layout.simple_list_item_1, listaAcuerdos);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);

        return rootView;
    }



}
