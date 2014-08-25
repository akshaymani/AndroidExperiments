package com.example.countriesfrag;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class AddCountryFragment extends Fragment {
	EditText countryName;
	AddCountryFragmentListener listener;
	Button cancel;
	Button done;
	
	public AddCountryFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = 
				inflater.inflate(R.layout.fragment_add_country, container, false);
		countryName = (EditText) mainView.findViewById(R.id.countryName);
		cancel = (Button) mainView.findViewById(R.id.cancelButton);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager manager = 
						getFragmentManager();
				//hideKeyboard();
				manager.popBackStack();
			}
		});
		done = (Button) mainView.findViewById(R.id.doneButton);
		done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager manager = 
						getFragmentManager();
				if(listener != null) {
					String cName = countryName.getText().toString();
					listener.addCountry(cName);
				}
				hideKeyboard();
				manager.popBackStack();

			}
		});
		return mainView;

	}
	
	public void hideKeyboard() {
		InputMethodManager input = (InputMethodManager) getActivity().
                getSystemService(Activity.INPUT_METHOD_SERVICE);
        input.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
	}
	
}
