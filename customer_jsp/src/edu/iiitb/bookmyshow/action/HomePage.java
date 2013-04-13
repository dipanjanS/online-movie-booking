package edu.iiitb.bookmyshow.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.City;
import edu.iiitb.bookmyshow.model.DateDisplay;
import edu.iiitb.bookmyshow.model.Movie;
import edu.iiitb.bookmyshow.model.MovieList;
import edu.iiitb.bookmyshow.model.TopMovies;

public class HomePage extends ActionSupport{
	String userName;
	String cityName;
	ArrayList<City>cityList=new ArrayList<City>();
	ArrayList<Movie>movieList=new ArrayList<Movie>();
	ArrayList<DateDisplay>dateList=new ArrayList<DateDisplay>();
	ArrayList<MovieList>allMovies=new ArrayList<MovieList>();
	ArrayList<String>returnCityList=new ArrayList<String>();
	ArrayList<String>returnMovieList=new ArrayList<String>();
	ArrayList<String>returnDateList=new ArrayList<String>();
	
	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}
	ArrayList<TopMovies>topMovieList=new ArrayList<TopMovies>();
	public ArrayList<DateDisplay> getDateList() {
		return dateList;
	}

	public void setDateList(ArrayList<DateDisplay> dateList) {
		this.dateList = dateList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<TopMovies> getTopMovieList() {
		return topMovieList;
	}
	public void setTopMovieList(ArrayList<TopMovies> topMovieList) {
		this.topMovieList = topMovieList;
	}

	public ArrayList<City> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<City> cityList) {
		this.cityList = cityList;
	}
	

	public ArrayList<String> getReturnCityList() {
		return returnCityList;
	}

	public void setReturnCityList(ArrayList<String> returnCityList) {
		this.returnCityList = returnCityList;
	}

	public ArrayList<String> getReturnMovieList() {
		return returnMovieList;
	}

	public void setReturnMovieList(ArrayList<String> returnMovieList) {
		this.returnMovieList = returnMovieList;
	}

	public ArrayList<String> getReturnDateList() {
		return returnDateList;
	}

	public void setReturnDateList(ArrayList<String> returnDateList) {
		this.returnDateList = returnDateList;
	}

	public String execute(){
		if(ActionContext.getContext().getSession().get("userSession")==null){
			addActionError("");
		}

		cityList=City.fetchCity();
		movieList=Movie.fetchMovie();
		topMovieList=TopMovies.getTopMovie();
		//dateList=DateDisplay.fetchDate();
		
		allMovies=MovieList.returnAllMovies();
		System.out.println("from sign in page......"+allMovies.size());
		for(int i=0;i<cityList.size();i++){
			returnCityList.add(cityList.get(i).getCityName());
		}
		for(int i=0;i<movieList.size();i++){
			returnMovieList.add(movieList.get(i).getMovieName());
		}
		
		Calendar currentCal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("Current date of Calendar : "
                        + dateFormat.format(currentCal.getTime()));
        returnDateList.add(dateFormat.format(currentCal.getTime()));
        // Use of add() method for date increment
        currentCal.add(Calendar.DATE, 1);
        returnDateList.add(dateFormat.format(currentCal.getTime()));
        System.out.println("After increment date of Calendar : "
                + dateFormat.format(currentCal.getTime()));
        currentCal.add(Calendar.DATE, 1);
        returnDateList.add(dateFormat.format(currentCal.getTime()));
        System.out.println(dateFormat.format(currentCal.getTime()));
		/*for(int i=0;i<dateList.size();i++){
			returnDateList.add(dateList.get(i).getDisplayDate());
		}*/
		for(int i=0;i<allMovies.size();i++){
			System.out.println("movies..."+allMovies.get(i).getMovieName());
		}

		return SUCCESS;
	}
}
