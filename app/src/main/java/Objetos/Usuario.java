package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class Usuario {

    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private int telf;
    private String alias;
    private String pss;
    private tipoUsuario tipo;
    private Vehiculo vehiculo;

    public Usuario (String dni, String pss, tipoUsuario tipo, Vehiculo vehiculo){ //son PK y FKs, pss no lo es pero deberia
        this.dni = dni;
        this.tipo = tipo;
        this.vehiculo = vehiculo;
        this.pss = pss;
        direccion="";
        nombre="";
        apellido="";
        telf=0;
        alias="";
    }
    public Usuario getUsuario(){return this;}
    public void setUsuario(String dni, String nombre, String apellido, String direccion, int telf,
                           String alias, String pss,tipoUsuario tipo, Vehiculo vehiculo){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telf = telf;
        this.alias = alias;
        this.pss = pss;
        this.tipo = tipo;
        this.vehiculo = vehiculo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelf() {
        return telf;
    }

    public void setTelf(int telf) {
        this.telf = telf;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPss() {
        return pss;
    }

    public void setPss(String pss) {
        this.pss = pss;
    }

    public tipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(tipoUsuario tipo) {
        this.tipo = tipo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telf=" + telf +
                ", alias='" + alias + '\'' +
                ", pss='" + pss + '\'' +
                ", tipo=" + tipo +
                ", vehiculo=" + vehiculo +
                '}';
    }
}
