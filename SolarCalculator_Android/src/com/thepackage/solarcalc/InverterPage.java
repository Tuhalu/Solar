package com.thepackage.solarcalc;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InverterPage extends Activity {
	
	private String cost;
	private String efficiency;
	private String loss;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inverter);	
		
		Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent myIntent = new Intent(view.getContext(), PanelInfoPage.class);
        		startActivityForResult(myIntent, 0);
        	}
        });
        
        Button continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		cost = ((EditText) findViewById(R.id.costEdit)).getText().toString();
        		float eff = Integer.parseInt(((EditText) findViewById(R.id.efficiencyEdit)).getText().toString());
        		efficiency = Float.toString(eff);
        		float los = Integer.parseInt(((EditText) findViewById(R.id.lossEdit)).getText().toString());
        		loss = Float.toString(los);
        		String response = null;
        		
        		HttpFactory newPost = new HttpFactory();
    			List<NameValuePair> values = new ArrayList<NameValuePair>();  
        		values.add(new BasicNameValuePair("cost", cost));
        		values.add(new BasicNameValuePair("efficiency", efficiency));
        		values.add(new BasicNameValuePair("efficiencyloss", loss));
        		newPost.allowRedirects(false);
        		try {        			
					newPost.getStringFromUrl("inverterinput", values);
				} catch (Exception e) {
					//Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}
        		
        		response = newPost.getResponseCode();
        		
        		if (response.charAt(0) == '4' || response.charAt(0) == '5' || response.charAt(0) == '(') {
        			Toast.makeText(getApplicationContext(), "Error "+response+" Occured. Please Try Again.", Toast.LENGTH_LONG).show();
        		} else { 
        			Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
        			Intent myIntent = new Intent(view.getContext(), ResultsPage.class);
	        		startActivityForResult(myIntent, 0);
        		}
        	}
        });
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit, menu);
        MenuItem item = menu.getItem(0);
        item.setOnMenuItemClickListener(new OnMenuItemClickListener(){
			public boolean onMenuItemClick(MenuItem item) {
				Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
				myIntent.putExtra("finish", true);
		        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivityForResult(myIntent, 0);
		        finish();
				return false;
			}
        });
        return true;
    }
	
}
