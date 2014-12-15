package com.example.ruben.easytransport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import Helpers.LoginSesion;
import Objetos.Usuario;


public class ModificarDatosUsuario extends ActionBarActivity {

    private Button botonGuardarCambios;
    private EditText Nombre=null;
    private EditText Apellidos=null;
    private EditText Email=null;
    private EditText Contrasena=null;
    private EditText RepContrasena=null;
    LoginSesion session;

    Usuario user;
    String email;


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
        setContentView(R.layout.activity_modificar_datos_usuario);


        session = new LoginSesion(getApplicationContext());

        botonGuardarCambios = (Button)findViewById(R.id.btnregistrar);
        Nombre = (EditText) findViewById(R.id.EntrarNombre);
        Apellidos = (EditText) findViewById(R.id.EntrarApellidos);
        Email = (EditText) findViewById(R.id.EntrarEmail);
        Contrasena = (EditText) findViewById(R.id.EntrarContrasena);
        RepContrasena = (EditText) findViewById(R.id.RepetirContrasena);

//esto va pero si se mantiene sesion casca.... por eso SharedPreferences
      /*  Bundle b = getIntent().getExtras();

        if(b!=null)
        {
            email =(String) b.get("user");

        }*/


        String _Nom;
        String _Apell;
        String _Email;
        String _Contrasena;
        String _RepContrasena;

        Intent intent = new Intent();

        //falta recoger TODOS los datos del usuario a modificar.
    /* Si es de sesion pillar el usuario de sesion
    Usuario u = Sessions.getUsuarioLogeado();  o si adrían lo hizo de otra manera pues de la otra manera.

 */
        //copiar estas lineas ******* para obtener email
        HashMap<String, String> usuario = session.getUserDetails();

        // email, aunque ponga KEY_NAME pilla el email, chapuzas by Adrian.
        email = usuario.get(LoginSesion.KEY_EMAIL);


// CON ESTO OBTINES EL EMAIL
        try{user=JavaPHPMySQL.getUsuarioByEmail(email);}
        catch (Exception e){
            e.printStackTrace();
        }

        Email.setText(user.getEmail());
        Nombre.setText(user.getNombre());
        Apellidos.setText(user.getApellido());
        Contrasena.setText(user.getPassword());
        RepContrasena.setText(user.getPassword());


        botonGuardarCambios.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String n;
                String a;
                String e;
                String pass;
                String repass;
                int id= getUsuarioLog().getIdUsuario();

                String rol = "No se usa";

                n = Nombre.getText().toString();
                a = Apellidos.getText().toString();
                e = Email.getText().toString();
                pass = Contrasena.getText().toString();
                repass = RepContrasena.getText().toString();
                if(pass.length() < 3){
                    Toast.makeText(getApplicationContext(), "La contraseña debe ser mayor a 3 caracteres", Toast.LENGTH_LONG).show();
                }else {
                    if (!e.matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
                        Toast.makeText(getApplicationContext(), "El formato de email no es correcto.", Toast.LENGTH_LONG).show();
                    } else {

                        if (pass.equals(repass)) {
                            JavaPHPMySQL.updateUsuario(id, n, a, rol, pass, e);
                            Toast.makeText(getBaseContext(), "Se han guardado los cambios", Toast.LENGTH_LONG).show();
                            finish();

                        } else {
                            Toast.makeText(getBaseContext(), "No coinciden las contraseñas", Toast.LENGTH_LONG).show();
                        }
                    }
                }}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.modificar_datos_usuario, menu);
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
