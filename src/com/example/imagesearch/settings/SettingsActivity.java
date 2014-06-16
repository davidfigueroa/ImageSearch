package com.example.imagesearch.settings;

import com.example.imagesearch.R;
import com.example.imagesearch.R.layout;
import com.example.imagesearch.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SettingsActivity extends Activity {
	public static final String SettingDataKeyName = "sd";	
	private SettingData data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		data = (SettingData) getIntent().getSerializableExtra(SettingDataKeyName);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	public void onSaveClick(MenuItem mi) {
		Intent i = new Intent();
		i.putExtra(SettingDataKeyName, data);
		setResult(RESULT_OK, i);
		finish();
	}
}
