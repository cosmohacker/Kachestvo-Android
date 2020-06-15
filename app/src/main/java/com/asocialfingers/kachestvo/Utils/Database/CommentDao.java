package com.asocialfingers.kachestvo.Utils.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class CommentDao {

    DBHelper dbHelper;

    public CommentDao(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    public long insertComment(CommentDetails commentDetails) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_NAME_SURNAME, commentDetails.getNameSurname());
        values.put(dbHelper.KEY_COMMENT, commentDetails.getComment());
        values.put(dbHelper.KEY_CREATED_AT, commentDetails.getCreated_at());
        long id = db.insert(dbHelper.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public ArrayList<CommentDetails> getAllComments() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "select " + dbHelper.KEY_ID + "," +
                dbHelper.KEY_NAME_SURNAME + "," +
                dbHelper.KEY_COMMENT + "," +
                dbHelper.KEY_CREATED_AT + " from " + dbHelper.TABLE_NAME;

        ArrayList<CommentDetails> commentList = new ArrayList<CommentDetails>();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CommentDetails c = new CommentDetails();
            c.setId(cursor.getInt(0));
            c.setNameSurname(cursor.getString(1));
            c.setComment(cursor.getString(2));
            c.setCreated_at(cursor.getString(3));
            commentList.add(c);
            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return commentList;
    }

    public int updateComment(CommentDetails commentDetails) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_NAME_SURNAME, commentDetails.getNameSurname());
        values.put(dbHelper.KEY_COMMENT, commentDetails.getComment());
        values.put(dbHelper.KEY_CREATED_AT, commentDetails.getCreated_at());
        int count = db.update(dbHelper.TABLE_NAME, values,
                dbHelper.KEY_ID + " = ?", new String[]{"" + commentDetails.getId()});
        return count;

    }

    public int deleteComment(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete(dbHelper.TABLE_NAME,
                dbHelper.KEY_ID + "= ? ", new String[]{"" + id});

        return count;
    }
}
