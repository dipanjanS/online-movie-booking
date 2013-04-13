package edu.iiitb.bookmyshow.action;

import com.opensymphony.xwork2.ActionSupport;

public class AboutUs extends ActionSupport{
	public String execute(){
		System.out.println("in about us page..");
		return SUCCESS;
	}
}
