package com.example.sayingsays;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class CreatePicture extends Activity implements View.OnClickListener {

	Button uploadFromPhn, camera;
	Intent leIntent;
	int galOrCam = 0; // 0 for camera, 1 for gallery
	TouchImageView iv;
	Bitmap bmp;
	String quote, color;
	Paint p = new Paint(); // for setting quote attributes and ridding
							// pixelation
	int textX, textY;
	Options options;
	final static int cameraData = 0; // this is

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.createpicture);
		setContentView(R.layout.createpicture);
		instantiateVars();
		// default picture to upload
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher); // make
																					// better
																					// default
																					// picture
		bmp = BitmapFactory.decodeStream(is);
		p.setStyle(Paint.Style.FILL);
		p.setAntiAlias(true);
		p.setFakeBoldText(true);
		p.setTextSize(90);
		Typeface type = Typeface.createFromAsset(getAssets(),
				"font/DecoNeue-Light.ttf");

		p.setTypeface(type);

		options = new BitmapFactory.Options();
		options.inScaled = false;

	}

	private void instantiateVars() {
		// TODO Auto-generated method stub
		uploadFromPhn = (Button) findViewById(R.id.uploadphnBt);
		camera = (Button) findViewById(R.id.cameraBt);
		iv = (TouchImageView) findViewById(R.id.photoIv);
		uploadFromPhn.setOnClickListener(this);
		camera.setOnClickListener(this);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			quote = extras.getString("KEY");
			color = extras.getString("COLOR");
		}
		textX = iv.getWidth();
		textY = iv.getHeight();
		// if (color.equals("black")){
		// p.setARGB(255, 0, 0, 0);
		// }
		// else {
		p.setARGB(255, 255, 255, 255);
		// }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case (R.id.uploadphnBt):
			leIntent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			galOrCam = 1;
			startActivityForResult(leIntent, cameraData);
			break;
		case (R.id.cameraBt):
			leIntent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			galOrCam = 0;
			startActivityForResult(leIntent, cameraData);
			break;
		case (R.id.wallpaperBt):
			BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
			Bitmap imgbitmap = drawable.getBitmap();
			try {
				getApplicationContext().setWallpaper(imgbitmap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// this method should be used whenever startActivityForResult is used
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (galOrCam == 1) { // gallery is accessed
				Uri targetUri = data.getData();
				// The matrix stuff is a temporary solution for glitch in
				// uploading from gallery
				// (image is uploaded as rotated)
				Matrix matrix = new Matrix();
				matrix.postRotate(0);
				try {
					Bitmap rotatedBitmap = BitmapFactory
							.decodeStream(getContentResolver().openInputStream(
									targetUri));
					bmp = Bitmap.createBitmap(rotatedBitmap, 0, 0,
							rotatedBitmap.getWidth(),
							rotatedBitmap.getHeight(), matrix, true);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else { // if camera is active
				Bundle extras = data.getExtras();
				bmp = (Bitmap) extras.get("data");
			}
			Bitmap tempBitmap = Bitmap.createBitmap(bmp.getWidth(),
					bmp.getHeight(), Bitmap.Config.RGB_565);
			Canvas tempCanvas = new Canvas(tempBitmap);
			// get screen width, height
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);

			p.setTextSize(tempBitmap.getWidth() / 15);
			tempCanvas.drawBitmap(bmp, 0, 0, null);
			tempCanvas.drawText(quote, tempBitmap.getWidth() / 14,
					tempBitmap.getHeight() / 10, p);
			iv.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));

		}
	}

}