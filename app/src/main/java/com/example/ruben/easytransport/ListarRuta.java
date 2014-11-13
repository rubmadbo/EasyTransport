package com.example.ruben.easytransport;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.util.Date;

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
        origen=(EditText)findViewById(R.id.TextFecha);
        destino=(EditText)findViewById(R.id.TextHora);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Des");
        String message1 = intent.getStringExtra("Ori");
        String data1= intent.getStringExtra("Dat1");
        String data2= intent.getStringExtra("Dat2");
        //lo de destino.setText estaba para origen message y destino message2, NOLO HE PROBADO PERO SUPONGO QUE AHORA ESTA BIEN
        destino.setText(message, TextView.BufferType.EDITABLE);
        origen.setText(message1, TextView.BufferType.EDITABLE);

        //Insercion de las rutas en el listView
        ListView li = (ListView)findViewById(R.id.listView_rutas);
        final ArrayList<Ruta> listaRuta = new ArrayList();
        Ruta ruta;

        //Conexión a la base de datos
        Date d = new Date();
        //JDCC: IMPORTANTE LA FECHA ACTUAL ESTABA COMO "";
        String _fecha_actual= d.toString();
        JavaPHPMySQL db = new JavaPHPMySQL();
        String json = db.getAllRutas();
        ArrayList<Ruta> rutas = db.mostrarAllRutas(json);
        //se copia rutas en listaRuta, porque listaRuta tiene que ser final para estar en el OnItemClickListener
       for (int i=0; i<rutas.size();i++){
            listaRuta.set(i,rutas.get(i));
        }

        // REVISAR PARA QUE HAGA LA BUSQUEDA BIEN TIENE QUE SER ALGO ASI
        rutas = new ArrayList<Ruta>(); //creo q esto la borra sino pues crear otro arraylist
        for (int j = 0; j<listaRuta.size();j++) {
            //poner if para que se cumpla las cosas del filtro.he añadido el primer if, el segundo ya estaba(tomar de ejemplo)
           // if (listaRuta.get(j).getDestino().equalsIgnoreCase(message)&& listaRuta.get(j).getOrigen().equalsIgnoreCase(message1)){}
            // if(dest.equalsIgnoreCase(message) &&orig.equalsIgnoreCase(message1)&&_fecha_actual.compareTo(data1)>=0 && _fecha_actual.compareTo(data2)<=0){
            rutas.add(listaRuta.get(j));
             //}
        }


        ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(ListarRuta.this,android.R.layout.simple_list_item_1, listaRuta);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ArrayList<Ruta> rutas = new ArrayList();
                Ruta rutaSelected = rutas.get(position);
                Intent intent = new Intent(ListarRuta.this, Anadir_acuerdo.class);
                String origen_ruta = rutaSelected.getOrigen();
                intent.putExtra("Origen", origen_ruta);
                String destino_ruta = rutaSelected.getDestino();
                intent.putExtra("Destino", destino_ruta);
               // int Id_ruta = rutaSelected.getId();
                //intent.putExtra("IdRuta", Id_ruta);
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
