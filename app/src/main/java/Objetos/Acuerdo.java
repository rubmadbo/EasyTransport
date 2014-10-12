package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class Acuerdo {

    private int id;
    //private Usuario transportista;
    //private Usuario cliente;
    //private Ruta ruta;
    private String transportista;
    private String cliente;
    private int ruta;
    //private double precio; para el 1er Sprint es String
    private String precio;
    private String comentario;



    public Acuerdo(int id, String t, String c, int r, String p, String com){
        this.id = id;
        transportista= t;
        cliente = c;
        ruta = r;
        precio = p;
        comentario = com;

    }

    public Acuerdo getAcuerdo(){return this;}
    public void setAcuerdo(int id, String t, String c, int r, String p, String com){
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

    public String getTransportista() {
        return transportista;
    }

    public void setTransportista(String transportista) {
        this.transportista = transportista;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
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
