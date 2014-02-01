package com.example.friendfitter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Login extends Activity implements View.OnClickListener{
	
	
	Button login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instantiateButtons();
		setContentView(R.layout.login);
	}

	private void instantiateButtons() {
		// TODO Auto-generated method stub
		login = (Button) findViewById(R.id.loginbt);
		login.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case (R.id.loginbt) {
			
		}
		}
		
	}

}
