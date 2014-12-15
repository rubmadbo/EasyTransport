package com.example.ruben.easytransport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import Helpers.LoginSesion;
import Objetos.Ruta;
import Objetos.Usuario;


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

    public Usuario getUsuarioLog(){
        LoginSesion session;

        Usuario user = new Usuario(0,"","","","tuviejaa",null,null,null,"");
        String email;

        session = new LoginSesion(getApplicationContext());

        HashMap<String, String> usuario = session.getUserDetails();

        // email
        email = usuario.get(LoginSesion.KEY_EMAIL);
        // CON ESTO OBTINES EL EMAIL
        try{user=JavaPHPMySQL.getUsuarioByEmail(email);}
        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

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
        final int transportistaId = intent.getIntExtra("Id_trans",0);

        JavaPHPMySQL bd = new JavaPHPMySQL();
        final Usuario usuarioLogeado = bd.getUsuarioByUserId(getUsuarioLog().getIdUsuario()); //remitente es el usuario2 en bbdd
        String json = bd.getAllRutas();
        ArrayList<Ruta> rutas = bd.mostrarAllRutas(json);
        Usuario transportista1=null;

        for (int i=0; i<rutas.size();i++){
            if (rutas.get(i).getIdRuta()== rutaId){
                transportista1 = bd.getUsuarioByUserId(rutas.get(i).getTransportista().getIdUsuario());
            }
        }

        destino.setText(dest_);
        origen.setText(orig_);
        remitente.setText(usuarioLogeado.getNombre()+" "+usuarioLogeado.getApellido());
        transportista.setText(transportista1.getNombre()+" "+transportista1.getApellido());
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

                if (!r.equals("") && !t.equals("") && !e.equals("") && !rec.equals("") && !din.equals("") && !rec.equalsIgnoreCase(e)) {

                    //este seria el usuario logeado que quiere crear el acuerdo(remitente)
                    JavaPHPMySQL bd = new JavaPHPMySQL();
                    //xapuzaaa JDCC, he quitado el autoincremente tamb de acuerdo en la bbdd
                    String a = bd.getNumeroAcuerdos();
                    int j = Integer.parseInt(bd.mostrarNumeroAcuerdos(a));
                    //int i=1;
                    bd.insertarAcuerdo(j+1,Double.parseDouble(din), com, "pendiente", rutaId, usuarioLogeado.getIdUsuario(), e, rec,"");
                    bd.insertarAcuerdoenUsuariohasAcuerdo(j+1,usuarioLogeado.getIdUsuario());
                    //i++;
                    Usuario transportista = JavaPHPMySQL.getUsuarioByUserId(transportistaId);
                    String Mensaje = "Hola "+transportista.getNombre()+" "+transportista.getApellido()+"! "
                            +"Se ha añadido un nuevo acuerdo a una ruta en la que usted figura como transportista."+ "\\n"+"Entre a la aplicación para conocer más detalles.";
                    JavaPHPMySQL.enviarEmail(transportista.getEmail(),Mensaje,"Nuevo Acuerdo Creado");
                    Toast.makeText(Anadir_acuerdo.this, "Se ha enviado el acuerdo", Toast.LENGTH_LONG).show();

                    finish();
                } else if (rec.equalsIgnoreCase(e)) {
                    Toast.makeText(Anadir_acuerdo.this, "El punto de recogida y punto de entrega no pueden ser iguales", Toast.LENGTH_LONG).show();
                }else if (r.equals("") || t.equals("") || e.equals("") || rec.equals("") || din.equals("")){
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
