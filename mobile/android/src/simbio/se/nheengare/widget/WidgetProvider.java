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
	public static final String ACTION_CHANGE_WORD = "simbio.se.nheengare.widget.action.CHANGE_WORD";

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
			int wordId = WidgetDataManager.getCurrentWordIdForWidget(context, id);
			Word word;
			if (wordId == Config.WORD_WIDGET_DEFAULT_ID) {
				word = getRandomWord(context);
				WidgetDataManager.createdWidgetWithId(context, id, word.getId());
			} else {
				word = BlackBoard.getBlackBoard(context).getWordWithId(wordId);
			}
			updatePendingIntent(context, id, word.getWriteUnique());
		}
	}

	public static void updatePendingIntent(Context context, int widgetId, String wordText) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

		Intent intentOpenWord = new Intent();
		intentOpenWord.setAction(ACTION_OPEN_WORD);
		intentOpenWord.putExtra("WidgetID", widgetId);

		Intent intentChangeWord = new Intent();
		intentChangeWord.setAction(ACTION_CHANGE_WORD);
		intentChangeWord.putExtra("WidgetID", widgetId);

		PendingIntent pendingIntentOpenWord = PendingIntent.getBroadcast(context, 0, intentOpenWord, PendingIntent.FLAG_UPDATE_CURRENT);
		PendingIntent pendingIntentChangeWord = PendingIntent.getBroadcast(context, 0, intentChangeWord, PendingIntent.FLAG_UPDATE_CURRENT);

		remoteViews.setOnClickPendingIntent(R.id.widget_icon, pendingIntentChangeWord);
		remoteViews.setOnClickPendingIntent(R.id.widget_txt, pendingIntentOpenWord);
		remoteViews.setOnClickPendingIntent(R.id.widget_rl, pendingIntentOpenWord);

		remoteViews.setTextViewText(R.id.widget_txt, wordText);
		pushWidgetUpdate(context, remoteViews, widgetId);
	}

	public static Word getRandomWord(Context context) {
		ArrayList<Word> words = BlackBoard.getBlackBoard(context).getWords();
		return words.get((int) (Math.random() * (words.size() - 1)));
	}

	public static void pushWidgetUpdate(Context context, RemoteViews remoteViews, int appWidgetId) {
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		manager.updateAppWidget(appWidgetId, remoteViews);
	}
}