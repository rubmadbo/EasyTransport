package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class Usuario {//*modificaaar para idUsuario*/
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String Rol;
    private String password;
    private Vehiculo vehiculo;
    private Ruta ruta;
    private Acuerdo acuerdo;


    public Usuario(int idUsuario, String nombre, String apellido, String rol, String password, Vehiculo vehiculo, Ruta ruta, Acuerdo acuerdo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        Rol = rol;
        this.password = password;
        this.vehiculo = vehiculo;
        this.ruta = ruta;
        this.acuerdo = acuerdo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Acuerdo getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(Acuerdo acuerdo) {
        this.acuerdo = acuerdo;
    }

    @Override
    public String toString() {//modificar vehiculo.getid ruta y acuerdo (añadir id en cada una d las clases)
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", Rol='" + Rol + '\'' +
                ", password='" + password + '\'' +
                ", vehiculo=" + vehiculo +
                ", ruta=" + ruta +
                ", acuerdo=" + acuerdo +
                '}';
    }
}