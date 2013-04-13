package edu.iiitb.bookmyshow.model;

import java.util.ArrayList;
import java.util.Calendar;

public class DateDisplay {
	String displayDate;
	static ArrayList<DateDisplay> returnDate=new ArrayList<DateDisplay>();
	static Calendar now = Calendar.getInstance();

	public String getDisplayDate() {
		return displayDate;
	}

	public void setDisplayDate(String displayDate) {
		this.displayDate = displayDate;
	}

	public static ArrayList<DateDisplay> getReturnDate() {
		return returnDate;
	}

	public static void setReturnDate(ArrayList<DateDisplay> returnDate) {
		DateDisplay.returnDate = returnDate;
		
	}
	
	public static ArrayList<DateDisplay> fetchDate(){
		returnDate.clear();
		DateDisplay dateOp1=new DateDisplay();
		int nowMonth=now.get(Calendar.MONTH)+1;
		int nowDate=now.get(Calendar.DATE);
		
		dateOp1.setDisplayDate(nowDate+"-"+nowMonth+"-"+now.get(Calendar.YEAR));
		returnDate.add(dateOp1);
		DateDisplay dateOp2=new DateDisplay();
		nowDate=now.get(Calendar.DATE)+1;
		dateOp2.setDisplayDate(nowDate+"-"+nowMonth+"-"+now.get(Calendar.YEAR));
		returnDate.add(dateOp2);
		
		DateDisplay dateOp3=new DateDisplay();
		nowDate=now.get(Calendar.DATE)+2;
		dateOp3.setDisplayDate(nowDate+"-"+nowMonth+"-"+now.get(Calendar.YEAR));
		
		returnDate.add(dateOp3);
		return returnDate;
	
	}
	
	
	/*
	 * if (now.get(Calendar.YEAR) == a.year
							&& now.get(Calendar.MONTH) + 1 == a.month
							&& now.get(Calendar.DATE) == a.day
							&& now.get(Calendar.HOUR_OF_DAY) == a.hour
							&& now.get(Calendar.MINUTE) == a.minute)
	 * 
	 */
}
