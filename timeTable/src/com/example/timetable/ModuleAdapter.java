package com.example.timetable;

import java.util.ArrayList;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ModuleAdapter extends BaseAdapter{

	private Context context = null;//The context in which the list is located
	private Intent intent = null;//Interface to jump
	private ArrayList<Module> ModuleInfo;//info of all fruit
	private Intent serviceIntent;
	private SharedPreferences sp;
	private SharedPreferences sharedPreferences;
	
	public ModuleAdapter(Context context, Intent intent, ArrayList<Module> moduleInfo2) {
		this.context = context;
		this.intent = intent;
		this.ModuleInfo = moduleInfo2;
	}//This is a constructor used to pass arguments from the outside
	
	public Context getContext() {
		return context;
	}//getter
	
	public Intent getIntent() {
		return intent;
	}//getter
	
	public void startActivity() {
		this.getContext().startActivity(getIntent());
	}//It's essentially startActivity (intent) the same like the normal form
	 //It just USES incoming data from outside
	
	//private Intent serviceIntent = new Intent(getContext(),NotifyService.class);
	
	public void removeItem(int position) {
		ModuleInfo.remove(position);
		getContext().stopService(serviceIntent);
		this.notifyDataSetChanged();
	}
	
//	public ArrayList<Module> passList() {
//		return ModuleInfo;
//	}
	
	@Override
	public int getCount() {
		return ModuleInfo.size();
	}

	@Override
	public Module getItem(int arg0) {
		// TODO Auto-generated method stub
		return ModuleInfo.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// a method to load the fruit info into the listfruit.xml(a model) to Apply to
		// each item

		LinearLayout ll = null;
		if (arg1 != null) {
			ll = (LinearLayout) arg1;
			// If it was previously loaded, it can be reloaded from here without having to
			// reload,
			// making a big difference in reducing memory consumption
		} else {
			ll = (LinearLayout) LayoutInflater.from(this.getContext()).inflate(R.layout.itemoflist, null);
			// Get the linear layout from the listfruit.xml
		}

		final Module module = getItem(arg0);
		serviceIntent = new Intent(getContext(),NotifyService.class);
		
		TextView texta = (TextView) ll.findViewById(R.id.texta);
		TextView textb = (TextView) ll.findViewById(R.id.textb);
		TextView textc = (TextView) ll.findViewById(R.id.textc);
		TextView textd = (TextView) ll.findViewById(R.id.textd);

		TextView courseCode = (TextView) ll.findViewById(R.id.courseCode);
		TextView courseType = (TextView) ll.findViewById(R.id.courseType);
		TextView courseWeek = (TextView) ll.findViewById(R.id.courseWeek);
		final TextView courseTime = (TextView) ll.findViewById(R.id.courseTime);
		TextView courseLocation = (TextView) ll.findViewById(R.id.courseLocation);

		// Set text
		courseCode.setText(module.getCode());
		courseType.setText(module.getSelectLL().substring(0, 1));
		courseWeek.setText(module.getWeek().substring(0, 2));
		courseTime.setText(module.getSelectTime1());
		courseLocation.setText(module.getLocation());
		
		final Button edit = (Button) ll.findViewById(R.id.edit);
		Button delete = (Button) ll.findViewById(R.id.delete);
		final int num = arg0;
		
		sp  = this.getContext().getSharedPreferences("Notice1", Context.MODE_PRIVATE);
		delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				new AlertDialog.Builder(getContext()).setTitle("Tips").setMessage("Are you sure to delete?")
						.setPositiveButton("YES", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								ModuleAdapter.this.removeItem(num);
								edit.setText("Turn On Notice");
								Editor e = sp.edit();
								e.putString("Notice"+num, "Turn On Notice");
								e.commit();
							}
						}).setNegativeButton("NO", null).show();

			}
		});
		
		
		edit.setText(sp.getString("Notice"+num, "Turn On Notice"));
		
		final String[] st = new String[]{"0Minute","5Minutes","10Minutes","15Minutes"};
		edit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(edit.getText().toString().equals("Turn On Notice")) {
					//Intent serviceIntent = new Intent(getContext(),NotifyService.class);
					new AlertDialog.Builder(getContext()).setTitle("Select a notification time in advance").setSingleChoiceItems(st, 0, new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							int i = 0;
							switch(arg1) {
							case 1:i = arg1*5;
							case 2:i = arg1*5;
							case 3:i = arg1*5;
							}
							serviceIntent.putExtra("st", i);
							
						}
					}).setPositiveButton("Confirm", new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							serviceIntent.putExtra("time", module.getSelectTime1());
							serviceIntent.putExtra("week", module.getWeek());
							serviceIntent.putExtra("order", ""+num);
							String title = module.getName()+" Upcoming...";
							String notifyc = module.getLocation()+" HINT: "+module.getComment();
							serviceIntent.putExtra("title", title);
							serviceIntent.putExtra("notifyc", notifyc);	
							getContext().startService(serviceIntent);
						}
					}).show();
					
					edit.setText("Turn Off Notice");
					Editor e = sp.edit();
					e.putString("Notice"+num, "Turn Off Notice");
					e.commit();
				}else {
					getContext().stopService(serviceIntent);
					System.out.println("333333333");
					edit.setText("Turn On Notice");
					Editor e = sp.edit();
					e.putString("Notice"+num, "Turn On Notice");
					e.commit();
				}
				
			}
		});
		
		sharedPreferences = this.getContext().getSharedPreferences("changecolor", Context.MODE_PRIVATE);
		if(sharedPreferences.getString("FontC","Black").equals("Red")) {
			texta.setTextColor(getContext().getResources().getColor(R.color.red));
			textb.setTextColor(getContext().getResources().getColor(R.color.red));
			textc.setTextColor(getContext().getResources().getColor(R.color.red));
			textd.setTextColor(getContext().getResources().getColor(R.color.red));
			courseCode.setTextColor(getContext().getResources().getColor(R.color.red));
			courseType.setTextColor(getContext().getResources().getColor(R.color.red));
			courseTime.setTextColor(getContext().getResources().getColor(R.color.red));
			courseLocation.setTextColor(getContext().getResources().getColor(R.color.red));
			courseWeek.setTextColor(getContext().getResources().getColor(R.color.red));
			delete.setTextColor(getContext().getResources().getColor(R.color.red));
			edit.setTextColor(getContext().getResources().getColor(R.color.red));
		} else if (sharedPreferences.getString("FontC","Black").equals("Blue")) {
			texta.setTextColor(getContext().getResources().getColor(R.color.blue));
			textb.setTextColor(getContext().getResources().getColor(R.color.blue));
			textc.setTextColor(getContext().getResources().getColor(R.color.blue));
			textd.setTextColor(getContext().getResources().getColor(R.color.blue));
			courseCode.setTextColor(getContext().getResources().getColor(R.color.blue));
			courseType.setTextColor(getContext().getResources().getColor(R.color.blue));
			courseTime.setTextColor(getContext().getResources().getColor(R.color.blue));
			courseLocation.setTextColor(getContext().getResources().getColor(R.color.blue));
			courseWeek.setTextColor(getContext().getResources().getColor(R.color.blue));
			delete.setTextColor(getContext().getResources().getColor(R.color.blue));
			edit.setTextColor(getContext().getResources().getColor(R.color.blue));
		}else {
			texta.setTextColor(getContext().getResources().getColor(R.color.balck));
			textb.setTextColor(getContext().getResources().getColor(R.color.balck));
			textc.setTextColor(getContext().getResources().getColor(R.color.balck));
			textd.setTextColor(getContext().getResources().getColor(R.color.balck));
			courseCode.setTextColor(getContext().getResources().getColor(R.color.balck));
			courseType.setTextColor(getContext().getResources().getColor(R.color.balck));
			courseTime.setTextColor(getContext().getResources().getColor(R.color.balck));
			courseLocation.setTextColor(getContext().getResources().getColor(R.color.balck));
			courseWeek.setTextColor(getContext().getResources().getColor(R.color.balck));
			delete.setTextColor(getContext().getResources().getColor(R.color.balck));
			edit.setTextColor(getContext().getResources().getColor(R.color.balck));
		}
		
		if(sharedPreferences.getString("Font", "").equals("Serif")) {
			texta.setTypeface(Typeface.SERIF);
			textb.setTypeface(Typeface.SERIF);
			textc.setTypeface(Typeface.SERIF);
			textd.setTypeface(Typeface.SERIF);
			courseCode.setTypeface(Typeface.SERIF);
			courseType.setTypeface(Typeface.SERIF);
			courseTime.setTypeface(Typeface.SERIF);
			courseLocation.setTypeface(Typeface.SERIF);
			courseWeek.setTypeface(Typeface.SERIF);
			delete.setTypeface(Typeface.SERIF);
			edit.setTypeface(Typeface.SERIF);
		}else if(sharedPreferences.getString("Font", "").equals("Monospace")) {
			texta.setTypeface(Typeface.MONOSPACE);
			textb.setTypeface(Typeface.MONOSPACE);
			textc.setTypeface(Typeface.MONOSPACE);
			textd.setTypeface(Typeface.MONOSPACE);
			courseCode.setTypeface(Typeface.MONOSPACE);
			courseType.setTypeface(Typeface.MONOSPACE);
			courseTime.setTypeface(Typeface.MONOSPACE);
			courseLocation.setTypeface(Typeface.MONOSPACE);
			courseWeek.setTypeface(Typeface.MONOSPACE);
			delete.setTypeface(Typeface.MONOSPACE);
			edit.setTypeface(Typeface.MONOSPACE);
		}else {
			texta.setTypeface(Typeface.SANS_SERIF);
			textb.setTypeface(Typeface.SANS_SERIF);
			textc.setTypeface(Typeface.SANS_SERIF);
			textd.setTypeface(Typeface.SANS_SERIF);
			courseCode.setTypeface(Typeface.SANS_SERIF);
			courseType.setTypeface(Typeface.SANS_SERIF);
			courseTime.setTypeface(Typeface.SANS_SERIF);
			courseLocation.setTypeface(Typeface.SANS_SERIF);
			courseWeek.setTypeface(Typeface.SANS_SERIF);
			delete.setTypeface(Typeface.SANS_SERIF);
			edit.setTypeface(Typeface.SANS_SERIF);
		}
		
		return ll;

	}

}
