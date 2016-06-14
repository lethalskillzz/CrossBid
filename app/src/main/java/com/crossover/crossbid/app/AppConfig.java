package com.crossover.crossbid.app;

/**
 * Created by Ibrahim on 09/06/2016.
 */
public class AppConfig {


    //Database Constants
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "crossbid.db";

    public static  String USER_TABLE = "users";
    public static  String ID = "id";
    public static  String USERNAME= "username";
    public static  String PASSWORD= "password";

    public static  String ITEM_TABLE = "items";
    public static  String OWNER_ID= "owner_id";
    public static  String ITEM_TITLE= "item_name";
    public static  String STARTING_PRICE= "starting_price";
    public static  String ITEM_IMAGE= "item_image";

    public static  String BID_TABLE = "bids";
    public static  String BIDDER_ID= "bidder_id";
    public static  String ITEM_ID= "item_id";
    public static  String BID_PRICE= "bid_price";

    //RecyclerView Constants
    public static final int VIEW_BID_ALL = 3;
    public static final int VIEW_BID_MY = 2;
    public static final int VIEW_BID_WON = 1;
    public static final int VIEW_PROG = 0;

}
