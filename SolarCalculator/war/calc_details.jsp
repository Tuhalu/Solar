<!doctype html>
<%@ page language="java" %>

<html lang="en">

<head>
 
	<title>Panel Congfiguration</title>
	
	<%@ include file="info.jsp" %>
	
</head>

<body>

	<%@ include file="header.jsp" %>
		
	<h1>Panel Configuration</h1>
	
	<div class="messagebar">
		Please enter your panel's details
	</div>
	
	<aside>
	
		<ul>
			<li>Calculation Type</li>
			<li>Location</li>
			<li>Panels</li>
			<li class="current">Panel Configuration</li>
			<li>Inverter</li>
			<li>Electricity Information</li>
			<li>Results</li>
		</ul>
	
	</aside>
	
	<div class="body">
	
		<form name="panel_config" action="/panelsconfig" method="post">
			<div class="left">
				<label for="facing">Facing</label>
				<label for="angle">Roof Angle</label>
				<label for="bracing">Adjust for bracing?</label>
			</div>
			<div class="right">
				<input type="text" name="facing" />
				<input type="text" name="angle" />
				<input type="checkbox" name="bracing" value="yes" />
				<input type="hidden" name="id" value=<%= request.getParameter("id") %> />
			</div>
			<input type="submit" value="Continue" class="button" />
		</form>

	</div>
	
	<div class="controlbox">
		<ul>
			<li><a class="left" href="calc_panels.jsp"></a></li>
			<li><a class="center" href="#"></a></li>
			<li><a class="right" href="calc_inverter.jsp"></a></li>
		</ul>
	</div>
		
	<%@ include file="footer.jsp" %>
  
</body>
</html>