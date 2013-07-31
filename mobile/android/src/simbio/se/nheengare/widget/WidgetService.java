package simbio.se.nheengare.widget;

import simbio.se.nheengare.models.Word;
import simbio.se.nheengare.utils.Config;
import simbio.se.nheengare.utils.SimbiLog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WidgetService extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int widgetId = intent.getIntExtra("WidgetID", Config.WORD_WIDGET_DEFAULT_ID);
		SimbiLog.print("onStartCommand", widgetId);
		if (widgetId != Config.WORD_WIDGET_DEFAULT_ID) {
			Word word = WidgetProvider.getRandomWord(getApplicationContext());
			WidgetDataManager.createdWidgetWithId(getApplicationContext(), widgetId, word.getId());
			SimbiLog.print(">>", widgetId, word.getWriteUnique());
			WidgetProvider.updatePendingIntent(getApplicationContext(), widgetId, word.getWriteUnique());
		}
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		SimbiLog.print("onBind");
		return null;
	}
}
