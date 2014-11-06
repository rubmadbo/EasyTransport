package Objetos;

import java.util.Date;

/**
 * Created by JD on 10/10/2014.
 */
public class Ruta {
    private int id;
    private String origen;
    private String destino;
    private String recogida;
    private String entrega;
    private String horaInicio;
    private String horaFin;  //revisar formato DATETIME y DATE
    private String fecha;
    private String comentario;

    private Usuario transportista;
    //en la base de datos la columna idACuerdo se pone a 0 si no se le pasa acuerdo porque no tiene

    private int idTransportista ;

    public Ruta(int id, String origen, String destino, String recogida, String entrega, String horaInicio, String horaFin, String fecha, String comentario, Usuario transportista, Acuerdo acuerdo) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.recogida = recogida;
        this.entrega = entrega;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.comentario = comentario;
        this.transportista = transportista;
        this.idAcuerdo = idAcuerdo;
        this.idTransportista = idTransportista;
        idAcuerdo= acuerdo.getId();
        idTransportista = transportista.getIdUsuario() ;
    }

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

    public String getRecogida() {
        return recogida;
    }

    public void setRecogida(String recogida) {
        this.recogida = recogida;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getTransportista() {
        return transportista;
    }

    public void setTransportista(Usuario transportista) {
        this.transportista = transportista;
    }

    public int getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(int idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
    }

    public int getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(int idTransportista) {
        this.idTransportista = idTransportista;
    }

}