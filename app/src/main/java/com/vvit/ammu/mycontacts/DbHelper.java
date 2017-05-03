package com.vvit.ammu.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by Ammu on 03-05-2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String name = "MyContactsDb";
    public static final int version = 1;

    public static final String table = "MyContacts";
    public static final String id = "id";
    public static final String contactName = "ContactName";
    public static final String mobile = "ContactNumber";

    public DbHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE MyContacts(id INTEGER,ContactName TEXT,ContactNumber TEXT)";
        //String q="CREATE TABLE "+table+" ("+id+" INTEGER, "+contactName+" TEXT, "+mobile+" TEXT)";
        db.execSQL(query);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(contactName,contact.getContactName());
        cv.put(mobile,contact.getContactNumber());
        db.insert(table,null,cv);
    }

    public Cursor getContacts(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+table;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}
