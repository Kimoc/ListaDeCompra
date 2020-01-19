package com.aarongutierrez.listadecompra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;


public class ListasSQLiteHelper extends SQLiteOpenHelper {

    String aux;

    String sqlCreate = "CREATE TABLE Listas (idLista INTEGER PRIMARY KEY, nomLista VARCHAR NOT NULL, timestamp VARCHAR  )"; //YYYY-MM-DDTHH:MM:SS


   // String sqlInsert = "INSERT INTO Listas(idLista ,nomLista) VALUES (1,'ListaTest1'"+aux+")";





    public ListasSQLiteHelper(Context context, String dbNname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbNname, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREATES TEST LIST
        db.execSQL(sqlCreate);


      //TIMEPSTAMP
        Date now = new Date();
        long timestamp = now.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        aux = sdf.format(timestamp);

        db.execSQL("INSERT INTO Listas(idLista ,nomLista,timestamp) VALUES (1,'ListaTest1',datetime())");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


}
