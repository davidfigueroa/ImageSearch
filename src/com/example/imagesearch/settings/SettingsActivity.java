package com.example.imagesearch.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.imagesearch.R;

public class SettingsActivity extends Activity {
	public static final String SettingDataKeyName = "sd";	
	private SettingData data;
	private Spinner imageSizeSpinner, colorFilterSpinner, imageTypeSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		data = (SettingData) getIntent().getSerializableExtra(SettingDataKeyName);

		imageSizeSpinner = (Spinner) findViewById(R.id.spinImageSize);
		ArrayAdapter<ImageSize> imageSizeAdapter = new ArrayAdapter<ImageSize>(this, android.R.layout.simple_spinner_item, ImageSize.values());
		imageSizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		imageSizeSpinner.setAdapter(imageSizeAdapter);
		imageSizeSpinner.setSelection(imageSizeAdapter.getPosition(data.getImageSize()));

		colorFilterSpinner = (Spinner) findViewById(R.id.spinColorFilter);
		ArrayAdapter<ColorFilter> colorFilterAdapter = new ArrayAdapter<ColorFilter>(this, android.R.layout.simple_spinner_item, ColorFilter.values());
		colorFilterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		colorFilterSpinner.setAdapter(colorFilterAdapter);
		colorFilterSpinner.setSelection(colorFilterAdapter.getPosition(data.getColorFilter()));

		imageTypeSpinner = (Spinner) findViewById(R.id.spinImageType);
		ArrayAdapter<ImageType> imageTypeAdapter = new ArrayAdapter<ImageType>(this, android.R.layout.simple_spinner_item, ImageType.values());
		imageTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		imageTypeSpinner.setAdapter(imageTypeAdapter);
		imageTypeSpinner.setSelection(imageTypeAdapter.getPosition(data.getImageType()));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void onSaveClick(MenuItem mi) {
		Intent i = new Intent();
		
		data.setImageSize(ImageSize.valueOf(imageSizeSpinner.getSelectedItem().toString()));
		data.setColorFilter(ColorFilter.valueOf(colorFilterSpinner.getSelectedItem().toString()));
		data.setImageType(ImageType.valueOf(imageTypeSpinner.getSelectedItem().toString()));
		
		i.putExtra(SettingDataKeyName, data);
		setResult(RESULT_OK, i);
		finish();
	}
}
