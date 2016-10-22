package com.example.gpsservice.service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import com.example.gpsservice.domain.Result;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.Toast;

public class GPSLocationService {

	private Context mContext;
	private static final String dbpath = Environment
			.getExternalStorageDirectory().getPath() + "/tracking.db";
	private SQLiteDatabase db;
	private List<Result> gpses = new LinkedList<Result>();

	GPSLocationService(Context context) {
		this.mContext = context;
		createDB();
	}

	private void createDB() {
		File f = new File(dbpath);
		if (!f.exists()) {
			db = SQLiteDatabase.openOrCreateDatabase(dbpath, null);
			db.execSQL("create table gps_t (user_id integer,time long,"
					+ "location long,cur_date date);");
		} else
			db = SQLiteDatabase.openDatabase(dbpath, null,
					SQLiteDatabase.OPEN_READWRITE);
	}

	public void closeDB() {
		// sdb.close();
		db.close();
	}

	public boolean saveToLocal(Result gps) {

		gpses.add(gps);
		boolean result = true;
		try {
			String StrSql = String
					.format("insert into gps_t (user_id,time,location,cur_date) values (%d,%d,%d,'%s')",
							gps.getUser_id(), gps.getTime(), gps.getLocation(),
							gps.getCur_date());
			db.execSQL(StrSql);
			result = true;
		} catch (Exception e) {
			result = false;
			Toast.makeText(mContext, "保存GPS数据失败:" + e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
		return result;
	}

	public List<Result> findAll() {
		return gpses;
	}

	/**
	 * finish
	 * 
	 * @param gpsLocations
	 * @return
	 */
	public boolean saveToServer(List<Result> gpses) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(List<Result> gpses) {
		gpses = null;
		gpses = new LinkedList<Result>();

		boolean result = true;
		try {
			for (Result gps : gpses) {
				String StrSql = String
						.format("delete from gps_t where cur_date ="
								+ gps.getCur_date());
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
