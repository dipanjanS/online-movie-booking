package edu.iiitb.bookmyshow.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.CinemaDetails;

public class AllTheatres extends ActionSupport{
	ArrayList<CinemaDetails>cinemaList=new ArrayList<CinemaDetails>();
	
	public ArrayList<CinemaDetails> getCinemaList() {
		return cinemaList;
	}

	public void setCinemaList(ArrayList<CinemaDetails> cinemaList) {
		this.cinemaList = cinemaList;
	}

	public String allTheatreList(){
		
		cinemaList=CinemaDetails.fetchCinema();
		System.out.println("in theatre details size of arraylist.."+cinemaList.size());
		return SUCCESS;
	}
}
