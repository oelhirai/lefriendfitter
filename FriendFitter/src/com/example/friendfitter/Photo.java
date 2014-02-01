package com.example.friendfitter;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Photo extends Activity implements View.OnClickListener {

	Button uploadFromPhn;
	Button pushPic;
	Button camera;
	Intent leIntent;
	int galOrCam = 0; // 0 for camera, 1 for gallery
	ImageView iv;
	Bitmap bmp;
	final static int cameraData = 0; // this is

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		instantiateVars();
		// dims the background
		// WindowManager.LayoutParams windowManager =
		// getWindow().getAttributes();
		// windowManager.dimAmount = 0.50f;
		// getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

		// default picture to upload
		InputStream is = getResources().openRawResource(R.drawable.megamanderp);
		bmp = BitmapFactory.decodeStream(is);

	}

	private void instantiateVars() {
		// TODO Auto-generated method stub
		uploadFromPhn = (Button) findViewById(R.id.uploadphnBt);
		pushPic = (Button) findViewById(R.id.uploadServerBt);
		camera = (Button) findViewById(R.id.cameraBt);
		iv = (ImageView) findViewById(R.id.photoIv);
		uploadFromPhn.setOnClickListener(this);
		pushPic.setOnClickListener(this);
		camera.setOnClickListener(this);

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
		case (R.id.uploadServerBt):
			break;
		case (R.id.cameraBt):
			leIntent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			galOrCam = 0;
			startActivityForResult(leIntent, cameraData);
			break;
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
				//The matrix stuff is a temporary solution for glitch in uploading from gallery
				//(image is uploaded as rotated)
				Matrix matrix = new Matrix();
				matrix.postRotate(90);
				try {
					Bitmap rotatedBitmap = BitmapFactory.decodeStream(getContentResolver()
							.openInputStream(targetUri));
					bmp = Bitmap.createBitmap(rotatedBitmap, 0, 0, rotatedBitmap.getWidth(), rotatedBitmap.getHeight(), matrix, true);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else { // if camera is active
				Bundle extras = data.getExtras();
				bmp = (Bitmap) extras.get("data");
			}
			iv.setImageBitmap(bmp);
		}
	}

}