package com.example.countries;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

//public class AddCountryActivity extends ActionBarActivity {
public class AddCountryActivity extends Activity {

	EditText countryNameEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_country);
		
		countryNameEditText = (EditText) findViewById(R.id.countryName);
	}
	
	public void cancel(View view) {
		setResult(RESULT_CANCELED); // this is not required, but now onActivityResume() is called with empty intent as the param
		// finish terminates this activity, so activity manager opens the activity that is on the activity stack trace after this activity is popped
		finish();
	}
	
	public void done(View view) {
		String countryName = countryNameEditText.getText().toString();
		// Intent intent = new Intent(this,CountryListActivity.class); since this intent will not be used to launch a new activity
		
		Intent intent = new Intent();
		
		intent.putExtra("COUNTRY_NAME", countryName);
		//startActivity(intent);
		// we don't want to launch a new activity, as it will create a new activity on the activity stack trace
		// so the above line is not used here
		
		setResult(RESULT_OK, intent); // this will send the value back to the parent activity
		// onActivityResult() is the method called on the parent
		
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_country, menu);
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
