package com.rubyapps.addictedtostyle.helper;

import com.rubyapps.addictedtostyle.R;
import com.rubyapps.addictedtostyle.app.AppConfig;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

public class NotificationUtils {

	private String TAG = NotificationUtils.class.getSimpleName();

	private Context mContext;

	public NotificationUtils() {
	}

	public NotificationUtils(Context mContext) {
		this.mContext = mContext;
	}

	public void showNotificationMessage(String title, String message,
			String url, Intent intent) {

		int icon = R.drawable.logo;

		int mNotificationId = AppConfig.NOTIFICATION_ID;

		PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
				0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				mContext);
		Notification notification = mBuilder
				.setSmallIcon(icon)
				.setTicker(title)
				.setWhen(0)
				.setAutoCancel(true)
				.setContentTitle(title)
				.setStyle(inboxStyle)
				.setContentIntent(resultPendingIntent)
				.setSound(
						RingtoneManager
								.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
				.setLargeIcon(
						BitmapFactory.decodeResource(mContext.getResources(),
								icon)).setContentText(message).build();

		NotificationManager notificationManager = (NotificationManager) mContext
				.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(mNotificationId, notification);
		
	}

}
