package com.example.day3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StopActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView tv= new TextView(this);
		tv.setText("StopActivity!~~");
		tv.setTextSize(50);
		setContentView(tv);
	}
}
