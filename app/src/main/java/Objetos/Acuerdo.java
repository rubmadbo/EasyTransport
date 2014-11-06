package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class Acuerdo {

    private int id;
    private Usuario transporstista;
    private Usuario cliente;
    private Ruta ruta;
    private Double precio;
    private String comentario; //puede ser null
    private String estado; //pendiente , aceptado, rechazado

    public Acuerdo(int id, Usuario transporstista, Usuario cliente, Ruta ruta, Double precio, String comentario, String estado) {
        this.id = id;
        this.transporstista = transporstista;
        this.cliente = cliente;
        this.ruta = ruta;
        this.precio = precio;
        this.comentario = comentario;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getTransporstista() {
        return transporstista;
    }

    public void setTransporstista(Usuario transporstista) {
        this.transporstista = transporstista;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Acuerdo{" +
                "id=" + id +
                ", transporstista=" + transporstista +
                ", cliente=" + cliente +
                ", ruta=" + ruta +
                ", precio=" + precio +
                ", comentario='" + comentario + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
