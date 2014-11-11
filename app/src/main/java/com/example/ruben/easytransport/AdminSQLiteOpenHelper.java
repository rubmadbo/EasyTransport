package com.example.ruben.easytransport;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Objetos.Acuerdo;
import Objetos.Ruta;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    //llamamos al constructor
    public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    //creamos la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Acuerdo(idAcuerdo integer primary key, usuarioTransportista integer, " +
                "usuarioCliente integer, ruta integer, precio integer, comentario text, FOREIGN KEY(usuarioTransportista) " +
                "REFERENCES Usuarios(DNI),FOREIGN KEY(usuarioCliente) REFERENCES Usuarios(DNI), " +
                "FOREIGN KEY(ruta) REFERENCES Ruta(idRuta))");
//las horas y fechas en text? o cambiamos formato
        db.execSQL("create table Ruta(idRuta integer primary key, inicio text, final text, horaInicio text, horaFinal text," +
                "fecha text, comentarios text, transportista integer, FOREIGN KEY(transportista) REFERENCES Usuarios(DNI))");

        db.execSQL("create table TipoUsuario(idTipoUsuario integer primary key, nombre text, descripcion text)");

        db.execSQL("create table Vehiculo(matricula text primary key, marca String, modelo String)");

        db.execSQL("create table Usuarios(DNI integer primary key, nombre text, apellidos text, direccion text, telefono integer, " +
                "alias text, contrasenya text, tipoUsuario integer, vehiculo integer, FOREIGN KEY(tipoUsuario) " +
                "REFERENCES TipoUsuario(idTipoUsuario),FOREIGN KEY(vehiculo) REFERENCES Vehiculo(matricula))");


        db.execSQL("insert into Vehiculo values('1122FFF','Opel','Astra')");
        db.execSQL("insert into Vehiculo values('2233HHH','Seat','Panda')");
        db.execSQL("insert into Vehiculo values('3344ZZZ','Seat','Ibiza')");
        db.execSQL("insert into Vehiculo values('4455GHQ','Ford','Fiesta')");

    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists Vehiculo");
        db.execSQL("drop table if exists Acuerdo");
        db.execSQL("drop table if exists Ruta");
        db.execSQL("drop table if exists TipoUsuario");
        db.execSQL("drop table if exists Usuarios");
        db.execSQL("drop table if exists Vehiculo");

        db.execSQL("create table Acuerdo(idAcuerdo integer primary key, usuarioTransportista integer, " +
                "usuarioCliente integer, ruta integer, precio integer, comentario text, FOREIGN KEY(usuarioTransportista) " +
                "REFERENCES Usuarios(DNI),FOREIGN KEY(usuarioCliente) REFERENCES Usuarios(DNI), " +
                "FOREIGN KEY(ruta) REFERENCES Ruta(idRuta))");

        db.execSQL("create table Ruta(idRuta integer primary key, inicio text, final text, horaInicio text, horaFinal text," +
                "fecha text, comentarios text, transportista integer, FOREIGN KEY(transportista) REFERENCES Usuarios(DNI))");

        db.execSQL("create table TipoUsuario(idTipoUsuario integer primary key, nombre text, descripcion text)");

        db.execSQL("create table Vehiculo(matricula text primary key, marca String, modelo String)");

        db.execSQL("create table Usuarios(DNI integer primary key, nombre text, apellidos text, direccion text, telefono integer, " +
                "alias text, contrasenya text, tipoUsuario integer, vehiculo integer, FOREIGN KEY(tipoUsuario) " +
                "REFERENCES TipoUsuario(idTipoUsuario),FOREIGN KEY(vehiculo) REFERENCES Vehiculo(matricula))");

        //JD: habría que hacer db.close(); ??¿

    }

    public List<String> getVehiculos() {

        List<String> vehiculos = new ArrayList<String>();
        String selectQuery = "SELECT * FROM Vehiculo";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                vehiculos.add(cursor.getString(1) + " " + cursor.getString(2));
                //System.out.println("Elementos de la base de datos -> " + cursor.getString(1)+" "+cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return vehiculos;
    }


    public List<Ruta> getRutas() {

        List<Ruta> rutas = new ArrayList<Ruta>();
        String selectQuery = "SELECT * FROM Ruta";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
             //  Ruta ruta= new Ruta(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                  //     cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
             //  rutas.add(ruta);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return rutas;

    }
    public List<Acuerdo> getAcuerdos() {

        List<Acuerdo> acuerdos = new ArrayList<Acuerdo>();
        String selectQuery = "SELECT * FROM Acuerdo";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
              //  Acuerdo acuerdo= new Acuerdo(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3),
               //         cursor.getString(4), cursor.getString(5));

               // acuerdos.add(acuerdo);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return acuerdos;

    }

}