package com.aarongutierrez.listadecompra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton btNuevaLista;

    //--------------ON CREATE---------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btNuevaLista= (FloatingActionButton) findViewById(R.id.bt_nueva_lista);

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
}
