package com.example.ruben.easytransport;
import android.content.ContentValues;
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
                "usuarioCliente integer, ruta integer, precio integer, comentario text, FOREIGN KEY(usuarioTransportista) " +
                "REFERENCES Usuarios(DNI),FOREIGN KEY(usuarioCliente) REFERENCES Usuarios(DNI), " +
                "FOREIGN KEY(ruta) REFERENCES Ruta(idRuta))");

        db.execSQL("create table Ruta(idRuta integer primary key, inicio text, final text, horaInicio text, horaFinal text," +
                "fecha text, comentarios text, transportista integer, FOREIGN KEY(transportista) REFERENCES Usuarios(DNI))");

        db.execSQL("create table TipoUsuario(idTipoUsuario integer primary key, nombre text, descripcion text)");

        db.execSQL("create table Vehiculo(matricula text primary key, marca text, modelo text)");

        db.execSQL("create table Usuarios(DNI integer primary key, nombre text, apellidos text, direccion text, telefono integer, " +
                "alias text, contrasenya text, tipoUsuario integer, vehiculo integer, FOREIGN KEY(tipoUsuario) " +
                "REFERENCES TipoUsuario(idTipoUsuario),FOREIGN KEY(vehiculo) REFERENCES Vehiculo(matricula))");

        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matricula", "1122FFF");
        values.put("marca", "Opel");
        values.put("modelo", "Astra");
        db.insert("Vehiculo", null, values);

        values.put("matricula", "2233HHH");
        values.put("marca", "Seat");
        values.put("modelo", "Panda");
        db.insert("Vehiculo", null, values);

        values.put("matricula", "3344ZZZ");
        values.put("marca", "Ford");
        values.put("modelo", "Fiesta");
        db.insert("Vehiculo", null, values);

        values.put("matricula", "4455GHQ");
        values.put("marca", "Seat");
        values.put("modelo", "Ibiza");
        db.insert("Vehiculo", null, values);

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

        db.execSQL("create table Vehiculo(matricula text primary key, marca text, modelo text)");

        db.execSQL("create table Usuarios(DNI integer primary key, nombre text, apellidos text, direccion text, telefono integer, " +
                "alias text, contrasenya text, tipoUsuario integer, vehiculo integer, FOREIGN KEY(tipoUsuario) " +
                "REFERENCES TipoUsuario(idTipoUsuario),FOREIGN KEY(vehiculo) REFERENCES Vehiculo(matricula))");

    }
}