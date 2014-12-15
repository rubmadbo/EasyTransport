package Helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ruben.easytransport.LoginFirst;

import java.util.HashMap;

/**
 * Created by adrian_home on 13/12/14.
 */
public class LoginSesion {

        SharedPreferences pref;


        SharedPreferences.Editor editor;


        Context _context;


        int PRIVATE_MODE = 0;

        //archivo de guardado
        private static final String PREF_NAME = "AndroidHivePref";


        private static final String IS_LOGIN = "IsLoggedIn";




        public static final String KEY_NAME = "name";


        public static final String KEY_EMAIL = "email";

        public LoginSesion(Context context){

            this._context = context;

            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

            editor = pref.edit();
        }


        public void createLoginSession(String email, String name){

            editor.putBoolean(IS_LOGIN, true);

            editor.putString(KEY_NAME, name);

            editor.putString(KEY_EMAIL, email);

            editor.commit();
        }


        public void checkLogin(){

            if(!this.isLoggedIn()){

                Intent i = new Intent(_context, LoginFirst.class);

                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                _context.startActivity(i);
            }

        }



        /**
         * datos usuario
         * */
        public HashMap<String, String> getUserDetails(){
            HashMap<String, String> user = new HashMap<String, String>();

            user.put(KEY_NAME, pref.getString(KEY_NAME, null));

            user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));


            return user;
        }


        public void logoutUser(){

            editor.clear();

            editor.commit();


            Intent i = new Intent(_context, LoginFirst.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

        /**
         * Quick check for login
         * **/
        // Get Login State
        public boolean isLoggedIn(){

            return pref.getBoolean(IS_LOGIN, false);
        }
    public String EmailRetrieve(){

        String a;
        a=pref.getString(KEY_EMAIL, KEY_EMAIL);
        return a;



    }
    }

