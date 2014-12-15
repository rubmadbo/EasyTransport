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
import java.util.HashMap;

import Helpers.LoginSesion;
import Objetos.Ruta;
import Objetos.Usuario;


public class VistaRutas extends Fragment {

    LoginSesion session;
    public Usuario getUsuarioLog(){


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

            //si te sale error aqui ir a buil.gradle el de la carpera mas externa y donde pone minSdkVersion pones 9
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            final View rootView = inflater.inflate(R.layout.fragment_vista_rutas, container, false);
            Button boton = (Button) rootView.findViewById(R.id.buttonAnyadir);

            //Insercion de las rutas en el listView
            ListView li = (ListView) rootView.findViewById(R.id.listViewRutas);
            //ArrayList<Ruta> listaRuta = new ArrayList();
            Ruta ruta;
            JavaPHPMySQL bd = new JavaPHPMySQL();

       /* String json = bd.getAllRutas();
        ArrayList<Ruta> listaRuta =  bd.mostrarAllRutas(json);*/

            ArrayList<Ruta> listaRuta = new ArrayList<Ruta>();

            try{
                listaRuta = bd.getRutasByUserId(getUsuarioLog().getIdUsuario());
            }catch (Exception e){
                e.printStackTrace();
            }


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
                String sFechaRuta = listaRuta.get(i).getFecha();
                Ruta rutaActual = listaRuta.get(i);
                try {
                    Date fechaRuta = formatter.parse(sFechaRuta);
                    if(fechaActual.getTime() <= fechaRuta.getTime()){
                        rutasActuales.add(rutaActual);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            //Inserción en el ListView

            ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(VistaRutas.this.getActivity(),android.R.layout.simple_list_item_1, rutasActuales);
            adap.notifyDataSetChanged();
            li.setAdapter(adap);

            final ArrayList<Ruta> finalListaRuta = rutasActuales;
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

                            if(bd.getAcuerdosByRutaId(rutaSelected.getIdRuta()).size() == 0)   {
                                bd.borrarRuta(rutaSelected.getIdRuta());
                                refresh();
                                Toast.makeText(getActivity(), "La ruta ha sido borrada correctamente", Toast.LENGTH_SHORT).show();
                            }
                            else Toast.makeText(getActivity(), "No se puede eliminar una ruta con un acuerdo asociado", Toast.LENGTH_SHORT).show();
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
                    throwActivity();
                }
            });


        return rootView;

    }

    public void throwActivity() {
        // start activity
        Intent intent = new Intent();
        intent.setClass(getActivity(), GestionDeRutas.class);
        int requestCode = 1;
        startActivityForResult(intent,requestCode);
    }

    // callback
    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {

        refresh();

    }



    public void refresh(){

        // Create new fragment and transaction
        Fragment newFragment = new VistaRutas();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(this.getId(), newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


}