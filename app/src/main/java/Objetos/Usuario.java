package Objetos;

import java.util.List;

/**
 * Created by JD on 10/10/2014.
 */
public class Usuario {//*modificaaar para idUsuario*/
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String Rol;
    private String password;
    private String email;
    private List<Vehiculo> vehiculos;
    private List<Ruta> ruta;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private List<Acuerdo> acuerdos;

    public Usuario(int idUsuario, String nombre, String apellido, String rol, String password, List<Vehiculo> vehiculos,
                 List<Ruta> ruta, List<Acuerdo> acuerdos, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        Rol = rol;
        this.password = password;
        this.vehiculos = vehiculos;
        this.ruta = ruta;
        this.acuerdos = acuerdos;
        this.email=email;
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

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Ruta> getRuta() {
        return ruta;
    }

    public void setRuta(List<Ruta> ruta) {
        this.ruta = ruta;
    }

    public List<Acuerdo> getAcuerdos() {
        return acuerdos;
    }

    public void setAcuerdos(List<Acuerdo> acuerdos) {
        this.acuerdos = acuerdos;
    }

    public String toString(){
        return getNombre() + " " + getApellido();
    }
}