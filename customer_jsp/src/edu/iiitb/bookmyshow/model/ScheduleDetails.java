package edu.iiitb.bookmyshow.model;

import java.sql.Time;

public class ScheduleDetails {
	int scheduleId;
	Time showTime;
	
	

	public Time getShowTime() {
		return showTime;
	}
	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	
	
}
