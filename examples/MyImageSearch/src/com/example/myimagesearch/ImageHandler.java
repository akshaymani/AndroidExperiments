package com.example.myimagesearch;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageHandler extends Handler {

	ImageView imageView;
	ProgressBar progressBar;
	int position;
	
	public ImageHandler(ProgressBar pb,ImageView imageView, int position) {
		super();
		this.imageView = imageView;
		this.position = position;
		this.progressBar = pb;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		int imagePos = (Integer) imageView.getTag();
		
		if (position == imagePos) {
			Bundle b = msg.getData();
			Bitmap image = b.getParcelable("IMAGE");
			imageView.setImageBitmap(image);
			progressBar.setVisibility(View.INVISIBLE);
		}
	}
	
	
	
	
}
