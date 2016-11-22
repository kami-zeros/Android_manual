package com.myproject.http;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ard_myproject.R;

public class HttpUtil {
	
	private static RequestQueue queue;
	private static ImageLoader imageLoader;
	
/*********--------根据API请求数据（json数据）--------*******************************/	
	public static void loadMeinvBean(Context context, String num, Listener<String> listener) {
		String url="http://apis.baidu.com/txapi/mvtp/meinv?num="+num;//num是数量
		
		StringRequest request=new StringRequest(url, listener, null){
			//配置请求头
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> map=new HashMap<String, String>();
				map.put("apikey", "26d8e0591405bb0ce972933b80fcbc02");
				return map;
			}
		};
		
		if (queue==null) {
			queue=Volley.newRequestQueue(context);
			Log.d("TAG", "HttpUtil:"+url);
		}
		queue.add(request);
	}
	
	/**********--------- -获得的图片放在控件上------------****************************** */
	public static void loadImage (String url, Context context, ImageView imageView) {
//		String apikey="26d8e0591405bb0ce972933b80fcbc02";
//		String url="http://apis.baidu.com/txapi/mvtp/meinv"+apikey;
		
		if (imageLoader==null) {
			createImageLoader(context);
		}
		ImageListener listener=ImageLoader.getImageListener(imageView, R.drawable.bucket_no_picture, R.drawable.bucket_no_picture);
		imageLoader.get(url, listener);
	}

	/********-------创建图片缓存---------*********************************************/
	private static void createImageLoader(Context context) {
		if (queue==null) {
			queue=Volley.newRequestQueue(context);
		}
		
		imageLoader=new ImageLoader(queue, new ImageCache() {
			
			LruCache<String, Bitmap> cache=new LruCache<String, Bitmap>(
					(int) Runtime.getRuntime().maxMemory()/8);
			@Override
			public void putBitmap(String arg0, Bitmap arg1) {
				cache.put(arg0, arg1);
			}
			@Override
			public Bitmap getBitmap(String arg0) {
				return cache.get(arg0);
			}
		});
	}
	
	/*******************--------根据网址请求html文件---------***********************/
	public static void gethtml(Context context, String url, Listener<String> listener){
		StringRequest request=new StringRequest(url, listener, null);
		Log.d("TAG", "gethtml:"+request);
		
		if (queue==null) {
			queue=Volley.newRequestQueue(context);
		}
		queue.add(request);
	} 
	
}
