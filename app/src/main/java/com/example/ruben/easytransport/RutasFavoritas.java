package com.example.ruben.easytransport;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Objetos.Ruta;


public class RutasFavoritas extends Fragment {

    private OnFragmentInteractionListener mListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.activity_vista_rutas, container, false);
        Button boton = (Button) rootView.findViewById(R.id.buttonAnyadir);

        //Insercion de las rutas en el listView
        ListView li = (ListView) rootView.findViewById(R.id.listViewRutas);
        final ArrayList<Ruta> listaRuta = new ArrayList();
        Ruta ruta;

        //Conexión a la base de datos
        /*AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(VistaRutas.this.getActivity(), "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();*/

        //Consulta a la base de datos
        /*Cursor cur = db.rawQuery("SELECT * FROM Ruta", null);*/

        //Crear una lista de rutas con el resultado de la consulta
        /*if(cur.moveToFirst()){
            do{
                ruta = new Ruta(cur.getInt(0),cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4),
                        cur.getString(5),cur.getString(6),cur.getInt(7));
                listaRuta.add(ruta);
            }while(cur.moveToNext());
        }*/

        //Inserción en el ListView
        ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(RutasFavoritas.this.getActivity(),android.R.layout.simple_list_item_1, listaRuta);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);

        li.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                final AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
                b.setIcon(android.R.drawable.ic_dialog_alert);
                b.setMessage("¿Desea borrar la ruta seleccionada?");
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(view.getContext(), "administracion", null, 1);
                        Ruta rutaSelected = listaRuta.get(position);
                        borrarRuta(rutaSelected.getId());
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
                Intent intent = new Intent(RutasFavoritas.this.getActivity(),HistoricoDeRutas.class);//Historico de rutas por crear
                startActivity(intent);
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
