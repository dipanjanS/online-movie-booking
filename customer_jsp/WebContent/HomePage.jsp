<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>

<head>
<link rel="stylesheet"
	href="resources/jQuery/css/smoothness/jquery-ui-1.9.2.custom.css" />
<script src="resources/jQuery/js/jquery-1.8.3.js"></script>
<script src="resources/jQuery/js/jquery-ui-1.9.2.custom.js"></script>
<script src="resources/vticker/jquery.vticker.js"></script>
<link rel="stylesheet" href="resources/chosenselect/chosen.css" />
<script src="resources/chosenselect/chosen.jquery.js"></script>
<link rel="stylesheet" href="resources/fluxslider/css/stylebb.css" />
<script src="resources/fluxslider/js/flux.js"></script>


<script>
	$(document).ready(function() {
		$("#panel").hide().fadeIn("slow");
	});

	$(function() {
		var $scroller = $("#scroller");
		$scroller.vTicker('init', {
			height : 350,
			showItems : 4
		});

	});

	$(function() {
		$('.chzn-select').chosen();
		$('.chzn-select-deselect').chosen({
			allow_single_deselect : true
		});
	});

	$(function() {
		$(document).tooltip(
				{
					position : {
						my : "center bottom-20",
						at : "center top",
						using : function(position, feedback) {
							$(this).css(position);
							$("<div>").addClass("arrow").addClass(
									feedback.vertical).addClass(
									feedback.horizontal).appendTo(this);
						}
					}
				});
	});

	$(function() {
		if (!flux.browser.supportsTransitions)
			alert("Flux Slider requires a browser that supports CSS3 transitions");

		window.f = new flux.slider('#slider', {
			pagination : false,
			controls : true
		});
	});

	function flash() {
		var text = document.getElementById('flash');
		text.style.color = (text.style.color == 'red') ? 'orange' : 'red';
	}
	var clr = setInterval(flash, 300);
</script>



<style>
.ui-tooltip,.arrow:after {
	background: black;
	border: 1px solid white;
}

.ui-tooltip {
	padding: 10px 20px;
	color: white;
	border-radius: 20px;
	font: bold 14px "Helvetica Neue", Sans-Serif;
	text-transform: uppercase;
	box-shadow: 0 0 7px black;
}

.arrow {
	width: 70px;
	height: 16px;
	overflow: hidden;
	position: absolute;
	left: 50%;
	margin-left: -35px;
	bottom: -16px;
}

.arrow.top {
	top: -16px;
	bottom: auto;
}

.arrow.left {
	left: 20%;
}

.arrow:after {
	content: "";
	position: absolute;
	left: 20px;
	top: -20px;
	width: 25px;
	height: 25px;
	box-shadow: 6px 5px 9px -9px black;
	-webkit-transform: rotate(45deg);
	-moz-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	-o-transform: rotate(45deg);
	tranform: rotate(45deg);
}

.arrow.top:after {
	bottom: -20px;
	top: auto;
}

.ImageBorder {
	border-width: 5px;
	border-color: Black;
}
</style>

</head>

<body>

	<div class="container-fluid" id="panel"
		style="background-image: url(siteimages/bodypattern.png);">
		<br /> <br /> <br /> <br />
		<s:form action="searchTheatre" method="post">
			<div class="row-fluid">
				<div class="span6">
					<h4>
						<b>Welcome <s:property value="%{#session.userSession}" /></b>
					</h4>
				</div>
				

					<s:if test="hasActionErrors()">
						<div class="span2 offset4">
						<a href="register.action"><h4>
								<b>Register</a> | <a href="sign.action">Sign In</b>
							</h4></a>
						</div>
					</s:if>
					<s:else>
						<div class="span2 offset4">
							<a href="logout.action"><h4>
									<b>Sign Out</b>
								</h4></a>
						</div>
					</s:else>
				
			</div>


			<br />
			<br />

			<div class="row-fluid">

				<div class="span4">
					<div class="well">

						<br />
						<marquee>
							<p align="center"
								style="font-size: 25px; font-family: Segoe UI; color: #FF3300;">Book
								your tickets now!</p>
						</marquee>
						<br /> <br /> <br />

						<div class="input-prepend">
							<span class="add-on"><i class="icon-map-marker"
								style="color: red;"></i></span>
							<s:select name="returnCity" list="returnCityList" headerKey="0"
								headerValue="Select City" theme="simple" cssClass="chzn-select"
								title="Enter your city" style="width:350px" />
						</div>
						<br /> <br />

						<div class="input-prepend">
							<span class="add-on"><i class="icon-film"
								style="color: red;"></i></span>
							<s:select name="returnMovie" list="returnMovieList" headerKey="0"
								headerValue="Select Movie" theme="simple" cssClass="chzn-select"
								title="Enter the movie of your choice" style="width:350px" />
						</div>
						<br /> <br />

						<div class="input-prepend">
							<span class="add-on"><i class=" icon-calendar"
								style="color: red;"></i></span>
							<s:select name="returnDate" list="returnDateList" headerKey="0"
								headerValue="Select Date" theme="simple" cssClass="chzn-select"
								title="Enter the date of your choice" style="width:350px" />
						</div>


						<br /> <br /> <br />
						<div align="center">
							<s:submit value="Search Theatre"
								cssClass="btn btn-large btn-primary" theme="simple"></s:submit>
						</div>
						<br />

					</div>
				</div>

				<div class="span8">

					<div class="well well-large">
						<div id="slider" align="center">
						<img src="resources/fluxslider/img/Banner.jpg" alt="" />
							<img src="resources/fluxslider/img/avatar.jpg" alt="" /> 
							<img src="resources/fluxslider/img/batman2.jpg" alt="" />
							<img
								src="resources/fluxslider/img/ironman.jpg" alt="" /> 
								
								<img
								src="resources/fluxslider/img/tron.jpg" alt="" /> 
								<img src="resources/fluxslider/img/bookmovies2.jpg" alt="" />
								<img
								src="resources/fluxslider/img/greenhornet.jpg" />
								<img src="resources/fluxslider/img/3idiots.jpg" alt="" />
								<img src="resources/fluxslider/img/mi1.jpg" alt="" />
								<img src="resources/fluxslider/img/avengers.jpg" alt="" />
								<img src="resources/fluxslider/img/dabangg2.jpg" alt="" />
								<img src="resources/fluxslider/img/batman.jpg" alt="" />
								<img src="resources/fluxslider/img/mi2.jpg" alt="" />
								<img src="resources/fluxslider/img/transformers1.jpg" alt="" />
								<img src="resources/fluxslider/img/hobbit.jpg" alt="" />
								<img src="resources/fluxslider/img/transformers2.jpg" alt="" />
						</div>
					</div>
				</div>
			</div>
			<br />
			<br />

		</s:form>

		<div class="row-fluid">
			<div class="span7">

				<p id="flash" align="center"
					style="font-size: 25px; font-family: cursive; color: red;"><b>Top
					5 Movies !</b></p>

				<table class="table">

					<thead>
						<tr>
							<th
								style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Movie</h4></th>
							<th
								style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Category</h4></th>
							<th
								style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><h4>Rating</h4></th>
						</tr>
					</thead>

					<tbody>
						<s:iterator value="topMovieList">
							<tr>
								<td
									style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><a
									href="movieDetail.action?movie=<s:property value="movie"/>"><s:property
											value="movie" /></a></td>
								<td
									style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'><s:property
										value="category" /></td>
								<td
									style='text-align: left; vertical-align: middle; font-size: 18px; height: 30px'>
									<s:iterator var="rate" begin="1" end="%{rating}">
										<img src='./siteimages/displaystar.jpg' width="14px" />
									</s:iterator>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="span5">
				<div id="scroller"
					style="border: 2px solid silver; padding-right: 15px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; background-image: url(siteimages/tickerpattern.png);">
					<ul id="list">
						<li>Will you be able to watch The Hobbit if you have not
							watched Lord of the Rings? The answer is, yes. After all, this is
							where it all started. Watching The Hobbit is like eating a
							delicious cake and if you've watched the LOTR trilogy it would
							seem as if the cake was covered with the perfect icing.
							<hr size="3" color="#4D7094" width="510px">
						</li>
						<li>Dabangg 2. Let's just say, it does not disappoint.
							Debutant director Arbaaz Khan manages to retain much of the tempo
							and charm of the original. Much like the Munna Bhai series, it is
							simply another chapter in the life of our lead character.
							Everything we loved about the original is very much there, the
							menacing villain, the knuckle headed dialogues. Moments that make
							you giggle, not because of brilliant writing, simply because
							Chulbul Pandey delivers them with such childish charm that you
							can't help but smile. Then there is the spicy item number.
							<hr size="3" color="#4D7094" width="510px">
						</li>
						<li>This time it was because of a shooting that killed five
							random people. An old relationship with an ex-army colleague
							brings Jack Reacher (Tom Cruise) to surface back on the grid. The
							intention of this movie was to have movie-goers on the edge of
							their seats. In some parts it will definitely have you on the
							edge of your seats, but in others it will be quite the opposite.
							Moreover, fans of the novel may be a tad-bit disappointed when
							they see that Jack Reacher in the movie is only about 5 feet 7
							inches tall as opposed to the Jack Reacher in the book who is 6
							feet 5 inches tall. Though the character in the movie doesn't
							really match up on the height aspect, the rest of the character
							was pretty much the same.
							<hr size="3" color="#4D7094" width="510px">
						</li>
						<li>Rise Of The Guardians: "Eyes full of wonder, with dreams
							that are waiting to be fulfilled..." What a wonderful world it
							is! Like every year, this holiday season comes a specially made
							film for kids, teens and adults that shall leave you with that
							warm, fuzzy feeling that shall linger on for a long time after.
							The film comes with no set agenda, it is meant to be a family
							entertainer and it proves itself so. Peter Ramsey, previously
							associated with films such as Shark Tale, makes a great effort
							delivering a film that will be savored across all age groups.
							<hr size="3" color="#4D7094" width="510px">
						</li>
						<li>The most feared vampire, Count Dracula, returns in a
							slightly different avatar this year with Hotel Transylvania, also
							known as the monster's favorite haunt. A tweak to the tale of the
							legendary villain of all time, the script is semi-funny and
							adorable. And to know that it's actually no more than just a
							story of an overprotective father who doesn't want to let his 118
							year-old daughter out of his sight lest she encounters danger in
							the form of a human. All the world's ghosts, ghouls and monsters
							reunite at the Hotel Transylvania, a place that Count Dracula
							(Voice-Over by Adam Sandler) built for his counterparts to find
							solace every time they wanted to de-stress from the rather
							difficult lives they lead hiding away from the humans residing in
							the world.
							<hr size="3" color="#4D7094" width="510px">
						</li>
						<li>What happens when a ridiculous movie from 1984 is remade?
							Red Dawn (2012) is the answer to that question. In this case the
							"resurrection" of the 1984 movie, of the same name, proved to be
							a disaster. Jed, played by Chris Hemsworth, and his brother Matt,
							played by Josh Peck lead a group of teenagers against the North
							Korean invasion. They call themselves "The Wolverines". This
							pretty much sums up the movie. This movie is quite similar to
							Resident Evil (if you think of the outline) and not to mention a
							billion other Zombie movies. And as usual, everything begins in
							the United States of America. When you watch the movie you can
							still see Chris Hemsworths' Thor-ny role. Someone needs to
							elucidate the fact that here he was playing a mere mortal, not
							the God of thunder.
							<hr size="3" color="#4D7094" width="510px">
						</li>

					</ul>
				</div>
			</div>
		</div>
		<br /> <br />
	</div>

</body>
</html>