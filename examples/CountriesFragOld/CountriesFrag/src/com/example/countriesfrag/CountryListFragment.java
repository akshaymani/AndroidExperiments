package com.example.countriesfrag;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
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
	Button addCountryButton;
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
		
		addCountryButton = (Button) mainView.findViewById(R.id.addButton);
		addCountryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(listener != null) {
					listener.switchToAddCountry();
				}
			}
		});
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
	
	
}
