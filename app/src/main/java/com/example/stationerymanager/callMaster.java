package com.example.stationerymanager;

import android.provider.BaseColumns;

public final class callMaster {
    private callMaster(){}

    protected static class call implements BaseColumns {

        public static final String TABLE_NAME = "Calls";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_NUMBER = "Number";
        public static final String COLUMN_NAME_HOWMUCH = "How_Much";
        public static final String COLUMN_NAME_NOOFMINS = "No_of_Mins";
    }
}

