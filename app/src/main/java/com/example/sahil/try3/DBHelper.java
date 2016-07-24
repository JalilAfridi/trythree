package com.example.sahil.try3;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DBveg.db";
    public static final String Items_TABLE_NAME = "Items";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String NAME_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_PRICE = "price";
    public static final String CONTACTS_COLUMN_ICON = "icon";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table Items " +"(id integer primary key, name text,price text,icon text)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Items");
        onCreate(db);
    }

    public boolean insertItem  (String name, String price, String icon)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("icon", icon);
        db.insert("Items", null, contentValues);
        return true;
    }

    public Cursor getData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Items where name="+name+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Items_TABLE_NAME);
        return numRows;
    }

    public boolean updateItems (Integer id, String name, String price, String icon)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("icon", icon);
        db.update("Items", contentValues, "id = ? ",new String[]{Integer.toString(id)} );
        return true;
    }

    public Integer deleteItem (String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Items",
                "name = ? ",
                new String[] { name });
    }

    public ArrayList<String> getAllItems(String Colum_name){
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Items", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(Colum_name)));
            res.moveToNext();
        }
        return array_list;
    }
}