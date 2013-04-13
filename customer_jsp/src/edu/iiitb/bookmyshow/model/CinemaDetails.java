package edu.iiitb.bookmyshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Utils.DBManager;

public class CinemaDetails {
	String cinemaName;
	String address;
	String rating;
	String cinemaId;
	String cityName;
	int noOfScreens;
	int capacity;
	
	static ArrayList<CinemaDetails>returnCinema=new ArrayList<CinemaDetails>();
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getNoOfScreens() {
		return noOfScreens;
	}
	public void setNoOfScreens(int noOfScreens) {
		this.noOfScreens = noOfScreens;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public static ArrayList<CinemaDetails> getReturnCinema() {
		return returnCinema;
	}
	public static void setReturnCinema(ArrayList<CinemaDetails> returnCinema) {
		CinemaDetails.returnCinema = returnCinema;
	}
	public String getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public static ArrayList<CinemaDetails> fetchCinema(){
		returnCinema.clear();
try{
			Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			
			sqlQuery = " select cityname,cinemaname,address,rating,numofscreens,capacity from city,cinema where cinema.cityid=city.cityid;";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			while(rs.next()){
				CinemaDetails c = new CinemaDetails();
				c.setCityName(rs.getString(1));
				c.setCinemaName(rs.getString(2));
				c.setAddress(rs.getString(3));
				c.setRating(rs.getString(4));
				c.setNoOfScreens(rs.getInt(5));
				c.setCapacity(rs.getInt(6));
				returnCinema.add(c);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		return returnCinema;
	}

}
