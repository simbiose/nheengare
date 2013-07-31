package simbio.se.nheengare.widget;

import simbio.se.nheengare.activities.DetailActivity;
import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.utils.Config;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.google.analytics.tracking.android.EasyTracker;

public class WidgetBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(WidgetProvider.ACTION_OPEN_WORD)) {
			Intent activityIntent = new Intent(context, DetailActivity.class);
			activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			int wordId = WidgetDataManager.getCurrentWordIdForWidget(context, intent.getIntExtra("WidgetID", Config.WORD_WIDGET_DEFAULT_ID));
			activityIntent.putExtra("Word", wordId);
			activityIntent.putExtra("isFromHome", false);
			context.startActivity(activityIntent);
			EasyTracker.getInstance().setContext(context);
			EasyTracker.getTracker().sendView("/OW/" + wordId);
		} else if (intent.getAction().equals(WidgetProvider.ACTION_CHANGE_WORD)) {
			int widgetId = intent.getIntExtra("WidgetID", Config.WORD_WIDGET_DEFAULT_ID);
			if (widgetId == Config.WORD_WIDGET_DEFAULT_ID)
				return;
			Word word = WidgetProvider.getRandomWord(context);
			WidgetDataManager.createdWidgetWithId(context, widgetId, word.getId());
			WidgetProvider.updatePendingIntent(context, widgetId, word.getWriteUnique());
			EasyTracker.getInstance().setContext(context);
			EasyTracker.getTracker().sendView("/CW/" + widgetId);
		} else if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
			ComponentName provider = new ComponentName(context, WidgetProvider.class);
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			int ids[] = manager.getAppWidgetIds(provider);
			for (int id : ids)
				WidgetProvider.scheduleRefresh(context, id);
		}
	}
}
