<!doctype html>
<%@ page language="java" %>

<html lang="en">

<head>
 
	<title>Results</title>
	
	<%@ include file="info.jsp" %>
	
</head>

<body>

	<%@ include file="header.jsp" %>
		
	<h1>Results</h1>
			
	<h2>Return On Investment</h2>
	<span class="resulttitle">Estimated Time Till ROI :</span>
	<span class="result">0</span>
	<br />
	<span class="resulttitle">Expected Daily Output :</span>
	<span class="result">0</span>
	<br />
	<span class="resulttitle">System Cost :</span>
	<span class="result">0</span>
	<br />
	<span class="resulttitle">Savings (1st month) :</span>
	<span class="result">0</span>
	<br />
	<span class="resulttitle">Savings (Lifespan) :</span>
	<span class="result">0</span>
	<br />
	
	<form name="reset_data" action="/resetdata" method="post">
		<input type="submit" value="Reset Data" class="button"/>
	</form>
	
	<%@ include file="footer.jsp" %>
  
</body>
</html>