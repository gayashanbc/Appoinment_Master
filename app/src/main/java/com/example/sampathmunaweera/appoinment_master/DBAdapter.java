package com.example.sampathmunaweera.appoinment_master;

/**
 * Created by sampathmunaweera on 3/25/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DBAdapter {

    private static final String DATABASE_TABLE = "AppoinmentDB";
    public static final String KEY_ROW_ID = "_id";
    public static final String KEY_title = "title";
    public static final String KEY_description = "description";
    public static final String KEY_date = "date";

    SQLiteDatabase mDb;
    Context mCtx;
    DBHelper mDbHelper;

    public DBAdapter(Context context) {
        this.mCtx = context;
        open();


    }

    public DBAdapter open() throws SQLException {
        mDbHelper = new DBHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long insertAppoinment(String title, String dec, String cdate) {
        // System.out.println("insertAppoinment"+title+"  insertAppoinment date"+cdate);
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_title, title);
        initialValues.put(KEY_description, dec);
        initialValues.put(KEY_date, cdate);
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }


    public boolean deleteAllAppointments(String date) {


        System.out.println("in delete method");
        mDb.execSQL("DELETE FROM " + DATABASE_TABLE + " WHERE date='" + date + "'");
        return true;
    }


    public List<String> serach(String date) {
        List<String> strList = new ArrayList<String>();
//        Cursor cursor =  mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
//        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
//            strList.add(Integer.toString(cursor.getInt(0)));
//            strList.add(cursor.getString(1));
//            strList.add(cursor.getString(2));
//            strList.add(cursor.getString(3));
//            System.out.println("returned list"+strList);
//
//        }
//        return strList;
        ArrayList<String> array_list = new ArrayList<String>();


        Cursor res = mDb.rawQuery("select * from AppoinmentDB where date like '" + date + "'", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("_id")));
            array_list.add(res.getString(res.getColumnIndex("title")));
            array_list.add(res.getString(res.getColumnIndex("description")));
            array_list.add(res.getString(res.getColumnIndex("date")));
            res.moveToNext();
        }
        return array_list;

    }


    public boolean delesolo(int id) {
        String where = KEY_ROW_ID;
        /*int numOfRecordsDeleted = mDb.delete(DATABASE_TABLE, where, new String[]{Integer.toString(id)});

        return numOfRecordsDeleted;*/
        mDb.execSQL("DELETE FROM " + DATABASE_TABLE + " WHERE _id='" + id + "'");
        return true;
    }


    public boolean Login(String username, String password) throws SQLException {
        Cursor mCursor = mDb.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE username=? AND password=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }

}
