package com.example.simplefragments;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements FirstFragmentListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// For example if we turn the device - Activity along with the Fragment will be terminated and if we want to load the existing fragment then use below code
		/*if (null == savedInstanceState) { First Way
			addFirstFragment();
		}*/ // Now the second way is given below

		FragmentManager manager = getFragmentManager();

		Fragment f = manager.findFragmentByTag("FF");
		if (null == f) {
			addFirstFragment();
		}
	}

	public void switchToSecondFragment() {
		FragmentManager manager = getFragmentManager();

		Fragment f = manager.findFragmentByTag("FF");

				if (null != f) {		
					FragmentTransaction trans = manager.beginTransaction();
					SecondFragment sf = new SecondFragment();
					// remove the first fragment from the activity
					trans.remove(f); // only 'this' is referring to the onClickListener which would be wrong

					// add the second fragment
					trans.add(R.id.mainContainer,sf, "SF");

					trans.addToBackStack("Switch"); // I can use the manager object and pop the fragments with the String names
					trans.commit();
				}
	}

	// Static vs Dynamic Fragment - https://developer.android.com/training/basics/fragments/fragment-ui.html#AddAtRuntime

	private void addFirstFragment() {
		// get access to fragment manager
		FragmentManager manager = getFragmentManager();

		FirstFragment ff = new FirstFragment(); // Regular Java Object added to the activity
		
		// ff.setRetainInstance(true); to retain the firstfragment even though the view will be recreated if activity is destroyed
		// adding itself as the listener
		ff.listener = this;
		
		// create a fragment transaction
		FragmentTransaction trans = manager.beginTransaction();

		// add the first fragment to the transaction
		// trans.add(Fragment,tag) is used for background fragments
		trans.add(R.id.mainContainer, ff , "FF");

		trans.commit();
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
