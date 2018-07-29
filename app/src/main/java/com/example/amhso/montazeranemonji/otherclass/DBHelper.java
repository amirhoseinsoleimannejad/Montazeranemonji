package com.example.amhso.montazeranemonji.otherclass;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "recent_media";
    public static final String CONTACTS_TABLE_NAME_MARKER = "marker";

    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_URL = "url";



    public static final String CONTACTS_COLUMN_NAME_MARKER = "name";
    public static final String CONTACTS_COLUMN_IMAGE = "image";





    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 2);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table recent_media " +
                        "(id integer primary key, name text,url text)"
        );


        db.execSQL(
                "create table marker " +
                        "(id integer primary key, name text,image text)"
        );
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS recent_media");
        db.execSQL("DROP TABLE IF EXISTS marker");

        onCreate(db);
    }





    public boolean insertMedia (String name, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("url", url);

        db.insert("recent_media", null, contentValues);
        return true;
    }





    public boolean insertMarker (String name, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("image", image);

        SQLiteDatabase db_write = this.getReadableDatabase();
        Cursor res =  db_write.rawQuery( "select * from marker where name=\""+name+"\" and image=\""+image+"\"", null );

        Log.i("ggggggggggggggggg", "gggggggggggggg: "+res.getCount());
        if(res.getCount()==0){
            db.insert("marker", null, contentValues);
        }
        return true;
    }




    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from recent_media where id="+id+"", null );
        return res;
    }




    public int numberOfRowsTableRecent(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }




    public int numberOfRowsTableMarker(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME_MARKER);
        return numRows;
    }


//    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }

//    public Integer deleteContact (Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("contacts",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }

    public ArrayList<video> getAllRecent() {
        ArrayList<video> array_list = new ArrayList<video>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from recent_media", null );
        res.moveToLast();

        while(res.isBeforeFirst() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));

            video v=new video(res.getString(res.getColumnIndex(CONTACTS_COLUMN_URL)),res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)),"","","");
            array_list.add(v);
            res.moveToPrevious();
        }
        return array_list;
    }









    public ArrayList<Item> getAllMarker() {
        ArrayList<Item> array_list = new ArrayList<Item>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from marker", null );
        res.moveToLast();

        while(res.isBeforeFirst() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));

//            video v=new video(res.getString(res.getColumnIndex(CONTACTS_COLUMN_URL)),res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)),"","","");
//
//

            array_list.add(new Item(res.getString(res.getColumnIndex(CONTACTS_COLUMN_IMAGE)), res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME_MARKER)), res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME_MARKER)), "12:14"));
            res.moveToPrevious();
        }
        return array_list;
    }
}
