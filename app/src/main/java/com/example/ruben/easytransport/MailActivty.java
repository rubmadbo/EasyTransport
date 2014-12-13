package com.example.ruben.easytransport;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import Objetos.Usuario;

public class MailActivty extends ActionBarActivity {

    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_activty);
        final Button button = (Button) findViewById(R.id.btnSingIn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mail_activty, menu);
        return true;
    }

    public void buttonOnClickLogin(View v)
    {
        email = (EditText)findViewById(R.id.Mail);

        String _mail = email.getText().toString();
        Usuario u = null;
        //aqui se supone qu ese trae el usuario del email que se ha introducido
        try{
        u=JavaPHPMySQL.getUsuarioByEmail(_mail);
        }catch(Exception e){e.printStackTrace();}

        if (u!=null){
            Random r = new Random();
            int i1 = r.nextInt(100000 - 65) + 65;

        JavaPHPMySQL.cambiarPass(u.getIdUsuario(),String.valueOf(i1));
        JavaPHPMySQL.enviarEmail(_mail,String.valueOf(i1));
        Toast.makeText(this, "Se ha cambiado tu contraseña y se te ha enviado un email", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "No existe el usuario en la Base de Datos", Toast.LENGTH_LONG).show();
        }
        /*Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tu contraseña será restablecida cuando envies este email");
        intent.putExtra(Intent.EXTRA_TEXT, "Tu nueva contraseña es "+""+i1);
        intent.setData(Uri.parse("mailto:" + _mail)); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);
        Toast.makeText(this, "Tu contraseña ha sido restablecida .", Toast.LENGTH_SHORT).show();

//codigo muy nazi no se puede no poner visible
        /*Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"adrian4192@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Restablecer Contraseña");
        i.putExtra(Intent.EXTRA_TEXT   , "4ndx90m");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
*/
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
