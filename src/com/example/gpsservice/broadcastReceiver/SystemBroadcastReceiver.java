package com.example.gpsservice.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SystemBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// ����Service
        context.startService(new Intent("com.example.gpsservice.service.SystemLocalService"));
        Log.i("device-gps-local-service", "GPS���زɼ����������ɹ�!");

        // ����Service
        context.startService(new Intent("com.example.gpsservice.service.SystemRemoteService"));
        Log.i("device-gps-remote-service", "GPSԶ���ϴ����������ɹ�!");
		
	}

}
