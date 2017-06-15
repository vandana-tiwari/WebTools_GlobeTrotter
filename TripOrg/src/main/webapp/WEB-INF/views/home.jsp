<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Globe-trotter</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700'
	rel='stylesheet' type='text/css'>
<link href="resources/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<script src="resources/js/jquery.min.js"></script>
<!---strat-date-piker---->
<link rel="stylesheet" href="resources/css/jquery-ui.css" />
<script src="resources/js/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker,#datepicker1").datepicker();
	});
</script>
<!---/End-date-piker---->
<link type="text/css" rel="stylesheet" href="resources/css/JFGrid.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/JFFormStyle-1.css" />
<script type="text/javascript" src="resources/js/JFCore.js"></script>
<script type="text/javascript" src="resources/js/JFForms.js"></script>

<script type="text/javascript">
	(function() {
		JC.init({
			domainKey : ''
		});
	})();
</script>

<script>
	$(function() {
		var pull = $('#pull');
		menu = $('nav ul');
		menuHeight = menu.height();

		$(pull).on('click', function(e) {
			e.preventDefault();
			menu.slideToggle();
		});

		$(window).resize(function() {
			var w = $(window).width();
			if (w > 320 && menu.is(':hidden')) {
				menu.removeAttr('style');
			}
		});
	});
</script>
</head>
<script>
	$(document).ready(
			function() {

				$("#datepicker1").datepicker(
						{
							dateFormat : "dd-M-yy",
							minDate : 0,
							onSelect : function(date) {
								var date2 = $('#datepicker1').datepicker(
										'getDate');
								date2.setDate(date2.getDate() + 1);
								$('#datepicker2').datepicker('setDate', date2);
								//sets minDate to dt1 date + 1
								$('#datepicker2').datepicker('option',
										'minDate', date2);
							}
						});
				$('#datepicker2').datepicker(
						{
							dateFormat : "dd-M-yy",
							onClose : function() {
								var dt1 = $('#datepicker1').datepicker(
										'getDate');
								var dt2 = $('#datepicker2').datepicker(
										'getDate');
								//check to prevent a user from entering a date below date of dt1
								if (dt2 <= dt1) {
									var minDate = $('#datepicker2').datepicker(
											'option', 'minDate');
									$('#datepicker2').datepicker('setDate',
											minDate);
								}
							}
						});
			});
</script>
<body>
	<div class="header_bg">
		<div class="wrap">
			<div class="header">
				<div class="logo">
					<a href="home.htm"><img src="resources/images/globetrotter.png"
						alt="" height ="50" width="200"></a>
				</div>
				<div class="h_right">
					<!--start menu -->
					<ul class="menu">


						<li class="active"><a href="home.htm">HOME</a></li> 
						<li style="list-style: none">|</li>
						<li><a href="apartments.htm">APARTMENTS</a></li> 
						<li style="list-style: none">|</li>
						<li><a href="contactus.htm">CONTACT US</a></li> 
						<li style="list-style: none">|</li>
						<!--  <li><a href="login.htm">LOGIN</a></li>-->
						<c:if test="${!empty sessionScope.loggedInUser}">
							<!--  Welcome ${sessionScope.loggedInUserName} ${sessionScope.loggedInUser}Name: -->

							<li><a href="acountInfo.htm">MY ACCOUNT</a></li> 
							<li style="list-style: none">|</li>
			<c:if test="${!empty sessionScope.isHost}">
								<li><a href="newAdvertisement.htm">POST A NEW ADVERTISEMENT</a></li>
								<li><a href="myListings.htm">MY LISTINGS</a></li> 
								<li style="list-style: none">|</li>
			</c:if>
							<li><a href="logout.htm">LOGOUT</a></li> 
							<li style="list-style: none">|</li>
		</c:if>

						<c:if test="${empty sessionScope.loggedInUser}">

							<li><a href="login.htm">LOGIN</a></li> 
							<li style="list-style: none">|</li>
			<li><a href="registerHost.htm">BECOME HOST</a></li> 
			
						</c:if>

						
					</ul>
					
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	
		
	<!--start main -->
		
			<!--start grids_of_3 -->
			<div class="grids_of_3">
				<div class="grid1_of_3">
					<div class="grid1_of_3_img">
						<img src="resources/images/apt1.jpg"
							alt="" height="300" width="400" /> 
						
					</div>
					<h4>
						<a href="#">1 Bed<span>59$</span></a>
					</h4>
					<p>This immaculate, professionally-designed apartment with a private deck and patio invites comfort, and exudes modern elegance. With 1 bedrooms, 1 bath, generous living space and stylish finishes, you'll enjoy a perfect setting for relaxing and entertaining. Beautiful mahogany hardwood floors and plenty of natural night flow throughout the home's open, airy layout. </p>
				</div>
				<div class="grid1_of_3">
					<div class="grid1_of_3_img">
						 <img src="resources/images/apt2.jpg"
							alt="" height="300" width="400"/> 
					
					</div>
					<h4>
						<a href="#">2 Bed<span>99$</span></a>
					</h4>
					<p>Masterful design and modern luxury are uniquely embodied in this 2 bedroom apartment. This one-of-a-kind house, created by New York architect James Carpenter, is sheathed in high-performance, museum-quality insulated glass atop an historic Art Deco loft building in the heart of Tribeca.</p>
				</div>
				<div class="grid1_of_3">
					<div class="grid1_of_3_img">
						<img src="resources/images/apt3.jpg"
							alt="" height="300" width="400" /> 
						
					</div>
					<h4>
						<a href="#">Entire apartment<span>159$</span></a>
					</h4>
					<p>Paradise at The Point! This luxurious 4 bedroom 4.5 bath Dienst-built estate on the Lake Norman peninsula is a dream home in a high-end community that's home to Trump National Golf Club. Spanning over 4900 SF, this magnificent waterfront residence graced by soaring ceilings and wall-to-wall windows is a haven for gazing at Lake Norman views from multiple vantage points.</p>
				</div>
				<div class="clear"></div>
			</div>
		
	
	<!--start main -->
	<div class="footer_bg">
		<div class="wrap">
			<div class="footer">
				<div class="copy">
					<p class="link">
						<span>© All rights reserved Globe-trotter</span>
					</p>
				</div>
				
				<div class="soc_icons">
					<ul>
						<li><a class="icon1" href="#"></a></li>
						<li><a class="icon2" href="#"></a></li>
						
					</ul>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
</html>