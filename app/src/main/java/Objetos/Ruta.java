package Objetos;

import java.util.Date;
import java.util.List;

/**
 * Created by JD on 10/10/2014.
 */
public class Ruta {
    private int idRuta;
    private String Origen;
    private String Destino ;
    private String HoraInicio; //para guardar en bbdd tiene que tener ese formato "2012-09-25 19:47:00.000";
    private String Fecha; //para guardar en bbdd tiene que tener ese formato "2014-10-12";
    private String Comentario;
    private Usuario transportista;
    private int favorita; //0 = false
    //en la base de datos la columna idACuerdo se pone a 0 si no se le pasa acuerdo porque no tiene


    public Ruta(int idRuta, String origen, String destino, String horaInicio, String fecha, String comentario, Usuario transportista, int favorita) {
        this.idRuta = idRuta;
        Origen = origen;
        Destino = destino;
        HoraInicio = horaInicio;
        Fecha = fecha;
        Comentario = comentario;
        this.transportista = transportista;
        this.favorita = favorita;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String origen) {
        Origen = origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public Usuario getTransportista() {
        return transportista;
    }

    public void setTransportista(Usuario transportista) {
        this.transportista = transportista;
    }

    public int getFavorita() {
        return favorita;
    }

    public void setFavorita(int favorita) {
        this.favorita = favorita;
    }

    @Override
    public String toString() {
        return  "\nOrigen: " + Origen + "\n" +
                "Destino: " + Destino + "\n" +
                "Hora Salida: " + HoraInicio + "\n" +
                "Fecha: " + Fecha + "\n" +
                "Comentario: " + Comentario + "\n" ;
    }
}