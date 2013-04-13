package edu.iiitb.bookmyshow.action;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import Utils.DBManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.bookmyshow.model.PriceDetails;
import edu.iiitb.bookmyshow.model.ScheduleDetails;
import edu.iiitb.bookmyshow.model.TheatreDetails;

@SuppressWarnings("serial")
public class Schedule extends ActionSupport{
	String cinemaId;
	String movieName;
	String cityName;
	String imageURL;
	int rating;
	String cast;
	String category;
	String storyLine;
	String trailer;
	String showDate;
	java.sql.Date sqlDate;
	ArrayList<ScheduleDetails> scheduleList=new ArrayList<ScheduleDetails>();
	Map session=ActionContext.getContext().getSession();
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";

	public ArrayList<ScheduleDetails> getScheduleList() {
		return scheduleList;
	}
	public void setScheduleList(ArrayList<ScheduleDetails> scheduleList) {
		this.scheduleList = scheduleList;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
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
	public String getStoryLine() {
		return storyLine;
	}
	public void setStoryLine(String storyLine) {
		this.storyLine = storyLine;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public String execute(){
		//System.out.println("session user name in schedule.java"+ActionContext.getContext().getSession().get("userSession"));
		//System.out.println("in schedule from theatre...."+cinemaId+"...."+movieName+"....."+cityName+"...."+imageURL+"...."+showDate);
		System.out.println("date now as per editing in schedule......."+showDate);
		/*try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			System.out.println("date to be sent..."+showDate);
			java.util.Date date = sdf.parse(showDate);
			System.out.println("util date...."+date);
			sqlDate = new Date(date.getTime());
			System.out.println("converted date...."+sqlDate);
			System.out.println("month of date..."+sqlDate.getMonth());
			if(sqlDate.getMonth()==0){
				sqlDate.setMonth(sqlDate.getMonth()-1);
				sqlDate.setYear(sqlDate.getYear()+1);
			}
			else{
				sqlDate.setMonth(sqlDate.getMonth()-1);
			}
			System.out.println("converted date after editing...."+sqlDate);
			java.sql.Date bDate = new java.sql.Date( sdf.parse( showDate).getTime() );
			System.out.println("new..."+bDate);

		}catch(Exception e){
			e.printStackTrace();
		}*/
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sqlDate= new java.sql.Date( sdf.parse( showDate).getTime() );
		}catch(Exception ex){
			
		}
		System.out.println("sql date"+sqlDate);
		scheduleList=getScheduledetails(cinemaId,movieName,cityName,sqlDate);

		for(int i=0;i<scheduleList.size();i++){
			System.out.println("schedule id...."+scheduleList.get(i).getScheduleId());
		}
		System.out.println(imageURL);
		return SUCCESS;
	}

	public ArrayList<ScheduleDetails>getScheduledetails(String cinemaId,String movieName,String cityName,Date sqlDate){
		System.out.println("in get schedule details.....");
		try{
			scheduleList.clear();
			java.sql.Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);

			sqlQuery = "select schedule.scheduleId,showTime,imageURL,storyline,movie.rating,trailer,category,cast " +
					"from movie,city,schedule,screen,cinema where " +
					"movie.moviename='"+movieName+"'and schedule.showDate='"+sqlDate+"'and city.cityname='"+cityName+"'and cinema.cinemaID='"
					+cinemaId+"'and movie.movieid=schedule.movieid and city.cityid=cinema.cityid and " +
					"screen.cinemaid=cinema.cinemaid and screen.screenid=schedule.screenid;";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();

			while(rs.next()){
				ScheduleDetails s=new ScheduleDetails();
				s.setScheduleId(rs.getInt(1));
				s.setShowTime(rs.getTime(2));
				imageURL=rs.getString(3);
				storyLine=rs.getString(4);
				rating=rs.getInt(5);
				trailer=rs.getString(6);
				category=rs.getString(7);
				cast=rs.getString(8);
				scheduleList.add(s);
				System.out.println("fetching from database...."+category);

			}

		}
		catch( Exception e){
			e.printStackTrace();

		}
		return scheduleList;
	}


}
