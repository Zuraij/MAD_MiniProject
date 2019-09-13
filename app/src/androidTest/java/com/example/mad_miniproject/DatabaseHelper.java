package com.example.user.mad_new;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    static final String TABLE_NAME = "Staff";

    // Table columns
    static final String _ID = "_id";
    static final String USERNAME = "username";
    static final String EMAIL = "email";
    static final String FIRSTNAME = "firstname";
    static final String LASTNAME = "lastname";
    static final String POSITION = "postion";
    static final String ADDRESS = "address";
    static final String GENDER = "gender";
    static final String PHONE = "phone";
    static final String SALARY = "salary";
    static final String PASSWORD = "password";

    // Database Information
    private static final String DB_NAME = "Staff.DB";

    // database version
    private static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + USERNAME + " TEXT NOT NULL , "
            + EMAIL + " TEXT NOT NULL , "
            + FIRSTNAME + " TEXT NOT NULL , "
            +LASTNAME + " TEXT NOT NULL , "
            +POSITION + " TEXT NOT NULL , "
            +ADDRESS + " TEXT NOT NULL , "
            +GENDER + " TEXT NOT NULL , "
            +PHONE + " TEXT NOT NULL , "
            +SALARY + " TEXT NOT NULL , "
            +PASSWORD + " TEXT NOT NULL);";

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {
        db1.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean loginfun(String email,String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                _ID,
                USERNAME,
                EMAIL,
                PASSWORD
        };

// Filter results WHERE "title" = 'My Title'
        String selection = USERNAME + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            String uname = cursor.getString(cursor.getColumnIndexOrThrow(USERNAME));
            String pword = cursor.getString(cursor.getColumnIndexOrThrow(PASSWORD));

            if (uname.matches(email) && password.matches(pword)) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
