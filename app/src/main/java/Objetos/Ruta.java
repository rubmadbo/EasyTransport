package Objetos;

import java.util.Date;

/**
 * Created by JD on 10/10/2014.
 */
public class Ruta {
    private int id;
    private String origen;
    private String destino;
    //private Date fecha; de momento es una String
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private String comentario;
    private Usuario transportista;

    //aunque no creo que tenga sentido crear una ruta sin TODOS los atributos.solo con el set y get suficiente
    public void Ruta(int id, Usuario t){
        this.id = id;
        transportista = t;
        origen = "";
        destino = "";
        fecha = "";
        horaInicio = "";
        horaFin = "";
        comentario = "";
    }

    public void setRuta(int id, String o, String d, String f,String h1,String h2 ,String com, Usuario t ){
        this.id = id;
        origen = o;
        destino = d;
        fecha = f;
        horaInicio = h1;
        horaFin = h2;
        comentario = com;
        transportista = t;

    }
    public Ruta getRuta(){ return this;}

}
