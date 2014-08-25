package com.example.countries;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CountryListActivity extends ActionBarActivity { // To allow the activities to work before the android honeycomb series
//public class CountryListActivity extends Activity { // To create the second activity as a popup box we have to change the default superclass
	TextView countryListTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { // Bundle object information is lost if the device is shut down
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_country_list);
		
		countryListTextView = (TextView) findViewById(R.id.countryList);
		
		if (null != savedInstanceState) {
			String countryName = savedInstanceState.getString("COUNTRY_NAME");
			countryListTextView.setText(countryName);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		String countryName = countryListTextView.getText().toString();
		outState.putString("COUNTRY_NAME", countryName);
	}

	public void addCountry(View view) {
		Intent intent = new Intent(this, AddCountryActivity.class);
		//startActivity(intent);
		// using this we cannot a get a result back from the child activity
		
		startActivityForResult(intent, 101); // 101 is a unique identifier the result request made to the sub-activity
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		if (arg0 == 101 && arg1 == RESULT_OK) {
			String countryName = arg2.getStringExtra("COUNTRY_NAME");
			countryListTextView.setText(countryName);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.country_list, menu);
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
