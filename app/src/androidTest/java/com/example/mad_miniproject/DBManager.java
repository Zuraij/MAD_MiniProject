package com.example.user.mad_new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    DBManager(Context c) {
        context = c;
    }

    //database open
    void open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    //database close
    public void close() {
        dbHelper.close();
    }

    //insert in database
    void insert(String username, String email ,String firstname, String lastname, String position ,String address ,String gender,int phone ,double salary ,String password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.USERNAME, username);
        contentValue.put(DatabaseHelper.EMAIL, email);
        contentValue.put(DatabaseHelper.FIRSTNAME, firstname);
        contentValue.put(DatabaseHelper.LASTNAME, lastname);
        contentValue.put(DatabaseHelper.POSITION, position);
        contentValue.put(DatabaseHelper.ADDRESS, address);
        contentValue.put(DatabaseHelper.GENDER, gender);
        contentValue.put(DatabaseHelper.PHONE, phone);
        contentValue.put(DatabaseHelper.SALARY, salary);
        contentValue.put(DatabaseHelper.PASSWORD, password);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }


    //update in database
    void update(long _id, String username, String email ,String firstname, String lastname, String position ,String address ,String gender ,int phone ,double salary ,String password)  {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.USERNAME, username);
        contentValue.put(DatabaseHelper.EMAIL, email);
        contentValue.put(DatabaseHelper.FIRSTNAME, firstname);
        contentValue.put(DatabaseHelper.LASTNAME, lastname);
        contentValue.put(DatabaseHelper.POSITION, position);
        contentValue.put(DatabaseHelper.ADDRESS, address);
        contentValue.put(DatabaseHelper.GENDER, gender);
        contentValue.put(DatabaseHelper.PHONE, phone);
        contentValue.put(DatabaseHelper.SALARY, salary);
        contentValue.put(DatabaseHelper.PASSWORD, password);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValue, DatabaseHelper._ID + " = " + _id, null);
    }


    //delete in database
    void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }


    Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.USERNAME,DatabaseHelper.EMAIL,
                DatabaseHelper.FIRSTNAME,DatabaseHelper.LASTNAME,DatabaseHelper.POSITION,DatabaseHelper.ADDRESS,DatabaseHelper.GENDER,DatabaseHelper.PHONE
                ,DatabaseHelper.SALARY
                , DatabaseHelper.PASSWORD };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}
