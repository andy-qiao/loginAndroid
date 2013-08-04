package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.message.BasicNameValuePair;

//import com.example.test.MainActivity.LoadAllContact;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;

import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

public class MainActivity extends Activity {
	
	
		Button login ;
        EditText user,pass;
        
        //************ New  
        
     // Progress Dialog
    	private ProgressDialog pDialog;

    	// Creating JSON Parser object
    	JSONParser jParser = new JSONParser();
    	
    	
    	private static String url_login = "http://127.0.0.1/cont/login.php"; //########## Change it to URL

    	// JSON Node names
    	private static final String TAG_SUCCESS = "success";
    	private static final String TAG_user = "username";
    	private static final String TAG_pass = "password";
    	

	
	

	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 login = (Button) findViewById(R.id.buttonLog);
         user = (EditText) findViewById(R.id.editTextLOGIN);
         pass = (EditText) findViewById(R.id.editTextPass);
		 
      // ######################## For the Network #################
         
         StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
         StrictMode.setThreadPolicy(policy);
         
      // ######################## END  #################  
         
         

       login.setOnClickListener(new OnClickListener(){

		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
	     
		//	int d = 3;
		//	String k= "FFF";
			
			//user.setText(username+"//"+passr);
		
			
	//	Intent i = new Intent(MainActivity.this,ResultActivity.class);
    //    i.putExtra("username", username.toString());
    //    i.putExtra("password", passr.toString());
       // i.putExtra("a",d);
       // i.putExtra("h",k);
		
      //  startActivity(i);
			
		
			
			
				     
				 
				
			
			
			// $$$$$$$$$$$ The New $$$$$$$$$$
			
			
			new LoadAllContact().execute();
			
			
			// ###### Dialog #########
			
			/*
			 
            AlertDialog alertDialog1 = new AlertDialog.Builder(
                    MainActivity.this).create();
 
            alertDialog1.setTitle("Alert Dialog");
 
            alertDialog1.setMessage("Its Work Until Here");
 
           
 
            alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
 
                public void onClick(DialogInterface dialog, int which) {
                    
                    Toast.makeText(getApplicationContext(),
                            "You clicked on OK", Toast.LENGTH_SHORT).show();
                }
            });
 
            alertDialog1.show();
			
			*/
			
			//###### End Dialog #########
			
			
			
        
		}    	// close onClick allContact   
		 
    	   
       }); // close onClick

     
     		
	} // close onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	class LoadAllContact extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Loading Contact. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		} // close onPreEx

		/**
		 * getting All contact from url
		 * */
		protected String doInBackground(String... params) {

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					// Check for success tag
					int success;
					String username = user.getText().toString();
				    String passr = pass.getText().toString();
					try {
						// Building Parameters
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair(TAG_user, username));
						params.add(new BasicNameValuePair(TAG_pass, passr));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jParser.getJSONFromUrl(url_login, params);

						// check your log for json response
						Log.d("Single Contact Details", json.toString());
						
						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							
							Intent i = new Intent(MainActivity.this,ResultActivity.class);
						    i.putExtra("username", username);
						    i.putExtra("password", passr);
						       // i.putExtra("a",d);
						       // i.putExtra("h",k);
								
						    startActivity(i);

						} // close If
						else{
							// product with pid not found
							
							Intent i = new Intent(MainActivity.this,ResultActivity.class);
						  //  i.putExtra("username", username);
						  //  i.putExtra("password", passr);
						       // i.putExtra("a",d);
						       // i.putExtra("h",k);
								
						    startActivity(i);
							
							
						} // close Else
					} // close Try
					catch (JSONException e) {
						e.printStackTrace();
					} // close Catch
				
				}// closr Run
			}); // close Runable

			return null;
		} // close DoInBack
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all contact
			pDialog.dismiss();
		}
	}//	
	
	
	
}

