package com.example.gpsservice.domain;

import java.util.Date;



public class Result {
	private Integer user_id;
	private Long time;// ת�����ʱ���ַ���
	private Long location;// ת�����λ���ַ���
	private Date cur_date;// �ռ�ʱ��

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
