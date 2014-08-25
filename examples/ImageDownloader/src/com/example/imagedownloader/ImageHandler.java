package com.example.imagedownloader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ImageHandler extends Handler {
	
	ImageView imageView;
	
	public ImageHandler(ImageView iv) {
		this.imageView = iv;
	}

	@Override
	public void handleMessage(Message msg) {
		
		super.handleMessage(msg);
		Bitmap image = msg.getData().getParcelable("IMAGE");
		imageView.setImageBitmap(image);
	}
	
	

}
