package br.com.edu.settingsapplication.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SharedPreferencesHelper {

    public static SharedPreferences getSharedPreference(Context context, String preferenceName) {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    public static void saveBoolean(Context context, String preferenceName, String preferenceKey, boolean value) {
        SharedPreferences.Editor editor = getSharedPreference(context, preferenceName).edit();
        editor.putBoolean(preferenceKey, value);
        editor.apply();
    }

    public static void saveString(Context context, String preferenceName, String preferenceKey, String value) {
        SharedPreferences.Editor editor = getSharedPreference(context, preferenceName).edit();
        editor.putString(preferenceKey, value);
        editor.apply();
    }

    public static void saveInteger(Context context, String preferenceName, String preferenceKey, int value) {
        SharedPreferences.Editor editor = getSharedPreference(context, preferenceName).edit();
        editor.putInt(preferenceKey, value);
        editor.apply();
    }


    public static class Preference {
        public static final String NIGHT_MODE_NAME = "night_mode_shared_preferences";
        public static final String NIGHT_MODE_VALUE = "night_mode";
        public static final String VISUALISATION_MODE_NAME = "visualisation_mode_preferences";
        public static final String VISUALISATION_MODE_VALUE = "visualisation_mode";

        public static SharedPreferences getNightMode(Context context) {
            return getSharedPreference(context, NIGHT_MODE_NAME);
        }

        public static SharedPreferences getVisualisationMode(Context context) {
            return getSharedPreference(context, VISUALISATION_MODE_NAME);
        }
    }

}
