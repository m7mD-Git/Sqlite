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
public class AdapterDB extends SQLiteOpenHelper {
///////////////// columns
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "mail";

    //////////////// database
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "Student";
    private static final int DATABASE_VERSION = 1;
    ////////////// create
    private static final String DATABASE_CREATE =
            "create table"+ DATABASE_TABLE+" (id text not null, "
                    + "name text not null, mail text not null);";

    private SQLiteDatabase db;
    AdapterDB(Context context)
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
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }
    /////////////////////////


    //---closes the database---
    public void close()
    {
        this.close();
    }

    //---insert a contact into the database---
    public boolean insertStudent(String id, String name,String mail)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, id);
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_MAIL, mail);

        return db.insert(DATABASE_TABLE,null, initialValues)>0;

    }
    //---deletes a particular contact---
    public boolean deleteContact(long Id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + Id, null) > 0;
    }
    //---retrieves all the contacts---
    public Cursor getAllstudens()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_NAME,KEY_MAIL}, null, null, null, null, null);
    }
    //---retrieves a particular contact---
    public Cursor getContact(long Id) throws SQLException
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor =
                db.query( DATABASE_TABLE, new String[] {KEY_ID,
                                KEY_NAME,KEY_MAIL}, KEY_ID + "=" + Id, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //---updates a contact---
    public boolean updateContact(long rowId, String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(KEY_NAME, name);
        value.put(KEY_MAIL, email);
        return db.update(DATABASE_TABLE, value, KEY_ID + "=" + rowId, null) > 0;
    }
}