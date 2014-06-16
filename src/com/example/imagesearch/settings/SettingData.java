package com.example.imagesearch.settings;

import java.io.Serializable;

public class SettingData implements Serializable {
	private static final long serialVersionUID = 8003380714215280115L;
	private ImageSize imageSize;
	private ColorFilter colorFilter;
	private ImageType imageType;
	private String site;	
	
	public SettingData() {
		this.colorFilter = ColorFilter.none;
		this.imageSize = ImageSize.any;
		this.imageType = ImageType.any;
	}
	
	public ImageSize getImageSize() {
		return imageSize;
	}
	public ColorFilter getColorFilter() {
		return colorFilter;
	}
	public ImageType getImageType() {
		return imageType;
	}
	public String getSite() {
		return site;
	}

}
