package com.aarongutierrez.listadecompra.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aarongutierrez.listadecompra.model.Lista;

import java.util.ArrayList;


public class SQLiteHelper extends SQLiteOpenHelper {

    //***______________________________SQLITE COMMANDS___________________________________***//

    //TABLES

    String sqlCreateTableListas = "CREATE TABLE Listas ( nomLista STRING PRIMARY KEY NOT NULL, timestamp TIMESTAMP DEFAULT   CURRENT_TIMESTAMP  )";//Tabla listas
    String sqlCreateTableCategorias = "CREATE TABLE Categorias ( nomCateg STRING PRIMARY KEY NOT NULL ,imagen INTEGER  )";//Tabla Categorias
    String sqlCreateTableProductos="CREATE TABLE Productos ( nomProd STRING PRIMARY KEY NOT NULL ,imagen INTEGER , categoria STRING REFERENCES nomCateg (Categorias))";
    String sqlCreateTableListaProductos="CREATE TABLE ListaProductos( nomLista STRING PRIMARY KEY REFERENCES nomLista(Listas) ON DELETE CASCADE ," +
                                        "productos STRING REFERENCES nomProd(Productos))";



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
        db.execSQL(sqlCreateTableListas);
        db.execSQL(sqlCreateTableCategorias);
        db.execSQL(sqlCreateTableProductos);
        db.execSQL(sqlCreateTableListaProductos);

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
