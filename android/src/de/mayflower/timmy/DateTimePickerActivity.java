package de.mayflower.timmy;

import java.util.Date;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.support.v4.app.NavUtils;

public class DateTimePickerActivity extends Activity {

	public static final String DATETIME_MESSAGE = "de.mayflower.timmy.MESSAGE";
	
	String field = null;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_time_picker);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
        
        if (intent != null) {
        
	        field = intent.getStringExtra(MainActivity.FIELD_MESSAGE);
	        
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_date_time_picker, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void closeThisForm(Date message) {
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		
		
		if (message != null) {
			
			intent.putExtra(DATETIME_MESSAGE, message);
			intent.putExtra(MainActivity.FIELD_MESSAGE, this.field);
			
		}
		
    	startActivity(intent);
    	//this.finish();
	
	}
	
	public void ReturnDateTimeToMainFormApplySettings (View view) {
		
		// TODO: Use Calendar
		
		TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
		DatePicker datePicker1 = (DatePicker) findViewById(R.id.datePicker1);
		
		Date date = new Date ();
		
		date.setHours(timePicker1.getCurrentHour());
		date.setMinutes(timePicker1.getCurrentMinute());
		
		date.setDate(datePicker1.getDayOfMonth());
		date.setMonth(datePicker1.getMonth());
		int year = datePicker1.getYear();
		date.setYear(datePicker1.getYear()-1900);
		
		closeThisForm(date);
		
	}
	
	public void ReturnDateTimeToMainFormIgnoreSettings (View view) {
		
		closeThisForm(null);
		
	}

}
