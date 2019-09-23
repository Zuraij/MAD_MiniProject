package com.example.user.mad_new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelperStd extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student1.db";
    public static final String TABLE_NAME = "Student1";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FirstName";
    public static final String COL_3 = "LastName";
    public static final String COL_4 = "Age";
    public static final String COL_5 = "Email";
    public static final String COL_6= "ContactNo";
    public static final String COL_7 = "Address";
    public static final String COL_8 = "Username";
    public static final String COL_9 = "Password";

    public DatabaseHelperStd(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Student1(ID INTEGER PRIMARY KEY AUTOINCREMENT,FirstName TEXT,LastName TEXT,Age INTEGER,Email TEXT,ContactNo INTEGER,Address TEXT,Username TEXT,Password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Student1");
        onCreate(db);
    }
    public boolean insertData(String FirstName, String LastName, String Age, String Email, String Contactno, String Address, String UserName, String Password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,FirstName);
        contentValues.put(COL_3,LastName);
        contentValues.put(COL_4,Age);
        contentValues.put(COL_5,Email);
        contentValues.put(COL_6,Contactno);
        contentValues.put(COL_7,Address);
        contentValues.put(COL_8,UserName);
        contentValues.put(COL_9,Password);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String ID, String FirstName, String LastName, String Age, String Email, String Contactno, String Address, String UserName, String Password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,FirstName);
        contentValues.put(COL_3,LastName);
        contentValues.put(COL_4,Age);
        contentValues.put(COL_5,Email);
        contentValues.put(COL_6,Contactno);
        contentValues.put(COL_7,Address);
        contentValues.put(COL_8,UserName);
        contentValues.put(COL_9,Password);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {ID});
        return true;


    }
    public Integer deleteData (String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{ID});

    }
    public boolean LoginStudent(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COL_1,
                COL_8,
                COL_9
        };

// Filter results WHERE "title" = 'My Title'
        String selection = COL_8 + " = ?";
        String[] selectionArgs = { username };

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
            String uname = cursor.getString(cursor.getColumnIndexOrThrow(COL_8));
            String pword = cursor.getString(cursor.getColumnIndexOrThrow(COL_9));

            if (uname.matches(username) && password.matches(pword)) {
                return true;
            }

            else {
                return false;
            }
        }

        return false;
    }

}
