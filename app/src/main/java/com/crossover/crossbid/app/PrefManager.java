package com.crossover.crossbid.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ibrahim on 12/06/2016.
 */
public class PrefManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "CrossBid";

    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USERNAME = "username";


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void storeUserDetails(String userId, String username, boolean isInit) {

        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USERNAME, username);
        if(isInit) {
            editor.putBoolean(KEY_IS_LOGGED_IN, true);
        }
        editor.commit();
    }

    public String getUserId() { return pref.getString(KEY_USER_ID, null); }

    public String getUsername() { return pref.getString(KEY_USERNAME, null); }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }

}
