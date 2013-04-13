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
<script>
	$(document).ready(function() {
		$('#myCarousel').carousel({
			interval : 3000
		});
	});

	function flash() {
		var text = document.getElementById('flash');
		text.style.color = (text.style.color == 'red') ? 'blue' : 'red';
	}
	var clr = setInterval(flash, 300);
</script>

<style>
#div1 {
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
	border: 14px ridge #CCCCB2;
}
</style>
</head>
<body>
	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);"
		align="center">

		<br /> <br /> <br /> <br /> <br /> <br />
		<div class="row-fluid">
			<div class="span6">
				<br />
				<div id="myCarousel" class="carousel slide">

					<div class="carousel-inner">
						<div class="item active">
							<img src="resources/fluxslider/img/Banner.jpg" alt="" />
						</div>
						<div class="item">
							<img src="resources/fluxslider/img/greenhornet.jpg" />
						</div>
						<div class="item">
							<img src="resources/fluxslider/img/mi1.jpg" />
						</div>
						<div class="item">
							<img src="resources/fluxslider/img/transformers1.jpg" />
						</div>
						<div class="item">
							<img src="resources/fluxslider/img/bookmovies.jpg" alt="" />
						</div>
						<div class="item">
							<img src="resources/fluxslider/img/batman2.jpg" alt="" />
						</div>
						<div class="item">
							<img src="resources/fluxslider/img/bookmovies2.jpg" />
						</div>
						<div class="item">
							<img src="resources/fluxslider/img/booklogo.jpg" />
						</div>
						<div class="item">
							<img src="resources/fluxslider/img/avengers.jpg" />
						</div>
					</div>
					<a class="carousel-control left" href="#myCarousel"
						data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
						href="#myCarousel" data-slide="next">&rsaquo;</a>
				</div>
			</div>

			<div class="span5 offset1" id="div1"
				style="background-image: url(siteimages/signinbox.png); height: 355px;">
				<h3 id="flash"><i class="icon-film"></i>&nbsp;Book My Movie&nbsp;<i class="icon-film"></i></h3>
				<b>Please Enter your credentials below</b>

				<s:form action="signIn" method="post">

					<br />
					<div class="input-prepend">
						<span class="add-on"><i class="icon-user"
							style="color: black;"></i></span>
						<s:textfield name="userName" theme="simple"
							placeholder="Please enter username" cssClass="input-xlarge" />
					</div>
					<br />
					<div class="input-prepend">
						<span class="add-on"><i class="icon-key"
							style="color: black;"></i></span>
						<s:password name="password" theme="simple"
							placeholder="Please enter password" cssClass="input-xlarge" />
					</div>
					<br />
					<s:submit name="commandButton" id="mysubmit" value="LOGIN"
						cssClass="btn btn-primary" theme="simple"/>
						
								<a class="pull-right" href="cannotSignIn.action" style="padding-right: 80px"><font size="4px">Can't access your account ?</font> 
					</a>

					<s:if test="hasActionErrors()">
						<s:iterator value="actionErrors">

							<div class="span10 offset1" align="center">
								<br />
								<div class="alert alert-error">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<s:property />
								</div>
							</div>

						</s:iterator>
					</s:if>
				</s:form>


				<br /> <br />
			</div>
		</div>
		<br /> <br /> <br /> <br />
	</div>
</body>


</html>