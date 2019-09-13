package com.example.timetable;

import java.util.Calendar;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class TimeReceiver extends BroadcastReceiver {

	private Calendar calendar;
	private Intent serviceIntent;
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		String dayofweek = arg1.getStringExtra("WEEK");
		String order = arg1.getStringExtra("ORDER");
		String title = arg1.getStringExtra("TITLE");
		String notifyc = arg1.getStringExtra("NOTIFYC");		
		calendar = Calendar.getInstance();
		int w = calendar.get(Calendar.DAY_OF_WEEK);
		String ws = "";
        switch (w) {  
            case 1:  
                ws = "Sunday";break;
            case 2:  
            	ws = "Monday";break;            
            case 3:  
            	ws = "Tuesday";break;
            case 4:  
            	ws = "Wednesday";break;  
            case 5:  
            	ws = "Thursday";break;
            case 6:  
            	ws = "Friday";break; 
            case 7:  
            	ws = "Saturday";break;
        }  
		
		if(dayofweek.equals(ws)) {
			Intent i = new Intent(arg0, MainActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(arg0, Integer.parseInt(order), i, PendingIntent.FLAG_UPDATE_CURRENT);
			NotificationManager manager = (NotificationManager) arg0.getSystemService(Context.NOTIFICATION_SERVICE);
			
			Notification notification = new NotificationCompat.Builder(arg0)
			        .setContentTitle(title)//Set the title, as necessary
			        .setContentText(notifyc)//Set the content, as necessary
			        .setWhen(System.currentTimeMillis())//Set time, default Settings, can be ignored
			        .setSmallIcon(R.drawable.ic_launcher)//Set the small icon in the notification bar. It must be set
			        .setAutoCancel(true)//Set auto delete. After clicking the notification bar, the system will automatically delete the notification of the status bar, which is used with setContentIntent
			        .setContentIntent(pendingIntent)//Sets the jump after the message is clicked in the notification bar. The parameter is a pendingIntent
			        .build();
			manager.notify(Integer.parseInt(order),notification);
			System.out.println("*********");
			//NotifyService ns = new NotifyService();
			//ns.repeatAlarm();
		}
		
		serviceIntent = new Intent(arg0,loopService.class);
		serviceIntent.putExtra("st", arg1.getIntExtra("ST", 0));
		serviceIntent.putExtra("time", arg1.getStringExtra("TIME"));
		serviceIntent.putExtra("week", dayofweek);
		serviceIntent.putExtra("order", order);
		serviceIntent.putExtra("title", title);
		serviceIntent.putExtra("notifyc", notifyc);	
		arg0.startService(serviceIntent);
		
	}

}
