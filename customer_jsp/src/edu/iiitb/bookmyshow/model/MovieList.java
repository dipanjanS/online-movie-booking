package edu.iiitb.bookmyshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Utils.DBManager;

public class MovieList {
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	String movieName;
	int movieID;
	static ArrayList<MovieList>allMovies=new ArrayList<MovieList>();
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public static ArrayList<MovieList> getAllMovies() {
		return allMovies;
	}
	public static void setAllMovies(ArrayList<MovieList> allMovies) {
		MovieList.allMovies = allMovies;
	}
	public static ArrayList<MovieList> returnAllMovies(){
		allMovies.clear();
		try{
					Connection con=DBManager.getConnection();
					DBManager.useDataBase(con);
					
					sqlQuery = "select movieName,movieID from movie;";
					statement=con.prepareStatement(sqlQuery);
					rs=statement.executeQuery();
					
					while(rs.next()){
						MovieList m=new MovieList();
						m.setMovieName(rs.getString(1));
						m.setMovieID(rs.getInt(2));
						allMovies.add(m);
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			
		return allMovies;
	}

}
