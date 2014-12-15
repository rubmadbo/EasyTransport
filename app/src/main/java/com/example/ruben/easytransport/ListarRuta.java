package com.example.ruben.easytransport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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


public class ListarRuta extends Activity {



    public Usuario getUsuarioLog(){
        LoginSesion session;

        Usuario user = new Usuario(0,"","","","tuviejaa",null,null,null,"");
        String email;

        session = new LoginSesion(getApplicationContext());

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


    private Button boton;
    private EditText origen;
    private EditText destino;
    private EditText fechas;
    private String dest;
    private String orig;
    private String fecha;
    private int idUsuarioLogeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ruta);
        boton=(Button)findViewById(R.id.button_volver);
        origen=(EditText)findViewById(R.id.origen);
        destino=(EditText)findViewById(R.id.dest);
        fechas = (EditText)findViewById(R.id.fechasEntre);

        Intent intent = getIntent();
        String message1 = intent.getStringExtra("Ori");
        String message = intent.getStringExtra("Des");
        String data1= intent.getStringExtra("Dat1");
        String data2= intent.getStringExtra("Dat2");
        origen.setText(message1, TextView.BufferType.EDITABLE);
        destino.setText(message, TextView.BufferType.EDITABLE);
        fechas.setText(data1 + " - " + data2);

        //Insercion de las rutas en el listView
        ListView li = (ListView)findViewById(R.id.listView_rutas);
        ArrayList<Ruta> listaRuta = new ArrayList();

        JavaPHPMySQL bd = new JavaPHPMySQL();
        String json = bd.getAllRutas();
        ArrayList<Ruta> listaRutas =  bd.mostrarAllRutas(json);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for(int i=0; i<listaRutas.size();i++) {
            Ruta rutaActual = listaRutas.get(i);
            String sFechaRuta = rutaActual.getFecha();
            try {
                Date fechaRuta = formatter.parse(sFechaRuta);
                Date fechaDesde = formatter.parse(data1);
                Date fechaHasta = formatter.parse(data2);
                if (listaRutas.get(i).getDestino().equalsIgnoreCase(message) && listaRutas.get(i).getOrigen().equalsIgnoreCase(message1)
                        && fechaRuta.getTime() >= fechaDesde.getTime() && fechaRuta.getTime() <= fechaHasta.getTime() && listaRutas.get(i).getTransportista().getIdUsuario()!= getUsuarioLog().getIdUsuario()) {
                    listaRuta.add(rutaActual);

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        if(listaRuta.isEmpty())
            Toast.makeText(this, "No hay ninguna ruta que se ajuste a la busqueda especificada", Toast.LENGTH_LONG).show();

        final ArrayList<Ruta> final_list = listaRuta;

        ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(ListarRuta.this,android.R.layout.simple_list_item_1, listaRuta);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Ruta rutaSelected = final_list.get(position);
                Intent intent = new Intent(ListarRuta.this, Anadir_acuerdo.class);
                String origen_ruta = rutaSelected.getOrigen();
                intent.putExtra("Origen", origen_ruta);
                String destino_ruta = rutaSelected.getDestino();
                intent.putExtra("Destino", destino_ruta);
                int Id_ruta = rutaSelected.getIdRuta();
                intent.putExtra("IdRuta", Id_ruta);
                int Id_trans = rutaSelected.getTransportista().getIdUsuario();
                intent.putExtra("Id_trans", Id_trans);

                startActivity(intent);
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listar_ruta, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}

