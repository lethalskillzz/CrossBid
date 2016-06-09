package com.crossover.crossbid.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.crossover.crossbid.app.AppConfig;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, AppConfig.DATABASE_NAME, null, AppConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + AppConfig.userTable +
                        "(id integer primary key, "+AppConfig.username+" text, "+AppConfig.password+" text)"
        );

        db.execSQL(
                "create table " + AppConfig.itemTable +
                        "(id integer primary key, "+AppConfig.ownerId+" integer, " +
                        ""+AppConfig.itemName+" text, "+AppConfig.startingPrice+" float, "+AppConfig.itemImage+" blob)"
        );

        db.execSQL(
                "create table " + AppConfig.bidTable +
                        "(id integer primary key, "+AppConfig.bidderId+" integer, " +
                        ""+AppConfig.itemId+" integer, "+AppConfig.bidPrice+" float)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+AppConfig.userTable);
        db.execSQL("DROP TABLE IF EXISTS "+AppConfig.itemTable);
        db.execSQL("DROP TABLE IF EXISTS "+AppConfig.bidTable);
        onCreate(db);
    }

    public void clearItems() {
        getWritableDatabase().execSQL("DELETE FROM "+AppConfig.itemTable+";");
    }

    public void clearBids() {
        getWritableDatabase().execSQL("DELETE FROM "+AppConfig.bidTable+";");
    }

    public void insertUser(String username, String password) {
        ContentValues cv=new ContentValues();
        cv.put(AppConfig.username, username);
        cv.put(AppConfig.password, password);
        getWritableDatabase().insert(AppConfig.userTable, AppConfig.username, cv);
    }

    public void insertItem(int ownerId, String itemName, float startingPrice, byte[] itemImage) {
        ContentValues cv=new ContentValues();
        cv.put(AppConfig.ownerId, ownerId);
        cv.put(AppConfig.itemName, itemName);
        cv.put(AppConfig.startingPrice, startingPrice);
        cv.put(AppConfig.itemImage, itemImage);
        getWritableDatabase().insert(AppConfig.itemTable, AppConfig.ownerId, cv);
    }

    public void insertBid(int bidderId, int  itemId, float bidPrice ) {
        ContentValues cv=new ContentValues();
        cv.put(AppConfig.bidderId, bidderId);
        cv.put(AppConfig.itemId, itemId);
        cv.put(AppConfig.bidPrice, bidPrice);
        getWritableDatabase().insert(AppConfig.bidTable, AppConfig.bidderId, cv);
    }


}