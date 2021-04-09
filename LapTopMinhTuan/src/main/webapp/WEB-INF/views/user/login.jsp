
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
<script src="themes/js/jquery-1.7.1.min.js"></script>
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
						<li class="active">Đăng nhập</li>
					</ul>
					<h3 class="title1"> 
						<span>
							Đăng nhập
						</span>
					</h3>
					<div class="well">
						<form:form action="user" method="post" modelAttribute="user"
							class="form-horizontal" role="form" id="register">
							<label style="color: red;">${message}</label>
							<div class="control-group">
								<label class="control-label">Tài Khoản: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:input path="id" placeholder="Username" cssClass="form-control"/><form:errors path="id"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">Mật khẩu: <sup
									style="color: red">*</sup></label>
								<div class="controls">
									<form:password path="password" id="password"
										placeholder="Password" cssClass="form-control" /><form:errors path="password"/>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label"> </label>
								<div class="controls">
									<input type="checkbox" name="remember" value="true" style="margin-top: -4px;">
									Ghi nhớ ?
									<a href="user/forgot"><span style="color: blue;">Quên mật khẩu</span></a>
								</div>
							</div>

							<div class="control-group">
								<div class="controls">
									<input class="btn btn-large btn-danger" type="submit"
										value="Đăng nhập">
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
</html>