package com.example.newsreader;

import java.util.ArrayList;

public interface ListNewsFragmentListener {

	public void refreshView(ArrayList<NewsItem> items);
	
}
