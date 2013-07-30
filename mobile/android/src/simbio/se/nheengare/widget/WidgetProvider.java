package simbio.se.nheengare.widget;

import java.util.ArrayList;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.BlackBoard;
import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.utils.Config;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

	public static final String ACTION_OPEN_WORD = "simbio.se.nheengare.widget.action.OPEN_WORD";

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		ComponentName provider = new ComponentName(context, WidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		updateWidgetsIntents(context, manager.getAppWidgetIds(provider));
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		updateWidgetsIntents(context, appWidgetIds);
	}

	private void updateWidgetsIntents(Context context, int[] appWidgetIds) {
		for (int id : appWidgetIds) {
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
			PendingIntent pendingIntent = buildButtonPendingIntent(context, id);
			remoteViews.setOnClickPendingIntent(R.id.widget_icon, pendingIntent);
			remoteViews.setOnClickPendingIntent(R.id.widget_txt, pendingIntent);
			remoteViews.setOnClickPendingIntent(R.id.widget_rl, pendingIntent);

			int wordId = WidgetDataManager.getCurrentWordIdForWidget(context, id);
			Word word;
			if (wordId == Config.WORD_WIDGET_DEFAULT_ID) {
				word = getRandomWord(context);
				WidgetDataManager.createdWidgetWithId(context, id, word.getId());
			} else {
				word = BlackBoard.getBlackBoard(context).getWordWithId(wordId);
			}

			remoteViews.setTextViewText(R.id.widget_txt, word.getWriteUnique());
			pushWidgetUpdate(context, remoteViews, id);
		}
	}

	public static Word getRandomWord(Context context) {
		ArrayList<Word> words = BlackBoard.getBlackBoard(context).getWords();
		return words.get((int) (Math.random() * (words.size() - 1)));
	}

	public static PendingIntent buildButtonPendingIntent(Context context, int widgetId) {
		Intent intent = new Intent();
		intent.setAction(ACTION_OPEN_WORD);
		intent.putExtra("WidgetID", widgetId);
		return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public static void pushWidgetUpdate(Context context, RemoteViews remoteViews, int appWidgetId) {
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(appWidgetId, remoteViews);
	}
}