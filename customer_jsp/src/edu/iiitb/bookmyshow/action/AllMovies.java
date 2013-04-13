package edu.iiitb.bookmyshow.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.Movie;

public class AllMovies extends ActionSupport {
	ArrayList<Movie> movieList=new ArrayList<Movie>();
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}
	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}
	public String allMovieList(){
		
		movieList=Movie.fetchMovie();
		System.out.println("movie list size...."+movieList.size());
		return SUCCESS;
	}
}
