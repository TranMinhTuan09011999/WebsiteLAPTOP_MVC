<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath }/" />
<meta charset="utf-8">
<title>LapTopMinhTuan</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<jsp:include page="menu.jsp"></jsp:include>
				<!--  -->
				<div class="span9" id="mainCol">
					<ul class="breadcrumb">
						<li><a href="user/home">Trang Chủ</a> <span class="divider">/</span></li>
						<li class="active">Liên Hệ</li>
					</ul>
					<h3 class="title1"> 
						<span>
							Liên Hệ Với Chúng Tôi
						</span>
					</h3>
					<hr class="soft">
				</div>
				<div class="row">
					<div class="span4">
						<h4>Địa Chỉ Liên Hệ</h4>
						<p>
							83/6, đường 385,<br> phường Tăng Nhơn Phú A, Quận 9, TPHCM <br> <br>Email: minhtuan@gmail.com<br>
							﻿Phone: 01213321897<br> Facebook: <a
								href="https://www.facebook.com/profile.php?id=100009171599550">http://facebook.com/TuanDTPH04388</a><br>
							Website: http://laptopminhtuan.com.vn<br>					
						</p>
					</div>

					<div class="span4">
						<h4>Thời Gian Mở Cửa</h4>
						<h5>Thứ 2 -> Thứ 6:</h5>
						<span> 08:30 AM - 09:00 PM </span> <br>
						<h5>Thứ 7:</h5>
						<span> 09:00 AM - 07:00 PM </span><br>
						<h5>Chủ Nhật:</h5>
						<span>12:30 PM - 06:00 PM</span> <br>
					</div>
					<br>
					<hr class="soft">
				</div>

				<!--  -->
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>



</html>