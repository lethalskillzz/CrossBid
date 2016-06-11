package com.crossover.crossbid.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.crossover.crossbid.app.AppConfig;
import com.crossover.crossbid.model.BidItem;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, AppConfig.DATABASE_NAME, null, AppConfig.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + AppConfig.USER_TABLE +
                        "("+AppConfig.id+" integer primary key, "+AppConfig.username+" text, "+AppConfig.password+" text)"
        );

        db.execSQL(
                "create table " + AppConfig.ITEM_TABLE +
                        "("+AppConfig.id+" integer primary key, "+AppConfig.ownerId+" integer, " +
                        ""+AppConfig.itemTitle+" text, "+AppConfig.startingPrice+" float, "+AppConfig.itemImage+" blob)"
        );

        db.execSQL(
                "create table " + AppConfig.BID_TABLE +
                        "("+AppConfig.id+" integer primary key, "+AppConfig.bidderId+" integer, " +
                        ""+AppConfig.itemId+" integer, "+AppConfig.bidPrice+" float)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+AppConfig.USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+AppConfig.ITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+AppConfig.BID_TABLE);
        onCreate(db);
    }

    public void clearItems() {
        getWritableDatabase().execSQL("DELETE FROM "+AppConfig.ITEM_TABLE+";");
    }

    public void clearBids() {
        getWritableDatabase().execSQL("DELETE FROM "+AppConfig.BID_TABLE+";");
    }

    //Inserting New User
    public void insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(AppConfig.username, username);
        cv.put(AppConfig.password, password);
        db.insert(AppConfig.USER_TABLE, AppConfig.username, cv);
        db.close(); // Closing database connection
    }

    //Inserting New Item
    public void insertItem(int ownerId, String itemName, float startingPrice, byte[] itemImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(AppConfig.ownerId, ownerId);
        cv.put(AppConfig.itemTitle, itemName);
        cv.put(AppConfig.startingPrice, startingPrice);
        cv.put(AppConfig.itemImage, itemImage);
        db.insert(AppConfig.ITEM_TABLE, AppConfig.ownerId, cv);
        db.close(); // Closing database connection
    }

    //Inserting New Bid
    public void insertBid(int bidderId, int  itemId, float bidPrice ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(AppConfig.bidderId, bidderId);
        cv.put(AppConfig.itemId, itemId);
        cv.put(AppConfig.bidPrice, bidPrice);
        db.insert(AppConfig.BID_TABLE, AppConfig.bidderId, cv);
        db.close(); // Closing database connection
    }

    // Getting All Bids
    public List<BidItem> getAllBids(int my_Id) {
        List<BidItem> bidList = new ArrayList<BidItem>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + AppConfig.ITEM_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BidItem bidItems = new BidItem();
                bidItems.setId(Integer.parseInt(cursor.getString(0)));
                bidItems.setType(AppConfig.VIEW_BID_ALL);
                bidItems.setTitle(cursor.getString(2));
                bidItems.setIsBid(isBid(Integer.parseInt(cursor.getString(0)), my_Id));
                bidItems.setPrice();
                bidItems.setCount();
            } while (cursor.moveToNext());
        }
    }


    // Getting Item
    private boolean isBid(int bid_id, int bidder_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(AppConfig.BID_TABLE, new String[] { AppConfig.id },
                AppConfig.bidderId + "=?", new String[] { String.valueOf(bidder_id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        if(Integer.parseInt(cursor.getString(0)) == bid_id) {
            return true;
        } else
            return false;
    }

    // Getting Item
    private int bidCount(int bid_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(AppConfig.BID_TABLE, new String[] { AppConfig.id },
                AppConfig.bidderId + "=?", new String[] { String.valueOf(bid_id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        if(Integer.parseInt(cursor.getString(0)) == bidder_Id) {
            return true;
        } else
            return false;
    }

}