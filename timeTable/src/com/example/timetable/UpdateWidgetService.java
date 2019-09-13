package com.example.timetable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {

	private Timer timer;
	private TimerTask task;
	ArrayList<Module> ModuleInfo = new ArrayList<Module>();
	private AppWidgetManager awm;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		
		
		
		awm = AppWidgetManager.getInstance(getApplicationContext());
		timer = new Timer();
		task = new TimerTask() {
		
			@Override
			public void run() {
				if(ModuleInfo.size()!=loadData1()) {
					System.out.println(ModuleInfo.size()+"55555"+loadData1());
					ModuleInfo = UpdateWidgetService.this.loadData2();
				}
				
				ComponentName componentName = new ComponentName(UpdateWidgetService.this, WidgetJava.class);
				RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.widget);
				
				int count = ModuleInfo.size();
				System.out.println("!!!!!!!!!!****"+ModuleInfo.size());
				int p0 = 0;
				for (int i = 0; i < count; i++) {
					if (compareWeek(ModuleInfo.get(i).getWeek())) {
						if (ModuleInfo.get(i).getSelectLL().equals("Lecture")) {
							p0 = i;
							break;
						}
					}
				}
				int l0 = 0;
				for (int i = 0; i < count; i++) {
					if (compareWeek(ModuleInfo.get(i).getWeek())) {
						if (ModuleInfo.get(i).getSelectLL().equals("Practice")) {
							l0 = i;
							break;
						}
					}
				}
				Calendar calendarMIN = Calendar.getInstance();
				String[] timeMIN = ModuleInfo.get(p0).getSelectTime1().split(":");
				calendarMIN.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeMIN[0]));
				calendarMIN.set(Calendar.MINUTE, Integer.parseInt(timeMIN[1]));
				calendarMIN.set(Calendar.SECOND, 0);
				Calendar crrentTimeMIN = Calendar.getInstance();
				long min = calendarMIN.getTimeInMillis()-crrentTimeMIN.getTimeInMillis();
				
				Calendar calendarMIN2 = Calendar.getInstance();
				String[] timeMIN2 = ModuleInfo.get(l0).getSelectTime1().split(":");
				calendarMIN2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeMIN2[0]));
				calendarMIN2.set(Calendar.MINUTE, Integer.parseInt(timeMIN2[1]));
				calendarMIN2.set(Calendar.SECOND, 0);
				Calendar crrentTimeMIN2 = Calendar.getInstance();
				long min2 = calendarMIN2.getTimeInMillis()-crrentTimeMIN2.getTimeInMillis();
				
				for(int i = 0; i<count; i++) {
					System.out.println("ASD7");
					if(compareWeek(ModuleInfo.get(i).getWeek())) {
						System.out.println("ASD6");
						Calendar calendar = Calendar.getInstance();
						String[] time = ModuleInfo.get(i).getSelectTime1().split(":");
						calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
						calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
						calendar.set(Calendar.SECOND, 0);
						
						Calendar crrentTime = Calendar.getInstance();
//						calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
//						calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
//						calendar.set(Calendar.SECOND, 0);
						if(ModuleInfo.get(i).getSelectLL().equals("Lecture")) {
							long millisTime = calendar.getTimeInMillis();
							long current = crrentTime.getTimeInMillis();
							long diff = millisTime-current;
							if(min>=diff&&diff>=0) {
								min = diff;
							}else {
								continue;
							}
							if(diff<=5400000 && diff>=0) {
								System.out.println("ASD1");
								remoteViews.setTextViewText(R.id.ONETIME, ModuleInfo.get(i).getSelectTime1());
								remoteViews.setTextViewText(R.id.COURSEL, ModuleInfo.get(i).getName());
							}else {
								System.out.println("ASD2");
								remoteViews.setTextViewText(R.id.ONETIME, "NO");
								remoteViews.setTextViewText(R.id.COURSEL, "Have Fun!");
							}
						}else {
							long millisTime = calendar.getTimeInMillis();
							long current = crrentTime.getTimeInMillis();
							long diff = millisTime-current;
							if(min2>=diff&&diff>=0) {
								min2 = diff;
							}else {
								continue;
							}
							if(diff<=5400000 && diff>=0) {
								System.out.println("ASD3");
								remoteViews.setTextViewText(R.id.TWOTIME, ModuleInfo.get(i).getSelectTime1());
								remoteViews.setTextViewText(R.id.COURSEP, ModuleInfo.get(i).getName());
							}else {
								System.out.println("ASD4");
								remoteViews.setTextViewText(R.id.TWOTIME, "NO");
								remoteViews.setTextViewText(R.id.COURSEP, "Have Fun!");
							}
						}//lab
					}//judge week
					
				}//loop
				System.out.println("ASD10");
				awm.updateAppWidget(componentName, remoteViews);
			}
			
		};
		timer.scheduleAtFixedRate(task, 0, 2000);
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		timer.cancel();
		//task.cancel();
		timer = null;
		//task = null;
		super.onDestroy();
	}
	
	public boolean compareWeek(String weekday) {
		Calendar calendar = Calendar.getInstance();
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
        if(weekday.equals(ws)) {
        	return true;
        }else {
        	return false;
        }
	}
	
	private int loadData1() {
		try {
			
				
				// Open input stream to reload the info for "to"
				InputStream is = this.openFileInput("length");
				// Convert to bytes
				byte[] bytes = new byte[is.available()];
				// Read the bytes info
				is.read(bytes);
				// close input stream
				is.close();
				// Get the string and output
				String str = new String(bytes, "utf-8");
//				String[] newStr = str.split("!");
				int length = Integer.parseInt(str);
				System.out.println("!!!!!!!!"+length);
				return length;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	private ArrayList<Module> loadData2() {
		ArrayList<Module> newInfo = new ArrayList<Module>();
		try {
			
				
				// Open input stream to reload the info for "to"
				InputStream is = this.openFileInput("length");
				// Convert to bytes
				byte[] bytes = new byte[is.available()];
				// Read the bytes info
				is.read(bytes);
				// close input stream
				is.close();
				// Get the string and output
				String str = new String(bytes, "utf-8");
//				String[] newStr = str.split("!");
				int length = Integer.parseInt(str);
				System.out.println("!!!!!!!!"+length);
			
			
			for (int position = 0; position < length; position++) {
				// Open input stream to reload the info for "to"
				InputStream is2 = this.openFileInput(Integer.toString(position));
				// Convert to bytes
				byte[] bytes2 = new byte[is2.available()];
				// Read the bytes info
				is2.read(bytes2);
				// close input stream
				is2.close();
				// Get the string and output
				String str2 = new String(bytes2, "utf-8");
				String[] newStr2 = str2.split("!");
				newInfo.add(new Module(newStr2[0], newStr2[1], newStr2[2], newStr2[3], newStr2[4], newStr2[5],
						newStr2[6], newStr2[7]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newInfo;
	}
	
	
	
}
