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
                        "("+AppConfig.ID+" integer primary key, "+AppConfig.USERNAME+" text, "+AppConfig.PASSWORD+" text)"
        );

        db.execSQL(
                "create table " + AppConfig.ITEM_TABLE +
                        "("+AppConfig.ID+" integer primary key, "+AppConfig.OWNER_ID+" integer, " +
                        ""+AppConfig.ITEM_TITLE+" text, "+AppConfig.STARTING_PRICE+" float, "+AppConfig.ITEM_IMAGE+" blob)"
        );

        db.execSQL(
                "create table " + AppConfig.BID_TABLE +
                        "("+AppConfig.ID+" integer primary key, "+AppConfig.BIDDER_ID+" integer, " +
                        ""+AppConfig.ITEM_ID+" integer, "+AppConfig.BID_PRICE+" float)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+AppConfig.USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+AppConfig.ITEM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+AppConfig.BID_TABLE);
        onCreate(db);
    }

    //Delete all items
    public void clearItems() {
        getWritableDatabase().execSQL("DELETE FROM "+AppConfig.ITEM_TABLE+";");
    }

    //Delete all bids
    public void clearBids() {
        getWritableDatabase().execSQL("DELETE FROM "+AppConfig.BID_TABLE+";");
    }

    //Inserting New User
    public long insertUser(String username, String password) {
        long index;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(AppConfig.USERNAME, username);
        cv.put(AppConfig.PASSWORD, password);
        index = db.insert(AppConfig.USER_TABLE, AppConfig.USERNAME, cv);
        db.close(); // Closing database connection
        return index;
    }

    //Inserting New Item
    public void insertItem(int ownerId, String itemName, float startingPrice, byte[] itemImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(AppConfig.OWNER_ID, ownerId);
        cv.put(AppConfig.ITEM_TITLE, itemName);
        cv.put(AppConfig.STARTING_PRICE, startingPrice);
        cv.put(AppConfig.ITEM_IMAGE, itemImage);
        db.insert(AppConfig.ITEM_TABLE, AppConfig.OWNER_ID, cv);
        db.close(); // Closing database connection
    }

    //Inserting New Bid
    public void insertBid(int bidderId, int  itemId, float bidPrice ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(AppConfig.BIDDER_ID, bidderId);
        cv.put(AppConfig.ITEM_ID, itemId);
        cv.put(AppConfig.BID_PRICE, bidPrice);
        db.insert(AppConfig.BID_TABLE, AppConfig.BIDDER_ID, cv);
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

                if (Float.parseFloat(cursor.getString(3)) >= bidPrice(Integer.parseInt(cursor.getString(0))))
                    bidItems.setPrice(cursor.getString(3));
                else
                    bidItems.setPrice(String.valueOf(bidPrice(Integer.parseInt(cursor.getString(0)))));

                bidItems.setCount(String.valueOf(bidCount(Integer.parseInt(cursor.getString(0)))) + " bids");

                bidList.add(bidItems);
            } while (cursor.moveToNext());
        }
        return bidList;
    }


    // Checking weather user bid on item
    private boolean isBid(int bid_id, int bidder_id) {
        boolean isBid = false;
        // Select All Query
        String selectQuery = "SELECT * FROM " + AppConfig.BID_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and checking for match
        if (cursor.moveToFirst()) {
            do {
                if(Integer.parseInt(cursor.getString(0)) == bid_id || Integer.parseInt(cursor.getString(1)) == bidder_id) {
                    isBid = true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close(); // Closing database connection
        return isBid;
    }



    // Getting total number of bids an item
    private int bidCount(int bid_id) {
        int count;
        // Select All Query
        String selectQuery = "SELECT * FROM " + AppConfig.BID_TABLE + "WHERE " + AppConfig.ITEM_ID + " =" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(bid_id)});

        count = cursor.getCount();
        cursor.close(); // Closing database connection
        // return count
        return count;
    }


    // Getting the highest bid price on an item
    private Float bidPrice(int bid_id) {
        float price = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(AppConfig.BID_TABLE, new String[] { AppConfig.BID_PRICE },
                AppConfig.ID + "=?", new String[] { String.valueOf(bid_id) }, null, null, null, null);
        if (cursor != null) {
            // looping through all rows and checking for highest bid price
            if (cursor.moveToFirst()) {
                do {
                    if(Float.parseFloat(cursor.getString(0)) >= price) {
                        price = Float.parseFloat(cursor.getString(0));
                    }
                } while (cursor.moveToNext());
            }
        }
        cursor.close(); // Closing database connection
        // return highest price
        return price;
    }


    //Fetch All Users from USER_TABLE
    public String[] fetchAllUsers() {
        int i = 0;
        String[] allUsers = new String[] {};

        // Select All Query
        String selectQuery = "SELECT * FROM " + AppConfig.USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and checking for match
        if (cursor.moveToFirst()) {
            do {
                allUsers[i] = cursor.getString(0)+":"+cursor.getString(1)+":"+cursor.getString(2);
                i++;
            } while (cursor.moveToNext());

        }
        cursor.close(); // Closing database connection
        return allUsers;
    }

}