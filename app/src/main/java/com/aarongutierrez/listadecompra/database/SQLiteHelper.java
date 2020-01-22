package com.aarongutierrez.listadecompra.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aarongutierrez.listadecompra.model.Lista;

import java.util.ArrayList;
import java.util.List;


public class SQLiteHelper extends SQLiteOpenHelper {

    //***______________________________SQLITE COMMANDS___________________________________***//

    String sqlCreate = "CREATE TABLE Listas ( nomLista VARCHAR PRIMARY KEY NOT NULL, timestamp TIMESTAMP DEFAULT   CURRENT_TIMESTAMP  )";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Listas.db";
    
    //Singleton 
    private static SQLiteHelper sqlHelper =null;
    public static SQLiteHelper getInstance(Context context ) {
        if(sqlHelper ==null){
            sqlHelper =new SQLiteHelper(context);
        }
        return sqlHelper;
        
    }
    //ARRAYLISTS DATOS
    ArrayList<Lista> listas;

    //Constructor
    private SQLiteHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean datoscargados;
        //CREATES  LIST------------
        db.execSQL(sqlCreate);

        //INSERT TEST TIMEPSTAMP-------------
        db.execSQL("INSERT INTO Listas(nomLista,timestamp) VALUES ('ListaTest',datetime())");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {




    }

    public boolean chargeData(){
        SQLiteDatabase db= this.getReadableDatabase();
        listas=new ArrayList<>();

        Cursor cursor= db.rawQuery("SELECT nomLista,timestamp FROM Listas;",null);
        if(cursor.moveToFirst()){
            do{
                String nombreLista= cursor.getString(0);
                String fechaCreacion = cursor.getString(1);
                listas.add(new Lista(nombreLista,fechaCreacion));
            }while (cursor.moveToNext());
        }
        cursor.close();

        db.close();

        return true;

    }
    public ArrayList<Lista> getListas(){
        return listas;
    }


}
