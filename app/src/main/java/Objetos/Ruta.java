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
    //private Usuario transportista;
    int idU;

    //aunque no creo que tenga sentido crear una ruta sin TODOS los atributos.solo con el set y get suficiente
    public  Ruta(int id/*, Usuario t*/){
        this.id = id;
        //transportista = t;
        origen = "";
        destino = "";
        fecha = "";
        horaInicio = "";
        horaFin = "";
        comentario = "";
    }

    public void setRuta(int id, String o, String d, String f,String h1,String h2 ,String com, /*Usuario t*/int idU ){
        this.id = id;
        origen = o;
        destino = d;
        fecha = f;
        horaInicio = h1;
        horaFin = h2;
        comentario = com;
        //transportista = t;
        this.idU=idU;
    }
    public Ruta(int id, String origen, String destino, String fecha,String h1,String h2 ,String coment, int idU){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.horaInicio = h1;
        this.horaFin = h2;
        this.comentario = coment;
        this.idU = idU;
    }
    public Ruta getRuta(){ return this;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    @Override
    public String toString() {
       return   "\nOrigen:\t\t" + getOrigen() +"\n"+
                "Destino:\t" + getDestino() + "\n"+
                "Fecha:\t" + getFecha() + "\n" +
                "Hora:\t" + getHoraInicio()+ "\n";
    }

}
