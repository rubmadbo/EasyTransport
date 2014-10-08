package com.example.ruben.easytransport;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    //llamamos al constructor
    public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    //creamos la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Acuerdo(idAcuerdo integer primary key, usuarioTransportista integer, " +
                "usuarioCliente integer, ruta integer, precio integer, comentario text)");

        db.execSQL("create table Ruta(idRuta integer primary key, inicio text, final text, horaInicio text, horaFinal text" +
                "fecha text, comentarios text, transportista integer)");

        db.execSQL("create table TipoUsuario(idTipoUsuario integer primary key, nombre text, descripcion text)");

        db.execSQL("create table Usuarios(DNI integer primary key, nombre text, apellidos text, direccion text, telefono integer, " +
                "alias text, contrasenya text, tipoUsuario integer, vehiculo integer)");

        db.execSQL("create table Vehiculo(matricula text primary key, marca text, modelo text)");
    }

    //borrar la tabla y crear la nueva tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists votantes");
        db.execSQL("create table votantes(dni integer primary key, nombre text, colegio text, nromesa integer)");
    }
}