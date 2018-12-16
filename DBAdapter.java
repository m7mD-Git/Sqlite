package com.qalex.m7md.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by m7mD on 8/3/2016.
 */
public class DBAdapter {
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "mail";
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "Student";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_CREATE =
            "create table Student (id text not null, "
                    + "name text not null, mail text not null);";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                   db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w("", "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS studens");
            onCreate(db);
        }
    }
    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }
    //---insert a contact into the database---
    public long insertStudent(String id, String name,String mail)
    {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, id);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_MAIL, mail);

        return db.insert(DATABASE_TABLE, null, initialValues);

    }
    //---deletes a particular contact---
    public boolean deleteContact(long Id)
    {
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + Id, null) > 0;
    }
    //---retrieves all the contacts---
    public Cursor getAllstudens()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_NAME,KEY_MAIL}, null, null, null, null, null);
    }
    //---retrieves a particular contact---
    public Cursor getContact(long Id) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ID,
                                KEY_NAME,KEY_MAIL}, KEY_ID + "=" + Id, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //---updates a contact---
    public boolean updateContact(long rowId, String name, String email){
        ContentValues value = new ContentValues();
        value.put(KEY_NAME, name);
        return db.update(DATABASE_TABLE, value, KEY_ID + "=" + rowId, null) > 0;
    }
}