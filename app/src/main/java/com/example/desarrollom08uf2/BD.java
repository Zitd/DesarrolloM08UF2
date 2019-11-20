package com.example.desarrollom08uf2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BD extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Alumno.db";
    public static final String TABLE_NAME = "tablaAlumno";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Nombre";
    public static final String COL_3 = "Apellido";
    public static final String COL_4 = "Nota";

    public BD(Context context) {//Esto servira para crear la base de datos
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COL_2+" TEXT,"+COL_3+" TEXT,"+COL_4+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nombre,String apellido, String nota){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nombre);
        contentValues.put(COL_3,apellido);
        contentValues.put(COL_4,nota);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)//Si result es -1 significa que ha fallado
            return false;
        else
            return true;

    }
    public Cursor getDatos(){//Esto sirve como acceso al resultado
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return result;
    }
}