package com.example.imagesearch;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class GoogleImageSearch {
	private static AsyncHttpClient client = new AsyncHttpClient();
	private static final String BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images";
	
	public static void getImages(String queryString, int start, final ResponseHandler responseHandler) {
	      RequestParams params = new RequestParams();
	      params.add("v", "1.0");
	      params.add("rsz", "8");
	      params.add("start", String.valueOf(start));
	      params.add("q", queryString);
	      client.get(BASE_URL, params, new JsonHttpResponseHandler() {
	    	  public void onSuccess(JSONObject json) {
	    		try {
	    			JSONArray results = json.getJSONObject("responseData").getJSONArray("results");
	    			ImageInfo[] ret = new ImageInfo[results.length()];
	    			for (int i = 0; i < results.length(); i++) {
	    				JSONObject o = (JSONObject) results.get(i);
	    				ImageInfo im = new ImageInfo(o.getString("url"), o.getString("tbUrl"));
	    				ret[i] = im;
	    			}
	    			responseHandler.onSuccess(ret);
				} catch (Exception e) {
					responseHandler.onError(e);
				}
	    	  };
	    	  public void onFailure(Throwable e, JSONObject json) {
				try {
					if (e != null) {
						responseHandler.onError(e);
					} else {
						String errorMessage = json.getString("responseDetails");
						responseHandler.onError(new Exception(errorMessage));
					}
				} catch (Exception e2) {
					responseHandler.onError(e2);
				}
	    	  };
	      });
	 }
	
	public static interface ResponseHandler {
		void onSuccess(ImageInfo[] images);
		void onError(Throwable error);
	}
}
