<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>

	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);">

		<br /> <br /> <br /> <br />
		<div class="alert alert-block alert-success" align="center">

			<h2 class="alert-heading" align="center">
				Success!
				</h2>
				Congratulations, you have successfully registered.
		</div>
		<br /> <br />
		<div align="center">
			<a href="home.action?userName=<s:property value="userName"/>"
				class="btn btn-primary">Return to Home</a>
		</div>
		<br />
		<br />
		<br />
	</div>
	
</body>

</html>