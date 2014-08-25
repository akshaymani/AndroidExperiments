package com.example.countrysfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CountryListFragment extends Fragment {

	TextView countryName;
	Button addButton;
	public CountrysFragmentListener listener;
	
	public CountryListFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.fragment_countrylist, container, false);
		
		countryName = (TextView) mainView.findViewById(R.id.textView1);
		addButton = (Button) mainView.findViewById(R.id.addCountry);
		addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != listener) {
					listener.addCountry();
				}
			}
			
		});
			
		return mainView;
	}
	
}
