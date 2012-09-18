<!doctype html>
<%@ page language="java" %>

<html lang="en">

<head>
 
	<title>Inverter Information</title>
	
	<%@ include file="info.jsp" %>
	
</head>

<body>

	<%@ include file="header.jsp" %>
		
	<h1>Inverter Information</h1>
	
	<div class="messagebar">
		Please enter your inverter information.
	</div>
	
	<aside>
	
		<ul>
			<li>Calculation Type</li>
			<li>Location</li>
			<li>Panels</li>
			<li>Panel Configuration</li>
			<li class="current">Inverter</li>
			<li>Electricity Information</li>
			<li>Results</li>
		</ul>
	
	</aside>
	
	<div class="body">
	
		<form name="inverter" action="/showresults" method="post">
			<div class="left">
				<label for="cost">Inverter Cost</label>
				<label for="efficiency">Inverter Efficiency</label>
				<label for="efficiencyloss">Annual Efficiency Loss</label>
			</div>
			<div class="right">
				<input type="text" name="cost" />
				<input type="text" name="efficiency" />
				<input type="text" name="efficiencyloss" />
				<input type="hidden" name="id" value=<%= request.getParameter("id") %> />
			</div>
			<input type="submit" value="Continue" class="button" />
		</form>

	</div>
	
	<div class="controlbox">
		<ul>
			<li><a class="left" href="calc_panel_config.jsp"></a></li>
			<li><a class="center" href="#"></a></li>
			<li><a class="right" href="calc_elec.jsp"></a></li>
		</ul>
	</div>
		
	<%@ include file="footer.jsp" %>
  
</body>
</html>