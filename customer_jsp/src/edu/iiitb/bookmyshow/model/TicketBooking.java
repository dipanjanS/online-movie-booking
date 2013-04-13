package edu.iiitb.bookmyshow.model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;

import edu.iiitb.bookmyshow.action.SelectTheatre;

import Utils.DBManager;

public class TicketBooking {

	int transactionNum;
	String movieName; 
	String showDate;
	String showTime;
	int totalSeats, amount, bookingId;
	
	static ArrayList<TicketBooking> bookHistory = new ArrayList<TicketBooking>();
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";

	public static ArrayList<TicketBooking> fetchCustomerBookings(int custId){

		bookHistory.clear();

		try{
			java.sql.Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
			sqlQuery = 
					"SELECT transactionNum, movieName, showDate, showTime, totalSeats, amount, transaction.bookingID" +
							" FROM movie, transaction, bookinginfo, schedule WHERE bookinginfo.customerID= " + custId +
							" AND movie.movieID=schedule.movieID AND bookinginfo.scheduleID=schedule.scheduleID" +
							" AND transaction.bookingID=bookinginfo.bookingID and showDate > '"+now+"';";

			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();

			while(rs.next()){

				TicketBooking bookingOp = new TicketBooking();

				bookingOp.setTransactionNum(rs.getInt(1));
				bookingOp.setMovieName(rs.getString(2));
				bookingOp.setShowDate(rs.getDate(3).toString());
				bookingOp.setShowDate(SelectTheatre.reverseString(bookingOp.getShowDate()));
				bookingOp.setShowTime(rs.getTime(4).toString());
				bookingOp.setTotalSeats(rs.getInt(5));
				bookingOp.setAmount(rs.getInt(6));
				bookingOp.setBookingId(rs.getInt(7));
				System.out.println("date from bokked...."+rs.getDate(3));
				System.out.println(">>>>"+bookingOp.getShowDate());
				bookHistory.add(bookingOp);
			}
		}
		catch (Exception e) {
			bookHistory.clear();
			e.printStackTrace();
		}
		return bookHistory;
	}
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(int transactionNum) {
		this.transactionNum = transactionNum;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
