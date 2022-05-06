package simbio.se.nheengare.widget;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.utils.Config;

public class WidgetService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int widgetId = intent.getIntExtra("WidgetID", Config.WORD_WIDGET_DEFAULT_ID);
        if (widgetId != Config.WORD_WIDGET_DEFAULT_ID) {
            Word word = WidgetProvider.getRandomWord(getApplicationContext());
            WidgetDataManager.createdWidgetWithId(getApplicationContext(), widgetId, word.getId());
            WidgetProvider.updatePendingIntent(getApplicationContext(), widgetId, word.getWriteUnique());
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
