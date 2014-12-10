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
    private String MotivoRechazo;

    public Acuerdo(int idAcuerdo, Double precio, String comentario, String estado, Ruta ruta, String punto_recogida, String punto_entrega, Usuario remitente, int leido, String motivoRechazo) {
        this.idAcuerdo = idAcuerdo;
        this.precio = precio;
        this.comentario = comentario;
        this.estado = estado;
        this.ruta = ruta;
        Punto_recogida = punto_recogida;
        Punto_entrega = punto_entrega;
        this.remitente = remitente;
        this.leido = leido;
        MotivoRechazo = motivoRechazo;
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

    public String getMotivoRechazo() {
        return MotivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        MotivoRechazo = motivoRechazo;
    }

    @Override
    public String toString() {
        if(estado.equals("rechazado")){
            return  "\n" + "*********  "+ getRuta().getOrigen() +" - " + getRuta().getDestino() + "  *********\n" +
                    "\t\tPunto de recogida: " + Punto_recogida + "\n" +
                    "\t\tPunto de entrega: " + Punto_entrega + "\n" +
                    "\t\tPrecio: " + precio + " €\n" +
                    "\t\tComentario: " + comentario + "\n" +
                    "\t\tEstado: " + estado + "\n"+
                    "\t\tMotivo del Rechazo: " + MotivoRechazo +"\n";
        }
        else{
            return  "\n" + "*********  "+ getRuta().getOrigen() +" - " + getRuta().getDestino() + "  *********\n" +
                    "\t\tPunto de recogida: " + Punto_recogida + "\n" +
                    "\t\tPunto de entrega: " + Punto_entrega + "\n" +
                    "\t\tPrecio: " + precio + " €\n" +
                    "\t\tComentario: " + comentario + "\n" +
                    "\t\tEstado: " + estado + "\n";
        }

    }
}
