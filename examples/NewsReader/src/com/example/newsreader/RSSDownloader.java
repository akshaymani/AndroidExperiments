package com.example.newsreader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import android.os.AsyncTask;

// Not being used as of now
public class RSSDownloader extends AsyncTask<String, Void, Boolean> {

	RSSHandler rssHandler = new RSSHandler();
	
	// Constructor with pointer to the fragment
	public RSSDownloader(ArrayList<NewsItem> items) {
		
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		downloadAndParseXML(params[0]);
		return true;
	}
	
	public void downloadAndParseXML(String link) {
		try {
			URL url = new URL(link);
			URLConnection connection = url.openConnection();
			InputStream inStr = connection.getInputStream();
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			parser.parse(inStr, rssHandler);
			
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

}
