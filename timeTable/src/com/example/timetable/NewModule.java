package com.example.timetable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.textservice.SuggestionsInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class NewModule extends Activity{

	private Button addMod;
	ArrayList<Module> ModuleInfo = new ArrayList<Module>();
	private ListView lvModule;
	private ModuleAdapter moduleAdapter=null;
	private LinearLayout linearZero;
	private SharedPreferences sharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_module);
		
		this.reloadData();
		System.out.println("MMMMMMMMM");
		
		sharedPreferences = this.getSharedPreferences("changecolor", Context.MODE_PRIVATE);
		addMod = (Button) this.findViewById(R.id.btnAdd);
		linearZero = (LinearLayout) this.findViewById(R.id.linearZero);
		
		if(sharedPreferences.getString("FontC","Black").equals("Red")) {
			addMod.setTextColor(getResources().getColor(R.color.red));
		} else if (sharedPreferences.getString("FontC","Black").equals("Blue")) {
			addMod.setTextColor(getResources().getColor(R.color.blue));
		}else {
			addMod.setTextColor(getResources().getColor(R.color.balck));
		}
		
		if(sharedPreferences.getString("Font", "").equals("Serif")) {
			addMod.setTypeface(Typeface.SERIF);
		}else if(sharedPreferences.getString("Font", "").equals("Monospace")) {
			addMod.setTypeface(Typeface.MONOSPACE);
		}else {
			addMod.setTypeface(Typeface.SANS_SERIF);
		}
		
		if (sharedPreferences.getString("BGColor", "White").equals("Red")) {
			linearZero.setBackgroundColor(getResources().getColor(R.color.red));
		} else if (sharedPreferences.getString("BGColor", "White").equals("Green")) {
			linearZero.setBackgroundColor(getResources().getColor(R.color.green));
		} else {
			linearZero.setBackgroundColor(getResources().getColor(R.color.white));
		}
		
		addMod.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				LayoutInflater inflater = getLayoutInflater();
				final View layout = inflater.inflate(R.layout.dialog, null);
				
				new AlertDialog.Builder(NewModule.this).setTitle("New Moudle")
				.setView(layout).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						EditText modCode = (EditText) layout.findViewById(R.id.modCode);
						EditText modName = (EditText) layout.findViewById(R.id.modName);
						EditText modWeek = (EditText) layout.findViewById(R.id.modWeek);
						EditText modLocation = (EditText) layout.findViewById(R.id.modLocation);
						EditText modComment = (EditText) layout.findViewById(R.id.modComment);
						EditText timeStart = (EditText) layout.findViewById(R.id.timeStart);
						EditText timeEnd = (EditText) layout.findViewById(R.id.timeEnd);
						
						String code = modCode.getText().toString();
						String name = modName.getText().toString();
						String week = modWeek.getText().toString();				
						String location = modLocation.getText().toString();
						String comment = modComment.getText().toString();
						String selectTime1 = timeStart.getText().toString();
						//System.out.println(selectTime1);
						String selectTime2 = timeEnd.getText().toString();
						String selectLL;
						
						RadioButton lecture = (RadioButton) layout.findViewById(R.id.lecture);
						RadioButton lab = (RadioButton) layout.findViewById(R.id.lab);
						if(lecture.isChecked()) {
							selectLL = lecture.getText().toString(); 
						}else {
							selectLL = lab.getText().toString(); 
						}						
						
						Module course = new Module(code, name, selectLL, week, selectTime1, selectTime2,
								location, comment);
						
						ModuleInfo.add(course);
						System.out.println("!!!!!!!"+ModuleInfo.size()+"!!!!!!!");
						lvModule = (ListView) NewModule.this.findViewById(R.id.lvModule);
						moduleAdapter = new ModuleAdapter(NewModule.this, new Intent(NewModule.this, DetailActivity.class), ModuleInfo);
						lvModule.setAdapter(moduleAdapter);
						
//						timeStart.setOnClickListener(new View.OnClickListener() {
//							
//							@Override
//							public void onClick(View arg0) {
//
//								new TimePickerDialog(NewModule.this, new TimePickerDialog.OnTimeSetListener() {
//									
//									@Override
//									public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
//										timeStart.setText(String.format("%d:%d", arg1, arg0));
//									}
//								}, 10, 25, true).show();
//							}
//						});
					}
				}).setNegativeButton("Cancal", null).show();
				
				
			}
		});
		System.out.println("HHHHHHH");
		System.out.println("GGGGGG");
		
		lvModule = (ListView) NewModule.this.findViewById(R.id.lvModule);
		moduleAdapter = new ModuleAdapter(this, new Intent(this, DetailActivity.class), ModuleInfo);
		lvModule.setAdapter(moduleAdapter);

		lvModule.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Module course = moduleAdapter.getItem(arg2);
				Intent i = moduleAdapter.getIntent();
				
				String information = course.getCode() + "!" + course.getName() + "!" + course.getSelectLL() + "!"
						+ course.getWeek() + "!" + course.getSelectTime1() + "!" + course.getSelectTime2() + "!"
						+ course.getLocation() + "!" + course.getComment();
				i.putExtra("info",information);
				moduleAdapter.startActivity();
			}
			
		});
		
		
	}
	
	// This method USES internal storage to reload data
	private void reloadData() {
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
//				ModuleInfo.add(
//						new Module(newStr[1], newStr[2], newStr[3], newStr[4], newStr[5], newStr[6], newStr[7], newStr[8]));

			
			
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
				ModuleInfo.add(new Module(newStr2[0], newStr2[1], newStr2[2], newStr2[3], newStr2[4], newStr2[5],
						newStr2[6], newStr2[7]));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	
	// This method USES internal storage to save data
	private void saveData() {
		try {
//			if(moduleAdapter.getCount()!=ModuleInfo.size()) {
//				ModuleInfo = moduleAdapter.passList();
//			}
			System.out.println(ModuleInfo.size()+"!!!!!!!!"+ModuleInfo.isEmpty());
			int length = ModuleInfo.size();
			
			String len = length+"";
			// Open output stream to save the info for "to"
			OutputStream osl = this.openFileOutput("length", Context.MODE_PRIVATE);
			// Writes the information to be saved in the buffer pool
			osl.write(len.getBytes("UTF-8"));
			osl.flush();
			// close output stream
			osl.close();
			
			for (int position = 0; position < length; position++) {

				String message = ModuleInfo.get(position).getCode() + "!"
						+ ModuleInfo.get(position).getName() + "!" + ModuleInfo.get(position).getSelectLL() + "!"
						+ ModuleInfo.get(position).getWeek() + "!" + ModuleInfo.get(position).getSelectTime1() + "!"
						+ ModuleInfo.get(position).getSelectTime2() + "!" + ModuleInfo.get(position).getLocation() + "!"
						+ ModuleInfo.get(position).getComment();

				// Open output stream to save the info for "to"
				OutputStream os = this.openFileOutput(Integer.toString(position), Context.MODE_PRIVATE);
				// Writes the information to be saved in the buffer pool
				os.write(message.getBytes("UTF-8"));
				os.flush();
				// close output stream
				os.close();
				System.out.println(ModuleInfo.size()+"Size:");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	protected void onPause() {
		super.onPause();
		// Rewrite the onPause() method so that each pause will save the corresponding
		// information
		NewModule.this.saveData();
	}
	
}
