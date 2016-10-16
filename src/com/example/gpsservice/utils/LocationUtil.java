package com.example.gpsservice.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.text.StaticLayout;
import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.example.gpsservice.domain.Result;

public class LocationUtil {

	/**
	 * 根据定位的结果返回转换后的结果
	 * 
	 * @param location
	 * @return
	 */
	public static Result getResult(AMapLocation location) {
		if (null == location) {
			return null;
		}
		StringBuffer location_tmp;
		StringBuffer time_tmp;
		Result result = null;

		if (location.getErrorCode() == 0) {

		  location_tmp = Location2String(location.getLongitude(),
					location.getLatitude());

			if (!location.getProvider().equalsIgnoreCase(
					android.location.LocationManager.GPS_PROVIDER)) {

				time_tmp = Time2String(formatUTC(location.getTime(),
						"yyyy-MM-dd HH:mm:ss:sss"));
			}
		}
		
//		result = new Result((use_id, time, location);
		
		

		return null;
	}

	private static SimpleDateFormat sdf = null;

	public synchronized static String[] formatUTC(long l, String strPattern) {
		if (TextUtils.isEmpty(strPattern)) {
			strPattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (sdf == null) {
			try {
				sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
			} catch (Throwable e) {
			}
		} else {
			sdf.applyPattern(strPattern);
		}
		if (l <= 0l) {
			l = System.currentTimeMillis();
		}
		// return sdf == null ? "NULL" : sdf.format(l);
		return null;
	}

	private static StringBuffer Location2String(double longitude, double latitude) {
		return null;
	}

	private static StringBuffer Time2String(String[] times) {
		return null;
	}

}
