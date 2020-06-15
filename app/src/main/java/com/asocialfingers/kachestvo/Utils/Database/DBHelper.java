package com.asocialfingers.kachestvo.Utils.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = DBHelper.class.getSimpleName();

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "kachestvo";

    public static final String TABLE_NAME = "comments";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME_SURNAME = "nameSurname";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_CREATED_AT = "created_at";

    public static DBHelper dbHelper;

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strCreateTable = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT,"
                + KEY_NAME_SURNAME + " TEXT,"
                + KEY_COMMENT + " TEXT,"
                + KEY_CREATED_AT + " TEXT)";
        db.execSQL(strCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void deleteTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_NAME, null, null);
        db.close();

        Log.d(TAG, "Deleted all comment info from sqlite");
    }

    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        return dbHelper;
    }

}
