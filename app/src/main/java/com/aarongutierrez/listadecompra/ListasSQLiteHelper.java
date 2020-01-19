package com.aarongutierrez.listadecompra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;





public class ListasSQLiteHelper extends SQLiteOpenHelper {

    String aux;

    String sqlCreate = "CREATE TABLE Listas ( nomLista VARCHAR PRIMARY KEY NOT NULL, timestamp VARCHAR  )"; //YYYY-MM-DD HH:MM:SS

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Listas.db";


    public ListasSQLiteHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATES  LIST------------
        db.execSQL(sqlCreate);

        //INSERT TEST TIMEPSTAMP-------------
        db.execSQL("INSERT INTO Listas(nomLista,timestamp) VALUES ('ListaTest',datetime())");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {




    }


}
