package com.crossover.crossbid.app;

/**
 * Created by Ibrahim on 09/06/2016.
 */
public class AppConfig {


    //Database Constants
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "crossbid.db";

    public static  String USER_TABLE = "users";
    public static  String id = "id";
    public static  String username= "username";
    public static  String password= "password";

    public static  String ITEM_TABLE = "items";
    public static  String ownerId= "owner_id";
    public static  String itemTitle= "item_name";
    public static  String startingPrice= "starting_price";
    public static  String itemImage= "item_image";

    public static  String BID_TABLE = "bids";
    public static  String bidderId= "bidder_id";
    public static  String itemId= "item_id";
    public static  String bidPrice= "bid_price";

    //RecyclerView Constants
    public static final int VIEW_BID_ALL = 3;
    public static final int VIEW_BID_MY = 2;
    public static final int VIEW_BID_WON = 1;
    public static final int VIEW_PROG = 0;

}
