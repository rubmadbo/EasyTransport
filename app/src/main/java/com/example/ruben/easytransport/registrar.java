package com.example.ruben.easytransport;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class registrar extends ActionBarActivity {

    //Faire la meme chose que busquedaRuta
    private Button botonRegistrar;
    private EditText Nombre=null;
    private EditText Apellidos=null;
    private EditText Email=null;
    private EditText Contrasena=null;
    private EditText RepContrasena=null;
    private CheckBox VerContrasena=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final Context context = getApplicationContext();
        botonRegistrar = (Button)findViewById(R.id.ButtonRegistrar);
        // Si pusemos el boton :




                Nombre = (EditText) findViewById(R.id.EntrarNombre);
                Apellidos = (EditText) findViewById(R.id.EntrarApellidos);
                Email = (EditText) findViewById(R.id.EntrarEmail);
                Contrasena = (EditText) findViewById(R.id.EntrarContrasena);
                RepContrasena = (EditText) findViewById(R.id.RepetirContrasena);
                VerContrasena = (CheckBox) findViewById(R.id.ContrasenaVisible);

                String _Nom = Nombre.getText().toString();
                String _Apell = Apellidos.getText().toString();
                String _Email = Email.getText().toString();
                String _Contrasena = Contrasena.getText().toString();
                String _RepContrasena = RepContrasena.getText().toString();


                if(_Nom!=null && _Apell!=null && _Email!=null && _Contrasena!=null && _RepContrasena!=null) {



                    // A ver lo que es el intent
                    /// Cambiar el intent
                    /*Intent intent = new Intent(this,LoginFirst.class);
                    intent.putExtra("Nom",_Nom );
                    intent.putExtra("Apell",_Apell );
                    intent.putExtra("Email",_Email );
                    intent.putExtra("Cont",_Contrasena );
                    intent.putExtra("RepCont",_RepContrasena );
                    getActivity().startActivity(intent);*/

                }
                else if(_Nom==null || _Apell==null || _Email==null || _Contrasena==null || _RepContrasena==null) {
                    Toast.makeText(context, "Rellene todos los campos", Toast.LENGTH_SHORT).show();


                }








    }





}
