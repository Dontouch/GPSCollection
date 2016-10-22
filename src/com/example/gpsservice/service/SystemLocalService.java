package com.example.gpsservice.service;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.example.gpsservice.domain.Result;
import com.example.gpsservice.utils.LocationUtil;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.widget.Toast;

public class SystemLocalService extends Service {

	static LocationManager locationManager;
	static String provider;
	private GPSLocationService gpsLocationService;

	private AMapLocationClient locationClient = null;

	Context tag = SystemLocalService.this;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		gpsLocationService = new GPSLocationService(tag);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {

		// initGPSLocationListener();

		super.onStart(intent, startId);
		initLocation(); // 初始化定位
		showToast(SystemLocalService.this, "正在定位");
		locationClient.startLocation();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		destroyLocation();// 销毁
	}

	private void initLocation() {
		// 初始化client
		locationClient = new AMapLocationClient(this.getApplicationContext());
		// 设置定位参数
		locationClient.setLocationOption(getDefaultOption());
		// 设置定位监听
		locationClient.setLocationListener(locationListener);
	}

	/**
	 * 默认的参数
	 */
	private AMapLocationClientOption getDefaultOption() {
		AMapLocationClientOption mOption = new AMapLocationClientOption();
		mOption.setLocationMode(AMapLocationMode.Hight_Accuracy);// 可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
		mOption.setGpsFirst(false);// 可选，设置是否gps优先，只在高精度模式下有效。默认关闭
		mOption.setHttpTimeOut(30000);// 可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
		mOption.setInterval(5000);// 可选，设置定位间隔。默认为2秒
		mOption.setNeedAddress(true);// 可选，设置是否返回逆地理地址信息。默认是ture
		mOption.setOnceLocation(false);// 可选，设置是否单次定位。默认是false
		mOption.setOnceLocationLatest(false);// 可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
		return mOption;
	}

	/**
	 * 定位监听
	 */
	AMapLocationListener locationListener = new AMapLocationListener() {
		@Override
		public void onLocationChanged(AMapLocation loc) {
			if (null != loc) {
				// 解析定位结果
				Thread thread = new Thread(new DoCollectThread(loc));
				thread.run();

			} else {
				showToast(SystemLocalService.this, "定位失败，loc is null");

			}
		}
	};

	private void destroyLocation() {
		if (null != locationClient) {
			/**
			 * 如果AMapLocationClient是在当前Activity实例化的，
			 * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
			 */
			locationClient.onDestroy();
			locationClient = null;
		}
	}

	class DoCollectThread implements Runnable {

		AMapLocation location;

		public DoCollectThread(AMapLocation location) {
			this.location = location;
		}

		@Override
		public void run() {

			Result result = LocationUtil.getResult(location);
			showToast(SystemLocalService.this,result.toString());
			// 存储在本地
			gpsLocationService.saveToLocal(result);
		}

	}

	private void showToast(Context context, String str) {
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}
	
	

}
