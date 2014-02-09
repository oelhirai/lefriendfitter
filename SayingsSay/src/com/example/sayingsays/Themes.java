package com.example.sayingsays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Themes extends Activity implements View.OnClickListener {
	ImageButton nature, city, movies, dog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecttheme);
		instantiateView();
	}

	public void instantiateView() {
		nature = (ImageButton) findViewById(R.id.natureThemeBt);
		city = (ImageButton) findViewById(R.id.cityThemeBt);
		movies = (ImageButton) findViewById(R.id.movieThemeBt);
		dog = (ImageButton) findViewById(R.id.dogThemeBt);
		nature.setOnClickListener(this);
		city.setOnClickListener(this);
		movies.setOnClickListener(this);
		dog.setOnClickListener(this);
	}

	// Intent i = new Intent(this, FindAndroidActivity.class);
	// i.putExtra("KEY",YourData);

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.natureThemeBt:
			try {
				Class themeSelect = Class.forName("com.example.sayingsays.NatureTheme");
				Intent themeIntent = new Intent(Themes.this, themeSelect);
				startActivity(themeIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case R.id.cityThemeBt:
			try {
				Class themeSelect = Class.forName("com.example.sayingsays.CityTheme");
				Intent themeIntent = new Intent(Themes.this, themeSelect);
				startActivity(themeIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case R.id.movieThemeBt:
			try {
				Class themeSelect = Class.forName("com.example.sayingsays.MovieTheme");
				Intent themeIntent = new Intent(Themes.this, themeSelect);
				startActivity(themeIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		case R.id.dogThemeBt:
			try {
				Class themeSelect = Class.forName("com.example.sayingsays.DogTheme");
				Intent themeIntent = new Intent(Themes.this, themeSelect);
				startActivity(themeIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
		}
	}
}

/* 			String bread = etSend.getText().toString();
Bundle basket = new Bundle();
basket.putString("key", bread);
Intent a = new Intent(Data.this, OpenedClass.class);
a.putExtras(basket);
startActivity(a);
*/