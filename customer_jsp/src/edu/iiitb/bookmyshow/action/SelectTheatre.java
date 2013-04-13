package edu.iiitb.bookmyshow.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.CinemaDetails;
import edu.iiitb.bookmyshow.model.SearchTheatre;
import edu.iiitb.bookmyshow.model.TheatreDetails;

public class SelectTheatre extends ActionSupport{
	String returnCity;
	String returnMovie;
	String returnDate;
	static String day;
	static String month;
	static String year;
	static String sendDate=null;
	ArrayList<String>cinemaNames=new ArrayList<String>();
	ArrayList<String>cinemaAddress=new ArrayList<String>();
	ArrayList<String>cinemaRating=new ArrayList<String>();
	ArrayList<CinemaDetails> cinemaList=new ArrayList<CinemaDetails>();
	ArrayList<TheatreDetails> returnSchedule=new ArrayList<TheatreDetails>();
	
	public ArrayList<String> getCinemaNames() {
		return cinemaNames;
	}
	public void setCinemaNames(ArrayList<String> cinemaNames) {
		this.cinemaNames = cinemaNames;
	}
	public ArrayList<String> getCinemaAddress() {
		return cinemaAddress;
	}
	public void setCinemaAddress(ArrayList<String> cinemaAddress) {
		this.cinemaAddress = cinemaAddress;
	}
	public ArrayList<String> getCinemaRating() {
		return cinemaRating;
	}
	public void setCinemaRating(ArrayList<String> cinemaRating) {
		this.cinemaRating = cinemaRating;
	}
	
	public ArrayList<CinemaDetails> getCinemaList() {
		return cinemaList;
	}
	public void setCinemaList(ArrayList<CinemaDetails> cinemaList) {
		this.cinemaList = cinemaList;
	}

public ArrayList<TheatreDetails> getReturnSchedule() {
		return returnSchedule;
	}
	public void setReturnSchedule(ArrayList<TheatreDetails> returnSchedule) {
		this.returnSchedule = returnSchedule;
	}


public String getReturnCity() {
		return returnCity;
	}
	public void setReturnCity(String returnCity) {
		this.returnCity = returnCity;
	}
	public String getReturnMovie() {
		return returnMovie;
	}
	public void setReturnMovie(String returnMovie) {
		this.returnMovie = returnMovie;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
public String bookTheatre(){
	returnSchedule.clear();
	System.out.println(returnDate);
	//sendDate=reverseString(returnDate);
	sendDate=returnDate;
	System.out.println("date to be sent..."+sendDate);
	returnSchedule=SearchTheatre.returnSchedule(returnCity,returnMovie,sendDate);
	cinemaList=SearchTheatre.cinemaList(returnCity,returnMovie,sendDate);
	for(int i=0;i<cinemaList.size();i++){
		System.out.println("ids..."+cinemaList.get(i).getCinemaId());
	}
	if(returnSchedule.size()==0){
		System.out.println("in error...");
		return ERROR;
	}
	else
		return SUCCESS;
}

public static String reverseString(String returnDate){
	StringTokenizer st=new StringTokenizer(returnDate, "-");
	while(st.hasMoreTokens()){
		day=st.nextToken();
		month=st.nextToken();
		year=st.nextToken();
	}
	sendDate=year+"-"+month+"-"+day;
	return sendDate;
}

}
