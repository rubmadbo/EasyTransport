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

import java.util.ArrayList;

import Objetos.Ruta;


public class ListarRuta extends Activity {


    private Button boton;
    private EditText origen;
    private EditText destino;
    private String dest;
    private String orig;
    private String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ruta);
        boton=(Button)findViewById(R.id.button_volver);
        origen=(EditText)findViewById(R.id.origen);
        destino=(EditText)findViewById(R.id.dest);

        Intent intent = getIntent();
        String message1 = intent.getStringExtra("Ori");
        String message = intent.getStringExtra("Des");
        String data1= intent.getStringExtra("Dat1");
        String data2= intent.getStringExtra("Dat2");
        origen.setText(message1, TextView.BufferType.EDITABLE);
        destino.setText(message, TextView.BufferType.EDITABLE);

        //Insercion de las rutas en el listView
        ListView li = (ListView)findViewById(R.id.listView_rutas);
        ArrayList<Ruta> listaRuta = new ArrayList();

        Ruta ruta;
        JavaPHPMySQL bd = new JavaPHPMySQL();
        String json = bd.getAllRutas();
        ArrayList<Ruta> listaRutas =  bd.mostrarAllRutas(json);
       for(int i=0; i<listaRutas.size();i++) {

           if (listaRutas.get(i).getDestino().equalsIgnoreCase(message) && listaRutas.get(i).getOrigen().equalsIgnoreCase(message1)
                   ) {
               Ruta r = listaRutas.get(i);
               listaRuta.add(r);

           }
       }
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