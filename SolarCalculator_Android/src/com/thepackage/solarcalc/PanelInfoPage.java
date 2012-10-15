package com.thepackage.solarcalc;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class PanelInfoPage extends Activity implements SensorEventListener{
	
	private String paneloutput;
	private String cost;
	private String efficiencyloss;
	private String facing;
	private SensorManager sensorManager;
    private EditText dirEdit;
    private Boolean useSensor = false;
    private int myAzimuth = 0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.panelinfo);	
		
		dirEdit = (EditText) findViewById(R.id.dirEdit);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		CheckBox dirCheckBox = (CheckBox) findViewById(R.id.dirCheck);
        dirCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        	public void onCheckedChanged(CompoundButton button, boolean checked) {
				if (checked) {
					button.setText("Stop");
					useSensor = true;
				} else {
					button.setText("Use Compass");
					useSensor = false;
				}
        	}
		});
		
		Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent myIntent = new Intent(view.getContext(), LocationPage.class);
        		startActivityForResult(myIntent, 0);
        	}
        });
        
        Button continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		paneloutput = ((EditText) findViewById(R.id.sizeEdit)).getText().toString();
        		cost = ((EditText) findViewById(R.id.costEdit)).getText().toString();
        		float efflos = Integer.parseInt(((EditText) findViewById(R.id.lossEdit)).getText().toString());
        		efficiencyloss = Float.toString(efflos/100);
        		facing = dirEdit.getText().toString();
        		String response = null;
        		
        		HttpFactory newPost = new HttpFactory();
    			List<NameValuePair> values = new ArrayList<NameValuePair>();  
        		values.add(new BasicNameValuePair("paneloutput", paneloutput));
        		values.add(new BasicNameValuePair("cost", cost));
        		values.add(new BasicNameValuePair("efficiencyloss", efficiencyloss));
        		values.add(new BasicNameValuePair("angle", facing));
        		if (((CheckBox) findViewById(R.id.bracingCheck)).isChecked()) {
        			values.add(new BasicNameValuePair("bracing", "yes"));
        		}
        		try {        			
					newPost.getStringFromUrl("panelsinput", values);
				} catch (Exception e) {
					//Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
				}

				response = newPost.getResponseCode();

        		if (response.charAt(0) == '4' || response.charAt(0) == '5' || response.charAt(0) == '(') {
        			Toast.makeText(getApplicationContext(), "Error "+response+" Occured. Please Try Again.", Toast.LENGTH_LONG).show();
        		} else {
        			Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
	        		Intent myIntent = new Intent(view.getContext(), InverterPage.class);
	        		startActivityForResult(myIntent, 0);
        		}
        	}
        });
	}
	
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) {
		if (useSensor) {
			myAzimuth = (int)Math.round(event.values[0]);
			dirEdit.setText(myAzimuth+"");
		}
	}
	
	/** Register for the updates when Activity is in foreground */
	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, 
		sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
		SensorManager.SENSOR_DELAY_NORMAL);
	}

	/** Stop the updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
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
