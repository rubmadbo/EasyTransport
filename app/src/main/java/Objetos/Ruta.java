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
    private Date HoraInicio; //para guardar en bbdd tiene que tener ese formato "2012-09-25 19:47:00.000";
    private String horaIniString;
    private String horaFinString;
    private Date Fecha; //para guardar en bbdd tiene que tener ese formato "2014-10-12";
    private String fechaString;
    private String Comentario;
    private List<Acuerdo> acuerdos;
    private Usuario transportista;
    private boolean favorita;
    //en la base de datos la columna idACuerdo se pone a 0 si no se le pasa acuerdo porque no tiene


    public Ruta(int idRuta, boolean favorita, String origen, String destino, Date horaInicio, Date fecha, String comentario) {
        this.idRuta = idRuta;
        this.favorita = favorita;
        this.Origen = origen;
        this.Destino = destino;
        HoraInicio = horaInicio;
        Fecha = fecha;
        Comentario = comentario;
    }

    public Ruta(int idRuta, String origen, String destino, String punto_recogida, String punto_entrega, String horaInicio, String horaFin, String fecha, String comentario) {
        this.idRuta = idRuta;
        Origen = origen;
        Destino = destino;
        horaIniString = horaInicio;
        horaFinString = horaFin;
        fechaString = fecha;
        Comentario = comentario;
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

    public Date getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        HoraInicio = horaInicio;
    }


    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public List<Acuerdo> getAcuerdos() {
        return acuerdos;
    }

    public void setAcuerdos(List<Acuerdo> acuerdos) {
        this.acuerdos = acuerdos;
    }

    public Usuario getTransportista() {
        return transportista;
    }

    public void setTransportista(Usuario transportista) {
        this.transportista = transportista;
    }

    public String getHoraIniString() {
        return horaIniString;
    }

    public String getHoraFinString() {
        return horaFinString;
    }

    public String getFechaString() {
        return fechaString;
    }

    public String toString(){
        return  "\n"+"Origen: " + Origen + "\n" +
                "Destino: " + Destino + "\n" +
                "Hora inicio: " + horaIniString + "\n" +
                "Hora fin: " + getHoraFinString() + "\n" +
                "Fecha: " + fechaString + "\n" +
                "Comentario: " + Comentario +"\n";
    }

}