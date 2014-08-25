package com.example.simplefragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FirstFragment extends Fragment {

	Button switchButton;
	public FirstFragmentListener listener;
	
	public FirstFragment() {
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate our xml incase of fragments ourselves but incase of activity it is automatically done once we call setContentView()
		
		// View represents a single widget on a the screen, editText etc are sub-classes of it.
		// If we specify in below statement as true then the container (ViewGroup) would load the fragment and once the activity is called
		// ...then again this fragment is loaded, so multiple instances are there which is not required
		//View mainView = inflater.inflate(R.layout.fragment_first, container, true);
		View mainView = inflater.inflate(R.layout.fragment_first, container, false);
		
		switchButton = (Button) mainView.findViewById(R.id.button1);
		switchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.switchToSecondFragment();
				}
				/*
				// The below two lines remove the dependency between fragments, so now make use of interfaces
				MainActivity ma = (MainActivity) getActivity();
				ma.switchToSecondFragment();*/
				
				// --------------------------------------
/*				FragmentManager manager = getFragmentManager();
				
				FragmentTransaction trans = manager.beginTransaction();
				
				SecondFragment sf = new SecondFragment();
				
				// remove the first fragment from the activity
				trans.remove(FirstFragment.this); // only 'this' is referring to the onClickListener which would be wrong
				
				// add the second fragment
				trans.add(R.id.mainContainer,sf, "SF");
				
				// If we directly write commit method after the above line, on pressing the back button it would go
				// ....to android apps sections instead of first fragment
				
				// Fragment Manager also manages the Fragment Manager Back Stack()
				
				trans.addToBackStack("Switch"); // I can use the manager object and pop the fragments with the String names
				trans.commit();
*/
			}
		});
	
		//return super.onCreateView(inflater, container, savedInstanceState);
		return mainView;
	}
	
}
