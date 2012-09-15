<!doctype html>
<%@ page language="java" %>

<html lang="en">

<head>
	<meta charset="utf-8">
	
	<title>Panels</title>
	
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
		
		<h1><a href="index.html">Calculadora de Energia Solar</a></h1>
		
		<nav>
		
			<ul>
				<li><a href="index.html">Home</a></li>
				<img class="seperator" src="img/nav_sep.png" />
				<li><a href="calc_type.html">Calculator</a></li>
				<img class="seperator" src="img/nav_sep.png" />
				<li><a href="compare.html">Compare</a></li>
				<img class="seperator" src="img/nav_sep.png" />
				<li><a href="about.html">About</a></li>
				<img class="seperator" src="img/nav_sep.png" />
			</ul>
		
		</nav>
	
	</header>
	
	<article>
		
		<h1>Panels</h1>
		
		<div class="messagebar">
			Please enter your panel information.
		</div>
		
		<aside>
		
			<ul>
				<li>Calculation Type</li>
				<li>Location</li>
				<li class="current">Panels</li>
				<li>Panel Configuration</li>
				<li>Inverter</li>
				<li>Electricity Details</li>
				<li>Results</li>
			</ul>
		
		</aside>
		
		<div class="body">
		
			<form name="panels" action="" method="post">
				<div class="left">
					<label for="kilowatts">System Size (KW)</label>
					<label for="cost">Cost ($)</label>
					<label for="efficiencyloss">Annual Efficiency Loss</label>
				</div>
				<div class="right">
					<input type="text" name="kilowatts" />
					<input type="text" name="cost" />
					<input type="text" name="efficiencyloss" />
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


  
</body>
</html>