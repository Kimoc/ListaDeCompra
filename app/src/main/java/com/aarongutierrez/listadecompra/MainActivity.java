package com.aarongutierrez.listadecompra;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements DialogFragmentCrearLista.DialogFragmentCrearListaListener {

    //BUTTONS ATTRIBUTES
    private FloatingActionButton btNuevaLista;



    //--------------ON CREATE---------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btNuevaLista= findViewById(R.id.bt_nueva_lista);

        //DATABASE
        ListasSQLiteHelper lsqlh = new ListasSQLiteHelper(this);
        SQLiteDatabase db = lsqlh.getWritableDatabase();
        if(db == null) {
            Toast.makeText(this, "Connection error to DB", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Connected to DB", Toast.LENGTH_SHORT).show();

            // Ací aniran les sentències d'actualització de la base de dades


            db.close();
        }



        btNuevaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialogCrearLista();
            }
        });
    }

    // AlertDialog para acceder al dialgofragment de crear nueva lista
    public void openAlertDialogCrearLista(){
        DialogFragmentCrearLista crearListaAlertDialog = new DialogFragmentCrearLista();
        crearListaAlertDialog.show(getSupportFragmentManager(),"alert dialog");

    }

    //INSERT CREAR LISTA
    @Override
    public void apllyText(String inputUser) {
        ListasSQLiteHelper lsqlh = new ListasSQLiteHelper(this);
        SQLiteDatabase db = lsqlh.getWritableDatabase();
        if(db == null) {
            Toast.makeText(this, "Connection error to DB", Toast.LENGTH_SHORT).show();
        } else {


            //Genera nuevo registro (nueva lista) con clave-valor *nomlista-*mas el input del AlerDialog;
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nomLista", inputUser);
            nuevoRegistro.put("timeStamp",getCurrentTimeStamp());
            // Inserim el registre a la base de dades
            db.insert("Listas", null, nuevoRegistro);

            db.close();
            Toast.makeText(this, "Nueva lista creada", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     *
     * @return yyyy-MM-dd HH:mm:ss formate date as string
     */
    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
