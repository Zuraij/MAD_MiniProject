package com.example.user.mad_new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.user.mad_new.IMSContract.SQL_CREATE_MESSAGE;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notice Board.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_MESSAGE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addNotice(String topic, String description) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(IMSContract.Notices.COLUMN_NAME_TOPIC, topic);
        values.put(IMSContract.Notices.COLUMN_NAME_DESCRIPTION, description);

        long newRowID = db.insert(IMSContract.Notices.TABLE_NAME, null, values);

        return newRowID;
    }

    public long updateNotice(String id, String topic, String description) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseColumns._ID,Integer.valueOf(id));
        values.put(IMSContract.Notices.COLUMN_NAME_TOPIC, topic);
        values.put(IMSContract.Notices.COLUMN_NAME_DESCRIPTION, description);

        String filter = "_id=" +id;

        long newRowID = db.update(IMSContract.Notices.TABLE_NAME, values, filter, null);

        return newRowID;
    }

    public Map<String, ArrayList<String>> initMessages() {
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                IMSContract.Notices.COLUMN_NAME_TOPIC,
                IMSContract.Notices.COLUMN_NAME_DESCRIPTION
        };

        Cursor cursor = db.query(
                IMSContract.Notices.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        ArrayList<String> topics = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();

        while (cursor.moveToNext()) {
            int notice_id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String topic = cursor.getString(cursor.getColumnIndexOrThrow(IMSContract.Notices.COLUMN_NAME_TOPIC));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(IMSContract.Notices.COLUMN_NAME_DESCRIPTION));

            id.add(Integer.toString(notice_id));
            topics.add(topic);
            descriptions.add(description);

        }
        cursor.close();

        Map<String, ArrayList<String>> map = new HashMap<>();
        map.put("id", id);
        map.put("topics", topics);
        map.put("descriptions", descriptions);

        return map;
    }

    public long deleteNotice(String id) {

        SQLiteDatabase db = getWritableDatabase();
        String filter = BaseColumns._ID+ " = " + id;

        long rowId = db.delete(IMSContract.Notices.TABLE_NAME, filter, null);

        return rowId;
    }
}
