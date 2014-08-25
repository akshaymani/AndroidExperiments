package com.example.myimagesearch;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.util.Log;

public class FlickrGetter {
    public static final String TAG = "FlickrGetter";

    private static final String ENDPOINT = "https://api.flickr.com/services/rest/";
    private static final String API_KEY = "326fbc8a31c1254cb5f0125c8cc63b03";
    private static final String METHOD_GET_RECENT = "flickr.photos.getRecent";
    private static final String METHOD_GET_SEARCH = "flickr.photos.search";
    private static final String PARAM_EXTRAS = "extras";

    private static final String EXTRA_SMALL_URL = "url_s";

    private static final String XML_PHOTO = "photo";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }
    
    public ArrayList<FlickrItem> fetchItems() {
        ArrayList<FlickrItem> items = new ArrayList<FlickrItem>();
        
        try {
            String url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD_GET_SEARCH)
                    .appendQueryParameter("api_key", API_KEY)
                    .appendQueryParameter(PARAM_EXTRAS, EXTRA_SMALL_URL)
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("text", "India")
                    .build().toString();
            Log.i("FlickrFetchr", url);
            String jsonString = getUrl(url);
            String json = jsonString.substring(14, jsonString.toString().length()-1);
            Log.i(TAG, "Received json: " + json);
            
            JSONObject mainObject = new JSONObject(json);
            JSONObject photoObject = mainObject.getJSONObject("photos");
            JSONArray photos = photoObject.getJSONArray("photo");
            for(int i = 0; i < photos.length(); ++i) {
            		JSONObject obj = photos.getJSONObject(i);
            		FlickrItem item = new FlickrItem();
            		item.title = obj.getString("title");
            		item.id = obj.getString("id");
            		item.url = obj.getString("url_s");
            		if(item.url != null) {
                		items.add(item);
                		Log.i(TAG, item.toString());
                		
            		}


            }
 
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to Get items", ioe);
        } catch (JSONException e) {
			e.printStackTrace();
		}
        return items;
    }


}
