package com.example.tiancai.mjpgstreamer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class surface extends Activity implements Runnable{
	private SurfaceHolder holder;    
    private Thread mythread;    
    private Canvas canvas;
    URL videoUrl;
    private String url;
    private int w;
    private int h;
    HttpURLConnection conn;
    Bitmap bmp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.surface);
		
		url = getIntent().getExtras().getString("CameraIp");
		
		w = getWindowManager().getDefaultDisplay().getWidth();
		h = getWindowManager().getDefaultDisplay().getHeight();
		
		SurfaceView surface = (SurfaceView)findViewById(R.id.surface);
		
        surface.setKeepScreenOn(true);
        mythread = new Thread(this);     
        holder = surface.getHolder();
        holder.addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				mythread.start();
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				// TODO Auto-generated method stub
				
			}
		});

	}
	private void draw() {
		// TODO Auto-generated method stub
		try {    
			InputStream inputstream = null;

//			url = "http://192.168.8.1:8083/?action=snapshot";
			videoUrl=new URL(url);    

			conn = (HttpURLConnection)videoUrl.openConnection();

			conn.setDoInput(true);

			conn.connect();

			inputstream = conn.getInputStream();

			bmp = BitmapFactory.decodeStream(inputstream); 
			canvas = holder.lockCanvas(); 
			canvas.drawColor(Color.WHITE);
			RectF rectf = new RectF(0, 0, w, h);
			canvas.drawBitmap(bmp, null, rectf, null);
			holder.unlockCanvasAndPost(canvas);

			conn.disconnect();
        } catch (Exception ex) {    
        } finally {      
        } 
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			draw();
		}
	}
	
}
