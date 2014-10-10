package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class Acuerdo {

    private int id;
    private Usuario transportista;
    private Usuario cliente;
    private Ruta ruta;
    private double precio;
    private String comentario;

    public void Acuerdo(int id, Usuario t, Usuario c, Ruta r, double p, String com){
        id = id;
        transportista= t;
        cliente = c;
        ruta = r;
        precio = p;
        comentario = com;

    }

    public Acuerdo getAcuerdo(){return this;}
    public void setAcuerdo(int id, Usuario t, Usuario c, Ruta r, double p, String com){
        id = id;
        transportista= t;
        cliente = c;
        ruta = r;
        precio = p;
        comentario = com;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getTransportista() {
        return transportista;
    }

    public void setTransportista(Usuario transportista) {
        this.transportista = transportista;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Acuerdo{" +
                "id=" + id +
                ", transportista=" + transportista +
                ", cliente=" + cliente +
                ", ruta=" + ruta +
                ", precio=" + precio +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
