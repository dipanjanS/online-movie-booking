<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<link rel="stylesheet"
	href="resources/jQuery/css/smoothness/jquery-ui-1.9.2.custom.css" />
<script src="resources/jQuery/js/jquery-1.8.3.js"></script>
<script src="resources/jQuery/js/jquery-ui-1.9.2.custom.js"></script>
<script src="styles/js/bootstrap.js"></script>
<link href="styles/css/bootstrapunited.css" rel="stylesheet">
<link href="styles/css/font-awesome.css" rel="stylesheet">

<script>

function GetCurrentPageName() { 
	var PageURL = document.location.href; 
	var PageName = PageURL.substring(PageURL.lastIndexOf('/') + 1); 

	return PageName.toLowerCase() ;
}

$(document).ready(function(){	
	var CurrPage = GetCurrentPageName();
	console.log(CurrPage);
	
	switch(CurrPage){
		case 'home':
	  		$('#li_home').addClass('active') ;
	  		break;
		case 'allmovies':
			$('#li_allmovies').addClass('active') ;
	  		break;
		case 'alltheatres':
	 		$('#li_alltheatres').addClass('active') ;
	  		break;
		case 'faq':
	 		$('#li_faq').addClass('active') ;
	  		break;
		case 'about':
	 		$('#li_about').addClass('active') ;
	  		break;
		case 'viewprofile':
	 		$('#li_account').addClass('active') ;
	  		break;
		case 'bookedhistory':
	 		$('#li_account').addClass('active') ;
	  		break;
		case 'changepassword':
	 		$('#li_account').addClass('active') ;
	  		break;
	}
});

</script>


</head>
<body>

	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />


</body>
</html>