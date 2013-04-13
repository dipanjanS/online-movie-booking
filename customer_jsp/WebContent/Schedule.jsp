<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
		<br />

		<div class="row-fluid">
			<div class="span7">
				<table class="table">

					<tr>
						<td style='text-align: left; vertical-align: middle; font-size: 20px; height: 80px'><b>Movie:</b></td>
						<td style='text-align: left; vertical-align: middle; font-size: 20px; height: 80px;color: Red'><b><s:property value="movieName"/></b></td>
					</tr>

					<tr>
						<td style='text-align: left;font-size: 20px; height: 80px'><b>Story:</b></td>
						<td style='text-align:justify; vertical-align: middle; font-size: 16px; height: 80px'><b><s:property value="storyLine" /></b></td>
					</tr>

					<tr>
						<td style='text-align: left;font-size: 20px; height: 40px; padding-top: 30px;'><b>Cast:</b></td>
						<td style='text-align: left;vertical-align:middle; font-size: 16px; height: 30px'><b><s:property value="cast" /></b></td>
					</tr>

					<tr>
						<td style='text-align: left;font-size: 20px; height: 40px; padding-top: 22px;'><b>Rating:</b></td>
						<td style='text-align: left;font-size: 20px; height: 40px; padding-top: 20px;'><s:iterator var="rate" begin="1" end="%{rating}">
								<img src='./siteimages/displaystar.jpg' width="20px" />
							</s:iterator></td>
					</tr>
					<table class="table">
					<tr>
						<td style='text-align: left;font-size: 20px; height: 40px; padding-top: 22px;'><b>Schedule:</b></td>
						<s:iterator value="scheduleList">
							<td style='text-align: left;font-size: 20px; height: 40px; padding-top: 22px;'><a
								href="bookTicket.action?scheduleId=<s:property value="scheduleId"/>&movieName=<s:property value="movieName"/>"><s:property
										value="showTime" /></a></td>
						</s:iterator>
					</tr>
					</table>
				</table>
			</div>
			<div class="span5">
				<table  class ="table" style="border-top: none;">
					<tr>
						<td style="padding-left: 75px"><iframe title="YouTube Video Player" width="350"
								height="300" src="<s:property value="trailer" />" seamless>
							</iframe></td>
					</tr>
					<tr>
						<td style="padding-left: 75px;padding-top: 50px;"><img src="images/<s:property value="imageURL"/>"
							class="img-polaroid" style="width: 350px; height: 450px" /></td>


						
					</tr>
				</table>
			</div>
		</div>
		<br /> <br />
		<br />
	</div>
</body>

</html>