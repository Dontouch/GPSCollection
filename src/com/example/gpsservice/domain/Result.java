package com.example.gpsservice.domain;

import java.util.Date;



public class Result {
	private Integer user_id;
	private Long time;// 转换后的时间字符串
	private Long location;// 转换后的位置字符串
	private Date cur_date;// 收集时间

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public Date getCur_date() {
		return cur_date;
	}

	public void setCur_date(Date date) {
		this.cur_date = date;
	}

	

}
