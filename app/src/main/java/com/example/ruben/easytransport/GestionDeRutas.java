package com.example.ruben.easytransport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;

import Objetos.Ruta;


public class GestionDeRutas extends ActionBarActivity {
    private Spinner spinner;
    private String[] datos; //= {"Ferrari","Coupe","Lamborginhi"};
    private EditText origen;
    private EditText destino;
    private DatePicker dP;
    private TimePicker tP;
    private EditText comentario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_rutas);
        rellenarSpinner();
    }

    public void rellenarSpinner(){
        try {
            spinner = (Spinner) findViewById(R.id.lista1);
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            List<String> vehiculos = admin.getVehiculos();
            ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, vehiculos);
            spinner.setAdapter(adapt);

        }catch (Exception e){
            System.out.println("MENSAJE DE ERROR ----------------------------------------> ");
            e.printStackTrace();
        }
    }
    public void buttonOnClick(View v){
       // Toast.makeText(this, "Has pulsado el botón",Toast.LENGTH_SHORT).show();

        //Button btAceptar = (Button) v;
       // ((Button) v).setText("Clicked");
        //JD: siguientes 2 lineas ,codigo duplicado mejor ponerlo explicitamente ne el onCreate
        //Fran: No, es mejor hacerlo solo donde toca
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        List<String> vehiculos = admin.getVehiculos();
        String vehiculo = vehiculos.get(spinner.getSelectedItemPosition());

        destino = (EditText)findViewById(R.id.TextDestino);
        origen = (EditText)findViewById(R.id.TextOrigen);
        String d = destino.getText().toString();
        String o = origen.getText().toString();

        dP= (DatePicker)findViewById(R.id.datePicker);
        int mes = dP.getMonth();
        int year= dP.getYear();
        int dia = dP.getDayOfMonth();
        String fecha = String.format("%d/%d/%d", dia, mes+1, year);

        tP = (TimePicker)findViewById(R.id.timePicker);
        int hora = tP.getCurrentHour();
        int minutos =tP.getCurrentMinute();
        String horaInicio = String.format("%d:%d", hora, minutos);

        comentario =(EditText)findViewById(R.id.editTComentario);
        String com = comentario.getText().toString();

        if(!d.equals("") && !o.equals("")){
            // meterlos a la BBDD
            SQLiteDatabase db = admin.getWritableDatabase();

            //el id de Ruta deberia de aumentar con dada ruta
            String selectQuery = "SELECT * FROM Ruta";
            Cursor cursor = db.rawQuery(selectQuery, null);
            int id=1;
            int idTransportista=1; //esto de momento hasta q tengamos usuario sino seria =u.getUsuario().id;
            if (cursor.moveToFirst()){
                id =cursor.getCount()+1;}


            //db.delete("Ruta",null,null); //JD:se carga SOLO el contenido de la tabla
           // db.delete("Acuerdo",null,null);
            //JD:Ruta no guarda el vehiculo .. Donde lo inserto? horaFIN que cojones¿?¿? he puesto el comntario por no dejarlo vacio
            db.execSQL("INSERT INTO Ruta VALUES('"+id+"','"+o+"','"+d+"','"+fecha+"','"+horaInicio+"','¿NUSE?','"+com+"','"+idTransportista+"')");

            //JD:printa todas las rutas guardas en el log
            /*List<Ruta> rutas= admin.getRutas();
            for (int i=0; i<rutas.size();i++){
                System.out.println(rutas.get(i).toString());
            }*/

            //Fran: Justo antes del toast de deberia de hacer la actualización del ListView de VistaRutas, pero no consigo que vaya
            //sin de un error.
            Toast.makeText(this, "La ruta se ha insertado correctamente", Toast.LENGTH_SHORT).show();
            finish();

        }
        else if(o.equals(d)){   Toast.makeText(this, "El origen no puede ser igual al destino", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gestion_de_rutas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


}
