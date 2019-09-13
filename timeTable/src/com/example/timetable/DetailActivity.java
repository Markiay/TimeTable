package com.example.timetable;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DetailActivity extends Activity{
	
	private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, code, name, ll, week, time1, time2, location, comment;
	private Button back;
	private SharedPreferences sharedPreferences;
	private LinearLayout llayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.module_detail);
		
		code = (TextView) this.findViewById(R.id.Acode);
		name = (TextView) this.findViewById(R.id.Aname);
		ll = (TextView) this.findViewById(R.id.LL);
		week = (TextView) this.findViewById(R.id.Aweek);
		location = (TextView) this.findViewById(R.id.Alocation);
		comment = (TextView) this.findViewById(R.id.Acomment);
		time1 = (TextView) this.findViewById(R.id.TimeS);
		time2 = (TextView) this.findViewById(R.id.TimeE);
		
		back = (Button) this.findViewById(R.id.btnBack);
		
		tv1 = (TextView) this.findViewById(R.id.textView1);
		tv2 = (TextView) this.findViewById(R.id.textView2);
		tv3 = (TextView) this.findViewById(R.id.textView3);
		tv4 = (TextView) this.findViewById(R.id.textView4);
		tv5 = (TextView) this.findViewById(R.id.textView5);
		tv6 = (TextView) this.findViewById(R.id.textView6);
		tv7 = (TextView) this.findViewById(R.id.textView7);
		tv8 = (TextView) this.findViewById(R.id.textView8);
		tv9 = (TextView) this.findViewById(R.id.textView9);
		
		sharedPreferences = this.getSharedPreferences("changecolor", Context.MODE_PRIVATE);
		if(sharedPreferences.getString("FontC","Black").equals("Red")) {
			code.setTextColor(getResources().getColor(R.color.red));
			name.setTextColor(getResources().getColor(R.color.red));
			ll.setTextColor(getResources().getColor(R.color.red));
			week.setTextColor(getResources().getColor(R.color.red));
			location.setTextColor(getResources().getColor(R.color.red));
			comment.setTextColor(getResources().getColor(R.color.red));
			time1.setTextColor(getResources().getColor(R.color.red));
			time2.setTextColor(getResources().getColor(R.color.red));
			back.setTextColor(getResources().getColor(R.color.red));
			tv1.setTextColor(getResources().getColor(R.color.red));
			tv2.setTextColor(getResources().getColor(R.color.red));
			tv3.setTextColor(getResources().getColor(R.color.red));
			tv4.setTextColor(getResources().getColor(R.color.red));
			tv5.setTextColor(getResources().getColor(R.color.red));
			tv6.setTextColor(getResources().getColor(R.color.red));
			tv7.setTextColor(getResources().getColor(R.color.red));
			tv8.setTextColor(getResources().getColor(R.color.red));
			tv9.setTextColor(getResources().getColor(R.color.red));
		} else if (sharedPreferences.getString("FontC","Black").equals("Blue")) {
			code.setTextColor(getResources().getColor(R.color.blue));
			name.setTextColor(getResources().getColor(R.color.blue));
			ll.setTextColor(getResources().getColor(R.color.blue));
			week.setTextColor(getResources().getColor(R.color.blue));
			location.setTextColor(getResources().getColor(R.color.blue));
			comment.setTextColor(getResources().getColor(R.color.blue));
			time1.setTextColor(getResources().getColor(R.color.blue));
			time2.setTextColor(getResources().getColor(R.color.blue));
			back.setTextColor(getResources().getColor(R.color.blue));
			tv1.setTextColor(getResources().getColor(R.color.blue));
			tv2.setTextColor(getResources().getColor(R.color.blue));
			tv3.setTextColor(getResources().getColor(R.color.blue));
			tv4.setTextColor(getResources().getColor(R.color.blue));
			tv5.setTextColor(getResources().getColor(R.color.blue));
			tv6.setTextColor(getResources().getColor(R.color.blue));
			tv7.setTextColor(getResources().getColor(R.color.blue));
			tv8.setTextColor(getResources().getColor(R.color.blue));
			tv9.setTextColor(getResources().getColor(R.color.blue));
		}else {
			code.setTextColor(getResources().getColor(R.color.balck));
			name.setTextColor(getResources().getColor(R.color.balck));
			ll.setTextColor(getResources().getColor(R.color.balck));
			week.setTextColor(getResources().getColor(R.color.balck));
			location.setTextColor(getResources().getColor(R.color.balck));
			comment.setTextColor(getResources().getColor(R.color.balck));
			time1.setTextColor(getResources().getColor(R.color.balck));
			time2.setTextColor(getResources().getColor(R.color.balck));
			back.setTextColor(getResources().getColor(R.color.balck));
			tv1.setTextColor(getResources().getColor(R.color.balck));
			tv2.setTextColor(getResources().getColor(R.color.balck));
			tv3.setTextColor(getResources().getColor(R.color.balck));
			tv4.setTextColor(getResources().getColor(R.color.balck));
			tv5.setTextColor(getResources().getColor(R.color.balck));
			tv6.setTextColor(getResources().getColor(R.color.balck));
			tv7.setTextColor(getResources().getColor(R.color.balck));
			tv8.setTextColor(getResources().getColor(R.color.balck));
			tv9.setTextColor(getResources().getColor(R.color.balck));
		}
		
		if(sharedPreferences.getString("Font", "").equals("Serif")) {
			code.setTypeface(Typeface.SERIF);
			name.setTypeface(Typeface.SERIF);
			ll.setTypeface(Typeface.SERIF);
			week.setTypeface(Typeface.SERIF);
			location.setTypeface(Typeface.SERIF);
			comment.setTypeface(Typeface.SERIF);
			time1.setTypeface(Typeface.SERIF);
			time2.setTypeface(Typeface.SERIF);
			back.setTypeface(Typeface.SERIF);
			tv1.setTypeface(Typeface.SERIF);
			tv2.setTypeface(Typeface.SERIF);
			tv3.setTypeface(Typeface.SERIF);
			tv4.setTypeface(Typeface.SERIF);
			tv5.setTypeface(Typeface.SERIF);
			tv6.setTypeface(Typeface.SERIF);
			tv7.setTypeface(Typeface.SERIF);
			tv8.setTypeface(Typeface.SERIF);
			tv9.setTypeface(Typeface.SERIF);
		}else if(sharedPreferences.getString("Font", "").equals("Monospace")) {
			code.setTypeface(Typeface.MONOSPACE);
			name.setTypeface(Typeface.MONOSPACE);
			ll.setTypeface(Typeface.MONOSPACE);
			week.setTypeface(Typeface.MONOSPACE);
			location.setTypeface(Typeface.MONOSPACE);
			comment.setTypeface(Typeface.MONOSPACE);
			time1.setTypeface(Typeface.MONOSPACE);
			time2.setTypeface(Typeface.MONOSPACE);
			back.setTypeface(Typeface.MONOSPACE);
			tv1.setTypeface(Typeface.MONOSPACE);
			tv2.setTypeface(Typeface.MONOSPACE);
			tv3.setTypeface(Typeface.MONOSPACE);
			tv4.setTypeface(Typeface.MONOSPACE);
			tv5.setTypeface(Typeface.MONOSPACE);
			tv6.setTypeface(Typeface.MONOSPACE);
			tv7.setTypeface(Typeface.MONOSPACE);
			tv8.setTypeface(Typeface.MONOSPACE);
			tv9.setTypeface(Typeface.MONOSPACE);
		}else {
			code.setTypeface(Typeface.SANS_SERIF);
			name.setTypeface(Typeface.SANS_SERIF);
			ll.setTypeface(Typeface.SANS_SERIF);
			week.setTypeface(Typeface.SANS_SERIF);
			location.setTypeface(Typeface.SANS_SERIF);
			comment.setTypeface(Typeface.SANS_SERIF);
			time1.setTypeface(Typeface.SANS_SERIF);
			time2.setTypeface(Typeface.SANS_SERIF);
			back.setTypeface(Typeface.SANS_SERIF);
			tv1.setTypeface(Typeface.SANS_SERIF);
			tv2.setTypeface(Typeface.SANS_SERIF);
			tv3.setTypeface(Typeface.SANS_SERIF);
			tv4.setTypeface(Typeface.SANS_SERIF);
			tv5.setTypeface(Typeface.SANS_SERIF);
			tv6.setTypeface(Typeface.SANS_SERIF);
			tv7.setTypeface(Typeface.SANS_SERIF);
			tv8.setTypeface(Typeface.SANS_SERIF);
			tv9.setTypeface(Typeface.SANS_SERIF);
		}
		
		llayout = (LinearLayout) this.findViewById(R.id.llayout);
		if (sharedPreferences.getString("BGColor", "White").equals("Red")) {
			llayout.setBackgroundColor(getResources().getColor(R.color.red));
		} else if (sharedPreferences.getString("BGColor", "White").equals("Green")) {
			llayout.setBackgroundColor(getResources().getColor(R.color.green));
		} else {
			llayout.setBackgroundColor(getResources().getColor(R.color.white));
		}
		
		String str = getIntent().getStringExtra("info");
		String[] newstr = str.split("!");
		code.setText(newstr[0]);
		name.setText(newstr[1]);
		ll.setText(newstr[2]);
		week.setText(newstr[3]);
		time1.setText(newstr[4]);
		time2.setText(newstr[5]);
		location.setText(newstr[6]);
		comment.setText(newstr[7]);
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
	}
}
