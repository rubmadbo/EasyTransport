package com.example.ruben.easytransport;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

import java.util.ArrayList;

import Objetos.Ruta;


public class ListarRuta extends ActionBarActivity {


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
        origen=(EditText)findViewById(R.id.editText);
        destino=(EditText)findViewById(R.id.editText2);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Des");
        String message1 = intent.getStringExtra("Ori");
        String data1= intent.getStringExtra("Dat1");
        String data2= intent.getStringExtra("Dat2");
        origen.setText(message, TextView.BufferType.EDITABLE);
        destino.setText(message1, TextView.BufferType.EDITABLE);

        //Insercion de las rutas en el listView
        ListView li = (ListView)findViewById(R.id.listView_rutas);
        final ArrayList<Ruta> listaRuta = new ArrayList();
        Ruta ruta;

        //Conexión a la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(ListarRuta.this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //Sacamos todo a pelo y luego se mejora
        Cursor cur = db.rawQuery("SELECT * FROM Ruta", null);
        String _fecha_acutal="";
        if(cur.moveToFirst()){
            do{
                ruta = new Ruta(cur.getInt(0),cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4),
                        cur.getString(5),cur.getString(6),cur.getInt(7));
                dest= ruta.getDestino();
                orig= ruta.getOrigen();
                _fecha_acutal=ruta.getFecha();


                if(dest.equalsIgnoreCase(message) &&orig.equalsIgnoreCase(message1)&&_fecha_acutal.compareTo(data1)>=0 && _fecha_acutal.compareTo(data2)<=0){
                listaRuta.add(ruta);
                }

            }while(cur.moveToNext());
        }
        ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(ListarRuta.this,android.R.layout.simple_list_item_1, listaRuta);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);
        ///////////////////////////////////////////////////////////////////////
        //JD: A partir de aqui es lo nuevo no consigo que en Intent Anadir_acuerdo .... pase de una pantalla a otra
        //TODO: falta además crear un metodo que se llame borrarRuta y que borre solo la ruta si no tiene un acuerdo asociado
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            //obtenemos la ruta seleccionada
            Ruta rutaSelected = listaRuta.get(position);

                Intent intent = new Intent(ListarRuta.this.getBaseContext(),AnadirAcuerdo.class);
                intent.putExtra("Origen", rutaSelected.getOrigen());
                intent.putExtra("Destino", rutaSelected.getDestino());
                intent.putExtra("IdRuta", rutaSelected.getId());

                startActivity(intent);

                System.out.println("Eso es lo qu eenvia lita ruta Origen: "+rutaSelected.getOrigen()+" Destino: "+rutaSelected.getDestino()+" IdRuta: "+rutaSelected.getId());
                // Empieza la activity
                startActivity(intent);


            }
            ////////////////////////////////////////////////////////////////////
        });

        cur.close();
        admin.close();

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
