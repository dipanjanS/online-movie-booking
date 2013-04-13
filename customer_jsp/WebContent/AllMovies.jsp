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

		<h1 align="center"><i class="icon-film icon-1x"></i>&nbsp;&nbsp;All Movies</h1>
			<hr size="4" color="#009987">
			<br />
		<s:iterator value="movieList">
			
			<div class="span6 offset1">
				<div style="background-image: url(siteimages/moviecontainer.jpg);height: 380px;width: 520px;background-repeat: no-repeat;">
				<div class="media" >
				<br/><br/><br/>
					<a class="pull-left" href="movieDetail.action?movie=<s:property value="movieName"/>"> <img class="img-polaroid"
						data-src="holder.js/64x64"
						src="images/<s:property value="imageURL"/>"
						style="width: 200px; height: 250px">
					</a>
					<div class="media-body">
						<h4 class="media-heading">
							<a
								href="movieDetail.action?movie=<s:property value="movieName"/>">

								<s:property value="movieName" />

							</a>
						</h4>
						<br />
						<font style="color: #B82E00;font-size: 16px;"><b>Rating: </b></font>
						<s:iterator var="rate" begin="1" end="%{rating}">
							<img src='./siteimages/displaystar.jpg' width="15px" />
						</s:iterator>
						<br /><br/>
						<font style="color: #B82E00; font-size: 16px;"><b>Genre: </b></font>
						<b><s:property value="category" /></b>
						<br /><br/>
						<font style="color: #B82E00;font-size: 16px;"><b>Cast: </b></font>
						<b><s:property value="cast" /></b>
						<br />
					</div>
				</div>
				<br/><br/><br/>
				</div>
				<br/><br/><br/><br/>
			</div>
			
		</s:iterator>
	</div>
</body>
</html>