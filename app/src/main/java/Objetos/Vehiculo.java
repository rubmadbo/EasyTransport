package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class Vehiculo {

    private String matricula;
    private String marca;
    private String modelo;

    public void Vehiculo(String matricula){//matricula es PK en la bbdd
        this.matricula = matricula;
        marca="";
        modelo="";
    }
    public Vehiculo getVehiculo(){return this;}
    public void setVehiculo(String matricula, String marca, String modelo){
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
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

    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
