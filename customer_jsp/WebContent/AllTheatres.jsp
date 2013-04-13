<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

		<br /> <br /> <br /> <br /> <br />
		<h1 align="center"><i class="icon-building icon-1x"></i>&nbsp;&nbsp;All Theatres</h1>
			<hr size="4" color="#009987">
			<br />
		<s:iterator value="cinemaList">

			<div class="span10 offset3">
				<div>
					<div class="media">
						<br />
						<br /> <a class="pull-left"
							href="#">
							<img class="img-polaroid" data-src="holder.js/64x64"
							src="http://maps.googleapis.com/maps/api/staticmap?center='+<s:property value="cinemaName" />+' '+<s:property value="address"/>' '+<s:property value="cityName"/>+'&style=feature:road.local|element:labels&zoom=15&size=350x300&scale=2&maptype=hybrid&markers=icon:http://chart.apis.google.com/chart?chst=d_map_pin_icon|'+<s:property value="cinemaName" />+' '+<s:property value="address"/>+' '+<s:property value="cityName"/>+'&sensor=false"
							width="350" height="250">
						</a>
						<div class="media-body">
							<br/>
							<h3 class="media-heading">

								<s:property value="cinemaName" />
							</h3>
							<br /> <font style="color: #B82E00; font-size: 18px;"><b>Address:
							</b></font> <b><s:property value="address" /></b> <br />
							<br /> <font style="color: #B82E00; font-size: 18px;"><b>City:
							</b></font> <b><s:property value="cityName" /></b> <br />
							<br /> <font style="color: #B82E00; font-size: 16px;"><b>Number of screens:
							</b></font><b><s:property value="noOfScreens" /></b> <br/>
							<br /> <font style="color: #B82E00; font-size: 16px;"><b>Capacity:
							</b></font><b><s:property value="capacity" /></b> <br/><br/>
							<font style="color: #B82E00; font-size: 16px;"><b>Rating:
							</b></font>
							<s:iterator var="rate" begin="1" end="%{rating}">
								<img src='./siteimages/displaystar.jpg' width="15px" />
							</s:iterator>
							<br />
							<br />
							
							
							
						</div>
					</div>
					<br />
					<br />
				</div>
			</div>
		</s:iterator>
	</div>
</body>
</html>