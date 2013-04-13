<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
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
		<div class="alert alert-block alert-error" align="center">

			<h2 class="alert-heading" align="center">Error Occured!</h2>
			Oops! some error occured in processing your request. Please try again!
		</div>
		<div align="center">
		<br /><br/><br/>
		<a href="home" class="btn btn-primary">Return to Home</a>
		<br /><br/><br/>
		</div>
	</div>
</body>
</html>