package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class Acuerdo {

    private int idAcuerdo;
    private Double precio;
    private String comentario; //puede ser null
    private String estado; //pendiente , aceptado, rechazado
    private Ruta ruta;
    private String Punto_recogida;
    private String Punto_entrega;
    private Usuario remitente; //no esta en bbdd
    private int leido;

    public Acuerdo(int idAcuerdo, Double precio, String comentario, String estado, Ruta ruta, String punto_recogida, String punto_entrega, Usuario remitente, int leido) {
        this.idAcuerdo = idAcuerdo;
        this.precio = precio;
        this.comentario = comentario;
        this.estado = estado;
        this.ruta = ruta;
        Punto_recogida = punto_recogida;
        Punto_entrega = punto_entrega;
        this.remitente = remitente;
        this.leido = leido;
    }

    public int getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(int idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
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

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public String getPunto_recogida() {
        return Punto_recogida;
    }

    public void setPunto_recogida(String punto_recogida) {
        Punto_recogida = punto_recogida;
    }

    public String getPunto_entrega() {
        return Punto_entrega;
    }

    public void setPunto_entrega(String punto_entrega) {
        Punto_entrega = punto_entrega;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public void setRemitente(Usuario remitente) {
        this.remitente = remitente;
    }

    public int getLeido() {
        return leido;
    }

    public void setLeido(int leido) {
        this.leido = leido;
    }


    @Override
    public String toString() {
        return  //"\n" + "Ruta: "+ getRuta().getOrigen() +" - " + getRuta().getDestino() + "\n" +
                "\n" +"Punto de recogida: " + Punto_recogida + "\n" +
                "Punto de entrega: " + Punto_entrega + "\n" +
                "Precio: " + precio + "\n" +
                "Comentario: " + comentario + "\n" +
                "Estado: " + estado + "\n";
    }
}
