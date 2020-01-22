package com.aarongutierrez.listadecompra;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aarongutierrez.listadecompra.adapters.ListasAdapter;
import com.aarongutierrez.listadecompra.database.SQLiteHelper;
import com.aarongutierrez.listadecompra.model.Lista;
import com.aarongutierrez.listadecompra.fragments.DialogFragmentCrearLista;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DialogFragmentCrearLista.DialogFragmentCrearListaListener{


    private FloatingActionButton btNuevaLista;
    private RecyclerView recyclerViewListas;
    private ListasAdapter listasAdapter;
    private SQLiteDatabase db;


    public static final String POS_LIST="com.aarongutierrez.listadecompra";
    private ArrayList<Lista> listas;

    //--------------ON CREATE---------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btNuevaLista= findViewById(R.id.bt_nueva_lista);





        //DATABASE
        SQLiteHelper lsqlh = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = lsqlh.getWritableDatabase();
        if(db == null) {
            Toast.makeText(this, "Connection error to DB", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Connected to DB", Toast.LENGTH_SHORT).show();
            lsqlh.chargeData();
            // Ací aniran les sentències d'actualització de la base de dades
            db.close();
        }

        listasAdapter=new ListasAdapter(this,getAllItems());
        recyclerViewListas=findViewById(R.id.rv_listas);
        recyclerViewListas.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerViewListas.setAdapter(listasAdapter);
        DividerItemDecoration itemDecoration= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerViewListas.addItemDecoration(itemDecoration);



        btNuevaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAlertDialogCrearLista();
            }
        });
    }

    //  AlertDialog para acceder al dialgofragment de el cual recoje el input del usuario para crear nueva lista
        public void openAlertDialogCrearLista(){
        DialogFragmentCrearLista crearListaAlertDialog = new DialogFragmentCrearLista();
        crearListaAlertDialog.show(getSupportFragmentManager(),"alert dialog");

    }

    /**
     *
     * @param inputUser interficie del dialogfragmet para recojer datos del usuario;
     */
    @Override
    public void apllyText(String inputUser) {
        SQLiteHelper lsqlh =SQLiteHelper.getInstance(this);
        SQLiteDatabase db = lsqlh.getWritableDatabase();
        if(db == null) {
            Toast.makeText(this, "Connection error to DB", Toast.LENGTH_SHORT).show();
        } else {
            //Genera nuevo registro (nueva lista) con clave-valor *nomlista-*mas el input del AlerDialog;

            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nomLista", inputUser);


            // RELIAZMOS INSERT
            db.insert("Listas", null, nuevoRegistro);

            db.close();
            Toast.makeText(this, "Nueva lista creada", Toast.LENGTH_SHORT).show();

        }
    }





    private Cursor getAllItems() {
        SQLiteHelper lsqlh = SQLiteHelper.getInstance(this);
        SQLiteDatabase db = lsqlh.getReadableDatabase();
        return db.query(
                ListaContract.ListaEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ListaContract.ListaEntry.COLUMN_TIMESTAMP + " ASC"
        );

    }
}
