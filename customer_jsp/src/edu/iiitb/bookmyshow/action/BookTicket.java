package edu.iiitb.bookmyshow.action;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import Utils.DBManager;

import edu.iiitb.bookmyshow.model.PriceDetails;
import edu.iiitb.bookmyshow.model.ScheduleDetails;

public class BookTicket extends ActionSupport{
	
	int scheduleId;
	String movieName;
	int genClass,silverClass,goldClass;
	Date showDate;
	Time showTime;
	ArrayList<String>ticketClass=new ArrayList<String>();
	ArrayList<String>quantityList=new ArrayList<String>();
	Map session=ActionContext.getContext().getSession();
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	
	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	

	public int getGenClass() {
		return genClass;
	}

	public void setGenClass(int genClass) {
		this.genClass = genClass;
	}

	public int getSilverClass() {
		return silverClass;
	}

	public void setSilverClass(int silverClass) {
		this.silverClass = silverClass;
	}

	public int getGoldClass() {
		return goldClass;
	}

	public void setGoldClass(int goldClass) {
		this.goldClass = goldClass;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Time getShowTime() {
		return showTime;
	}

	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}
	
	public ArrayList<String> getTicketClass() {
		return ticketClass;
	}

	public void setTicketClass(ArrayList<String> ticketClass) {
		this.ticketClass = ticketClass;
	}
	
	public ArrayList<String> getQuantityList() {
		return quantityList;
	}

	public void setQuantityList(ArrayList<String> quantityList) {
		this.quantityList = quantityList;
	}

	public String bookTicketDetails(){
		//System.out.println("in book ticket session variable....."+ActionContext.getContext().getSession().get("userSession"));
		if(ActionContext.getContext().getSession().get("userSession")==null){
			System.out.println("user not registered.......");
			return ERROR;
		}
		else{
			
		System.out.println("in else part...will return success");
		System.out.println("in book ticket..."+scheduleId+"..moviename..."+movieName);
		
		try{
			
			java.sql.Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);

			sqlQuery = "select genclass,silverclass,goldClass,showDate,showTime from schedule where scheduleID='"+scheduleId+"';";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();

			while(rs.next()){
				
				genClass=rs.getInt(1);
				silverClass=rs.getInt(2);
				goldClass=rs.getInt(3);
				showDate=rs.getDate(4);
				showTime=rs.getTime(5);
			}

		}
		catch( Exception e){
			e.printStackTrace();

		}
		
		ticketClass.add(genClass+"");
		ticketClass.add(silverClass+"");
		ticketClass.add(goldClass+"");
		
		for(int i=10;i>=1;i--){
			quantityList.add(i+"");
		}
		
		return SUCCESS;
		}
	}
}
