package com.aarongutierrez.listadecompra;


import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    //BUTTONS ATTRIBUTES
    private FloatingActionButton btNuevaLista;

    //DB ATTRIBUTES
    public static final String db_name = "Listas.db";
    public static final int DB_VERSION = 1;

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



    public void registrarLista(String nombreLitsa){

        ListasSQLiteHelper listasSQLiteHelper= new ListasSQLiteHelper(getContext());
    }


}
