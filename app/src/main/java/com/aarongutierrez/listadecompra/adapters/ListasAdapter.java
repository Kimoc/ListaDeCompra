package com.aarongutierrez.listadecompra.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aarongutierrez.listadecompra.ListaContract;
import com.aarongutierrez.listadecompra.R;
import com.aarongutierrez.listadecompra.interfaces.IListaListener;


public class ListasAdapter extends RecyclerView.Adapter<ListasAdapter.ListaViewHolder> {
    private Context context;
    private Cursor cursor;

    //Constructor
    public ListasAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    //VIEWHOLDER
    public class ListaViewHolder extends RecyclerView.ViewHolder/* implements View.OnClickListener*/{

        private  TextView tvNombreLista;
        private TextView tvFechaCreacion;
        private IListaListener listaListener;
        public ListaViewHolder(View itemView ) {
            super(itemView);
            tvNombreLista=itemView.findViewById(R.id.tv_nomlista);
            tvFechaCreacion=itemView.findViewById(R.id.tv_timestamp);
            this.listaListener=listaListener;

          //  itemView.setOnClickListener(this);
        }


      //  @Override
     //   public void onClick(View v) {
      //      listaListener.onListaSeleccionado(getAdapterPosition());
      //  }
    }
    @Override
    public ListaViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.layout_item_lista,parent,false);
        return new ListaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListaViewHolder holder, int position) {
        if(!cursor.moveToPosition(position)){
            return;
        }
        String nomLista = cursor.getString(cursor.getColumnIndex(ListaContract.ListaEntry.COLUMN_NAME));
        String timestamp= cursor.getString(cursor.getColumnIndex(ListaContract.ListaEntry.COLUMN_TIMESTAMP));
        holder.tvFechaCreacion.setText(timestamp);
        holder.tvNombreLista.setText(nomLista);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }

        cursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }

}
