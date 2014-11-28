<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Homework to DATA1">
		<meta name="keywords" content="" />
		<meta name="author" content="Jakub Kadlčík">
		<meta name="copyright" content="FrostyX" />
		<meta name="robots" content="index,follow" />
		<link rel="icon" href="favicon.ico">

		<title>Blog | DATA1</title>

		<!-- Bootstrap core CSS -->
		<link href="/bundle/bootstrap/css/bootstrap.min.css" rel="stylesheet">

		<!-- Custom styles for this template -->
		<link href="/style.css" rel="stylesheet">

		<!-- Font Awesome -->
		<link rel="stylesheet" href="/bundle/font-awesome/css/font-awesome.min.css">

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<div class="container" id="page">
			<div class="navbar nav-pills" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="/">DATA1 - Blog</a>
					</div>
					<div class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right" id="menu">
							<li><a href="/">Posts</a></li>
							<li><a href="http://ondrejvaverka.com/vyuka/1415w/data1/10_home.php">Info</a></li>
							@if (Auth::check())
							<li><a href="/logout">Logout</a></li>
							<li><a href="/posts/{{ Auth::user()->login }}">{{ Auth::user()->login }}</a></li>
							@else
							<li><a href="/login">Login</a></li>
							@endif
						</ul>
					</div>
				</div>
			</div>

			<div id="content">
				@yield('content')
			</div>

		</div>

		<footer class="footer">
			<div class="container">
				<p class="text-muted">
					<a href="https://github.com/FrostyX/" title="FrostyX on GitHub.com">Jakub Kadlčík</a> &copy; 2014 |
					<a href="mailto:jakub.kadlcik@upol.cz">jakub.kadlcik@upol.cz</a>
				</p>
			</div>
		</footer>

		<!-- Bootstrap core JavaScript
		================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script src="/bundle/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>
