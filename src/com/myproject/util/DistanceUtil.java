package com.myproject.util;

import com.baidu.mapapi.model.LatLng;

public class DistanceUtil {
	
	private static final double EARTH_RADIUS = 6378.137*1000;//地球半径m
	private static double rad(double d) {
	   return d * Math.PI / 180.0;
	}

	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
	   double radLat1 = rad(lat1);
	   double radLat2 = rad(lat2);
	   double a = radLat1 - radLat2;
	   double b = rad(lng1) - rad(lng2);

	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +	//pow是次方pow(a,b)即a的b次幂
			   		Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;	//Math.ceil()用作向上取整。Math.floor()用作向下取整。Math.round() 我们数学中常用到的四舍五入取整。
	   return s;
	}
	
	public static double getDistance(LatLng ll1, LatLng ll2) {
		
		return getDistance(ll1.latitude, ll1.longitude, 
				 ll2.latitude,  ll2.longitude);
	}
	
	
}
