package com.example.sayingsays;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Quote extends Activity implements View.OnClickListener {

	EditText quote;
	Button choosePic;
	String quoteS;
	RadioButton white, black;
	String p;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quote);
		instantiateVars();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case (R.id.choosePicBt):
			String quoteS = quote.getText().toString();
			i = new Intent(Quote.this, CreatePicture.class);
			i.putExtra("KEY", quoteS);
			i.putExtra("COLOR", p);
			startActivity(i);
			break;
		}

	}

	private void instantiateVars() {
		// TODO Auto-generated method stub
		quote = (EditText) findViewById(R.id.quoteTextEt);
		choosePic = (Button) findViewById(R.id.choosePicBt);
		choosePic.setOnClickListener(this);
		white = (RadioButton) findViewById(R.id.whiteRb);
		black = (RadioButton) findViewById(R.id.blackRb);
		p = "white";
	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch (view.getId()) {
		case R.id.whiteRb:
			if (checked) {
				p = "white";
			}
			break;
		case R.id.blackRb:
			if (checked)
				System.out.println("Hai!");
				p = "black";
			break;
		}
	}
}