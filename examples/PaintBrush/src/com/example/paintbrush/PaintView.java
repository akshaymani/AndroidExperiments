package com.example.paintbrush;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintView extends View {

	Paint paint;
	ArrayList<Point> points = new ArrayList<Point>();
	
	Bitmap offScreenBitmap;
	Canvas offScreenCanvas;
	
	// If we use this then movement has to ve handled in code, won't show in the custom views
	public PaintView(Context context) {
		super(context);
	}

	public PaintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(3);
		
		offScreenBitmap = Bitmap.createBitmap(1000,1000,Config.ARGB_8888);
		offScreenCanvas = new Canvas(offScreenBitmap);
	}
	
	// In the game we have to keep two buffers - main thread is running graphics resolution
	// Doing animation on canvas
	// Show the buffered view once it has been completed rendered

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/*for (Point p : points) {
			canvas.drawCircle(p.x, p.y, 30, paint);
			// screen is not refreshed, so explicity we have to tell to redraw as the finger is moving
		}*/
		
		for (int i = 0; i < points.size() - 1; i++) {
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			//canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
			offScreenCanvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
		}
		
		// display the offscreen bitmap to the user
		canvas.drawBitmap(offScreenBitmap, 0, 0, paint);
		
		// Now we can save the offScreenBitmap to our local system using File I/O operations
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN ||
				event.getAction() == MotionEvent.ACTION_MOVE) {
			Point p = new Point();
			p.x = (int) event.getX();
			p.y = (int) event.getY();
			
			points.add(p);
			
			// redraw the screen
			invalidate();
			return true; // must return true to handle on touch events
		}
		return super.onTouchEvent(event);
	}
	
	

}
