package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class tipoUsuario {

    private int id;
    private String nombre;
    private String descripcion;


    public tipoUsuario (int i){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "tipoUsuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
