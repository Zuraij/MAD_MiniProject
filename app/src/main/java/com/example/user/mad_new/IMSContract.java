package com.example.user.mad_new;

import android.provider.BaseColumns;

public final class IMSContract {

    private IMSContract() {

    }

    public static class Notices implements BaseColumns {

        public static final String TABLE_NAME = "Notices";
        public static final String COLUMN_NAME_TOPIC = "topic";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

    public static final String SQL_CREATE_MESSAGE = "CREATE TABLE " + Notices.TABLE_NAME + " (" +
            Notices._ID + " INTEGER PRIMARY KEY ," +
            Notices.COLUMN_NAME_TOPIC + " TEXT ," +
            Notices.COLUMN_NAME_DESCRIPTION + " TEXT)";
}