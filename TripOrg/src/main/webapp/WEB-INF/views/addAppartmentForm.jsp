<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<title>Globe-trotter</title>
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
	<link href="resources/css/style.css" media="all" rel="stylesheet" type="text/css">
	<script src="resources/js/jquery.min.js">
	</script>
	<!---strat-date-piker==-->
	<link href="resources/css/jquery-ui.css" rel="stylesheet">
	<script src="resources/js/jquery-ui.js">
	</script><!--  <script>
    $(function() {
        $("#datepicker,#datepicker1").datepicker();
    });
</script>-->
	<!---/End-date-piker==-->
	<link href="resources/css/JFGrid.css" rel="stylesheet" type="text/css">
	<link href="resources/css/JFFormStyle-1.css" rel="stylesheet" type="text/css">
	<script src="resources/js/JFCore.js" type="text/javascript">
	</script>
	<script src="resources/js/JFForms.js" type="text/javascript">
	</script><!-- Set here the key for your domain in order to hide the watermark on the web server -->

	<script type="text/javascript">
	   (function() {
	       JC.init({
	           domainKey : ''
	       });
	   })();
	</script><!--nav-->

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
	<script>
	$(document).ready(function () {

	      $("#datepicker1").datepicker({
	          
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
</head>
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
						<li><a href="apartments.htm">APARTMENTS</a></li>
						<li style="list-style: none">|</li>
						<li><a href="contactus.htm">CONTACT US</a></li>
						<li style="list-style: none">| <!--  <li><a href="login.htm">LOGIN</a></li>-->
						 <!--  Welcome ${sessionScope.loggedInUserName} ${sessionScope.loggedInUser}Name: --></li>
						<li><a href="acountInfo.htm">MY ACCOUNT</a></li>
						<li style="list-style: none">| </li>
						<li class="active"><a href="newAdvertisement.htm">POST A NEW ADVERTISEMENT</a></li>
						<li><a href="myListings.htm">MY LISTINGS</a></li>
						<li style="list-style: none">| </li>
						<li><a href="logout.htm">LOGOUT</a></li>
						<li style="list-style: none">| </li>
						<li><a href="login.htm">LOGIN</a></li>
						<li style="list-style: none">|</li>
						<li><a href="registerHost.htm">BECOME HOST</a></li>
						<li style="list-style: none">
							<div class="clear"></div>
						</li>
					</ul><!-- start profile_details -->
				</div>
				<div class="clear"></div>
				
			</div>
		</div>
	</div>
	<h2>New Apartment Details ${sessionScope.loggedInUser}</h2>
	<div>
		<div class="contact_right">
			<div class="contact-form">
				<div>
					<h4>Title</h4>
				</div>
			</div>
		</div>
		<div class="contact_right">
			<div class="contact-form">
				<div></div>
				<h4>Category</h4>
			</div>
		</div>
	</div>
	<div class="contact_right">
		<div class="contact-form">
			<div>
				<h4>check-in:</h4>
				<div class="book_date btm"></div>
			</div>
		</div>
		<div class="contact_right">
			<div class="contact-form">
				<div>
					<h4>check-out</h4>
					<div class="book_date btm"></div>
				</div>
			</div>
		</div>
		<div class="contact_right">
			<div class="contact-form">
				<div>
					<h4>number of rooms</h4>1 2 3 4 5 
				</div>
			</div>
		</div>
		<div class="contact_right">
			<div class="contact-form">
				<div>
					<h4>number of beds</h4>1 2 3 4 5 
				</div>
			</div>
		</div>
		<div class="contact_right">
			<div class="contact-form">
				<div>
					<h4>number of bathrooms</h4>1 2 3 4 5 
				</div>
			</div>
		</div>
		<div class="contact_right">
			<div class="contact-form">
				<div>
					<h4>Furnished</h4><select class="frm-field required" id="country" name="furnished" onchange="change_country(this.value)">
						<option value="Yes">
							Yes
						</option>
						<option value="No">
							No
						</option>
					</select>
				</div>
			</div>
		</div>
		<div class="contact_right">
			<div class="contact-form">
				<div></div>
				<h4>Price per Night</h4>
			</div>
		</div>
	</div>
	<div class="contact_right">
		<div class="contact-form">
			<div>
				<h4>Maximum number of occupants</h4>1 2 3 4 5 
			</div>
		</div>
	</div>
	<div class="contact_right">
		<div class="contact-form">
			<div>
				<h4>Price per extra Occupant</h4>
			</div>
		</div>
	</div>
	<div class="contact_right">
		<div class="contact-form">
			<div>
				<h4>Description</h4>
			</div>
		</div>
	</div>
	<div class="contact_right">
		<div class="contact-form">
			<div>
				<h4>Street</h4>
			</div>
		</div>
	</div>
	<div class="contact_right">
		<div class="contact-form">
			<div>
				<h4>City</h4>
			</div>
		</div>
	</div>
	<div class="contact_right">
		<div class="contact-form">
			<div>
				<h4>State</h4>${state} 
			</div>
		</div>
	</div>
	<div class="contact_right">
		<div class="contact-form">
			<div>
				<h4>Zip</h4>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div>
		<h4>Select Photo (Max Size 5MB)</h4><input type="submit" value="Create Advertisement">
	</div>
</body>
</html>