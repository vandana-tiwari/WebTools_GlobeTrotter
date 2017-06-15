<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Globe-trotter</title>
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

<!---/End-date-piker---->
<link type="text/css" rel="stylesheet" href="resources/css/JFGrid.css" />
<link type="text/css" rel="stylesheet" href="resources/css/JFFormStyle-1.css" />
<script type="text/javascript" src="resources/js/JFCore.js"></script>
<script type="text/javascript" src="resources/js/JFForms.js"></script>
<!-- Set here the key for your domain in order to hide the watermark on the web server -->
<script type="text/javascript">
	(function() {
		JC.init({
			domainKey : ''
		});
	})();
</script>
<!--nav-->
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
$(document).ready(function () {

	   $("#datepicker1").datepicker({
	       dateFormat: "yy-mm-dd",
	       minDate: 0,
	       onSelect: function (date) {
	           var date2 = $('#datepicker1').datepicker('getDate');
	           date2.setDate(date2.getDate() + 1);
	           $('#datepicker2').datepicker('setDate', date2);
	           //sets minDate to dt1 date + 1
	           $('#datepicker2').datepicker('option', 'minDate', date2);
	       }
	   });
	   $('#datepicker2').datepicker({
	       dateFormat: "yy-mm-dd",
	       onClose: function () {
	           var dt1 = $('#datepicker1').datepicker('getDate');
	           var dt2 = $('#datepicker2').datepicker('getDate');
	           //check to prevent a user from entering a date below date of dt1
	           if (dt2 <= dt1) {
	               var minDate = $('#datepicker2').datepicker('option', 'minDate');
	               $('#datepicker2').datepicker('setDate', minDate);
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
					<a href="index.html"><img src="resources/images/globetrotter.png"
						alt="" height ="50" width="200"></a>
				</div>
				<div class="h_right">
					<!--start menu -->
					<ul class="menu">


						<li><a href="home.htm">HOME</a></li> 
						<li style="list-style: none">|</li>
						<li class="active"><a href="apartments.htm">APARTMENTS</a></li> 
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

<c:if test="${!empty sessionScope.noApt}">
	<font color="red"><p>No results found for the search... Please retry with a new search...</p></font>
	</c:if>
<form action="listApartmets.htm" method="post">

<div class="span_of_2">
				<div class="span2_of_1">
					<h4>check-in:</h4>
					<div class="book_date btm">
						
							<input class="date" id="datepicker1" name="checkIn" title="Select Check In Date" required = "true" type="text" >
						
					</div>	
					<div class="sel_room">
						<h4>number of rooms</h4>
						<select id="country" name="rooms" onchange="change_country(this.value)" class="frm-field required" readonly>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
		        		</select>
					</div>
					
					<div class="sel_room left">
						<h4>Furnished</h4>
						<select id="country" name="furnished" onchange="change_country(this.value)" class="frm-field required" readonly>
							<option value="Yes">Yes</option>
							<option value="No">No</option>
		        		</select>
					</div>	
					<div class="sel_room">
						<h4>Occupants</h4>
						<select id="country" name="occupants" onchange="change_country(this.value)" class="frm-field required" readonly>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
		        		</select>
					</div>	
				</div>
				<div class="span2_of_1">
					<h4>check-out</h4>
					<div class="book_date btm">
						
							<input id="datepicker2" class="date" name="checkOut" title="Select Check In Date" required = "true" type="text" >
						
					</div>	
					<div class="sel_room">
					<div class="contact_right">
					<div class="contact-form">
						<h4>City</h4>
						<input name = "city" pattern="[a-zA-Z]*" title="Enter a valid city. Only alphabets" required = "true" type="text" class="textbox">
					</div>	
					</div>
					</div>
					
					<div class="sel_room left">
						<h4>State:</h4>
						<c:set var="states" value="<%= new com.webToolsProj.TripOrg.POJO.Address().getStates() %>"/>
						<select id="country" name = "state" onchange="change_country(this.value)" class="frm-field required" >
							<c:forEach var="state" items="${states}">
							<option value="${state}">${state}</option>
							</c:forEach>
		        		</select>
					</div>	
					
				</div>
				<div class="clear"></div>
				<div class="res_btn">
					<div class="contact_right">
					<div class="contact-form">
					<input type="submit" value="Search" style="width: 280px;">
					</div>
					</div>
					</div>

			</div>
			

</form>
</body>
</html>