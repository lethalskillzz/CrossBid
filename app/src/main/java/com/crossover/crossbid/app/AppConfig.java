package com.crossover.crossbid.app;

/**
 * Created by Ibrahim on 09/06/2016.
 */
public class AppConfig {


    //Database constants
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "crossbid.db";

    public static  String userTable = "users";
    public static  String username= "username";
    public static  String password= "password";

    public static  String itemTable = "items";
    public static  String ownerId= "owner_id";
    public static  String itemName= "item_name";
    public static  String startingPrice= "starting_price";
    public static  String itemImage= "item_image";

    public static  String bidTable = "bids";
    public static  String bidderId= "bidder_id";
    public static  String itemId= "item_id";
    public static  String bidPrice= "bid_price";
}
