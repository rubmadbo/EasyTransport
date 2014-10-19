package com.example.ruben.easytransport;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.List;

import Objetos.Acuerdo;


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
        Button botonAnadirAcuerdo = (Button)findViewById(R.id.buttonAnyadirAcuerdo);
        botonAnadirAcuerdo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = getIntent();
                String dest_ = intent.getStringExtra("Origen");
                String orig_ = intent.getStringExtra("Destino");
                String rutaId= intent.getStringExtra("IdRuta");

                //JD:recogemos datos del formulario
                destino = (EditText)findViewById(R.id.editTDestinoAcuerdo);
                origen = (EditText)findViewById(R.id.editTOrigenAcuerdo);
                remitente = (EditText)findViewById(R.id.editTRemitente);
                transportista = (EditText)findViewById(R.id.editTTransportista);
                entrega = (EditText)findViewById(R.id.editTEntrega);
                dinero = (EditText)findViewById(R.id.editTDinero);
                comentario = (EditText)findViewById(R.id.editTComentarioAcuerdo);
                recogida = (EditText)findViewById(R.id.editTRecogida);
                destino.setText(dest_);
                origen.setText(orig_);
                origen.getText().toString();
                String r = remitente.getText().toString();
                String t = transportista.getText().toString();
                String e = entrega.getText().toString();
                String din = dinero.getText().toString();
                String com = comentario.getText().toString();
                String rec = recogida.getText().toString();

                dP= (DatePicker)findViewById(R.id.datePickerAcuerdo);
                int mes = dP.getMonth();
                int year= dP.getYear();
                int dia = dP.getDayOfMonth();
                String fecha = String.format("%d/%d/%d", dia, mes, year);

                //conexion a bbdd
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(Anadir_acuerdo.this, "administracion", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                //sacamos el id que le corresponde a este acuerdo
                String selectQuery = "SELECT * FROM Acuerdo";
                Cursor cursor = db.rawQuery(selectQuery, null);
                int id=1;
                if (cursor.moveToFirst()){
                    id =cursor.getCount()+1;}
                //insertamos datos
                // añadido rutaId, ahora se añaden con el Id de la ruta
                db.execSQL("INSERT INTO Acuerdo VALUES('"+id+"','"+t+"','"+r+"','"+rutaId+"','"+din+"','"+com+"')");

                List<Acuerdo> acuerdos= admin.getAcuerdos();
                for (int i=0; i<acuerdos.size();i++){
                    System.out.println(acuerdos.get(i).toString());
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setMessage("Se ha enviado el acuerdo");
                dialog.setCancelable(true);
                dialog.show();

                //finish();
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