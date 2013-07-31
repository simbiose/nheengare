package simbio.se.nheengare.widget;

import simbio.se.nheengare.utils.Config;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class WidgetDataManager {

	public static int getCurrentWordIdForWidget(Context context, int widgetId) {
		int currentImageOrZeroIfNotExistes = PreferenceManager.getDefaultSharedPreferences(context).getInt(getKeyForWidgetId(widgetId), 0);
		if (currentImageOrZeroIfNotExistes == 0) {
			createdWidgetWithId(context, widgetId, Config.WORD_WIDGET_DEFAULT_ID);
			return Config.WORD_WIDGET_DEFAULT_ID;
		}
		return currentImageOrZeroIfNotExistes;
	}

	public static void updateWidgetWithId(Context context, int widgetId, int currentWordId) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(getKeyForWidgetId(widgetId), currentWordId);
		editor.commit();
	}

	public static void createdWidgetWithId(Context context, int widgetId, int wordId) {
		updateWidgetWithId(context, widgetId, wordId);
	}

	public static void deletedWidgetWithId(Context context, int widgetId) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(getKeyForWidgetId(widgetId));
		editor.commit();
	}

	public static long getLastTimeForWidget(Context context, int widgetId) {
		long currentImageOrZeroIfNotExistes = PreferenceManager.getDefaultSharedPreferences(context).getLong(getKeyForWidgetTime(widgetId), 0);
		return currentImageOrZeroIfNotExistes;
	}

	public static void createOrUpdateWidgetTimeWithId(Context context, int widgetId, long currentTime) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putLong(getKeyForWidgetTime(widgetId), currentTime);
		editor.commit();
	}

	public static void deletedWidgetTimeWithId(Context context, int widgetId) {
		createOrUpdateWidgetTimeWithId(context, widgetId, -1l);
	}

	private static String getKeyForWidgetId(int widgetId) {
		return "WORD_ID_" + widgetId;
	}

	private static String getKeyForWidgetTime(int widgetId) {
		return "W_T_" + widgetId;
	}

}
