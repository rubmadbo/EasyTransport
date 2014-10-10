package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class tipoUsuario {

    private int id;
    private String nombre;
    private String descripcion;


    public void tipoUsuario (int i){
        this.id= i;
        nombre="";
        descripcion= "";
    }
    public tipoUsuario getTipoUsuario(){return this;}
    public void setUsuario(int i, String nombre, String descripcion){
        id = i;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
