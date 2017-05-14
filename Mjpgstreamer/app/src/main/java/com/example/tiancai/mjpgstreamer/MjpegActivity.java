package com.example.tiancai.mjpgstreamer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class MjpegActivity extends Activity{

	private EditText edIP;
    private Button openbutton;
    public static String CameraIp;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        edIP=(EditText)findViewById(R.id.editIP);
        openbutton=(Button)findViewById(R.id.openbtn);
        
        CameraIp =edIP.getText().toString();
        
        openbutton.setOnClickListener(new Button.OnClickListener()
        {
              public void onClick(View v) {
            	  Intent intent = new Intent(getApplication(), surface.class);
            	  Bundle bundle = new Bundle();
            	  bundle.putString("CameraIp", CameraIp);
            	  intent.putExtras(bundle);
            	  startActivity(intent);
            }
        });
    }
}