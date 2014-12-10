package com.example.ruben.easytransport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Objetos.Ruta;


public class HistoricoRutas extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //si te sale error aqui ir a buil.gradle el de la carpera mas externa y donde pone minSdkVersion pones 9
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final View rootView = inflater.inflate(R.layout.fragment_historico_rutas, container, false);

        //Insercion de las rutas en el listView
        ListView li = (ListView) rootView.findViewById(R.id.LVHistoricas);

        JavaPHPMySQL bd = new JavaPHPMySQL();
        String json = bd.getAllRutas();
        ArrayList<Ruta> listaRuta =  bd.mostrarAllRutas(json);
        ArrayList<Ruta> rutasActuales = new ArrayList<Ruta>();

        final Calendar c = Calendar.getInstance();
        int year,month,day;

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        GregorianCalendar calendar = new GregorianCalendar(year,month,day);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = calendar.getTime();

        for (int i=0; i < listaRuta.size();i++){
            String FechaRuta = listaRuta.get(i).getFecha();
            Ruta rutaActual = listaRuta.get(i);
            try {
                Date fechaRuta = formatter.parse(FechaRuta);
                if(fechaActual.getTime() > fechaRuta.getTime()){
                    rutasActuales.add(rutaActual);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //Inserción en el ListView

        ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(HistoricoRutas.this.getActivity(),android.R.layout.simple_list_item_1, rutasActuales);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);

        final ArrayList<Ruta> finalListaRuta = rutasActuales;

        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                Ruta rutaSelected = finalListaRuta.get(position);
                //Intent intent = new Intent(getActivity(), ListarAcuerdos.class);
                String Id_ruta =String.valueOf(rutaSelected.getIdRuta());



               // Bundle arguments = new Bundle();
               // arguments.putString("idRuta", Id_ruta);
                //ListarAcuerdos fragment = ListarAcuerdos.newInstance(arguments);
                //FragmentTransaction ft = getFragmentManager().beginTransaction();
                //esto es lo q hace q se muestre encima de loq habia en vez de cargar el xml
               // ft.replace(android.R.id.content, fragment);
              //  ft.commit();

            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}