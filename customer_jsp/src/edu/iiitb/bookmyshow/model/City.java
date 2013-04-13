package edu.iiitb.bookmyshow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Utils.DBManager;

public class City {
	static PreparedStatement statement=null;
	static ResultSet rs=null;
	static String sqlQuery="";
	int cityID;
	String cityName;
	static ArrayList<City> returnCity=new ArrayList<City>();
	public City(){
		
		
	}
	public City(int cityID, String cityName) {
		super();
		this.cityID = cityID;
		this.cityName = cityName;
		
	}

	public int getCityID() {
		return cityID;
	}
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public ArrayList<City> getReturnCity() {
		return returnCity;
	}

	public void setReturnCity(ArrayList<City> returnCity) {
		this.returnCity = returnCity;
	}
	public static ArrayList<City> fetchCity(){
		returnCity.clear();
try{
			Connection con=DBManager.getConnection();
			DBManager.useDataBase(con);
			
			sqlQuery = "select * from city;";
			statement=con.prepareStatement(sqlQuery);
			rs=statement.executeQuery();
			
			while(rs.next()){
				City cityOp = new City();
				cityOp.setCityID(rs.getInt(1));
				cityOp.setCityName(rs.getString(2));
				returnCity.add(cityOp);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		return returnCity;
	}
}
