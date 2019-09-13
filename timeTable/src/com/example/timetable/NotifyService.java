package com.example.timetable;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;

public class NotifyService extends Service {

	private AlarmManager alarmManager;
	private Calendar calendar;
	private String order;
	private SharedPreferences sp;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		order = intent.getStringExtra("order");
		String dayofweek = intent.getStringExtra("week");
		String title = intent.getStringExtra("title");
		String notifyc = intent.getStringExtra("notifyc");
		String[] time = intent.getStringExtra("time").split(":");
		int i = intent.getIntExtra("st", 0);
		System.out.println("000000000"+i);
		calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
		calendar.set(Calendar.SECOND, 0);
		
		Intent intent2 = new Intent(this,TimeReceiver.class);
		intent2.putExtra("WEEK", dayofweek);
		intent2.putExtra("ORDER", order);
		intent2.putExtra("TITLE", title);
		intent2.putExtra("NOTIFYC", notifyc);
		intent2.putExtra("TIME", intent.getStringExtra("time"));
		intent2.putExtra("ST", i);
		Calendar c = Calendar.getInstance();
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Integer.parseInt(order), intent2, PendingIntent.FLAG_UPDATE_CURRENT);
		if(c.getTimeInMillis()>=(calendar.getTimeInMillis()-i*60*1000)) {
			alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()-i*60*1000+604800000, pendingIntent);
		}else {
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()-i*60*1000, pendingIntent);
			}else {
				alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()-i*60*1000, 604800000, pendingIntent);			
			}
		}
		
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Intent intent2 = new Intent(this,TimeReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, Integer.parseInt(order), intent2, PendingIntent.FLAG_UPDATE_CURRENT);
		alarmManager.cancel(pendingIntent);
		Intent intent3 = new Intent(this,loopService.class);
		this.stopService(intent3);
		super.onDestroy();
		System.out.println("8888888");
	}
	
}
