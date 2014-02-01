package com.example.friendfitter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Login extends Activity implements View.OnClickListener {

	Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		instantiateVars();
	}

	private void instantiateVars() {
		// TODO Auto-generated method stub
		login = (Button) findViewById(R.id.loginbt);
		login.setOnClickListener(this);
	}

	//this is where we may login, and later the password, name, and group should be registered here
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case (R.id.loginbt):
			try {
				Class mainMenu = Class.forName("com.example.friendfitter.Menu");
				Intent menuIntent = new Intent(Login.this, mainMenu);
				startActivity(menuIntent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
