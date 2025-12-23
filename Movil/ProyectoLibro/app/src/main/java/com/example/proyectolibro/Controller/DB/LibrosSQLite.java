package com.example.proyectolibro.Controller.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectolibro.Model.Libro;

import java.util.ArrayList;

public class LibrosSQLite extends SQLiteOpenHelper {
    private ArrayList<Libro> listaLibros;

    private String sqlCreate = "CREATE TABLE libros (id INTEGER PRIMARY KEY AUTOINCREMENT, img INTEGER, titulo TEXT, descripcion TEXT, fecha TEXT)";

    public LibrosSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS libros");
        db.execSQL(sqlCreate);
    }

    public long addLibro(SQLiteDatabase db, Libro libro) {

        ContentValues values = new ContentValues();
        values.put("img", libro.getImagen());
        values.put("titulo", libro.getTitulo());
        values.put("descripcion", libro.getTexto());
        values.put("fecha", libro.getFecha());

        long resultado = db.insert("libros", null, values);
        return resultado;
    }

    public long deleteLibro(SQLiteDatabase db, int id) {

        long resultado = db.delete("libros", "id=" + id, null);
        return resultado;
    }

    public long updateLibro(SQLiteDatabase db, Libro libro, int id) {

        ContentValues values = new ContentValues();
        values.put("img", libro.getImagen());
        values.put("titulo", libro.getTitulo());
        values.put("descripcion", libro.getTexto());
        values.put("fecha", libro.getFecha());

        long resultado = db.update("libros", values, "id=" + id, null);
        return resultado;
    }

    public ArrayList<Libro> consultLibros(SQLiteDatabase db) {

        listaLibros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        Cursor miCursor = db.rawQuery(sql, null);

        if (miCursor.moveToFirst()){
            do {
                int id = miCursor.getInt(0);
                int img = miCursor.getInt(1);
                String titulo = miCursor.getString(2);
                String descripcion = miCursor.getString(3);
                String fecha = miCursor.getString(4);

                Libro libro = new Libro(img, titulo, descripcion, fecha, false);
                libro.setId(id);
                listaLibros.add(libro);
            }while (miCursor.moveToNext());
        }
        miCursor.close();

        return listaLibros;
    }
}
