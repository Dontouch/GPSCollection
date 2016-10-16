package com.example.gpsservice.service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.example.gpsservice.domain.GPSLocation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

public class GPSLocationService {

	private Context mContext;
	private static final String dbpath = Environment
			.getExternalStorageDirectory().getPath() + "/result.db";
	private SQLiteDatabase db;
	private List<GPSLocation> gpses = new LinkedList<GPSLocation>();

	GPSLocationService(Context context) {
		this.mContext = context;
		createDB();
	}

	private void createDB() {
		File f = new File(dbpath);
		if (!f.exists()) {
			db = SQLiteDatabase.openOrCreateDatabase(dbpath, null);
			db.execSQL("create table tab_gps (infotype integer,latitude double,"
					+ "longitude double,altitude double,direct float,speed float,gpstime date);");
		} else
			db = SQLiteDatabase.openDatabase(dbpath, null,
					SQLiteDatabase.OPEN_READWRITE);
	}

	public void closeDB() {
		// sdb.close();
		db.close();
	}

	public boolean saveToLocal(GPSLocation gpsLocation) {

		gpses.add(gpsLocation);
		boolean result = true;
		try {
			String StrSql = String
					.format("insert into tab_gps (infotype,latitude,longitude,altitude,direct,speed,gpstime) values (%d,%.1f,%.1f,%.1f,%.1f,%.1f,'%s')",
							gpsLocation.getInfoType(),
							gpsLocation.getLatitude(),
							gpsLocation.getLongitude(),
							gpsLocation.getAltitude(), gpsLocation.getDirect(),
							gpsLocation.getSpeed(), gpsLocation.getGpsTime());
			db.execSQL(StrSql);
			result = true;
		} catch (Exception e) {
			result = false;
			Toast.makeText(mContext, "保存GPS数据失败:" + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		return result;
	}

	public List<GPSLocation> findAll() {
		return gpses;
	}

	/**
	 * finish  
	 * @param gpsLocations 
	 * @return
	 */
	public boolean saveToServer(List<GPSLocation> gpsLocations) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(List<GPSLocation> gpsLocations) {
		gpses = null;
		gpses = new LinkedList<GPSLocation>();

		boolean result = true;
		try {
			for (GPSLocation gpsLocation : gpsLocations) {
				String StrSql = String
						.format("delete from tab_gps where gpstime ="
								+ gpsLocation.getGpsTime());
				db.execSQL(StrSql);
			}
			result = true;
		} catch (Exception e) {
			result = false;
			Toast.makeText(mContext, "保存GPS数据失败:" + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		return result;

	}

}
