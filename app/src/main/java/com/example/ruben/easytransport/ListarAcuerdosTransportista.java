package com.example.ruben.easytransport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Objetos.Acuerdo;
import Objetos.Ruta;


public class ListarAcuerdosTransportista extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.fragment_listar_acuerdos, container, false);
        ListView li = (ListView) rootView.findViewById(R.id.listViewAcuerdos);

        JavaPHPMySQL db = new JavaPHPMySQL();
        ArrayList<Acuerdo> listaAcuerdos;
        int UsuarioLogeado = 3; // habría que cargarlo de la session de userlogeado en caso que se quieran ver los acuerdos por usuario

       listaAcuerdos = db.getAcuerdosByTransId(UsuarioLogeado);

            ArrayAdapter<Acuerdo> adap = new ArrayAdapter<Acuerdo>(ListarAcuerdosTransportista.this.getActivity(), android.R.layout.simple_list_item_1, listaAcuerdos);
            adap.notifyDataSetChanged();
            li.setAdapter(adap);

        final ArrayList<Acuerdo> finalListaAcuerdo = listaAcuerdos;

        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                final AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
                b.setIcon(android.R.drawable.ic_dialog_alert);
                b.setMessage("¿Revisar acuerdo?");
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        Acuerdo acuerdoSelected = finalListaAcuerdo.get(position);

                        if(acuerdoSelected.getEstado().equals("pendiente"))   {

                            Intent intent = new Intent(getActivity().getBaseContext(),AcuerdoPropuesto.class);
                            int idRuta = acuerdoSelected.getIdAcuerdo();
                            intent.putExtra("IdRuta", idRuta);
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
                            startActivity(intent);
                        }
                        else Toast.makeText(getActivity(), "Ya has aceptado/rechazado este acuerdo.", Toast.LENGTH_SHORT).show();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                b.show();
            }
        });


        return rootView;
    }

}

