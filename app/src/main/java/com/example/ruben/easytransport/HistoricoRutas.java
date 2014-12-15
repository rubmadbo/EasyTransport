package com.example.ruben.easytransport;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import Helpers.LoginSesion;
import Objetos.Ruta;
import Objetos.Usuario;


public class HistoricoRutas extends Fragment {

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

    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //si te sale error aqui ir a buil.gradle el de la carpera mas externa y donde pone minSdkVersion pones 9
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final View rootView = inflater.inflate(R.layout.fragment_historico_rutas, container, false);

        //Insercion de las rutas en el listView
        ListView li = (ListView) rootView.findViewById(R.id.LVHistoricas);

        JavaPHPMySQL bd = new JavaPHPMySQL();
        ArrayList<Ruta> listaRuta=null;
        try {
            listaRuta = bd.getRutasByUserId(getUsuarioLog().getIdUsuario());
        }catch (Exception e){e.printStackTrace();}
        if(listaRuta.isEmpty())
            Toast.makeText(getActivity(), "No tienes rutas caducadas", Toast.LENGTH_LONG).show();
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


                Intent intent = new Intent(getActivity().getBaseContext(),ListarAcuerdosRuta.class);
                int idRuta = rutaSelected.getIdRuta();
                intent.putExtra("IdRuta", idRuta);
                getActivity().startActivity(intent);



               /* Bundle arguments = new Bundle();
                arguments.putString("idRuta", Id_ruta);
                ListarAcuerdosTransportista fragment = ListarAcuerdosTransportista.newInstance(arguments);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                //esto es lo q hace q se muestre encima de loq habia en vez de cargar el xml
                ft.replace(android.R.id.content, fragment);
                ft.commit();*/

            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}