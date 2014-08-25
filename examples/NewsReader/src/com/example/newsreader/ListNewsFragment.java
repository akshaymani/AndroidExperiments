package com.example.newsreader;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Intent;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListNewsFragment extends Fragment {

	Button refresh;
	ListView listview;
	RSSNewsAdapter adapter;
	ArrayList<NewsItem> items = new ArrayList<NewsItem>();
	
	public ListNewsFragmentListener listener;

	public ListNewsFragment() {
		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = 
				inflater.inflate(R.layout.fragment_listnews, container, false);
		
		listview = (ListView) mainView.findViewById(R.id.listView1);

		adapter = new RSSNewsAdapter(getActivity(), items);
		
		// attach the adapter to the list
		listview.setAdapter(adapter);
		
		refresh = (Button) mainView.findViewById(R.id.button1);
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		return mainView;
	}


	public void refreshView(ArrayList<NewsItem> updatedItems) {
		Log.i("ListNewsFragment", "updatedItems: " + updatedItems.size());
		Log.i("ListNewsFragment", "View Updated - Either Refresh Button inside the Fragment Clicked or New Updates received on local boradcast receiver");
		items.clear();
		items.addAll(updatedItems);
		//adapter.notifyDataSetChanged();	
	}
	
}
