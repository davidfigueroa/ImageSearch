package com.example.imagesearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int ShowSettingRequestCode = 1;	
	private EditText edSearchString;
	private ImageInfoArrayAdapter adapter;
	private SettingData settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edSearchString = (EditText)findViewById(R.id.etSearchString);
		
		//image view
		GridView gvImages = (GridView)findViewById(R.id.gvImages);
		adapter = new ImageInfoArrayAdapter(this);
		gvImages.setAdapter(adapter);
		gvImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onImageClick(position);
			}
		});
		
		//bSearch click
		Button bSearch = (Button)findViewById(R.id.bSearch);
		bSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				search();
			}
		});
	}

	private void search() {
		if (edSearchString.getText().length() == 0) {
			Toast.makeText(this, getString(R.string.search_hint), Toast.LENGTH_SHORT).show();
			return;
		}
		
		GoogleImageSearch.getImages(edSearchString.getText().toString(), 0, new GoogleImageSearch.ResponseHandler() {
			@Override
			public void onSuccess(ImageInfo[] images) {
				adapter.clear();
				adapter.addAll(images);				
			}
			@Override
			public void onError(Throwable error) {
				Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
		});
	}

	private void onImageClick(int position) {
		ImageInfo imageInfo = adapter.getItem(position);
		Intent i = new Intent(this, ImageDisplayActivity.class);
		i.putExtra(ImageDisplayActivity.ImageUrl, imageInfo.getUrl());
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onShowSettingsClick(MenuItem mi) {
		Intent i = new Intent(this, SettingsActivity.class);
		i.putExtra(SettingsActivity.SettingDataKeyName, this.settings);
		startActivityForResult(i, ShowSettingRequestCode);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == ShowSettingRequestCode && resultCode == RESULT_OK) {
			this.settings = (SettingData) data.getSerializableExtra(SettingsActivity.SettingDataKeyName);
			if (edSearchString.getText().length() > 0) {
				search();
			}
		}
	}
}
