package com.example.ruben.easytransport;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import static com.example.ruben.easytransport.R.id.listView2;
import static com.example.ruben.easytransport.R.id.listView3;


public class BusquedaRuta extends Fragment {


        private Button boton;
        private EditText origen=null;
        private EditText destino=null;
        private DatePicker _date=null;
        private DatePicker _date2=null;
        private ListView lista;
         private ListView lista1;


    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_busqueda_ruta, container, false);
            boton=(Button)rootView.findViewById(R.id.button_Busqueda);
            lista=(ListView)rootView.findViewById(listView2);
            lista1=(ListView)rootView.findViewById(listView3);

                //falta inicializar ListView

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1);
                lista.setAdapter(adapter);
                lista1.setAdapter(adapter);



            boton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {


                    destino=(EditText)rootView.findViewById(R.id.TextDestino);
                    origen=(EditText)rootView.findViewById(R.id.TextOrigen);

                    String _dest = destino.getText().toString();
                    String _orig = origen.getText().toString();

                    _date= (DatePicker)rootView.findViewById(R.id.datePicker);
                    _date2= (DatePicker)rootView.findViewById(R.id.datePicker2);

                    int mes = _date.getMonth();
                    int year= _date.getYear();
                    int dia = _date.getDayOfMonth();
                    String _fecha = String.format("%d/%d/%d", dia, mes, year);

                    int mes_2 = _date2.getMonth();
                    int year_2= _date2.getYear();
                    int dia_2 = _date2.getDayOfMonth();
                    String _fecha2 = String.format("%d/%d/%d", dia_2, mes_2, year_2);


                    if(_date!=null && _date2!=null && _fecha .compareTo(_fecha2)<=0 && _dest.length()>0 && _orig.length()>0) {
                        //Toast.makeText(this, "Realizando la búsqueda", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(BusquedaRuta.this.getActivity(), ListarRuta.class);
                        startActivity(intent);

                    }
                    if(_date!=null && _date2!=null &&  _fecha .compareTo(_fecha2)>0 && _dest.length()>0 && _orig.length()>0) {

                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                        dialog.setMessage("La fecha de inicio no puede ser mayor que la final");
                        dialog.setCancelable(true);
                        dialog.show();


                    }else{

                        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                        dialog.setMessage("Completa todos los campos");
                        dialog.setCancelable(true);
                        dialog.show();


                    }


                }
            });
            return rootView;

        }




    }
