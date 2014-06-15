package com.example.imagesearch;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
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
	      client.get(BASE_URL, params, new AsyncHttpResponseHandler() {
	    	@Override
	    	public void onSuccess(int httpCode, Header[] arg1, byte[] arg2) {
	    		try {
					String responseContent = new String(arg2, "UTF-8");
		    		if (httpCode == 200) {
		    			JSONObject json = new JSONObject(responseContent);
		    			JSONArray results = json.getJSONObject("responseData").getJSONArray("results");
		    			ImageInfo[] ret = new ImageInfo[results.length()];
		    			for (int i = 0; i < results.length(); i++) {
		    				JSONObject o = (JSONObject) results.get(i);
		    				ImageInfo im = new ImageInfo(o.getString("url"), o.getString("tbUrl"));
		    				ret[i] = im;
		    			}
		    			responseHandler.onSuccess(ret);
		    		} else {
		    			responseHandler.onError(new Throwable(responseContent));
		    		}
				} catch (Exception e) {
					responseHandler.onError(e);
				}
	    	}
	    	@Override
	    	public void onFailure(int arg0, Header[] arg1, byte[] arg2,	Throwable arg3) {
	    	}
	      });
	 }
	
	public static interface ResponseHandler {
		void onSuccess(ImageInfo[] images);
		void onError(Throwable error);
	}
}
