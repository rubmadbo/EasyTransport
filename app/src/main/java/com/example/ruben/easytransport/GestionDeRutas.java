package com.example.ruben.easytransport;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import Objetos.Ruta;
import Objetos.Vehiculo;


public class GestionDeRutas extends ActionBarActivity {
    private Spinner spinner;
    private String[] datos; //= {"Ferrari","Coupe","Lamborginhi"};
    private EditText origen;
    private EditText destino;
    private DatePicker dP;
    private TimePicker tP;
    private EditText comentario;

    private EditText textFecha;
    static final int DATE_DIALOG_ID = 0;
    private int year;
    private int month;
    private int day;

    private EditText textHora;
    static final int TIMER_DIALOG_ID = 1;
    private int hour;
    private int min;

    final Calendar c = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_de_rutas);
        rellenarSpinner();

        textFecha = (EditText)findViewById(R.id.TextFecha);
        textHora = (EditText)findViewById(R.id.TextHora);

        textHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIMER_DIALOG_ID);
            }
        });

        textFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        hour = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);

        updateDate();
        updateTime();

    }

    private void updateTime() {
        textHora.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(min)).append(":").append("00"));
    }

    private void updateDate(){
        textFecha.setText(new StringBuilder().append(pad(day)).append("/").append(pad(month + 1)).append("/").append(year));
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hour = hourOfDay;
                    min = minute;
                    updateTime();
                }
            };

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener(){
                public void onDateSet(DatePicker view, int yearOf, int monthOfYear, int dayOfMonth){
                    if(yearOf > c.get(Calendar.YEAR)) {
                        year = yearOf;
                        month = monthOfYear;
                        day = dayOfMonth;
                        updateDate();
                    }
                    else if(yearOf == c.get(Calendar.YEAR) && monthOfYear >= (c.get(Calendar.MONTH))){
                        year = yearOf;
                        month = monthOfYear;
                        day = dayOfMonth;
                        updateDate();
                    }
                    else if (yearOf == c.get(Calendar.YEAR) && monthOfYear == (c.get(Calendar.MONTH))+1 && dayOfMonth > c.get(Calendar.DAY_OF_MONTH)){
                        year = yearOf;
                        month = monthOfYear;
                        day = dayOfMonth;
                        updateDate();
                    }
                    else showToast();
                    }



            };

    public void showToast(){
        Toast.makeText(this, "La fecha no puede ser anterior a la actual", Toast.LENGTH_SHORT).show();
    }

    protected Dialog onCreateDialog(int id){
        switch (id){
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,mDateSetListener,year,month,day);
            case TIMER_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, hour, min, false);
        }
        return null;
    }


    public void rellenarSpinner(){
        try {
            spinner = (Spinner) findViewById(R.id.lista1);
            JavaPHPMySQL bd = new JavaPHPMySQL();
            ArrayList<String> vehiculos = new ArrayList<String>();
            ArrayList<Vehiculo> listaVehiculos = bd.getVehiculoByUserId(1);

            for(int i=0; i < listaVehiculos.size(); i++){
                vehiculos.add(listaVehiculos.get(i).toString());
            }
            ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, vehiculos);
            spinner.setAdapter(adapt);

        }catch (Exception e){
            System.out.println("MENSAJE DE ERROR ----------------------------------------> ");
            e.printStackTrace();
        }
    }
    public void buttonOnClick(View v) throws ParseException {
        JavaPHPMySQL bd = new JavaPHPMySQL();

        ArrayList<String> vehiculos = new ArrayList<String>();
        ArrayList<Vehiculo> listaVehiculos = bd.getVehiculoByUserId(1);

        for(int i=0; i < listaVehiculos.size(); i++){
            vehiculos.add(listaVehiculos.get(i).toString());
        }
        String vehiculo = vehiculos.get(spinner.getSelectedItemPosition());

        destino = (EditText)findViewById(R.id.TextDestino);
        origen = (EditText)findViewById(R.id.TextOrigen);
        String d = destino.getText().toString();
        String o = origen.getText().toString();

        comentario = (EditText) findViewById(R.id.editTComentario);

        if (!d.equals("") && !o.equals("")) {
            String fecha = textFecha.getText().toString();
            String horaInicio = textHora.getText().toString();
            String com = comentario.getText().toString();

            bd.insertarRuta(o,d,horaInicio,fecha,com,1);
            Toast.makeText(this, "La ruta se ha insertado correctamente", Toast.LENGTH_SHORT).show();
            finish();

        }  else if ((!d.equals("") && !o.equals("")) && o.equals(d)){
            Toast.makeText(this, "El origen no puede ser igual al destino", Toast.LENGTH_SHORT).show();
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
