package com.example.ruben.easytransport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class BusquedaRuta extends Fragment {


    private Button boton;
    private EditText origen = null;
    private EditText destino = null;
    private DatePicker _date = null;
    private DatePicker _date2 = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_busqueda_ruta, container, false);
        boton = (Button) rootView.findViewById(R.id.button_Busqueda);

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                destino = (EditText) rootView.findViewById(R.id.TextDestino);
                origen = (EditText) rootView.findViewById(R.id.TextOrigen);

                String _dest = destino.getText().toString();
                String _orig = origen.getText().toString();

                _date = (DatePicker) rootView.findViewById(R.id.datePicker);
                _date2 = (DatePicker) rootView.findViewById(R.id.datePicker2);


                int mes = _date.getMonth();
                int year = _date.getYear();
                int dia = _date.getDayOfMonth();
                GregorianCalendar fechaDesdeSeleccionada = new GregorianCalendar(year, mes, dia);
                Date fechaDesde = fechaDesdeSeleccionada.getTime();
                String _fecha = String.format("%d/%d/%d", dia, mes + 1, year);

                int mes_2 = _date2.getMonth();
                int year_2 = _date2.getYear();
                int dia_2 = _date2.getDayOfMonth();
                GregorianCalendar fechaHastaSeleccionada = new GregorianCalendar(year_2, mes_2, dia_2);
                Date fechaHasta = fechaHastaSeleccionada.getTime();
                String _fecha2 = String.format("%d/%d/%d", dia_2, mes_2 + 1, year_2);

                Calendar c = Calendar.getInstance();
                int actualYear = c.get(Calendar.YEAR);
                int actualMonth = c.get(Calendar.MONTH);
                int actualDay = c.get(Calendar.DAY_OF_MONTH);
                GregorianCalendar calendarHoy = new GregorianCalendar(actualYear, actualMonth, actualDay);
                Date fechaActual = calendarHoy.getTime();


                if (_date != null && _date2 != null && fechaDesde.getTime() <= fechaHasta.getTime() &&
                        fechaActual.getTime() <= fechaDesde.getTime() && fechaActual.getTime() <= fechaHasta.getTime()) {
                    Intent intent = new Intent(getActivity().getBaseContext(), ListarRuta.class);
                    intent.putExtra("Des", _dest);
                    intent.putExtra("Ori", _orig);
                    intent.putExtra("Dat1", _fecha);
                    intent.putExtra("Dat2", _fecha2);
                    getActivity().startActivity(intent);

                } else if (_date == null || _date2 == null || _fecha == null || _fecha2 == null || _dest.length() <= 0 || _orig.length() <= 0) {
                    Toast.makeText(getActivity(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                } else if (_date != null && _date2 != null && fechaActual.getTime() > fechaDesde.getTime() || fechaActual.getTime() > fechaHasta.getTime()) {
                    Toast.makeText(getActivity(), "Compruebe que las fechas no sean inferiores a la actual", Toast.LENGTH_SHORT).show();
                } else if (_date != null && _date2 != null && fechaHasta.getTime() <= fechaDesde.getTime()) {
                    Toast.makeText(getActivity(), "La fecha de inicio no puede ser mayor que la de fin", Toast.LENGTH_SHORT).show();
                }
                //hay que arreglar el equals del origen destino
                else if (_date != null && _date2 != null && _fecha.compareTo(_fecha2) > 0 && _dest.length() > 0 && _orig.length() > 0 && year > year_2 && _dest.equalsIgnoreCase(_orig)) {
                    Toast.makeText(getActivity(), "El origen no puede ser igual al destino", Toast.LENGTH_SHORT).show();
                }


            }


        });
        return rootView;

    }
}
