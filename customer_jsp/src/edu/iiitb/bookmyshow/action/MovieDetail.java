package edu.iiitb.bookmyshow.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Utils.DBManager;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.Movie;

public class MovieDetail extends ActionSupport{
	String movie;
	String storyLine;
	int rating;
	String trailer;
	String cast;
	String category;
	String imageURL;
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	
	
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

	
	public String getMovie() {
		return movie;
	}
	
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public String execute(){
		
		try{
			Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			
			sqlQuery = "select * from movie where movieName='"+movie+"';";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			
			while(rs.next()){
				setMovie(rs.getString(2));
				setStoryLine(rs.getString(3));
				setImageURL(rs.getString(4));
				setRating(rs.getInt(5));
				setTrailer(rs.getString(6));
				setCast(rs.getString(8));
				setCategory(rs.getString(7));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
