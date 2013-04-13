package edu.iiitb.bookmyshow.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import Utils.DBManager;



public class TopMovies {
	String movie;
	int rating;
	String category;
	static int swapRating=0;
	static String swapMovie;
	static String swapCategory;
	static ArrayList<TopMovies>topMovies=new ArrayList<TopMovies>();
	static ArrayList<TopMovies>topMoviesReturn=new ArrayList<TopMovies>();
	
	public static ArrayList<TopMovies> getTopMoviesReturn() {
		return topMoviesReturn;
	}

	public static void setTopMoviesReturn(ArrayList<TopMovies> topMoviesReturn) {
		TopMovies.topMoviesReturn = topMoviesReturn;
	}
	
	
	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	

	public static ArrayList<TopMovies> getTopMovies() {
		return topMovies;
	}

	public static void setTopMovies(ArrayList<TopMovies> topMovies) {
		TopMovies.topMovies = topMovies;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}	
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	

	public static ArrayList<TopMovies> getTopMovie(){
		int res=0;
		topMovies.clear();
		topMoviesReturn.clear();
		try{
			java.sql.Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);

			sqlQuery = "select movieName,category,rating from movie;";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();

			while(rs.next()){
				TopMovies movieOp = new TopMovies();
				movieOp.setMovie(rs.getString(1));
				movieOp.setCategory(rs.getString(2));
				movieOp.setRating(rs.getInt(3));
				topMovies.add(movieOp);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<topMovies.size();i++){
			for(int j=0;j<topMovies.size();j++){
				if(topMovies.get(i).getRating()>topMovies.get(j).getRating()){
					swapRating=topMovies.get(i).getRating();
					swapMovie=topMovies.get(i).getMovie();
					swapCategory=topMovies.get(i).getCategory();
					
					topMovies.get(i).setRating(topMovies.get(j).getRating());
					topMovies.get(i).setMovie(topMovies.get(j).getMovie());
					topMovies.get(i).setCategory(topMovies.get(j).getCategory());
					
					topMovies.get(j).setRating(swapRating);
					topMovies.get(j).setMovie(swapMovie);
					topMovies.get(j).setCategory(swapCategory);
				}
			}
		}
		System.out.println("SIZE:"+topMovies.size());
		if(topMovies.size()<=5)
			topMoviesReturn=topMovies;
		else{
			for(int i=0;i<5;i++){
				topMoviesReturn.add(topMovies.get(i));
			}
		}
		return topMoviesReturn;
	}
}
