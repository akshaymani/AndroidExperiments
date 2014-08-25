package com.example.countriesfrag;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WikiFragment extends Fragment {

	String countryName;
	
	WebView webView;
	
	public WikiFragment() {
		
	}
	
	// We will need to add permission for the internet to our application, Intent by default has the permission that is why in earlier case we didn't need it
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.fragment_wiki, container, false);
		webView = (WebView) mainView.findViewById(R.id.webView1);
		
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				
				view.loadUrl(url);
				
				return true; 
			}
			
		});
		
		String url = "http://en.wikipedia.org/wiki/"+countryName;
		webView.loadUrl(url);
		
		return mainView;
	}
	
}
