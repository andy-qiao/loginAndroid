package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		
		Intent data = this.getIntent(); // get the intent that run this activity and store it in variable data

		// Get The variable That Come from  MainActivity
		String usern = data.getExtras().getString("username");
		String passw = data.getExtras().getString("password");
	//	int s = data.getExtras().getInt("a");
	//	String g= data.getExtras().getString("h");
		
		
		
			


		EditText mul = (EditText) findViewById(R.id.editTextMul);




		mul.append("username: "+usern+"  // Password: "+passw );

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
