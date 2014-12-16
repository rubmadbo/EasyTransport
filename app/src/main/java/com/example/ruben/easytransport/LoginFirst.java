package com.example.ruben.easytransport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Helpers.LoginSesion;
import Objetos.Usuario;


public class LoginFirst extends ActionBarActivity {



    EditText usuario;
    EditText contr ;
    LoginSesion session;
    Usuario user;
    String usu;
    String con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first);


         usuario = (EditText) findViewById(R.id.Usuario);
         contr = (EditText) findViewById(R.id.Contraseña);
         session= new LoginSesion(getApplicationContext());



        }


    public void buttonOnClickLoginOlvido(View v) {

        Intent a = new Intent(this, MailActivty.class);
        startActivity(a);

    }
    public void buttonOnClickLogin(View v) {
        usu = usuario.getText().toString();
        con = contr.getText().toString();

        if(usu.equals("") || con.equals("")){
            Toast.makeText(this, "Rellene los campos.", Toast.LENGTH_SHORT).show();
        }
        else {

            boolean resultado = false;
            try {
                resultado = JavaPHPMySQL.loginSuccess(usuario.getText().toString(), contr.getText().toString());
            } catch (Exception e) {

            }

            if (resultado) {

                session.createLoginSession(usuario.getText().toString(), contr.getText().toString());
                try {

                    user = JavaPHPMySQL.getUsuarioByEmail(usuario.getText().toString());

                } catch (Exception e) {

            /*if (user != null){
                Sessions.setUsuarioLogeado(user);
            }*/

                }
                //no puedes pasar objetos por intent solo tipos primitivos
                Intent a = new Intent(this, MenuPrincipal.class);
                startActivity(a);
            } else {
                Toast.makeText(this, "Usuario o Contraseña incorrecta", Toast.LENGTH_SHORT).show();

            }
        }

    }
    public void buttonOnClickLoginNuevoUser(View v)
    {

        Intent a =new Intent(this,registrar.class);
        startActivity(a);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_first, menu);
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
   public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }




}
