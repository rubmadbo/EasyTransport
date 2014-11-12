package com.example.ruben.easytransport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Objetos.Ruta;


public class VistaRutas extends Fragment {
    final Calendar c = Calendar.getInstance();
    Date fecha = new Date();
    private int year;
    private int month;
    private int day;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //si te sale error aqui ir a buil.gradle el de la carpera mas externa y donde pone minSdkVersion pones 9
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final View rootView = inflater.inflate(R.layout.activity_vista_rutas, container, false);
        Button boton = (Button) rootView.findViewById(R.id.buttonAnyadir);

        //Insercion de las rutas en el listView
        ListView li = (ListView) rootView.findViewById(R.id.listViewRutas);
        //ArrayList<Ruta> listaRuta = new ArrayList();
        Ruta ruta;
        JavaPHPMySQL bd = new JavaPHPMySQL();
        String json = bd.getAllRutas();
        ArrayList<Ruta> listaRuta =  bd.mostrarAllRutas(json);
        ArrayList<Ruta> listaRutasActuales = new ArrayList<Ruta>();


        //Aqui agregar solo las rutas que no se hayan pasado.


        //Inserción en el ListView

        ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(VistaRutas.this.getActivity(),android.R.layout.simple_list_item_1, listaRuta);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);

        final ArrayList<Ruta> finalListaRuta = listaRuta;
        li.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                final AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
                b.setIcon(android.R.drawable.ic_dialog_alert);
                b.setMessage("¿Desea borrar la ruta seleccionada?");
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        JavaPHPMySQL bd = new JavaPHPMySQL();
                        Ruta rutaSelected = finalListaRuta.get(position);
                        //IMPORTANTE: tener en cuenta que no se puede borrar ruta si tiene acuerdo asociado!
                        //getAcuerdosByRuta esta sin acabar
                     if(bd.getAcuerdosByRutaId(rutaSelected.getIdRuta()) == null)   {
                        bd.borrarRuta(rutaSelected.getIdRuta());}
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

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(VistaRutas.this.getActivity(),GestionDeRutas.class);
                startActivity(intent);
            }
        });
    return rootView;
    }

    //metodo VIEJO
    public void borrarRuta(int rutaid) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(VistaRutas.this.getActivity(), "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String selectQuery = "SELECT * FROM Acuerdo WHERE ruta="+rutaid+"";
        Cursor cursor = db.rawQuery(selectQuery, null);
        //devuelve false si no hay ninguno que cumple la query
        if (cursor.moveToFirst()){
            Toast.makeText(VistaRutas.this.getActivity(), "No se puede borrar una ruta asociada a un Acuerdo", Toast.LENGTH_LONG).show();
        }else {db.execSQL("DELETE FROM Ruta WHERE idRuta="+rutaid+"");
            Toast.makeText(VistaRutas.this.getActivity(), "La ruta ha sido borrada", Toast.LENGTH_LONG).show();

        }
    }
}