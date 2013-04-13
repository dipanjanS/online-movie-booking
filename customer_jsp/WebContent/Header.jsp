<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>


<html>
<head>
<script>
</script>

</head>
<body>

<div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <div class="navbar-text pull-left"><img src="siteimages/brandlogo.gif"/></div>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li id="li_home"><a href="home"><i class="icon-home icon-2x"></i><font size="4px">&nbsp;&nbsp;Home</font></a></li>
         	  <li id="li_allmovies"><a href="allMovies"><i class="icon-film icon-2x"></i><font size="4px">&nbsp;&nbsp;Movies</font></a></li>
         	  <li id="li_alltheatres" ><a href="allTheatres"><i class="icon-building icon-2x"></i><font size="4px">&nbsp;&nbsp;Cinema Theatres</font></a></li>
              <li id="li_faq"><a href="faq"><i class="icon-question-sign icon-2x"></i><font size="4px">&nbsp;&nbsp;FAQ</font></a></li>
              <li id="li_about"><a href="about"><i class="icon-group icon-2x"></i><font size="4px">&nbsp;&nbsp;About Us</font></a></li>              
              <li id="li_account" class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-cogs icon-2x"></i><font size="4px">&nbsp;&nbsp;Account Information<b class="caret"></b></font></a>
                <ul class="dropdown-menu">
                  <li><a href="viewProfile"><font size="4px"><i class="icon-user"></i>&nbsp;&nbsp;&nbsp;View User Profile</font></a></li>
                  <li><a href="bookedHistory"><font size="4px"><i class="icon-credit-card"></i>&nbsp;&nbsp;View Booking History</font></a></li>
                  <li><a href="changePassword"><font size="4px"><i class="icon-unlock"></i>&nbsp;&nbsp;Change Password</font></a></li>
                </ul>
              </li>
              
              </ul>
  
          </div>
        </div>
      </div>
    </div>
    

</body>

</html>