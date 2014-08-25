package com.paypal.cal.report;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.zip.GZIPInputStream;

public class Decompress {

	public static void decompress(String basedir, String compressed, String output) throws IOException, Exception{
		/*File file = new File(basedir + compressed);
		GZIPInputStream gzip = new GZIPInputStream(new FileInputStream(file));
		FileOutputStream fos = new FileOutputStream(basedir + output);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = gzip.read(buffer)) > 0) {
			fos.write(buffer, 0, length);
		}
		fos.close();
		gzip.close();*/
		
		File file = new File(basedir + compressed);
		InputStream fileStream = new FileInputStream(file);
		InputStream gzipStream = new GZIPInputStream(fileStream);
		Reader decoder = new InputStreamReader(gzipStream, "UTF-8");
		BufferedReader br = new BufferedReader(decoder);
		
		File outfile = new File(basedir + output);
		
	}
	
}
