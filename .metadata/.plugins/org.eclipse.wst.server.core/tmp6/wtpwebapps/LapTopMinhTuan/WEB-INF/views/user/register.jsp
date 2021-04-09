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
<style type="text/css">
	*[id$=errors]{
	    margin-left: 10px;
		color:red;
		font-style:italic;
	}
</style>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="mainBody">
		<div class="container">
			<div class="row">
				<jsp:include page="menu.jsp"></jsp:include>
				<!--  -->
				<div class="span9">
					<ul class="breadcrumb">
						<li><a href="user/home">Trang chủ</a> <span class="divider">/</span></li>
						<li class="active">Đăng Ký</li>
					</ul>
					<h3 class="title1"> 
						<span>
							Đăng ký tài khoản
						</span>
					</h3>
					<div class="well">
						<form:form action="user/register" method="post"
							modelAttribute="register" class="form-horizontal"
							enctype="multipart/form-data" role="form" id="register">
							<h4>Thông tin cá nhân</h4>
							<h4 style="color: red;">${message}</h4>

							<div class="control-group">
								<label class="control-label">Họ và tên: <sup
									style="color: red">*</sup>
								</label>
								<div class="controls">
									<form:input path="fullName" id="fullName"
										placeholder="Full Name" cssClass="form-control" /><form:errors path="fullName" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Tên đăng nhập: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:input path="id" id="id" placeholder="Username"
										cssClass="form-control" /><form:errors path="id" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Mật khẩu: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:password path="password" id="password"
										placeholder="Password" cssClass="form-control" /><form:errors path="password" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Địa chỉ Email: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:input path="email" id="email" placeholder="Email Address"
										cssClass="form-control" /><form:errors path="email" />								
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Ngày sinh: <sup
									style="color: red">*</sup>
								</label>
								<div class="controls">
									<form:input path="birthday" id="birthday"
										placeholder="dd/mm/yyyy" cssClass="form-control" /><form:errors path="birthday" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Số điện thoại: <sup
									style="color: red">*</sup>
								</label>
								<div class="controls">
									<form:input path="numberPhone" id="numberPhone"
										placeholder="Number Phone" cssClass="form-control" /><form:errors path="numberPhone" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Giới tính: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:select path="gender" cssClass="form-control">
										<option value="0">-- Chọn --</option>
										<option value="1">Nam</option>
										<option value="2">Nữ</option>
									</form:select>
									<form:errors path="gender" />
								</div>
							</div>


							<div class="control-group">
								<label class="control-label">Địa chỉ: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:select path="address" cssClass="form-control">
										<option value="">-- Chọn --</option>
										<option value="TP Can Tho">TP. Cần Thơ</option>
										<option value="TP Da Nang">TP. Đà Nẵng</option>
										<option value="TP Hai Phong">TP. Hải Phòng</option>
										<option value="TP Ha Noi">TP. Hà Nội</option>
										<option value="TP Ho Chi Minh">TP. HCM</option>

										<option value="An Giang">An Giang</option>
										<option value="Ba Ria - Vung Tau">Bà Rịa - Vũng Tàu</option>
										<option value="Bac Giang">Bắc Giang</option>
										<option value="Ban Kan">Bắc Kạn</option>
										<option value="Bac Lieu">Bạc Liêu</option>
										<option value="Bac Ninh">Bắc Ninh</option>
										<option value="Ben Tre">Bến Tre</option>
										<option value="Binh Dinh">Bình Định</option>
										<option value="Binh Duong">Bình Dương</option>
										<option value="Binh Phuoc">Bình Phước</option>
										<option value="Binh Thuan">Bình Thuận</option>
										<option value="Ca Mau">Cà Mau</option>
										<option value="Cao Bang">Cao Bằng</option>
										<option value="Dak Lak">Đắk Lắk</option>
										<option value="Dak Nong">Đắk Nông</option>
										<option value="Dien Bien">Điện Biên</option>
										<option value="Dong Nai">Đồng Nai</option>
										<option value="Dong Thap">Đồng Tháp</option>
										<option value="Gia Lai">Gia Lai</option>
										<option value="Ha Giang">Hà Giang</option>
										<option value="Ha Nam">Hà Nam</option>
										<option value="Ha Tinh">Hà Tĩnh</option>
										<option value="Hai Duong">Hải Dương</option>
										<option value="Hau Giang">Hậu Giang</option>
										<option value="Hoa Binh">Hòa Bình</option>
										<option value="Hung Yen">Hưng Yên</option>
										<option value="Khanh Hoa">Khánh Hòa</option>
										<option value="Kien Giang">Kiên Giang</option>
										<option value="Kon Tum">Kon Tum</option>
										<option value="Lai Chau">Lai Châu</option>
										<option value="Lam Dong">Lâm Đồng</option>
										<option value="Lang Son">Lạng Sơn</option>
										<option value="Lao Cai">Lào Cai</option>
										<option value="Long An">Long An</option>
										<option value="Nam Dinh">Nam Định</option>
										<option value="Nghe An">Nghệ An</option>
										<option value="Ninh Binh">Ninh Bình</option>
										<option value="Ninh Thuan">Ninh Thuận</option>
										<option value="Phu Tho">Phú Thọ</option>
										<option value="Quang Binh">Quảng Bình</option>
										<option value="Quang Nam">Quảng Nam</option>
										<option value="Quang Ngai">Quảng Ngãi</option>
										<option value="Quang Ninh">Quảng Ninh</option>
										<option value="Quang Tri">Quảng Trị</option>
										<option value="Soc Trang">Sóc Trăng</option>
										<option value="Son La">Sơn La</option>
										<option value="Tay Ninh">Tây Ninh</option>
										<option value="Thai Binh">Thái Bình</option>
										<option value="Thai Nguyen">Thái Nguyên</option>
										<option value="Thanh Hoa">Thanh Hóa</option>
										<option value="Thua Thien Hue">Thừa Thiên Huế</option>
										<option value="Tien Giang">Tiền Giang</option>
										<option value="Tra Vinh">Trà Vinh</option>
										<option value="Tuyen Quang">Tuyên Quang</option>
										<option value="Vinh Long">Vĩnh Long</option>
										<option value="Vinh Phuc">Vĩnh Phúc</option>
										<option value="Yen Bai">Yên Bái</option>
										<option value="Phu Yen">Phú Yên</option>
									</form:select>
									<form:errors path="address" />
								</div>
							</div>

							<div class="control-group">
								<label class="control-label">Hình ảnh: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<input id="photo" name="file_image" type="file"
										class="form-control" /><form:errors path="photo" />
								</div>
							</div>

							<form:input path="activated" type="hidden" name="activated" value="false" />

							<div class="control-group">
								<div class="controls">
									<input class="btn btn-large btn-success" type="submit"
										value="Đăng Ký">
								</div>
							</div>
							
						</form:form>
					</div>

				</div>
				<!--  -->
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>


<link href="assets/jquery-ui/jquery-ui.min.css" rel="stylesheet" />
<script src="assets/jquery-ui/jquery-ui.min.js"></script>
<script>
	$(function() {
		$(".birthday").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("input#birthday").datepicker({
			dateFormat : 'dd/mm/yy'
		});
	});
</script>

</html>