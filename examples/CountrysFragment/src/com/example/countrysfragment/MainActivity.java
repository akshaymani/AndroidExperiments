package com.example.countrysfragment;


import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements CountrysFragmentListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentManager manager = getFragmentManager();

		Fragment f = manager.findFragmentByTag("AddCountry");
		if (null == f) {
			addCountry();
		}
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

	@Override // For CountryList Widgets
	public void addCountry() {
		
		FragmentManager manager = getFragmentManager();
		
		CountryListFragment countryListFragment = new CountryListFragment();
		
		countryListFragment.listener = this;
		
		FragmentTransaction trans = manager.beginTransaction();
		
		trans.add(R.id.mainContainer, countryListFragment , "AddCountry");

		trans.commit();

	}

	@Override // For AddCountry Widgets
	public void done(String countryName) {
		
		
	}

	@Override // For AddCountry Widgets
	public void cancel() {
		FragmentManager manager = getFragmentManager();
		
		AddCountryFragment addCountryFragment = new AddCountryFragment();
		
		addCountryFragment.listener = this;
		finish();
	}
}
