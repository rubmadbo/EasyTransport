package com.example.ruben.easytransport;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import Objetos.Ruta;


public class RutasFavoritas extends Fragment {

    private OnFragmentInteractionListener mListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_vista_rutas, container, false);
        Button boton = (Button) rootView.findViewById(R.id.buttonAnyadir);

        //Insercion de las rutas en el listView
        ImageView aceptar=(ImageView) rootView.findViewById(R.id.Estado_acuerdo);
        ImageView rechazado=(ImageView) rootView.findViewById(R.id.Estado_acuerdo1);
        ImageView espera=(ImageView) rootView.findViewById(R.id.Estado_acuerdo2);
        //Poner un imagen dentro un ImageView
        aceptar.setImageResource(R.drawable.acuerdo);
        rechazado.setImageResource(R.drawable.close);
        espera.setImageResource(R.drawable.espera);

        ListView li = (ListView) rootView.findViewById(R.id.listViewRutas);
        final ArrayList<Ruta> listaRuta = new ArrayList();

        //Crear una lista de rutas con el resultado de la consulta
         JavaPHPMySQL db = new JavaPHPMySQL();
        //String json = db.getRutasFavoritas(idUsuario);
       // listaRuta = db.mostrarAllRutas(json);


        /*Tenemos que poner el campo de la base de datos "estadoAcuerdo"

           if(estadoAcuerdo.equals("Aceptado"))
        {
            aceptar.setVisibility(View.VISIBLE);
            rechazado.setVisibility(View.INVISIBLE);
            espera.setVisibility(View.INVISIBLE);
        }
        if(estadoAcuerdo.equals("Rechazado"))
        {
            aceptar.setVisibility(View.INVISIBLE);
            rechazado.setVisibility(View.VISIBLE);
            espera.setVisibility(View.INVISIBLE);
        }
        if(estadoAcuerdo.equals("Rechazado"))
        {
            aceptar.setVisibility(View.INVISIBLE);
            rechazado.setVisibility(View.INVISIBLE);
            espera.setVisibility(View.VISIBLE);
        }
        */

        //Inserción en el ListView
        ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(RutasFavoritas.this.getActivity(),android.R.layout.simple_list_item_1, listaRuta);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);

        li.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                final AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
                b.setIcon(android.R.drawable.ic_dialog_alert);
                b.setMessage("¿Desea borrar la ruta seleccionada de favoritos?");
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Ruta rutaSelected = listaRuta.get(position);
                        //db.borrarRutafavorita(rutaSelected.getIdRuta());
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

                b.show();
                return true;

            }
        });

        //Cerramos la conexion
        /*cur.close();
        admin.close();*/

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              // Intent intent = new Intent(RutasFavoritas.this.getActivity(),HistoricoDeRutas.class);//Historico de rutas por crear
              //  startActivity(intent);
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
