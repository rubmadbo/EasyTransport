package com.example.ruben.easytransport;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class Anadir_acuerdo extends ActionBarActivity {
    private EditText origen;
    private EditText destino;
    private EditText remitente;
    private EditText transportista;
    private EditText entrega;
    private EditText dinero;
    private EditText recogida;
    private DatePicker dP;
    private EditText comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_acuerdo2);

        destino = (EditText)findViewById(R.id.editTDestinoAcuerdo);
        origen = (EditText)findViewById(R.id.editTOrigenAcuerdo);
        remitente = (EditText)findViewById(R.id.editTRemitente);
        transportista = (EditText)findViewById(R.id.editTTransportista);
        entrega = (EditText)findViewById(R.id.editTEntrega);
        dinero = (EditText)findViewById(R.id.editTDinero);
        comentario = (EditText)findViewById(R.id.editTComentarioAcuerdo);
        recogida = (EditText)findViewById(R.id.editTRecogida);


        Intent intent = getIntent();
        String dest_ = intent.getStringExtra("Destino");
        String orig_ = intent.getStringExtra("Origen");
        final int rutaId= intent.getIntExtra("IdRuta",0);
        destino.setText(dest_);
        origen.setText(orig_);

        Button botonAnadirAcuerdo = (Button)findViewById(R.id.buttonAnyadirAcuerdo);
        botonAnadirAcuerdo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //JD:recogemos datos del formulario
                String r = remitente.getText().toString();
                String t = transportista.getText().toString();
                String e = entrega.getText().toString();
                String din = dinero.getText().toString();
                String com = comentario.getText().toString();
                String rec = recogida.getText().toString();

                if(!r.equals("") && !t.equals("") && !e.equals("") && !rec.equals("") && !din.equals("")){
                   /* dP= (DatePicker)findViewById(R.id.datePickerAcuerdo);
                    int mes = dP.getMonth()+1;
                    int year= dP.getYear();
                    int dia = dP.getDayOfMonth();
                    String fecha = String.format("%d/%d/%d", dia, mes, year);*/

                    //conexion a bbdd
                    JavaPHPMySQL bd = new JavaPHPMySQL();
                    //JDCC: HAY QUE HABLAR PRIMERO COMO CAMBIAMOS LA CLASE ACUERDO
                    //TMABIEN AQUI HABRIA QUE VER COMO COJER EL ID DEL USUARIO QUE QUIERE CREAR UN ACUERDO
                    //db.insertarAcuerdo(r,t,e,din,com.rec);

                /*List<Acuerdo> acuerdos= admin.getAcuerdos();
                for (int i=0; i<acuerdos.size();i++){
                    System.out.println(acuerdos.get(i).toString());
                }*/
                    Toast.makeText(Anadir_acuerdo.this, "Se ha enviado el acuerdo", Toast.LENGTH_LONG).show();

                    finish();
                }
                else{
                    Toast.makeText(Anadir_acuerdo.this, "Rellene todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.anadir_acuerdo, menu);
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
