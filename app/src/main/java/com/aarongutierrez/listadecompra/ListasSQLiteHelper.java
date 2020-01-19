package com.aarongutierrez.listadecompra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;





public class ListasSQLiteHelper extends SQLiteOpenHelper {

    String aux;

    String sqlCreate = "CREATE TABLE Listas (idLista INTEGER PRIMARY KEY, nomLista VARCHAR NOT NULL, timestamp VARCHAR  )"; //YYYY-MM-DDTHH:MM:SS

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Listas.db";


    public ListasSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATES TEST LIST------------
        db.execSQL(sqlCreate);

        //INSERT TEST TIMEPSTAMP-------------
        db.execSQL("INSERT INTO Listas(idLista ,nomLista,timestamp) VALUES (1,'ListaTest1',datetime())");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }


}
