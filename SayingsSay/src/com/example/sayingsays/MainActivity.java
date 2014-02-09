package com.example.sayingsays;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	Button takePic;
	Button themes;
	Button rand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		instantiateView();
	}

	private void instantiateView() {
		// TODO Auto-generated method stub
		takePic = (Button) findViewById(R.id.uploadBt);
		themes = (Button) findViewById(R.id.themesBt);
		rand = (Button) findViewById(R.id.randomBt);
		takePic.setOnClickListener(this);
		themes.setOnClickListener(this);
		rand.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case (R.id.uploadBt):
			try {
				Class mainMenu = Class.forName("com.example.sayingsays.Quote");
				Intent menuIntent = new Intent(MainActivity.this, mainMenu);
				startActivity(menuIntent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case (R.id.themesBt):
			try {
				Class mainMenu = Class.forName("com.example.sayingsays.Themes");
				Intent menuIntent = new Intent(MainActivity.this, mainMenu);
				startActivity(menuIntent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case (R.id.randomBt):
			fortuneCookie();
		}
	}
	
	public void fortuneCookie() {
		Random generator = new Random();
		Context context = getApplicationContext();

		//CREATES QUOTES
		int arrayLength = 18;
		ArrayList<String> quotes = new ArrayList<String>(arrayLength);
		//1-5
		quotes.add("Conquer your fears or they will conquer you");
		quotes.add("Happiness is often a rebound from hard work");
		quotes.add("Do not follow where the path may lead. Go where there is no path...and leave a trail");
		quotes.add("Life is too short to waste on what bores you. Seek adventure with your own hands.");
		quotes.add("The best revenge is massive success");
		//6-10
		quotes.add("KEEP HOPE ALIVE");
		quotes.add("Everything has beauty, but not everyone can see");
		quotes.add("Change your thoughts, and you change the world");
		quotes.add("Change always starts from you");
		quotes.add("The big things may get you accepted, but it's the small things that get you loved.");
		//10-15
		quotes.add("The secret to success is confident, luck and hard work");
		quotes.add("Don't seek success, chase excellence and success will follow");
		quotes.add("Now is all we have, and dreams won't chase themselves");
		quotes.add("Simplicity is bliss");
		quotes.add("Relationships are what life is made of. Treasure them.");
		//16-17
		quotes.add("Once you leave this world, you lose everything but the memories you shared with those you love");
		quotes.add("Death is very likely the single best invention of Life. It is Life's change agent. It clears out the old to make way for the new.");

		//CREATES TOASTS
		int r = generator.nextInt(arrayLength);
		String text = quotes.get(r);
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		Toast.makeText(context, text, duration).show();
	}
}
