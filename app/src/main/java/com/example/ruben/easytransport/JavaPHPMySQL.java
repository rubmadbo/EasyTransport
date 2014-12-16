package com.example.ruben.easytransport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Objetos.Acuerdo;
import Objetos.Ruta;
import Objetos.Usuario;
import Objetos.Vehiculo;

//He tenido q descargarme la librería desde aqui
// https://code.google.com/p/json-simple/downloads/detail?name=json-simple-1.1.1.jar&can=2&q=
//luego meter el jar en la capeta de proyecto; Project Structure-> app (modules) Dependencis add (2da opcion y pones la ruta)

public class JavaPHPMySQL {

    /**
     * @param args the command line arguments
     */

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String SERVER_PATH = "http://easytransport1.esy.es/";

// ENVIA UN JSON CON LAS VARIABLES AL PHP Y EJECUTA UNA CONSULTA SQL , NO RECOGE VALORES
    //SE PUEDE USAR PARA INSERTAR, DELETE, ...
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

    //ENVIA VARIABLES EN JSON, EJECUTA SCRIPT Y RECUPERA DATOS EN STRING
    //CUANDO RECUPERA LOS DATOS HAY QUE LLAMAR A UN METODO MOSTRAR, EJEMPLO: getVehiculoByUserId
    public static String getDataFromFilter(String jsonString, String nombreScript){
        //jdcc primero enviamos las variables en el json
        StringBuffer response = null;

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


            //jdcc: ahora recuperamos los datos
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

      public static void insertarRuta(String Origen, String Destino,String HoraInicio, String Fecha, String Comentario, int idTransportista) {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        //Añadimos el nombre, apellidos y email del usuario
        //es IMPORTANTE que pongamos lo que esta entre comillas igual que la columna de la BBDD
        jsonObj.put("idRuta", 0);
        jsonObj.put("Favorita", 0);
        jsonObj.put("Origen", Origen);
        jsonObj.put("Destino", Destino);
        jsonObj.put("HoraInicio", HoraInicio);
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

    public static void cambiarPass(int idUsuario, String pass) {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        //Añadimos el nombre, apellidos y email del usuario
        //es IMPORTANTE que pongamos lo que esta entre comillas igual que la columna de la BBDD
        jsonObj.put("idUsuario", idUsuario);
        jsonObj.put("Password", pass);


        //Creamos una lista para almacenar el JSON
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObj));
        //Generamos el String JSON
        String jsonString = JSONValue.toJSONString(l);
        System.out.println("JSON GENERADO:");
        System.out.println(jsonString);
        System.out.println("");

        insercion(jsonString, "cambiarPass.php");
    }
    public static void enviarEmailCambioPass(String email, String pass) {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        //Añadimos el nombre, apellidos y email del usuario
        //es IMPORTANTE que pongamos lo que esta entre comillas igual que la columna de la BBDD
        jsonObj.put("email", email);
        jsonObj.put("Password", pass);


        //Creamos una lista para almacenar el JSON
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObj));
        //Generamos el String JSON
        String jsonString = JSONValue.toJSONString(l);
        System.out.println("JSON GENERADO:");
        System.out.println(jsonString);
        System.out.println("");

        insercion(jsonString, "enviarEmailCambioPass.php");
    }

    public static void enviarEmail(String email, String Mensaje, String Asunto) {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        //Añadimos el nombre, apellidos y email del usuario
        //es IMPORTANTE que pongamos lo que esta entre comillas igual que la columna de la BBDD
        jsonObj.put("email", email);
        jsonObj.put("Mensaje", Mensaje);
        jsonObj.put("Asunto", Asunto);

        //Creamos una lista para almacenar el JSON
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObj));
        //Generamos el String JSON
        String jsonString = JSONValue.toJSONString(l);
        System.out.println("JSON GENERADO:");
        System.out.println(jsonString);
        System.out.println("");

        insercion(jsonString, "enviarEmail.php");
    }


    public static void insertarUsuario(String nombre, String apellido, String rol, String pass, String email) {
        //Creamos un objeto JSON
        JSONObject jsonObj = new JSONObject();
        //Añadimos el nombre, apellidos y email del usuario
        //es IMPORTANTE que pongamos lo que esta entre comillas igual que la columna de la BBDD
        jsonObj.put("idUsuario", 0);
        jsonObj.put("Nombre", nombre);
        jsonObj.put("Apellido", apellido);
        jsonObj.put("Rol", rol);
        jsonObj.put("email", email);
        jsonObj.put("Password", pass);

        //Creamos una lista para almacenar el JSON
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObj));
        //Generamos el String JSON
        String jsonString = JSONValue.toJSONString(l);
        System.out.println("JSON GENERADO:");
        System.out.println(jsonString);
        System.out.println("");

        insercion(jsonString, "insertarUsuario.php");
    }

    public static void insertarAcuerdoenUsuariohasAcuerdo(int idAcuerdo, int idUsuario){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idAcuerdo", idAcuerdo);
        jsonObject.put("idUsuario", idUsuario);

        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);

        insercion(jsonString, "insertarAcuerdoenUsuariohasAcuerdo.php");
    }


    public static void insertarAcuerdo(int idAcuerdo,double precio, String comentario, String estado, int idRuta, int idUsuario, String Punto_recogida, String Punto_entrega, String mRechazo){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idAcuerdo", idAcuerdo);
        jsonObject.put("Precio", precio);
        jsonObject.put("Comentario", comentario);
        jsonObject.put("Estado", estado);
        jsonObject.put("idRuta", idRuta);
        jsonObject.put("idUsuario", idUsuario);
        jsonObject.put("Punto_recogida", Punto_recogida);
        jsonObject.put("Punto_entrega",Punto_entrega);
        jsonObject.put("Leido",0);
        jsonObject.put("MotivoRechazo",mRechazo);

        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);

        insercion(jsonString, "insertarAcuerdo.php");

    }

    //IMPORTANTE: tener en cuenta que no se puede borrar ruta si tiene acuerdo asociado!
    public static void borrarRuta(int idRuta){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idRuta", idRuta);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);

        insercion(jsonString, "borrarRuta.php"); //puede usar insercion porque solo recibe una variable y ejecuta una query
    }

    public static Usuario getUsuarioByEmail(String email){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        String json= getDataFromFilter(jsonString, "getUsuarioByEmail.php");
        return mostrarUsuario(json);
    }

    public static Usuario getUsuarioByUserId(int idUsuario){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idUsuario", idUsuario);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
        String json= getDataFromFilter(jsonString, "getUsuarioByUserId.php");
        return mostrarUsuario(json);
    }

    public static Ruta getRutaByRutaId(int idRuta){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idRuta", idRuta);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
        String json= getDataFromFilter(jsonString, "getRutaById.php");
        return mostrarRuta(json);
    }

    public static ArrayList<Acuerdo> getAcuerdosByUserId(int idUsuario){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idUsuario", idUsuario);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
        String json= getDataFromFilter(jsonString, "getAcuedosByUserId.php");
        return mostrarAcuerdos(json);
    }

    /**
     * Devuelve los acuerdos del transportista
     * @param idUsuario
     * @return ArrayList<Acuerdo>
     */
    public static ArrayList<Acuerdo> getAcuerdosByTransId(int idUsuario){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idUsuario", idUsuario);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
        String json= getDataFromFilter(jsonString, "getAcuerdosByTransId.php");
        return mostrarAcuerdos(json);
    }

    public static int getRemitenteByAcuerdoId(int idAcuerdo){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idAcuerdo", idAcuerdo);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
        String json= getDataFromFilter(jsonString, "getRemitenteByAcuerdoId.php");

        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");
        Object jsonObject1 =JSONValue.parse(json.toString());
        JSONArray array=(JSONArray)jsonObject1;
        String idUsuario;
            JSONObject row = (JSONObject) array.get(0);
            idUsuario = row.get("idUsuario").toString();

        return Integer.parseInt(idUsuario);
    }

    public static void updateUsuario(int idUsuario, String nombre, String apellido, String rol, String pass, String email){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idUsuario", idUsuario);
        jsonObject.put("Nombre", nombre);
        jsonObject.put("Apellido", apellido);
        jsonObject.put("Rol", rol);
        jsonObject.put("email", email);
        jsonObject.put("Password", pass);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
        insercion(jsonString, "updateUsuario.php");
    }



    public static void updateEstadoAcuerdo(String estado, int idAcuerdo, String motivoRechazo){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Estado", estado);
        jsonObject.put("idAcuerdo", idAcuerdo);
        jsonObject.put("MotivoRechazo", motivoRechazo);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
        insercion(jsonString, "updateEstadoAcuerdo.php");
    }

    public static ArrayList<Ruta> getRutasByUserId(int idUsuario){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idUsuario", idUsuario);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
        String json= getDataFromFilter(jsonString, "getRutasByUserId.php");
        return mostrarAllRutas(json);
    }


    public static ArrayList<Vehiculo> getVehiculoByUserId(int idUsuario){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idUsuario", idUsuario);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
       String json= getDataFromFilter(jsonString, "getVehiculoByUserId.php");
       return mostrarVehiculos(json);
    }

    public static ArrayList<Acuerdo> getAcuerdosByRutaId(int idRuta){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idRuta", idRuta);
        List l = new LinkedList();
        l.addAll(Arrays.asList(jsonObject));

        String jsonString = JSONValue.toJSONString(l);
        //el script obtiene la variable idUsuario hace consulta y recupera datos
        String json= getDataFromFilter(jsonString, "getAcuerdosByRutaId.php");
        return mostrarAcuerdos(json);
    }

    public static String getNumeroAcuerdos(){

        StringBuffer response = null;

        try {
            //Generar la URL
            String url = SERVER_PATH+"getNumeroAcuerdos.php";
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

  public static boolean loginSuccess(String email, String pass){
      boolean result;
      //Usuario u = new Usuario(0,"","","","tuviejaa",null,null,null,"");
      String correoUsuario="tuvieja";
      try {
          correoUsuario = getUsuarioByEmail(email).getPassword();
      }catch (Exception e){}

      result =  correoUsuario.equals(pass);
      return result;
  }

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

    public static Usuario mostrarUsuario(String json){
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json.toString());
        //Convertir el objeto JSON en un array
        JSONArray array=(JSONArray)jsonObject;
        Usuario usuario=null;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            JSONObject row =(JSONObject)array.get(i);
            String idUsuario = row.get("idUsuario").toString();
            String Nombre = row.get("Nombre").toString();
            String Apellido = row.get("Apellido").toString();
            String Rol = row.get("Rol").toString();
            String Password = row.get("Password").toString();
            String email = row.get("email").toString();

            usuario = new Usuario(Integer.parseInt(idUsuario), Nombre,Apellido,Rol,Password,null,null,null,email);
        }
        return usuario;
    }

    public static ArrayList<Acuerdo> mostrarAcuerdos(String json){
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");
        //Crear un Objeto JSON a partir del string JSON
        ArrayList listaAcuerdos = new ArrayList();
        Object jsonObject =JSONValue.parse(json.toString());
        //Convertir el objeto JSON en un array
        JSONArray array=(JSONArray)jsonObject;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            JSONObject row =(JSONObject)array.get(i);
            String idAcuerdo = row.get("idAcuerdo").toString();
            String Precio = row.get("Precio").toString();
            String Comentario = row.get("Comentario").toString();
            String Estado = row.get("Estado").toString();
            String idRuta = row.get("idRuta").toString(); //se mete a null porque se sabe de que ruta es
            String Punto_recogida = row.get("Punto_recogida").toString();
            String Punto_entrega = row.get("Punto_entrega").toString();
            String Leido = row.get("Leido").toString();
            String MotivoRechazo = row.get("MotivoRechazo").toString();

           Ruta ruta = getRutaByRutaId(Integer.parseInt(idRuta));
           Acuerdo acuerdo = new Acuerdo(Integer.parseInt(idAcuerdo),Double.parseDouble(Precio),Comentario ,Estado,ruta,Punto_recogida,Punto_entrega,null,Integer.parseInt(Leido), MotivoRechazo);
           listaAcuerdos.add(acuerdo);

        }
        return listaAcuerdos;
    }

    public static ArrayList<Vehiculo> mostrarVehiculos(String json){
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");
        //Crear un Objeto JSON a partir del string JSON
        ArrayList listaVehiculos = new ArrayList();
        Object jsonObject =JSONValue.parse(json.toString());
        //Convertir el objeto JSON en un array
        JSONArray array=(JSONArray)jsonObject;
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            JSONObject row =(JSONObject)array.get(i);
            String idVehiculo = row.get("idVehiculo").toString();
            String Matricula = row.get("Matricula").toString();
            String Marca = row.get("Marca").toString();
            String Modelo = row.get("Modelo").toString();
            //String idUsuario = row.get("idUsuario").toString(); //se mete a null porque se sabe a que usuario pertenece
            String Capacidad = row.get("Capacidad").toString();

            Vehiculo vehiculo = new Vehiculo(Integer.parseInt(idVehiculo),Matricula,Marca ,Modelo,null,Integer.parseInt(Capacidad));

            listaVehiculos.add(vehiculo);


        }
        //Mostrar la información en pantalla
        // for(int j=0;j<listaVehiculos.size();j++){listaVehiculos.toString();}
        return listaVehiculos;
    }

    public static String mostrarNumeroAcuerdos(String json){ System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");
        //Crear un Objeto JSON a partir del string JSON
        Object jsonObject =JSONValue.parse(json.toString());
        //Convertir el objeto JSON en un array
        JSONArray array=(JSONArray)jsonObject;
        String cuenta="";
        //Iterar el array y extraer la información
        for(int i=0;i<array.size();i++){
            JSONObject row =(JSONObject)array.get(i);
            cuenta = row.get("COUNT(*)").toString();

        }
        return cuenta;
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
                String HoraInicio = row.get("HoraInicio").toString();
                String Fecha = row.get("Fecha").toString();
                String Comentario = row.get("Comentario").toString();
                String idTransportista = row.get("idTransportista").toString();
                String Favorita = row.get("Favorita").toString();

                Usuario transportista = getUsuarioByUserId(Integer.parseInt(idTransportista));
                Ruta ruta = new Ruta(Integer.parseInt(idRuta),Origen,Destino,HoraInicio,Fecha,Comentario,transportista,Integer.parseInt(Favorita));

                listaRutas.add(ruta);
            }
        return listaRutas;
        }

    public static Ruta mostrarRuta(String json){
        System.out.println("INFORMACIÓN OBTENIDA DE LA BASE DE DATOS:");
        //Crear un Objeto JSON a partir del string JSON
        Ruta ruta = null;
        Object jsonObject =JSONValue.parse(json.toString());
        //Convertir el objeto JSON en un array
        JSONArray array=(JSONArray)jsonObject;
        //Iterar el array y extraer la información
        if(!array.isEmpty()){
            JSONObject row =(JSONObject)array.get(0);
            String idRuta = row.get("idRuta").toString();
            String Origen = row.get("Origen").toString();
            String Destino = row.get("Destino").toString();
            String HoraInicio = row.get("HoraInicio").toString();
            String Fecha = row.get("Fecha").toString();
            String Comentario = row.get("Comentario").toString();
            String idTransportista = row.get("idTransportista").toString();
            String Favorita = row.get("Favorita").toString();

            Usuario transportista = getUsuarioByUserId(Integer.parseInt(idTransportista));
            ruta = new Ruta(Integer.parseInt(idRuta),Origen,Destino,HoraInicio,Fecha,Comentario,transportista,Integer.parseInt(Favorita));
        }
        return ruta;
    }



    }



