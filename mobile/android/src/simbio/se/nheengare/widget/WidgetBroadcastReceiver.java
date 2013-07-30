package simbio.se.nheengare.widget;

import simbio.se.nheengare.activities.DetailActivity;
import simbio.se.nheengare.utils.Config;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WidgetBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(WidgetProvider.ACTION_OPEN_WORD)) {
			Intent activityIntent = new Intent(context, DetailActivity.class);
			activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			activityIntent.putExtra("Word", WidgetDataManager.getCurrentWordIdForWidget(context, intent.getIntExtra("WidgetID", Config.WORD_WIDGET_DEFAULT_ID)));
			activityIntent.putExtra("isFromHome", false);
			context.startActivity(activityIntent);
		}
	}
}
