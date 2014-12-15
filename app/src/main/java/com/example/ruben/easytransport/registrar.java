package com.example.ruben.easytransport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class registrar extends ActionBarActivity {

    private Button botonRegistrar;
    private EditText Nombre=null;
    private EditText Apellidos=null;
    private EditText Email=null;
    private EditText Contrasena=null;
    private EditText RepContrasena=null;
    private CheckBox terminos=null;

    @Override


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        botonRegistrar = (Button)findViewById(R.id.btnregistrar);
                Nombre = (EditText) findViewById(R.id.EntrarNombre);
                Apellidos = (EditText) findViewById(R.id.EntrarApellidos);
                Email = (EditText) findViewById(R.id.EntrarEmail);
                Contrasena = (EditText) findViewById(R.id.EntrarContrasena);
                RepContrasena = (EditText) findViewById(R.id.RepetirContrasena);
                terminos = (CheckBox) findViewById(R.id.checkBox);



        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String _Nom = Nombre.getText().toString();
                String _Apell = Apellidos.getText().toString();
                String _Email = Email.getText().toString();
                String _Contrasena = Contrasena.getText().toString();
                String _RepContrasena = RepContrasena.getText().toString();

                if(terminos.isChecked()) {

                    if (_Nom !="" && _Apell !="" && _Email != "" && _Contrasena != "" && _RepContrasena != "") {
                        if(_Contrasena.length() < 3){
                            Toast.makeText(getApplicationContext(), "La contraseña debe ser mayor a 3 caracteres", Toast.LENGTH_LONG).show();
                        }else{
                            if (!_Email.matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$")){
                                Toast.makeText(getApplicationContext(), "El formato de email no es correcto.", Toast.LENGTH_LONG).show();
                            }else {


                                if (_Contrasena.equals(_RepContrasena)) {
                                    //REVISAR INTENT QUE NO SE CUAL PONER QUEel base context lo puse porque this no iba.
                                    JavaPHPMySQL.insertarUsuario(_Nom, _Apell, "no importa", _Contrasena, _Email);
                                    JavaPHPMySQL.enviarEmail(_Email,"Bienvenido a EasyTransport "+_Nom+"!","Bienvenido");
                                    Intent a = new Intent(getBaseContext(), MenuPrincipal.class);
                                    startActivity(a);

                                } else {
                                    Toast.makeText(getApplicationContext(), "No coinciden las contraseñas", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                    else if (_Nom == null || _Apell == null || _Email == null || _Contrasena == null || _RepContrasena == null) {
                        Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_LONG).show();

                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Tienes que aceptar nuestos terminos y condiciones.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
