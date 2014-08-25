package com.example.simpleactivities;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	EditText userEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// get a reference to the edit text, if we place this statement above setContentView() method, we would get a null value
		userEditText = (EditText) findViewById(R.id.userEditText);
	}
	
	public void send(View view) {
		// userEditText = (EditText) findViewById(R.id.userEditText); if this statement is placed here, everytime it will have to do a tree search
		// get the text user typed
		String userText = userEditText.getText().toString();
		
		Intent intent = new Intent(this, SecondActivity.class);
		
		intent.setAction("MainActivity"); // we can use this to specify from which activity the intent came but generally not preferrable
		
		// attach the string to the intent as an extra - as long as it implements the parcelable/serializable interface
		// cannot attach large binary data to intent
		intent.putExtra("USER_TEXT", userText); // this is different from the data we attach to the Intent which is generally a Uri
		// limit of the intent extra's is 1 MB, avoid attaching large data
		
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
