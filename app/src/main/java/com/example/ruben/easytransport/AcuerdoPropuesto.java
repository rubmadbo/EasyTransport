package com.example.ruben.easytransport;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acuerdo_propuesto);

        radioRechazar = (RadioButton) findViewById(R.id.radioRechazar);
        radioAceptar = (RadioButton) findViewById(R.id.radioAceptar);
        TmotivoRechazo = (TextView) findViewById(R.id.textMotivoRechazo);
        ETmotivoRechazo = (EditText) findViewById(R.id.editTRechazo);

        radioRechazar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TmotivoRechazo.setVisibility(View.VISIBLE);
                ETmotivoRechazo.setVisibility(View.VISIBLE);
                Toast.makeText(AcuerdoPropuesto.this, "Botón rechazar", Toast.LENGTH_SHORT).show();
            }
        });

        radioAceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TmotivoRechazo.setVisibility(View.INVISIBLE);
                ETmotivoRechazo.setVisibility(View.INVISIBLE);
               Toast.makeText(AcuerdoPropuesto.this, "Botón aceptar", Toast.LENGTH_SHORT).show();
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
