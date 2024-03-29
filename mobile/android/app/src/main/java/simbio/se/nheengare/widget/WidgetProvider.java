package simbio.se.nheengare.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Calendar;

import simbio.se.nheengare.R;
import simbio.se.nheengare.core.BlackBoard;
import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.utils.Config;

public class WidgetProvider extends AppWidgetProvider {

    public static final String ACTION_OPEN_WORD = "simbio.se.nheengare.widget.action.OPEN_WORD";
    public static final String ACTION_CHANGE_WORD = "simbio.se.nheengare.widget.action.CHANGE_WORD";

    public static void updatePendingIntent(Context context, int widgetId, String wordText) {
        ComponentName provider = new ComponentName(context, WidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        int[] ids = manager.getAppWidgetIds(provider);
        for (int id : ids) {
            if (id == widgetId) {
                updatePendingIntent(context, widgetId, wordText, remoteViews);
            } else {
                int wordId = WidgetDataManager.getCurrentWordIdForWidget(context, id);
                Word word;
                if (wordId == Config.WORD_WIDGET_DEFAULT_ID) {
                    word = getRandomWord(context);
                    WidgetDataManager.createdWidgetWithId(context, id, word.getId());
                } else {
                    word = BlackBoard.getBlackBoard(context).getWordWithId(wordId);
                }
                updatePendingIntent(context, id, word.getWriteUnique(), remoteViews);
            }
        }
    }

    public static void updatePendingIntent(Context context, int widgetId, String wordText, RemoteViews remoteViews) {
        Intent intentOpenWord = new Intent();
        intentOpenWord.setAction(ACTION_OPEN_WORD);
        intentOpenWord.putExtra("WidgetID", widgetId);

        Intent intentChangeWord = new Intent();
        intentChangeWord.setAction(ACTION_CHANGE_WORD);
        intentChangeWord.putExtra("WidgetID", widgetId);

        PendingIntent pendingIntentOpenWord = PendingIntent.getBroadcast(context, widgetId, intentOpenWord, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentChangeWord = PendingIntent.getBroadcast(context, widgetId, intentChangeWord, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.widget_icon, pendingIntentChangeWord);
        remoteViews.setOnClickPendingIntent(R.id.widget_txt, pendingIntentOpenWord);
        remoteViews.setOnClickPendingIntent(R.id.widget_rl, pendingIntentOpenWord);

        remoteViews.setTextViewText(R.id.widget_txt, wordText);
        pushWidgetUpdate(context, remoteViews, widgetId);

        long lastBaseTimeOrZeroIfFirstTimeOrLessOneIfDeleted = WidgetDataManager.getLastTimeForWidget(context, widgetId);
        if (lastBaseTimeOrZeroIfFirstTimeOrLessOneIfDeleted == 0L) {
            scheduleRefresh(context, widgetId);
        }
    }

    public static void scheduleRefresh(Context context, int widgetId) {
        Calendar calendar = Calendar.getInstance();
        long timeBase = calendar.getTimeInMillis();
        long timeWait = 6 * 60 * 60 * 1000;

        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra("WidgetID", widgetId);
        PendingIntent pendingIntent = PendingIntent.getService(context, widgetId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pendingIntent);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, timeBase, timeWait, pendingIntent);

        WidgetDataManager.createOrUpdateWidgetTimeWithId(context, widgetId, timeBase);
    }

    public static Word getRandomWord(Context context) {
        ArrayList<Word> words = BlackBoard.getBlackBoard(context).getWords();
        return words.get((int) (Math.random() * (words.size() - 1)));
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews, int appWidgetId) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(appWidgetId, remoteViews);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        for (int id : appWidgetIds) {
            WidgetDataManager.deletedWidgetTimeWithId(context, id);
            WidgetDataManager.deletedWidgetWithId(context, id);
        }
    }

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
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        for (int id : appWidgetIds) {
            int wordId = WidgetDataManager.getCurrentWordIdForWidget(context, id);
            Word word;
            if (wordId == Config.WORD_WIDGET_DEFAULT_ID) {
                word = getRandomWord(context);
                WidgetDataManager.createdWidgetWithId(context, id, word.getId());
            } else {
                word = BlackBoard.getBlackBoard(context).getWordWithId(wordId);
            }
            updatePendingIntent(context, id, word.getWriteUnique(), remoteViews);
        }
    }

}
