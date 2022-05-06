package simbio.se.nheengare.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import simbio.se.nheengare.utils.Config;

public class WidgetDataManager {

    public static int getCurrentWordIdForWidget(Context context, int widgetId) {
        int currentImageOrZeroIfNotExists = PreferenceManager.getDefaultSharedPreferences(context).getInt(getKeyForWidgetId(widgetId), 0);
        if (currentImageOrZeroIfNotExists == 0) {
            createdWidgetWithId(context, widgetId, Config.WORD_WIDGET_DEFAULT_ID);
            return Config.WORD_WIDGET_DEFAULT_ID;
        }
        return currentImageOrZeroIfNotExists;
    }

    public static void updateWidgetWithId(Context context, int widgetId, int currentWordId) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(getKeyForWidgetId(widgetId), currentWordId);
        editor.apply();
    }

    public static void createdWidgetWithId(Context context, int widgetId, int wordId) {
        updateWidgetWithId(context, widgetId, wordId);
    }

    public static void deletedWidgetWithId(Context context, int widgetId) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(getKeyForWidgetId(widgetId));
        editor.apply();
    }

    public static long getLastTimeForWidget(Context context, int widgetId) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(getKeyForWidgetTime(widgetId), 0);
    }

    public static void createOrUpdateWidgetTimeWithId(Context context, int widgetId, long currentTime) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(getKeyForWidgetTime(widgetId), currentTime);
        editor.apply();
    }

    public static void deletedWidgetTimeWithId(Context context, int widgetId) {
        createOrUpdateWidgetTimeWithId(context, widgetId, -1L);
    }

    private static String getKeyForWidgetId(int widgetId) {
        return "WORD_ID_" + widgetId;
    }

    private static String getKeyForWidgetTime(int widgetId) {
        return "W_T_" + widgetId;
    }

}
