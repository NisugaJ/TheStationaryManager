package com.example.stationerymanager;

import android.provider.BaseColumns;

public final class faxMaster {
    private faxMaster(){}

    protected static class fax implements BaseColumns {

        public static final String TABLE_NAME = "Fax";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_NUMBER = "Number";
        public static final String COLUMN_NAME_HOWMUCH = "How_Much";
        public static final String COLUMN_NAME_NOOFFAX = "No_of_Fax";
    }
}
