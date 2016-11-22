package com.myproject.util;

import com.baidu.mapapi.model.LatLng;

public class DistanceUtil {
	
	private static final double EARTH_RADIUS = 6378.137*1000;//����뾶m
	private static double rad(double d) {
	   return d * Math.PI / 180.0;
	}

	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
	   double radLat1 = rad(lat1);
	   double radLat2 = rad(lat2);
	   double a = radLat1 - radLat2;
	   double b = rad(lng1) - rad(lng2);

	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +	//pow�Ǵη�pow(a,b)��a��b����
			   		Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;	//Math.ceil()��������ȡ����Math.floor()��������ȡ����Math.round() ������ѧ�г��õ�����������ȡ����
	   return s;
	}
	
	public static double getDistance(LatLng ll1, LatLng ll2) {
		
		return getDistance(ll1.latitude, ll1.longitude, 
				 ll2.latitude,  ll2.longitude);
	}
	
	
}
