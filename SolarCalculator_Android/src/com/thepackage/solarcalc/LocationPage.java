package com.thepackage.solarcalc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LocationPage extends Activity {
	
	private String longitude;
	private String latitude;
	private String usage;
	private String hourly;
	private String sunlight;
	
	private LocationManager locManager;
	private Boolean useLoc = false;
	private Location location;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		
		Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent myIntent = new Intent(view.getContext(), MainActivity.class);
        		startActivityForResult(myIntent, 0);
        	}
        });
        
        Button continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		if (!useLoc) {
		    		longitude = ((EditText) findViewById(R.id.longEdit)).getText().toString();
		    		latitude = ((EditText) findViewById(R.id.latEdit)).getText().toString();
        		}
        		usage = ((EditText) findViewById(R.id.dailyusageEdit)).getText().toString();
        		hourly = ((EditText) findViewById(R.id.dayusageEdit)).getText().toString();
        		sunlight = ((EditText) findViewById(R.id.dailysunEdit)).getText().toString();
        		String response = null;
        		
        		HttpFactory newPost = new HttpFactory();
        		List<NameValuePair> values = new ArrayList<NameValuePair>();  
        		values.add(new BasicNameValuePair("longitude", longitude));
        		values.add(new BasicNameValuePair("latitude", latitude));
        		values.add(new BasicNameValuePair("usage", usage));
        		values.add(new BasicNameValuePair("hourly", hourly));
        		values.add(new BasicNameValuePair("sunlight", sunlight));
        		try {        			
					newPost.getStringFromUrl("locationinput", values);
					response = newPost.getResponseCode();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}

        		if (response.charAt(0) == '4' || response.charAt(0) == '5' || response.charAt(0) == '(') {
        			Toast.makeText(getApplicationContext(), "Error "+response+" Occured. Please Try Again.", Toast.LENGTH_LONG).show();
        		} else {
        			Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
	        		Intent myIntent = new Intent(view.getContext(), PanelInfoPage.class);
	        		startActivityForResult(myIntent, 0);
        		}
        	}
        });
        
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		 // Define a listener that responds to location updates
		 LocationListener locListener = new LocationListener() {
		     public void onLocationChanged(Location loc) {
		       // Called when a new location is found by the network location provider.
		    	 if (location == null) {
		    		 location = loc;
		    	 }
		     }
		
		     public void onStatusChanged(String provider, int status, Bundle extras) {}
		
		     public void onProviderEnabled(String provider) {}
		
		     public void onProviderDisabled(String provider) {}
		   };
		
		 // Register the listener with the Location Manager to receive location updates
		 locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
        
        CheckBox locCheckBox = (CheckBox) findViewById(R.id.locCheck);
        locCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        	public void onCheckedChanged(CompoundButton button, boolean checked) {
				LinearLayout lin = (LinearLayout) findViewById(R.id.longLatLay);
				EditText curloc = (EditText) findViewById(R.id.curlocEdit);
				if (checked) {					
				    if (location != null) {
					    Geocoder geocoder = new Geocoder(getApplicationContext());
					    List<Address> addr;
						try {
							addr = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
							String placeName = addr.get(0).getLocality();
		                    String country = addr.get(0).getCountryName();
		                    String road = addr.get(0).getThoroughfare();
		                    String locInfo = String.format("%s, %s, %s", road,placeName,country);
						    
		                    latitude = Location.convert(location.getLatitude(), Location.FORMAT_DEGREES);
		                    longitude = Location.convert(location.getLongitude(), Location.FORMAT_DEGREES);
						    curloc.setText(locInfo);
						    useLoc = true;
						} catch (IOException e) {
							Toast.makeText(getApplicationContext(), "Problem With Location", Toast.LENGTH_SHORT).show();
							curloc.setText("Location Not Available");
						}						
				    } else {
				    	curloc.setText("Location Not Available");
				    }
					lin.setVisibility(View.GONE);
					curloc.setVisibility(View.VISIBLE);
				} else {
					useLoc = false;
					lin.setVisibility(View.VISIBLE);
					curloc.setVisibility(View.GONE);
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
