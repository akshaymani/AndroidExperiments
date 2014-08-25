package com.example.contentproviderexample;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void browser(View view) {
		// uri of bookmarks content provider
		Uri uri = Uri.parse("content://browser/bookmarks");
		
		// or
		//Uri uri1 = Browser.BOOKMARKS_URI;
		
		// columns to read from content provider
		String[] cols = { Browser.BookmarkColumns.DATE,
						Browser.BookmarkColumns.TITLE,
						Browser.BookmarkColumns.URL};
		
		// get access to the content resolver
		ContentResolver resolver = getContentResolver();
		
		// query the content provider
		Cursor c = resolver.query(uri, cols, null, null, null);
		
		if (c.getCount() > 0) {
			c.moveToFirst();
			int urlIndex = c.getColumnIndex(Browser.BookmarkColumns.URL);
			
			do {
				String url = c.getString(urlIndex);
				Log.i("MainActivity", url);
				c.moveToNext();
			} while (!c.isAfterLast());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
