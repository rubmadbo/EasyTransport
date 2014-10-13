package com.example.ruben.easytransport;


import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.List;

import Objetos.Acuerdo;


public class AnadirAcuerdo extends Fragment {

    private EditText origen;
    private EditText destino;
    private EditText remitente;
    private EditText transportista;
    private EditText entrega;
    private EditText dinero;
    private EditText recogida;
    private DatePicker dP;
    private EditText comentario;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       final View rootView = inflater.inflate(R.layout.activity_anadir_acuerdo, container, false);



        Button botonAnadirAcuerdo = (Button) rootView.findViewById(R.id.button_volver);
        botonAnadirAcuerdo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            //JD:recogemos datos del formulario
                destino = (EditText)rootView.findViewById(R.id.editTDestinoAcuerdo);
                origen = (EditText)rootView.findViewById(R.id.editTOrigenAcuerdo);
                remitente = (EditText)rootView.findViewById(R.id.editTRemitente);
                transportista = (EditText)rootView.findViewById(R.id.editTTransportista);
                entrega = (EditText)rootView.findViewById(R.id.editTEntrega);
                dinero = (EditText)rootView.findViewById(R.id.editTDinero);
                comentario = (EditText)rootView.findViewById(R.id.editTComentarioAcuerdo);
                recogida = (EditText)rootView.findViewById(R.id.editTRecogida);
                String d = destino.getText().toString();
                String o = origen.getText().toString();
                String r = remitente.getText().toString();
                String t = transportista.getText().toString();
                String e = entrega.getText().toString();
                String din = dinero.getText().toString();
                String com = comentario.getText().toString();
                String rec = recogida.getText().toString();

                dP= (DatePicker)rootView.findViewById(R.id.datePickerAcuerdo);
                int mes = dP.getMonth();
                int year= dP.getYear();
                int dia = dP.getDayOfMonth();
                String fecha = String.format("%d/%d/%d", dia, mes, year);

            //conexion a bbdd
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(AnadirAcuerdo.this.getActivity(), "administracion", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
            //sacamos el id que le corresponde a este acuerdo
                String selectQuery = "SELECT * FROM Acuerdo";
                Cursor cursor = db.rawQuery(selectQuery, null);
                int id=1;
                if (cursor.moveToFirst()){
                    id =cursor.getCount()+1;}
            //insertamos datos
                db.execSQL("INSERT INTO Acuerdo VALUES('"+id+"','"+t+"','"+r+"','1','"+din+"','"+com+"')");

                List<Acuerdo> acuerdos= admin.getAcuerdos();
                for (int i=0; i<acuerdos.size();i++){
                    System.out.println(acuerdos.get(i).toString());
                }
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setMessage("Se ha enviado el acuerdo");
                dialog.setCancelable(true);
                dialog.show();

                //finish(); JD:NOSE QUE HACE ESTO pero estaba en getstion de rutas creo q lo puso fran lo ponemos?
            }
        });
        return rootView;
    }

}
