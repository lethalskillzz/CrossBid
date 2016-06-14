package com.crossover.crossbid.util;

import android.content.Context;
import android.content.Intent;

import com.crossover.crossbid.activity.LoginActivity;
import com.crossover.crossbid.app.PrefManager;

/**
 * Created by Ibrahim on 10/06/2016.
 */
public class Logout {

    private Context context;

    public Logout(Context context) {
        this.context = context;
    }

    public void logout() {
        PrefManager pref = new PrefManager(context);
        pref.clearSession();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        context.startActivity(intent);
    }
}
