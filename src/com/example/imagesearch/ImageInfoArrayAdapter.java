package com.example.imagesearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

public class ImageInfoArrayAdapter extends ArrayAdapter<ImageInfo> {
	public ImageInfoArrayAdapter(Context context) {
		super(context, R.layout.image_item);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageInfo imageInfo = getItem(position);
		
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.image_item, parent, false);
		}
		
		SmartImageView ivImage = (SmartImageView)convertView; 	
		ivImage.setImageUrl(imageInfo.getThumbnailUrl());
		
		return convertView;
	}
}
