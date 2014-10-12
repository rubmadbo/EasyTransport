package com.example.ruben.easytransport;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;


public class AnadirAcuerdo extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_anadir_acuerdo, container, false);


        Button botonAnadirAcuerdo = (Button) rootView.findViewById(R.id.button_volver);
        botonAnadirAcuerdo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setMessage("Se ha enviado el acuerdo");
                dialog.setCancelable(true);
                dialog.show();
            }
        });
        return rootView;
    }
}
