<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>

<head>
<title>Insert title here</title>
<script>
	$(document).ready(function() {
		$("#panel").hide().fadeIn("slow");
	});
</script>
</head>

<body>

	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);">

		<br /> <br /> <br /> <br />
	<div align="center">
		<h2>
			<font style="color: orange;">Book Tickets for </font> <font
				style="font-size: 35px; color: black;">"<s:property
					value="returnMovie" />"
			</font>
		</h2>
</div>

		<br /> <br />
	<s:iterator value="cinemaList" status="st">
		<div class="span8 offset4">
				<div>
				<div class="media" >
				<br/><br/>
					<a class="pull-left" href="#"> <img class="img-polaroid"
						data-src="holder.js/64x64"
						src="http://maps.googleapis.com/maps/api/staticmap?center='+<s:property value="cinemaName" />+' '+<s:property value="address"/>' '+<s:property value="returnCity"/>+'&style=feature:road.local|element:labels&zoom=17&size=350x250&scale=2&maptype=hybrid&markers=icon:http://chart.apis.google.com/chart?chst=d_map_pin_icon|'+<s:property value="cinemaName" />+' '+<s:property value="address"/>+' '+<s:property value="returnCity"/>+'&sensor=false" width="300" height="150">
					</a>
					<div class="media-body">
						<h3 class="media-heading">
							
							<a
							href="schedule.action?cinemaId=<s:property value="cinemaId"/>&movieName=<s:property value="returnMovie"/>&cityName=<s:property value="returnCity"/>&showDate=<s:property value="returnDate"/>"><s:property
									value="cinemaName" /></a>
						</h3>
						<br />
						<font style="color: #B82E00;font-size: 18px;"><b>Address: </b></font>
						<b><s:property
								value="address" /></b>
						<br /><br/>
						<font style="color: #B82E00; font-size: 16px;"><b>Rating: </b></font>
						<s:iterator var="rate" begin="1" end="%{rating}">
								<img src='./siteimages/displaystar.jpg' width="15px" />
							</s:iterator>
						<br /><br/>
						
						<a href="schedule.action?cinemaId=<s:property value="cinemaId"/>&movieName=<s:property value="returnMovie"/>&cityName=<s:property value="returnCity"/>&showDate=<s:property value="returnDate"/>" class="btn btn-inverse">View Schedule</a>
						
					</div>
				</div>
				<br/><br/>
				</div>
	
			</div>
			</s:iterator>

	</div>
</body>

</html>