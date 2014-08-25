package com.example.simpleintent;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void browse(View view) {
		// create an intent for requesting android system
		// to view a webpage
		Intent intent = new Intent(Intent.ACTION_VIEW); // android.action.intent.VIEW
		
		// android system asks the package manager is there any activity which can handle this intent
		// attach Uri for the page
		intent.setData(Uri.parse("http://www.google.com"));
		//intent.setData(Uri.parse("content://contacts/people"));
		
		// send the intent to android system
		startActivity(intent);
	}
	
	public void launchMain(View view) {
		// create an explicit intent to launch mainactivity
		//Intent intent = new Intent(this, MainActivity.class);
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		
		//send the intent to android system
		startActivity(intent);
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
