package com.example.ruben.easytransport;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class AcuerdoPropuesto extends ActionBarActivity {

    private RadioButton radioRechazar;
    private RadioButton radioAceptar;
    private TextView TmotivoRechazo;
    private EditText ETmotivoRechazo;
    private EditText ETRemitente;
    private EditText Origen;
    private EditText Destino;
    private EditText Fecha;
    private EditText Dinero;
    private EditText Comentario;
    private Button Confirmar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acuerdo_propuesto);

        radioRechazar = (RadioButton) findViewById(R.id.radioRechazar);
        radioAceptar = (RadioButton) findViewById(R.id.radioAceptar);
        TmotivoRechazo = (TextView) findViewById(R.id.textMotivoRechazo);
        ETmotivoRechazo = (EditText) findViewById(R.id.editTRechazo);
        ETRemitente = (EditText) findViewById(R.id.editTRemitente_acuerdo);
        Origen = (EditText) findViewById(R.id.editTOrigen);
        Destino = (EditText) findViewById(R.id.editTDestino);
        Fecha = (EditText) findViewById(R.id.editTFecha);
        Dinero = (EditText) findViewById(R.id.editTDinero) ;
        Comentario = (EditText) findViewById(R.id.editTComentarios);
        Confirmar = (Button) findViewById(R.id.buttonAceptar);


        Intent intent = getIntent();
        final int rutaId = intent.getIntExtra("IdRuta",0);
        ETRemitente.setText(intent.getStringExtra("Remitente"));
        Destino.setText(intent.getStringExtra("Destino"));
        Origen.setText(intent.getStringExtra("Origen"));
        Fecha.setText(intent.getStringExtra("Fecha"));
        Dinero.setText(intent.getStringExtra("Dinero"));
        Comentario.setText(intent.getStringExtra("Comentario"));

        radioRechazar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TmotivoRechazo.setVisibility(View.VISIBLE);
                ETmotivoRechazo.setVisibility(View.VISIBLE);
            }
        });

        radioAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TmotivoRechazo.setVisibility(View.INVISIBLE);
                ETmotivoRechazo.setVisibility(View.INVISIBLE);
            }
        });

        Confirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(radioAceptar.isChecked()){
                    //acciones al aceptar acuerdo.
                    JavaPHPMySQL db = new JavaPHPMySQL();
                    db.updateEstadoAcuerdo("aceptado", rutaId);
                    Toast.makeText(getApplicationContext(), "El acuerdo ha sido aceptado", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if(radioRechazar.isChecked()){
                    //acciones al rechazar acuerdo
                    JavaPHPMySQL db = new JavaPHPMySQL();
                    db.updateEstadoAcuerdo("rechazado", rutaId);
                    Toast.makeText(getApplicationContext(), "El acuerdo ha sido rechazado", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.acuerdo_propuesto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
