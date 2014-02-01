package com.example.friendfitter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity implements View.OnClickListener {

	Button takePicture;
	Button streamPhotos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.menu);
		instantiateVars();
		super.onCreate(savedInstanceState);
	}

	private void instantiateVars() {
		// TODO Auto-generated method stub
		takePicture = (Button) findViewById(R.id.takePhotobt);
		streamPhotos = (Button) findViewById(R.id.photoStreambt);
		takePicture.setOnClickListener(this);
		streamPhotos.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case (R.id.takePhotobt):
			break;

		case (R.id.photoStreambt):
			break;
		}
	}

}