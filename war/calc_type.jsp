<%@ page language="java" %>
<!doctype html>

<html lang="en">

<head>
	<meta charset="utf-8">
	
	<title>Calculation Type</title>
	
	<meta name="title" content="calculadora de energia solar">
	<meta name="description" content="A Solar Power Calculator.">
	<meta name="google-site-verification" content="">	
	<meta name="author" content="The Package">
	<meta name="Copyright" content="Copyright The Package 2012. All Rights Reserved.">
	
	<link rel="shortcut icon" href="img/favicon.ico">
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
	<!--[if lt IE 8 ]> <link rel="stylesheet" href="css/ie.css"> <![endif]-->
	<!--[if IE 8 ]> <link rel="stylesheet" href="css/ie8.css"> <![endif]-->
</head>

<body>

<div class="wrapper">

	<header>
		
		<h1><a href="">Calculadora de Energia Solar</a></h1>
		
		<nav>
		
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<img class="seperator" src="img/nav_sep.png" />
				<li><a href="calc_type.jsp">Calculator</a></li>
				<img class="seperator" src="img/nav_sep.png" />
				<li><a href="compare.jsp">Compare</a></li>
				<img class="seperator" src="img/nav_sep.png" />
				<li><a href="about.jsp">About</a></li>
				<img class="seperator" src="img/nav_sep.png" />
			</ul>
		
		</nav>
	
	</header>
	
	<article>
		
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
				<li>Electricity Details</li>
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
					<input type="radio" name="type" value = "new "/>
					<input type="radio" name="type" value = "new "/>
				</div>
				<input type="submit" value="Continue" class="button" />
			</form>
			
		</div>
		
		<div class="controlbox">
			<ul>
				<li><a class="left" href=""></a></li>
				<li><a class="center" href=""></a></li>
				<li><a class="right" href=""></a></li>
			</ul>
		</div>
	
	</article>
	
	<footer>
		
		<ul>
			<li><a href="">Contact</a></li>
			<li><a href="">Legal</a></li>
		</ul>
		
		<p>&copy; Copyright The Package 2012. All Rights Reserved.</p>
		
	</footer>

</div>

<script src="js/functions.js"></script>
  
</body>
</html>