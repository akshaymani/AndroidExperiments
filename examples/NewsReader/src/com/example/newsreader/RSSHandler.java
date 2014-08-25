package com.example.newsreader;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class RSSHandler extends DefaultHandler {
	
	ArrayList<NewsItem> items;
	NewsItem item;
	public final String TAG = "RSSHandler";
	boolean insideItem;
	StringBuffer buffer;

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		Log.i(TAG, "start Document");
		items = new ArrayList<NewsItem>();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		Log.i(TAG, "endDocument");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (localName.equalsIgnoreCase("item")) {
			insideItem = true;
			item = new NewsItem();
		}
		
		if (insideItem && localName.equalsIgnoreCase("title")) {
			buffer = new StringBuffer();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if (localName.equalsIgnoreCase("item")) {
			insideItem = false;
			items.add(item);
		}
		
		if (insideItem && localName.equalsIgnoreCase("title")) {
			item.title = buffer.toString();
			Log.i(TAG, item.title);
			buffer = null;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		if (null != buffer) {
			buffer.append(ch,start,length);
		}
	}
	
}
