package com.example.friendfitter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends Activity implements View.OnClickListener {

	// YO GUYZ. These two are what they are,
	Button login, register;
	EditText group, password, name;
	String groupS, passwordS, nameS;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	// php login script location:

	// localhost :
	// testing on your device
	// put your local ip instead, on windows, run CMD > ipconfig
	// or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String LOGIN_URL =
	// "http://xxx.xxx.x.x:1234/webservice/login.php";

	// testing on Emulator:
	private static final String LOGIN_URL = "http://128.237.201.225/friend_fitter/login.php";

	// JSON element ids from repsonse of php script:
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		instantiateVars();
	}

	private void instantiateVars() {
		// TODO Auto-generated method stub
		login = (Button) findViewById(R.id.loginbt);
		register = (Button) findViewById(R.id.registerbt);
		group = (EditText) findViewById(R.id.groupNameet);
		password = (EditText) findViewById(R.id.passwordet);
		name = (EditText) findViewById(R.id.nameet);
		login.setOnClickListener(this);
	}

	// this is where we may login, and later the password, name, and group
	// should be registered here
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case (R.id.loginbt):
			// The variables will contain the string corresponding to input
			// fields
			//groupS = group.getText().toString();
			//passwordS = password.getText().toString();
			//nameS = name.getText().toString();
			new AttemptLogin().execute();
			/*try {
				Class mainMenu = Class.forName("com.example.friendfitter.Menu");
				Intent menuIntent = new Intent(Login.this, mainMenu);
				startActivity(menuIntent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;*/
		case (R.id.registerbt):
			break;
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	class AttemptLogin extends AsyncTask<String, String, String> {
		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Login.this);
			pDialog.setMessage("Attempting login...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			// CHANGED THIS TO MATCH OUR IMPLEMENTATION
			String groupName = "a"; //group.getText().toString();
			String passwordName = "tartanhacks";//password.getText().toString();
			String Name = "friend_fitter"; //name.getText().toString();

			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("groupname", groupName));
				params.add(new BasicNameValuePair("username", Name));
				params.add(new BasicNameValuePair("password", passwordName));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
						params);
				// check your log for json response
				Log.d("Login attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("Login Successful!", json.toString());

					// CHANGED FOR OUR IMPLEMENTATION
					Class mainMenu = Class
							.forName("com.example.friendfitter.Menu");
					Intent menuIntent = new Intent(Login.this, mainMenu);
					startActivity(menuIntent);
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("Login Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (file_url != null) {
				Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
			}
		}
	}

}
