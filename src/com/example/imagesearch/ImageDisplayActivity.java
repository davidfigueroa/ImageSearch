package com.example.imagesearch;

import android.app.Activity;
import android.os.Bundle;

import com.loopj.android.image.SmartImageView;

public class ImageDisplayActivity extends Activity {
	public static final String ImageUrl = "url";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		getActionBar().hide();
		
		String imageUrl = getIntent().getStringExtra(ImageUrl);
		SmartImageView ivImage = (SmartImageView) findViewById(R.id.ivImage);
		ivImage.setImageUrl(imageUrl);
	}
}
