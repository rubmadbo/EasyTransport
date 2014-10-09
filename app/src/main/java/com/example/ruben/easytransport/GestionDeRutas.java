package com.example.ruben.easytransport;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;


public class GestionDeRutas extends ActionBarActivity {
    private Spinner spinner;
    private String[] datos= {"Ferrari","Coupe","Lamborginhi"};private EditText origen;
    private EditText destino;
    private DatePicker dP;
    private TimePicker tP;

    /*private int year;
    private int mes;
    private int dia;
    private int hora;
    private String d;
    private String o;
    private int minutos;
    private String vehiculo;*/
    EditText comentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_rutas);

        rellenarSpinner();
    }
    public void rellenarSpinner(){
        try {
            ArrayList<String> listaVehiculos = null;
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            spinner = (Spinner) findViewById(R.id.lista1);
            Cursor consulta = bd.rawQuery("select * from Vehiculo", null);

            while (consulta.moveToNext()) {
                System.out.println("Elementos de la base de datos -> " + consulta.getString(1) + " " + consulta.getString(2));
                //listaVehiculos.add(consulta.getString(1) + " " + consulta.getString(2));
            }
            //ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaVehiculos);
            //spinner.setAdapter(adapt);

        }catch (Exception e){
            System.out.println("MENSAJE DE ERROR ----------------------------------------> ");
            e.printStackTrace();
        }
    }
    public void buttonOnClick(View v){
        Toast.makeText(this, "Has pulsado el botón",Toast.LENGTH_SHORT).show();

        /*Button btAceptar = (Button) v;
        ((Button) v).setText("Clicked");

        String vehiculo = datos [spinner.getSelectedItemPosition()];

        destino = (EditText)findViewById(R.id.editTDestino);
        origen = (EditText)findViewById(R.id.editTOrigen);
        String d = destino.getText().toString();
        String o = origen.getText().toString();

        dP= (DatePicker)findViewById(R.id.datePicker);
        int mes = dP.getMonth();
        int year= dP.getYear();
        int dia = dP.getDayOfMonth();

        tP = (TimePicker)findViewById(R.id.timePicker);
        int hora = tP.getCurrentHour();
        int minutos =tP.getCurrentMinute();

        comentario =(EditText)findViewById(R.id.editTComentario);
        String com = comentario.getText().toString();
        //simplemente para saber si ha cogido bien las variables
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(" Vehiculo: "+vehiculo+" Origen: "+o+" Destino: "+d+" Fecha: "+dia+"/"+mes+"/"+year+" Hora: "+hora+":"+minutos+" Comentario: "+com);
        dialog.setCancelable(true);
        dialog.show();*/
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
