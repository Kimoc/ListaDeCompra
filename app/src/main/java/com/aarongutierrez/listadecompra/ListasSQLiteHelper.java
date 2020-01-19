package com.aarongutierrez.listadecompra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ListasSQLiteHelper extends SQLiteOpenHelper {


    String sqlCreate = "CREATE TABLE Listas (idLista INTEGER PRIMARY KEY, nomLista VARCHAR NOT NULL)";
    String sqlInsert = "INSERT INTO Listas(idLista ,nomLista) VALUES (1,'istaTest1')";

    public ListasSQLiteHelper(Context context, String dbNname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbNname, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL(sqlInsert);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
