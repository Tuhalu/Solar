<!doctype html>
<%@ page language="java" %>

<html lang="en">
<head>
	
	<title>Calculation Type</title>
	
	<%@ include file="info.jsp" %>
	
</head>

<body>

	<%@ include file="header.jsp" %>
	
	<h1>Calculation Type</h1>
	
	<div class="messagebar">
		Please select the type of calculation you wish to run.
	</div>
	
	<aside>
	
		<ul>
			<li class="current">Calculation Type</li>
			<li>Location</li>
			<li>Panels</li>
			<li>Panel Configuration</li>
			<li>Inverter</li>
			<li>Electricity Information</li>
			<li>Results</li>
		</ul>
	
	</aside>

	<div class="body">

		<form name="calc_type" action="/createsystem" method="post">
			<div class="left">
				<label for="usage">New Installation</label>
				<label for="hourly">Existing Installation</label>
			</div>
			<div class="right">
				<input type="radio" name="type" value = "new "checked />
				<input type="radio" name="type" value = "new " disabled />
			</div>
			<input type="submit" value="Continue" class="button" />
		</form>
		
	</div>
	
	<div class="controlbox">
		<ul>
			<li><a class="left" href="#"></a></li>
			<li><a class="center" href="#"></a></li>
			<li><a class="right" href="calc_location.jsp"></a></li>
		</ul>
	</div>
	
	<%@ include file="footer.jsp" %>
  
</body>
</html>