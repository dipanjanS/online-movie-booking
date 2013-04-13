package edu.iiitb.bookmyshow.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Utils.DBManager;

public class SearchTheatre {
	static ArrayList<TheatreDetails>returnSchedule=new ArrayList<TheatreDetails>();
	 static ArrayList<CinemaDetails>returnCinemaList=new ArrayList<CinemaDetails>();
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	public static ArrayList<TheatreDetails> returnSchedule(String returnCityList,String returnMovieList,String returnDateList){
		
		try{
			returnSchedule.clear();
			java.sql.Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			
			sqlQuery = "select moviename,cityname,cinemaname,screennum,showdate,showtime from movie,cinema,screen,schedule,city where movie.moviename='"+returnMovieList+"'and city.cityname='"+returnCityList+"'and schedule.showdate='"+returnDateList+"'and movie.movieid=schedule.movieid and city.cityid=cinema.cityid and screen.cinemaid=cinema.cinemaid and screen.screenid=schedule.screenid;";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			
			while(rs.next()){
				TheatreDetails t=new TheatreDetails();
				t.setMovieName(rs.getString(1));
				t.setCityName(rs.getString(2));
				t.setCinemaName(rs.getString(3));
				t.setScreenNum(rs.getInt(4));
				t.setShowDate(rs.getDate(5));
				t.setShowTime(rs.getTime(6));
				returnSchedule.add(t);
				
				
			}
		
		}
			catch( Exception e){
				e.printStackTrace();
			}
		
		
		return returnSchedule;
	}
	public static ArrayList<CinemaDetails>cinemaList(String returnCityList,String returnMovieList,String returnDateList){
		
		try{
			returnCinemaList.clear();
			java.sql.Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			
			sqlQuery = "select distinct cinema.cinemaname,cinema.address,cinema.cinemaID, cinema.rating from movie,cinema,city,schedule,screen where movie.moviename='"+returnMovieList+"'and city.cityname='"+returnCityList+"'and schedule.showdate='"+returnDateList+"' and city.cityid=cinema.cityid and schedule.movieid=movie.movieid and schedule.screenID = screen.screenID and screen.cinemaID = cinema.cinemaID;";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			
			while(rs.next()){
				CinemaDetails c=new CinemaDetails();
				c.setCinemaName(rs.getString(1));
				c.setAddress(rs.getString(2));
				c.setCinemaId(rs.getInt(3)+"");
				c.setRating(rs.getString(4));
				returnCinemaList.add(c);
				
				
			}
		
		}
			catch( Exception e){
				e.printStackTrace();
			}
		
		return returnCinemaList;
	}
}
