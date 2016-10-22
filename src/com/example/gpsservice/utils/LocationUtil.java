package com.example.gpsservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		if (null == location)
			return null;

		Long location_tmp = null;
		Long time_tmp = null;
		Result result = null;

		if (location.getErrorCode() == 0) {

			location_tmp = Location2Long(location.getLongitude(),
					location.getLatitude());

			if (!location.getProvider().equalsIgnoreCase(
					android.location.LocationManager.GPS_PROVIDER)) {

				time_tmp = Time2Long(new Date(location.getTime()));

				new Date(location.getTime());
			}

			result = new Result();
			result.setUser_id(1);
			result.setTime(time_tmp);
			result.setLocation(location_tmp);

			
			result.setCur_date(new Date());

		} else {
			return null;
		}

		return result;
	}

	private static Long Location2Long(double longitude, double latitude) {
		if (longitude == 0 || latitude == 0)
			return null;

		/**
		 * tag longitude in China 73°33′E ~ 135°02′30′′E latitude in China
		 * 3°51′N ~53°33′N
		 * 
		 * longitude_range 135°05′ - 73°33′ convert ′ (135*60+05) - (73 *60 +
		 * 33) = 3692 latitude_range 53°33′ - 3°51′ convert ′ (53*60 + 33 ) -
		 * (3*60+51) = 2982
		 * 
		 * cut longitude ′ cut latitude ′
		 * 
		 * param longitude - 73°33′(73 *60 + 33) column index
		 * 
		 * param latitude - 3°51′ (3*60+51) row index
		 * 
		 * final index (result) row* 3692 + column ;
		 * 
		 * double r=度+分/60+秒/3600
		 * 
		 * 
		 */
		double start_longitude = 73.0 + 33.0 / 60.0;
		double start_latitude = 3.0 + 51.0 / 60.0;
		double end_longitude = 135.0 + 2.0 / 60.0 + 30.0 / 3600.0;

		long longitude_all = (long) ((end_longitude - start_longitude) * 3600.0);

		long column = (long) ((longitude - start_longitude) * 3600.0);
		long row = (long) ((latitude - start_latitude) * 3600.0);

		return row * longitude_all + column;

	}

	private static Long Time2Long(Date date) {
		if (date == null)
			return null;

		date.setTime(date.getTime() - date.getTime() % 1000);
		Date tmp = new Date();
		tmp.setHours(0);
		tmp.setMinutes(0);
		tmp.setSeconds(0);
		tmp.setTime(tmp.getTime() - tmp.getTime() % 1000);
		long second_interval = date.getTime() - tmp.getTime();
		second_interval /= 5000;
		return second_interval;
	}

}
