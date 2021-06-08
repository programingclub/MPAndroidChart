package com.irvingmedina.graficasconsqlite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatosDB extends SQLiteOpenHelper {

    private static final String NOMBRE_DB = "ejemplo.bd";
    private static final int VERSION_DB = 1;
    private static final String TABLA_EJEMPLO =  "CREATE TABLE Ejemplo (Dato1 Int, Dato2 Int, Texto Text)";
    private static final String DROP_TABLA = "DROP TABLE  IF EXISTS Ejemplo";

    public DatosDB(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_EJEMPLO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLA);
        db.execSQL(TABLA_EJEMPLO);

    }

    public void agregarDatos(String dato1, String dato2, String texto){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null){
            db.execSQL("INSERT INTO Ejemplo VALUES ('"+dato1+"', '"+dato2+"', '"+texto+"')");
            db.close();
        }
    }

    public Cursor getValues(){
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor c = bd.rawQuery("SELECT dato1, texto from Ejemplo;", null);
        return c;
    }

    public Cursor getDatosBar(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT dato1, dato2 from Ejemplo;", null);
        return c;
    }


}
