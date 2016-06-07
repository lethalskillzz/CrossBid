import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "crossbid.db";
    private String user_table = "users";
    private String item_table = "items";
    private String bid_table = "bids";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + user_table +
                        "(id integer primary key, username text, password text)"
        );

        db.execSQL(
                "create table " + item_table +
                        "(id integer primary key, owner_id integer, item_name text, starting_price float, item_image blob)"
        );

        db.execSQL(
                "create table " + bid_table +
                        "(id integer primary key, bidder_id integer, item_id integer, bid_price float)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+user_table);
        db.execSQL("DROP TABLE IF EXISTS "+item_table);
        db.execSQL("DROP TABLE IF EXISTS "+bid_table);
        onCreate(db);
    }

    public void clearItems() {
        getWritableDatabase().execSQL("DELETE FROM "+item_table+";");
    }

    public void clearBids() {
        getWritableDatabase().execSQL("DELETE FROM "+bid_table+";");
    }

    public void insertUser(String username, String password) {
        ContentValues cv=new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        getWritableDatabase().insert(user_table, "username", cv);
    }

    public void insertItem(int owner_id, String item_name, float starting_price, byte[] item_image) {
        ContentValues cv=new ContentValues();
        cv.put("owner_id", owner_id);
        cv.put("item_name", item_name);
        cv.put("starting_price", starting_price);
        cv.put("item_image", item_image);
        getWritableDatabase().insert(item_table, "owner_id", cv);
    }

    public void insertBid(int bidder_id, int  item_id, float bid_price ) {
        ContentValues cv=new ContentValues();
        cv.put("bidder_id", bidder_id);
        cv.put("item_id", item_id);
        cv.put("bid_price", bid_price);
        getWritableDatabase().insert(bid_table, "bidder_id", cv);
    }


}