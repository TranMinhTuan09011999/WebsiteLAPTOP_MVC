<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!doctype html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="apple-touch-icon" sizes="76x76"
	href="login/images/apple-icon.png">
<link rel="icon" type="login/image/png" sizes="96x96"
	href="login/images/favicon.png">

<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<script src="assets/js/jquery-3.1.1.min.js"></script>
<title><s:message code="admin.login.text" /></title>

<link href="login/css/bootstrap.css" rel="stylesheet" />
<link href="login/css/coming-sssoon.css" rel="stylesheet" />

<!--     Fonts     -->
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.css"
	rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Grand+Hotel'
	rel='stylesheet' type='text/css'>

</head>
<style type="text/css">
*[id$=errors] {
	color: red;
	font-style: italic;
}
</style>
<body>
	<nav class="navbar navbar-transparent navbar-fixed-top"
		role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<div class="main"
		style="background-image: url('login/images/video_bg.jpg')">
		<video id="video_background" preload="auto" autoplay="true"
			loop="loop" muted="muted" volume="0">
			<source src="login/video/time.webm" type="video/webm">
			<source src="login/video/time.mp4" type="video/mp4">
			Video not supported
		</video>
		<!--    Change the image source '/images/video_bg.jpg')" with your favourite image.     -->

		<div class="cover black" data-color="black"></div>

		<!--   You can change the black color for the filter with those colors: blue, green, red, orange       -->

		<div class="container">
			<h1 class="logo cursive">LapTopminhtuan.com</h1>
			<!--  H1 can have 2 designs: "logo" and "logo cursive"           -->
			
			<div class="content">
				<h4 class="motto" style="min-height: 45px;">
					<s:message code="admin.login.title" />
				</h4>
				<div class="subscribe">
					<h5 class="info-text">
						<span style="color: white">${message}</span>
					</h5>
					<div class="row">
						<div class="col-md-4 col-md-offset-4 col-sm6-6 col-sm-offset-3 " style="margin-left:445px">
							<form:form class="form-inline" action="admin/forgot.htm"
								modelAttribute="account" >
								<br>
								<div class="form-group">
									<label style="color:white;" for="email">Email: </label>
									<form:input path="username" type="email"
										placeholder="Vui lòng nhập địa chỉ Email!!!"
										class="form-control transparent" /><form:errors path="username"/>
								</div>
								<span style="color: red">${message2}</span>
								<br>
								<br>
								<div class="form-group" style="width: 400px;">
									<a href="admin" type="submit"
										class="btn btn-success btn-fill"> <s:message
											code="admin.login" /></a>
									<button type="submit" class="btn btn-danger btn-fill">
										<s:message code="admin.getPass" />
									</button>
								</div>
							</form:form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="login/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="login/js/bootstrap.min.js" type="text/javascript"></script>
</html>
