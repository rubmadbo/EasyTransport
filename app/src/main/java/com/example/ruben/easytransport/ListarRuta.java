package com.example.ruben.easytransport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ListarRuta extends ActionBarActivity {


    private Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ruta);
        boton=(Button)findViewById(R.id.button_volver);
        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ListarRuta.this,BusquedaRuta.class);
                startActivity(intent);
            }
        });
/*

        //Insercion de las rutas en el listView
        ListView li = (ListView)findViewById(R.id.listViewRutas);
        ArrayList<Ruta> listaRuta = new ArrayList();
        Ruta ruta;

        //Conexión a la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(VistaRutas.this.getActivity(), "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        //Consulta a la base de datos
        Cursor cur = db.rawQuery("SELECT * FROM Ruta WHERE ", null);

        if(cur.moveToFirst()){
            do{
                ruta = new Ruta(cur.getInt(0),cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4),
                        cur.getString(5),cur.getString(6),cur.getInt(7));
                listaRuta.add(ruta);
            }while(cur.moveToNext());
        }

        //Inserción en el ListView
        ArrayAdapter<Ruta> adap = new ArrayAdapter<Ruta>(VistaRutas.this.getActivity(),android.R.layout.simple_list_item_1, listaRuta);
        adap.notifyDataSetChanged();
        li.setAdapter(adap);

        //Cerramos la conexion
        cur.close();
        admin.close();




*/

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
