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

    public void Usuario (String dni, String pss, tipoUsuario tipo, Vehiculo vehiculo){ //son PK y FKs, pss no lo es pero deberia
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
}
