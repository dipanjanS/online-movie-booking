<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
		
			<h2 align="center">
				<font style="color: orange;"><s:property value="movie" /></font><br /><br /><br/>
			</h2>

		
		
		<div class="row-fluid">
		
			<div class="span12">
				
				<div class="span3">
					<img src="images/<s:property value="imageURL"/>"
						class="img-polaroid" style="width: 350px; height: 450px" />
				</div>

				<div class="span5">
					<h4 class="media-heading">Storyline</h4>
					<hr size="4" color="#009987">
					<s:property value="storyLine" />
					<br />
					<br />
					<br />
					<br />

					<h4 class="media-heading">Category</h4>
					<hr size="4" color="#009987">
					<s:property value="category" />
					<br />
					<br />
					<br />
					<br />

					<h4 class="media-heading">Cast</h4>
					<hr size="4" color="#009987">
					<s:property value="cast" />
					<br />
					<br />
					<br />
					<br />
					<h4 class="media-heading">Rating</h4>
					<hr size="4" color="#009987">
					<s:iterator var="rate" begin="1" end="%{rating}">
								<img src='./siteimages/displaystar.jpg' width="20px" />
					</s:iterator>
				</div>
				<div class="span4">
					<a class="pull-left" href="#"> <iframe
							title="YouTube Video Player" width="350" height="300"
							src="<s:property value="trailer" />" seamless> </iframe>
					</a>
				</div>
			</div>
		</div>
		<br/><br/><br/>
	</div>
</body>

</html>
