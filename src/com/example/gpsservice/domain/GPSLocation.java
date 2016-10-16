package com.example.gpsservice.domain;

public class GPSLocation {

	private int InfoType; // ��������
	private double Latitude; // γ��
	private double Longitude; // ����
	private double Altitude; // ����
	private float Direct; // ����
	private float Speed; // �ٶ�
	private String GpsTime; // GPSʱ��

	
	
	public int getInfoType() {
		return InfoType;
	}



	public void setInfoType(int infoType) {
		InfoType = infoType;
	}



	public double getLatitude() {
		return Latitude;
	}



	public void setLatitude(double latitude) {
		Latitude = latitude;
	}



	public double getLongitude() {
		return Longitude;
	}



	public void setLongitude(double longitude) {
		Longitude = longitude;
	}



	public double getAltitude() {
		return Altitude;
	}



	public void setAltitude(double altitude) {
		Altitude = altitude;
	}



	public float getDirect() {
		return Direct;
	}



	public void setDirect(float direct) {
		Direct = direct;
	}



	public float getSpeed() {
		return Speed;
	}



	public void setSpeed(float speed) {
		Speed = speed;
	}



	public String getGpsTime() {
		return GpsTime;
	}



	public void setGpsTime(String gpsTime) {
		GpsTime = gpsTime;
	}



	public GPSLocation(int infoType, double latitude,double longitude,double altitude,float direct,float speed,String gpsTime){
		this.InfoType = infoType;
		this.Latitude = latitude;
		this.Longitude = longitude;
		this.Altitude = altitude;
		this.Direct = direct;
		this.Speed = speed;
		this.GpsTime = gpsTime;
	}



			
}
