package com.thepackage.solarcalc;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsPage extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results);
		
		TextView roiTime = (TextView) findViewById(R.id.roiTimeText);
		TextView dailyOut = (TextView) findViewById(R.id.dailyOutText);
		TextView cost = (TextView) findViewById(R.id.costText);
		TextView monthSave = (TextView) findViewById(R.id.monthSaveText);
		TextView lifeSave = (TextView) findViewById(R.id.lifeSaveText);
		
		HttpFactory newPost = new HttpFactory();
		String content;
		String response;
		List<NameValuePair> values = new ArrayList<NameValuePair>();
		newPost.allowRedirects(true);
		try {
			content = newPost.getStringResponse("calc_results.jsp");
			List<NameValuePair> results = htmlParse(content);
			roiTime.setText(results.get(0).getValue());
			dailyOut.setText(results.get(1).getValue());
			cost.setText(results.get(2).getValue());
			monthSave.setText(results.get(3).getValue());
			lifeSave.setText(results.get(4).getValue());
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}
		
		response = newPost.getResponseCode();
		
		if (response.charAt(0) == '4' || response.charAt(0) == '5' || response.charAt(0) == '(') {
			Toast.makeText(getApplicationContext(), "Error "+response+" Occured. Please Try Again.", Toast.LENGTH_LONG).show();
		}
		
		Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent myIntent = new Intent(view.getContext(), MainActivity.class);
        		myIntent.putExtra("finish", true);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(myIntent, 0);
                finish();
        	}
        });
	}
	
	private List<NameValuePair> htmlParse(String content) {
		List<NameValuePair> results = new ArrayList<NameValuePair>();
		String workingcopy;
		int start = 0;
		int end = content.length();
		String[] split;
		start = content.indexOf("<h1>Results</h1>")+16;
		end = content.indexOf("<form");
		workingcopy = content.substring(start, end);
		split = workingcopy.split("</span>\\s");
		for (int i = 0; i < split.length; i++) {
			split[i] = split[i].trim();
			split[i] = split[i].replaceAll("</br>", "");
			split[i] = split[i].replaceAll("\\s<", "");
			split[i] = split[i].replaceAll("span class=\"resulttitle\">", "");
			split[i] = split[i].replaceAll("span class=\"result\">", "");
			split[i] = split[i].replaceAll("<", "");
		}
		for (int i = 0; i < split.length - 2; i += 2) {
			results.add(new BasicNameValuePair(split[i], split[i+1]));
		}
		return results;
	}

	@Override
    public void onBackPressed() {
		moveTaskToBack(true);
		Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
		myIntent.putExtra("finish", true);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(myIntent, 0);
        finish();
    }
}
