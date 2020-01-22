package com.aarongutierrez.listadecompra;

import android.provider.BaseColumns;

public class ListaContract  {
    private ListaContract(){

    }

    public static final class ListaEntry implements BaseColumns {
        public static final String TABLE_NAME = "Listas";
        public static final String COLUMN_NAME = "nomLista";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
