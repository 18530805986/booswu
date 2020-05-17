package com.sawapps.baymaxhealthcare;


import android.app.Activity;

import android.content.Context;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.support.design.widget.Snackbar;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;



public class Utils {


    public static String BASE_URL = "http://ec2-54-85-214-162.compute-1.amazonaws.com:8080";


    static void showSnackbar(View view, String string) {

        Snackbar.make(view, string, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveLocally(Context context, String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences("ORNews", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
        Log.v("locale", "savingLocally " + value);
    }


    public static String getLocal(Context context, String key) {
        String locale = context.getSharedPreferences("ORNews", Context.MODE_PRIVATE).getString(key, null);

        return locale;

    }


    static String getBuildNumber(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


}
