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
				<div class="span9" id="mainCol">
					<ul class="breadcrumb">
						<li><a href="user/home">Trang Chủ</a> <span class="divider">/</span></li>
						<li class="active">Giới Thiệu</li>
					</ul>
					<h3 class="title1"> 
						<span>
							Giới Thiệu Hoạt Động Của Cửa Hàng
						</span>
					</h3>
					<hr class="soft">
					<h4>1. Hình Thức Mua Hàng Online LapTopMinhTuan</h4>
					<p>- Bạn đăt hàng trên Web → Nhân viên LapTopMinhTuan sẽ gọi
						điện thoại xác nhận đơn hàng của bạn → Giao hàng và Thu tiền tận
						nơi trên toàn quốc.</p>
					<br>

					<h4>2. Thời gian mở cửa tại các Shop của LapTopMinhTuan</h4>
					<p>- Các shop của LapTopMinhTuan mở cửa từ 8h tới 21h tất cả các
						ngày, kể cả thứ 7 và Chủ Nhật</p>
					<br>

					<h4>3. Địa chỉ các Shop của LapTopMinhTuan xem ở đâu ?</h4>
					<p>- Bạn bấm phần “Liên Hệ” trên đầu trang website để coi địa
						chỉ và số điện thoại các của các Shop của LapTopMinhTuan.</p>
					<br>	
					
					<h4>4. Địa chỉ các Shop của LapTopMinhTuan xem ở đâu ?</h4>
					<p>- Bạn bấm phần “Liên Hệ” trên đầu trang website để coi địa
						chỉ và số điện thoại các của các Shop của LapTopMinhTuan.</p>
					<br>					

				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>



</html>