package com.example.ruben.easytransport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import Helpers.LoginSesion;
import Objetos.Acuerdo;
import Objetos.Ruta;
import Objetos.Usuario;


public class ListarAcuerdosTransportista extends Fragment {

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.fragment_listar_acuerdos, container, false);
        ListView li = (ListView) rootView.findViewById(R.id.listViewAcuerdos);

        JavaPHPMySQL db = new JavaPHPMySQL();
        ArrayList<Acuerdo> listaAcuerdos;
        int UsuarioLogeado = getUsuarioLog().getIdUsuario(); // habría que cargarlo de la session de userlogeado en caso que se quieran ver los acuerdos por usuario

       listaAcuerdos = db.getAcuerdosByTransId(UsuarioLogeado);
        if(listaAcuerdos.isEmpty())
            Toast.makeText(getActivity(), "No tienes ningún acuerdo asociado a tu cuenta", Toast.LENGTH_LONG).show();


            ArrayAdapter<Acuerdo> adap = new ArrayAdapter<Acuerdo>(ListarAcuerdosTransportista.this.getActivity(), android.R.layout.simple_list_item_1, listaAcuerdos);
            adap.notifyDataSetChanged();
            li.setAdapter(adap);

        final ArrayList<Acuerdo> finalListaAcuerdo = listaAcuerdos;

        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                Acuerdo acuerdoSelected = finalListaAcuerdo.get(position);

                if(acuerdoSelected.getEstado().equals("pendiente"))   {

                    Intent intent = new Intent();
                    intent.setClass(getActivity(), AcuerdoPropuesto.class);
                    int requestCode = 1;
                    int IdAcuerdo = acuerdoSelected.getIdAcuerdo();
                    intent.putExtra("IdAcuerdo", IdAcuerdo);
                    String remitente = "Juanito";//Fran: No me pilla el remitente, hay que revisarlo.
                    intent.putExtra("Remitente", remitente);
                    String origen_ruta = acuerdoSelected.getRuta().getOrigen();
                    intent.putExtra("Origen", origen_ruta);
                    String destino_ruta = acuerdoSelected.getRuta().getDestino();
                    intent.putExtra("Destino", destino_ruta);
                    String fecha = acuerdoSelected.getRuta().getFecha();
                    intent.putExtra("Fecha", fecha);
                    String dinero = acuerdoSelected.getPrecio().toString();
                    intent.putExtra("Dinero" ,dinero);
                    String comentario = acuerdoSelected.getComentario();
                    intent.putExtra("Comentario", comentario);
                    startActivityForResult(intent,requestCode);
                }
                else Toast.makeText(getActivity(), "Ya has aceptado/rechazado este acuerdo.", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }

   // callback
    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {

        refresh();

    }



    public void refresh(){

        // Create new fragment and transaction
        Fragment newFragment = new ListarAcuerdosTransportista();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(this.getId(), newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

}

