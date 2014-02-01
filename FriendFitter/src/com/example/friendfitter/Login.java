package com.example.friendfitter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements View.OnClickListener {

	// YO GUYZ. These two are what they are,
	Button login, register;
	EditText group, password, name;
	String groupS, passwordS, nameS;

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
			// The variables will contain the string corresponding to input fields
			groupS = group.getText().toString();
			passwordS = password.getText().toString();
			nameS = name.getText().toString();
			// So guys, this login right now just leads straight to the main menu
			// we can change to to only go to the enu conditionally
			try {
				Class mainMenu = Class.forName("com.example.friendfitter.Menu");
				Intent menuIntent = new Intent(Login.this, mainMenu);
				startActivity(menuIntent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
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
}
