package Objetos;

/**
 * Created by JD on 10/10/2014.
 */
public class Acuerdo {

    int id;
    Usuario transportista;
    Usuario cliente;
    Ruta ruta;
    double precio;
    String comentario;

    public void Acuerdo(int id, Usuario t, Usuario c, Ruta r, double p, String com){
        id = id;
        transportista= t;
        cliente = c;
        ruta = r;
        precio = p;
        comentario = com;

    }

    public Acuerdo getAcuerdo(){return this;}
    public void setAcuerdo(int id, Usuario t, Usuario c, Ruta r, double p, String com){
        id = id;
        transportista= t;
        cliente = c;
        ruta = r;
        precio = p;
        comentario = com;
    }
}
