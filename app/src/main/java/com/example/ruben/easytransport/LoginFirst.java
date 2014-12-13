package com.example.ruben.easytransport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Helpers.LoginSesion;


public class LoginFirst extends ActionBarActivity {

    Boolean resgistro= false;


    LoginSesion session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_first);

        final Button button = (Button) findViewById(R.id.btnSingIn);
        EditText usuario = (EditText) findViewById(R.id.Usuario);
        EditText contr = (EditText) findViewById(R.id.Contraseña);

        /*button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (JavaPHPMySQL.loginSuccess(usuario.getText().toString(), contr.getText().toString())) {
                    Sessions.setUsuarioLogeado(getUsuarioByEmail(usuario.getText().toString()));

                    Intent a = new Intent(this, MenuPrincipal.class);
                    startActivity(a);
                }
            }
        });*/
        //user en Bd
        if(resgistro)
        {


           session.createLoginSession(usuario.getText().toString(),contr.getText().toString());



        }


    }

    public void buttonOnClickLoginOlvido(View v) {

        Intent a = new Intent(this, MailActivty.class);
        startActivity(a);

    }
    public void buttonOnClickLogin(View v) {

        Intent a = new Intent(this, MenuPrincipal.class);
        startActivity(a);

    }
    public void buttonOnClickLoginNuevoUser(View v)
    {

        Intent a =new Intent(this,MailActivty.class);
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
}
