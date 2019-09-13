package com.example.timetable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btnEnter, btnQuit, btnSet;
	private RelativeLayout relativeLayout;
	private SharedPreferences sharedPreferences;
	private TextView textZero;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sharedPreferences = this.getSharedPreferences("changecolor", Context.MODE_PRIVATE);
		btnEnter = (Button) this.findViewById(R.id.btnEnter);
		btnQuit = (Button) this.findViewById(R.id.btnQuit);
		btnSet = (Button) this.findViewById(R.id.btnSet);
		textZero = (TextView) this.findViewById(R.id.textZero);
		relativeLayout = (RelativeLayout) this.findViewById(R.id.mainbg);
		
		if(sharedPreferences.getString("FontC","Black").equals("Red")) {
			btnEnter.setTextColor(getResources().getColor(R.color.red));
			btnQuit.setTextColor(getResources().getColor(R.color.red));
			btnSet.setTextColor(getResources().getColor(R.color.red));
			textZero.setTextColor(getResources().getColor(R.color.red));
		} else if (sharedPreferences.getString("FontC","Black").equals("Blue")) {
			btnEnter.setTextColor(getResources().getColor(R.color.blue));
			btnQuit.setTextColor(getResources().getColor(R.color.blue));
			btnSet.setTextColor(getResources().getColor(R.color.blue));
			textZero.setTextColor(getResources().getColor(R.color.blue));
		}else {
			btnEnter.setTextColor(getResources().getColor(R.color.balck));
			btnQuit.setTextColor(getResources().getColor(R.color.balck));
			btnSet.setTextColor(getResources().getColor(R.color.balck));
			textZero.setTextColor(getResources().getColor(R.color.balck));
		}
		
		if(sharedPreferences.getString("Font", "").equals("Serif")) {
			btnEnter.setTypeface(Typeface.SERIF);
			btnQuit.setTypeface(Typeface.SERIF);
			btnSet.setTypeface(Typeface.SERIF);
			textZero.setTypeface(Typeface.SERIF);
		}else if(sharedPreferences.getString("Font", "").equals("Monospace")) {
			btnEnter.setTypeface(Typeface.MONOSPACE);
			btnQuit.setTypeface(Typeface.MONOSPACE);
			btnSet.setTypeface(Typeface.MONOSPACE);
			textZero.setTypeface(Typeface.MONOSPACE);
		}else {
			btnEnter.setTypeface(Typeface.SANS_SERIF);
			btnQuit.setTypeface(Typeface.SANS_SERIF);
			btnSet.setTypeface(Typeface.SANS_SERIF);
			textZero.setTypeface(Typeface.SANS_SERIF);
		}
		
		if (sharedPreferences.getString("BGColor", "White").equals("Red")) {
			relativeLayout.setBackgroundColor(getResources().getColor(R.color.red));
		} else if (sharedPreferences.getString("BGColor", "White").equals("Green")) {
			relativeLayout.setBackgroundColor(getResources().getColor(R.color.green));
		} else {
			relativeLayout.setBackgroundColor(getResources().getColor(R.color.white));
		}
		
		btnEnter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainActivity.this,NewModule.class);
				MainActivity.this.startActivity(i);
			}
		});
		
		btnQuit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		btnSet.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				LayoutInflater inflater = getLayoutInflater();
				final View layout = inflater.inflate(R.layout.change_dialog, null);
				final Spinner spFontColor = (Spinner) layout.findViewById(R.id.spFontColor);
				final Spinner spFont = (Spinner) layout.findViewById(R.id.spFont);
				final Spinner spBGColor = (Spinner) layout.findViewById(R.id.spBGColor);
				
				ArrayAdapter<String> adapterFC = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item);
				adapterFC.add("Black");
				adapterFC.add("Red");
				adapterFC.add("Blue");
				ArrayAdapter<String> adapterF = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item);
				adapterF.add("Sans");
				adapterF.add("Serif");
				adapterF.add("Monospace");
				ArrayAdapter<String> adapterBGC = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item);
				adapterBGC.add("White");
				adapterBGC.add("Red");
				adapterBGC.add("Green");
				
				spFontColor.setAdapter(adapterFC);
				spFont.setAdapter(adapterF);
				spBGColor.setAdapter(adapterBGC);
				
				new AlertDialog.Builder(MainActivity.this).setTitle("Setting").setView(layout)
						.setPositiveButton("Confirm", new OnClickListener() {
							
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								String FontC = (String) spFontColor.getSelectedItem();
								Editor e = sharedPreferences.edit();
								e.putString("FontC", FontC);
								if(FontC.equals("Red")) {
									btnEnter.setTextColor(getResources().getColor(R.color.red));
									btnQuit.setTextColor(getResources().getColor(R.color.red));
									btnSet.setTextColor(getResources().getColor(R.color.red));
									textZero.setTextColor(getResources().getColor(R.color.red));
								} else if (FontC.equals("Blue")) {
									btnEnter.setTextColor(getResources().getColor(R.color.blue));
									btnQuit.setTextColor(getResources().getColor(R.color.blue));
									btnSet.setTextColor(getResources().getColor(R.color.blue));
									textZero.setTextColor(getResources().getColor(R.color.blue));
								}else {
									btnEnter.setTextColor(getResources().getColor(R.color.balck));
									btnQuit.setTextColor(getResources().getColor(R.color.balck));
									btnSet.setTextColor(getResources().getColor(R.color.balck));
									textZero.setTextColor(getResources().getColor(R.color.balck));
								}
								
								String Font = (String) spFont.getSelectedItem();
								e.putString("Font", Font);
								if(Font.equals("Serif")) {
									btnEnter.setTypeface(Typeface.SERIF);
									btnQuit.setTypeface(Typeface.SERIF);
									btnSet.setTypeface(Typeface.SERIF);
									textZero.setTypeface(Typeface.SERIF);
								}else if(Font.equals("Monospace")) {
									btnEnter.setTypeface(Typeface.MONOSPACE);
									btnQuit.setTypeface(Typeface.MONOSPACE);
									btnSet.setTypeface(Typeface.MONOSPACE);
									textZero.setTypeface(Typeface.MONOSPACE);
								}else {
									btnEnter.setTypeface(Typeface.SANS_SERIF);
									btnQuit.setTypeface(Typeface.SANS_SERIF);
									btnSet.setTypeface(Typeface.SANS_SERIF);
									textZero.setTypeface(Typeface.SANS_SERIF);
								}

								String BGColor = (String) spBGColor.getSelectedItem();
								e.putString("BGColor", BGColor);
								e.commit();
								if (BGColor.equals("Red")) {
									relativeLayout.setBackgroundColor(getResources().getColor(R.color.red));
								} else if (BGColor.equals("Green")) {
									relativeLayout.setBackgroundColor(getResources().getColor(R.color.green));
								} else {
									relativeLayout.setBackgroundColor(getResources().getColor(R.color.white));
								}
							}
						}).setNegativeButton("Cancel", null).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
