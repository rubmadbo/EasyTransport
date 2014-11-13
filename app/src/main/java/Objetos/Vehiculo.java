package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class Vehiculo {
    private int idVehiculo;
    private String matricula;
    private String marca;
    private String modelo;
    private Usuario conductor;
    private int Capacidad;


    public Vehiculo(int idVehiculo, String matricula, String marca, String modelo, Usuario conductor, int capacidad) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.conductor = conductor;
        Capacidad = capacidad;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Usuario getConductor() {
        return conductor;
    }

    public void setConductor(Usuario conductor) {
        this.conductor = conductor;
    }

    public int getCapacidad() {
        return Capacidad;
    }

    public void setCapacidad(int capacidad) {
        Capacidad = capacidad;
    }

    @Override
    public String toString() {
        return getMarca() + " "+ getModelo();
    }
}