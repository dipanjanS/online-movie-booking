package edu.iiitb.bookmyshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Utils.DBManager;

public class Movie {
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	static ArrayList<Movie> returnMovie=new ArrayList<Movie>();
	int movieID;
	String movieName;
	String storyLine;
	int rating;
	String trailer;
	String cast;
	String category;
	String imageURL;
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public static ArrayList<Movie> getReturnMovie() {
		return returnMovie;
	}
	public static void setReturnMovie(ArrayList<Movie> returnMovie) {
		Movie.returnMovie = returnMovie;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getStoryLine() {
		return storyLine;
	}
	public void setStoryLine(String storyLine) {
		this.storyLine = storyLine;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public static ArrayList<Movie> fetchMovie(){
		returnMovie.clear();
try{
			Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			
			sqlQuery = "select * from movie;";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			
			while(rs.next()){
				Movie movieOp = new Movie();
				movieOp.setMovieID(rs.getInt(1));
				movieOp.setMovieName(rs.getString(2));
				movieOp.setStoryLine(rs.getString(3));
				movieOp.setImageURL(rs.getString(4));
				movieOp.setRating(rs.getInt(5));
				movieOp.setTrailer(rs.getString(6));
				movieOp.setCast(rs.getString(8));
				movieOp.setCategory(rs.getString(7));
				returnMovie.add(movieOp);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		return returnMovie;
	}
	
}