package sagnak.clockwidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

// widget'i güncelleyen receiver
public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // geçerli zaman
        String currentTime = new SimpleDateFormat("hh:mm:ss").format(new Date(System.currentTimeMillis()));

        // geçerli zamanı widget ui'a atıyoruz
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        remoteViews.setTextViewText(R.id.textView, currentTime);

        // widget ui'ını yeniliyoruz
        ComponentName widget = new ComponentName(context, ClockWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(widget, remoteViews);
    }

}
