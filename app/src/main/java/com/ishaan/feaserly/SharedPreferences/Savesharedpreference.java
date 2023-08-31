package com.ishaan.feaserly.SharedPreferences;

import static com.ishaan.feaserly.SharedPreferences.PreferencesUtility.*;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Savesharedpreference {
        static SharedPreferences getPreferences(Context context) {
            return PreferenceManager.getDefaultSharedPreferences(context);
        }

        public static void setLoggedIn(Context context, boolean loggedIn) {
            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putBoolean(LOGGED_IN_PREF, loggedIn);
            editor.apply();
        }

        public static boolean getLoggedStatus(Context context) {
            return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
        }

    public static void setToken(Context context, String token) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(Token, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        return getPreferences(context).getString(Token,"");
    }
    }
