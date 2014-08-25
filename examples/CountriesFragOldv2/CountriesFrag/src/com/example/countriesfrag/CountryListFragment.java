package com.example.countriesfrag;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CountryListFragment extends Fragment{
	ListView countryList;
	CountryListFragmentListener listener;
	ArrayList<String> countries = new ArrayList<String>();
	
	//ArrayAdapter<String> adapter;
	CountryAdapter adapter;
	
	StringBuffer buffer = new StringBuffer();
	
	public void addCountry(String name) {
		//buffer.append(name + "\n");
		
		countries.add(name);
		
		// reload the list view
		adapter.notifyDataSetChanged();
	}
	
	public void initCountries() {
		countries.add("India");
		countries.add("China");
		countries.add("Australia");
		countries.add("Austria");
		countries.add("Brazil");
		countries.add("Chile");
		countries.add("Comoros");
		countries.add("Cote-d'Ivoire");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// countryList.setText(buffer.toString());
	}

	public CountryListFragment() {
		initCountries();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// For every fragment that wants it's own options menu, needs to use the below statement
		setHasOptionsMenu(true);
		
		View mainView = 
				inflater.inflate(R.layout.fragment_country_list, container, false);
		
		countryList = (ListView) mainView.findViewById(R.id.listView1);
		
		// attach the onitemclicklistener
		countryList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(), countries.get(position), Toast.LENGTH_SHORT).show();
				
				loadWikiFragment(countries.get(position));
			}
		});
		
		// changes for adapter ?
		
		/*adapter = new ArrayAdapter<String>(getActivity(), // context 
				R.layout.row, // xml to inflate
				R.id.textView1, // id of textview in row.xml
				countries);*/
		
		adapter = new CountryAdapter(getActivity(), countries);
		
		// attach the adapter to the list
		countryList.setAdapter(adapter);
		
		return mainView;
	}

	public void loadWikiFragment(String name) {
		FragmentManager manager = getFragmentManager();
		
		FragmentTransaction trans = manager.beginTransaction();
		
		WikiFragment wf = new WikiFragment();
		wf.countryName = name;
		
		trans.remove(this);
		trans.add(R.id.mainContainer,wf, "WF");
		trans.addToBackStack(null);
		trans.commit();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		
		// add the Add Country option in options menu
		// menu.add("Add Country"); simply as options menu now below options menu as action item code is there
		
		MenuItem item = menu.add("Add Country");
		
		item.setIcon(android.R.drawable.ic_menu_add);
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().toString().equalsIgnoreCase("Add Country")) {
			if (null != listener) {
				listener.switchToAddCountry();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
