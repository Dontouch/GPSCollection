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
		initLocation(); // ��ʼ����λ
		showToast(SystemLocalService.this, "���ڶ�λ");
		locationClient.startLocation();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		destroyLocation();// ����
	}

	private void initLocation() {
		// ��ʼ��client
		locationClient = new AMapLocationClient(this.getApplicationContext());
		// ���ö�λ����
		locationClient.setLocationOption(getDefaultOption());
		// ���ö�λ����
		locationClient.setLocationListener(locationListener);
	}

	/**
	 * Ĭ�ϵĲ���
	 */
	private AMapLocationClientOption getDefaultOption() {
		AMapLocationClientOption mOption = new AMapLocationClientOption();
		mOption.setLocationMode(AMapLocationMode.Hight_Accuracy);// ��ѡ�����ö�λģʽ����ѡ��ģʽ�и߾��ȡ����豸�������硣Ĭ��Ϊ�߾���ģʽ
		mOption.setGpsFirst(false);// ��ѡ�������Ƿ�gps���ȣ�ֻ�ڸ߾���ģʽ����Ч��Ĭ�Ϲر�
		mOption.setHttpTimeOut(30000);// ��ѡ��������������ʱʱ�䡣Ĭ��Ϊ30�롣�ڽ��豸ģʽ����Ч
		mOption.setInterval(5000);// ��ѡ�����ö�λ�����Ĭ��Ϊ2��
		mOption.setNeedAddress(true);// ��ѡ�������Ƿ񷵻�������ַ��Ϣ��Ĭ����ture
		mOption.setOnceLocation(false);// ��ѡ�������Ƿ񵥴ζ�λ��Ĭ����false
		mOption.setOnceLocationLatest(false);// ��ѡ�������Ƿ�ȴ�wifiˢ�£�Ĭ��Ϊfalse.�������Ϊtrue,���Զ���Ϊ���ζ�λ��������λʱ��Ҫʹ��
		return mOption;
	}

	/**
	 * ��λ����
	 */
	AMapLocationListener locationListener = new AMapLocationListener() {
		@Override
		public void onLocationChanged(AMapLocation loc) {
			if (null != loc) {
				// ������λ���
				Thread thread = new Thread(new DoCollectThread(loc));
				thread.run();

			} else {
				showToast(SystemLocalService.this, "��λʧ�ܣ�loc is null");

			}
		}
	};

	private void destroyLocation() {
		if (null != locationClient) {
			/**
			 * ���AMapLocationClient���ڵ�ǰActivityʵ�����ģ�
			 * ��Activity��onDestroy��һ��Ҫִ��AMapLocationClient��onDestroy
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
			// �洢�ڱ���
			gpsLocationService.saveToLocal(result);
		}

	}

	private void showToast(Context context, String str) {
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}
	
	

}
