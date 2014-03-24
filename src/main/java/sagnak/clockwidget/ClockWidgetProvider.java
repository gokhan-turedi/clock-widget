package sagnak.clockwidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by HP on 24.03.2014.
 */
public class ClockWidgetProvider extends AppWidgetProvider {

    // uygulamaya ait ilk widget home screen'e eklenince tetiklenir
    @Override
    public void onEnabled(Context context) {
        Toast.makeText(context, "ClockWidgetProvider.onEnabled", Toast.LENGTH_SHORT).show();
        super.onEnabled(context);

        // güncelleyici receiver için broadcast yayını hazırlıyoruz
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // alarmManager yardımı ile her sn broadcast yayınlıyoruz
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(
                AlarmManager.RTC, // type
                0, // triggerAtMillis
                1 * 1000, // intervalMillis
                pendingIntent // operation
        );
    }

    // uygulamaya ait son widget da home screen'den kaldırılınca tetiklenir
    @Override
    public void onDisabled(Context context) {
        Toast.makeText(context, "ClockWidgetProvider.onDisabled", Toast.LENGTH_SHORT).show();
        super.onDisabled(context);

        // güncelleyici receiver için periyodik tetikleme mekanizmasını kaldırıyoruz
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

}
