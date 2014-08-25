package com.example.countrysfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddCountryFragment extends Fragment {
	
	TextView enterName;
	EditText countryName;
	Button done;
	Button cancel;
	public CountrysFragmentListener listener;

	public AddCountryFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.fragment_addcountry, container, false);
		
		enterName = (TextView) mainView.findViewById(R.id.textView1);
		countryName = (EditText) mainView.findViewById(R.id.editText1);
		done = (Button) mainView.findViewById(R.id.doneButton);
		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != listener) {
					listener.done(countryName.getText().toString());
				}
			}
			
		});
		
		cancel = (Button) mainView.findViewById(R.id.cancelButton);
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (null != listener) {
					listener.cancel();
				}
			}
			
		});
		
		return mainView;
	}
	
}
