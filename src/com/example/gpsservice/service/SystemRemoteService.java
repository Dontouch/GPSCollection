package com.example.gpsservice.service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.gpsservice.domain.GPSLocation;
import com.example.gpsservice.utils.Constant;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class SystemRemoteService extends Service{

	Context tag = SystemRemoteService.this;
    private GPSLocationService gpsLocationService;
    
    
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		gpsLocationService=new GPSLocationService(tag);
	}


	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		doService();
		super.onStart(intent, startId);
	}
	
	protected void doService()
    {
        // ��������ִ��
        new Timer().schedule(new TimerTask()
        {

            @Override
            public void run()
            {
                // ���汾�ص�GPS���ݵ�Զ�̷�����
                List<GPSLocation> gpsLocations=gpsLocationService.findAll();
                boolean flag=gpsLocationService.saveToServer(gpsLocations);
                if(flag)
                {
                    // ɾ���Ѿ��ϴ���GPS����
                    gpsLocationService.delete(gpsLocations);
                }
            }
        }, 0, Constant.GPS_THREAD_TIME_WAIT);


    }
	
	

}
