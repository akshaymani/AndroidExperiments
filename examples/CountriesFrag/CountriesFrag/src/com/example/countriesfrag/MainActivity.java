package com.example.countriesfrag;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity implements CountryListFragmentListener, AddCountryFragmentListener, TabListener {

	CountryListFragment clf;
	AboutFragment af;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		FragmentManager manager = getFragmentManager();
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab countryListTab = actionBar.newTab();
		countryListTab.setText("Country List");
		countryListTab.setTabListener(this); // make the activity as the listener for both the tabs - TabListener
		ActionBar.Tab aboutTab = actionBar.newTab();
		aboutTab.setText("About");
		aboutTab.setTabListener(this);
		
		// add both the tabs to the action bar
		actionBar.addTab(countryListTab);
		actionBar.addTab(aboutTab);

		/*CountryListFragment f = 
				(CountryListFragment)manager.findFragmentByTag("CLF");
		if (f == null) {
			addCountryListFragment();
		} else {
			f.listener = this;
		}*/
    }
    
    @Override
	public void onBackPressed() {
		FragmentManager manager = getFragmentManager();
		if(manager.getBackStackEntryCount() >= 1)
			manager.popBackStack();
		else
			super.onBackPressed();
	}
    public void addCountryListFragment() {
		// get access to FragmentManager
		FragmentManager manager = getFragmentManager();

		CountryListFragment ff = new CountryListFragment();
		ff.listener = this;
		
		// create a fragment transaction
		FragmentTransaction trans = manager.beginTransaction();

		// add the first fragment to the transaction
		trans.add(R.id.mainContainer, ff, "CLF");

		trans.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

	@Override
	public void switchToAddCountry() {
		FragmentManager manager = getFragmentManager();
		Fragment f = manager.findFragmentByTag("CLF");
		if (f != null) {
			AddCountryFragment sf = new AddCountryFragment();
			sf.listener = this;

			FragmentTransaction trans = manager.beginTransaction();
			// remove first fragment from the activity
			trans.remove(f);

			// add the second fragment
			trans.add(R.id.mainContainer, sf, "ACF");

			trans.addToBackStack(null);

			trans.commit();
		}

		
	}

	@Override
	public void addCountry(String add) {
		FragmentManager manager = getFragmentManager();

		CountryListFragment f = 
				(CountryListFragment)manager.findFragmentByTag("CLF");
		f.addCountry(add);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		if (tab.getText().toString().equalsIgnoreCase("Country List")) {
			if (null == clf) {
				clf = new CountryListFragment();
				clf.listener = this;
				ft.add(R.id.mainContainer,clf,"CLF"); // Tab listener will automatically do the commit
			}
			else {
				ft.attach(clf);
			}
		}
		if (tab.getText().toString().equalsIgnoreCase("About")) {
			if (null == af) {
				af = new AboutFragment();
				ft.add(R.id.mainContainer,af,"AF"); // Tab listener will automatically do the commit
			}
			else {
				ft.attach(af);
			}
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (tab.getText().toString().equalsIgnoreCase("Country List")) {
			if (null != clf) {
				ft.detach(clf);
			}
		}
		if (tab.getText().toString().equalsIgnoreCase("About")) {
			if (null != af) {
				ft.detach(af);
			}
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}
}
