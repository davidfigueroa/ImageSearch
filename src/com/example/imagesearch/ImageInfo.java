package com.example.imagesearch;

public class ImageInfo {
	private String url;
	private String thumbnailUrl;
	
	public ImageInfo(String url, String thumbnailUrl) {
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
	}
	
	public String getUrl() {
		return url;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
}
