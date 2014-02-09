package com.example.sayingsays;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MovieTheme extends Activity implements View.OnClickListener {
	int curIndex = 0;
	Bitmap bmp;
	int[] imagesToShow = { R.drawable.m_1, R.drawable.m_2, R.drawable.m_3,
						   R.drawable.m_4 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.previewtheme);
		final ImageView displayPics = (ImageView) findViewById(R.id.themeIv);
		final Button setTheme = (Button) findViewById(R.id.setThemeBt);
		setTheme.setOnClickListener(this);
		
		// !!!NOTE: MUST MAKE SURE ARRAY CAN HOLD ALL PICTURES!!!
		int[] imagesToShow = { R.drawable.m_1, R.drawable.m_2, R.drawable.m_3,
				               R.drawable.m_4 };
		animate(displayPics, imagesToShow, 0, true);
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.setThemeBt:
			try {
				InputStream is = getResources().openRawResource(imagesToShow[curIndex]);
				bmp = BitmapFactory.decodeStream(is);
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	private void animate(final ImageView imageView, final int images[],
			final int imageIndex, final boolean forever) {

		int fadeInDuration = 400; // Configure time values here
		int timeBetween = 2400;
		int fadeOutDuration = 800;

		imageView.setVisibility(View.INVISIBLE);
		imageView.setImageResource(images[imageIndex]);
		curIndex = imageIndex; // Track which image we are on

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator()); // add this
		fadeIn.setDuration(fadeInDuration);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator()); // and this
		fadeOut.setStartOffset(fadeInDuration + timeBetween);
		fadeOut.setDuration(fadeOutDuration);

		AnimationSet animation = new AnimationSet(false); // change to false
		animation.addAnimation(fadeIn);
		animation.addAnimation(fadeOut);
		animation.setRepeatCount(1);
		imageView.setAnimation(animation);

		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				if (images.length - 1 > imageIndex) {
					animate(imageView, images, imageIndex + 1, forever);

				} else {
					if (forever == true) {
						animate(imageView, images, 0, forever);
					}
				}
			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}
		});
	}
}