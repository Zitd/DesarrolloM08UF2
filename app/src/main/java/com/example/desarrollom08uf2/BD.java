package com.example.desarrollom08uf2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Alumno.db";
    static String TABLE_NAME = "tablaAlumno";
    static  String COL_1 = "ID";
    static  String COL_2 = "Nombre";
    static  String COL_3 = "Apellido";
    static  String COL_4 = "Nota";

    BD(Context context) {//Esto servirá para crear la base de datos
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        createTable(TABLE_NAME,COL_1,COL_2,COL_3,COL_4);
    }

    void createTable(String TABLE_NAME, String COL_1, String COL_2, String COL_3, String COL_4){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("CREATE TABLE " + TABLE_NAME +" ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_2+" TEXT,"+COL_3+" TEXT,"+COL_4+" INTEGER)");

    }

    boolean insertData(String nombre, String apellido, String nota){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,nota);
        long result = db.insert(TABLE_NAME,null,contentValues);
        //Si result es -1 significa que ha fallado
        return result != -1;

    }
    Cursor getDatos(){//Esto sirve como acceso al resultado
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
    }
}