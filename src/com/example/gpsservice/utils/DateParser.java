package com.example.gpsservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

	
	public static String getCurrentTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

}
