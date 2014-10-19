package com.example.ruben.easytransport;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
        final AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(ListarRuta.this, "administracion", null, 1);
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
        li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


            Ruta rutaSelected = listaRuta.get(position);


                Bundle bundle = new Bundle();
                String origen_ruta = rutaSelected.getOrigen();
                bundle.putString("Origen", origen_ruta);
                String destino_ruta = rutaSelected.getDestino();
                bundle.putString("Destino", destino_ruta);
                int Id_ruta = rutaSelected.getId();
                bundle.putInt("IdRuta", Id_ruta);
                GestionDeRutas fragInfo = new GestionDeRutas();
                AnadirAcuerdo fragobj = new AnadirAcuerdo();
                fragobj.setArguments(bundle);
                //No peta pero asi no creo que se haga en el intent en proceso....
               // Intent a = new Intent(this,AnadirAcuerdo.class);
               // startActivity(a);


            }

        });

        li.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final AlertDialog.Builder b = new AlertDialog.Builder(ListarRuta.this);
                b.setIcon(android.R.drawable.ic_dialog_alert);
                b.setMessage("¿Desea borrar la ruta seleccionada?");
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(ListarRuta.this, "administracion", null, 1);
                        Ruta rutaSelected = listaRuta.get(position);
                        admin.borrarRuta(rutaSelected.getId());
                        Toast.makeText(ListarRuta.this,"La ruta ha sido borrada",Toast.LENGTH_LONG).show();
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
