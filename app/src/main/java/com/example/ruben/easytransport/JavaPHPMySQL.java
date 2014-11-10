package com.example.ruben.easytransport;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
//He tenido q descargarme la librería desde aqui
// https://code.google.com/p/json-simple/downloads/detail?name=json-simple-1.1.1.jar&can=2&q=
//luego meter el jar en la capeta de proyecto; Project Structure-> app (modules) Dependencis add (2da opcion y pones la ruta)
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import Objetos.Acuerdo;
import Objetos.Ruta;

public class JavaPHPMySQL {

    /**
     * @param args the command line arguments
     */

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String SERVER_PATH = "http://easytransport1.esy.es/";


    //mirar lo de date y dateTime
    /*
    static int idRuta = 1;
    static String Origen = "Valencia";
    static String Destino = "Barcelona";
    static String Punto_recogida = "recogida";
    static String Punto_entrega = "entrega";
    static String HoraInicio = "2012-09-25 19:47:00.000";
    static String HoraFin = "2012-09-25 19:47:00.000";
    static String Fecha = "2014-10-12";
    static String Comentario = "esto es un comentario";
    static int idAcuerdo = 0;
    static int idTransportista = 1;*/

    /*public static void main(String[] args) {
        //Tenemos dos funciones, una para enviar por GET y otra para enviar por POST

        sendPost();
        //sendGet();

    }*/

 //scripts php a crear (entre parentesis van los argumentos q se le pasa al php)
    //borrarRuta.php (idRuta), getVehiculos.php, getUsuarios().php, getAcuerdosByRuta.php(idRuta), getRutasAnteriores.php (Date)
    //getRutasFavoritas(idUsuario).
    public static void insertarRuta(String Origen, String Destino, String Punto_recogida, String Punto_entrega,
                                    String HoraInicio, String HoraFin, String Fecha, String Comentario, int idTransportista) {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        //Añadimos el nombre, apellidos y email del usuario
        //es IMPORTANTE que pongamos lo que esta entre comillas igual que la columna de la BBDD
        jsonObj.put("idRuta", 0);
        jsonObj.put("Origen", Origen);
        jsonObj.put("Destino", Destino);
        jsonObj.put("Punto_recogida", Punto_recogida);//hay que quitarlo
        jsonObj.put("Punto_entrega", Punto_entrega);//hay que quitarlo
        jsonObj.put("HoraInicio", HoraInicio);
        jsonObj.put("HoraFin", HoraFin);//hay que quitarlo, para que vale esto????
        jsonObj.put("Fecha", Fecha);
        jsonObj.put("Comentario", Comentario);
        jsonObj.put("idTransportista", idTransportista);

        //Creamos una lista para almacenar el JSON
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObj));
        //Generamos el String JSON
        String jsonString = JSONValue.toJSONString(l);
        System.out.println("JSON GENERADO:");
        System.out.println(jsonString);
        System.out.println("");

        insercion(jsonString, "insertarRuta.php");
    }

    public static void insertarAcuerdo(double precio, String comentario, String estado, int idRuta, int idUsuario ){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idAcuerdo", 0);
        jsonObject.put("Precio", precio);
        jsonObject.put("Comentario", comentario);
        jsonObject.put("Estado", estado);
        jsonObject.put("idRuta", idRuta);
        jsonObject.put("idUsuario", idUsuario);

        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);

        insercion(jsonString, "insertarAcuerdo.php");

    }

    public static void insercion(String jsonString, String nombreScript) {
        try {
            //Codificar el json a URL
            jsonString = URLEncoder.encode(jsonString, "UTF-8");
            //Generar la URL
            String url = SERVER_PATH+nombreScript;
            //Creamos un nuevo objeto URL con la url donde queremos enviar el JSON
            URL obj = new URL(url);
            //Creamos un objeto de conexión
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //Añadimos la cabecera
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            //Creamos los parametros para enviar
            String urlParameters = "json="+jsonString;
            // Enviamos los datos por POST
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            //Capturamos la respuesta del servidor
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //Mostramos la respuesta del servidor por consola
            System.out.println(response);
            //cerramos la conexión
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



       /* public static void main(String[] args) {
            //Obtenemos el JSON
            String json = getAllRutas();
            //Lo mostramos
            mostrarAllRutas(json);

        }*/

    public static String getAllRutas(){

            StringBuffer response = null;

            try {
                //Generar la URL
                String url = SERVER_PATH+"getAllRutas.php";
                //Creamos un nuevo objeto URL con la url donde pedir el JSON
                URL obj = new URL(url);
                //Creamos un objeto de conexión
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                //Añadimos la cabecera
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                // Enviamos la petición por POST
                con.setDoOutput(true);
                //Capturamos la respuesta del servidor
                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                //Mostramos la respuesta del servidor por consola
                System.out.println("Respuesta del servidor: "+response);
                System.out.println();
                //cerramos la conexión
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response.toString();
        }

    public static ArrayList<Ruta> mostrarAllRutas(String json){
            System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");
            //Crear un Objeto JSON a partir del string JSON
            ArrayList listaRutas = new ArrayList();
            Object jsonObject =JSONValue.parse(json.toString());
            //Convertir el objeto JSON en un array
            JSONArray array=(JSONArray)jsonObject;
            //Iterar el array y extraer la información
            for(int i=0;i<array.size();i++){
                JSONObject row =(JSONObject)array.get(i);
                String idRuta = row.get("idRuta").toString();
                String Origen = row.get("Origen").toString();
                String Destino = row.get("Destino").toString();
                String Punto_entrega = row.get("Punto_entrega").toString();
                String Punto_recogida = row.get("Punto_recogida").toString();
                String HoraInicio = row.get("HoraInicio").toString();
                String HoraFin = row.get("HoraFin").toString();
                String Fecha = row.get("Fecha").toString();
                String Comentario = row.get("Comentario").toString();
                String idTransportista = row.get("idTransportista").toString();

                Ruta ruta = new Ruta(Integer.parseInt(idRuta),Origen,Destino,Punto_recogida,Punto_entrega,HoraInicio,
                        HoraFin,Fecha,Comentario);

                listaRutas.add(ruta);

                //Mostrar la información en pantalla
                System.out.println("idRuta: "+idRuta+" Origen: "+Origen+" Destino: "+Destino+" Punto de entrega: "
                        +Punto_entrega+" Punto de recogida: "+Punto_recogida+" Hora de Inicio: "+HoraInicio+" Hora Fin: "+HoraFin+
                        " Fecha: "+Fecha+" Comentario: "+Comentario+" idTransportista: "+idTransportista);
            }
        return listaRutas;
        }

    }



    /*
    public static void sendGet(){
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        //Añadimos el nombre, apellidos y email del usuario
        jsonObj.put("idRuta",idRuta);
        jsonObj.put("Origen", Origen);
        jsonObj.put("Destino", Destino);
        jsonObj.put("Punto_recogida", Punto_recogida);
        jsonObj.put("Punto_entrega", Punto_entrega);
        jsonObj.put("HoraInicio", HoraInicio);
        jsonObj.put("HoraFin", HoraFin);
        jsonObj.put("Fecha", Fecha);
        jsonObj.put("Comentario", Comentario);
        jsonObj.put("idAcuerdo", idAcuerdo);
        jsonObj.put("idTransportista", idTransportista);
        //Creamos una lista para almacenar el JSON
        List  l = new LinkedList();
        l.addAll(Arrays.asList(jsonObj));
        //Generamos el String JSON
        String jsonString = JSONValue.toJSONString(l);
        System.out.println("JSON GENERADO:");
        System.out.println(jsonString);
        System.out.println("");

        try{
            //Codificar el json a URL
            jsonString = URLEncoder.encode(jsonString, "UTF-8");
            //Generar la URL
            String url = SERVER_PATH+"listenGet.php?json="+jsonString;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/


