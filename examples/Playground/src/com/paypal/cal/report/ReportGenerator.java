package com.paypal.cal.report;

import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReportGenerator {

	public static void main (String[] args) throws IOException, ParseException, Exception {

		String basedir = "";
		String compressedJson = "Global-URL-Report.json.gz";
		String startdate = "06/07/2014";
		int noofdays = 1;
		String component_name = "accountmanagementserv";
		String environment = "LIVE";
		ArrayList<String> url_list = urlgenerate(startdate,noofdays,component_name,environment);
		//String url = "http://cal-vip-a.slc.paypal.com/24x7/2014/07-July/17/report/accountmanagementserv/json/Global-URL-Report.json.gz";
		String outputJson = "output.json";

		try {		

			HashMap<String,Long> url_totalhits = new HashMap<String,Long>();
			
			for (int k = 0; k < url_list.size(); k++) {
				Wget.wGet(compressedJson, url_list.get(k));

				Decompress.decompress(basedir, compressedJson, outputJson);

				JSONParser parser = new JSONParser();
				Object obj = parser.parse(new FileReader(basedir + outputJson));

				ContainerFactory containerFactory = new ContainerFactory(){
					public List creatArrayContainer() {
						return new LinkedList();
					}

					public Map createObjectContainer() {
						return new LinkedHashMap();
					}

				};
				Map json = (Map)parser.parse(new FileReader(basedir + outputJson), containerFactory);

				//System.out.println(JSONValue.toJSONString(json));
				String sjson = JSONValue.toJSONString(json);

				String[] urls = sjson.split(",\"url\":\"");

				String[] total_count = sjson.split(",\"url\"");

				
				for (int i = 1; i < urls.length; i++) {
					
					//System.out.println("URL: " + urls[i].split("\"}")[0] + " and Total Count: " + total_count[i-1].split("\"total_count\":")[1]);
					long newcount = 0;
					if (null != url_totalhits.get(urls[i].split("\"}")[0])) {
						newcount = url_totalhits.get(urls[i].split("\"}")[0]) + Long.valueOf(total_count[i-1].split("\"total_count\":")[1]);
					}
					else {
						newcount = Long.valueOf(total_count[i-1].split("\"total_count\":")[1]);
					}
					//System.out.println(newcount);
					url_totalhits.put(urls[i].split("\"}")[0], newcount);
				}
			}
			printMap(url_totalhits);

		}
		catch(ParseException pe){
			System.out.println(pe);
		}

	}

	private static ArrayList<String> urlgenerate(String startdate, int noofdays, String component_name, String environment) {
		// TODO Auto-generated method stub

		int currentday = DateUtility.getDayFromDate(startdate);
		int currentmonth = DateUtility.getMonthFromDate(startdate);
		int currentyear = DateUtility.getYearFromDate(startdate);
		ArrayList<String> urls = new ArrayList<String>();
		for (int i = 0 ; i < noofdays; i++) {
			String newday = DateUtility.generateIncrementalDay(currentday,currentmonth,currentyear);
			String newmonth = DateUtility.generateIncrementalMonth(currentday,currentmonth,currentyear);
			String newyear = DateUtility.generateIncrementalYear(currentday,currentmonth,currentyear);
			currentday = Integer.valueOf(newday);
			currentmonth = Integer.valueOf(newmonth);
			currentyear = Integer.valueOf(newyear);
			newday = appendzero(newday);
			newmonth = appendzero(newmonth)+"-"+DateUtility.getMonth(Integer.valueOf(newmonth));
			// "http://cal-vip-a.slc.paypal.com/24x7/2014/07-July/17/report/accountmanagementserv/json/Global-URL-Report.json.gz"
			String url = "http://cal-vip-a.slc.paypal.com/24x7/"+newyear+"/"+newmonth+"/"+newday+"/report/"+component_name+"/json/Global-URL-Report.json.gz";
			System.out.println(url);
			urls.add(url);
		}
		return urls;
	}

	private static String appendzero(String val) {
		int v = Integer.valueOf(val);
		if (v < 10) {
			return "0"+val;
		}
		return val;
	}

	public static void printMap(Map mp) {
		Iterator it = mp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			System.out.println("URL: " + pairs.getKey()+ " and Total Count: " + pairs.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}
	}

}
