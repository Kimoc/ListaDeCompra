package com.aarongutierrez.listadecompra;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogFragmentCrearLista extends AppCompatDialogFragment {


    private EditText  inpuntCrearLista;
    private DialogFragmentCrearLista listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_crear_lista,null);

        builder.setView(view)
                .setTitle("Crear nueva lista")
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputUsuario =inpuntCrearLista.getText().toString();

                    }
                })
                .setPositiveButton("GUARDAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        inpuntCrearLista= view.findViewById(R.id.et_nombar_lista);
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
