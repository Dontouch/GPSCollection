package com.example.gpsservice.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SystemBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// 调用Service
        context.startService(new Intent("com.example.gpsservice.service.SystemLocalService"));
        Log.i("device-gps-local-service", "GPS本地采集服务启动成功!");

        // 调用Service
        context.startService(new Intent("com.example.gpsservice.service.SystemRemoteService"));
        Log.i("device-gps-remote-service", "GPS远程上传服务启动成功!");
		
	}

}
