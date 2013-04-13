package edu.iiitb.bookmyshow.model;

import java.sql.Date;
import java.sql.Time;

public class PriceDetails {
	int genClass;
	int silverClass;
	int goldClass;
	Time showTime;
	Date showDate;
	public int getGenClass() {
		return genClass;
	}
	public void setGenClass(int genClass) {
		this.genClass = genClass;
	}
	public int getSilverClass() {
		return silverClass;
	}
	public void setSilverClass(int silverClass) {
		this.silverClass = silverClass;
	}
	public int getGoldClass() {
		return goldClass;
	}
	public void setGoldClass(int goldClass) {
		this.goldClass = goldClass;
	}
	public Time getShowTime() {
		return showTime;
	}
	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	
	
}
