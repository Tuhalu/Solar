package com.thepackage.solarcalc;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button newButton = (Button) findViewById(R.id.newButton);
        newButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		
        		String response = null;
        		
        		HttpFactory newPost = new HttpFactory();
        		List<NameValuePair> values = new ArrayList<NameValuePair>();  
        		values.add(new BasicNameValuePair("SystemType", "new"));
        		newPost.allowRedirects(false);
        		try {
					newPost.getStringFromUrl("createsystem", values);
				} catch (Exception e) {
					//Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}
        		
				response = newPost.getResponseCode();
				newPost.getSessionID();

        		if (response.charAt(0) == '4' || response.charAt(0) == '5' || response.charAt(0) == '(') {
        			Toast.makeText(getApplicationContext(), "Error "+response+" Occured. Please Try Again.", Toast.LENGTH_SHORT).show();
        		} else {
        			Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
        			Intent myIntent = new Intent(view.getContext(), LocationPage.class);
        			startActivityForResult(myIntent, 0);
        		}
        	}
        });
    }
    
    @Override
    public void onBackPressed() {
    	finish();
        System.exit(0);
    }
}
